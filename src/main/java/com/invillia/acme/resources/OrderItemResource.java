package com.invillia.acme.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.service.OrderItemService;

@RestController
@RequestMapping(value="/orders")
public class OrderItemResource {
	
	@Autowired
	private OrderItemService orderItemService;

	@RequestMapping(value="/{id}/items", method=RequestMethod.GET)
	public ResponseEntity<List<OrderItem>> find(@PathVariable Integer id) {
		List<OrderItem> list = orderItemService.findByOrderId(id);
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}/items/{idItem}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid OrderItem orderItem, @PathVariable Integer id, @PathVariable Integer idItem) {
		OrderItem item = orderItemService.find(idItem);
		orderItem.setOrder(new Order(id));
		orderItem = orderItemService.update(orderItem);
		return ResponseEntity.noContent().build();
	}
}
