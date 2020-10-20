package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * The Class ResultatRechercheEntiteStockMouvementValue.
 */
public class ResultatRechercheEntiteStockMouvementValue {

	private Long nombreResultaRechercher;

	private Set<EntiteStockMouvementValue> entiteStockMouvement = new TreeSet<EntiteStockMouvementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<EntiteStockMouvementValue> getEntiteStockMouvement() {
		return entiteStockMouvement;
	}

	public void setEntiteStockMouvement(
			Set<EntiteStockMouvementValue> entiteStockMouvement) {
		this.entiteStockMouvement = entiteStockMouvement;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultatRechercheEntiteStockMouvementValue [");
		if (nombreResultaRechercher != null)
			builder.append("nombreResultaRechercher=")
					.append(nombreResultaRechercher).append(", ");
		if (entiteStockMouvement != null)
			builder.append("entiteStockMouvement=")
					.append(entiteStockMouvement);
		builder.append("]");
		return builder.toString();
	}
  
}
