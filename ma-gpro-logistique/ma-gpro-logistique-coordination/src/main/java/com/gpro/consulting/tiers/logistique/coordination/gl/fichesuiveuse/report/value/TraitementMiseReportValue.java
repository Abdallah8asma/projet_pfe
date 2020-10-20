package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.report.value;

import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 29/03/2016
 *
 */
public class TraitementMiseReportValue implements Comparable<TraitementMiseReportValue>{
	
	private List<String> observations;
	private Long machineId;
	private String machineDesignation;
	private String traitementDesignation;
	private Boolean showObservations = false;
	
	//added on 01/04/2016, by Wahid Gazzah
	private Long order;
	
	//added on 06/04/2016, by Wahid Gazzah
	private String type;//Teinture, Finissage, Preparation
	
	private List<ElementRecetteMiseReportValue> listElementRecetteMise;

	//added on 26/09/2016, by Zeineb Medimagh
	private Double prixUnitaire;
	private Double prixTotal;
	private String prixUnitaireLabel="PU:";
	private String prixTotalLabel= "PT:";
		
	@Override
	public int compareTo(TraitementMiseReportValue element) {
		return order.compareTo(element.getOrder());
	}
	
	public List<String> getObservations() {
		return observations;
	}
	public void setObservations(List<String> observations) {
		this.observations = observations;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public String getMachineDesignation() {
		return machineDesignation;
	}
	public void setMachineDesignation(String machineDesignation) {
		this.machineDesignation = machineDesignation;
	}
	public String getTraitementDesignation() {
		return traitementDesignation;
	}
	public void setTraitementDesignation(String traitementDesignation) {
		this.traitementDesignation = traitementDesignation;
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
	public List<ElementRecetteMiseReportValue> getListElementRecetteMise() {
		return listElementRecetteMise;
	}
	public void setListElementRecetteMise(
			List<ElementRecetteMiseReportValue> listElementRecetteMise) {
		this.listElementRecetteMise = listElementRecetteMise;
	}

	public Boolean getShowObservations() {
		return showObservations;
	}

	public void setShowObservations(Boolean showObservations) {
		this.showObservations = showObservations;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getPrixUnitaireLabel() {
		return prixUnitaireLabel;
	}

	public void setPrixUnitaireLabel(String prixUnitaireLabel) {
		this.prixUnitaireLabel = prixUnitaireLabel;
	}

	public String getPrixTotalLabel() {
		return prixTotalLabel;
	}

	public void setPrixTotalLabel(String prixTotalLabel) {
		this.prixTotalLabel = prixTotalLabel;
	}
	
}
