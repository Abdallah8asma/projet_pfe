package com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheDetFactureAchatValue {
	/** The nombre resulta rechercher. */
	private Long nombreResultaRechercher;

	/** The list. */
	private List<DetFactureAchatValue> list = new ArrayList<DetFactureAchatValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetFactureAchatValue> getList() {
		return list;
	}

	public void setList(List<DetFactureAchatValue> list) {
		this.list = list;
	}

	

}
