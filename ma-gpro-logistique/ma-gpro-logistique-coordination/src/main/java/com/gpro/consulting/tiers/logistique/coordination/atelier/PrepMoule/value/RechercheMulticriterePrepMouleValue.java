package com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value;

import java.util.Calendar;

public class RechercheMulticriterePrepMouleValue {


	/** The id. */
	private Long id;
	
	private Long idMoule;

	/** The reference. */
	private String reference;

	private String designation;
	
	private String machine;
	
	private String emplacement;
	 
	private Calendar datePrep;
	
	private Calendar datePrepF;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMoule() {
		return idMoule;
	}

	public void setIdMoule(Long idMoule) {
		this.idMoule = idMoule;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}


	public Calendar getDatePrep() {
		return datePrep;
	}

	public void setDatePrep(Calendar datePrep) {
		this.datePrep = datePrep;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Calendar getDatePrepF() {
		return datePrepF;
	}

	public void setDatePrepF(Calendar datePrepF) {
		this.datePrepF = datePrepF;
	}

	@Override
	public String toString() {
		return "RechercheMulticriterePrepMouleValue [id=" + id + ", idMoule=" + idMoule + ", reference=" + reference
				+ ", designation=" + designation + ", machine=" + machine + ", emplacement=" + emplacement
				+ ", datePrep=" + datePrep + ", datePrepF=" + datePrepF + "]";
	}


	
	

}
