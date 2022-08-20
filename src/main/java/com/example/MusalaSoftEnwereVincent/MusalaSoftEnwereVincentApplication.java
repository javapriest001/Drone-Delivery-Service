package com.example.MusalaSoftEnwereVincent;

import com.example.MusalaSoftEnwereVincent.enumeration.Model;
import com.example.MusalaSoftEnwereVincent.enumeration.State;
import com.example.MusalaSoftEnwereVincent.model.Drone;
import com.example.MusalaSoftEnwereVincent.model.Medication;
import com.example.MusalaSoftEnwereVincent.repository.DroneRepository;
import com.example.MusalaSoftEnwereVincent.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusalaSoftEnwereVincentApplication implements CommandLineRunner {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(MusalaSoftEnwereVincentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Medication medication1 = new Medication();


        droneRepository.save(new Drone("DRN1", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN2", Model.LIGHTWEIGHT , (short) 300, 20, State.IDLE , null) );
        droneRepository.save(new Drone("DRN3", Model.CRUISERWEIGHT , (short) 450, 89, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN4", Model.MIDDLEWEIGHT , (short) 500, 15, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN5", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN6", Model.HEAVYWEIGHT , (short) 500, 90, State.IDLE , null) );
        droneRepository.save(new Drone("DRN7", Model.MIDDLEWEIGHT , (short) 500, 100, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN8", Model.CRUISERWEIGHT , (short) 500, 10, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN9", Model.LIGHTWEIGHT , (short) 500, 65, State.IDLE , null) );
        droneRepository.save(new Drone("DRN10", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null)) ;

    }
}
