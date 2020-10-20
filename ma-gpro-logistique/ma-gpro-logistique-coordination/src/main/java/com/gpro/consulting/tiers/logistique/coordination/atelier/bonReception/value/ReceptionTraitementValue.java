/**
 * 
 */
package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

/**
 * @author Ameni
 *
 */
public class ReceptionTraitementValue {

	/** The Id. */
	private Long id;

	/** The traitementId. */
	private Long traitementId;
	
	/** The bonReceptionId. */
	private Long bonReceptionId;
	
	/** The machineId. */
	private Long machineId;
	
	/** The observations. */
	private String observations;
	
	/** Version */
	private String version;

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
	 * @return the traitementId
	 */
	public Long getTraitementId() {
		return traitementId;
	}

	/**
	 * @param traitementId the traitementId to set
	 */
	public void setTraitementId(Long traitementId) {
		this.traitementId = traitementId;
	}

	/**
	 * @return the bonReceptionId
	 */
	public Long getBonReceptionId() {
		return bonReceptionId;
	}

	/**
	 * @param bonReceptionId the bonReceptionId to set
	 */
	public void setBonReceptionId(Long bonReceptionId) {
		this.bonReceptionId = bonReceptionId;
	}

	/**
	 * @return the machineId
	 */
	public Long getMachineId() {
		return machineId;
	}

	/**
	 * @param machineId the machineId to set
	 */
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
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

}
