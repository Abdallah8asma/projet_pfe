package com.gpro.consulting.tiers.commun.coordination.value.elementBase;



// TODO: Auto-generated Javadoc
/**
 * The Class SousFamilleProduitValue.
 * @author med
 */
public class SousFamilleProduitValue   {
	
	/** The id. */
	private Long id;
	
	/** The designation. */
	private String designation;
	
	/** The famille produit id. */
	private Long familleProduitId;
	
	
	
	/** The tva. */
	private Double tva;
	
	
	 private Long idTaxe ;
	
	
	/** The serialisable. */
	private boolean serialisable;
	
	
	
	
	
	
	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
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
	
	/**
	 * Gets the famille produit id.
	 *
	 * @return the famille produit id
	 */
	public Long getFamilleProduitId() {
		return familleProduitId;
	}
	
	/**
	 * Sets the famille produit id.
	 *
	 * @param familleProduitId the new famille produit id
	 */
	public void setFamilleProduitId(Long familleProduitId) {
		this.familleProduitId = familleProduitId;
	}

	@Override
	public String toString() {
		return "SousFamilleProduitValue [id=" + id + ", designation=" + designation + ", familleProduitId="
				+ familleProduitId + "]";
	}


}