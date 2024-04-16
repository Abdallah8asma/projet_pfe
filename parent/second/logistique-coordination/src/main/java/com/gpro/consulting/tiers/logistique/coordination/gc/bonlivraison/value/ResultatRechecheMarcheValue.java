package com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zeineb Medimagh
 * @since 17/11/2016
 *
 */
public class ResultatRechecheMarcheValue {
	
	private Long nombreResultaRechercher;
	
	private List<MarcheValue> listMarche = new ArrayList<MarcheValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	

	public List<MarcheValue> getListMarche() {
		return listMarche;
	}

	public void setListMarche(List<MarcheValue> listMarche) {
		this.listMarche = listMarche;
	}

	@Override
	public String toString() {
		return "ResultatRechecheBonCommandeValue [nombreResultaRechercher=" + nombreResultaRechercher
				+ ", listMarche=" + listMarche + "]";
	}
	
	
}
