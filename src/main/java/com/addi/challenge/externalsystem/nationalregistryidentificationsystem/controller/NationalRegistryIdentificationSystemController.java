package com.addi.challenge.externalsystem.nationalregistryidentificationsystem.controller;

import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.entity.Person;
import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.service.NationalRegistryIdentificationSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nationalregistry")
public class NationalRegistryIdentificationSystemController {

    private final NationalRegistryIdentificationSystemService nationalRegistryIdentificationSystemService;

    public NationalRegistryIdentificationSystemController(NationalRegistryIdentificationSystemService nationalRegistryIdentificationSystemService) {
        this.nationalRegistryIdentificationSystemService = nationalRegistryIdentificationSystemService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(nationalRegistryIdentificationSystemService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person savedPerson = nationalRegistryIdentificationSystemService.save(person);

        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{nationalIdentificationNumber}")
    @ResponseBody
    public ResponseEntity<Person> findByNationalIdentificationNumber(@PathVariable("nationalIdentificationNumber") String nationalIdentificationNumber) {
        Person person = nationalRegistryIdentificationSystemService.findByNationalIdentificationNumber(nationalIdentificationNumber);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{nationalIdentificationNumber}")
    void deleteByNationalIdentificationNumber(@PathVariable String nationalIdentificationNumber) {
        nationalRegistryIdentificationSystemService.deleteByNationalIdentificationNumber(nationalIdentificationNumber);
    }
}
