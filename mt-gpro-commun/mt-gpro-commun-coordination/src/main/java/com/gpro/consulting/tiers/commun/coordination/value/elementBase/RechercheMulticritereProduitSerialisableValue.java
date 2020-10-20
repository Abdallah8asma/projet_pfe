package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

import com.gpro.consulting.tiers.commun.coordination.elementbase.constante.IConstanteProduitSerialisable;

public class RechercheMulticritereProduitSerialisableValue  implements IConstanteProduitSerialisable{

	
	
	private Long id;

	

	private Long produitId;

	private Long fournisseurId;
	

	private Long clientId;
	
	

	private Calendar dateAchat;
	
	private Calendar dateAchatDe;
	private Calendar dateAchatA;
	

	private Calendar dateVente;
	
	private Calendar dateVenteDe;
	private Calendar dateVenteA;


	private String numBonReception;
	

	private String numBonLivraison;
	
	private String numFacture;


	private Double prixAchat;
	

	private Double prixVente;
	
	
	private Calendar dateFinGarantie;
	
	
	private Calendar dateFinGarantieDe;
	private Calendar dateFinGarantieA;
	
	
	private String numSerie;
	
	
	private String critereSpeciale;
	
	
	private Long boutiqueId;
	
	private Long magasinId;
	
	private String historiqueBTsortie;
	
	private String historiqueBTreception;
	
	private Long partieInteressee;
	
	
	private String numero;
	
	private String referenceFournisseur;
	
	private String reference;
	private String designation ;
	
	
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getReferenceFournisseur() {
		return referenceFournisseur;
	}
	public void setReferenceFournisseur(String referenceFournisseur) {
		this.referenceFournisseur = referenceFournisseur;
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
	public Long getPartieInteressee() {
		return partieInteressee;
	}
	public void setPartieInteressee(Long partieInteressee) {
		this.partieInteressee = partieInteressee;
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
	public String getNumFacture() {
		return numFacture;
	}
	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}
	public String getCritereSpeciale() {
		return critereSpeciale;
	}
	public void setCritereSpeciale(String critereSpeciale) {
		this.critereSpeciale = critereSpeciale;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
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
	public Calendar getDateAchatDe() {
		return dateAchatDe;
	}
	public void setDateAchatDe(Calendar dateAchatDe) {
		this.dateAchatDe = dateAchatDe;
	}
	public Calendar getDateAchatA() {
		return dateAchatA;
	}
	public void setDateAchatA(Calendar dateAchatA) {
		this.dateAchatA = dateAchatA;
	}
	public Calendar getDateVente() {
		return dateVente;
	}
	public void setDateVente(Calendar dateVente) {
		this.dateVente = dateVente;
	}
	public Calendar getDateVenteDe() {
		return dateVenteDe;
	}
	public void setDateVenteDe(Calendar dateVenteDe) {
		this.dateVenteDe = dateVenteDe;
	}
	public Calendar getDateVenteA() {
		return dateVenteA;
	}
	public void setDateVenteA(Calendar dateVenteA) {
		this.dateVenteA = dateVenteA;
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
	public Calendar getDateFinGarantieDe() {
		return dateFinGarantieDe;
	}
	public void setDateFinGarantieDe(Calendar dateFinGarantieDe) {
		this.dateFinGarantieDe = dateFinGarantieDe;
	}
	public Calendar getDateFinGarantieA() {
		return dateFinGarantieA;
	}
	public void setDateFinGarantieA(Calendar dateFinGarantieA) {
		this.dateFinGarantieA = dateFinGarantieA;
	}
	
	
	

}
