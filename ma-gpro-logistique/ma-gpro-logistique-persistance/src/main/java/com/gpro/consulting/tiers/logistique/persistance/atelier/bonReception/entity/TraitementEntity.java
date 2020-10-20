/**
 * 
 */
package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * @author Ameni
 *
 */
@Entity
@Table(name = IConstanteLogistique.TABLE_TRAITEMENT)
public class TraitementEntity implements Serializable {
	private static final long serialVersionUID = -3904803468954519377L;

	@Id
	@SequenceGenerator(name = "TRAITEMENT_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_TRAITEMENT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAITEMENT_ID_GENERATOR")
	private Long id;

	/** The designation. */
	@Column(name = "designation")
	private String designation;

	/** The observations. */
	@Column(name = "observations")
	private String observations;
	
	/** The facture. */
	@Column(name = "facture")
	private boolean facture;

	/** The suppression. */
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

	/** Version */
	@Column(name = "version")
	private String version;
	
	/** PU: Prix Unitaire */
	@Column(name = "pu")
	private Double pu;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the facture
	 */
	public boolean isFacture() {
		return facture;
	}

	/**
	 * @param facture the facture to set
	 */
	public void setFacture(boolean facture) {
		this.facture = facture;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public Double getPU() {
		return pu;
	}

	public void setPU(Double pu) {
		this.pu = pu;
	}
	
}
