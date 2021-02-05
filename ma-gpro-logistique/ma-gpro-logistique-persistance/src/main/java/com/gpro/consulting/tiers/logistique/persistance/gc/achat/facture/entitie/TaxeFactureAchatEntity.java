package com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.entitie;

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
 * @since 29/02/2016
 *
 */
@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_TAXEFACTUREACHAT)
public class TaxeFactureAchatEntity implements Serializable {

	private static final long serialVersionUID = -6594954595337101125L;

	@Id
	@SequenceGenerator(name = "TAXEFACTURE_ACHAT_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_CTFA_SEQ, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAXEFACTURE_ACHAT_ID_GENERATOR")
	private Long id;

	@Column(name = "POURCENTAGE")
	private Double pourcentage;

	@Column(name = "MONTANT")
	private Double montant;

	/** many-to-one association to {@link FactureAchatEntity}. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GC_FACTACHAT_ID")
	private FactureAchatEntity factureAchat;

	@Column(name = "GC_TAXE_ID")
	private Long taxeId;

	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression;

	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

	@Column(name = "VERSION")
	private String version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
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

	public FactureAchatEntity getFactureAchat() {
		return factureAchat;
	}

	public void setFactureAchat(FactureAchatEntity factureAchat) {
		this.factureAchat = factureAchat;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

}
