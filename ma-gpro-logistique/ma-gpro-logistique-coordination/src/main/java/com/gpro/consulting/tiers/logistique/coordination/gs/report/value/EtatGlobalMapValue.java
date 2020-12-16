package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;

/**
 * 
 * @author zeineb medimagh
 * @since 23/01/2017
 *
 */
public class EtatGlobalMapValue {
	
	private Long id;
	private Double qteActuelle;
	private Double qteReservee;
	
	private Long nbRouleauxActuels;
	private Long nbRouleauxReserves;
	
	private String referenceArticle;
	private String libelleArticle;
	private String familleArticle;
	private Double prixUnitaire;
	private Double prixTotal;
	
	private Long idArticle;
	
	
	
	public Long getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
	public Double getQteActuelle() {
		return qteActuelle;
	}
	public void setQteActuelle(Double qteActuelle) {
		this.qteActuelle = qteActuelle;
	}
	public Double getQteReservee() {
		return qteReservee;
	}
	public void setQteReservee(Double qteReservee) {
		this.qteReservee = qteReservee;
	}
	public Long getNbRouleauxActuels() {
		return nbRouleauxActuels;
	}
	public void setNbRouleauxActuels(Long nbRouleauxActuels) {
		this.nbRouleauxActuels = nbRouleauxActuels;
	}
	public Long getNbRouleauxReserves() {
		return nbRouleauxReserves;
	}
	public void setNbRouleauxReserves(Long nbRouleauxReserves) {
		this.nbRouleauxReserves = nbRouleauxReserves;
	}
	public String getReferenceArticle() {
		return referenceArticle;
	}
	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}
	public String getLibelleArticle() {
		return libelleArticle;
	}
	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}
	public String getFamilleArticle() {
		return familleArticle;
	}
	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EtatGlobalMapValue [id=" + id + ", qteActuelle=" + qteActuelle + ", qteReservee=" + qteReservee
				+ ", nbRouleauxActuels=" + nbRouleauxActuels + ", nbRouleauxReserves=" + nbRouleauxReserves
				+ ", referenceArticle=" + referenceArticle + ", libelleArticle=" + libelleArticle + ", familleArticle="
				+ familleArticle + ", prixUnitaire=" + prixUnitaire + ", prixTotal=" + prixTotal + "]";
	}
	
}
