package com.addi.challenge.externalsystem.nationalregistryidentificationsystem.service;

import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.entity.Person;

import java.util.List;

public interface NationalRegistryIdentificationSystemService {
    List<Person> findAll();

    Person save(Person person);

    void deleteById(Long personId);

    Person findByNationalIdentificationNumber(String nationalIdentificationNumber);

}
