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

	@Override
	public String toString() {
		return "ResultatRechecheReglementElementValue [id=" + id + ", reference=" + reference + ", date=" + date
				+ ", montantTotal=" + montantTotal + ", partieIntId=" + partieIntId + ", partieIntAbreviation="
				+ partieIntAbreviation + ", partieIntReference=" + partieIntReference + "]";
	}

}
