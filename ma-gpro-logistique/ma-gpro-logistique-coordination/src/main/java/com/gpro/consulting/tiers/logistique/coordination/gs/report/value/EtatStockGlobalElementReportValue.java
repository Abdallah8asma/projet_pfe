package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

/**
 * 
 * @author Zeineb Medimagh
 * @since 30/10/2016
 *
 */
public class EtatStockGlobalElementReportValue extends EtatStockElementReportValue{

	private String sousFamilleArticle;
	private String couleur;
	private String producteur;
	private String codeFournisseur;
	private Double qteReservee;
	private Long rouleauActuel;
	private Long rouleauReserve;
	
	private Long articleId;
	
	
	
	private Double qteLibre;
	
	
	
	
	
	public Double getQteLibre() {
		return qteLibre;
	}
	public void setQteLibre(Double qteLibre) {
		this.qteLibre = qteLibre;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getSousFamilleArticle() {
		return sousFamilleArticle;
	}
	public void setSousFamilleArticle(String sousFamilleArticle) {
		this.sousFamilleArticle = sousFamilleArticle;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getProducteur() {
		return producteur;
	}
	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}
	public String getCodeFournisseur() {
		return codeFournisseur;
	}
	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}
	public Double getQteReservee() {
		return qteReservee;
	}
	public void setQteReservee(Double qteReservee) {
		this.qteReservee = qteReservee;
	}
	public Long getRouleauActuel() {
		return rouleauActuel;
	}
	public void setRouleauActuel(Long rouleauActuel) {
		this.rouleauActuel = rouleauActuel;
	}
	public Long getRouleauReserve() {
		return rouleauReserve;
	}
	public void setRouleauReserve(Long rouleauReserve) {
		this.rouleauReserve = rouleauReserve;
	}
	
}
