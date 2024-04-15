package com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
 * Classe représentant categorie Partie Interessée de l'application.
 * 
 * @author $mohamed: $ @
 */
@Entity
@Table(name = IConstante.TABLE_GROUPE_CLIENT)
public class GroupeClientEntite implements Serializable {

	/** The Constant serialVersionUID. */


	/**
	 * 
	 */
	private static final long serialVersionUID = 6234540802776384133L;

	/** Identifiant technique. */
	/** L'id de la table. */
	@Id
	@SequenceGenerator(name = "GROUPE_CLIENT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GROUPE_CLIENT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUPE_CLIENT_ID_GENERATOR")
	private Long id;

	/** The designation. */
	@Column(name = "designation")
	private String designation;

	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;
	
	@Column(name = "pi_famillepi_id")
	private Long famillePartieInteressee;
	
	
	@Column(name = "code_entreprise")
	private String codeEntreprise;
	
	
	

	public String getCodeEntreprise() {
		return codeEntreprise;
	}

	public void setCodeEntreprise(String codeEntreprise) {
		this.codeEntreprise = codeEntreprise;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getFamillePartieInteressee() {
		return famillePartieInteressee;
	}

	public void setFamillePartieInteressee(Long famillePartieInteressee) {
		this.famillePartieInteressee = famillePartieInteressee;
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
	 * @param id
	 *            the new id
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
	 * @param designation
	 *            the new designation
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
	 * @param blSuppression
	 *            the new bl suppression
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
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
	 * @param dateSuppression
	 *            the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
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
	 * @param dateCreation
	 *            the new date creation
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
	 * @param dateModification
	 *            the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
