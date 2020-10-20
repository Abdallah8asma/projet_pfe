package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;

/**
 * This comparator sorts a list of DetFactureAchatValue by ProduitId and Choix
 * into ascending order
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 *
 */
public class DetFactureAchatFaconComparator implements Comparator<DetFactureAchatValue> {

	@Override
	public int compare(DetFactureAchatValue o1, DetFactureAchatValue o2) {

		return ComparisonChain.start().compare(o1.getTraitementFaconId(), o2.getTraitementFaconId())
				.compare(o1.getFicheId(), o2.getFicheId()).result();
	}
}
