package com.allaboutkids.services;

import com.allaboutkids.entities.Prediction;
import com.allaboutkids.repositories.PredictionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class PredictionService {

    @Autowired
    private PredictionRepository predictionRepository;

    public List<Prediction> getAllPredictions() {
        List<Prediction> allPredictions = predictionRepository.findAll();
//        List<Payment> sortedPayments = allPayments.stream()
//                .sorted(Comparator.comparing(Payment::getMonth))
//                .collect(Collectors.toList());
        return allPredictions;
    }

    public void generatePrediction(Prediction prediction) {

        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writeValueAsString(prediction);

            HttpURLConnection post = (HttpURLConnection) new URL("http://127.0.0.1:7777/post").openConnection();

            post.setRequestMethod("POST");
            post.setRequestProperty("Content-Type", "application/json");
            post.setRequestProperty("Accept", "application/json");

            post.setDoOutput(true);
            OutputStream os = post.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(json);
            osw.flush();
            osw.close();
            os.close();

            post.connect();
            String postData = new String(post.getInputStream().readAllBytes());
            System.out.println(postData);

            //HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(postData);
            System.out.println(obj);
            prediction.setProfit(obj.getString("profit"));
            predictionRepository.save(prediction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
