package com.example.MusalaSoftEnwereVincent.serviceImpl;

import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.MedicationRepository;
import com.example.MusalaSoftEnwereVincent.service.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicationServiceImplTest {

    @Mock
    MedicationRepository medicationRepository;

    @InjectMocks
    MedicationServiceImpl medicationService;

    Medication medication;
    List<Medication> medications;


    @BeforeEach
    void setUp() {
        medication = new Medication("MD1" , "Flagyl" , 100 , "0xeef45d");
        medications = new ArrayList<>(Arrays.asList(medication));
        when(medicationRepository.save(medication)).thenReturn(medication);
        when(medicationRepository.findById(medication.getCode())).thenReturn(Optional.ofNullable(medication));
        when(medicationRepository.findAll()).thenReturn(medications);
    }

    @Test
    void createMedication() {
        assertEquals(medicationService.createMedication(medication) , medication);
    }

    @Test
    void getMedications() {
        assertEquals(medicationService.getMedications() , medications);
    }

    @Test
    void findMedicationByCode() {
        assertEquals(medicationService.findMedicationByCode(medication.getCode()) , medication);
    }

    @Test
    void validateName_true() {
        assertTrue(medicationService.validateName("DRN1"));
    }
    @Test
    void validateName_false() {
        assertFalse(medicationService.validateName("DRN1!"));
    }

    @Test
    void validateCode_TRUE() {
        assertTrue(medicationService.validateCode("MD1"));
    }

    @Test
    void validateCode_false() {
        assertFalse(medicationService.validateCode("MD-1"));
    }
}