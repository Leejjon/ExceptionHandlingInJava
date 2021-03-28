package com.example.demo.controllers;

import com.example.specification.api.PersonsApi;
import com.example.specification.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController implements PersonsApi {
    private static final String EXAMPLE_NAME = "Leon";

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

    @Override
    public ResponseEntity<Person> getPersonByName(String name) {
        if (name.equals(EXAMPLE_NAME)) {
            return ResponseEntity.ok(
                    new Person()
                            .name("Leon")
                            .favouritenumber(new BigDecimal(5))
                            .birthtimestamp(OffsetDateTime.now())
            );
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @Override
    public ResponseEntity<Void> updatePerson(String name, Person exampleSchema) {
        if (name.equals(EXAMPLE_NAME)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @Override
    public ResponseEntity<List<Person>> getPersons(Boolean listHasItems) {
        List<Person> persons = new ArrayList<>();
        if (listHasItems != null && listHasItems) {
            persons.add(
                    new Person()
                            .name("Leon")
                            .favouritenumber(new BigDecimal(5))
                            .birthtimestamp(OffsetDateTime.now())
            );
        }
        return ResponseEntity.ok(persons);
    }
}
