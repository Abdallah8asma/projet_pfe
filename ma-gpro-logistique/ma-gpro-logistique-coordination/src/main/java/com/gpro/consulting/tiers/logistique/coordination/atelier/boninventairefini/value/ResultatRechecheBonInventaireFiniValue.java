package com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ghazi Atroussi
 * @since 18/12/2016
 *
 */
public class ResultatRechecheBonInventaireFiniValue {
	
	private Long nombreResultaRechercher;
	
	private Set<BonInventaireFiniValue> list = new TreeSet <BonInventaireFiniValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<BonInventaireFiniValue> getList() {
		return list;
	}

	public void setList(Set<BonInventaireFiniValue> list) {
		this.list = list;
	}

}
