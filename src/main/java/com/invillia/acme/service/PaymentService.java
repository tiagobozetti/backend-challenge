package com.invillia.acme.service;

import java.util.Date;
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
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.enums.StatusOrder;
import com.invillia.acme.domain.enums.StatusPayment;
import com.invillia.acme.dto.PaymentDTO;
import com.invillia.acme.repositories.PaymentRepository;
import com.invillia.acme.service.exceptions.DataIntegrityException;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderService orderService;

	public Payment find(Integer id) {
		Optional<Payment> cat = paymentRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Payment.class.getName()));
	}
	
	@Transactional
	public Payment insert(Payment payment) {
		payment.setId(null);
		return paymentRepository.save(payment);
	}
	
	@Transactional
	public void delete(Integer id) {
		find(id);
		
		try {
			paymentRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Payment has association");
		}
	}
	
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}
	
	public Page<Payment> findPage(Integer page, Integer linesPerPage, String paymentBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), paymentBy);
		return paymentRepository.findAll(pageRequest);
	}

	public Payment fromDTO(@Valid PaymentDTO paymentDTO) {		
		return new Payment(paymentDTO);
	}
	
	@Transactional
	public void confirma(Integer id) {
		Payment payment = find(id);
		payment.setStatus(StatusPayment.CONCLUDED.getId());
		paymentRepository.save(payment);
		
		Order order = orderService.find(payment.getOrder().getId());
		order.setStatus(StatusOrder.CONCLUDED.getId());
		order.setConfirmationDate(new Date());
		orderService.update(order);
		
	}
	
}
