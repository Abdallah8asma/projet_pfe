package com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value;

import java.util.ArrayList;
import java.util.List;

public class ResultatRechecheProduitBonCommandeValue {
	private Long nombreResultaRechercher;

	private List<ProduitCommandeValue> listProduitCommandeValues = new ArrayList<ProduitCommandeValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<ProduitCommandeValue> getListProduitCommandeValues() {
		return listProduitCommandeValues;
	}

	public void setListProduitCommandeValues(List<ProduitCommandeValue> listProduitCommandeValues) {
		this.listProduitCommandeValues = listProduitCommandeValues;
	}
	


}
