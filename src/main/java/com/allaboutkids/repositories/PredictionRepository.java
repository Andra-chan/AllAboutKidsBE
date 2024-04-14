package com.allaboutkids.repositories;

import com.allaboutkids.entities.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
}
