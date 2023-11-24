package com.example.demo.http.controllers;

import com.example.demo.DTO.MeasurementDTO;
import com.example.demo.http.errorResponse.MeasurementErrorResponse;
import com.example.demo.http.exceptions.MeasurementNotFoundException;
import com.example.demo.service.MeasurementService;
import com.example.demo.util.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private MeasurementService service;
    private EntityDtoConverter converter;

    @Autowired
    public MeasurementController(MeasurementService service, EntityDtoConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody MeasurementDTO measurementDTO){
        service.addMeasure(converter.dtoToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements(){
        return service.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainingMeasurements(){
        return service.getRainingMeasurements();
    }



}
