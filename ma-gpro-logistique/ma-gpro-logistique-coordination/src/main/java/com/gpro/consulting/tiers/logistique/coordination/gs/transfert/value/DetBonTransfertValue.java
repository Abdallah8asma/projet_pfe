package com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value;

import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;

/**
 * @author samer
 * @since
 *
 */
public class DetBonTransfertValue implements Comparable<DetBonTransfertValue> {

	private Long id;

	private Double quantite;

	private Double quantiteRecu;

	private Double quantiteEnvoyer;

	private Long produitId;

	private BonTransfertValue bonTransfert;

	private boolean serialisable;

	private String numeroSeries;

	private String description;

	private Set<ProduitSerialisableValue> produitsSerialisable;

	public Double getQuantiteRecu() {
		return quantiteRecu;
	}

	public void setQuantiteRecu(Double quantiteRecu) {
		this.quantiteRecu = quantiteRecu;
	}

	public Double getQuantiteEnvoyer() {
		return quantiteEnvoyer;
	}

	public void setQuantiteEnvoyer(Double quantiteEnvoyer) {
		this.quantiteEnvoyer = quantiteEnvoyer;
	}

	public Set<ProduitSerialisableValue> getProduitsSerialisable() {
		return produitsSerialisable;
	}

	public void setProduitsSerialisable(Set<ProduitSerialisableValue> produitsSerialisable) {
		this.produitsSerialisable = produitsSerialisable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public boolean isSerialisable() {
		return serialisable;
	}

	public void setSerialisable(boolean serialisable) {
		this.serialisable = serialisable;
	}

	public String getNumeroSeries() {
		return numeroSeries;
	}

	public void setNumeroSeries(String numeroSeries) {
		this.numeroSeries = numeroSeries;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BonTransfertValue getBonTransfert() {
		return bonTransfert;
	}

	public void setBonTransfert(BonTransfertValue bonTransfert) {
		this.bonTransfert = bonTransfert;
	}

	@Override
	public int compareTo(DetBonTransfertValue o) {
		// TODO Auto-generated method stub
		return (o.getId().compareTo(id));
	}

}
