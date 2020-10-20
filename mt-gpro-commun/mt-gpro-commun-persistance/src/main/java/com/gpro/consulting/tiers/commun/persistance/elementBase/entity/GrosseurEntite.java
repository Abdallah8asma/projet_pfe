package com.gpro.consulting.tiers.commun.persistance.elementBase.entity;

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
* Classe: Grosseur
* @author $AMENI
* 
*/
@Entity
@Table(name = IConstante.TABLE_EB_GROSSEUR)
public class GrosseurEntite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7337368850482167754L;

	@Id
	@SequenceGenerator(name="GROSSEUR_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_GROSSEUR,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="GROSSEUR_ID_GENERATOR")
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

	
	/************* Getters & Setters *****************/
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

	/************* Equals & ToString *****************/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}	
	
}
