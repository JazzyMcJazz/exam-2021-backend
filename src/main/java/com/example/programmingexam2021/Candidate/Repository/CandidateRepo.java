package com.example.programmingexam2021.Candidate.Repository;

import com.example.programmingexam2021.Candidate.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByParty_Id(char id);
}
