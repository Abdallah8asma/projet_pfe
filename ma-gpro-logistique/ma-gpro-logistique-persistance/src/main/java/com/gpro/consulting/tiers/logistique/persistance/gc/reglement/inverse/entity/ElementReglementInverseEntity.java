package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_ELEMENTREGLEMENT_INVERSE)
public class ElementReglementInverseEntity implements Serializable{

	private static final long serialVersionUID = -790786736790464877L;

	@Id
	@SequenceGenerator(name="ELEMENTREGLEMENT_INVERSE_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_ELEMENTREGLEMENT_INVERSE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ELEMENTREGLEMENT_INVERSE_ID_GENERATOR")
    private Long id;
	
	@Column(name="MONTANT")
	private Double montant;
	
	@Column(name="REF_FACTURE")
	private String refFacture;
	
	@Column(name="REF_BL")
	private String refBL;
	
	@Column(name="MONTANT_DEMANDE")
	private Double montantDemande;
	
	@Column(name="DATE_ECHEANCE")
	private Calendar dateEcheance;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "REGLEMENT_ID")
	private ReglementInverseEntity reglement;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getRefBL() {
		return refBL;
	}

	public void setRefBL(String refBL) {
		this.refBL = refBL;
	}

	public Double getMontantDemande() {
		return montantDemande;
	}

	public void setMontantDemande(Double montantDemande) {
		this.montantDemande = montantDemande;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public ReglementInverseEntity getReglement() {
		return reglement;
	}

	public void setReglement(ReglementInverseEntity reglement) {
		this.reglement = reglement;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
