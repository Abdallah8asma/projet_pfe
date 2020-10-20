package com.gpro.consulting.tiers.logistique.coordination.gs.value;

// TODO: Auto-generated Javadoc
/**
 * The Class MagasinValue.
 */
public class MagasinValue {

	/** The id. */
	private Long id;

	/** The designation. */
	private String designation;

	/** The pi com site id. */
	private Long piComSiteId;

	/** The depot. */

	private Boolean depot;

	/** The pointVente. */

	private Boolean pointVente;

	private Long boutiqueId;

	private Long societeId;

	private String boutiqueAbreviation;

	private String societeAbreviation;

	public Long getSocieteId() {
		return societeId;
	}

	public void setSocieteId(Long societeId) {
		this.societeId = societeId;
	}

	public String getSocieteAbreviation() {
		return societeAbreviation;
	}

	public void setSocieteAbreviation(String societeAbreviation) {
		this.societeAbreviation = societeAbreviation;
	}

	public String getBoutiqueAbreviation() {
		return boutiqueAbreviation;
	}

	public void setBoutiqueAbreviation(String boutiqueAbreviation) {
		this.boutiqueAbreviation = boutiqueAbreviation;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Boolean getDepot() {
		return depot;
	}

	public void setDepot(Boolean depot) {
		this.depot = depot;
	}

	public Boolean getPointVente() {
		return pointVente;
	}

	public void setPointVente(Boolean pointVente) {
		this.pointVente = pointVente;
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
	 * Gets the pi com site id.
	 *
	 * @return the pi com site id
	 */
	public Long getPiComSiteId() {
		return piComSiteId;
	}

	/**
	 * Sets the pi com site id.
	 *
	 * @param piComSiteId the new pi com site id
	 */
	public void setPiComSiteId(Long piComSiteId) {
		this.piComSiteId = piComSiteId;
	}

}
