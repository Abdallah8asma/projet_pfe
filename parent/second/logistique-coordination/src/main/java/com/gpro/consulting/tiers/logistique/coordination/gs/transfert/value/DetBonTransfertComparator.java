package com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

/**
 * This comparator sorts a list of DetLivraisonVenteValue by ProduitId and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class DetBonTransfertComparator implements Comparator<DetBonTransfertValue> {

	@Override
	public int compare(DetBonTransfertValue o1, DetBonTransfertValue o2) {

		return ComparisonChain.start().compare(o1.getProduitId(), o2.getProduitId())
				// .compare(o1.getChoix(), o2.getChoix())
				.result();
	}
}
