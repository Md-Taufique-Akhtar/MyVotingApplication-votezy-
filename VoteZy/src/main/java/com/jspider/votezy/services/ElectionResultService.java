package com.jspider.votezy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jspider.votezy.entity.Candidate;
import com.jspider.votezy.entity.ElectionResult;
import com.jspider.votezy.exception.ResourceNotFoundException;
import com.jspider.votezy.repository.CandidateRepository;
import com.jspider.votezy.repository.ElectionResultRepository;
import com.jspider.votezy.repository.VoteRepository;

@Service
public class ElectionResultService {
	private CandidateRepository candidateRepository;
	private ElectionResultRepository electionResultRepository;
	private VoteRepository voteRepository;
	public ElectionResultService(CandidateRepository candidateRepository,
			ElectionResultRepository electionResultRepository, VoteRepository voteRepository) {

		this.candidateRepository = candidateRepository;
		this.electionResultRepository = electionResultRepository;
		this.voteRepository = voteRepository;
	}
	
	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult> existingResult=this.electionResultRepository.findByElectionName(electionName);
		if(existingResult.isPresent()) {
			return existingResult.get();
		}
		if(voteRepository.count()==0) {
			throw new IllegalStateException("can not declare the result as no votes have been");
		}
		List<Candidate>allCandidates=candidateRepository.findAllByOrderByVoteCountDesc();
		if(allCandidates.isEmpty()) {
			throw new ResourceNotFoundException("No candidate avaliable");
		}
		
		Candidate winner=allCandidates.get(0);
		int totalVotes=0;
		for(Candidate candidate : allCandidates) {
			totalVotes+=candidate.getVoteCount();
		}
		ElectionResult result = new ElectionResult();
		result.setElectionName(electionName);
		result.setWinner(winner);
		result.setTotalVotes(totalVotes);
		return electionResultRepository.save(result);
	}
	
	public List<ElectionResult>getAllResults(){
		return electionResultRepository.findAll();
	}
}
