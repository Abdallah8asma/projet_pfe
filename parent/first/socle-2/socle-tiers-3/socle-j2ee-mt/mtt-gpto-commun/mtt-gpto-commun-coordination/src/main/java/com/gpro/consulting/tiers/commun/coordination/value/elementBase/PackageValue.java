package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Calendar;
import java.util.List;

/**
 * Classe: Package
 * 
 * @author: $SAMER
 * 
 */

public class PackageValue implements Comparable<PackageValue> {

	private Long id;

	private String reference;

	private String nom;

	private Calendar dateDebut;

	private Calendar dateFin;

	private Long clientId;

	private Long groupeId;

	private Long boutiqueId;

	private List<DetailsPackageValue> listDetPackage;
	
	
	private String clientAbreviation;
	
	private String groupeAbreviation;

	/************* Getters & Setters *****************/

	
	
	/************* Equals & ToString *****************/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getGroupeAbreviation() {
		return groupeAbreviation;
	}

	public void setGroupeAbreviation(String groupeAbreviation) {
		this.groupeAbreviation = groupeAbreviation;
	}

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

	public Calendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Calendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
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


	

	public List<DetailsPackageValue> getListDetPackage() {
		return listDetPackage;
	}

	public void setListDetPackage(List<DetailsPackageValue> listDetPackage) {
		this.listDetPackage = listDetPackage;
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	@Override
	public int compareTo(PackageValue o) {
		PackageValue element = (PackageValue) o;
		return (element.getId().compareTo(id));
	}

}
