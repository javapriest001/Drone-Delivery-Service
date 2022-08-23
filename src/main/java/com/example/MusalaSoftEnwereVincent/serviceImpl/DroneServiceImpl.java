package com.example.MusalaSoftEnwereVincent.serviceImpl;
import com.example.MusalaSoftEnwereVincent.Response.*;
import com.example.MusalaSoftEnwereVincent.enumeration.State;
import com.example.MusalaSoftEnwereVincent.exception.DroneNotFoundException;
import com.example.MusalaSoftEnwereVincent.exception.ExcessWeightException;
import com.example.MusalaSoftEnwereVincent.exception.MedicationNotFoundException;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.DroneRepository;
import com.example.MusalaSoftEnwereVincent.repository.MedicationRepository;
import com.example.MusalaSoftEnwereVincent.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//TODO: Introduce a periodic task to check drones battery levels and create history/audit event log for this.
//TODO: Unit Testing;


@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    static List<Medication> medications = new ArrayList<>();
    static Map<Medication , String> medicationsList = new HashMap<>();

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository , MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    public  Drone findDroneById(String serialNumber){
       Drone searchedDrone = droneRepository.findById(serialNumber)
                .orElseThrow(()-> new DroneNotFoundException(serialNumber));
     //  searchedDrone.setMedication(medications);
        return searchedDrone;
    }

    @Override
    public  List<Drone> getAllDrones(){
        return  droneRepository.findAll();
    }

    @Override
    public RegisterResponse createDrone(Drone drone) {
         droneRepository.save(drone);
         return new RegisterResponse("Success", LocalDateTime.now(),  drone.getSerialNumber() );
    }

    @Override
    public AvailableDroneResponse getAvailableDrones(List<Drone> allDroneLists) {
            List<Drone> availableDrones =   allDroneLists.stream().filter(drone -> drone.getBatteryCapacity() >= 25 && drone.getStateOfDuty() == State.IDLE)
                        .toList();
            return  new AvailableDroneResponse("success" , LocalDateTime.now() , availableDrones);
    }

    public LoadDroneResponse loadMedication(String droneSerialCode, String medicationCode){
        LoadDroneResponse loadDroneResponse = new LoadDroneResponse();
        Drone drone = findDroneById(droneSerialCode);
        Medication medication = medicationRepository.findById(medicationCode)
                .orElseThrow(()-> new MedicationNotFoundException(medicationCode));
//        List<Medication> medications = new ArrayList<>();
        int totalLoadedDroneWeight = drone.getMedication().stream()
                .map(Medication::getWeight).toList()
                .stream().reduce(0, Integer::sum);

        if(drone.getStateOfDuty() == State.IDLE){
           if (drone.getBatteryCapacity() >= 25){
               if(totalLoadedDroneWeight <= 500){
                   if (totalLoadedDroneWeight + medication.getWeight() <= 500){
                       drone.setStateOfDuty(State.LOADING);
                       if (!drone.getMedication().contains(medication)){
                          // drone.getMedication().add(medication);
                           medicationsList.put(medication, droneSerialCode);
                           medicationsList.forEach((key , value) ->{
                               if (value.equalsIgnoreCase(droneSerialCode)){
                                   drone.getMedication().add(key);
                               }
                           });
                          // drone.setMedication(medications);
                           drone.setStateOfDuty(State.LOADED);
                           loadDroneResponse = new LoadDroneResponse("success" , LocalDateTime.now() , droneSerialCode , drone.getMedication());
                       }
                   }else {
                       throw new ExcessWeightException("The medication Weight Has Exceeded The Accepted Weight");
                   }
               }else{
                   throw new ExcessWeightException("You Cant Add More Than 500gr to the Drone");
               }
           }
        }
        return loadDroneResponse;
    }

    @Override
    public LoadedMedicationResponse loadedMedicationsForADrone(String serialNumber) {
        Drone drone = findDroneById(serialNumber);
        medicationsList.forEach((key , value) ->{
            if (value.equalsIgnoreCase(serialNumber)){
                drone.getMedication().add(key);
            }
        });
        return  new LoadedMedicationResponse("success" , LocalDateTime.now() , drone.getSerialNumber() ,  drone.getMedication() );
    }

    @Override
    public void periodicCheckForBatteryHealth(List<Drone> drones) {
        Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drones.forEach(drone ->  logger
                        .info("Battery Level for --- " + drone.getSerialNumber() + " is " + drone.getBatteryCapacity()));
            }
        } ,2000, 200000);


    }

    @Override
    public BatteryLevelResponse checkBatteryLevel(String serialNumber) {
        int batteryLevel = findDroneById(serialNumber).getBatteryCapacity();
        return  new BatteryLevelResponse("success" , LocalDateTime.now() , serialNumber , batteryLevel);
    }
}
