package com.example.luxoftenwerevincent.service;


import com.example.luxoftenwerevincent.enumeration.State;
import com.example.luxoftenwerevincent.model.Drone;
import com.example.luxoftenwerevincent.model.Medication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface DroneService {

    Drone findDroneById(String serialNumber);
    List<Drone> getAllDrones();
    Drone createDrone(Drone drone);
    State checkingLoadStatus(String serialNumber);
    boolean loadDrone(String serialNumber , List<Medication> medicationList);
    int checkBatteryLevel(String serialNumber);

    short calculateMedicationWeight(List<Short> medicationList);

    List<Short> listOfMedicationWeight(List<Medication> medicationList);



}
