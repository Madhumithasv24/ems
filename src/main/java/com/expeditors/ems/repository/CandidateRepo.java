package com.expeditors.ems.repository;

import com.expeditors.ems.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate,Integer> {

}
