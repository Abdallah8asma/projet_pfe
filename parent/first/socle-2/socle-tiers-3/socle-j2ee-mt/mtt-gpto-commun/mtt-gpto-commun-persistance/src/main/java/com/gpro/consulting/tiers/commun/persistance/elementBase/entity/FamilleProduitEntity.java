package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FamilleProduitEntity.
 * @author med
 */
@Entity
@Table(name=IConstante.TABLE_FAMILLE_PRODUIT)
public class FamilleProduitEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8876303328086824248L;

	/** The id. */
	@Id
	@SequenceGenerator(name="FAMILLEPROD_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_FAMILLE_PRODUIT)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAMILLEPROD_ID_GENERATOR")
	private Long id;

	/** The designation. */
	private String designation;

	/** The bl suppression. */
	@Column(name="bl_suppression")
	private boolean blSuppression;

	/** The date creation. */
	@Column(name="date_creation")
	private Date dateCreation;

	/** The date modification. */
	@Column(name="date_modification")
	private Date dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Date dateSuppression;
	
	
	
	/** The tva. */
	@Column(name = "tva")
	private Double tva;
	
	
	@Column(name="id_taxe")
	 private Long idTaxe ;
	
	
	/** The serialisable. */
	@Column(name = "serialisable")
	private boolean serialisable;
	
	
	
	/** The famille produit id. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="eb_superfamilleprod_id")
	private SuperFamilleProduitEntity superfamilleProduit;
	
	
	
	
	
	

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public SuperFamilleProduitEntity getSuperfamilleProduit() {
		return superfamilleProduit;
	}

	public void setSuperfamilleProduit(SuperFamilleProduitEntity superfamilleProduit) {
		this.superfamilleProduit = superfamilleProduit;
	}

	public Long getIdTaxe() {
		return idTaxe;
	}

	public void setIdTaxe(Long idTaxe) {
		this.idTaxe = idTaxe;
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

	/**
	 * Checks if is bl suppression.
	 *
	 * @return true, if is bl suppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Date getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Date getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression the new date suppression
	 */
	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}