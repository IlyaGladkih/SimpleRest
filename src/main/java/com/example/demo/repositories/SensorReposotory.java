package com.example.demo.repositories;

import com.example.demo.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorReposotory extends JpaRepository<Sensor, Integer> {

    public Optional<Sensor> findSensorByNameEquals(String name);
}
