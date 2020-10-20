package com.gpro.consulting.tiers.logistique.service.atelier.mise.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService;

/**
 * Implementation des services du bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
@Service
@Transactional
public class MiseServiceImpl implements IMiseService {

	/** Service Domaine */
	@Autowired
	private IMiseDomaine vMiseDomaine;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerMise(MiseValue pMiseValue) {

		return vMiseDomaine.creerMise(pMiseValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerMise(Long pId) {
		vMiseDomaine.supprimerMise(pId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierMise(MiseValue pMise) {
		return vMiseDomaine.modifierMise(pMise);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MiseValue rechercheMiseParId(Long pMise) {

		return vMiseDomaine.rechercheMiseParId(pMise);
	}

	@Override
	public ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue pRechercheMiseMulitCritere) {

		return vMiseDomaine.rechercherMiseMultiCritere(pRechercheMiseMulitCritere);
	}

	@Override
	public List<MiseValue> listerMise() {
		// TODO Auto-generated method stub
		return vMiseDomaine.listerMise();
	}

	@Override
	public TraitementMiseValue getTraitementMiseById(Long id) {
		// TODO Auto-generated method stub
		return vMiseDomaine.getTraitementMiseById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gpro.consulting.tiers.logistique.service.atelier.mise.IMiseService#
	 * listRefMiseParRefBR(java.lang.String)
	 */
	@Override
	public String listRefMiseParRefBR(String referenceBR) {

		return vMiseDomaine.listRefMiseParRefBR(referenceBR);
	}

}
