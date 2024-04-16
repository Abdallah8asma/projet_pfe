package com.gpro.consulting.tiers.logistique.coordination.gl.facon.value;

import java.util.Calendar;
import java.util.List;

/**
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public class FicheFaconValue{
	
	private Long id;
	private Long produitId;
	private Long partieIntId;
	private String refBonReception;
	private String methodeTeinture;
	private String observations;
	private boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private Double poidsEcru;
	private Double poidsFini;
	
	private List<TraitementFicheValue> listeTraitementsFiche;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getPartieIntId() {
		return partieIntId;
	}
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}
	public String getRefBonReception() {
		return refBonReception;
	}
	public void setRefBonReception(String refBonReception) {
		this.refBonReception = refBonReception;
	}
	public String getMethodeTeinture() {
		return methodeTeinture;
	}
	public void setMethodeTeinture(String methodeTeinture) {
		this.methodeTeinture = methodeTeinture;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
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
	
	public Double getPoidsEcru() {
		return poidsEcru;
	}
	public void setPoidsEcru(Double poidsEcru) {
		this.poidsEcru = poidsEcru;
	}
	public Double getPoidsFini() {
		return poidsFini;
	}
	public void setPoidsFini(Double poidsFini) {
		this.poidsFini = poidsFini;
	}
	public List<TraitementFicheValue> getListeTraitementsFiche() {
		return listeTraitementsFiche;
	}
	public void setListeTraitementsFiche(List<TraitementFicheValue> listeTraitementsFiche) {
		this.listeTraitementsFiche = listeTraitementsFiche;
	}
}
