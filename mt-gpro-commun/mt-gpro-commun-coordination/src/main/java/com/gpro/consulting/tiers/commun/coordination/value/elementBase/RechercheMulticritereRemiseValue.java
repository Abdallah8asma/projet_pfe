package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

/**
 * The Class PartieInteresseValue.
 */
public class RechercheMulticritereRemiseValue { 


	/** The id. */
	private Long id;
	
	private Long produitId;
	
	private Calendar dateDebutDe;
	private Calendar dateDebutA;
	
	private Calendar dateFinDe;
	private Calendar dateFinA;
	
	private Double remise;
	
	private Long boutiqueId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Calendar getDateDebutDe() {
		return dateDebutDe;
	}

	public void setDateDebutDe(Calendar dateDebutDe) {
		this.dateDebutDe = dateDebutDe;
	}

	public Calendar getDateDebutA() {
		return dateDebutA;
	}

	public void setDateDebutA(Calendar dateDebutA) {
		this.dateDebutA = dateDebutA;
	}

	public Calendar getDateFinDe() {
		return dateFinDe;
	}

	public void setDateFinDe(Calendar dateFinDe) {
		this.dateFinDe = dateFinDe;
	}

	public Calendar getDateFinA() {
		return dateFinA;
	}

	public void setDateFinA(Calendar dateFinA) {
		this.dateFinA = dateFinA;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}
	
	
	
	
	
}
