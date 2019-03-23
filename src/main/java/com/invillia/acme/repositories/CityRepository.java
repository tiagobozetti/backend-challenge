package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
