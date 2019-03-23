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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.enums.StatusOrder;
import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.service.AddressService;
import com.invillia.acme.service.OrderItemService;
import com.invillia.acme.service.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderItemService orderItemService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order order = orderService.find(id);
		
		return ResponseEntity.ok().body(order);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Order>> findStatus(@RequestParam(value="status", defaultValue="OPEN") String status) {
		List<Order> order = orderService.findByStatus(StatusOrder.valueOf(status));
		
		if (order.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(order);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid OrderDTO orderDTO) {
		Address address = new Address(orderDTO.getAddressDTO());
		addressService.insert(address);
		
		Order order = orderService.fromDTO(orderDTO);
		order.setAddress(address);
		order.setStatus(StatusOrder.OPEN.getId());
		order = orderService.insert(order);
		
		List<OrderItem> listItems = orderDTO.getItems().stream().map(obj -> new OrderItem(obj)).collect(Collectors.toList());
		for(OrderItem item: listItems) {
			item.setOrder(order);
			orderItemService.insert(item);
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
