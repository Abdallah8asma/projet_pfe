package com.gpro.consulting.tiers.logistique.domaine.gs.report.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gs.report.value.EtatMouvementElementReportValue;

/**
 * This comparator sorts a list of EtatMouvementElementReportValue by bonMouvementStockDate 
 * and bonMouvementStockNumero into ascending order
 * 
 * @author Wahid Gazzah
 * @since 24/02/2016
 *
 */
public class EtatMouvementElementReportComparator implements Comparator<EtatMouvementElementReportValue>{
	
	@Override
    public int compare(EtatMouvementElementReportValue o1, EtatMouvementElementReportValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getBonMouvementStockDate(), o2.getBonMouvementStockDate())
	        .compare(o1.getBonMouvementStockNumero(), o2.getBonMouvementStockNumero())
	        .result();
    }
}
