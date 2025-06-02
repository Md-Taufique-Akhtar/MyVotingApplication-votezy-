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

import com.jspider.votezy.entity.Voter;
import com.jspider.votezy.repository.ElectionResultRepository;
import com.jspider.votezy.services.VoterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {

    private final ElectionResultRepository electionResultRepository;
	private VoterService  voterService;
	
	@Autowired
	public VoterController(VoterService voterService, ElectionResultRepository electionResultRepository) {
		this.voterService = voterService;
		this.electionResultRepository = electionResultRepository;
	}
	//201-resourse created 
	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
		Voter saveVoter = voterService.registerVoter(voter);
		return new ResponseEntity<>(saveVoter, HttpStatus.CREATED);
	}
	
	//it return data based on id
	@GetMapping("/{id}")
	public ResponseEntity<Voter>getVoterById(@PathVariable Long id){
		Voter voter = voterService.getVoterById(id);
		return new ResponseEntity<>(voter, HttpStatus.OK);
	}
	//its return all data
	@GetMapping()
	public ResponseEntity<List<Voter>>getAllVoter(){
		List<Voter> voterList=voterService.getAllVoters();
		return new ResponseEntity<>(voterList, HttpStatus.OK);
	}
	
	//update voter
	@PutMapping("/update/{id}")
	public ResponseEntity<Voter>updateVoter(@PathVariable Long id, @RequestBody Voter voter){
		Voter updatedVoter=voterService.updateVoter(id, voter);
		return new ResponseEntity<>(updatedVoter, HttpStatus.OK);
	}
	
	//Delete rout (can use boolean)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteVoter(@PathVariable Long id){
		voterService.deleteVoter(id);
		return new ResponseEntity<>("Voter with id "+id+" deleted", HttpStatus.OK);
	}
	
	
}
