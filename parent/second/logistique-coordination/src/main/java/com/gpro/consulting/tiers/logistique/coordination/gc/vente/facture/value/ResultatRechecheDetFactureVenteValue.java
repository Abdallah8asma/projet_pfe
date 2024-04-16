package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.ArrayList;
import java.util.List;



public class ResultatRechecheDetFactureVenteValue {
private Long nombreResultaRechercher;
	
	private List<DetFactureVenteValue> list = new ArrayList<DetFactureVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetFactureVenteValue> getList() {
		return list;
	}

	public void setList(List<DetFactureVenteValue> list) {
		this.list = list;
	}

	
	


}
