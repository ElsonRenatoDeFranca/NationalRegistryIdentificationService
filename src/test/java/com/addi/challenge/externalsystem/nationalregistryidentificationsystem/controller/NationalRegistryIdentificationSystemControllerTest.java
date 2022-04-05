package com.addi.challenge.externalsystem.nationalregistryidentificationsystem.controller;

import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.entity.Person;
import com.addi.challenge.externalsystem.nationalregistryidentificationsystem.service.NationalRegistryIdentificationSystemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NationalRegistryIdentificationSystemControllerTest {
    @Mock
    private NationalRegistryIdentificationSystemService nationalRegistryIdentificationSystemService;

    @InjectMocks
    private NationalRegistryIdentificationSystemController nationalRegistryIdentificationSystemController;

    @Test
    public void shouldReturnANotEmptyListWhenFindAllIsCalledAndThereIsAtLeastOneItemInTheDatabase() {
        when(nationalRegistryIdentificationSystemService.findAll()).thenReturn(createNotEmptyPersonMockList());

        ResponseEntity<List<Person>> actualPerson = this.nationalRegistryIdentificationSystemController.findAll();

        assertThat(actualPerson).isNotNull();

        assertThat(actualPerson.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnAnEmptyListWhenFindAllIsCalledAndThereIsNoItemInDatabase() {
        when(nationalRegistryIdentificationSystemService.findAll()).thenReturn(createEmptyPersonMockList());

        ResponseEntity<List<Person>> actualPerson = this.nationalRegistryIdentificationSystemController.findAll();

        assertThat(actualPerson).isNotNull();
        assertThat(actualPerson.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnNotNullWhenFindByIdIsCalled() {
        Person expectedPerson = createPersonMock();

        when(nationalRegistryIdentificationSystemService.findByNationalIdentificationNumber(any())).thenReturn(expectedPerson);

        ResponseEntity<Person> actualPerson = this.nationalRegistryIdentificationSystemController.findByNationalIdentificationNumber(expectedPerson.getNationalIdentificationNumber());

        assertThat(actualPerson).isNotNull();
        assertThat(actualPerson.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualPerson.getBody()).isEqualTo(expectedPerson);
    }

    @Test
    public void shouldDeleteAnExistingPersonFromTheDatabaseWhenDeleteByIdIsCalled() {
        Person expectedPerson = createPersonMock();

        doNothing().when(nationalRegistryIdentificationSystemService).deleteByNationalIdentificationNumber(any());

        this.nationalRegistryIdentificationSystemController.deleteByNationalIdentificationNumber(expectedPerson.getNationalIdentificationNumber());

        verify(nationalRegistryIdentificationSystemService, atLeast(1)).deleteByNationalIdentificationNumber(any());
    }

    @Test
    public void shouldAddANewPersonToTheDatabaseWhenSaveMethodIsCalled() {
        Person expectedPerson = createPersonMock();

        when(nationalRegistryIdentificationSystemService.save(any())).thenReturn(expectedPerson);

        ResponseEntity<Person> actualPerson = this.nationalRegistryIdentificationSystemController.save(expectedPerson);

        assertThat(actualPerson).isNotNull();
        assertThat(actualPerson.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(nationalRegistryIdentificationSystemService, atLeast(1)).save(any());
    }


    private List<Person> createNotEmptyPersonMockList() {
        return Arrays.asList(createPersonMock(), createPersonMock());
    }

    private List<Person> createEmptyPersonMockList() {
        return Collections.emptyList();
    }

    private Person createPersonMock() {
        return Person.builder().id(1L)
                .birthDate("15/02/2001")
                .lastName("Guedes")
                .firstName("Claudia")
                .nationalIdentificationNumber("90001")
                .email("claudiaguedes@gmail.com")
                .build();

    }
}