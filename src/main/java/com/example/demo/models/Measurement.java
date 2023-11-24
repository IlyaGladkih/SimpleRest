package com.example.demo.models;

import com.example.demo.DTO.MeasurementDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "measurements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "sensor")
@Builder
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;



}
