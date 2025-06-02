package com.jspider.votezy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Voter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name is requied")
	private String name;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid mail formate")
	private String email;
	
	
	private boolean hasVoted=false;
	
	@OneToOne(mappedBy = "voter", cascade = CascadeType.ALL) //we can use REMOVE
	private Vote vote; // inverse side
}
