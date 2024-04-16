package com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value;

/**
 * @author Wahid Gazzah
 * @since 17/12/2015
 *
 */
public class ReceptionTraitementReportValue {

	private Long id;
	//
	private String traitementObservation;

	private String observations;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTraitementObservation() {
		return traitementObservation;
	}
	public void setTraitementObservation(String traitementObservation) {
		this.traitementObservation = traitementObservation;
	}

	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}



}
