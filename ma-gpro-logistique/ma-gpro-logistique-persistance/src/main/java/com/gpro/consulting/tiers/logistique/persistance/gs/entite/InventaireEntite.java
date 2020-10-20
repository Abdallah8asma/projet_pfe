package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;
/**
 * 
 * 
 * @author mohamed
 *
 */

@Entity
@Table(name=IConstante.TABLE_INVENTAIRE)
public class InventaireEntite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8625000633569665694L;

	/** The id. */
	@Id
	@SequenceGenerator(name="GS_INVENTAIRE_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_INVENTAIRE)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GS_INVENTAIRE_ID_GENERATOR")
	private Long id;

	/** The date invetaire. */
	@Column(name="date_invetaire")
	private Calendar dateInvetaire;

	/** The description. */
	@Column(name="description")
	private String description;

	/** The valide. */
	@Column(name="valide")
	private Boolean valide;

	//bi-directional many-to-one association to GsDetailsinventaire
	/** The details inventaires. */
	@OneToMany(mappedBy="inventaire")
	private Set<DetailsInventaireEntite> detailsInventaires;

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
	 * Gets the date invetaire.
	 *
	 * @return the date invetaire
	 */
	public Calendar getDateInvetaire() {
		return dateInvetaire;
	}

	/**
	 * Sets the date invetaire.
	 *
	 * @param dateInvetaire the new date invetaire
	 */
	public void setDateInvetaire(Calendar dateInvetaire) {
		this.dateInvetaire = dateInvetaire;
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
	public Set<DetailsInventaireEntite> getDetailsInventaires() {
		return detailsInventaires;
	}

	/**
	 * Sets the details inventaires.
	 *
	 * @param detailsInventaires the new details inventaires
	 */
	public void setDetailsInventaires(
			Set<DetailsInventaireEntite> detailsInventaires) {
		this.detailsInventaires = detailsInventaires;
	}

	

}