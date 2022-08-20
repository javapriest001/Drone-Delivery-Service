package com.example.MusalaSoftEnwereVincent.service;


import com.example.MusalaSoftEnwereVincent.enumeration.State;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;

import java.util.List;


public interface DroneService {

    Drone findDroneById(String serialNumber);
    List<Drone> getAllDrones();
    Drone createDrone(Drone drone);
    State checkingLoadStatus(String serialNumber);

    List<Drone> getAvailableDrones(List<Drone> allDroneLists);
    boolean loadDrone(String serialNumber , List<Medication> medicationList);
    int checkBatteryLevel(String serialNumber);

    short calculateMedicationWeight(List<Short> medicationList);

    List<Short> listOfMedicationWeight(List<Medication> medicationList);



}
