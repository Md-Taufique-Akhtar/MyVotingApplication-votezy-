package com.jspider.votezy.entity;

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
	private Voter voter;
	
	@ManyToOne //candidate got many vote 
	@JoinColumn(name="candidate_id", unique=true)
	private Candidate candidate;
} 
