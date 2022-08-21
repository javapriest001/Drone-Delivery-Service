package com.example.MusalaSoftEnwereVincent.Response;

import com.example.MusalaSoftEnwereVincent.model.Drone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDroneResponse {
    private String message;
    private LocalDateTime timesStamp;
    private List<Drone> availableDrones ;
}
