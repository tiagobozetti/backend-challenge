package com.invillia.acme.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.service.AddressService;
import com.invillia.acme.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/stores")
@Api(value = "Store", description = "Manage store")
public class StoreResource {
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private AddressService addressService;

	@ApiOperation(value = "Returns a Store by id")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StoreDTO> find(@PathVariable Integer id) {
		Store store = storeService.find(id);
		StoreDTO storeDTO = new StoreDTO(store);
		
		return ResponseEntity.ok().body(storeDTO);
	}
	
	@ApiOperation(value = "Create a store and yours addresses")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid StoreDTO storeDTO) {
		Store store = storeService.fromDTO(storeDTO);
		
		store = storeService.insert(store);
		
		List<Address> listAddress = storeDTO.getAddresses().stream().map(obj -> new Address(obj)).collect(Collectors.toList());
		addressService.insert(listAddress, store);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Change a store")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid StoreDTO storeDTO, @PathVariable Integer id) {
		Store store = storeService.fromDTO(storeDTO);
		store.setId(id);
		store = storeService.update(store);
		return ResponseEntity.noContent().build();
	}
}
