package com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient;

import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.ResultatRechecheFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.ResultatRechecheFicheGroupeClientValue;

/**
 * Fiche Client Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public interface IFicheClientDomaine {

	public ResultatRechecheFicheClientValue rechercherMultiCritere(
			RechercheMulticritereFicheClientValue request);

	public ResultatRechecheFicheGroupeClientValue rechercherMultiCritereGroupeClient(
			RechercheMulticritereFicheGroupeClientValue request);

}
