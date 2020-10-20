package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

public class MouvementStockHistoryElementDetailleReportValue extends MouvementStockHistoryElementReportValue implements Comparable<Object>{
	

	private String sousFamille;
	private String couleurArticle;
	private String refLot;
	private String producteur;
	private String codeFournisseur;
	public String getSousFamille() {
		return sousFamille;
	}
	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}
	public String getCouleurArticle() {
		return couleurArticle;
	}
	public void setCouleurArticle(String couleurArticle) {
		this.couleurArticle = couleurArticle;
	}
	public String getRefLot() {
		return refLot;
	}
	public void setRefLot(String refLot) {
		this.refLot = refLot;
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
	
}

