package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DocumentReglementEntity;

/**
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_REGLEMENT_ACHAT)
public class ReglementAchatEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6357531144040969106L;

	/**
	 * 
	 */
	
	@Id
	@SequenceGenerator(name="REGLEMENT_ACHAT_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_REGLEMENT_ACHAT, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGLEMENT_ACHAT_ID_GENERATOR")
    private Long id;
	
	@Column(name="PI_PARTIEINT_ID")
	private Long partieIntId;
   
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="DATE")
	private Calendar date;
	
	@Column(name="MONTANT_TOTAL")
	private Double montantTotal;
	
	@Column(name="OBSERVATIONS")
	private String observations;
	   
	@OneToMany(mappedBy = "reglement", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DetailsReglementAchatEntity> listDetailsReglement;
	
	@OneToMany(mappedBy = "reglement", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ElementReglementAchatEntity> listElementReglement;
	
	/** *** many-to-one association to DocumentFactureVente. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reglement", cascade = CascadeType.ALL,orphanRemoval=true)
	private Set<DocumentReglementAchatEntity> documentReglement;
	
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
	
	@Column(name="ID_DEPOT")
	private Long idDepot;
	
	
	@Column(name="groupe_client_id")
	private Long groupeClientId;
	
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	
	  @Column(name = "declarer")
		private boolean declarer;
	    
	  
	  
	
	

	public boolean isDeclarer() {
		return declarer;
	}

	public void setDeclarer(boolean declarer) {
		this.declarer = declarer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Set<DocumentReglementAchatEntity> getDocumentReglement() {
		return documentReglement;
	}

	public void setDocumentReglement(Set<DocumentReglementAchatEntity> documentReglement) {
		this.documentReglement = documentReglement;
	}

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Set<DetailsReglementAchatEntity> getListDetailsReglement() {
		return listDetailsReglement;
	}

	public void setListDetailsReglement(
			Set<DetailsReglementAchatEntity> listDetailsReglement) {
		this.listDetailsReglement = listDetailsReglement;
	}

	public Set<ElementReglementAchatEntity> getListElementReglement() {
		return listElementReglement;
	}

	public void setListElementReglement(
			Set<ElementReglementAchatEntity> listElementReglement) {
		this.listElementReglement = listElementReglement;
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

	public ReglementAchatEntity() {
		super();
		
	}
	public ReglementAchatEntity(Long id, String reference) {
		super();
		this.id = id;
		this.reference = reference;
	}
	
	
	
}
