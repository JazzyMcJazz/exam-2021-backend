package com.example.programmingexam2021.Candidate.Service;

import com.example.programmingexam2021.Candidate.DTO.CandidateRequest;
import com.example.programmingexam2021.Candidate.Repository.CandidateRepo;
import com.example.programmingexam2021.Party.Repository.PartyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CandidateServiceImplTest {

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private PartyRepo partyRepo;

    private CandidateServiceImpl candidateService;

    @BeforeEach
    void setUp() {
        candidateService = new CandidateServiceImpl(candidateRepo, partyRepo);
    }

    @Test
    @Sql("/test-data.sql")
    void findAllByPartyId() {
        assertEquals(3, candidateService.findAllByPartyId('A').size());
    }

    @Test
    @Sql("/test-data.sql")
    void findAll() {
        assertEquals(4, candidateService.findAll().size());
    }

    @Test
    @Sql("/test-data.sql")
    void addCandidate() {
        CandidateRequest request = new CandidateRequest(null, "Bob Jørgensen", 'E');
        assertThrows(ResponseStatusException.class, () -> candidateService.addCandidate(request));

        request.setParty_id('A');
        assertEquals(105, candidateService.addCandidate(request).getId());
        assertEquals(4, candidateService.findAllByPartyId('A').size());
        assertEquals(5, candidateService.findAll().size());
    }

    @Test
    @Sql("/test-data.sql")
    void editCandidate() {
        CandidateRequest request1 = new CandidateRequest(null, "Bob Jørgensen", 'E');

        assertThrows(ResponseStatusException.class, () -> candidateService.editCandidate(request1));

        request1.setParty_id('A');
        request1.setId(101L);
        assertEquals(101, candidateService.editCandidate(request1).getId());
        assertEquals(3, candidateService.findAllByPartyId('A').size());
        assertEquals(4, candidateService.findAll().size());

        CandidateRequest request2 = new CandidateRequest(101L, "Bo Jørgensen", 'O');
        assertEquals(101, candidateService.editCandidate(request2).getId());
        assertEquals(2, candidateService.findAllByPartyId('O').size());
        assertEquals(4, candidateService.findAll().size());
    }

    @Test
    @Sql("/test-data.sql")
    void deleteCandidate() {
        assertThrows(ResponseStatusException.class, () -> candidateService.deleteCandidate(999L));

        candidateService.deleteCandidate(101L);
        assertEquals(2, candidateService.findAllByPartyId('A').size());
        assertEquals(3, candidateService.findAll().size());
    }
}