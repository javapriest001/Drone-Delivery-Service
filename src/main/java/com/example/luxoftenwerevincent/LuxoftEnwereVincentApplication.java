package com.example.luxoftenwerevincent;

import com.example.luxoftenwerevincent.enumeration.Model;
import com.example.luxoftenwerevincent.enumeration.State;
import com.example.luxoftenwerevincent.model.Drone;
import com.example.luxoftenwerevincent.model.Medication;
import com.example.luxoftenwerevincent.repository.DroneRepository;
import com.example.luxoftenwerevincent.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LuxoftEnwereVincentApplication implements CommandLineRunner {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(LuxoftEnwereVincentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Medication medication1 = new Medication();


        droneRepository.save(new Drone("DRN1", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN2", Model.LIGHTWEIGHT , (short) 300, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN3", Model.CRUISERWEIGHT , (short) 450, 100, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN4", Model.MIDDLEWEIGHT , (short) 500, 100, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN5", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN6", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN7", Model.MIDDLEWEIGHT , (short) 500, 100, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN8", Model.CRUISERWEIGHT , (short) 500, 100, State.IDLE , null)) ;
        droneRepository.save(new Drone("DRN9", Model.LIGHTWEIGHT , (short) 500, 100, State.IDLE , null) );
        droneRepository.save(new Drone("DRN10", Model.HEAVYWEIGHT , (short) 500, 100, State.IDLE , null)) ;

    }
}
