package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 19/02/2016
 * 
 */
public class MarcheValue {
	
	private Long id;
	private String designation;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private Long partieInteresseId;
	private String partieIntAbreviation;
	
	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}
	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}
	public Long getPartieInteresseId() {
		return partieInteresseId;
	}

	public void setPartieInteresseId(Long partieInteresseId) {
		this.partieInteresseId = partieInteresseId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Boolean isBlSuppression() {
		return blSuppression;
	}
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	public Calendar getDateSuppression() {
		return dateSuppression;
	}
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Calendar getDateModification() {
		return dateModification;
	}
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
