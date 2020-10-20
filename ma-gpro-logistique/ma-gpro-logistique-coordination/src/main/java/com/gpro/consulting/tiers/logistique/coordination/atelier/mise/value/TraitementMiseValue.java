package com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value;

import java.util.Calendar;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementRecetteMiseValue;

/**
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */
public class TraitementMiseValue implements Comparable<TraitementMiseValue>{
	
	private Long id;
	private Long traitementId;
	private Long machineId;
	private String observations;
	private Long ficheSuiveuseId;
	/** PU: Prix Unitaire */
	private Double pu;
	
	//added on 01/04/2016, by Wahid Gazzah
	private Long order;
	
	//added on 06/04/2016, by Wahid Gazzah
	
	//NEW Values: Teinture - Coton, Teinture - PES, Finissage, Preparation
	//OLD: Values: Teinture, Finissage, Preparation 
	private String type;
	
//	updated On 21/04/2016, by Wahid Gazzah
	private Set<ElementRecetteMiseValue> listElementRecetteMise;
	
	@Override
	public int compareTo(TraitementMiseValue element) {
		return order.compareTo(element.getOrder());
	}
	
	@JsonIgnore
	private Boolean blSuppression;
	@JsonIgnore
	private Calendar dateSuppression;
	@JsonIgnore
	private Calendar dateCreation;
	@JsonIgnore
	private Calendar dateModification;
	@JsonIgnore
	private String version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTraitementId() {
		return traitementId;
	}
	public void setTraitementId(Long traitementId) {
		this.traitementId = traitementId;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Long getFicheSuiveuseId() {
		return ficheSuiveuseId;
	}
	public void setFicheSuiveuseId(Long ficheSuiveuseId) {
		this.ficheSuiveuseId = ficheSuiveuseId;
	}
	public Boolean getBlSuppression() {
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
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<ElementRecetteMiseValue> getListElementRecetteMise() {
		return listElementRecetteMise;
	}
	public void setListElementRecetteMise(
			Set<ElementRecetteMiseValue> listElementRecetteMise) {
		this.listElementRecetteMise = listElementRecetteMise;
	}
	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}
	@Override
	public String toString() {
		return "TraitementMiseValue [id=" + id + ", traitementId=" + traitementId + ", machineId=" + machineId
				+ ", observations=" + observations + ", ficheSuiveuseId=" + ficheSuiveuseId + ", pu=" + pu + ", order="
				+ order + ", type=" + type + ", listElementRecetteMise=" + listElementRecetteMise + ", blSuppression="
				+ blSuppression + ", dateSuppression=" + dateSuppression + ", dateCreation=" + dateCreation
				+ ", dateModification=" + dateModification + ", version=" + version + "]";
	}
	
}
