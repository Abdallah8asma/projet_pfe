package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.HashSet;
import java.util.Set;


public class RechercheMulticritereArticleProduitValue {

	private Long id;

	private Long articleId;

	private Double qteDe;
	private Double qteA;

	private String produitSemiFini;

	private String referenceArticle;
	
	private Long impressionProduitId;
	
	private String grammage;
	
	private String dimension;
	
	
	private Long sousFamilleArticleId;
	
	
	private String infoMatiere;
	private boolean isOptimized;
	
	
	private Set<OptionArticleProduitValue> optionArticleProduits = new HashSet<OptionArticleProduitValue>();
	
	
	private Set<OperationArticleProduitValue> operationArticleProduits = new HashSet<OperationArticleProduitValue>();


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


	

	public Double getQteDe() {
		return qteDe;
	}


	public void setQteDe(Double qteDe) {
		this.qteDe = qteDe;
	}


	public Double getQteA() {
		return qteA;
	}


	public void setQteA(Double qteA) {
		this.qteA = qteA;
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


	public Long getImpressionProduitId() {
		return impressionProduitId;
	}


	public void setImpressionProduitId(Long impressionProduitId) {
		this.impressionProduitId = impressionProduitId;
	}


	public String getGrammage() {
		return grammage;
	}


	public void setGrammage(String grammage) {
		this.grammage = grammage;
	}


	public String getDimension() {
		return dimension;
	}


	public void setDimension(String dimension) {
		this.dimension = dimension;
	}


	public Long getSousFamilleArticleId() {
		return sousFamilleArticleId;
	}


	public void setSousFamilleArticleId(Long sousFamilleArticleId) {
		this.sousFamilleArticleId = sousFamilleArticleId;
	}


	public String getInfoMatiere() {
		return infoMatiere;
	}


	public void setInfoMatiere(String infoMatiere) {
		this.infoMatiere = infoMatiere;
	}


	public Set<OptionArticleProduitValue> getOptionArticleProduits() {
		return optionArticleProduits;
	}


	public void setOptionArticleProduits(Set<OptionArticleProduitValue> optionArticleProduits) {
		this.optionArticleProduits = optionArticleProduits;
	}


	public Set<OperationArticleProduitValue> getOperationArticleProduits() {
		return operationArticleProduits;
	}


	public void setOperationArticleProduits(Set<OperationArticleProduitValue> operationArticleProduits) {
		this.operationArticleProduits = operationArticleProduits;
	}


	public boolean isOptimized() {
		return isOptimized;
	}


	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	public static boolean checkForOptimization(RechercheMulticritereArticleProduitValue request) {

		return isNullOrEmpty(request.getArticleId())

				
				&& isNullOrEmpty(request.getQteA())
				&& isNullOrEmpty(request.getQteDe())
				&& isNullOrEmpty(request.getSousFamilleArticleId()) 
				&& isNullOrEmpty(request.getGrammage())
				&& isNullOrEmpty(request.getDimension())
				&& isNullOrEmpty(request.getImpressionProduitId())
				&& isNullOrEmpty(request.getInfoMatiere())
				&& isNullOrEmpty(request.getProduitSemiFini()) 
				&& isNullOrEmpty(request.getReferenceArticle())
		        &&isNullOrEmpty(request.getReferenceArticle());
		    
		       
			
				
				

				
                
             	
	}
	public static boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
	
	
}
