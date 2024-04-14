package com.allaboutkids.controllers;

import com.allaboutkids.entities.Parent;
import com.allaboutkids.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/parents")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> createParent(@RequestBody Parent parent) {
        return parentService.createParent(parent);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Parent> getParentById(@PathVariable Long id) {
        return parentService.getParentById(id);
    }

    @GetMapping("")
    public List<Parent> getAllParents(){
        return parentService.getAllParents();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteParent(@PathVariable Long id) {
        return parentService.deleteParent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parent> updateParent(@PathVariable Long id,@RequestBody Parent parentDetails){
        return parentService.updateParent(id, parentDetails);
    }

    @GetMapping("/cnp/{cnp}")
    public Parent getParentByCnp(@PathVariable String cnp){
        return parentService.getParentByCnp(cnp);
    }

    @GetMapping("/search/{lastName}")
    public List<Parent> getParentsByLastName(@PathVariable String lastName){
        return parentService.getParentsByLastName(lastName);
    }
}