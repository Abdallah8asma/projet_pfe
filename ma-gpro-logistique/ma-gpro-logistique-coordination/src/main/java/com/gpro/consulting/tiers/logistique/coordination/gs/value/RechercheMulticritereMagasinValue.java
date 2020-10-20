package com.gpro.consulting.tiers.logistique.coordination.gs.value;

public class RechercheMulticritereMagasinValue {

	/** The id. */
	private Long id;

	/** The designation. */
	private String designation;

	/** The pi com site id. */
	private Long piComSiteId;

	private Long boutiqueId;

	private Long societeId;

	private String depot;

	private String pointVente;

	public Long getSocieteId() {
		return societeId;
	}

	public void setSocieteId(Long societeId) {
		this.societeId = societeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getPiComSiteId() {
		return piComSiteId;
	}

	public void setPiComSiteId(Long piComSiteId) {
		this.piComSiteId = piComSiteId;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getPointVente() {
		return pointVente;
	}

	public void setPointVente(String pointVente) {
		this.pointVente = pointVente;
	}

}
