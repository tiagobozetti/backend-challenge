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

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.enums.StatusOrder;
import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.service.exceptions.DataIntegrityException;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public Order find(Integer id) {
		Optional<Order> cat = orderRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
	}
	
	@Transactional
	public Order insert(Order order) {
		order.setId(null);
		return orderRepository.save(order);
	}
	
	public Order update(Order order) {
//	 	Order newOrder = find(order.getId());
//		updateData(newOrder, order);
//		return orderRepository.save(newOrder);
		return null;
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			orderRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Order has association");
		}
	}
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return orderRepository.findAll(pageRequest);
	}

	public Order fromDTO(@Valid OrderDTO orderDTO) {
		return new Order();
	}
	
	public List<Order> findByStatus(StatusOrder statusOrder) {
		return orderRepository.findByStatus(statusOrder.getId());
	}
	
}
