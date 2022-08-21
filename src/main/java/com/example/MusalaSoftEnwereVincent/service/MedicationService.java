package com.example.MusalaSoftEnwereVincent.service;
import com.example.MusalaSoftEnwereVincent.model.Medication;

import java.util.List;

public interface MedicationService {
    Medication createMedication(Medication medication);

    List<Medication> getMedications();

    Medication findMedicationByCode(String code);
    boolean validateCode(String code);

    boolean validateName(String name);
}
