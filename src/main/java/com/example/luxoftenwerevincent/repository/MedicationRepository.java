package com.example.luxoftenwerevincent.repository;

import com.example.luxoftenwerevincent.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication , String> {

}
