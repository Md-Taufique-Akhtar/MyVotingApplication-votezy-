package com.jspider.votezy.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name is required!")
	private String name;
	
	@NotBlank(message="Party is required!")
	private String party;
	
	
	private int voteCount=0;
	
	//The table that contains the foreign key is called the child table.
	//The owning side of a relationship is the side that defines the foreign key and is responsible for managing the association.
	@OneToMany(mappedBy="candidate", cascade = CascadeType.ALL)
	private List<Vote>vote;
}
