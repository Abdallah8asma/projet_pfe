package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;



/**
 * The Class ProduitValue.
 * 
 * @author med
 */
public class ProduitSerialisableValue implements Comparable<ProduitSerialisableValue> {

private Long id;

	

	private Long produitId;

	private Long fournisseurId;
	

	private Long clientId;
	
	

	private Calendar dateAchat;
	

	private Calendar dateVente;

	private Calendar dateFacture;

	private String numBonReception;
	

	private String numBonLivraison;
	
	private String numFacture;


	private Double prixAchat;
	

	private Double prixVente;
	
	
	private Calendar dateFinGarantie;
	
	
	
	
	
	private String sousFamilleDesignation;
	private String familleDesignation;
	private String siteEntiteDesignation;
	private String partieIntersseDesignation;


	private String reference;
	private String designation;
	
	
	private String numSerie;
	
	
	
	private String fournisseurAbreviation;
	
	
	private boolean checked;
	
	
	
	
	private String blAncien;
	
	private String brRetour;
	
	
	

	private String factureAncien;
	

	private String factureAvoirAncien;
	
	
	private String numFactureAvoir;
	
	
	
	private Long boutiqueId;
	
	private Long magasinId;
	
	private String historiqueBTsortie;
	
	private String historiqueBTreception;
	
	private String boutiqueAbreviation;
	
	private String magasinDesignation;
	
	
	private String historiqueBSsortie;
	
	
	
	
	public String getHistoriqueBSsortie() {
		return historiqueBSsortie;
	}


	public void setHistoriqueBSsortie(String historiqueBSsortie) {
		this.historiqueBSsortie = historiqueBSsortie;
	}


	public String getBoutiqueAbreviation() {
		return boutiqueAbreviation;
	}


	public void setBoutiqueAbreviation(String boutiqueAbreviation) {
		this.boutiqueAbreviation = boutiqueAbreviation;
	}


	public String getMagasinDesignation() {
		return magasinDesignation;
	}


	public void setMagasinDesignation(String magasinDesignation) {
		this.magasinDesignation = magasinDesignation;
	}


	public Long getBoutiqueId() {
		return boutiqueId;
	}


	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}


	public Long getMagasinId() {
		return magasinId;
	}


	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}


	public String getHistoriqueBTsortie() {
		return historiqueBTsortie;
	}


	public void setHistoriqueBTsortie(String historiqueBTsortie) {
		this.historiqueBTsortie = historiqueBTsortie;
	}


	public String getHistoriqueBTreception() {
		return historiqueBTreception;
	}


	public void setHistoriqueBTreception(String historiqueBTreception) {
		this.historiqueBTreception = historiqueBTreception;
	}


	public String getNumFactureAvoir() {
		return numFactureAvoir;
	}


	public void setNumFactureAvoir(String numFactureAvoir) {
		this.numFactureAvoir = numFactureAvoir;
	}


	public String getFactureAncien() {
		return factureAncien;
	}


	public void setFactureAncien(String factureAncien) {
		this.factureAncien = factureAncien;
	}


	public String getFactureAvoirAncien() {
		return factureAvoirAncien;
	}


	public void setFactureAvoirAncien(String factureAvoirAncien) {
		this.factureAvoirAncien = factureAvoirAncien;
	}


	public String getBlAncien() {
		return blAncien;
	}


	public void setBlAncien(String blAncien) {
		this.blAncien = blAncien;
	}


	public String getBrRetour() {
		return brRetour;
	}


	public void setBrRetour(String brRetour) {
		this.brRetour = brRetour;
	}


	public Calendar getDateFacture() {
		return dateFacture;
	}


	public void setDateFacture(Calendar dateFacture) {
		this.dateFacture = dateFacture;
	}


	public String getNumFacture() {
		return numFacture;
	}


	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}


	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public String getFournisseurAbreviation() {
		return fournisseurAbreviation;
	}


	public void setFournisseurAbreviation(String fournisseurAbreviation) {
		this.fournisseurAbreviation = fournisseurAbreviation;
	}


	public String getNumSerie() {
		return numSerie;
	}


	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getSousFamilleDesignation() {
		return sousFamilleDesignation;
	}


	public void setSousFamilleDesignation(String sousFamilleDesignation) {
		this.sousFamilleDesignation = sousFamilleDesignation;
	}


	public String getFamilleDesignation() {
		return familleDesignation;
	}


	public void setFamilleDesignation(String familleDesignation) {
		this.familleDesignation = familleDesignation;
	}


	public String getSiteEntiteDesignation() {
		return siteEntiteDesignation;
	}


	public void setSiteEntiteDesignation(String siteEntiteDesignation) {
		this.siteEntiteDesignation = siteEntiteDesignation;
	}


	public String getPartieIntersseDesignation() {
		return partieIntersseDesignation;
	}


	public void setPartieIntersseDesignation(String partieIntersseDesignation) {
		this.partieIntersseDesignation = partieIntersseDesignation;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getProduitId() {
		return produitId;
	}


	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}


	public Long getFournisseurId() {
		return fournisseurId;
	}


	public void setFournisseurId(Long fournisseurId) {
		this.fournisseurId = fournisseurId;
	}


	public Long getClientId() {
		return clientId;
	}


	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	public Calendar getDateAchat() {
		return dateAchat;
	}


	public void setDateAchat(Calendar dateAchat) {
		this.dateAchat = dateAchat;
	}


	public Calendar getDateVente() {
		return dateVente;
	}


	public void setDateVente(Calendar dateVente) {
		this.dateVente = dateVente;
	}


	public String getNumBonReception() {
		return numBonReception;
	}


	public void setNumBonReception(String numBonReception) {
		this.numBonReception = numBonReception;
	}


	public String getNumBonLivraison() {
		return numBonLivraison;
	}


	public void setNumBonLivraison(String numBonLivraison) {
		this.numBonLivraison = numBonLivraison;
	}


	public Double getPrixAchat() {
		return prixAchat;
	}


	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}


	public Double getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(Double prixVente) {
		this.prixVente = prixVente;
	}


	public Calendar getDateFinGarantie() {
		return dateFinGarantie;
	}


	public void setDateFinGarantie(Calendar dateFinGarantie) {
		this.dateFinGarantie = dateFinGarantie;
	}


	@Override
	public int compareTo(ProduitSerialisableValue o) {
		ProduitSerialisableValue element = (ProduitSerialisableValue) o;
		return (element.getId().compareTo(id));
	}
	
	
	
	
}