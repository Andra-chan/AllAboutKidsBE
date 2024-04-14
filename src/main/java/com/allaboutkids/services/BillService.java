package com.allaboutkids.services;

import com.allaboutkids.entities.Bill;
import com.allaboutkids.entities.Payment;
import com.allaboutkids.repositories.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BillService {


    @Autowired
    private PaymentRepository paymentRepository;

    public ResponseEntity<HttpStatus> generateBillFromRPA(Bill bill) {
        System.out.println(bill.toString());
        try {
            String token = getToken();
            String json = """
                    {
                      "startInfo": {
                        "ReleaseKey": "c6a110fe-ac2d-4e83-a750-c89b08fe42f5",
                        "JobsCount": 1,
                        "Strategy": "ModernJobsCount",
                        "RobotIds": [],
                        "RuntimeType": "Unattended",
                        "NoOfRobots": 1,
                        "InputArguments": 
                      """;
            ObjectMapper jsonMapper = new ObjectMapper();
            String jsonBill = jsonMapper.writeValueAsString(bill);

            JSONObject obj = new JSONObject(jsonBill);
            System.out.println(obj);

            String alreadyExists = "true";

            Payment payment = getPaymentByCnp(obj.getJSONObject("parent").getString("cnp"));
            if(payment == null){
                alreadyExists = "false";
            }

            String newJson = json + "\"{\\\"" + "price" + "\\\":\\\"" + obj.getString("price") + "\\\"," +
                    "\\\"" + "description" + "\\\":\\\"" + obj.getString("description") + "\\\"," +
                    "\\\"" + "quantity" + "\\\":\\\"" + obj.getString("quantity") + "\\\"," +
                    "\\\"" + "address" + "\\\":\\\"" + obj.getString("address") + "\\\"," +
                    "\\\"" + "city" + "\\\":\\\"" + obj.getString("city") + "\\\"," +
                    "\\\"" + "county" + "\\\":\\\"" + obj.getString("county") + "\\\"," +
                    "\\\"" + "cnp" + "\\\":\\\"" + obj.getJSONObject("parent").getString("cnp") + "\\\"," +
                    "\\\"" + "alreadyExists" + "\\\":\\\"" + alreadyExists + "\\\"," +
                    "\\\"" + "email" + "\\\":\\\"" + obj.getJSONObject("parent").getString("email") + "\\\"," +
                    "\\\"" + "lastName" + "\\\":\\\"" + obj.getJSONObject("parent").getString("lastName") + "\\\"," +
                    "\\\"" + "firstName" + "\\\":\\\"" + obj.getJSONObject("parent").getString("firstName") + "\\\"}\"" +
                    "}}";

            System.out.println(newJson);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(newJson))
                    .uri(new URI("https://cloud.uipath.com/licentarpa/DefaultTenant/odata/Jobs/UiPath.Server.Configuration.OData.StartJobs"))
                    .header("Authorization", "Bearer " + token)
                    .header("Content-type", "application/json")
                    .header("X-UIPATH-TenantName", "DefaultTenant")
                    .header("X-UIPATH-OrganizationUnitId", "4399543")
                    .build();

            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public Payment getPaymentByCnp(String cnp) {
        return paymentRepository.findByCnp(cnp);

    }

    public String getToken(){
        try {
            String json = """
                    {
                    "grant_type": "refresh_token",
                    "client_id": "8DEv1AMNXczW3y4U15LL3jYf62jK93n5",
                    "refresh_token": "1iJh07bAux7ZJGVrP7oc4R5B6K8XzmxYI0sV4Kn1xPEjp"}""";
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .uri(new URI("https://account.uipath.com/oauth/token"))
                    .header("X-UIPATH-TenantName", "DefaultTenant")
                    .header("Content-type", "application/json")
                    .build();

            HttpURLConnection post = (HttpURLConnection) new URL("https://account.uipath.com/oauth/token").openConnection();

            post.setRequestMethod("POST");
            post.setRequestProperty("Content-Type", "application/json");
            post.setRequestProperty("X-UIPATH-TenantName", "DefaultTenant");

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
            return obj.getString("access_token");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
