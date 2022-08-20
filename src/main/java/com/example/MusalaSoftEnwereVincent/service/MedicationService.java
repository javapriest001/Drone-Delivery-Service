package com.example.MusalaSoftEnwereVincent.service;


import com.example.MusalaSoftEnwereVincent.DTO.MedicationDTO;
import com.example.MusalaSoftEnwereVincent.model.Medication;

public interface MedicationService {
    Medication createMedication(MedicationDTO medicationDTO);

    boolean validateCode(String code);

    boolean validateName(String name);
}
