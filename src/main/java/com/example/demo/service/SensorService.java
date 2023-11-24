package com.example.demo.service;

import com.example.demo.DTO.SensorDTO;
import com.example.demo.models.Sensor;
import com.example.demo.repositories.SensorReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private SensorReposotory sensorReposotory;

    @Autowired
    public SensorService(SensorReposotory sensorReposotory) {
        this.sensorReposotory = sensorReposotory;
    }

    @Transactional
    public void addSensor(Sensor sensor){
        sensorReposotory.save(sensor);
    }

    public Optional<Sensor> findSensorByName(Sensor sensor){
        return sensorReposotory.findSensorByNameEquals(sensor.getName());
    }

}
