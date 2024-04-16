package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Calendar;

public class RechercheMulticritereDetLivraisonValue {

	private Long partieIntId;
	private Long idProduit;
	private Long idDepot;

	private Calendar dateDe;

	private Calendar dateA;

	private Double prixDe;

	private Double prixA;

	private Double qteDe;

	private Double qteA;

	private Calendar dateStrA;

	private boolean isOptimized;

	private String numTelPassager;
	private String emailPassager;
	private String adressePassager;
	private String transporteur;

	private Long boutiqueId;


	private String numeroProduit;

	private String referenceFournisseurProduit;
	private String numOF;
	
	

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}

	public String getNumeroProduit() {
		return numeroProduit;
	}

	public void setNumeroProduit(String numeroProduit) {
		this.numeroProduit = numeroProduit;
	}

	public String getReferenceFournisseurProduit() {
		return referenceFournisseurProduit;
	}

	public void setReferenceFournisseurProduit(String referenceFournisseurProduit) {
		this.referenceFournisseurProduit = referenceFournisseurProduit;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getNumTelPassager() {
		return numTelPassager;
	}

	public void setNumTelPassager(String numTelPassager) {
		this.numTelPassager = numTelPassager;
	}

	public String getEmailPassager() {
		return emailPassager;
	}

	public void setEmailPassager(String emailPassager) {
		this.emailPassager = emailPassager;
	}

	public String getAdressePassager() {
		return adressePassager;
	}

	public void setAdressePassager(String adressePassager) {
		this.adressePassager = adressePassager;
	}

	public String getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}

	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}

	public Calendar getDateStrA() {
		return dateStrA;
	}

	public void setDateStrA(Calendar dateStrA) {
		this.dateStrA = dateStrA;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
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

	public Double getPrixDe() {
		return prixDe;
	}

	public void setPrixDe(Double prixDe) {
		this.prixDe = prixDe;
	}

	public Double getPrixA() {
		return prixA;
	}

	public void setPrixA(Double prixA) {
		this.prixA = prixA;
	}

	public Double getQteDe() {
		return qteDe;
	}

	public void setQteDe(Double qteDe) {
		this.qteDe = qteDe;
	}

	public Double getQteA() {
		return qteA;
	}

	public void setQteA(Double qteA) {
		this.qteA = qteA;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

}
