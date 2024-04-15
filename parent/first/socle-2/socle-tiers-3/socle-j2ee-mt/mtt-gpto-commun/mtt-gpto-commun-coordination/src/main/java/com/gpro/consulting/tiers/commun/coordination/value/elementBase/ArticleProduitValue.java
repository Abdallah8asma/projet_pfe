package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.HashSet;
import java.util.Set;

public class ArticleProduitValue {

	private Long id;

	private Long articleId;

	private Double qte;

	private String produitSemiFini;

	private String referenceArticle;
	private String designationArticle;

	
	private Long impressionProduitId;
	private String impressionDesignation;
	
	private String grammage;
	
	private String dimension;
	
	
	private Long sousFamilleArticleId;
	private String referenceFamilleArticle;
	
	
	private String infoMatiere;
	
	
	private Set<OptionArticleProduitValue> optionArticleProduits = new HashSet<OptionArticleProduitValue>();
	
	
	private Set<OperationArticleProduitValue> operationArticleProduits = new HashSet<OperationArticleProduitValue>();
	
	
	

	public String getInfoMatiere() {
		return infoMatiere;
	}

	public void setInfoMatiere(String infoMatiere) {
		this.infoMatiere = infoMatiere;
	}

	public Long getSousFamilleArticleId() {
		return sousFamilleArticleId;
	}

	public void setSousFamilleArticleId(Long sousFamilleArticleId) {
		this.sousFamilleArticleId = sousFamilleArticleId;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Set<OperationArticleProduitValue> getOperationArticleProduits() {
		return operationArticleProduits;
	}

	public void setOperationArticleProduits(Set<OperationArticleProduitValue> operationArticleProduits) {
		this.operationArticleProduits = operationArticleProduits;
	}

	public String getGrammage() {
		return grammage;
	}

	public void setGrammage(String grammage) {
		this.grammage = grammage;
	}

	public Set<OptionArticleProduitValue> getOptionArticleProduits() {
		return optionArticleProduits;
	}

	public void setOptionArticleProduits(Set<OptionArticleProduitValue> optionArticleProduits) {
		this.optionArticleProduits = optionArticleProduits;
	}

	public Long getImpressionProduitId() {
		return impressionProduitId;
	}

	public void setImpressionProduitId(Long impressionProduitId) {
		this.impressionProduitId = impressionProduitId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Double getQte() {
		return qte;
	}

	public void setQte(Double qte) {
		this.qte = qte;
	}

	public String getProduitSemiFini() {
		return produitSemiFini;
	}

	public void setProduitSemiFini(String produitSemiFini) {
		this.produitSemiFini = produitSemiFini;
	}

	public String getReferenceArticle() {
		return referenceArticle;
	}

	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	

	public String getDesignationArticle() {
		return designationArticle;
	}

	public void setDesignationArticle(String designationArticle) {
		this.designationArticle = designationArticle;
	}

	public String getReferenceFamilleArticle() {
		return referenceFamilleArticle;
	}

	public void setReferenceFamilleArticle(String referenceFamilleArticle) {
		this.referenceFamilleArticle = referenceFamilleArticle;
	}

	public String getImpressionDesignation() {
		return impressionDesignation;
	}

	public void setImpressionDesignation(String impressionDesignation) {
		this.impressionDesignation = impressionDesignation;
	}

	

}
