package com.gpro.consulting.tiers.logistique.coordination.gs.value;


/**
 * @author Wahid Gazzah
 * @since 18/05/2016
 */
public class MouvementStockReportValue implements Comparable<MouvementStockReportValue>{
	
	private Long id;
	private String referenceArticle;
	private String designationArticle;
	private String familleArticle;
	
	private Double quantiteAct;
	private Double quantiteLibre;
	private Double besoinOF;
	private Double quantiteReelle;
	private Double reservationOF;
	private Long quantiteOF;
	private String emplacement;
	
	//Mvt Stock Sortie
	private Double prixUnitaire;
	private Long nbRouleauxReel;
	private Long conesReel;
	private Long finconesReel;
	private Double poids;
	
	//Added on 14/11/2016, by Zeineb
	private Double bu;
	
	public int compareTo(MouvementStockReportValue element) {
		return (element.getId().compareTo(id));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getReferenceArticle() {
		return referenceArticle;
	}

	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	public String getDesignationArticle() {
		return designationArticle;
	}

	public void setDesignationArticle(String designationArticle) {
		this.designationArticle = designationArticle;
	}

	public String getFamilleArticle() {
		return familleArticle;
	}

	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
	}

	public Double getQuantiteAct() {
		return quantiteAct;
	}
	public void setQuantiteAct(Double quantiteAct) {
		this.quantiteAct = quantiteAct;
	}
	public Double getQuantiteLibre() {
		return quantiteLibre;
	}
	public void setQuantiteLibre(Double quantiteLibre) {
		this.quantiteLibre = quantiteLibre;
	}
	
	public Double getBesoinOF() {
		return besoinOF;
	}

	public void setBesoinOF(Double besoinOF) {
		this.besoinOF = besoinOF;
	}

	public Double getQuantiteReelle() {
		return quantiteReelle;
	}

	public void setQuantiteReelle(Double quantiteReelle) {
		this.quantiteReelle = quantiteReelle;
	}

	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Double getReservationOF() {
		return reservationOF;
	}

	public void setReservationOF(Double reservationOF) {
		this.reservationOF = reservationOF;
	}

	public Long getQuantiteOF() {
		return quantiteOF;
	}

	public void setQuantiteOF(Long quantiteOF) {
		this.quantiteOF = quantiteOF;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
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

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Double getBu() {
		return bu;
	}

	public void setBu(Double bu) {
		this.bu = bu;
	}
	
}
