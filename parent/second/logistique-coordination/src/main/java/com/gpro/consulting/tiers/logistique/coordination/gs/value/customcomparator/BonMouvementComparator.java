package com.gpro.consulting.tiers.logistique.coordination.gs.value.customcomparator;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;


public class BonMouvementComparator implements Comparator<BonMouvementStockValue> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(BonMouvementStockValue o1, BonMouvementStockValue o2) {
		// TODO Auto-generated method stub
	/*	return ComparisonChain.start()
		        .compare(o1.getDate(), o2.getDate())
		        .compare(o1.getId(), o2.getId())
		        .result();*/
		
		
		return ComparisonChain.start()
		      
		        .compare(o1.getId(), o2.getId())
		        .result();
		
	    }
	}

