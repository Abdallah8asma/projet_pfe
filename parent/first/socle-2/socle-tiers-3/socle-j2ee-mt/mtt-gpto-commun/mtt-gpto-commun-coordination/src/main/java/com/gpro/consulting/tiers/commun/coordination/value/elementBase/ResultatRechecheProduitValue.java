package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheProduitValue {

  private Long nombreResultaRechercher;

  private Set < ProduitValue > produitValues =  new TreeSet<ProduitValue>();

public Long getNombreResultaRechercher() {
	return nombreResultaRechercher;
}

public void setNombreResultaRechercher(Long nombreResultaRechercher) {
	this.nombreResultaRechercher = nombreResultaRechercher;
}

public Set<ProduitValue> getProduitValues() {
	return produitValues;
}

public void setProduitValues(Set<ProduitValue> produitValues) {
	this.produitValues = produitValues;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ResultatRechecheProduitValue [nombreResultaRechercher=").append(nombreResultaRechercher)
			.append(", produitValues=").append(produitValues).append("]");
	return builder.toString();
}
 

}
