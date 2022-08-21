package com.example.MusalaSoftEnwereVincent.service;


import com.example.MusalaSoftEnwereVincent.Response.*;
import com.example.MusalaSoftEnwereVincent.model.Drone;

import java.util.List;


public interface DroneService {

    Drone findDroneById(String serialNumber);
    List<Drone> getAllDrones();
    RegisterResponse createDrone(Drone drone);

    AvailableDroneResponse getAvailableDrones(List<Drone> allDroneLists);

    BatteryLevelResponse checkBatteryLevel(String serialNumber);

    LoadDroneResponse loadMedication(String droneSerialCode, String medicationCode);

    LoadedMedicationResponse loadedMedicationsForADrone(String serialNumber);

    void periodicCheckForBatteryHealth(List<Drone> drones);


}
