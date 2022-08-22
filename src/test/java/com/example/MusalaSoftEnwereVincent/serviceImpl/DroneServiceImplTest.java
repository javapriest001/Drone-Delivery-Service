package com.example.MusalaSoftEnwereVincent.serviceImpl;

import com.example.MusalaSoftEnwereVincent.Response.*;
import com.example.MusalaSoftEnwereVincent.enumeration.Model;
import com.example.MusalaSoftEnwereVincent.enumeration.State;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.DroneRepository;
import com.example.MusalaSoftEnwereVincent.repository.MedicationRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.MusalaSoftEnwereVincent.enumeration.Model.HEAVYWEIGHT;
import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DroneServiceImplTest {

    @Mock
    DroneRepository droneRepository;

    @Mock
    MedicationRepository medicationRepository;
    @InjectMocks
    DroneServiceImpl droneService;

    Drone testDrone;
    Medication testMedication;

    LocalDateTime localDateTime;

    @BeforeEach
    public void setUp() {
        localDateTime = LocalDateTime.of(2022, AUGUST,3,6,30,40,50000);
        testDrone = new Drone("DRN1" , HEAVYWEIGHT , (short) 100, 80 , State.IDLE , null );
        when(droneRepository.findById(testDrone.getSerialNumber()))
                .thenReturn(Optional.of(testDrone));
        testMedication = new Medication("MD1" , "Flagyl" , 100 , "0xdef5ee");
        when(medicationRepository.findById(testMedication.getCode()))
                .thenReturn(Optional.of(testMedication));
    }

    @Test
    public void testRegisterDrone(){
        //Create A Mock of Drone
        Drone drone = new Drone("DRN1" , HEAVYWEIGHT , (short) 100, 80 , State.IDLE , null );
        RegisterResponse registerResponse = new RegisterResponse("Success" ,localDateTime , "DRN1" );
        when(droneRepository.save(drone)).thenReturn(drone);
        var actual = droneService.createDrone(drone);
        actual.setTimesStamp(localDateTime);
        assertEquals( actual, registerResponse);
    }

    @Test
    public void testFindDroneById(){
        Drone drone = new Drone("DRN1" , HEAVYWEIGHT , (short) 100, 80 , State.IDLE , null );
        when(droneRepository.findById("DRN1")).thenReturn(Optional.of(drone));
        assertEquals(droneService.findDroneById("DRN1") , drone);
    }

    @Test
    public void testGetAvailableDrones() {
        Drone drone = new Drone("DRN1", HEAVYWEIGHT, (short) 100, 80, State.IDLE, null);
        Drone drone1 = new Drone("DRN2", HEAVYWEIGHT, (short) 10000, 24, State.IDLE, null);
        Drone drone3 = new Drone("DRN3", HEAVYWEIGHT, (short) 100, 85, State.IDLE, null);
        List<Drone> droneList = new ArrayList<>(Arrays.asList(drone, drone1, drone3));
        List<Drone> availableDroneList = new ArrayList<>(Arrays.asList(drone, drone3));
        AvailableDroneResponse availableDroneResponse = new AvailableDroneResponse("success", localDateTime, availableDroneList);
        var actual = droneService.getAvailableDrones(droneList);
        actual.setTimesStamp(localDateTime);
        assertEquals(actual, availableDroneResponse);
    }

    @Test
    public void testLoadDroneResponse(){
        Drone drone = new Drone("DRN1" , HEAVYWEIGHT , (short) 100, 80 , State.IDLE , null );
        Medication medication1 = new Medication("MD1" , "Flagyl" , 100 , "0xdef5ee");
        Medication medication2 = new Medication("MD2" , "7-keys" , 46 , "0xdef5ee");
        Medication medication3 = new Medication("MD3" , "Refnol" , 100 , "0xdef5ee");
        List<Medication> medicationList = new ArrayList<>(Arrays.asList(medication1));
        LoadDroneResponse loadDroneResponse = new LoadDroneResponse("success" , localDateTime , drone.getSerialNumber(), medicationList);
        var actual = droneService.loadMedication(drone.getSerialNumber(),  medication1.getCode());
        actual.setTimesStamp( localDateTime);
        assertEquals(actual , loadDroneResponse);
    }

    @Test
    public void testLoadedMedicationResponse(){
        Medication medication1 = new Medication("MD1" , "Flagyl" , 100 , "0xdef5ee");
        List<Medication> medicationList = new ArrayList<>(Arrays.asList(medication1));
        LoadedMedicationResponse loadDroneResponse = new LoadedMedicationResponse("success" , localDateTime, testDrone.getSerialNumber() ,medicationList );
        var actual = droneService.loadedMedicationsForADrone(testDrone.getSerialNumber());
        actual.setTimesStamp(localDateTime);
        actual.setMedications(medicationList);
        assertEquals(actual , loadDroneResponse);
    }

    @Test
    public void testBatteryLevelResponse(){
        BatteryLevelResponse batteryLevelResponse = new BatteryLevelResponse("success" , localDateTime , testDrone.getSerialNumber() , testDrone.getBatteryCapacity());
        var actual = droneService.checkBatteryLevel(testDrone.getSerialNumber());
        actual.setTimesStamp(localDateTime);
        assertEquals(batteryLevelResponse , actual);
    }




}