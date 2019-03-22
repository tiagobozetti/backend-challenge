package com.invillia.acme.domain.enums;

public enum StatusPayment {

	OPEN(1, "Open"),
	CONCLUDED(2, "Concluded");
	
	private int id;
	private String description;
	
	private StatusPayment(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static StatusPayment toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for(StatusPayment x : StatusPayment.values()) {
			if (id.equals(x.id)) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalid " + id);
	}
}
