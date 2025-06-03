package com.jspider.votezy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.votezy.entity.Candidate;
import com.jspider.votezy.entity.Vote;
import com.jspider.votezy.exception.ResourceNotFoundException;
import com.jspider.votezy.repository.CandidateRepository;

@Service
public class CandidateService {
	private CandidateRepository candidateRepository;
	@Autowired
	public CandidateService(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	//add Candidate method
	public Candidate addCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	//all candidate list
	public List<Candidate>getAllCandidate(){
		return candidateRepository.findAll();
		
	}
	
	public Candidate getCandidateById(Long id) {
		Candidate candidate=candidateRepository.findById(id).orElse(null);
		if(candidate == null) {
			throw new ResourceNotFoundException("Candidate with id "+id+" not found");
		}
		return candidate;
	}
	
	//update 
	public Candidate updatedCandidate(Long id, Candidate updatedCandidaate) {
		Candidate candidate =getCandidateById(id);
		if(updatedCandidaate.getName()!=null) {
			candidate.setName(updatedCandidaate.getName());
		}
		
		if(updatedCandidaate.getParty()!=null) {
			candidate.setName(updatedCandidaate.getParty());
		}
		return candidateRepository.save(candidate);
	}
	
	//delete-if party not getting any vote get delete , if candidate got vote then his id will be null in votetable
	//link break
	public void deleteCandidate(Long id) {
		Candidate candidate =getCandidateById(id);
		List<Vote>votes=candidate.getVote();
		for(Vote v:votes) {
			v.setCandidate(null);
		}
		candidate.getVote().clear();
		candidateRepository.delete(candidate);
	}
}
