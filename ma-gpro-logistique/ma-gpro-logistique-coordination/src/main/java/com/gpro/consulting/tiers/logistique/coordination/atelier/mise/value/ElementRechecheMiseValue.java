package com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value;

import java.util.Calendar;

/**
 * Classe valeur destinée pour l'IHM en cas de recheche d'un Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
/**
 * @author Ameni
 *
 */
public class ElementRechecheMiseValue implements Comparable{
	
	/** idMise */
	private Long idMise;

	/** ProduitId : EB */
	private Long designationProduit;

	/** Designation Produit */
	private String designationProduitDesignation;

	/** Reference Produit */
	private String referenceProduit;

	/** ClientId :PI */
	private Long abreviationClient;

	/** DesignationClient :PI */
	private String abreviationClientDesignation;

	/** Date Introduction ; BR */
	private Calendar dateIntroduction;

	/** poids: BR */
	private Double poids;

	/** Metrage annoncé */
	private Double metrage;

	private String reference;

	@Override
	  public int compareTo(Object o) {
		ElementRechecheMiseValue element= (ElementRechecheMiseValue)o;
	    return (element.getIdMise().compareTo(idMise));
	  }
	
	/**
	 * @return the designationProduit
	 */
	public Long getDesignationProduit() {
		return designationProduit;
	}

	/**
	 * @param designationProduit
	 *            the designationProduit to set
	 */
	public void setDesignationProduit(Long designationProduit) {
		this.designationProduit = designationProduit;
	}

	/**
	 * @return the abreviationClient
	 */
	public Long getAbreviationClient() {
		return abreviationClient;
	}

	/**
	 * @param abreviationClient
	 *            the abreviationClient to set
	 */
	public void setAbreviationClient(Long abreviationClient) {
		this.abreviationClient = abreviationClient;
	}

	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	/**
	 * @param dateIntroduction
	 *            the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	/**
	 * @return the poids
	 */
	public Double getPoids() {
		return poids;
	}

	/**
	 * @param poids
	 *            the poids to set
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}

	/**
	 * @return the metrage
	 */
	public Double getMetrage() {
		return metrage;
	}

	/**
	 * @param metrage
	 *            the metrage to set
	 */
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
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

	/** Accesseur en lecture de l'attribut abreviationClientDesignation.
	 * @return the abreviationClientDesignation
	 */
	public String getAbreviationClientDesignation() {
		return abreviationClientDesignation;
	}

	/** Accesseur en lecture de l'attribut idMise.
	 * @return the idMise
	 */
	public Long getIdMise() {
		return idMise;
	}

	/**
	 * @param idMise the idMise to set
	 */
	public void setIdMise(Long idMise) {
		this.idMise = idMise;
	}

	/**
	 * @param abreviationClientDesignation the abreviationClientDesignation to set
	 */
	public void setAbreviationClientDesignation(String abreviationClientDesignation) {
		this.abreviationClientDesignation = abreviationClientDesignation;
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
