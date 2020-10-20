package com.gpro.consulting.tiers.logistique.coordination.gs.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheEntiteStockStockValue {

	private Long nombreResultaRechercher;

	private Set<EntiteStockValue> entiteStock = new TreeSet<EntiteStockValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<EntiteStockValue> getEntiteStock() {
		return entiteStock;
	}

	public void setEntiteStock(Set<EntiteStockValue> entiteStock) {
		this.entiteStock = entiteStock;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultatRechecheEntiteStockStockValue [");
		if (nombreResultaRechercher != null)
			builder.append("nombreResultaRechercher=")
					.append(nombreResultaRechercher).append(", ");
		if (entiteStock != null)
			builder.append("entiteStock=").append(entiteStock);
		builder.append("]");
		return builder.toString();
	}

}
