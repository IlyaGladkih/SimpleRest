package com.example.demo.http.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MeasurementErrorResponse {

    private String message;

    private long timestamp;
}
