package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class InventaireEntite.
 */
public class InventaireValue {

	
	/** The id. */
	private Long id;


	/** The description. */
	private String description;

	/** The valide. */
	private Boolean valide;

	//bi-directional many-to-one association to GsDetailsinventaire
	/** The details inventaires. */
	private Set<DetailsInventaireValue> detailsInventaires;

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

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the valide.
	 *
	 * @return the valide
	 */
	public Boolean getValide() {
		return valide;
	}

	/**
	 * Sets the valide.
	 *
	 * @param valide the new valide
	 */
	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	/**
	 * Gets the details inventaires.
	 *
	 * @return the details inventaires
	 */
	public Set<DetailsInventaireValue> getDetailsInventaires() {
		return detailsInventaires;
	}

	/**
	 * Sets the details inventaires.
	 *
	 * @param detailsInventaires the new details inventaires
	 */
	public void setDetailsInventaires(Set<DetailsInventaireValue> detailsInventaires) {
		this.detailsInventaires = detailsInventaires;
	}

	
}
