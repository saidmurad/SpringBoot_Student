package com.student.controller;

import com.student.core.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class StudentControllerTest {

    @Test
    void add() {
        Student student = new Student();
        student.setFirstName("Mohammad");
        student.setSurname("Marega");
        student.setDept("Physics");
        // Get request using RestTemplate
        ResponseEntity<String > responseEntity = new RestTemplate().postForEntity("http://localhost:8081/college/student/", new HttpEntity<Student>(student), String.class);
        String locationUrl =  responseEntity.getHeaders().get("location").get(0);
        // Post request using RestTemplate
        ResponseEntity<Student> response = new RestTemplate().getForEntity("http://localhost:8081" + locationUrl, Student.class);
        System.out.println(response.getBody());

    }

    @Test
    void addNegative() {
        Student student = new Student();
        student.setFirstName("Mohammad");
        student.setSurname(null);
        student.setDept("Physics");
        String url = "http://localhost:8081/college/student/";

        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(url, new HttpEntity<Student>(student), String.class);
            assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        });

    }
}