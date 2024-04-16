package com.gpro.consulting.logistique.coordination.gc.boncomptoir.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetBonComptoirValue {

	private Long nombreResultaRechercher;

	private List<DetComptoirVenteValue> listDetailComptoir = new ArrayList<DetComptoirVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetComptoirVenteValue> getListDetailComptoir() {
		return listDetailComptoir;
	}

	public void setListDetailComptoir(List<DetComptoirVenteValue> listDetailComptoir) {
		this.listDetailComptoir = listDetailComptoir;
	}

}
