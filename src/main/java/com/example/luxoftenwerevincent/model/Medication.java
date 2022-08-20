package com.example.luxoftenwerevincent.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Medication {
    @Id
    private String code;
    private String name;
    private short weight;
    private String image;


}
