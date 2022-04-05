package com.addi.challenge.externalsystem.nationalregistryidentificationsystem.service;

import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.entity.Person;

import java.util.List;

public interface NationalRegistryIdentificationSystemService {
    List<Person> findAll();

    Person save(Person person);

    void deleteByNationalIdentificationNumber(String nationalIdentificationNumber);

    Person findByNationalIdentificationNumber(String nationalIdentificationNumber);

}
