package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class ResultatRechecheReglementElementValue implements Comparable<ResultatRechecheReglementElementValue> {

	private Long id;

	private String reference;
	private Calendar date;
	private Double montantTotal;

	private Long partieIntId;
	private String partieIntAbreviation;
	private String partieIntReference;

	private Long groupeClientId;

	private String groupeClientDesignation;
	
	private boolean declarer;
	
	private String refAvantChangement;
	
	
	private String referenceDetailReglement;
	private Double montantBlRegle ;
	private Double montantFactureRegle ;
	private Double montantFactureAvoirRegle ;
	private Long nombreBlRegle;
	private Long nombreFactureRegle;
	private Long nombreFactureAvoirRegle;
	private Double montantBlNonRegle;

	private Double montantFactureNonRegle;

	private Double montantFactureAvoirNonRegle;

	private Long nombreBlNonRegle;

	private Long nombreFactureNonRegle;

	private Long nombreFactureAvoirNonRegle;

	public String getReferenceDetailReglement() {
		return referenceDetailReglement;
	}

	public void setReferenceDetailReglement(String referenceDetailReglement) {
		this.referenceDetailReglement = referenceDetailReglement;
	}

	public String getRefAvantChangement() {
		return refAvantChangement;
	}

	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
	}

	public boolean isDeclarer() {
		return declarer;
	}

	public void setDeclarer(boolean declarer) {
		this.declarer = declarer;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public int compareTo(ResultatRechecheReglementElementValue element) {
		return (element.getId().compareTo(id));
	}

	public String getGroupeClientDesignation() {
		return groupeClientDesignation;
	}

	public void setGroupeClientDesignation(String groupeClientDesignation) {
		this.groupeClientDesignation = groupeClientDesignation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public String getPartieIntReference() {
		return partieIntReference;
	}

	public void setPartieIntReference(String partieIntReference) {
		this.partieIntReference = partieIntReference;
	}

	public Double getMontantBlRegle() {
		return montantBlRegle;
	}

	public void setMontantBlRegle(Double montantBlRegle) {
		this.montantBlRegle = montantBlRegle;
	}

	public Double getMontantFactureRegle() {
		return montantFactureRegle;
	}

	public void setMontantFactureRegle(Double montantFactureRegle) {
		this.montantFactureRegle = montantFactureRegle;
	}

	public Double getMontantFactureAvoirRegle() {
		return montantFactureAvoirRegle;
	}

	public void setMontantFactureAvoirRegle(Double montantFactureAvoirRegle) {
		this.montantFactureAvoirRegle = montantFactureAvoirRegle;
	}

	public Long getNombreBlRegle() {
		return nombreBlRegle;
	}

	public void setNombreBlRegle(Long nombreBlRegle) {
		this.nombreBlRegle = nombreBlRegle;
	}

	public Long getNombreFactureRegle() {
		return nombreFactureRegle;
	}

	public void setNombreFactureRegle(Long nombreFactureRegle) {
		this.nombreFactureRegle = nombreFactureRegle;
	}

	public Long getNombreFactureAvoirRegle() {
		return nombreFactureAvoirRegle;
	}

	public void setNombreFactureAvoirRegle(Long nombreFactureAvoirRegle) {
		this.nombreFactureAvoirRegle = nombreFactureAvoirRegle;
	}


	public Double getMontantBlNonRegle() {
		return montantBlNonRegle;
	}

	public void setMontantBlNonRegle(Double montantBlNonRegle) {
		this.montantBlNonRegle = montantBlNonRegle;
	}

	public Double getMontantFactureNonRegle() {
		return montantFactureNonRegle;
	}

	public void setMontantFactureNonRegle(Double montantFactureNonRegle) {
		this.montantFactureNonRegle = montantFactureNonRegle;
	}

	public Double getMontantFactureAvoirNonRegle() {
		return montantFactureAvoirNonRegle;
	}

	public void setMontantFactureAvoirNonRegle(Double montantFactureAvoirNonRegle) {
		this.montantFactureAvoirNonRegle = montantFactureAvoirNonRegle;
	}

	public Long getNombreBlNonRegle() {
		return nombreBlNonRegle;
	}

	public void setNombreBlNonRegle(Long nombreBlNonRegle) {
		this.nombreBlNonRegle = nombreBlNonRegle;
	}

	public Long getNombreFactureNonRegle() {
		return nombreFactureNonRegle;
	}

	public void setNombreFactureNonRegle(Long nombreFactureNonRegle) {
		this.nombreFactureNonRegle = nombreFactureNonRegle;
	}

	public Long getNombreFactureAvoirNonRegle() {
		return nombreFactureAvoirNonRegle;
	}

	public void setNombreFactureAvoirNonRegle(Long nombreFactureAvoirNonRegle) {
		this.nombreFactureAvoirNonRegle = nombreFactureAvoirNonRegle;
	}

	@Override
	public String toString() {
		return "ResultatRechecheReglementElementValue [id=" + id + ", reference=" + reference + ", date=" + date
				+ ", montantTotal=" + montantTotal + ", partieIntId=" + partieIntId + ", partieIntAbreviation="
				+ partieIntAbreviation + ", partieIntReference=" + partieIntReference + "]";
	}

}
