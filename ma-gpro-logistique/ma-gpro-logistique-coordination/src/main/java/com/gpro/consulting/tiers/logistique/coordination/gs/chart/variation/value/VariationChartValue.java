package com.gpro.consulting.tiers.logistique.coordination.gs.chart.variation.value;

import java.util.Calendar;

public class VariationChartValue {

	private String typeArticle;
	private String typeMouvement;
	
	private Calendar date;

	private Double quantite;
	private Double quantiteReelle;

	private Double poids;
	private Double poidsReel;

	private Long cones;
	private Long conesReel;

	private Long fincones;
	private Long finconesReel;
	
	private Long nbRouleaux;
	private Long nbRouleauxReel;
	
	public String getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(String typeArticle) {
		this.typeArticle = typeArticle;
	}
	public String getTypeMouvement() {
		return typeMouvement;
	}
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public Double getQuantiteReelle() {
		return quantiteReelle;
	}
	public void setQuantiteReelle(Double quantiteReelle) {
		this.quantiteReelle = quantiteReelle;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public Double getPoidsReel() {
		return poidsReel;
	}
	public void setPoidsReel(Double poidsReel) {
		this.poidsReel = poidsReel;
	}
	public Long getCones() {
		return cones;
	}
	public void setCones(Long cones) {
		this.cones = cones;
	}
	public Long getConesReel() {
		return conesReel;
	}
	public void setConesReel(Long conesReel) {
		this.conesReel = conesReel;
	}
	public Long getFincones() {
		return fincones;
	}
	public void setFincones(Long fincones) {
		this.fincones = fincones;
	}
	public Long getFinconesReel() {
		return finconesReel;
	}
	public void setFinconesReel(Long finconesReel) {
		this.finconesReel = finconesReel;
	}
	public Long getNbRouleaux() {
		return nbRouleaux;
	}
	public void setNbRouleaux(Long nbRouleaux) {
		this.nbRouleaux = nbRouleaux;
	}
	public Long getNbRouleauxReel() {
		return nbRouleauxReel;
	}
	public void setNbRouleauxReel(Long nbRouleauxReel) {
		this.nbRouleauxReel = nbRouleauxReel;
	}
	
	
}
