package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.Calendar;

public class RechercheMulticritereProduitReceptionAchatValue {

	private Long produitId;

	/** The ref. */
	private String reference;

	/** The ref. Externe */
	private String refexterne;

	/** partie Interessee Id *. */
	private String partieInteresseId;

	/** designation produit *. */
	private Long idProduitParDesignation;

	/** reference produit *. */
	private Long idProduitParRef;

	/** dateIntroduction debut. */
	private Calendar dateIntroductionDu;

	/** dateIntroduction fin. */
	private Calendar dateIntroductionA;

	/** dateIntroduction fin. */
	private Calendar dateIntroductionStrA;

	/** dateLivraison debut. */
	private Calendar dateLivraisonDu;

	/** dateLivraison fin. */
	private Calendar dateLivraisonA;

	/** The quantite du. */
	private Double quantiteDu;

	/** The quantite A. */
	private Double quantiteA;

	/** The cout du. */
	private Double coutDu;

	/** The cout A. */
	private Double coutA;

	/** The id depot. */
	private Long idDepot;

	private String type;

	private String boutiqueId;

	private String numeroProduit;

	private String referenceFournisseurProduit;

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

	public String getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(String boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getDateIntroductionStrA() {
		return dateIntroductionStrA;
	}

	public void setDateIntroductionStrA(Calendar dateIntroductionStrA) {
		this.dateIntroductionStrA = dateIntroductionStrA;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	public String getPartieInteresseId() {
		return partieInteresseId;
	}

	public void setPartieInteresseId(String partieInteresseId) {
		this.partieInteresseId = partieInteresseId;
	}

	public Long getIdProduitParDesignation() {
		return idProduitParDesignation;
	}

	public void setIdProduitParDesignation(Long idProduitParDesignation) {
		this.idProduitParDesignation = idProduitParDesignation;
	}

	public Long getIdProduitParRef() {
		return idProduitParRef;
	}

	public void setIdProduitParRef(Long idProduitParRef) {
		this.idProduitParRef = idProduitParRef;
	}

	public Calendar getDateIntroductionDu() {
		return dateIntroductionDu;
	}

	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}

	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}

	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}

	public Calendar getDateLivraisonA() {
		return dateLivraisonA;
	}

	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}

	public Double getQuantiteDu() {
		return quantiteDu;
	}

	public void setQuantiteDu(Double quantiteDu) {
		this.quantiteDu = quantiteDu;
	}

	public Double getQuantiteA() {
		return quantiteA;
	}

	public void setQuantiteA(Double quantiteA) {
		this.quantiteA = quantiteA;
	}

	public Double getCoutDu() {
		return coutDu;
	}

	public void setCoutDu(Double coutDu) {
		this.coutDu = coutDu;
	}

	public Double getCoutA() {
		return coutA;
	}

	public void setCoutA(Double coutA) {
		this.coutA = coutA;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

}
