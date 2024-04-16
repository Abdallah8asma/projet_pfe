package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportProductValue;

/**
 * This comparator sorts a list of FactureReportProductValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public class FactureReportProductComparatorByOrdre implements Comparator<FactureReportProductValue>{
	
	@Override
    public int compare(FactureReportProductValue o1, FactureReportProductValue o2) {
		 
	    return -1*ComparisonChain.start()
	    		 .compare(o1.getFicheId(), o2.getFicheId())
	        //.compare(o1.getProduitCode(), o2.getProduitCode())
	        //.compare(o1.getChoix(), o2.getChoix())
	        .result();
    }
}
