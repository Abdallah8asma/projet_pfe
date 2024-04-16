package com.gpro.consulting.tiers.logistique.coordination.gs.chart.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 13/04/2016
 *
 */
public class MouvementStockChartValue {
	
	private String type;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	
}
