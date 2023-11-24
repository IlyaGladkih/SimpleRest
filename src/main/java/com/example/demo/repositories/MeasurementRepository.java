package com.example.demo.repositories;

import com.example.demo.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

    public int countMeasurementsByRainingTrue();
}
