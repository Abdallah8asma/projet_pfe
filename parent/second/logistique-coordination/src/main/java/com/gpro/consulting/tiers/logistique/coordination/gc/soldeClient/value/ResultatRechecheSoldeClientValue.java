package com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheSoldeClientValue {

	private Long nombreResultaRechercher;
	private List<SoldeClientValue> list = new ArrayList<SoldeClientValue>();
	
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	public List<SoldeClientValue> getList() {
		return list;
	}
	public void setList(List<SoldeClientValue> list) {
		this.list = list;
	}
	
}
