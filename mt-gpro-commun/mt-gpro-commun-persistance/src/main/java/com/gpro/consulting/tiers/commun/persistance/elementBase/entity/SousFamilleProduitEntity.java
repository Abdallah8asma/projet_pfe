package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

import java.util.Calendar;


/**
 * The Class SousFamilleProduitEntity.
 * @author med
 */
@Entity
@Table(name=IConstante.TABLE_SOUS_FAMILLE_PRODUIT)
public class SousFamilleProduitEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8967447133990155822L;

	/** The id. */
	@Id
	@SequenceGenerator(name="SOUS-FAMILLE-PROD_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_SOUS_FAMILLE_PRODUIT)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOUS-FAMILLE-PROD_ID_GENERATOR")
	private Long id;

	/** The designation. */
	@Column(name="designation")
	private String designation;

	/** The famille produit id. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="eb_familleprod_id")
	private FamilleProduitEntity familleProduit;

	/** The bl supppression. */
	@Column(name="bl_suppression")
	private boolean blSupppression;

	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Temporal(TemporalType.DATE)
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	
	
	/** The tva. */
	@Column(name = "tva")
	private Double tva;
	
	 
	@Column(name="id_taxe")
	 private Long idTaxe ;
	
	/** The serialisable. */
	@Column(name = "serialisable")
	private boolean serialisable;
	
	
	

	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public Double getTva() {
		return tva;
	}

	public void setTva(Double tva) {
		this.tva = tva;
	}

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
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	

	public FamilleProduitEntity getFamilleProduit() {
		return familleProduit;
	}

	public void setFamilleProduit(FamilleProduitEntity familleProduit) {
		this.familleProduit = familleProduit;
	}

	/**
	 * Checks if is bl supppression.
	 *
	 * @return true, if is bl supppression
	 */
	public boolean isBlSupppression() {
		return blSupppression;
	}

	/**
	 * Sets the bl supppression.
	 *
	 * @param blSupppression the new bl supppression
	 */
	public void setBlSupppression(boolean blSupppression) {
		this.blSupppression = blSupppression;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}