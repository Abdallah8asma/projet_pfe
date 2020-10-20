package com.gpro.consulting.tiers.logistique.coordination.gl.facon.value;

import java.util.List;

/**
 * @author Zeineb Medimagh
 * @since 28/09/2016
 *
 */
public class ResultatRechecheFicheFaconElementValue implements Comparable<ResultatRechecheFicheFaconElementValue>{
	
	private Long id;
	private Long produitId;
	private Long partieIntId;
	private String refBonReception;
	private String methodeTeinture;
	private String observations;
	private String produitReference;
	private String produitDesignation;
	private String clientReference;
	private String clientAbreviation;
	private String codeCouleur;
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
	public String getProduitReference() {
		return produitReference;
	}
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getClientReference() {
		return clientReference;
	}
	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}
	public String getClientAbreviation() {
		return clientAbreviation;
	}
	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}
	public String getCodeCouleur() {
		return codeCouleur;
	}
	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}
	public List<TraitementFicheValue> getListeTraitementsFiche() {
		return listeTraitementsFiche;
	}
	public void setListeTraitementsFiche(List<TraitementFicheValue> listeTraitementsFiche) {
		this.listeTraitementsFiche = listeTraitementsFiche;
	}
	@Override
	public int compareTo(ResultatRechecheFicheFaconElementValue o) {
		ResultatRechecheFicheFaconElementValue element= (ResultatRechecheFicheFaconElementValue)o;
		return (element.getId().compareTo(id));
	}
}
