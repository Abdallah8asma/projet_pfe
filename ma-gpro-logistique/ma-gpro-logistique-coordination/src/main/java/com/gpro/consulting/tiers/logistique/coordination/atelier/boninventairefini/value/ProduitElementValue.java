package com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class ProduitElementValue {

	private String refProd;
	private Long produitId;
	private String produit;
	private Double quantite;
	private String unite;
	private Long nbrColis;
	private Double prixUnitaireHT;
	private Double remise;
	private Double prixTotalHT;
	
	public String getRefProd() {
		return refProd;
	}
	public void setRefProd(String refProd) {
		this.refProd = refProd;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public String getProduit() {
		return produit;
	}
	public void setProduit(String produit) {
		this.produit = produit;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public Long getNbrColis() {
		return nbrColis;
	}
	public void setNbrColis(Long nbrColis) {
		this.nbrColis = nbrColis;
	}
	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}
	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}
	public Double getRemise() {
		return remise;
	}
	public void setRemise(Double remise) {
		this.remise = remise;
	}
	public Double getPrixTotalHT() {
		return prixTotalHT;
	}
	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}
	
}
