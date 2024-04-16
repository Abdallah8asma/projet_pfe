package com.gpro.consulting.tiers.logistique.coordination.gl.facon.value;

import java.util.Calendar;

/**
 * @author Zeineb Medimagh
 * @since 27/09/2016
 *
 */
public class TraitementFaconValue implements Comparable<TraitementFaconValue>{
	
	private Long id;
	private String designation;
	private boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private Double pu;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isBlSuppression() {
		return blSuppression;
	}
	public void setBlSuppression(boolean blSuppression) {
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
	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public int compareTo(TraitementFaconValue element) {
		return id.compareTo(element.getId());
	}
	@Override
	public String toString() {
		return "TraitementFaconValue [id=" + id + ", designation=" + designation + ", blSuppression=" + blSuppression
				+ ", dateSuppression=" + dateSuppression + ", dateCreation=" + dateCreation + ", dateModification="
				+ dateModification + ", version=" + version + ", pu=" + pu + "]";
	}
	
}
