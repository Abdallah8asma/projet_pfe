package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.reglement;

import java.util.Calendar;

/**
 * 
 * @author Hajer Amri
 * @since 31/01/2017
 *
 */
public class ReglementReportParRefElementDetailsValue {

	private String numPiece;
	private String banque;
	private Double montant;
	private Calendar dateEmission;
	private Calendar dateEcheance;
	// private String refFacture;
	private String typeReglement;
	private String regle;
	private String observation;

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Calendar getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Calendar dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}


	public String getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(String typeReglement) {
		this.typeReglement = typeReglement;
	}

	public String getRegle() {
		return regle;
	}

	public void setRegle(String regle) {
		this.regle = regle;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

}
