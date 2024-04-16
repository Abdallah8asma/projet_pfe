package com.gpro.consulting.tiers.logistique.coordination.gc.reception.value;

import java.util.Calendar;

/**
 * @author Zeineb Medimagh
 * @since 17/11/2016
 *
 */
public class RechercheMulticritereBonReceptionValue {
	
	/** The ref. */
	private String reference;
	
	public String getRefexterne() {
		return refexterne;
	}

	public void setRefexterne(String refexterne) {
		this.refexterne = refexterne;
	}

	/** The ref. Externe */
	private String refexterne;

	/** partie Interessee Id **/
	private String partieInteresseId;

	/** designation produit **/
	private Long idProduitParDesignation;
	
	/** reference produit **/
	private Long idProduitParRef;

	/** dateIntroduction debut */
	private Calendar dateIntroductionDu ;
	
	/** dateIntroduction fin */
	private Calendar dateIntroductionA;
	
	/** dateLivraison debut */
	private Calendar dateLivraisonDu ;
	
	/** dateLivraison fin */
	private Calendar dateLivraisonA;
	
	private Double quantiteDu;
	
	private Double quantiteA;
	
	private Double coutDu;
	
	private Double coutA;
	
	private Long idDepot;

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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
	
}
