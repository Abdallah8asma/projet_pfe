package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
 * The Class ProduitEntity.
 * 
 * @author med
 */
@Entity
@Table(name = IConstante.TABLE_PRODUIT_SERIALISABLE)
public class ProduitSerialisableEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5756073865937610178L;

	@Id
	@SequenceGenerator(name = "EB_PRODUIT_SERIALISABLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_PRODUIT_SERIALISABLE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EB_PRODUIT_SERIALISABLE_ID_GENERATOR")
	private Long id;

	
	@Column(name = "num_serie")
	private String numSerie;
	
	@Column(name = "produit_id")
	private Long produitId;
	
	@Column(name = "fournisseur_id")
	private Long fournisseurId;
	
	@Column(name = "client_id")
	private Long clientId;
	
	
	@Column(name = "date_achat")
	private Calendar dateAchat;
	
	@Column(name = "date_vente")
	private Calendar dateVente;
	
	@Column(name = "date_facture")
	private Calendar dateFacture;

	@Column(name = "num_bon_reception")
	private String numBonReception;
	
	@Column(name = "num_bon_livraison")
	private String numBonLivraison;
	
	@Column(name = "num_facture")
	private String numFacture;
	

	@Column(name = "prix_achat")
	private Double prixAchat;
	
	@Column(name = "prix_vente")
	private Double prixVente;
	
	@Column(name = "date_fin_garantie")
	private Calendar dateFinGarantie;
	
	

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "date_modification")
	private Calendar dateModification;

	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "bl_suppression")
	private boolean blSuppression;
	
	
	
	
	@Column(name = "bl_ancien")
	private String blAncien;
	
	@Column(name = "br_retour")
	private String brRetour;
	
	
	@Column(name = "num_facture_avoir")
	private String numFactureAvoir;
	
	@Column(name = "facture_ancien")
	private String factureAncien;
	
	@Column(name = "facture_avoir_ancien")
	private String factureAvoirAncien;
	
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	
	@Column(name = "magasin_id")
	private Long magasinId;
	
	
	
	@Column(name = "historique_bt_sortie")
	private String historiqueBTsortie;
	
	
	@Column(name = "historique_bt_reception")
	private String historiqueBTreception;
	
	@Column(name = "magasin_designation")
	private String magasinDesignation;
	
	
	@Column(name = "historique_bs_sortie")
	private String historiqueBSsortie;
	
	
	
	

	public String getHistoriqueBSsortie() {
		return historiqueBSsortie;
	}

	public void setHistoriqueBSsortie(String historiqueBSsortie) {
		this.historiqueBSsortie = historiqueBSsortie;
	}

	public String getMagasinDesignation() {
		return magasinDesignation;
	}

	public void setMagasinDesignation(String magasinDesignation) {
		this.magasinDesignation = magasinDesignation;
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

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}



	
}