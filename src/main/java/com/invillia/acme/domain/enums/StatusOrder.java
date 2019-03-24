package com.invillia.acme.domain.enums;

public enum StatusOrder {

	OPEN(1, "Open"),
	CONCLUDED(2, "Concluded"),
	REFUNDED(3, "Refunded");
	
	private int id;
	private String description;
	
	private StatusOrder(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static StatusOrder toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for(StatusOrder x : StatusOrder.values()) {
			if (id.equals(x.id)) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalid " + id);
	}
}
