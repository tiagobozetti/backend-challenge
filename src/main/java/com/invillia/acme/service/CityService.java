package com.invillia.acme.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.City;
import com.invillia.acme.repositories.CityRepository;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	public City find(Integer id) {
		Optional<City> cat = cityRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + City.class.getName()));
	}
	
	@Transactional
	public City insert(City city) {
		city.setId(null);
		return cityRepository.save(city);
	}
	
	public City update(City city) {
		City newCity = find(city.getId());
		updateData(newCity, city);
		return cityRepository.save(newCity);
	}
	
	private void updateData(City newCity, City city) {
		newCity.setName(city.getName());
		newCity.setState(city.getState());
	}
}
