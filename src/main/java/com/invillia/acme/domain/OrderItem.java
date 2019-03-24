package com.invillia.acme.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invillia.acme.dto.OrderItemDTO;

@Entity
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	private String description;
	
	private Double unitPrice;
	
	private Double quantity;
	
	private Boolean refunded = false;
	
	public OrderItem() {
		
	}
	
	public OrderItem(Integer id, Order order, String description, Double unitPrice, Double quantity) {
		super();
		this.id = id;
		this.order = order;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	public OrderItem(OrderItemDTO orderItemDTO) {
		super();
		this.id = orderItemDTO.getId();
		this.description = orderItemDTO.getDescription();
		this.unitPrice = orderItemDTO.getUnitPrice();
		this.quantity = orderItemDTO.getQuantity();
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Boolean getRefunded() {
		return refunded;
	}

	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}
	
}
