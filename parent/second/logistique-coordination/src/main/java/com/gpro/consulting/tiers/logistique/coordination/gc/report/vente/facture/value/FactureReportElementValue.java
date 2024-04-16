package com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value;

import java.util.Calendar;

/**
 * 
 * @author Wahid Gazzah
 * @since 01/03/2016
 *
 */
public class FactureReportElementValue {

	private String reference;
	private Calendar date;
	private Double montantHTaxe;
	private Double montantTTC;
	private Double montantTaxe;
	private Double montantRemise;
	private String observations;
	private String infoLivraison;
	private Double metrageTotal;
	private String etat;

	private Long partieIntId;
	private Long modepaiementId;

	private String partieIntAbreviation;
	private String modepaiement;
	private String marche;

	// Added on 18/10/2016 by Zeineb Medimagh
	private String natureLivraison;

	private String groupePiDesignation;
	private Long devise;
	
	private Double montantConverti;
	
	
	
	

	public Double getMontantConverti() {
		return montantConverti;
	}

	public void setMontantConverti(Double montantConverti) {
		this.montantConverti = montantConverti;
	}

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public String getGroupePiDesignation() {
		return groupePiDesignation;
	}

	public void setGroupePiDesignation(String groupePiDesignation) {
		this.groupePiDesignation = groupePiDesignation;
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

	public String getInfoLivraison() {
		return infoLivraison;
	}

	public void setInfoLivraison(String infoLivraison) {
		this.infoLivraison = infoLivraison;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public String getModepaiement() {
		return modepaiement;
	}

	public void setModepaiement(String modepaiement) {
		this.modepaiement = modepaiement;
	}

	public String getMarche() {
		return marche;
	}

	public void setMarche(String marche) {
		this.marche = marche;
	}

	public String getNatureLivraison() {
		return natureLivraison;
	}

	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
	}
}
