package com.invillia.acme.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.OrderItemDTO;
import com.invillia.acme.repositories.OrderItemRepository;
import com.invillia.acme.service.exceptions.DataIntegrityException;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderItem find(Integer id) {
		Optional<OrderItem> cat = orderItemRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrderItem.class.getName()));
	}
	
	@Transactional
	public OrderItem insert(OrderItem orderItem) {
		orderItem.setId(null);
		return orderItemRepository.save(orderItem);
	}
	
	public OrderItem update(OrderItem orderItem) {
		OrderItem newOrderItem = find(orderItem.getId());
		updateData(newOrderItem, orderItem);
		return orderItemRepository.save(newOrderItem);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			orderItemRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("OrderItem has association");
		}
	}
	
	public List<OrderItem> findAll() {
		return orderItemRepository.findAll();
	}
	
	public Page<OrderItem> findPage(Integer page, Integer linesPerPage, String orderItemBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderItemBy);
		return orderItemRepository.findAll(pageRequest);
	}

	public OrderItem fromDTO(@Valid OrderItemDTO orderItemDTO) {
		return new OrderItem();
	}

	public List<OrderItem> findByOrderId(Integer id) {
		return orderItemRepository.findByOrderId(id);
	}
	
	private void updateData(OrderItem newOrderItem, OrderItem orderItem) {
		newOrderItem.setId(orderItem.getId());
		newOrderItem.setOrder(orderItem.getOrder());
		newOrderItem.setDescription(orderItem.getDescription());
		newOrderItem.setQuantity(orderItem.getQuantity());
		newOrderItem.setRefunded(orderItem.getRefunded());
		newOrderItem.setUnitPrice(orderItem.getUnitPrice());
	}
	
}
