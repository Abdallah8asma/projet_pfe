package com.gpro.consulting.tiers.commun.coordination.value.elementBase;


public class RechercheMulticritereProduitValue {
	private String reference;
	private String designation ;
	private String famille; 
	private String sousfamille;
	private String partieInteressee ;
	private String etat;
	private String site;
	private Double prix_inf;
	private Double prix_sup; 
	
	private Long groupeClientId;
	
	
	private String retour;
	
	
	private Long boutiqueId;
	
	
	private Long famillePiId;
	

	private String numero;
	
	private String referenceFournisseur;
	
	
	private String stock;
	
	
	private String pantone;
	
	private String format;
	
	
	
	
	public String getPantone() {
		return pantone;
	}


	public void setPantone(String pantone) {
		this.pantone = pantone;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getReferenceFournisseur() {
		return referenceFournisseur;
	}


	public void setReferenceFournisseur(String referenceFournisseur) {
		this.referenceFournisseur = referenceFournisseur;
	}


	public String getStock() {
		return stock;
	}


	public void setStock(String stock) {
		this.stock = stock;
	}


	public Long getFamillePiId() {
		return famillePiId;
	}


	public void setFamillePiId(Long famillePiId) {
		this.famillePiId = famillePiId;
	}


	public Long getBoutiqueId() {
		return boutiqueId;
	}


	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}


	public String getRetour() {
		return retour;
	}


	public void setRetour(String retour) {
		this.retour = retour;
	}


	public Long getGroupeClientId() {
		return groupeClientId;
	}


	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getFamille() {
		return famille;
	}


	public void setFamille(String famille) {
		this.famille = famille;
	}


	public String getSousfamille() {
		return sousfamille;
	}


	public void setSousfamille(String sousfamille) {
		this.sousfamille = sousfamille;
	}

	public String getPartieInteressee() {
		return partieInteressee;
	}


	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}
	


	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}


	/**
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}

    /**
	 * @return the prix_inf
	 */
	public Double getPrix_inf() {
		return prix_inf;
	}


	/**
	 * @param prix_inf the prix_inf to set
	 */
	public void setPrix_inf(Double prix_inf) {
		this.prix_inf = prix_inf;
	}


	/**
	 * @return the prix_sup
	 */
	public Double getPrix_sup() {
		return prix_sup;
	}


	/**
	 * @param prix_sup the prix_sup to set
	 */
	public void setPrix_sup(Double prix_sup) {
		this.prix_sup = prix_sup;
	}


	@Override
	public boolean equals(Object arg0) {
	// TODO Auto-generated method stub
	return super.equals(arg0);
}
}
