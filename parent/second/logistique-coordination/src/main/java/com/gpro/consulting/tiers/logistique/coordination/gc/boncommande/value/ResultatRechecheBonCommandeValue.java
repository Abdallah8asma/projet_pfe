package com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zeineb Medimagh
 * @since 17/11/2016
 *
 */
public class ResultatRechecheBonCommandeValue {

	private Long nombreResultaRechercher;

	private List<CommandeVenteValue> listCommandeVente = new ArrayList<CommandeVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<CommandeVenteValue> getListCommandeVente() {
		return listCommandeVente;
	}

	public void setListCommandeVente(List<CommandeVenteValue> listCommandeVente) {
		this.listCommandeVente = listCommandeVente;
	}

	@Override
	public String toString() {
		return "ResultatRechecheBonCommandeValue [nombreResultaRechercher=" + nombreResultaRechercher
				+ ", listCommandeVente=" + listCommandeVente + "]";
	}

}
