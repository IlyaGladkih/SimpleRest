package com.example.demo.util;

import com.example.demo.DTO.MeasurementDTO;
import com.example.demo.DTO.SensorDTO;
import com.example.demo.models.Measurement;
import com.example.demo.models.Sensor;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {

    public MeasurementDTO measurementToDTO(Measurement measurement){
        return MeasurementDTO.builder()
                .value(measurement.getValue())
                .raining(measurement.isRaining())
                .sensor(this.sensotToDTO(measurement.getSensor()))
                .build();
    }

    public Measurement dtoToMeasurement(MeasurementDTO dto){
        return Measurement.builder()
                .value(dto.getValue())
                .raining(dto.isRaining())
                .sensor(this.dtoToSensor(dto.getSensor()))
                .build();
    }

    public Sensor dtoToSensor(SensorDTO dto){
        return Sensor.builder()
                .name(dto.getName())
                .build();
    }

    public SensorDTO sensotToDTO(Sensor sensor){
        return SensorDTO.builder()
                .name(sensor.getName())
                .build();
    }
}
