package com.addi.challenge.externalsystem.nationalregistryidentificationsystem.repository;

import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalRegistryIdentificationSystemRepository extends JpaRepository<Person, Long> {
    //Person findByLastname(String lastname);
    Person findByNationalIdentificationNumber(String nationalIdentificationNumber);
    //List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);
}
