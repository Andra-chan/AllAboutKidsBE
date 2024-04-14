package com.allaboutkids.controllers;

import com.allaboutkids.entities.Prediction;
import com.allaboutkids.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/prediction")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @GetMapping("")
    public List<Prediction> getAllPredictions(){
        return predictionService.getAllPredictions();
    }

    @PostMapping("")
    public void generatePrediction(@RequestBody Prediction prediction) {
        predictionService.generatePrediction(prediction);
    }
}
