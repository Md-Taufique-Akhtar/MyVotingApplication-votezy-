package com.jspider.votezy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	/*
	 The @JsonIgnore annotation in Jackson is often used to stop cyclic references during serialization. Cyclic references occur when two (or more) entities reference each other, 
	 leading to infinite recursion when converting to JSON.
	 it occur most of time OneToMany
	 */
	//The table that contains the foreign key is called the child table.
	//The owning side of a relationship is the side that defines the foreign key and is responsible for managing the association.
	@OneToMany(mappedBy="candidate", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vote>vote;
}
