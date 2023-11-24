package com.example.demo.DTO;

import com.example.demo.models.Measurement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasurementDTO {

    private double value;

    private boolean raining;

    private SensorDTO sensor;


}
