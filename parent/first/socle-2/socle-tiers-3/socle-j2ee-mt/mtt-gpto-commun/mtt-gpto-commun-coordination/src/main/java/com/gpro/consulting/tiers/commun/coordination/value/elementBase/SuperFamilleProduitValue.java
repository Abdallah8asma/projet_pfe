package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Set;


// TODO: Auto-generated Javadoc
/**
 * The Class FamilleProduitValue.
 *@author med
 */
public class SuperFamilleProduitValue   {
	
	/** The id. */
	private Long id;
	
	/** The designation. */
	private String designation;
	
	/** List Sous Famille **/
	private Set<FamilleProduitValue> familles;
	
	/** The tva. */
	private Double tva;
	
	
	 private Long idTaxe ;
	 
	 
	 
	
	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
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

	public Set<FamilleProduitValue> getFamilles() {
		return familles;
	}

	public void setFamilles(Set<FamilleProduitValue> familles) {
		this.familles = familles;
	}




}