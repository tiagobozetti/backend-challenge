package com.invillia.acme.dto;

import java.io.Serializable;

public class OrderItemDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String description;
	private Double unitPrice;
	private Double quantity;
	
	public OrderItemDTO() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
		
}
