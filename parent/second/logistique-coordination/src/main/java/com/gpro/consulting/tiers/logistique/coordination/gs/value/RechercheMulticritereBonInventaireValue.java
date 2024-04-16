package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;
import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class RechercheMulticritereBonInventaireValue {
	
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

	private Long idDepot;

	private String type;
	
	private boolean isOptimized;
	
	
	
	private Long boutiqueId;

	
	

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

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
