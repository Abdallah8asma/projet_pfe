package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public class RechercheMulticritereReglementAchatValue {

	private Long partieIntId;
	private String reference;
	private Calendar dateReglementMin;
	private Calendar dateReglementMax;
	private Double montantMin;
	private Double montantMax;
	private Long idDepot;

	private Long groupeClientId;

	// critaire recherche element reglement

	private String refFacture;
	private String refBL;

	// critaire recherche details reglement

	private String numPiece;

	private String banque;

	private Double montant;

	private Calendar dateEmission;

	private Calendar dateEcheance;

	private Long boutiqueId;
	
	
	
	
	private String referenceDetailReglement;
	
	
	
	private String declarerRech;
	
	private String hasElementReglement;
	
	private String hasDetailReglement;
	
	
	
	

	public String getHasElementReglement() {
		return hasElementReglement;
	}

	public void setHasElementReglement(String hasElementReglement) {
		this.hasElementReglement = hasElementReglement;
	}

	public String getHasDetailReglement() {
		return hasDetailReglement;
	}

	public void setHasDetailReglement(String hasDetailReglement) {
		this.hasDetailReglement = hasDetailReglement;
	}

	public String getDeclarerRech() {
		return declarerRech;
	}

	public void setDeclarerRech(String declarerRech) {
		this.declarerRech = declarerRech;
	}

	public String getReferenceDetailReglement() {
		return referenceDetailReglement;
	}

	public void setReferenceDetailReglement(String referenceDetailReglement) {
		this.referenceDetailReglement = referenceDetailReglement;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Calendar getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Calendar dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getRefBL() {
		return refBL;
	}

	public void setRefBL(String refBL) {
		this.refBL = refBL;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
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

	public Calendar getDateReglementMin() {
		return dateReglementMin;
	}

	public void setDateReglementMin(Calendar dateReglementMin) {
		this.dateReglementMin = dateReglementMin;
	}

	public Calendar getDateReglementMax() {
		return dateReglementMax;
	}

	public void setDateReglementMax(Calendar dateReglementMax) {
		this.dateReglementMax = dateReglementMax;
	}

	public Double getMontantMin() {
		return montantMin;
	}

	public void setMontantMin(Double montantMin) {
		this.montantMin = montantMin;
	}

	public Double getMontantMax() {
		return montantMax;
	}

	public void setMontantMax(Double montantMax) {
		this.montantMax = montantMax;
	}

}
