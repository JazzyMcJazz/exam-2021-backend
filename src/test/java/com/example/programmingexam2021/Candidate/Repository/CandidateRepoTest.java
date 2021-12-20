package com.example.programmingexam2021.Candidate.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CandidateRepoTest {

    @Autowired
    CandidateRepo candidateRepo;

    @Test
    @Sql("/test-data.sql")
    void testRepo() {

        assertEquals(3, candidateRepo.findAllByParty_Id('A').size());
    }

}