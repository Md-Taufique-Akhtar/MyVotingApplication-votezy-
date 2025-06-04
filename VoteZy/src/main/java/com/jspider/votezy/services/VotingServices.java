package com.jspider.votezy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.votezy.entity.Candidate;
import com.jspider.votezy.entity.Vote;
import com.jspider.votezy.entity.Voter;
import com.jspider.votezy.exception.ResourceNotFoundException;
import com.jspider.votezy.exception.VoteNotAllowedException;
import com.jspider.votezy.repository.CandidateRepository;
import com.jspider.votezy.repository.VoteRepositiory;
import com.jspider.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;
@Service
public class VotingServices {
	private VoteRepositiory voteRepository; //1
	private CandidateRepository candidateRepository; //2.for the votecast
	private VoterRepository voterRepository; //3. hasVoted will be true so its required
	
	@Autowired
	public VotingServices(VoteRepositiory voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {

		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
		
	}
	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		//Check if the voter exists in the database.
		//if become true ie means there is no voter
		if(!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not found with ID "+voterId);
		}
		
		//this is for candidate
		if(!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate not found ID "+candidateId);
		}
		
		// How to check if a candidate has voted for themselves
		//findById()-return optional
		Voter voter=voterRepository.findById(voterId).get();
		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter ID: "+voterId+" has already casted vote");
		}
		
		//fetch candidate
		Candidate candidate=candidateRepository.findById(candidateId).get();
		Vote vote=new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
		voteRepository.save(vote);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		
		voter.setHasVoted(true);
		
		return vote;
	}
	
	//this method will return all votes data
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}
}
