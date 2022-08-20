package com.example.luxoftenwerevincent.DTO;


import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MedicationDTO {
    private String code;
    private String name;
    private short weight;
    private String image;

}
