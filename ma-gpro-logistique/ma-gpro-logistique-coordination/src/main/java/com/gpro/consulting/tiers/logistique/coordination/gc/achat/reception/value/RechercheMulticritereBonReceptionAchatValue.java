package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value;

import java.util.Calendar;

/**
 * The Class RechercheMulticritereBonReceptionAchatValue.
 *
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */
public class RechercheMulticritereBonReceptionAchatValue {

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

	/** is has facture. */
	private String facture;

	private String type;

	/**
	 * refAvoirRetour est la ref facture vente Avoir Retour utilise pour stock
	 * retour.
	 */

	private String refAvoirRetour;
	
	
	private Long boutiqueId;
	
	
	private String declare;
	
	

	public String getDeclare() {
		return declare;
	}

	public void setDeclare(String declare) {
		this.declare = declare;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefAvoirRetour() {
		return refAvoirRetour;
	}

	public void setRefAvoirRetour(String refAvoirRetour) {
		this.refAvoirRetour = refAvoirRetour;
	}

	public String getFacture() {
		return facture;
	}

	public void setFacture(String facture) {
		this.facture = facture;
	}

	/**
	 * Gets the refexterne.
	 *
	 * @return the refexterne
	 */
	public String getRefexterne() {
		return refexterne;
	}

	/**
	 * Sets the refexterne.
	 *
	 * @param refexterne the new refexterne
	 */
	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	/**
	 * Gets the id depot.
	 *
	 * @return the id depot
	 */
	public Long getIdDepot() {
		return idDepot;
	}

	/**
	 * Sets the id depot.
	 *
	 * @param idDepot the new id depot
	 */
	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the partie interesse id.
	 *
	 * @return the partie interesse id
	 */
	public String getPartieInteresseId() {
		return partieInteresseId;
	}

	/**
	 * Sets the partie interesse id.
	 *
	 * @param partieInteresseId the new partie interesse id
	 */
	public void setPartieInteresseId(String partieInteresseId) {
		this.partieInteresseId = partieInteresseId;
	}

	/**
	 * Gets the id produit par designation.
	 *
	 * @return the id produit par designation
	 */
	public Long getIdProduitParDesignation() {
		return idProduitParDesignation;
	}

	/**
	 * Sets the id produit par designation.
	 *
	 * @param idProduitParDesignation the new id produit par designation
	 */
	public void setIdProduitParDesignation(Long idProduitParDesignation) {
		this.idProduitParDesignation = idProduitParDesignation;
	}

	/**
	 * Gets the id produit par ref.
	 *
	 * @return the id produit par ref
	 */
	public Long getIdProduitParRef() {
		return idProduitParRef;
	}

	/**
	 * Sets the id produit par ref.
	 *
	 * @param idProduitParRef the new id produit par ref
	 */
	public void setIdProduitParRef(Long idProduitParRef) {
		this.idProduitParRef = idProduitParRef;
	}

	/**
	 * Gets the date introduction du.
	 *
	 * @return the date introduction du
	 */
	public Calendar getDateIntroductionDu() {
		return dateIntroductionDu;
	}

	/**
	 * Sets the date introduction du.
	 *
	 * @param dateIntroductionDu the new date introduction du
	 */
	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}

	/**
	 * Gets the date introduction A.
	 *
	 * @return the date introduction A
	 */
	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	/**
	 * Sets the date introduction A.
	 *
	 * @param dateIntroductionA the new date introduction A
	 */
	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

	/**
	 * Gets the date livraison du.
	 *
	 * @return the date livraison du
	 */
	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}

	/**
	 * Sets the date livraison du.
	 *
	 * @param dateLivraisonDu the new date livraison du
	 */
	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}

	/**
	 * Gets the date livraison A.
	 *
	 * @return the date livraison A
	 */
	public Calendar getDateLivraisonA() {
		return dateLivraisonA;
	}

	/**
	 * Sets the date livraison A.
	 *
	 * @param dateLivraisonA the new date livraison A
	 */
	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}

	/**
	 * Gets the quantite du.
	 *
	 * @return the quantite du
	 */
	public Double getQuantiteDu() {
		return quantiteDu;
	}

	/**
	 * Sets the quantite du.
	 *
	 * @param quantiteDu the new quantite du
	 */
	public void setQuantiteDu(Double quantiteDu) {
		this.quantiteDu = quantiteDu;
	}

	/**
	 * Gets the quantite A.
	 *
	 * @return the quantite A
	 */
	public Double getQuantiteA() {
		return quantiteA;
	}

	/**
	 * Sets the quantite A.
	 *
	 * @param quantiteA the new quantite A
	 */
	public void setQuantiteA(Double quantiteA) {
		this.quantiteA = quantiteA;
	}

	/**
	 * Gets the cout du.
	 *
	 * @return the cout du
	 */
	public Double getCoutDu() {
		return coutDu;
	}

	/**
	 * Sets the cout du.
	 *
	 * @param coutDu the new cout du
	 */
	public void setCoutDu(Double coutDu) {
		this.coutDu = coutDu;
	}

	/**
	 * Gets the cout A.
	 *
	 * @return the cout A
	 */
	public Double getCoutA() {
		return coutA;
	}

	/**
	 * Sets the cout A.
	 *
	 * @param coutA the new cout A
	 */
	public void setCoutA(Double coutA) {
		this.coutA = coutA;
	}

}
