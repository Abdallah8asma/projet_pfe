package com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public class RechercheMulticritereFicheClientValue {

	private Long clientId;
	private Long produitId;
	private Calendar dateMin;
	private Calendar dateMax;
	private String typeRapport;

	
	private String declarerRech ;
	
	
	
	
	
	public String getDeclarerRech() {
		return declarerRech;
	}
	public void setDeclarerRech(String declarerRech) {
		this.declarerRech = declarerRech;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Calendar getDateMin() {
		return dateMin;
	}
	public void setDateMin(Calendar dateMin) {
		this.dateMin = dateMin;
	}
	public Calendar getDateMax() {
		return dateMax;
	}
	public void setDateMax(Calendar dateMax) {
		this.dateMax = dateMax;
	}
	public String getTypeRapport() {
		return typeRapport;
	}
	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
}
