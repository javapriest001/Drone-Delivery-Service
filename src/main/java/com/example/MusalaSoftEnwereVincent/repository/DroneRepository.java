package com.example.MusalaSoftEnwereVincent.repository;

import com.example.MusalaSoftEnwereVincent.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {

}
