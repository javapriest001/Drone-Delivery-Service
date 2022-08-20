package com.example.MusalaSoftEnwereVincent.model;
import com.example.MusalaSoftEnwereVincent.enumeration.Model;
import com.example.MusalaSoftEnwereVincent.enumeration.State;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="drone_db")
public class Drone {
    @Id
    @Size(max = 500)
    private String serialNumber;
    @NotNull(message = "this filled must be filled")
    private Model model;
    private short weight;
    private int batteryCapacity;
    private State stateOfDuty;
    @OneToMany
    private List<Medication> medication;


}
