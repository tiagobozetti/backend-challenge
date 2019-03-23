package com.invillia.acme.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.enums.StatusPayment;
import com.invillia.acme.dto.PaymentDTO;
import com.invillia.acme.service.PaymentService;

@RestController
@RequestMapping(value="/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService paymentService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Payment> find(@PathVariable Integer id) {
		Payment payment = paymentService.find(id);
		
		return ResponseEntity.ok().body(payment);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid PaymentDTO paymentDTO) {
		
		Payment payment = paymentService.fromDTO(paymentDTO);
		payment.setStatus(StatusPayment.OPEN.getId());
		payment = paymentService.insert(payment);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/confirm/{id}", method=RequestMethod.POST)
	public ResponseEntity<Payment> confirm(@PathVariable Integer id) {
		Payment payment = paymentService.find(id);
		paymentService.confirma(payment.getId());
		return ResponseEntity.ok().body(payment);
	}
}
