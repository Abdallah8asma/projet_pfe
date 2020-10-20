package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

public class ProduitTailleValue {

	/** Id */
	private Long id;
	
	/** ebTailleId. */
	private long ebTailleId;

	/** ebProduitId */
	private long ebProduitId;
	
	/** Designation */
	private String designation;


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

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
	 * @return the ebTailleId
	 */
	public long getEbTailleId() {
		return ebTailleId;
	}

	/**
	 * @param ebTailleId the ebTailleId to set
	 */
	public void setEbTailleId(long ebTailleId) {
		this.ebTailleId = ebTailleId;
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