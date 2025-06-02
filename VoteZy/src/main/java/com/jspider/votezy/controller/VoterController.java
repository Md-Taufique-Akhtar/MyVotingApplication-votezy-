package com.jspider.votezy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.votezy.entity.Voter;
import com.jspider.votezy.services.VoterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
	private VoterService  voterService;
	
	@Autowired
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
	//201-resourse created 
	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
		Voter saveVoter = voterService.registerVoter(voter);
		return new ResponseEntity<>(saveVoter, HttpStatus.CREATED);
	}
}
