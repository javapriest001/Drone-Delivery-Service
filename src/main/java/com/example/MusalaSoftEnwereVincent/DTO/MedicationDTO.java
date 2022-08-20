package com.example.MusalaSoftEnwereVincent.DTO;


import lombok.*;

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
