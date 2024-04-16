package com.gpro.consulting.tiers.logistique.service.gc.ficheclient;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.ResultatRechecheFicheClientValue;

/**
 * Fiche Client Service Interface
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
public interface IFicheClientService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheFicheClientValue rechercherMultiCritere(
			RechercheMulticritereFicheClientValue request);
}
