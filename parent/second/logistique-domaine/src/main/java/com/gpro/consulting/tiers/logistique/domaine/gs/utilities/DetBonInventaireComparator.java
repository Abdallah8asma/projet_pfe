package com.gpro.consulting.tiers.logistique.domaine.gs.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonInventaireValue;

/**
 * This comparator sorts a list of DetLivraisonVenteValue by ProduitId and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class DetBonInventaireComparator implements Comparator<DetBonInventaireValue>{
	
	@Override
    public int compare(DetBonInventaireValue o1, DetBonInventaireValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getProduitId(), o2.getProduitId())
	        //.compare(o1.getChoix(), o2.getChoix())
	        .result();
    }
}
