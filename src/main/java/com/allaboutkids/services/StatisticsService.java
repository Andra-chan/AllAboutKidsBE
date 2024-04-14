package com.allaboutkids.services;

import com.allaboutkids.entities.Payment;
import com.allaboutkids.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    StudentService studentService;
    @Autowired
    PaymentService paymentService;

    public void generateStudentsStatistics(){
        try {
            List<Student> students = studentService.getAllStudents();
            String postBody = "";
            for(Student st : students){
                ObjectMapper jsonMapper = new ObjectMapper();
                String jsonStud = jsonMapper.writeValueAsString(st);
                postBody = postBody + jsonStud + ";";
            }
            postBody = postBody.substring(0,postBody.length() - 1);
            System.out.println(postBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(postBody))
                    .uri(new URI("https://p0276-iflmap.hcisbp.eu1.hana.ondemand.com/http/cpi/students?allaboutkids1"))
                    .header("Authorization", "Basic UzAwMjQ2NjA3NDg6QU0uMjdhbmRyYTA3bWloYWlsYTIwMDE=")
                    .header("Content-type", "application/json")
                    .header("Accept", "application/json")
                    .build();

            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void generatePaymentsStatistics(){
        try {
            List<Payment> payments = paymentService.getAllPayments();
            String postBody = "";
            for(Payment pm : payments){
                ObjectMapper jsonMapper = new ObjectMapper();
                String jsonStud = jsonMapper.writeValueAsString(pm);
                postBody = postBody + jsonStud + ";";
            }
            postBody = postBody.substring(0,postBody.length() - 1);
            System.out.println(postBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(postBody))
                    .uri(new URI("https://p0276-iflmap.hcisbp.eu1.hana.ondemand.com/http/cpi/payments?allaboutkids3"))
                    .header("Authorization", "Basic UzAwMjQ2NjA3NDg6QU0uMjdhbmRyYTA3bWloYWlsYTIwMDE=")
                    .header("Content-type", "application/json")
                    .header("Accept", "application/json")
                    .build();

            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
