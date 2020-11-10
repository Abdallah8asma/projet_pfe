package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.HashSet;
import java.util.Set;

public class ArticleProduitValue {

	private Long id;

	private Long articleId;

	private Double qte;

	private String produitSemiFini;

	private String referenceArticle;
	
	private Long impressionProduitId;
	
	
	private Set<OptionArticleProduitValue> optionArticleProduits = new HashSet<OptionArticleProduitValue>();
	
	

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

}
