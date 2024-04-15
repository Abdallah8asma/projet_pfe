package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;

/**
 * The Class RechercheMulticriterePackageValue.
 */
public class RechercheMulticriterePackageValue { 

	private Long id;

	private String reference;

	private String nom;

	private Calendar dateDebutDe;
	private Calendar dateDebutA;

	private Calendar dateFinDe;
	private Calendar dateFinA;

	private Long clientId;

	private Long groupeId;

	private Long boutiqueId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getGroupeId() {
		return groupeId;
	}

	public void setGroupeId(Long groupeId) {
		this.groupeId = groupeId;
	}

	public Long getBoutiqueId() {
		return boutiqueId;
	}

	public void setBoutiqueId(Long boutiqueId) {
		this.boutiqueId = boutiqueId;
	}

	
}
