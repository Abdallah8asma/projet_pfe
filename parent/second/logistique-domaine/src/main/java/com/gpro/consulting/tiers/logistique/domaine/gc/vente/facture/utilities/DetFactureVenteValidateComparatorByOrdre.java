package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;

/**
 * This comparator sorts a list of DetFactureVenteValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class DetFactureVenteValidateComparatorByOrdre implements Comparator<DetFactureVenteValue>{
	
	@Override
    public int compare(DetFactureVenteValue o1, DetFactureVenteValue o2) {
		 
	    return -1*ComparisonChain.start()
	            .compare(o1.getFicheId(), o2.getFicheId())
	        //.compare(o1.getProduitReference(), o2.getProduitReference())
	       // .compare(o1.getChoix(), o2.getChoix())
	        .result();
    }
}
