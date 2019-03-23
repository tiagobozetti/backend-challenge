package com.invillia.acme.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.City;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repositories.AddressRepository;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;

	public Address find(Integer id) {
		Optional<Address> cat = addressRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Address.class.getName()));
	}

	@Transactional
	public Address insert(Address address) {
		address.setId(null);
		return addressRepository.save(address);
	}

	@Transactional
	public void insert(List<Address> listAddress, Store store) {
		for (Address add : listAddress) {

			if (add.getCity() != null) {
				if (add.getCity().getState() != null) {
					stateService.find(add.getCity().getState().getId());
				}
				
				if (add.getCity().getId() != null) {
					cityService.find(add.getCity().getId());							
				} else {
					cityService.insert(add.getCity());
				}
			}

			add.setStore(store);
			addressRepository.save(add);
		}
	}
	
	public Address update(Address address) {
	 	Address newAddress = find(address.getId());
		updateData(newAddress, address);
		return addressRepository.save(newAddress);
	}
	
	private void updateData(Address newAddress, Address address) {
		if (address.getCity() != null) {
			if (address.getCity().getId() != null) {
				City ci = cityService.find(address.getCity().getId());
				cityService.update(ci);
				address.setCity(ci);
			} else {
				cityService.insert(address.getCity());
			}
		}
		
		newAddress.setStreetAddress(address.getStreetAddress());
		newAddress.setNumber(address.getNumber());
		newAddress.setZipCode(address.getZipCode());
		newAddress.setCity(address.getCity());		
	}
}
