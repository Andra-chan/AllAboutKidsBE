package com.allaboutkids.controllers;

import com.allaboutkids.entities.Payment;
import com.allaboutkids.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id,@RequestBody Payment paymentDetails){
        return paymentService.updatePayment(id, paymentDetails);
    }

    @GetMapping("/search/{description}")
    public List<Payment> getPaymentsByDescription(@PathVariable String description){
        return paymentService.getPaymentsByDescription(description);
    }
}
