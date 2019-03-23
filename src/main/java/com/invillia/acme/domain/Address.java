package com.invillia.acme.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invillia.acme.dto.AddressDTO;

@Entity
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String streetAddress;
	@NotEmpty
	private String number;
	@NotEmpty
	private String zipCode;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="store_id")
	@NotNull	
	private Store store;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	@NotNull
	private City city;
	
	public Address() {
		
	}

	public Address(Integer id, String streetAddress, String number, String zipCode,
			Store store, City city) {
		super();
		this.id = id;
		this.streetAddress = streetAddress;
		this.number = number;
		this.zipCode = zipCode;
		this.store = store;
		this.city = city;
	}

	public Address(AddressDTO addressDTO) {
		super();
		this.id = addressDTO.getId();
		this.streetAddress = addressDTO.getStreetAddress();
		this.number = addressDTO.getNumber();
		this.zipCode = addressDTO.getZipCode();
		this.store = new Store(addressDTO.getIdStoreDTO());
		this.city = new City(addressDTO.getCityDTO());
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
