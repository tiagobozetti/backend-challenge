package com.invillia.acme.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.invillia.acme.domain.City;

public class CityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	@NotNull
	private StateDTO stateDTO;

	public CityDTO() {
		
	}

	public CityDTO(Integer id, String name, StateDTO stateDTO) {
		super();
		this.id = id;
		this.name = name;
		this.stateDTO = stateDTO;
	}
	
	public CityDTO(City city) {
		this.id = city.getId();
		this.name = city.getName();
		this.stateDTO = new StateDTO(city.getState());
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

	public StateDTO getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}
	
	
}
