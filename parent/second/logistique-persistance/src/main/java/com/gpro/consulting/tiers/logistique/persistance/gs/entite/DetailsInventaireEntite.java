package com.gpro.consulting.tiers.logistique.persistance.gs.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;


// TODO: Auto-generated Javadoc
/**@author mohamed
 * 
 */
@Entity
@Table(name=IConstante.TABLE_DETAIS_INVENTAIRE)
public class DetailsInventaireEntite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4405944776029075521L;

	/** The id. */
	@Id
	@SequenceGenerator(name="GS_DETAILSINVENTAIRE_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_DETAIS_INVENTAIRE)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GS_DETAILSINVENTAIRE_ID_GENERATOR")
	private Long id;

	/** The cones actuel. */
	@Column(name="cones_actuel")
	private Long conesActuel;
	
	/** The fincone actuel. */
	@Column(name="fincone_actuel")
	private Long finconeActuel;

	/** The poids actuel. */
	@Column(name="poids_actuel")
	private Double poidsActuel;

	/** The qte actuelle. */
	@Column(name="qte_actuelle")
	private Double qteActuelle;

	/** The rouleaux actuel. */
	@Column(name="rouleaux_actuel")
	private Long rouleauxActuel;
	
	//bi-directional many-to-one association to GsEntitestock
	/** The entite stock. */
	@ManyToOne
	@JoinColumn(name="gs_entitestock_id")
	private EntiteStockEntite entiteStock;

	//bi-directional many-to-one association to GsInventaire
	/** The inventaire. */
	@ManyToOne
	@JoinColumn(name="gs_inventaire_id")
	private InventaireEntite inventaire;
	
	/** The bl suppression. */
	@Column(name="bl_suppression")
	private Boolean blSuppression;
	
	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;

	
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
	 * Gets the cones actuel.
	 *
	 * @return the cones actuel
	 */
	public Long getConesActuel() {
		return conesActuel;
	}

	/**
	 * Sets the cones actuel.
	 *
	 * @param conesActuel the new cones actuel
	 */
	public void setConesActuel(Long conesActuel) {
		this.conesActuel = conesActuel;
	}

	/**
	 * Gets the fincone actuel.
	 *
	 * @return the fincone actuel
	 */
	public Long getFinconeActuel() {
		return finconeActuel;
	}

	/**
	 * Sets the fincone actuel.
	 *
	 * @param finconeActuel the new fincone actuel
	 */
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
	 * Gets the bl suppression.
	 *
	 * @return the bl suppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	
	/**
	 * Gets the inventaire.
	 *
	 * @return the inventaire
	 */
	public InventaireEntite getInventaire() {
		return inventaire;
	}

	/**
	 * Sets the inventaire.
	 *
	 * @param inventaire the new inventaire
	 */
	public void setInventaire(InventaireEntite inventaire) {
		this.inventaire = inventaire;
	}

	public EntiteStockEntite getEntiteStock() {
		return entiteStock;
	}

	public void setEntiteStock(EntiteStockEntite entiteStock) {
		this.entiteStock = entiteStock;
	}



}