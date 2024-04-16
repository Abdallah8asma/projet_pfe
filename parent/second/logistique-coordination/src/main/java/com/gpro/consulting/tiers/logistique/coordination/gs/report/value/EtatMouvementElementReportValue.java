package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

import java.util.Calendar;

/**
 * 
 * @author Wahid Gazzah
 * @since 24/02/2016
 *
 */
public class EtatMouvementElementReportValue {
	
	private Long id;
	private Double quantiteReelle;
	private String typeEntree;
	
	// criteres de grouppement
	private Calendar bonMouvementStockDate;
	private String bonMouvementStockNumero;
	private String bonMouvementStockRaison;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantiteReelle() {
		return quantiteReelle;
	}

	public void setQuantiteReelle(Double quantiteReelle) {
		this.quantiteReelle = quantiteReelle;
	}

	public String getTypeEntree() {
		return typeEntree;
	}

	public void setTypeEntree(String typeEntree) {
		this.typeEntree = typeEntree;
	}

	public Calendar getBonMouvementStockDate() {
		return bonMouvementStockDate;
	}

	public void setBonMouvementStockDate(Calendar bonMouvementStockDate) {
		this.bonMouvementStockDate = bonMouvementStockDate;
	}

	public String getBonMouvementStockNumero() {
		return bonMouvementStockNumero;
	}

	public void setBonMouvementStockNumero(String bonMouvementStockNumero) {
		this.bonMouvementStockNumero = bonMouvementStockNumero;
	}
	
	public String getBonMouvementStockRaison() {
		return bonMouvementStockRaison;
	}
	
	public void setBonMouvementStockRaison(String bonMouvementStockRaison) {
		this.bonMouvementStockRaison = bonMouvementStockRaison;
	}


}
