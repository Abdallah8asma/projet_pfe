package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class ResultatRechecheFicheSuiveuseElementValue implements Comparable<ResultatRechecheFicheSuiveuseElementValue>{
	
	private Long id; // id fiche suiveuse
	private String referenceMise;
	private Long produitId;
	private Long partieIntId;
	private Calendar dateLivraison;
	private Calendar dateLancement;
	private String typeLivraison;
	private Double poidSortie;
	private Double laizeFini;
	
	private String produitReference;
	private String produitDesignation;
	private String clientReference;
	private String clientAbreviation;
	
	public int compareTo(ResultatRechecheFicheSuiveuseElementValue o) {
		ResultatRechecheFicheSuiveuseElementValue element= (ResultatRechecheFicheSuiveuseElementValue)o;
		return (element.getId().compareTo(id));
	}
	
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
	public String getTypeLivraison() {
		return typeLivraison;
	}
	public void setTypeLivraison(String typeLivraison) {
		this.typeLivraison = typeLivraison;
	}
	public Double getPoidSortie() {
		return poidSortie;
	}
	public void setPoidSortie(Double poidSortie) {
		this.poidSortie = poidSortie;
	}
	public Double getLaizeFini() {
		return laizeFini;
	}
	public void setLaizeFini(Double laizeFini) {
		this.laizeFini = laizeFini;
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

	public String getReferenceMise() {
		return referenceMise;
	}

	public void setReferenceMise(String referenceMise) {
		this.referenceMise = referenceMise;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Calendar getDateLancement() {
		return dateLancement;
	}

	public void setDateLancement(Calendar dateLancement) {
		this.dateLancement = dateLancement;
	}

	@Override
	public String toString() {
		return "ResultatRechecheFicheSuiveuseElementValue [id=" + id + ", referenceMise=" + referenceMise
				+ ", produitId=" + produitId + ", partieIntId=" + partieIntId + ", dateLivraison=" + dateLivraison
				+ ", dateLancement=" + dateLancement + ", typeLivraison=" + typeLivraison + ", poidSortie=" + poidSortie
				+ ", laizeFini=" + laizeFini + ", produitReference=" + produitReference + ", produitDesignation="
				+ produitDesignation + ", clientReference=" + clientReference + ", clientAbreviation="
				+ clientAbreviation + "]\n";
	}
	

}
