package com.gpro.consulting.tiers.commun.coordination.value.partieInteressee;

/**
 * The Class CategorieValue
 * 
 * @author $mohamed
 */
public class BanqueValue {

	/** The id. */
	private Long id;

	private String code;

	private String abreviation;

	/** The designation. */
	private String designation;

	private Boolean societe;

	public Boolean getSociete() {
		return societe;
	}

	public void setSociete(Boolean societe) {
		this.societe = societe;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
