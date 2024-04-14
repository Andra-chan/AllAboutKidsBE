package com.allaboutkids.controllers;

import com.allaboutkids.entities.Bill;
import com.allaboutkids.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> generateBillFromRPA(@RequestBody Bill billDetails){
        return billService.generateBillFromRPA(billDetails);
    }
}
