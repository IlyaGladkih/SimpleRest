package com.example.demo.http.advice;

import com.example.demo.http.errorResponse.SensorErrorResponse;
import com.example.demo.http.exceptions.NoSuchSensorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SensorControllerAdvice {

    @ExceptionHandler(NoSuchSensorException.class)
    public ResponseEntity<SensorErrorResponse> goHandleForMeasurementNotFoundException(NoSuchSensorException e){
        SensorErrorResponse response = new SensorErrorResponse("No such sensor",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
