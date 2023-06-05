package com.allaboutkids.services;

import com.allaboutkids.entities.Parent;
import com.allaboutkids.exceptions.ResourceNotFoundException;
import com.allaboutkids.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    public ResponseEntity<HttpStatus> createParent(Parent parent) {
        try {
            parentRepository.save(parent);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        }
    }

    public ResponseEntity<Parent> getParentById(Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No parent exists with id: " + id));
        return ResponseEntity.ok(parent);
    }

    public ResponseEntity<HttpStatus> deleteParent(Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No parent exists with id: " + id));
        parentRepository.delete(parent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Parent> getAllParents() {
        List<Parent> allParents = parentRepository.findAll();
        List<Parent> sortedParents = allParents.stream()
                .sorted(Comparator.comparing(Parent::getLastName))
                .collect(Collectors.toList());
        return sortedParents;
    }

    public ResponseEntity<Parent> upateParent(long id, Parent parentDetails) {
        Parent updateParent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No parent exists with id: " + id));
        updateParent.setFirstName(parentDetails.getFirstName());
        updateParent.setLastName(parentDetails.getLastName());
        updateParent.setPhone(parentDetails.getPhone());
        updateParent.setEmail(parentDetails.getEmail());
        updateParent.setCnp(parentDetails.getCnp());

        parentRepository.save(updateParent);
        return ResponseEntity.ok(updateParent);

    }

    public Parent getParentByCnp(String cnp) {
        return parentRepository.findByQueryCnp(cnp);
    }

    public List<Parent> getParentsByLastName(String lastName) {
        List<Parent> parents = parentRepository.findByQuery(lastName);

        List<Parent> parentsFound = parents.stream()
                .map(entry -> new Parent(entry.getId(), entry.getFirstName(),entry.getLastName(),entry.getPhone(),entry.getEmail(),entry.getCnp()))
                .collect(Collectors.toList());

        return parentsFound;
    }
}
