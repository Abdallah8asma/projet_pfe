package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;

/**
 * This comparator sorts a list of DetFactureAchatValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Hamdi Etteieb
 * @since 29/09/2018
 *
 */
public class DetFactureAchatValidateComparator implements Comparator<DetFactureAchatValue> {

	@Override
	public int compare(DetFactureAchatValue o1, DetFactureAchatValue o2) {

		return ComparisonChain.start().compare(o1.getProduitReference(), o2.getProduitReference())
				// .compare(o1.getChoix(), o2.getChoix())
				.result();
	}
}
