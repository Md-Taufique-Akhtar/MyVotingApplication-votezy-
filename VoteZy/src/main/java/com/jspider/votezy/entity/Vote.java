package com.jspider.votezy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne  //one person can vote one candidate
	@JoinColumn(name="voter_id", unique=true)   //this is owner side because of join column
	@JsonIgnore //only return vote id not to be return voter Id
	private Voter voter;
	
	@ManyToOne //candidate got many vote 
	@JoinColumn(name="candidate_id", unique=true)
	@JsonIgnore 
	private Candidate candidate;
	
	@JsonProperty("voterId")
	public Long getVoterId() {
		//if voter info not there at least return null
		return voter!=null ? voter.getId() : null;
	}
	
	@JsonProperty("CandidateId")
	public Long getCandidateId() {
		return candidate!=null ? candidate.getId() : null;
	}
} 
