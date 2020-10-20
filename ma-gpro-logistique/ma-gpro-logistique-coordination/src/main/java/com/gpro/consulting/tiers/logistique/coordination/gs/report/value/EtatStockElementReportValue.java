package com.gpro.consulting.tiers.logistique.coordination.gs.report.value;


/**
 * 
 * @author Wahid Gazzah
 * @since 16/02/2016
 *
 */
public class EtatStockElementReportValue implements Comparable<Object> {
	
	private Long id;
	private String referenceArticle;
	private String libelleArticle;
	private String familleArticle;
	private Double qteActuelle;
	private Double prixUnitaire;
	private Double pmp;
	private Double prixTotal;
	private String emplacement;
	
	private Long finconeActuel;
	private String designationMagasin;
	private String OA ;
	
	@Override
	public int compareTo(Object o) {
		EtatStockElementReportValue element= (EtatStockElementReportValue)o;
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

	public Double getQteActuelle() {
		return qteActuelle;
	}

	public void setQteActuelle(Double qteActuelle) {
		this.qteActuelle = qteActuelle;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getPmp() {
		return pmp;
	}

	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Long getFinconeActuel() {
		return finconeActuel;
	}

	public void setFinconeActuel(Long finconeActuel) {
		this.finconeActuel = finconeActuel;
	}

	public String getDesignationMagasin() {
		return designationMagasin;
	}

	public void setDesignationMagasin(String designationMagasin) {
		this.designationMagasin = designationMagasin;
	}

	public String getOA() {
		return OA;
	}

	public void setOA(String oA) {
		OA = oA;
	}
	
}
