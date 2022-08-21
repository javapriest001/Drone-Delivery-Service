package com.example.MusalaSoftEnwereVincent.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private LocalDateTime timesStamp;
    private String serialNumber;

}
