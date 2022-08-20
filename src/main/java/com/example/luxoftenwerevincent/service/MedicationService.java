package com.example.luxoftenwerevincent.service;


import com.example.luxoftenwerevincent.DTO.MedicationDTO;
import com.example.luxoftenwerevincent.model.Medication;

import java.util.Optional;

public interface MedicationService {
    Medication createMedication(MedicationDTO medicationDTO);

    boolean validateCode(String code);

    boolean validateName(String name);
}
