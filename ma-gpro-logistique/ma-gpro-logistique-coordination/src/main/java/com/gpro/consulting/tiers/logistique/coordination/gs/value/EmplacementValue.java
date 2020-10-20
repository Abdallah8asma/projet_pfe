package com.gpro.consulting.tiers.logistique.coordination.gs.value;



// TODO: Auto-generated Javadoc
/**
 * The Class EmplacementValue.
 */
public class EmplacementValue  {

	/** The id. */
	private Long id;

	/** The designation. */
	private String designation;

	//bi-directional many-to-one association to GsMagasin
	/** The magasin. */
	private Long magasin;

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

	public Long getMagasin() {
		return magasin;
	}

	public void setMagasin(Long magasin) {
		this.magasin = magasin;
	}



	
}
