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
 * @since 27/01/2016
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_TAXE)
public class TaxeEntity implements Serializable{

	private static final long serialVersionUID = 5276612588572395613L;

	@Id
	@SequenceGenerator(name="TAXE_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_CTA_SEQ, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAXE_ID_GENERATOR")
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
	
	@Column(name="TVA")
	private Boolean tva;
	@Column(name="valeur")
	private Double valeur ;
	
	
	

	public Boolean getTva() {
		return tva;
	}

	public void setTva(Boolean tva) {
		this.tva = tva;
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

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}
	
}
