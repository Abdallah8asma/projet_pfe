/**
 * @author Zeineb
 * @since 16 nov. 2016 
 */
package com.gpro.consulting.tiers.logistique.coordination.gc.reception.value;

import java.util.Calendar;

public class ProduitReceptionValue implements Comparable<ProduitReceptionValue> {

    private Long id;
	
	private Long commandeVenteId;
	
	private Long produitId;
	
	private Double prixUnitaire;

	private Double quantite;
	
	private Calendar dateLivraison;

	private Long partieIntersseId;
	
	private String PartieIntersseAbbreviation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommandeVenteId() {
		return commandeVenteId;
	}

	public void setCommandeVenteId(Long commandeVenteId) {
		this.commandeVenteId = commandeVenteId;
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
		return "ProduitCommandeValue [id=" + id + ", commandeVenteId=" + commandeVenteId + ", produitId=" + produitId
				+ ", prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", dateLivraison=" + dateLivraison
				+ ", partieIntersseId=" + partieIntersseId + ", PartieIntersseAbbreviation="
				+ PartieIntersseAbbreviation + "]";
	}

	@Override
	public int compareTo(ProduitReceptionValue o) {
		ProduitReceptionValue element = (ProduitReceptionValue) o;
		return element.getProduitId().compareTo(produitId);
	}

	
}
