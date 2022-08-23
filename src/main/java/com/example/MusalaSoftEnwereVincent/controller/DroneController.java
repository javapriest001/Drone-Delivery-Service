package com.example.MusalaSoftEnwereVincent.controller;

import com.example.MusalaSoftEnwereVincent.Response.*;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.service.DroneService;
import com.example.MusalaSoftEnwereVincent.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class DroneController {


    private final  DroneService droneService;
    private final  MedicationService medicationService;


    @Autowired
    public DroneController(DroneService droneService , MedicationService medicationService) {
        this.droneService = droneService;
        this.medicationService = medicationService;
    }

    @GetMapping(value = "/drones")
    public ResponseEntity<List<Drone>> ListOfDrones(){
        return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK) ;
    }

    @GetMapping(value = "/meds")
    public List<Medication> ListOfMedications(){
        return medicationService.getMedications();
    }

    @PostMapping(value = "/register")
    public RegisterResponse registerDrone(@RequestBody  Drone drone){
        return droneService.createDrone(drone);
    }

    @GetMapping(value = "/availabledrones")
    public AvailableDroneResponse listOfAvailableDrones(){
        droneService.periodicCheckForBatteryHealth(droneService.getAllDrones());
        return droneService.getAvailableDrones(droneService.getAllDrones());
    }

    @GetMapping(value="/drone/{serialNumber}")
    public Drone getDroneById(@PathVariable("serialNumber") String serialNumber){
        return droneService.findDroneById(serialNumber);
    }

    @GetMapping(value="/{serialNumber}/medications")
    public LoadedMedicationResponse getMedications(@PathVariable("serialNumber")  String serialNumber){
        return droneService.loadedMedicationsForADrone(serialNumber);
    }

    @GetMapping(value="/{serialNumber}/battery")
    public BatteryLevelResponse getBattery(@PathVariable("serialNumber") String serialNumber){
        return droneService.checkBatteryLevel(serialNumber);
    }

    @GetMapping(value="/{serialNumber}/load/{medCode}")
    public LoadDroneResponse loadDrone(@PathVariable(value = "serialNumber") String serialNumber , @PathVariable(value = "medCode") String medCode){
        return droneService.loadMedication(serialNumber, medCode);
    }



}
