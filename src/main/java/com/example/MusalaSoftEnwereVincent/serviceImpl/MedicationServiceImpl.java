package com.example.MusalaSoftEnwereVincent.serviceImpl;

import com.example.MusalaSoftEnwereVincent.DTO.MedicationDTO;
import com.example.MusalaSoftEnwereVincent.exception.MedicationNotSavedException;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.MedicationRepository;
import com.example.MusalaSoftEnwereVincent.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Medication createMedication(MedicationDTO medicationDTO) {
        Medication medication = new Medication();
        if (validateName(medicationDTO.getName())){
            if (validateCode(medicationDTO.getCode())){
                medication.setName(medicationDTO.getName());
                medication.setCode(medicationDTO.getCode());
                medication.setWeight(medicationDTO.getWeight());
                medication.setImage(medicationDTO.getImage());
                medicationRepository.save(medication);
            }else {
                throw new MedicationNotSavedException(medicationDTO.getCode() + " is an invalid Code Format");
            }
        }else {
            throw new MedicationNotSavedException(medicationDTO.getName() +  " is An invalid Name Format");
        }
        return medication;
    }

    public boolean validateName(String name){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]*$" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    public boolean validateCode(String code){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_]*$" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(code);
        return matcher.find();
    }
}
