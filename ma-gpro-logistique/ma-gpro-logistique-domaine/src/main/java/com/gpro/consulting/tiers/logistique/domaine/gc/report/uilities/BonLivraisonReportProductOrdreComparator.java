package com.gpro.consulting.tiers.logistique.domaine.gc.report.uilities;

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
public class BonLivraisonReportProductOrdreComparator implements Comparator<BonLivraisonReportProductValue>{
	
	@Override
    public int compare(BonLivraisonReportProductValue o1, BonLivraisonReportProductValue o2) {
		 
	    return -1*ComparisonChain.start()
	    		  .compare(o1.getFicheId(), o2.getFicheId())
	        //.compare(o1.getProduitCode(), o2.getProduitCode())
	       // .compare(o1.getChoix(), o2.getChoix())
	        .result();
    }
}
