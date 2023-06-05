package com.allaboutkids.services;

import com.allaboutkids.entities.Payment;
import com.allaboutkids.exceptions.ResourceNotFoundException;
import com.allaboutkids.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public ResponseEntity<HttpStatus> createPayment(Payment payment) {
        try {
            paymentRepository.save(payment);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        }
    }

    public ResponseEntity<Payment> getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No payment exists with id: " + id));
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<HttpStatus> deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No payment exists with id: " + id));
        paymentRepository.delete(payment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Payment> getAllPayments() {
        List<Payment> allPayments = paymentRepository.findAll();
//        List<Payment> sortedPayments = allPayments.stream()
//                .sorted(Comparator.comparing(Payment::getMonth))
//                .collect(Collectors.toList());
        return allPayments;
    }

    public ResponseEntity<Payment> updatePayment(Long id, Payment paymentDetails) {
        Payment updatePayment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No payment exists with id: " + id));
        updatePayment.setPrice(paymentDetails.getPrice());
        updatePayment.setMonth(paymentDetails.getMonth());
        updatePayment.setYear(paymentDetails.getYear());
        updatePayment.setDescription(paymentDetails.getDescription());
        updatePayment.setParent(paymentDetails.getParent());


        paymentRepository.save(updatePayment);
        return ResponseEntity.ok(updatePayment);
    }

    public List<Payment> getPaymentsByDescription(String description) {
        List<Payment> payments = paymentRepository.findByQuery(description);

        List<Payment> paymentsFound = payments.stream()
                .map(entry -> new Payment(entry.getId(), entry.getPrice(),entry.getMonth(),entry.getYear(),entry.getDescription(),entry.getParent()))
                .collect(Collectors.toList());

        return paymentsFound;
    }
}
