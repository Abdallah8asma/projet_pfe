package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */

@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_MARCHE)
public class MarcheEntity implements Serializable {
	
	private static final long serialVersionUID = 8782872646491735499L;
	
	@Id
	@SequenceGenerator(name="MARCHE_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_CMA_SEQ, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MARCHE_ID_GENERATOR")
    private Long id;
	
	@Column(name="DESIGNATION")
	private String designation;
	
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name="DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;
																							
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="ID_CLIENT")
	private Long partieInteresseId;

	
	
	
	public Long getPartieInteresseId() {
		return partieInteresseId;
	}

	public void setPartieInteresseId(Long partieInteresseId) {
		this.partieInteresseId = partieInteresseId;
	}

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

	public Boolean isBlSuppression() {
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


}
