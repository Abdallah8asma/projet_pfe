package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Calendar;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
 

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class DetBonInventaireValue implements Comparable<DetBonInventaireValue> {

	private Long id;

	private Double quantite;

	private Long produitId;

	private BonInventaireValue bonInventaire;

	private boolean serialisable;

	private String numeroSeries;

	private String description;
	
	private Set<ProduitSerialisableValue> produitsSerialisable;


	

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

	public BonInventaireValue getBonInventaire() {
		return bonInventaire;
	}

	public void setBonInventaire(BonInventaireValue bonInventaire) {
		this.bonInventaire = bonInventaire;
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

	@Override
	public int compareTo(DetBonInventaireValue o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
