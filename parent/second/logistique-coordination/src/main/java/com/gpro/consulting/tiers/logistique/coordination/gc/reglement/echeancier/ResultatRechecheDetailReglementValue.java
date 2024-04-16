package com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetailReglementValue {

	private Long nombreResultaRechercher;
	private List<ResultatRechecheDetailReglementElementValue> list = new ArrayList <ResultatRechecheDetailReglementElementValue>();
	
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	public List<ResultatRechecheDetailReglementElementValue> getList() {
		return list;
	}
	public void setList(List<ResultatRechecheDetailReglementElementValue> list) {
		this.list = list;
	}

}
