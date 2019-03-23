package com.invillia.acme.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.invillia.acme.domain.Address;

public class AddressDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String streetAddress;
	private String number;
	private String zipCode;
	private CityDTO cityDTO;
	private Integer idStoreDTO;
	
	public AddressDTO() {
		
	}

	public AddressDTO(Integer id, String streetAddress, String number, String zipCode, CityDTO cityDTO, Integer idStoreDTO) {
		super();
		this.id = id;
		this.streetAddress = streetAddress;
		this.number = number;
		this.zipCode = zipCode;
		this.cityDTO = cityDTO;
		this.idStoreDTO = idStoreDTO;
	}
	
	public AddressDTO(Address address) {
		this.id = address.getId();
		this.streetAddress = address.getStreetAddress();
		this.number = address.getNumber();
		this.zipCode = address.getZipCode();
		this.cityDTO = new CityDTO(address.getCity());
		this.idStoreDTO = address.getStore().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public CityDTO getCityDTO() {
		return cityDTO;
	}

	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}

	public Integer getIdStoreDTO() {
		return idStoreDTO;
	}

	public void setIdStoreDTO(Integer idStoreDTO) {
		this.idStoreDTO = idStoreDTO;
	}

}
