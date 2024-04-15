package com.gpro.consulting.tiers.commun.coordination.value.elementBase;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheProduitSerialisableValue {

  private Long nombreResultaRechercher;

  private Set < ProduitSerialisableValue > produitSerialisableValues =  new TreeSet<ProduitSerialisableValue>();

public Long getNombreResultaRechercher() {
	return nombreResultaRechercher;
}

public void setNombreResultaRechercher(Long nombreResultaRechercher) {
	this.nombreResultaRechercher = nombreResultaRechercher;
}

public Set<ProduitSerialisableValue> getProduitSerialisableValues() {
	return produitSerialisableValues;
}

public void setProduitSerialisableValues(Set<ProduitSerialisableValue> produitSerialisableValues) {
	this.produitSerialisableValues = produitSerialisableValues;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ResultatRechecheProduitSerialisableValue [nombreResultaRechercher=").append(nombreResultaRechercher)
			.append(", produitSerialisableValues=").append(produitSerialisableValues).append("]");
	return builder.toString();
}
 

}
