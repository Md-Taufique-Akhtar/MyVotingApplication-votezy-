package com.jspider.votezy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.votezy.entity.Candidate;
import com.jspider.votezy.entity.Vote;
import com.jspider.votezy.entity.Voter;
import com.jspider.votezy.exception.DuplicateResourceException;
import com.jspider.votezy.exception.ResourceNotFoundException;
import com.jspider.votezy.repository.CandidateRepository;
import com.jspider.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;
@Service
public class VoterService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepostory;
	
	@Autowired
	public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepostory) {
		
		this.voterRepository = voterRepository;
		this.candidateRepostory = candidateRepostory;
	}
	
	//Register voter
	public Voter registerVoter(Voter voter){
		if(voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("Voter with email id "+voter.getEmail()+" already exist!");
		}
		return voterRepository.save(voter);
	}
	
	//this method will be return all voters data
	public List<Voter> getAllVoters(){
		return voterRepository.findAll();
	}
	//this method will be return single voter data
	public Voter getVoterById(Long id) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourceNotFoundException("Voter with id "+id+" not found!");
			
		}
		return voter;
	}
	
	//update method
	public Voter updateVoter(Long id, Voter updatedVoter) {
		Voter voter =voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourceNotFoundException("Voter with Id "+id+" not found!" );
		}
		voter.setName(updatedVoter.getName());
		voter.setEmail(updatedVoter.getEmail());
		return voterRepository.save(voter);
	}
	/**
	 * Deletes a voter by ID and updates the corresponding candidate's vote count if the voter has voted.
	 * This operation is transactional — if any part fails (e.g., delete or save), the whole operation rolls back.
	 */
	//delete voter so i have to delete candidate vote 
	// Ensures the operation is atomic: if voterRepository.delete(voter) throws an exception,
	// then candidateRepository.save(candidate) will also be rolled back automatically.
	@Transactional //use:-our operation will be become atomic if our voterRepository.delete(voter); throw exception then candidateRepostory.save(candidate); will be rollback 

	public void deleteVoter(Long id) {
		Voter voter =voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourceNotFoundException("Can not delete voter with id "+id+" as it does not exist!" );
		}
		Vote vote=voter.getVote();
		if(vote!=null) {
			Candidate candidate=vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candidateRepostory.save(candidate);
		}
		voterRepository.delete(voter);
		
	}
	
	
}
