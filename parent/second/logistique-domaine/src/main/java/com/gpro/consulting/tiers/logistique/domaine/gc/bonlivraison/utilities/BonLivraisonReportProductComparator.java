package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.bonlivraison.value.BonLivraisonReportProductValue;

/**
 * This comparator sorts a list of BonLivraisonReportProductValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class BonLivraisonReportProductComparator implements Comparator<BonLivraisonReportProductValue>{
	
	@Override
    public int compare(BonLivraisonReportProductValue o1, BonLivraisonReportProductValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getProduitCode(), o2.getProduitCode())
	        .compare(o1.getChoix(), o2.getChoix())
	        .result();
    }
}
