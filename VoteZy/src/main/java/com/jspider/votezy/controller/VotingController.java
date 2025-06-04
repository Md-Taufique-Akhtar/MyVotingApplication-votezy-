package com.jspider.votezy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.votezy.dto.VoteRequestDTO;
import com.jspider.votezy.dto.VoteResponseDTO;
import com.jspider.votezy.entity.Vote;
import com.jspider.votezy.services.VotingServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {
	private VotingServices votingServices;

	public VotingController(VotingServices votingServices) {
		this.votingServices = votingServices;
	}
	
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest){
		Vote vote=votingServices.castVote(voteRequest.getVoterId(), voteRequest.getCandidateId());
		VoteResponseDTO voteResponse=new VoteResponseDTO("vote cast successfully! ", true, vote.getVoterId(), vote.getCandidateId());
		return new ResponseEntity<>(voteResponse,HttpStatus.OK);	
	}
	
	@GetMapping
	public ResponseEntity<List<Vote>> getAllVote(){
		List<Vote>voteList=votingServices.getAllVotes();
		return new ResponseEntity<>(voteList, HttpStatus.OK);
	}
}
