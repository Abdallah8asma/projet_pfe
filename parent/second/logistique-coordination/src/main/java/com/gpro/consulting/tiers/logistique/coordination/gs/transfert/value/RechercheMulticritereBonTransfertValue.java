package com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class RechercheMulticritereBonTransfertValue {

	private Long id;

	private String reference;

	private Calendar dateDe;
	private Calendar dateA;

	private String observations;

	private Double metrageMin;

	private Double metrageMax;

	private Boolean blSuppression;

	private Calendar dateSuppressionDe;
	private Calendar dateSuppressionA;

	private Calendar dateCreationDe;
	private Calendar dateCreationA;

	private Calendar dateModificationDe;
	private Calendar dateModificationA;

	private String version;

	private String type;

	private boolean isOptimized;

	private Long boutiqueId;

	private Long idDepotOrigine;

	private Long idDepotDestination;

	private String referenceSortie;

	private String status;

	private String statusAuto;

	private String referenceReception;

	public String getReferenceReception() {
		return referenceReception;
	}

	public void setReferenceReception(String referenceReception) {
		this.referenceReception = referenceReception;
	}

	public String getStatusAuto() {
		return statusAuto;
	}

	public void setStatusAuto(String statusAuto) {
		this.statusAuto = statusAuto;
	}

	public Long getIdDepotOrigine() {
		return idDepotOrigine;
	}

	public void setIdDepotOrigine(Long idDepotOrigine) {
		this.idDepotOrigine = idDepotOrigine;
	}

	public Long getIdDepotDestination() {
		return idDepotDestination;
	}

	public void setIdDepotDestination(Long idDepotDestination) {
		this.idDepotDestination = idDepotDestination;
	}

	public String getReferenceSortie() {
		return referenceSortie;
	}

	public void setReferenceSortie(String referenceSortie) {
		this.referenceSortie = referenceSortie;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateDe() {
		return dateDe;
	}

	public void setDateDe(Calendar dateDe) {
		this.dateDe = dateDe;
	}

	public Calendar getDateA() {
		return dateA;
	}

	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Double getMetrageMin() {
		return metrageMin;
	}

	public void setMetrageMin(Double metrageMin) {
		this.metrageMin = metrageMin;
	}

	public Double getMetrageMax() {
		return metrageMax;
	}

	public void setMetrageMax(Double metrageMax) {
		this.metrageMax = metrageMax;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppressionDe() {
		return dateSuppressionDe;
	}

	public void setDateSuppressionDe(Calendar dateSuppressionDe) {
		this.dateSuppressionDe = dateSuppressionDe;
	}

	public Calendar getDateSuppressionA() {
		return dateSuppressionA;
	}

	public void setDateSuppressionA(Calendar dateSuppressionA) {
		this.dateSuppressionA = dateSuppressionA;
	}

	public Calendar getDateCreationDe() {
		return dateCreationDe;
	}

	public void setDateCreationDe(Calendar dateCreationDe) {
		this.dateCreationDe = dateCreationDe;
	}

	public Calendar getDateCreationA() {
		return dateCreationA;
	}

	public void setDateCreationA(Calendar dateCreationA) {
		this.dateCreationA = dateCreationA;
	}

	public Calendar getDateModificationDe() {
		return dateModificationDe;
	}

	public void setDateModificationDe(Calendar dateModificationDe) {
		this.dateModificationDe = dateModificationDe;
	}

	public Calendar getDateModificationA() {
		return dateModificationA;
	}

	public void setDateModificationA(Calendar dateModificationA) {
		this.dateModificationA = dateModificationA;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
