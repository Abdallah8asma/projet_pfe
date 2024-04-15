package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

public class ProduitCouleurValue {

	/** Id */
	private Long id;
	
	/** The eb_couleur_id. */
	private long ebCouleurId;

	/** eb_produit_id */
	private long ebProduitId;
/** Designation */
	private String designation;
	
	public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

	/************* Getters & Setters *****************/
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
	 * @return the ebCouleurId
	 */
	public long getEbCouleurId() {
		return ebCouleurId;
	}

	/**
	 * @param ebCouleurId the ebCouleurId to set
	 */
	public void setEbCouleurId(long ebCouleurId) {
		this.ebCouleurId = ebCouleurId;
	}

	/**
	 * @return the ebProduitId
	 */
	public long getEbProduitId() {
		return ebProduitId;
	}

	/**
	 * @param ebProduitId the ebProduitId to set
	 */
	public void setEbProduitId(long ebProduitId) {
		this.ebProduitId = ebProduitId;
	}

	
}
