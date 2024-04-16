package com.gpro.consulting.tiers.logistique.domaine.gc.report.uilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportTraitementFaconValue;

/**
 * This comparator sorts a list of BonLivraisonReportProductValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class BonLivraisonReportTraitementFaconComparator implements Comparator<BonLivraisonReportTraitementFaconValue>{
	
	@Override
    public int compare(BonLivraisonReportTraitementFaconValue o1, BonLivraisonReportTraitementFaconValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getTraitementId(), o2.getTraitementId())
	        .result();
    }
}
