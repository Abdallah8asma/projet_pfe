package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;

/**
 * This comparator sorts a list of DetLivraisonVenteValue by ProduitId and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class DetLivraisonVenteComparator implements Comparator<DetLivraisonVenteValue>{
	
	@Override
    public int compare(DetLivraisonVenteValue o1, DetLivraisonVenteValue o2) {
		 
	    return ComparisonChain.start()
	    		.compare(o1.getFicheId(), o2.getFicheId())
	       // .compare(o1.getProduitId(), o2.getProduitId())
	        //.compare(o1.getChoix(), o2.getChoix())
	        .result();
    }
}
