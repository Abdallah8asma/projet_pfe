package com.gpro.consulting.tiers.logistique.coordination.gc.reception.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zeineb Medimagh
 * @since 17/11/2016
 *
 */
public class ResultatRechecheBonReceptionValue {

	private Long nombreResultaRechercher;

	private List<ReceptionVenteValue> listReceptionVente = new ArrayList<ReceptionVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<ReceptionVenteValue> getListReceptionVente() {
		return listReceptionVente;
	}

	public void setListReceptionVente(List<ReceptionVenteValue> listReceptionVente) {
		this.listReceptionVente = listReceptionVente;
	}

}
