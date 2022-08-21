package com.example.MusalaSoftEnwereVincent.exception;

public class MedicationNotFoundException extends RuntimeException{
    public MedicationNotFoundException(String medId){
        super("Medication with " + medId + "not Found");
    }
}
