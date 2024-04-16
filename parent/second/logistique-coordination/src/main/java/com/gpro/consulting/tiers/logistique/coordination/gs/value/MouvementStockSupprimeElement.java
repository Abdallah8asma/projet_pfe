package com.gpro.consulting.tiers.logistique.coordination.gs.value;

/**
 * 
 * @author Zeineb Medimagh
 * @since 25/11/2016
 */


public class MouvementStockSupprimeElement {

	private Long entiteStockId;
	private Double qteReelle;
	private Long nbRouleauxReel;
	private Long conesReel;
	private Long finconesReel;
	private Double poidsReel;

	public Long getEntiteStockId() {
		return entiteStockId;
	}

	public void setEntiteStockId(Long entiteStockId) {
		this.entiteStockId = entiteStockId;
	}

	public Double getQteReelle() {
		return qteReelle;
	}

	public void setQteReelle(Double qteReelle) {
		this.qteReelle = qteReelle;
	}

	public Long getNbRouleauxReel() {
		return nbRouleauxReel;
	}

	public void setNbRouleauxReel(Long nbRouleauxReel) {
		this.nbRouleauxReel = nbRouleauxReel;
	}

	public Long getConesReel() {
		return conesReel;
	}

	public void setConesReel(Long conesReel) {
		this.conesReel = conesReel;
	}

	public Long getFinconesReel() {
		return finconesReel;
	}

	public void setFinconesReel(Long finconesReel) {
		this.finconesReel = finconesReel;
	}

	public Double getPoidsReel() {
		return poidsReel;
	}

	public void setPoidsReel(Double poidsReel) {
		this.poidsReel = poidsReel;
	}

	@Override
	public String toString() {
		return "MouvementStockSupprimeElement [entiteStockId=" + entiteStockId + ", qteReelle=" + qteReelle
				+ ", nbRouleauxReel=" + nbRouleauxReel + ", conesReel=" + conesReel + ", finconesReel=" + finconesReel
				+ ", poidsReel=" + poidsReel + "]";
	}


}
