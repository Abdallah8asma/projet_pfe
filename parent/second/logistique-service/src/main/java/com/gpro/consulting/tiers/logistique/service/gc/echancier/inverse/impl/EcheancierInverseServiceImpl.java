package com.gpro.consulting.tiers.logistique.service.gc.echancier.inverse.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.ResultatRechecheDetailReglementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.echeancier.inverse.IEcheancierInverseDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.echancier.inverse.IEchancierInverseService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class EcheancierInverseServiceImpl implements IEchancierInverseService {

	@Autowired
	IEcheancierInverseDomaine echeancierDomaine;

	@Override
	public ResultatRechecheDetailReglementValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementValue request) {
		return echeancierDomaine.rechercherMultiCritere(request);
	}
	
}
