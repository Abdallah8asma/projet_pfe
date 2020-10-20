package com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

 /* @author Hajer Amri
 * @since 07/02/2017
 *
 */
public class SituationReportingValueComparator implements Comparator<SituationReportingValue>{
	
	@Override
    public int compare(SituationReportingValue o1, SituationReportingValue o2) {
		 
	    return ComparisonChain.start()
		    .compare(o2.getSoldeActuel(), o1.getSoldeActuel())
	        .compare(o1.getClientReference(), o2.getClientReference())
	        .result();
    }
}
