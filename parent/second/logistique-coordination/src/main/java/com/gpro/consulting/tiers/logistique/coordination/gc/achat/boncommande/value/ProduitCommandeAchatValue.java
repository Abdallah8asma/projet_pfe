
package com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value;

import java.util.Calendar;

public class ProduitCommandeAchatValue implements Comparable<ProduitCommandeAchatValue> {

	private Long id;

	private Long commandeAchatId;

	private Long produitId;

	private Double prixUnitaire;

	private Double quantite;

	private Calendar dateLivraison;

	private Long partieIntersseId;

	private String PartieIntersseAbbreviation;

	private Long taxeId;
	
	
	private String unite;
	
	


	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Long getTaxeId() {
		return taxeId;
	}

	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommandeAchatId() {
		return commandeAchatId;
	}

	public void setCommandeAchatId(Long commandeAchatId) {
		this.commandeAchatId = commandeAchatId;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public String getPartieIntersseAbbreviation() {
		return PartieIntersseAbbreviation;
	}

	public void setPartieIntersseAbbreviation(String partieIntersseAbbreviation) {
		PartieIntersseAbbreviation = partieIntersseAbbreviation;
	}

	@Override
	public String toString() {
		return "ProduitCommandeValue [id=" + id + ", commandeAchatId=" + commandeAchatId + ", produitId=" + produitId
				+ ", prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", dateLivraison=" + dateLivraison
				+ ", partieIntersseId=" + partieIntersseId + ", PartieIntersseAbbreviation="
				+ PartieIntersseAbbreviation + "]";
	}

	@Override
	public int compareTo(ProduitCommandeAchatValue o) {
		ProduitCommandeAchatValue element = (ProduitCommandeAchatValue) o;
		return element.getProduitId().compareTo(produitId);
	}

}
