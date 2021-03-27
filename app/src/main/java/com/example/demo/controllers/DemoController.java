package com.example.demo.controllers;

import com.example.specification.api.ExamplesApi;
import com.example.specification.model.ExampleSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController implements ExamplesApi {
    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

    @Override
    public ResponseEntity<ExampleSchema> getExample(String exampleId) {
        final String existingExampleId = "example";
        ExampleSchema someResult = new ExampleSchema();
        if (exampleId.equals(existingExampleId)) {
            final String iso8601TimeStamp = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
            return ResponseEntity.ok(
                    someResult.result("Good result")
                        .timestamp(iso8601TimeStamp)
            );
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @Override
    public ResponseEntity<Void> updateExample(ExampleSchema exampleSchema) {
        final String existingExampleId = "example";
        if (exampleSchema.getResult().equals(existingExampleId)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    public ResponseEntity<List<ExampleSchema>> getExamples(Boolean listHasItems) {
        List<ExampleSchema> schemas = new ArrayList<>();
        if (listHasItems == null || !listHasItems) {
            return ResponseEntity.ok(schemas);
        } else {
            schemas.add(new ExampleSchema().result("Good result"));
            return ResponseEntity.ok(schemas);
        }
    }
}
