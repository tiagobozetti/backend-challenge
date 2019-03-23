package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
