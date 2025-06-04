package com.jspider.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		List<Candidate> candidateList=this.candidateService.getAllCandidate();
		return  new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id){
		Candidate candidate=this.candidateService.getCandidateById(id);
		return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
	}
	//we should handle update with DTo
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate, @PathVariable
			Long id){
		Candidate updatedCandidate=this.candidateService.updatedCandidate(id, candidate);
		return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteCandidate(@PathVariable Long id){
		candidateService.deleteCandidate(id);
		return new  ResponseEntity<>("Candidate with id "+id+" deleted succesfully!", HttpStatus.OK);
	}
}
