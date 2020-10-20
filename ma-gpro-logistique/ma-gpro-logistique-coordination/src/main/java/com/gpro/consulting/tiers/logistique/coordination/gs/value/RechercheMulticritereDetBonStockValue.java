package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;

public class RechercheMulticritereDetBonStockValue {

	private Long partieIntId;
	private Long idProduit;
	private Long idDepot;

	private Calendar dateDe;

	private Calendar dateA;

	private Double prixDe;

	private Double prixA;

	private Double qteDe;

	private Double qteA;

	private Calendar dateStrA;

	public Calendar getDateStrA() {
		return dateStrA;
	}

	public void setDateStrA(Calendar dateStrA) {
		this.dateStrA = dateStrA;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateDe() {
		return dateDe;
	}

	public void setDateDe(Calendar dateDe) {
		this.dateDe = dateDe;
	}

	public Calendar getDateA() {
		return dateA;
	}

	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}

	public Double getPrixDe() {
		return prixDe;
	}

	public void setPrixDe(Double prixDe) {
		this.prixDe = prixDe;
	}

	public Double getPrixA() {
		return prixA;
	}

	public void setPrixA(Double prixA) {
		this.prixA = prixA;
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

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public Long getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}

}
