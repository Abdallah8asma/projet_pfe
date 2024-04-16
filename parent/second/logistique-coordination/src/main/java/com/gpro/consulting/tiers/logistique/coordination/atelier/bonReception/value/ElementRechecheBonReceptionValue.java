package com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value;

import java.util.Calendar;

/**
 * Classe valeur destinée pour l'IHM en cas de recheche d'un Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public class ElementRechecheBonReceptionValue implements Comparable{

	/** RefBR */
	private String referenceBR;
	
	/** idProduit : EB */
	private Long designationProduit;

	/** Designation Produit : EB */
	private String designationProduitDesignation;
	
	/** Reference Produit : EB */
	private String referenceProduit;

	/** Client :PI */
	private Long abreviationClient;

	/** Client :PI Designation */
	private String abreviationClientDesignation;

	/** Date Introduction BR */
	private Calendar dateIntroduction;
	
	/** Date Livraison BR*/
	private Calendar dateLivraison;

	/** Nombre de rouleau : BR */
	private Long nombreRouleau;

	/** Metrage annoncé */
	private Double metrageAnnonce;

	/** id Du Bon de reception */
	private Long idBonReception;

	 @Override
	  public int compareTo(Object o) {
	   ElementRechecheBonReceptionValue element= (ElementRechecheBonReceptionValue)o;
	    return (element.getIdBonReception().compareTo(idBonReception));
	  }
	 
	/**
	 * Accesseur en lecture de l'attribut <code>abreviationClient</code>.
	 * 
	 * @return Long L'attribut abreviationClient à lire.
	 */
	public Long getAbreviationClient() {
		return abreviationClient;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>abreviationClient</code>.
	 *
	 * @param abreviationClient
	 *            L'attribut abreviationClient à modifier.
	 */
	public void setAbreviationClient(Long abreviationClient) {
		this.abreviationClient = abreviationClient;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>designationProduit</code>.
	 * 
	 * @return Long L'attribut designationProduit à lire.
	 */
	public Long getDesignationProduit() {
		return designationProduit;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>designationProduit</code>.
	 *
	 * @param designationProduit
	 *            L'attribut designationProduit à modifier.
	 */
	public void setDesignationProduit(Long designationProduit) {
		this.designationProduit = designationProduit;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>dateIntroduction</code>.
	 * 
	 * @return Calendar L'attribut dateIntroduction à lire.
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>dateIntroduction</code>.
	 *
	 * @param dateIntroduction
	 *            L'attribut dateIntroduction à modifier.
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>nombreRouleau</code>.
	 * 
	 * @return Long L'attribut nombreRouleau à lire.
	 */
	public Long getNombreRouleau() {
		return nombreRouleau;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>nombreRouleau</code>.
	 *
	 * @param nombreRouleau
	 *            L'attribut nombreRouleau à modifier.
	 */
	public void setNombreRouleau(Long nombreRouleau) {
		this.nombreRouleau = nombreRouleau;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>metrageAnnonce</code>.
	 * 
	 * @return Double L'attribut metrageAnnonce à lire.
	 */
	public Double getMetrageAnnonce() {
		return metrageAnnonce;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>metrageAnnonce</code>.
	 *
	 * @param metrageAnnonce
	 *            L'attribut metrageAnnonce à modifier.
	 */
	public void setMetrageAnnonce(Double metrageAnnonce) {
		this.metrageAnnonce = metrageAnnonce;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>idBonReception</code>.
	 * 
	 * @return Long L'attribut idBonReception à lire.
	 */
	public Long getIdBonReception() {
		return idBonReception;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>idBonReception</code>.
	 *
	 * @param idBonReception
	 *            L'attribut idBonReception à modifier.
	 */
	public void setIdBonReception(Long idBonReception) {
		this.idBonReception = idBonReception;
	}

	/**
	 * Accesseur en lecture de l'attribut designationProduitDesignation.
	 * 
	 * @return the designationProduitDesignation
	 */
	public String getDesignationProduitDesignation() {
		return designationProduitDesignation;
	}

	/**
	 * @param designationProduitDesignation
	 *            the designationProduitDesignation to set
	 */
	public void setDesignationProduitDesignation(
			String designationProduitDesignation) {
		this.designationProduitDesignation = designationProduitDesignation;
	}

	/**
	 * Accesseur en lecture de l'attribut abreviationClientDesignation.
	 * 
	 * @return the abreviationClientDesignation
	 */
	public String getAbreviationClientDesignation() {
		return abreviationClientDesignation;
	}

	/**
	 * @param abreviationClientDesignation
	 *            the abreviationClientDesignation to set
	 */
	public void setAbreviationClientDesignation(
			String abreviationClientDesignation) {
		this.abreviationClientDesignation = abreviationClientDesignation;
	}

	/** Accesseur en lecture de l'attribut referenceBR.
	 * @return the referenceBR
	 */
	public String getReferenceBR() {
		return referenceBR;
	}

	/**
	 * @param referenceBR the referenceBR to set
	 */
	public void setReferenceBR(String referenceBR) {
		this.referenceBR = referenceBR;
	}

	/** Accesseur en lecture de l'attribut dateLivraison.
	 * @return the dateLivraison
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * @param dateLivraison the dateLivraison to set
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/** Accesseur en lecture de l'attribut referenceProduit.
	 * @return the referenceProduit
	 */
	public String getReferenceProduit() {
		return referenceProduit;
	}

	/**
	 * @param referenceProduit the referenceProduit to set
	 */
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	

	
}
