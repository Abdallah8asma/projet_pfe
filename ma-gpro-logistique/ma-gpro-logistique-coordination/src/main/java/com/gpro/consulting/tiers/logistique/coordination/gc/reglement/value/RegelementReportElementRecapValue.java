package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value;

import java.util.Calendar;

public class RegelementReportElementRecapValue {

	private int nbr;

	private String mois;

	private String type;

	private Double montantRegement;


	private Calendar debut, fin;

	

	public Double getMontantRegement() {
		return montantRegement;
	}

	public void setMontantRegement(Double montantRegement) {
		this.montantRegement = montantRegement;
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
