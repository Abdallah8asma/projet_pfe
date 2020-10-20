package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Calendar;
import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public class EntrepotValue implements Comparable{
	
	private Long id;
	private String designation;
	private Long partintId;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	private List<RouleauFiniValue> listRouleauFiniValue;
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the partintId
	 */
	public Long getPartintId() {
		return partintId;
	}
	/**
	 * @param partintId the partintId to set
	 */
	public void setPartintId(Long partintId) {
		this.partintId = partintId;
	}
	/**
	 * @return the blSuppression
	 */
	public Boolean isBlSuppression() {
		return blSuppression;
	}
	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}
	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}
	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}
	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the rouleauFiniValue
	 */
	public List<RouleauFiniValue> getListRouleauFiniValue() {
		return listRouleauFiniValue;
	}
	/**
	 * @param rouleauFiniValue the rouleauFiniValue to set
	 */
	public void setListRouleauFiniValue(List<RouleauFiniValue> listRouleauFiniValue) {
		this.listRouleauFiniValue = listRouleauFiniValue;
	}
	
	@Override
	public int compareTo(Object o) {
		EntrepotValue element= (EntrepotValue)o;
		return (element.getId().compareTo(id));
	}
	
}
