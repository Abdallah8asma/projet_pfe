package com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value;

import java.util.Calendar;
import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class BonTransfertValue implements Comparable<BonTransfertValue> {

	private Long id;

	private String reference;

	private Calendar date;

	private String observations;

	private Double metrageTotal;

	private List<DetBonTransfertValue> listDetBonTransfert;

	private Boolean blSuppression;

	private Calendar dateSuppression;

	private Calendar dateCreation;

	private Calendar dateModification;

	private String version;

	private Long idDepot;

	private String type;

	private String depotOrigine;
	private String depotDestination;

	private Long boutiqueId;

	private String refAvantChangement;

	private Long idDepotOrigine;

	private Long idDepotDestination;

	private String referenceSortie;

	private String status;

	private String referenceReception;

	private String statusAuto;

	public String getStatusAuto() {
		return statusAuto;
	}

	public void setStatusAuto(String statusAuto) {
		this.statusAuto = statusAuto;
	}

	public String getReferenceReception() {
		return referenceReception;
	}

	public void setReferenceReception(String referenceReception) {
		this.referenceReception = referenceReception;
	}

	public String getDepotOrigine() {
		return depotOrigine;
	}

	public void setDepotOrigine(String depotOrigine) {
		this.depotOrigine = depotOrigine;
	}

	public String getDepotDestination() {
		return depotDestination;
	}

	public void setDepotDestination(String depotDestination) {
		this.depotDestination = depotDestination;
	}

	public List<DetBonTransfertValue> getListDetBonTransfert() {
		return listDetBonTransfert;
	}

	public void setListDetBonTransfert(List<DetBonTransfertValue> listDetBonTransfert) {
		this.listDetBonTransfert = listDetBonTransfert;
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
	public int compareTo(BonTransfertValue element) {

		return (element.getReference().compareTo(reference));
	}

}
