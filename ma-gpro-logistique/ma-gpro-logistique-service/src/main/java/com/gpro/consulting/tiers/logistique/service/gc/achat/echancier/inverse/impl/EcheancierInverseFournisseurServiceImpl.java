package com.gpro.consulting.tiers.logistique.service.gc.achat.echancier.inverse.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.echeancier.inverse.IEcheancierInverseFournisseurDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.echancier.inverse.IEcheancierInverseFournisseurService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class EcheancierInverseFournisseurServiceImpl implements IEcheancierInverseFournisseurService {

	@Autowired
	IEcheancierInverseFournisseurDomaine echeancierDomaine;

	@Override
	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request) {
		return echeancierDomaine.rechercherMultiCritere(request);
	}
	
}
