package com.gpro.consulting.tiers.logistique.coordination.gc.report.mouvementproduit;

import java.util.Calendar;

public class MouvementProduitElementReportValue implements Comparable<MouvementProduitElementReportValue> {

	private Double quantite;

	private Calendar dateMouvement;

	private String typeMouvement;
	private String referenceMouvement;

	private Double quantiteEnStock;

	public Double getQuantiteEnStock() {
		return quantiteEnStock;
	}

	public void setQuantiteEnStock(Double quantiteEnStock) {
		this.quantiteEnStock = quantiteEnStock;
	}

	public Calendar getDateMouvement() {
		return dateMouvement;
	}

	public void setDateMouvement(Calendar dateMouvement) {
		this.dateMouvement = dateMouvement;
	}

	public String getTypeMouvement() {
		return typeMouvement;
	}

	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	public String getReferenceMouvement() {
		return referenceMouvement;
	}

	public void setReferenceMouvement(String referenceMouvement) {
		this.referenceMouvement = referenceMouvement;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	@Override
	public int compareTo(MouvementProduitElementReportValue arg0) {
		MouvementProduitElementReportValue element = (MouvementProduitElementReportValue) arg0;

		if (dateMouvement != null && element.getDateMouvement() != null)
			return (dateMouvement.compareTo(element.getDateMouvement()));
		else
			return 0;
	}

}
