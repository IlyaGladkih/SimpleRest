package com.example.demo.http.advice;

import com.example.demo.http.errorResponse.MeasurementErrorResponse;
import com.example.demo.http.exceptions.MeasurementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeasurementControllerAdvice {

    @ExceptionHandler(MeasurementNotFoundException.class)
    public ResponseEntity<MeasurementErrorResponse> goHandleForMeasurementNotFoundException(MeasurementNotFoundException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse("Measurements not found",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
