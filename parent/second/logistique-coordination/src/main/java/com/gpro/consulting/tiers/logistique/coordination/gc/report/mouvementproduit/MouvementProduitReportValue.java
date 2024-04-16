package com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit;

import java.util.List;

public class MouvementProduitReportValue {

	private Long produitId;
	private String produitReference;
	private String produitDesignation;

	private Double qteLivraison, qteReception, qteEnStock;

	private List<MouvementProduitElementReportValue> mouvement;

	public Double getQteLivraison() {
		return qteLivraison;
	}

	public void setQteLivraison(Double qteLivraison) {
		this.qteLivraison = qteLivraison;
	}

	public Double getQteReception() {
		return qteReception;
	}

	public void setQteReception(Double qteReception) {
		this.qteReception = qteReception;
	}

	public Double getQteEnStock() {
		return qteEnStock;
	}

	public void setQteEnStock(Double qteEnStock) {
		this.qteEnStock = qteEnStock;
	}

	public List<MouvementProduitElementReportValue> getMouvement() {
		return mouvement;
	}

	public void setMouvement(List<MouvementProduitElementReportValue> mouvement) {
		this.mouvement = mouvement;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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

}
