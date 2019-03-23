package com.invillia.acme.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invillia.acme.dto.PaymentDTO;

@Entity
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId
	private Order order;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date paymentDate;
	
	private String creditCardNumber;
	
	private Integer status;
	
	public Payment() {
		
	}

	public Payment(Integer id, Order order, Date paymentDate, String creditCardNumber, Integer status) {
		super();
		this.id = id;
		this.order = order;
		this.paymentDate = paymentDate;
		this.creditCardNumber = creditCardNumber;
		this.status = status;
	}
	
	public Payment(PaymentDTO paymentDTO) {
		super();
		this.id = paymentDTO.getId();
		this.order = new Order(paymentDTO.getOrderId());
		this.paymentDate = paymentDTO.getPaymentDate();
		this.creditCardNumber = paymentDTO.getCreditCardNumber();
		this.status = paymentDTO.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
