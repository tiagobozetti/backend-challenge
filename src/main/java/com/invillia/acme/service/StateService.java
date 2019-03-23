package com.invillia.acme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.State;
import com.invillia.acme.repositories.StateRepository;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	public State find(Integer id) {
		Optional<State> cat = stateRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + State.class.getName()));
	}
}
