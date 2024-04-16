package com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value;

import java.util.Calendar;

/**
 * @author Hamdi ETTEIEB
 * @since 11/09/2018
 *
 */
public class RechercheMulticritereBonCommandeValue {

	/** The ref. */
	private String reference;

	/** partie Interessee Id **/
	private String partieInteresseId;

	/** designation produit **/
	private Long idProduitParDesignation;

	/** reference produit **/
	private Long idProduitParRef;

	/** dateIntroduction debut */
	private Calendar dateIntroductionDu;

	/** dateIntroduction fin */
	private Calendar dateIntroductionA;

	/** dateLivraison debut */
	private Calendar dateLivraisonDu;

	/** dateLivraison fin */
	private Calendar dateLivraisonA;

	private Double quantiteDu;

	private Double quantiteA;

	private Double coutDu;

	private Double coutA;

	private String type;

	private String livre;

	private Long boutiqueId;
	
	
	private Long devise;
	
	

	public Long getDevise() {
		return devise;
	}

	public void setDevise(Long devise) {
		this.devise = devise;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public String getLivre() {
		return livre;
	}

	public void setLivre(String livre) {
		this.livre = livre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
