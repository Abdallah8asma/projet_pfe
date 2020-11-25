package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

public class UtilsValue {
	private Long id;
	
	/** The designation. */
	private String designation;
	private String description;
	private String type;
	
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/************* Getters & Setters *****************/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
