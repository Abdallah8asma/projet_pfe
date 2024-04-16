package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class ResultatRechecheBonLivraisonValue {
	
	private Long nombreResultaRechercher;
	
	private Set<LivraisonVenteValue> list = new TreeSet <LivraisonVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<LivraisonVenteValue> getList() {
		return list;
	}

	public void setList(Set<LivraisonVenteValue> list) {
		this.list = list;
	}

}
