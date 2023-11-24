package com.example.demo.service;

import com.example.demo.DTO.MeasurementDTO;
import com.example.demo.DTO.SensorDTO;
import com.example.demo.http.exceptions.MeasurementNotFoundException;
import com.example.demo.http.exceptions.NoSuchSensorException;
import com.example.demo.models.Measurement;
import com.example.demo.models.Sensor;
import com.example.demo.repositories.MeasurementRepository;
import com.example.demo.util.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private MeasurementRepository repository;

    private SensorService sensorService;

    private EntityDtoConverter converter;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository,
                              SensorService sensorService, EntityDtoConverter converter) {
        this.repository = measurementRepository;
        this.sensorService = sensorService;
        this.converter = converter;
    }

    @Transactional
    public void addMeasure(Measurement measurement){
        Optional<Sensor> sensorByName = sensorService.findSensorByName(measurement.getSensor());
        if(sensorByName.isPresent()) {
            measurement.setSensor(sensorByName.get());
            repository.save(measurement);}
        else throw new NoSuchSensorException();
    }

    public List<MeasurementDTO> getAllMeasurements() throws MeasurementNotFoundException{
        List<Measurement> all = repository.findAll();
        if(all.isEmpty()) throw new MeasurementNotFoundException();
        return all.stream().map(converter::measurementToDTO).collect(Collectors.toList());
    }

    public Integer getRainingMeasurements(){
        return repository.countMeasurementsByRainingTrue();
    }
}
