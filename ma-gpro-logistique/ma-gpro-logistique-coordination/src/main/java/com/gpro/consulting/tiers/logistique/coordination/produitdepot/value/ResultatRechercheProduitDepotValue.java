package com.gpro.consulting.tiers.logistique.coordination.produitdepot.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechercheProduitDepotValue {
	
	private Long nombreResultatRecherche;
	
	private Set<ProduitDepotValue> produitdepotvalues = new TreeSet<ProduitDepotValue>();

	public Long getNombreResultatRecherche() {
		return nombreResultatRecherche;
	}

	public void setNombreResultatRecherche(Long nombreResultatRecherche) {
		this.nombreResultatRecherche = nombreResultatRecherche;
	}

	public Set<ProduitDepotValue> getProduitdepotvalues() {
		return produitdepotvalues;
	}

	public void setProduitdepotvalues(Set<ProduitDepotValue> produitdepotvalues) {
		this.produitdepotvalues = produitdepotvalues;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultatRechercheProduitDepotValue [nombreResultaRechercher=").append(nombreResultatRecherche)
				.append(", produitdepotValues=").append(produitdepotvalues).append("]");
		return builder.toString();
	}
	 
	

}
