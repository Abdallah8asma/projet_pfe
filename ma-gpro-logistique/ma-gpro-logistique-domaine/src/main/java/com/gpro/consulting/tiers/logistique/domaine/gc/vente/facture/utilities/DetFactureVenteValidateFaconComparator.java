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
public class DetFactureVenteValidateFaconComparator implements Comparator<DetFactureVenteValue>{
	
	@Override
    public int compare(DetFactureVenteValue o1, DetFactureVenteValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getTraitementFaconId(), o2.getTraitementFaconId())
	        .result();
    }
}
