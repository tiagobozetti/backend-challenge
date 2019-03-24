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

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.enums.StatusOrder;
import com.invillia.acme.domain.enums.StatusPayment;
import com.invillia.acme.dto.PaymentDTO;
import com.invillia.acme.service.PaymentService;
import com.invillia.acme.service.exceptions.GenericException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/payments")
@Api(value = "Payments", description = "Manage payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService paymentService;

	@ApiOperation(value = "Returns all payments")			
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Payment> find(@PathVariable Integer id) {
		Payment payment = paymentService.find(id);
		
		return ResponseEntity.ok().body(payment);
	}
	
	@ApiOperation(value = "Create a payment")			
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid PaymentDTO paymentDTO) {
		
		Payment payment = paymentService.fromDTO(paymentDTO);
		payment.setStatus(StatusPayment.OPEN.getId());
		payment = paymentService.insert(payment);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Change status")			
	@RequestMapping(value="/{id}/status", method=RequestMethod.PATCH)
	public ResponseEntity<Payment> confirm(@RequestBody @Valid PaymentDTO paymentDTO, @PathVariable Integer id) {
		Payment payment = paymentService.find(id);
		
		if (paymentDTO.getStatus() == null) {
			throw new GenericException("Status is required.");
		} else {
			if (paymentDTO.getStatus().equals(StatusOrder.REFUNDED.getId())) {			
				paymentService.confirm(id);
			} else {
				payment.setStatus(paymentDTO.getStatus());
				paymentService.update(payment);
			}
		}
		
		return ResponseEntity.noContent().build();
	}
}
