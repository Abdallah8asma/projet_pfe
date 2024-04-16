/**
 * 
 */
package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;




/**
 * Un element de resultat de recherche
 * 
 * @author rkhaskho
 *
 */
public class ElementResultatRechecheRouleauStandardValue {


	/** Réf Produit */
	private String referenceProduit;

	/** Désignation */
	private String designation;

	/** Métrage */
	private Double metrage;

	/** Poids */
	private Double poids;

	/** Nombre de colis */
	private Long nombreColis;

	/** Prix unitaire */
	private Double prixUnitaire;

	/** Prix total */
	private Double prixTotal;

	/** Produit */
	private Long idProduit;
	
	/** Nombre des mises */
	private Long nombreMise;
	private String referenceMise;


	/**
	 * @return the referenceProduit
	 */
	public String getReferenceProduit() {
		return referenceProduit;
	}

	/**
	 * @param referenceProduit
	 *            the referenceProduit to set
	 */
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
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
	 * @return the nombreColis
	 */
	public Long getNombreColis() {
		return nombreColis;
	}

	/**
	 * @param nombreColis
	 *            the nombreColis to set
	 */
	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	/**
	 * @return the prixUnitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire
	 *            the prixUnitaire to set
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal
	 *            the prixTotal to set
	 */
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	/**
	 * @return the idProduit
	 */
	public Long getIdProduit() {
		return idProduit;
	}

	/**
	 * @param idProduit
	 *            the idProduit to set
	 */
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	
	public Long getNombreMise() {
		return nombreMise;
	}

	public void setNombreMise(Long nombreMise) {
		this.nombreMise = nombreMise;
	}
	
	
	public String getReferenceMise() {
		return referenceMise;
	}

	public void setReferenceMise(String referenceMise) {
		this.referenceMise = referenceMise;
	}

}

