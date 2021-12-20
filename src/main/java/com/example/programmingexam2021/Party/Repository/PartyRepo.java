package com.example.programmingexam2021.Party.Repository;

import com.example.programmingexam2021.Party.Entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepo extends JpaRepository<Party, Character> {
}
