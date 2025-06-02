package com.jspider.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspider.votezy.entity.Candidate;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	public List<Candidate> findAllByOrderByVoteCountDesc(); 
}
