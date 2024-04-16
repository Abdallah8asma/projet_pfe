package com.gpro.consulting.tiers.logistique.coordination.gc.report.boncommande.value;

/**
 * 
 * @author Zeineb Medimagh
 * @since 18/11/2016
 *
 */
public class BonCommandeReportProductValue implements Comparable<BonCommandeReportProductValue> {

	private String referenceProduit;
	private String designationProduit;
	private String sousFamilleProduit;
	private Double quantite;
	private Double pu;
	private Double prixTotal;
	private Double remise;

	// added on 03/04/2018
	private Double montantTaxeTVA;
	private Integer tauxTvaArt;

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public Double getMontantTaxeTVA() {
		return montantTaxeTVA;
	}

	public void setMontantTaxeTVA(Double montantTaxeTVA) {
		this.montantTaxeTVA = montantTaxeTVA;
	}

	public Integer getTauxTvaArt() {
		return tauxTvaArt;
	}

	public void setTauxTvaArt(Integer tauxTvaArt) {
		this.tauxTvaArt = tauxTvaArt;
	}

	/**
	 * 
	 */
	public BonCommandeReportProductValue() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param referenceProduit
	 * @param designationProduit
	 * @param sousFamilleProduit
	 * @param quantite
	 * @param pu
	 */

	public String getReferenceProduit() {
		return referenceProduit;
	}

	/**
	 * @param referenceProduit
	 * @param designationProduit
	 * @param sousFamilleProduit
	 * @param quantite
	 * @param pu
	 * @param prixTotal
	 */
	public BonCommandeReportProductValue(String referenceProduit, String designationProduit, String sousFamilleProduit,
			Double quantite, Double pu, Double prixTotal) {
		super();
		this.referenceProduit = referenceProduit;
		this.designationProduit = designationProduit;
		this.sousFamilleProduit = sousFamilleProduit;
		this.quantite = quantite;
		this.pu = pu;
		this.prixTotal = prixTotal;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	public String getDesignationProduit() {
		return designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	public String getSousFamilleProduit() {
		return sousFamilleProduit;
	}

	public void setSousFamilleProduit(String sousFamilleProduit) {
		this.sousFamilleProduit = sousFamilleProduit;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getPu() {
		return pu;
	}

	public void setPu(Double pu) {
		this.pu = pu;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	@Override
	public int compareTo(BonCommandeReportProductValue o) {
		BonCommandeReportProductValue element = (BonCommandeReportProductValue) o;
		return element.getReferenceProduit().compareTo(referenceProduit);
	}

}
