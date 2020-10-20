package com.gpro.consulting.tiers.logistique.persistance.gs.transfert.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;

/**
 * @author Samer Hassen
 * @since 04/09/20
 *
 */
@Entity
@Table(name = IConstante.TABLE_BON_TRANSFERT)
public class BonTransfertEntity implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 7246934583098501228L;

	@Id
	@SequenceGenerator(name = "BON_TRANSFERT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_BON_TRANSFERT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BON_TRANSFERT_ID_GENERATOR")
	private Long id;

	@Column(name = "REFERENCE")
	private String reference;

	@Column(name = "DATE")
	private Calendar date;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@Column(name = "METRAGE_TOTAL")
	private Double metrageTotal;

	@OneToMany(mappedBy = "bonTransfert", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DetBonTransfertEntity> listDetBonTransfert;

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

	@Column(name = "ID_DEPOT_ORIGINE")
	private Long idDepotOrigine;
	
	@Column(name = "ID_DEPOT_DESTINATION")
	private Long idDepotDestination;

	@Column(name = "type")
	private String type;
	
	@Column(name = "boutique_id")
	private Long boutiqueId;
	

	@Column(name = "REFERENCE_SORTIE")
	private String referenceSortie;
	
	@Column(name = "REFERENCE_RECEPTION")
	private String referenceReception;
	
	@Column(name = "status")
	private String status;
	
	
	@Column(name = "status_auto")
	private String statusAuto;
	
	
	
	
	
	

	public String getStatusAuto() {
		return statusAuto;
	}

	public void setStatusAuto(String statusAuto) {
		this.statusAuto = statusAuto;
	}

	public String getReferenceReception() {
		return referenceReception;
	}

	public void setReferenceReception(String referenceReception) {
		this.referenceReception = referenceReception;
	}

	public String getReferenceSortie() {
		return referenceSortie;
	}

	public void setReferenceSortie(String referenceSortie) {
		this.referenceSortie = referenceSortie;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Double getMetrageTotal() {
		return metrageTotal;
	}

	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
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



	public Set<DetBonTransfertEntity> getListDetBonTransfert() {
		return listDetBonTransfert;
	}

	public void setListDetBonTransfert(Set<DetBonTransfertEntity> listDetBonTransfert) {
		this.listDetBonTransfert = listDetBonTransfert;
	}



	public Long getIdDepotOrigine() {
		return idDepotOrigine;
	}

	public void setIdDepotOrigine(Long idDepotOrigine) {
		this.idDepotOrigine = idDepotOrigine;
	}

	public Long getIdDepotDestination() {
		return idDepotDestination;
	}

	public void setIdDepotDestination(Long idDepotDestination) {
		this.idDepotDestination = idDepotDestination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
