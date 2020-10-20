package com.gpro.consulting.logistique.coordination.gc.boncomptoir.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetailBonComptoirValue {

	private Long nombreResultaRechercher;

	private List<DetComptoirVenteValue> listDetailComptoire = new ArrayList<DetComptoirVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetComptoirVenteValue> getListDetailComptoire() {
		return listDetailComptoire;
	}

	public void setListDetailComptoire(List<DetComptoirVenteValue> listDetailComptoire) {
		this.listDetailComptoire = listDetailComptoire;
	}

}
