package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue;

/**
 * @author Ameni Berrich
 *
 */
public class LivraisonVenteFnReportingVue {

	private Long clientId;
	private String clientReference;
	private String clientAbreviation;
	private Double chiffreAffaire;
	
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
	public Double getChiffreAffaire() {
		return chiffreAffaire;
	}
	public void setChiffreAffaire(Double chiffreAffaire) {
		this.chiffreAffaire = chiffreAffaire;
	}
	
}
