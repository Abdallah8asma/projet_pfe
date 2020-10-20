/**
 * 
 */
package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

/**
 * @author Ameni
 *
 */
public class TraitementValue {
	
	/** The Id. */
	private Long id;

	/** The designation. */
	private String designation;

	/** The observations. */
	private String observations;
	
	/** The facture. */
	private boolean facture;

	/** Version */
	private String version;
	
	/** PU: Prix Unitaire */
	private Double pu;

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

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the facture
	 */
	public boolean isFacture() {
		return facture;
	}

	/**
	 * @param facture the facture to set
	 */
	public void setFacture(boolean facture) {
		this.facture = facture;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public Double getPU() {
		return pu;
	}

	public void setPU(Double pu) {
		this.pu = pu;
	}
	

	
}
