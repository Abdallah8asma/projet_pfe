package com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class SoldeClientValue implements Comparable<SoldeClientValue> {

	private Long id;
	private Long partIntId;
	private Double soldeInitial;
	private Calendar dateIntroduction;
	private Long seuil;
	private Boolean bloque;
	private String observation;

	// Added on 22/08/2016 By Ameni
	private Double chiffreAffaireTmp;
	private Double reglementTmp;
	private Calendar dateDemarrage;

	private String partieIntAbreviation;
	private String partieIntReference;

	private Double totalReglement;

	private Double totalFacture;

	private Double regelementPayes;

	private Double regelementEnCirculation;

	private Double totalFactureNonPaye;

	private Double soldeAnterieur;

	private Calendar dateSoldeAnterieur;

	
	
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

	public int compareTo(SoldeClientValue element) {
		return (element.getId().compareTo(id));
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

	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}

	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}

	public String getPartieIntReference() {
		return partieIntReference;
	}

	public void setPartieIntReference(String partieIntReference) {
		this.partieIntReference = partieIntReference;
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
