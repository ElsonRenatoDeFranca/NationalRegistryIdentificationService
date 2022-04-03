package com.addi.challenge.externalsystem.nationalregistryidentificationsystem.service;

import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.entity.Person;
import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.exception.PersonNotFoundException;
import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.repository.NationalRegistryIdentificationSystemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalRegistryIdentificationSystemServiceImpl implements NationalRegistryIdentificationSystemService {

    private final NationalRegistryIdentificationSystemRepository repository;

    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person not found";

    public NationalRegistryIdentificationSystemServiceImpl(NationalRegistryIdentificationSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findById(Long personId) {
        try {
            return repository.findById(personId).orElseThrow(() -> new PersonNotFoundException(PERSON_NOT_FOUND_EXCEPTION));
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Person findByNationalIdentificationNumber(String nationalIdentificationNumber) {
        return repository.findByNationalIdentificationNumber(nationalIdentificationNumber);
    }


    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public void deleteById(Long personId) {
        repository.deleteById(personId);
    }
}
