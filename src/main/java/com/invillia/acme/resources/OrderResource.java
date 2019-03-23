package com.invillia.acme.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.domain.Order;
import com.invillia.acme.service.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order order = orderService.find(id);
		
		return ResponseEntity.ok().body(order);
	}
}
