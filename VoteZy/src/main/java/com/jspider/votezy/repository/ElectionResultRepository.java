package com.jspider.votezy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspider.votezy.entity.ElectionResult;
@Repository
public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
	 Optional<ElectionResult> findByElectionName(String electionName);
}
