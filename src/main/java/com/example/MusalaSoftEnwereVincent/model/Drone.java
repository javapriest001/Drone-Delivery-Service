package com.example.MusalaSoftEnwereVincent.model;
import com.example.MusalaSoftEnwereVincent.enumeration.Model;
import com.example.MusalaSoftEnwereVincent.enumeration.State;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Medication> medication;
}
