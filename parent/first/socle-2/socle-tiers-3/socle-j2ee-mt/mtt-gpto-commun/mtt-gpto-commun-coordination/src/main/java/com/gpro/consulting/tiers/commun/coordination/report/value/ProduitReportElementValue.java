package com.gpro.consulting.tiers.commun.coordination.report.value;

/**
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public class ProduitReportElementValue {
	
	private String reference;
	private String designation ;
	private Long familleId; 
	private Long sousfamilleId;
	private Long partieInteresseeId;
	private Long siteId;
	private Double prix;
	private String etat;
	
	//conversion id / designation
	private String partieInteressee;
	private String sousfamille;
	private String famille; 
	private String site;
	
	private Double quantite;
	
	private Double prixSpecial;
	
	public Double getPrixspecial() {
		return prixSpecial;
	}
	public void setPrixspecial(Double prixspecial) {
		this.prixSpecial = prixspecial;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the famille
	 */
	public String getFamille() {
		return famille;
	}
	/**
	 * @param famille the famille to set
	 */
	public void setFamille(String famille) {
		this.famille = famille;
	}
	/**
	 * @return the sousfamille
	 */
	public String getSousfamille() {
		return sousfamille;
	}
	/**
	 * @param sousfamille the sousfamille to set
	 */
	public void setSousfamille(String sousfamille) {
		this.sousfamille = sousfamille;
	}
	/**
	 * @return the partieInteressee
	 */
	public String getPartieInteressee() {
		return partieInteressee;
	}
	/**
	 * @param partieInteressee the partieInteressee to set
	 */
	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}
	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}
	/**
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}
	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return the familleId
	 */
	public Long getFamilleId() {
		return familleId;
	}
	/**
	 * @param familleId the familleId to set
	 */
	public void setFamilleId(Long familleId) {
		this.familleId = familleId;
	}
	/**
	 * @return the sousfamilleId
	 */
	public Long getSousfamilleId() {
		return sousfamilleId;
	}
	/**
	 * @param sousfamilleId the sousfamilleId to set
	 */
	public void setSousfamilleId(Long sousfamilleId) {
		this.sousfamilleId = sousfamilleId;
	}
	/**
	 * @return the partieInteresseeId
	 */
	public Long getPartieInteresseeId() {
		return partieInteresseeId;
	}
	/**
	 * @param partieInteresseeId the partieInteresseeId to set
	 */
	public void setPartieInteresseeId(Long partieInteresseeId) {
		this.partieInteresseeId = partieInteresseeId;
	}
	/**
	 * @return the siteId
	 */
	public Long getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	
}
