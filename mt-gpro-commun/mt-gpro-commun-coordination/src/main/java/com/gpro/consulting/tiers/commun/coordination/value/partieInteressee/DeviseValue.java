package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;

public class DeviseValue {
	
	public static Long EURO  = 1l;
	public static Long DINAR  = 2l;
	
	public static Long DOLLAR  = 3l;

	
	private Long id;

	/** designation. */
	private String designation;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
