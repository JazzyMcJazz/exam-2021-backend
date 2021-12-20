package com.example.programmingexam2021.Party.Repository;

import com.example.programmingexam2021.Party.Entity.Party;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartyRepoTest {

    @Autowired
    PartyRepo partyRepo;

    @Test
    @Sql("/test-data.sql")
    void testRepo() {

        Party party = partyRepo.findById('A').orElseThrow();

        assertEquals("Socialdemokratiet", party.getName());
        assertEquals(6, partyRepo.findAll().size());

        party.setName("Socialdemokraterne");
        party = partyRepo.save(party);
        assertEquals('A', party.getId());
        assertEquals("Socialdemokraterne", party.getName());

        partyRepo.deleteById(party.getId());
        assertEquals(5, partyRepo.findAll().size());
    }
}