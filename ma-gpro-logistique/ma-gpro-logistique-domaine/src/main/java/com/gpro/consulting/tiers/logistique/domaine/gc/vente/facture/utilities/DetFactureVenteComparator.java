package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;

/**
 * This comparator sorts a list of DetFactureVenteValue by ProduitId and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class DetFactureVenteComparator implements Comparator<DetFactureVenteValue>{
	
	@Override
    public int compare(DetFactureVenteValue o1, DetFactureVenteValue o2) {
		 
		
		if(o1.getChoix() != null  && o2.getChoix() != null && o1.getProduitId() != null && o2.getProduitId() != null)
	    return ComparisonChain.start()
	        .compare(o1.getProduitId(), o2.getProduitId())
	        .compare(o1.getChoix(), o2.getChoix())
	        .result();
		else return 0;
    }
}
