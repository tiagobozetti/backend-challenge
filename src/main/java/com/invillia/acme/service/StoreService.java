package com.invillia.acme.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.repositories.StoreRepository;
import com.invillia.acme.service.exceptions.DataIntegrityException;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private BCryptPasswordEncoder bCryp;

	public Store find(Integer id) {
		Optional<Store> cat = storeRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Store.class.getName()));
	}
	
	@Transactional
	public Store insert(Store store) {
		store.setId(null);
		store.setSenha(store.getSenha());
		return storeRepository.save(store);
	}
	
	public Store update(Store store) {
	 	Store newStore = find(store.getId());
		updateData(newStore, store);
		return storeRepository.save(newStore);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			storeRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Store has association");
		}
	}
	
	public List<Store> findAll() {
		return storeRepository.findAll();
	}
	
	public Page<Store> findPage(Integer page, Integer linesPerPage, String storeBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), storeBy);
		return storeRepository.findAll(pageRequest);
	}
	
	public Store fromDTO(StoreDTO storeDTO) {
		Store store = new Store(storeDTO.getId(), storeDTO.getName(), storeDTO.getEmail(), bCryp.encode(storeDTO.getSenha()));		
		
		return store;
	}
	
	private void updateData(Store newStore, Store store) {
		newStore.setName(store.getName());
		for (Address address: store.getAddresses()) {
			addressService.update(address);
		}
	}
}
