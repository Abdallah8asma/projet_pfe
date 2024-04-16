package com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value;

import java.util.Calendar;

public class FactureReportElementRecapValue {

	private int nbr;

	private String mois;

	private String type;

	private Double montantTTC;

	private Double montantHTaxe;

	private Calendar debut, fin;
	
	
	private Long month ;
	
	
	private Long year ;
	
	
	private Double cibleCA;
	
	
	
	

	public Double getCibleCA() {
		return cibleCA;
	}

	public void setCibleCA(Double cibleCA) {
		this.cibleCA = cibleCA;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public Double getMontantHTaxe() {
		return montantHTaxe;
	}

	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}

	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getDebut() {
		return debut;
	}

	public void setDebut(Calendar debut) {
		this.debut = debut;
	}

	public Calendar getFin() {
		return fin;
	}

	public void setFin(Calendar fin) {
		this.fin = fin;
	}

}
