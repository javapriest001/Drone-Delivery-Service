package com.example.MusalaSoftEnwereVincent.exception;

public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException(String droneId){
        super("Drone with " + droneId + "not Found");
    }
}
