package com.gpro.consulting.tiers.logistique.service.gc.achat.echancier.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.RechercheMulticritereDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.echeancier.ResultatRechecheDetailReglementAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.echeancier.IEcheancierFournisseurDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.echancier.IEcheancierFournisseurService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class EcheancierFournisseurServiceImpl implements IEcheancierFournisseurService {

	@Autowired
	IEcheancierFournisseurDomaine echeancierDomaine;

	@Override
	public ResultatRechecheDetailReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereDetailReglementAchatValue request) {
		return echeancierDomaine.rechercherMultiCritere(request);
	}
	
}
