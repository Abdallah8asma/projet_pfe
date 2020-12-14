package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

public class MoldsValue {
	private Long id;

	/** The designation. */
	private String designation;

	/** The reference. */
	private String reference;
	
	/** The emplacement. */
	private String emplacement;

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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

}
