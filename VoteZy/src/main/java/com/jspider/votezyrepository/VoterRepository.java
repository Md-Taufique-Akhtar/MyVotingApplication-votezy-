package com.jspider.votezyrepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspider.votezy.entity.Voter;
@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
	public boolean existsByEmail(String email);
}
