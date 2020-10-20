package com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetailReglementAchatValue {

	private Long nombreResultaRechercher;
	private List<ResultatRechecheDetailReglementAchatElementValue> list = new ArrayList <ResultatRechecheDetailReglementAchatElementValue>();
	
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	public List<ResultatRechecheDetailReglementAchatElementValue> getList() {
		return list;
	}
	public void setList(List<ResultatRechecheDetailReglementAchatElementValue> list) {
		this.list = list;
	}

}
