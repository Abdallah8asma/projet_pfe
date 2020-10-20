package com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;


public class FicheClientComparator implements Comparator<FicheClientElementValue> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(FicheClientElementValue o1, FicheClientElementValue o2) {
		// TODO Auto-generated method stub
		return ComparisonChain.start()
		        .compare(o1.getDate(), o2.getDate())
		        .compare(o1.getReferenceComparator(), o2.getReferenceComparator())
		        .result();
	    }
	}

