package com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value;

import java.util.Comparator;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;

/**
 * ElementResultatRechecheRouleauStandard Comparator to the sort by: REFERENCE or METRAGE or PRIX_UNITAIRE
 * 
 * @author Wahid Gazzah
 * @since 22/01/2016
 *
 */
public class ElementResultatRechecheRouleauStandardComparator implements Comparator<ElementResultatRechecheRouleauStandardValue>{
	
	private String orderBy;
	
	@Override
	public int compare(ElementResultatRechecheRouleauStandardValue v1,
			ElementResultatRechecheRouleauStandardValue v2) {
	    switch (orderBy) {
	        case IConstanteLogistique.ORDER_BY_REFERENCE:
	        	return  v1.getReferenceProduit().compareTo(v2.getReferenceProduit());
			case IConstanteLogistique.ORDER_BY_METRAGE:
				return  v2.getMetrage().compareTo(v1.getMetrage());
			case IConstanteLogistique.ORDER_BY_PRIX_UNITAIRE:
				return  v2.getPrixUnitaire().compareTo(v1.getPrixUnitaire());
	
		    }
		return 0;
	}
	
	public void setOrderBy(String orderBy) {
	    this.orderBy = orderBy;
	}

}
