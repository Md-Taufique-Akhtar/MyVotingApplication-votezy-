package com.jspider.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.votezy.dto.ElectionResultRequestDTO;
import com.jspider.votezy.dto.ElectionResultResponseDTO;
import com.jspider.votezy.entity.ElectionResult;
import com.jspider.votezy.services.ElectionResultService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class ElectionResultController {
	private ElectionResultService electionResultSevice;
	
	@Autowired
	public ElectionResultController(ElectionResultService electionResultSevice) {
		this.electionResultSevice = electionResultSevice;
	}
	@PostMapping("/declare-result")
	public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultDTO) {
		ElectionResult result=electionResultSevice.declareElectionResult(electionResultDTO.getElectionName());
		ElectionResultResponseDTO  responseDTO=new ElectionResultResponseDTO();
		responseDTO.setElectionName(result.getElectionName());
		responseDTO.setTotalVotes(result.getTotalVotes());
		responseDTO.setWinnerId(result.getWinnerId());
		responseDTO.setWinnerVotes(result.getWinner().getVoteCount());
		return ResponseEntity.ok(responseDTO);
	}
	
	public ResponseEntity<List<ElectionResult>> getAllResults(){
		List<ElectionResult> results=electionResultSevice.getAllResults();
		return ResponseEntity.ok(results);
	}
}
