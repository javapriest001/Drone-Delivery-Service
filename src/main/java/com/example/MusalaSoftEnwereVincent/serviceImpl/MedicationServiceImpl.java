package com.example.MusalaSoftEnwereVincent.serviceImpl;
import com.example.MusalaSoftEnwereVincent.exception.MedicationNotFoundException;
import com.example.MusalaSoftEnwereVincent.exception.MedicationNotSavedException;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.MedicationRepository;
import com.example.MusalaSoftEnwereVincent.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Medication createMedication(Medication medication) {
        if (validateName(medication.getName())){
            if (validateCode(medication.getCode())){
                medicationRepository.save(medication);
            }else {
               throw new MedicationNotSavedException(medication.getCode() + " is an invalid Code Format");
            }
        }else {
           throw new MedicationNotSavedException(medication.getName() +  " is An invalid Name Format");
        }
        return medication;
    }

    public List<Medication> getMedications(){
        return medicationRepository.findAll();
    }


    public  Medication findMedicationByCode(String code){
        return medicationRepository.findById(code)
                .orElseThrow(()-> new MedicationNotFoundException(code));
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
