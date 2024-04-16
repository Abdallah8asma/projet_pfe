package com.gpro.consulting.tiers.logistique.coordination.gs.value;

/**
 * The Class DetailsInventaireValue.
 */
public class DetailsInventaireValue  {
	
	/** The id. */
	private Long id;

	/** The cones actuel. */
	private Long conesActuel;

	/** The fincone actuel. */
	private Long finconeActuel;

	/** The poids actuel. */
	private Double poidsActuel;

	/** The qte actuelle. */
	private Double qteActuelle;

	/** The rouleaux actuel. */
	private Long rouleauxActuel;

	//bi-directional many-to-one association to GsEntitestock
	/** The entite stock. */
	private Long entiteStock;

	//bi-directional many-to-one association to GsInventaire
	/** The inventaire. */
	private InventaireValue inventaire;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getConesActuel() {
		return conesActuel;
	}

	public void setConesActuel(Long conesActuel) {
		this.conesActuel = conesActuel;
	}

	public Long getFinconeActuel() {
		return finconeActuel;
	}

	public void setFinconeActuel(Long finconeActuel) {
		this.finconeActuel = finconeActuel;
	}

	/**
	 * Gets the poids actuel.
	 *
	 * @return the poids actuel
	 */
	public Double getPoidsActuel() {
		return poidsActuel;
	}

	/**
	 * Sets the poids actuel.
	 *
	 * @param poidsActuel the new poids actuel
	 */
	public void setPoidsActuel(Double poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

	/**
	 * Gets the qte actuelle.
	 *
	 * @return the qte actuelle
	 */
	public Double getQteActuelle() {
		return qteActuelle;
	}

	/**
	 * Sets the qte actuelle.
	 *
	 * @param qteActuelle the new qte actuelle
	 */
	public void setQteActuelle(Double qteActuelle) {
		this.qteActuelle = qteActuelle;
	}

	/**
	 * Gets the rouleaux actuel.
	 *
	 * @return the rouleaux actuel
	 */
	public Long getRouleauxActuel() {
		return rouleauxActuel;
	}

	/**
	 * Sets the rouleaux actuel.
	 *
	 * @param rouleauxActuel the new rouleaux actuel
	 */
	public void setRouleauxActuel(Long rouleauxActuel) {
		this.rouleauxActuel = rouleauxActuel;
	}



	/**
	 * Gets the inventaire.
	 *
	 * @return the inventaire
	 */
	public InventaireValue getInventaire() {
		return inventaire;
	}

	/**
	 * Sets the inventaire.
	 *
	 * @param inventaire the new inventaire
	 */
	public void setInventaire(InventaireValue inventaire) {
		this.inventaire = inventaire;
	}

	public Long getEntiteStock() {
		return entiteStock;
	}

	public void setEntiteStock(Long entiteStock) {
		this.entiteStock = entiteStock;
	}

	
	
}
