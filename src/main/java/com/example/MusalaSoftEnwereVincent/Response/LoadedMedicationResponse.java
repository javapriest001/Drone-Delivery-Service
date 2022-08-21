package com.example.MusalaSoftEnwereVincent.Response;

import com.example.MusalaSoftEnwereVincent.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadedMedicationResponse {
    private String message;
    private LocalDateTime timesStamp;
    private String serialNumber;
    private List<Medication> medications;
}
