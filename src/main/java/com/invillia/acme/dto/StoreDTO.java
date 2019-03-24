package com.invillia.acme.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invillia.acme.domain.Store;

public class StoreDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String senha;
	private String email;
	private List<AddressDTO> addresses = new ArrayList<>();
	
	private StoreDTO() {
		
	}

	public StoreDTO(Integer id, String name, String email, String senha) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.senha = senha;
	}
	
	public StoreDTO(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.email = store.getEmail();
		this.senha = store.getSenha();
		this.addresses = store.getAddresses().stream().map(obj -> new AddressDTO(obj)).collect(Collectors.toList());
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

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
}
