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
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name = IConstante.TABLE_REGION_PARTIE_INTERESSEE)
public class RegionEntite implements Serializable {

	private static final long serialVersionUID = -5132646901087391962L;

	/** Identifiant technique. */
	/** L'id de la table. */
	@Id
	@SequenceGenerator(name = "REGION_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_REGION_PARTIE_INTERESSEE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGION_ID_GENERATOR")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	
	
}
