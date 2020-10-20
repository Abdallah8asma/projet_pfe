package com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.fnClient;

/**
 * @author Ameni Berrich
 *
 */
public class ClientFnReportingValue implements Comparable<ClientFnReportingValue>{
	
	private Long id;
	private Long regionId;
	private Long partieIntId;
	private Double soldeInit;
	private Double chiffreDaffaire;
	private Double reglement;
	private Double paiement;
	private Double impaye;
	private Double soldeActuel;
	
	private String clientReference;
	private String clientAbreviation;
	private String regionDesignation;
	
	public int compareTo(ClientFnReportingValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Double getSoldeInit() {
		return soldeInit;
	}

	public void setSoldeInit(Double soldeInit) {
		this.soldeInit = soldeInit;
	}

	public Double getChiffreDaffaire() {
		return chiffreDaffaire;
	}

	public void setChiffreDaffaire(Double chiffreDaffaire) {
		this.chiffreDaffaire = chiffreDaffaire;
	}

	public Double getReglement() {
		return reglement;
	}

	public void setReglement(Double reglement) {
		this.reglement = reglement;
	}

	public Double getPaiement() {
		return paiement;
	}

	public void setPaiement(Double paiement) {
		this.paiement = paiement;
	}

	public Double getImpaye() {
		return impaye;
	}

	public void setImpaye(Double impaye) {
		this.impaye = impaye;
	}

	public Double getSoldeActuel() {
		return soldeActuel;
	}

	public void setSoldeActuel(Double soldeActuel) {
		this.soldeActuel = soldeActuel;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getRegionDesignation() {
		return regionDesignation;
	}

	public void setRegionDesignation(String regionDesignation) {
		this.regionDesignation = regionDesignation;
	}
	
}
