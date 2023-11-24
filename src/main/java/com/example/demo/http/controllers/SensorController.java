package com.example.demo.http.controllers;

import com.example.demo.DTO.SensorDTO;
import com.example.demo.service.SensorService;
import com.example.demo.util.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private SensorService service;

    private EntityDtoConverter converter;

    @Autowired
    public SensorController(SensorService service, EntityDtoConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody SensorDTO sensorDTO){
        service.addSensor(converter.dtoToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
