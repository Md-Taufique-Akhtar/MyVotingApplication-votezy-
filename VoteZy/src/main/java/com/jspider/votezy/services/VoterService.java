package com.jspider.votezy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.votezy.entity.Voter;
import com.jspider.votezy.exception.DuplicateResourceException;
import com.jspider.votezy.exception.ResourceNotFoundException;
import com.jspider.votezyrepository.CandidateRepository;
import com.jspider.votezyrepository.VoterRepository;
@Service
public class VoterService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepostory;
	
	@Autowired
	public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepostory) {
		super();
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
			throw new ResourceNotFoundException("Voter with id "+voter.getId()+" not found!");
			
		}
		return voter;
	}
	
	
}
