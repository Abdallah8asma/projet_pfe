package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

public class OperationArticleProduitValue {

	private Long id;

	private Long operationArticleId;

	private String nom;

	private String designation;

	private Double cout;

	private Double temps;

	private Long ordre;

	public Double getCout() {
		return cout;
	}

	public void setCout(Double cout) {
		this.cout = cout;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
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



	public Long getOperationArticleId() {
		return operationArticleId;
	}

	public void setOperationArticleId(Long operationArticleId) {
		this.operationArticleId = operationArticleId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
