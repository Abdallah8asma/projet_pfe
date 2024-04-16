package com.gpro.consulting.tiers.logistique.domaine.gc.achat.fichefournisseur;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.RechercheMulticritereFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.ResultatRechecheFicheFournisseurValue;

/**
 * Fiche Client Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public interface IFicheFournisseurDomaine {

	public ResultatRechecheFicheFournisseurValue rechercherMultiCritere(
			RechercheMulticritereFicheFournisseurValue request);



}
