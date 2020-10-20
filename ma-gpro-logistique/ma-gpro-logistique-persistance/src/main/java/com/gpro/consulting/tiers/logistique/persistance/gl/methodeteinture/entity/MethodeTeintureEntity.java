package com.gpro.consulting.tiers.logistique.persistance.gl.methodeteinture.entity;

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
 * @author Zeineb Medimagh
 * @since 03/10/2016
 *
 */

@Entity
@Table(name=IConstanteLogistique.TABLE_METHODE_TEINTURE)
public class MethodeTeintureEntity implements Serializable {
	
	private static final long serialVersionUID = 669811434041093587L;
	
	@Id
	@SequenceGenerator(name="METHODE_TEINTURE_ID_GENERATOR", sequenceName=IConstanteLogistique.SEQUENCE_GL_METHODE_TEINTURE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="METHODE_TEINTURE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "bl_suppression")
	private boolean blSuppression;
	
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "date_modification")
	private Calendar dateModification;
	
	@Column(name = "version")
	private String version;
	
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
