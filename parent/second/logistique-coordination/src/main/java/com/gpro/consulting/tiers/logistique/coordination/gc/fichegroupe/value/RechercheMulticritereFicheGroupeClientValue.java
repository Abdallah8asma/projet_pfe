package com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public class RechercheMulticritereFicheGroupeClientValue {

	private Long groupeClientId;
	private Long produitId;
	private Calendar dateMin;
	private Calendar dateMax;
	private String typeRapport;

	public Long getGroupeClientId() {
		return groupeClientId;
	}

	public void setGroupeClientId(Long groupeClientId) {
		this.groupeClientId = groupeClientId;
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
