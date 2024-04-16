package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

 

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class BonInventaireValue implements Comparable<BonInventaireValue> {

	private Long id;

	private String reference;

	private Calendar date;

	private String observations;

	private Double metrageTotal;

	private List<DetBonInventaireValue> listDetBonInventaire;

	private Boolean blSuppression;

	private Calendar dateSuppression;

	private Calendar dateCreation;

	private Calendar dateModification;

	private String version;

	private Long idDepot;

	private String type;
	
	private String depot;
	
	private Long boutiqueId;

	
	private String refAvantChangement;
	
	
	

	public String getRefAvantChangement() {
		return refAvantChangement;
	}



	public void setRefAvantChangement(String refAvantChangement) {
		this.refAvantChangement = refAvantChangement;
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



	public Calendar getDate() {
		return date;
	}



	public void setDate(Calendar date) {
		this.date = date;
	}



	public String getObservations() {
		return observations;
	}



	public void setObservations(String observations) {
		this.observations = observations;
	}



	public Double getMetrageTotal() {
		return metrageTotal;
	}



	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}



	public List<DetBonInventaireValue> getListDetBonInventaire() {
		return listDetBonInventaire;
	}



	public void setListDetBonInventaire(List<DetBonInventaireValue> listDetBonInventaire) {
		this.listDetBonInventaire = listDetBonInventaire;
	}



	public Boolean getBlSuppression() {
		return blSuppression;
	}



	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}



	public Calendar getDateSuppression() {
		return dateSuppression;
	}



	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}



	public Calendar getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}



	public Calendar getDateModification() {
		return dateModification;
	}



	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
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
	
	 


	@Override
	public int compareTo(BonInventaireValue element) {
		 
		return (element.getReference().compareTo(reference));
	}

}
