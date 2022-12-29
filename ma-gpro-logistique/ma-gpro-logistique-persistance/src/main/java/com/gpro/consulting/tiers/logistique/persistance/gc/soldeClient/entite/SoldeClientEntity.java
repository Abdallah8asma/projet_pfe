package com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.entite;

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
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_SOLDECLIENT)
public class SoldeClientEntity  implements Serializable {
	
	private static final long serialVersionUID = -8375473402012224719L;

	@Id
	@SequenceGenerator(name="SOLDECLIENT_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_SOLDECLIENT, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLDECLIENT_ID_GENERATOR")
    private Long id;
	
	@Column(name="PARTINT_ID")
	private Long partIntId;
	
	@Column(name="solde_initial")
	private Double soldeInitial;
	
	@Column(name="DATE_INIT")
	private Calendar dateIntroduction;
	
	@Column(name="SEUIL")
	private Long seuil;
	
	@Column(name="BLOQUE")
	private Boolean bloque;
	
	@Column(name="OBSERVATIONS")
	private String observation;
	   
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	

	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;
																							
	@Column(name="VERSION")
	private String version;
	
	@Column(name="CA_TEMP")
	private Double chiffreAffaireTmp;
	
	@Column(name="REGLEMENT_TEMP")
	private Double reglementTmp;
	
	@Column(name="DATE_DEMARRAGE")
	private Calendar dateDemarrage;
	
	

	
	@Column(name="total_reglement")
	private Double totalReglement;
	
	@Column(name="total_facture")
	private Double totalFacture;
	
	
	@Column(name="regelement_payes")
	private Double regelementPayes;
	
	@Column(name="regelement_en_circulation")
	private Double regelementEnCirculation;
	
	@Column(name="total_facture_non_paye")
	private Double totalFactureNonPaye;
	
	@Column(name="solde_anterieur")
	private Double soldeAnterieur;
	
	
	@Column(name="date_solde_anterieur")
	private Calendar dateSoldeAnterieur;
	
	
	
	@Column(name="solde_initial_nd")
	private Double soldeInitialNonDeclaree;
	
	
	

	public Double getSoldeInitialNonDeclaree() {
		return soldeInitialNonDeclaree;
	}

	public void setSoldeInitialNonDeclaree(Double soldeInitialNonDeclaree) {
		this.soldeInitialNonDeclaree = soldeInitialNonDeclaree;
	}

	public Double getTotalReglement() {
		return totalReglement;
	}

	public void setTotalReglement(Double totalReglement) {
		this.totalReglement = totalReglement;
	}

	public Double getTotalFacture() {
		return totalFacture;
	}

	public void setTotalFacture(Double totalFacture) {
		this.totalFacture = totalFacture;
	}

	public Double getRegelementPayes() {
		return regelementPayes;
	}

	public void setRegelementPayes(Double regelementPayes) {
		this.regelementPayes = regelementPayes;
	}

	public Double getRegelementEnCirculation() {
		return regelementEnCirculation;
	}

	public void setRegelementEnCirculation(Double regelementEnCirculation) {
		this.regelementEnCirculation = regelementEnCirculation;
	}

	public Double getTotalFactureNonPaye() {
		return totalFactureNonPaye;
	}

	public void setTotalFactureNonPaye(Double totalFactureNonPaye) {
		this.totalFactureNonPaye = totalFactureNonPaye;
	}

	public Double getSoldeAnterieur() {
		return soldeAnterieur;
	}

	public void setSoldeAnterieur(Double soldeAnterieur) {
		this.soldeAnterieur = soldeAnterieur;
	}

	public Calendar getDateSoldeAnterieur() {
		return dateSoldeAnterieur;
	}

	public void setDateSoldeAnterieur(Calendar dateSoldeAnterieur) {
		this.dateSoldeAnterieur = dateSoldeAnterieur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartIntId() {
		return partIntId;
	}

	public void setPartIntId(Long partIntId) {
		this.partIntId = partIntId;
	}

	public Double getSoldeInitial() {
		return soldeInitial;
	}

	public void setSoldeInitial(Double soldeInitial) {
		this.soldeInitial = soldeInitial;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Long getSeuil() {
		return seuil;
	}

	public void setSeuil(Long seuil) {
		this.seuil = seuil;
	}

	public Boolean getBloque() {
		return bloque;
	}

	public void setBloque(Boolean bloque) {
		this.bloque = bloque;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
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

	public Double getChiffreAffaireTmp() {
		return chiffreAffaireTmp;
	}

	public void setChiffreAffaireTmp(Double chiffreAffaireTmp) {
		this.chiffreAffaireTmp = chiffreAffaireTmp;
	}

	public Double getReglementTmp() {
		return reglementTmp;
	}

	public void setReglementTmp(Double reglementTmp) {
		this.reglementTmp = reglementTmp;
	}

	public Calendar getDateDemarrage() {
		return dateDemarrage;
	}

	public void setDateDemarrage(Calendar dateDemarrage) {
		this.dateDemarrage = dateDemarrage;
	}
	
}
