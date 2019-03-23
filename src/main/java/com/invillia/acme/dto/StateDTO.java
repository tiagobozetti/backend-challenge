package com.invillia.acme.dto;

import java.io.Serializable;

import com.invillia.acme.domain.State;

public class StateDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String abreviation;
	
	public StateDTO() {
		
	}

	public StateDTO(Integer id, String name, String abreviation) {
		super();
		this.id = id;
		this.name = name;
		this.abreviation = abreviation;
	}
	
	public StateDTO(State state) {
		super();
		this.id = state.getId();
		this.name = state.getName();
		this.abreviation = state.getAbreviation();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	
}
