package com.example.demo;

import com.example.specification.api.ExamplesApi;
import com.example.specification.model.ExampleSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.ok(someResult.result("Good result"));
        } else {
            return ResponseEntity.status(404).body(someResult.result("Bad result."));
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
