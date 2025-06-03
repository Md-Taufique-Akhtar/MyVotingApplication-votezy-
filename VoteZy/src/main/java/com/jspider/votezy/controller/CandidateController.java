package com.jspider.votezy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.votezy.entity.Candidate;
import com.jspider.votezy.services.CandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {
	private CandidateService candidateService;
	@Autowired
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	@PostMapping("/add-Candidate")
	public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate){
		Candidate savedCadidate=candidateService.addCandidate(candidate);
		return new ResponseEntity<Candidate>(savedCadidate, HttpStatus.CREATED);
	}
}
