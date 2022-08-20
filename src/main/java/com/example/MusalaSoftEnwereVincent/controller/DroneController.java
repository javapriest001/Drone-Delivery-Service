package com.example.MusalaSoftEnwereVincent.controller;

import com.example.MusalaSoftEnwereVincent.enumeration.State;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/drone")
public class DroneController {


    private final  DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping(value = "/drones")
    public List<Drone> ListOfDrones(){
        return droneService.getAllDrones();
    }

    @PostMapping(value = "/register")
    public Drone registerDrone(@RequestBody  Drone drone){
        return droneService.createDrone(drone);
    }

    @GetMapping(value = "/availabledrones")
    public List<Drone> listOfAvailableDrones(){
        return droneService.getAvailableDrones(ListOfDrones());
    }
    @GetMapping(value="/drone/{serialNumber}")
    public Drone getDroneById(@PathVariable String serialNumber){
        return droneService.findDroneById(serialNumber);
    }
    @GetMapping(value="/status/{serialNumber}")
    public State checkStatus(@PathVariable String serialNumber){
        return droneService.checkingLoadStatus(serialNumber);
    }

    @GetMapping(value="/{serialNumber}/medications")
    public List<Medication> getMedications(@PathVariable String serialNumber){
        return getDroneById(serialNumber).getMedication();
    }

    @GetMapping(value="/{serialNumber}/battery")
    public int getBattery(@PathVariable String serialNumber){
        return droneService.checkBatteryLevel(serialNumber);
    }

//    @PostMapping(value="/drone/{serialNumber}/load")
//    public int loadDrone(@PathVariable String serialNumber){
//
//       // return droneService.loadDrone(serialNumber, );
//    }


}
