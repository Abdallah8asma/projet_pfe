package com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */
public class RechercheMulticritereFicheSuiveuseValue {
	
	private String numMise;
	private Long produitId;
	private Long partieIntId;
	private Calendar dateEntreMin;
	private Calendar dateEntreMax;
	private String typeLivraison;
	private Double poidsSortieMin;
	private Double poidsSortieMax;
	
	//added on 04/04/2016, by Wahid Gazzah
	private Double rapportBain;
	
	public String getNumMise() {
		return numMise;
	}
	public void setNumMise(String numMise) {
		this.numMise = numMise;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getPartieIntId() {
		return partieIntId;
	}
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}
	public Calendar getDateEntreMin() {
		return dateEntreMin;
	}
	public void setDateEntreMin(Calendar dateEntreMin) {
		this.dateEntreMin = dateEntreMin;
	}
	public Calendar getDateEntreMax() {
		return dateEntreMax;
	}
	public void setDateEntreMax(Calendar dateEntreMax) {
		this.dateEntreMax = dateEntreMax;
	}
	public String getTypeLivraison() {
		return typeLivraison;
	}
	public void setTypeLivraison(String typeLivraison) {
		this.typeLivraison = typeLivraison;
	}
	public Double getPoidsSortieMin() {
		return poidsSortieMin;
	}
	public void setPoidsSortieMin(Double poidsSortieMin) {
		this.poidsSortieMin = poidsSortieMin;
	}
	public Double getPoidsSortieMax() {
		return poidsSortieMax;
	}
	public void setPoidsSortieMax(Double poidsSortieMax) {
		this.poidsSortieMax = poidsSortieMax;
	}
	public Double getRapportBain() {
		return rapportBain;
	}
	public void setRapportBain(Double rapportBain) {
		this.rapportBain = rapportBain;
	}
	@Override
	public String toString() {
		return "RechercheMulticritereFicheSuiveuseValue [numMise=" + numMise + ", produitId=" + produitId
				+ ", partieIntId=" + partieIntId + ", dateEntreMin=" + dateEntreMin + ", dateEntreMax=" + dateEntreMax
				+ ", typeLivraison=" + typeLivraison + ", poidsSortieMin=" + poidsSortieMin + ", poidsSortieMax="
				+ poidsSortieMax + ", rapportBain=" + rapportBain + "]";
	}
	
	
}
