package com.example.programmingexam2021.Candidate.Controller;

import com.example.programmingexam2021.Candidate.DTO.CandidateRequest;
import com.example.programmingexam2021.Candidate.DTO.CandidateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CandidateControllerTest {

    private final String PATH = "/api/candidate/";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("/test-data.sql")
    void getAllCandidates() {
        ResponseEntity<CandidateResponse[]> response = getAll();

        assertEquals(4, response.getBody().length);
    }

    @Test
    @Sql("/test-data.sql")
    void getAllCandidatesByPartyId() {
        ResponseEntity<CandidateResponse[]> response;


        response = getAllByPartyId('E');
        assertEquals(0, response.getBody().length);

        response = getAllByPartyId('A');
        assertEquals(3, response.getBody().length);
    }

    @Test
    @Sql("/test-data.sql")
    void createCandidate() {
        CandidateRequest request = new CandidateRequest(null, "Bob Jørgensen", 'E');
        ResponseEntity<CandidateResponse> response;

        response = create(request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        request.setParty_id('A');
        response = create(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Sql("/test-data.sql")
    void editCandidate() {
        CandidateRequest request = new CandidateRequest(101L, "Bob Jørgensen", 'E');
        ResponseEntity<CandidateResponse> response;

        response = edit(request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        request.setParty_id('O');
        response = edit(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals('O', response.getBody().getParty().getId());
    }

    @Test
    @Sql("/test-data.sql")
    void deleteCandidate() {
        ResponseEntity<?> response;

        response = delete(999L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        response = delete(101L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


    // Helper methods

    private ResponseEntity<CandidateResponse[]> getAll() {
        return restTemplate.getForEntity(createUrl(PATH), CandidateResponse[].class);
    }

    private ResponseEntity<CandidateResponse[]> getAllByPartyId(char id) {
        return restTemplate.getForEntity(createUrl(PATH+id), CandidateResponse[].class);
    }

    private ResponseEntity<CandidateResponse> create(CandidateRequest request) {
        return restTemplate.postForEntity(createUrl(PATH), request, CandidateResponse.class);
    }

    private ResponseEntity<CandidateResponse> edit(CandidateRequest request) {
        HttpEntity<?> entity = new HttpEntity<>(request, null);

        return restTemplate.exchange(
                createUrl(PATH),
                HttpMethod.PUT,
                entity,
                CandidateResponse.class
        );
    }

    private ResponseEntity<?> delete(Long id) {

        return restTemplate.exchange(
                createUrl(PATH+id),
                HttpMethod.DELETE,
                null,
                getClass());
    }

    private String createUrl(String path) {
        return "http://localhost:" + port + path;
    }

}