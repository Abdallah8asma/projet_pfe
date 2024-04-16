package com.gpro.consulting.tiers.logistique.service.gc.ficheclient.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.ResultatRechecheFicheClientValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient.IFicheClientDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.ficheclient.IFicheClientService;

/**
 * Implementation of {@link IFicheClientService}
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */
@Service
@Transactional
public class FicheClientServiceImpl implements IFicheClientService{

	@Autowired
	private IFicheClientDomaine ficheClientDomaine;

	@Override
	public ResultatRechecheFicheClientValue rechercherMultiCritere(
			RechercheMulticritereFicheClientValue request) {
		
		
		return ficheClientDomaine.rechercherMultiCritere(request);
	}
}
