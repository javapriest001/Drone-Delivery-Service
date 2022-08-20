package com.example.MusalaSoftEnwereVincent.serviceImpl;

import com.example.MusalaSoftEnwereVincent.enumeration.State;
import com.example.MusalaSoftEnwereVincent.exception.DroneNotFoundException;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.DroneRepository;
import com.example.MusalaSoftEnwereVincent.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

//TODO: Introduce a periodic task to check drones battery levels and create history/audit event log for this.
//TODO: Unit Testing;


@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public  Drone findDroneById(String serialNumber){
        return droneRepository.findById(serialNumber)
                .orElseThrow(()-> new DroneNotFoundException(serialNumber));
    }

    @Override
    public  List<Drone> getAllDrones(){
        return  droneRepository.findAll();
    }


    @Override
    public Drone createDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public State checkingLoadStatus(String serialNumber) {
        return  findDroneById(serialNumber).getStateOfDuty();
    }

    @Override
    public List<Drone> getAvailableDrones(List<Drone> allDroneLists) {
            return  allDroneLists.stream().filter(drone -> drone.getBatteryCapacity() >= 25 && drone.getStateOfDuty() == State.IDLE)
                        .toList();
    }


    @Override
    public boolean loadDrone(String serialNumber, List<Medication> medicationList) {
        boolean isLoaded = false;
        if(droneRepository.findById(serialNumber).isPresent()){
            //if (droneRepository.existsById())
            Drone drone = findDroneById(serialNumber);
            List<Short> weightListOfExistingDrone = listOfMedicationWeight(drone.getMedication());
            List<Short> inputWeightList = listOfMedicationWeight(medicationList);
            short count = calculateMedicationWeight(weightListOfExistingDrone);
            short inputMedicationCount = calculateMedicationWeight(inputWeightList);

            if (drone.getBatteryCapacity() >= 25){
                if(checkingLoadStatus(serialNumber) == State.IDLE || checkingLoadStatus(serialNumber) == State.LOADING && count < 500 ){
                    if (count + inputMedicationCount <= 500){
                        drone.setMedication(medicationList);
                        drone.setStateOfDuty(State.LOADED);
                        isLoaded = true;
                    } else{
                        System.out.println("Overloaded");
                    }
                }
            }
        }
        return isLoaded;
    }



    public short calculateMedicationWeight(List<Short> medicationList){
        short count = 0;
        for(Short shortNum: medicationList){
            count += shortNum;
        }
        return count;
    }

    public List<Short> listOfMedicationWeight(List<Medication> medicationList){
       return medicationList.stream().map(Medication::getWeight).collect(Collectors.toList());
    }

    @Override
    public int checkBatteryLevel(String serialNumber) {
        return findDroneById(serialNumber).getBatteryCapacity();
    }
}
