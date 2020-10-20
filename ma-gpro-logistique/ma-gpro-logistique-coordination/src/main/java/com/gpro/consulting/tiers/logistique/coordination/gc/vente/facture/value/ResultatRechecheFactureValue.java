package com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class ResultatRechecheFactureValue {
	
	private Long nombreResultaRechercher;
	
	private Set<FactureVenteValue> list = new TreeSet <FactureVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<FactureVenteValue> getList() {
		return list;
	}

	public void setList(Set<FactureVenteValue> list) {
		this.list = list;
	}
	
	

}
