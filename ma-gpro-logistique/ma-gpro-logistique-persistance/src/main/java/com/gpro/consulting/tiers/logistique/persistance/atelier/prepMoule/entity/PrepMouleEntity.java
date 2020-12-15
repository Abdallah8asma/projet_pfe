package com.gpro.consulting.tiers.logistique.persistance.atelier.prepMoule.entity;

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


@Entity
@Table(name = IConstanteLogistique.TABLE_PREP_MOULE)
public class PrepMouleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8526976040416582590L;
 
	
	/** The id. */
	@Id
	@SequenceGenerator(name = "PREP_MOULE_ID_GENERATOR", sequenceName = IConstanteLogistique.SEQUENCE_PREP_MOULE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PREP_MOULE_ID_GENERATOR")
	private Long id;

	/** The mole id. */
	@Column(name = "id_moule")
	private Long idMoule;
	
	/** The machine. */
	@Column(name = "machine")
	private String machine;
	
	/** The designation. */
	@Column(name = "designation")
	private String designation;
	
	/** The reference. */
	@Column(name = "reference")
	private String reference;
	
	/** The emplacement. */
	@Column(name = "emplacement")
	private String emplacement;
	
	/** The date of preparation. */
	@Column(name = "date_prep")
	private Calendar datePreparation;

	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private Boolean blSuppression;

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

	public Long getIdMoule() {
		return idMoule;
	}

	public void setIdMoule(Long idMoule) {
		this.idMoule = idMoule;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Calendar getDatePreparation() {
		return datePreparation;
	}

	public void setDatePreparation(Calendar datePreparation) {
		this.datePreparation = datePreparation;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PrepMouleEntity [id=" + id + ", idMoule=" + idMoule + ", machine=" + machine + ", designation="
				+ designation + ", reference=" + reference + ", emplacement=" + emplacement + ", datePreparation="
				+ datePreparation + ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression
				+ ", dateCreation=" + dateCreation + ", dateModification=" + dateModification + "]";
	}
	
}
