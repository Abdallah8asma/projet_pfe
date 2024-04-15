package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

public class OptionArticleProduitValue {

	private Long id;

	private Long optionArticleId;
	
	private String familleOptionArticleDesignation;
	
	private String nom;
	
	private String designation;
	
	
	

	public String getFamilleOptionArticleDesignation() {
		return familleOptionArticleDesignation;
	}

	public void setFamilleOptionArticleDesignation(String familleOptionArticleDesignation) {
		this.familleOptionArticleDesignation = familleOptionArticleDesignation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOptionArticleId() {
		return optionArticleId;
	}

	public void setOptionArticleId(Long optionArticleId) {
		this.optionArticleId = optionArticleId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	
	
	
}
