package com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value;

import java.util.Calendar;

/**
 * 
 * @author Wahid Gazzah
 * @since 01/03/2016
 *
 */
public class BonLivraisonReportElementValue {

	private String reference;
	private String refexterne;
	
	private Calendar date;
	private Double montantHTaxe;
	private Double montantTTC;
	private Double montantTaxe;
	private Double montantRemise;
	private String observations;
	private String infoSortie;
	private String partieIntAbreviation;
	private Double metrageTotal;
	private String transporteur;
	
	//Added on 07/10/2016 by Zeineb Medimagh
	private String natureLivraison;
	private String groupePiDesignation;
	private Long Devise;
	
	
	public Long getDevise() {
		return Devise;
	}
	public void setDevise(Long devise) {
		Devise = devise;
	}
	public String getGroupePiDesignation() {
		return groupePiDesignation;
	}
	public void setGroupePiDesignation(String groupePiDesignation) {
		this.groupePiDesignation = groupePiDesignation;
	}
	public String getRefexterne() {
		return refexterne;
	}
	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
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
	public Double getMontantHTaxe() {
		return montantHTaxe;
	}
	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}
	public Double getMontantTTC() {
		return montantTTC;
	}
	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}
	public Double getMontantTaxe() {
		return montantTaxe;
	}
	public void setMontantTaxe(Double montantTaxe) {
		this.montantTaxe = montantTaxe;
	}
	public Double getMontantRemise() {
		return montantRemise;
	}
	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getInfoSortie() {
		return infoSortie;
	}
	public void setInfoSortie(String infoSortie) {
		this.infoSortie = infoSortie;
	}
	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}
	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}
	public Double getMetrageTotal() {
		return metrageTotal;
	}
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}
	public String getTransporteur() {
		return transporteur;
	}
	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}
	public String getNatureLivraison() {
		return natureLivraison;
	}
	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}
	
}
