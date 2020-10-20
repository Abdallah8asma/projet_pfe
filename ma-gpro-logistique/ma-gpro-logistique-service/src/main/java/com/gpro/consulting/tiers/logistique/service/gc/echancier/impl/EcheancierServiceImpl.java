package com.gpro.consulting.tiers.logistique.service.gc.echancier.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.echeancier.IEcheancierDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.echancier.IEchancierService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class EcheancierServiceImpl implements IEchancierService {

	@Autowired
	IEcheancierDomaine echeancierDomaine;

	@Override
	public ResultatRechecheDetailReglementValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementValue request) {
		return echeancierDomaine.rechercherMultiCritere(request);
	}
	
}
