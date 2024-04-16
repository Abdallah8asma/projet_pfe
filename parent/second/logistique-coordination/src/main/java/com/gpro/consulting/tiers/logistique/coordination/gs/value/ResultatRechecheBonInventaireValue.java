package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class ResultatRechecheBonInventaireValue {

	private Long nombreResultaRechercher;

	private Set<BonInventaireValue> list = new TreeSet<BonInventaireValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<BonInventaireValue> getList() {
		return list;
	}

	public void setList(Set<BonInventaireValue> list) {
		this.list = list;
	}

}
