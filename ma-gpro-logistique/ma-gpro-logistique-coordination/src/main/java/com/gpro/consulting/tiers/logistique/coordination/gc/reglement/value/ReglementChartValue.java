package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Calendar;

public class ReglementChartValue {

	private int nbr;

	private String mois;

	private String type;

	private Double reglementPrevision;
	private Double reglementPaye;
	private Double reglementEchu;
	

	
	


	private Calendar debut, fin;






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






	public Double getReglementPrevision() {
		return reglementPrevision;
	}






	public void setReglementPrevision(Double reglementPrevision) {
		this.reglementPrevision = reglementPrevision;
	}






	public Double getReglementPaye() {
		return reglementPaye;
	}






	public void setReglementPaye(Double reglementPaye) {
		this.reglementPaye = reglementPaye;
	}






	public Double getReglementEchu() {
		return reglementEchu;
	}






	public void setReglementEchu(Double reglementEchu) {
		this.reglementEchu = reglementEchu;
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
