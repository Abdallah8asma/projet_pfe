package com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.vue;

/**
 * @author Ameni Berrich
 *
 */
public class BonSortieFnReportingVue {

	private Long clientId;
	private String clientReference;
	private String clientAbreviation;
	
	private Double metrageTotalECH;
	private Double metrageTotalVente;
	
	private String type;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public Double getMetrageTotalECH() {
		return metrageTotalECH;
	}

	public void setMetrageTotalECH(Double metrageTotalECH) {
		this.metrageTotalECH = metrageTotalECH;
	}

	public Double getMetrageTotalVente() {
		return metrageTotalVente;
	}

	public void setMetrageTotalVente(Double metrageTotalVente) {
		this.metrageTotalVente = metrageTotalVente;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
