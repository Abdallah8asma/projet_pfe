package com.gpro.consulting.tiers.logistique.service.atelier.prepMoule.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.PrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.RechercheMulticriterePrepMouleValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value.ResultatRecherchePrepMouleValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.prepMoule.IPrepMouleDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.prepMoule.IPrepMouleService;

/**
 * Implementation des services du bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
@Service
@Transactional
public class PrepMouleServiceImpl implements IPrepMouleService {

	/** Service Domaine */
	@Autowired
	private IPrepMouleDomaine vPrepMouleDomaine;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerPrepMoule(PrepMouleValue pPrepMouleValue) {

		return vPrepMouleDomaine.creerPrepMoule(pPrepMouleValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerPrepMoule(Long pId) {
		vPrepMouleDomaine.supprimerPrepMoule(pId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierPrepMoule(PrepMouleValue pPrepMoule) {
		return vPrepMouleDomaine.modifierPrepMoule(pPrepMoule);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrepMouleValue recherchePrepMouleParId(Long pPrepMoule) {

		return vPrepMouleDomaine.recherchePrepMouleParId(pPrepMoule);
	}

	@Override
	public ResultatRecherchePrepMouleValue rechercherPrepMouleMultiCritere(
			RechercheMulticriterePrepMouleValue pRecherchePrepMouleMulitCritere) {

		return vPrepMouleDomaine.rechercherPrepMouleMultiCritere(pRecherchePrepMouleMulitCritere);
	}

	@Override
	public List<PrepMouleValue> listerPrepMoule() {
		// TODO Auto-generated method stub
		return vPrepMouleDomaine.listerPrepMoule();
	}



	@Override
	public String listRefPrepMouleParRefBR(String referenceBR) {

		return vPrepMouleDomaine.listRefPrepMouleParRefBR(referenceBR);
	}

	@Override
	public List<PrepMouleValue> getReferencePrepMoule() {
		// TODO Auto-generated method stub
		return vPrepMouleDomaine.getReferencePrepMoule();
	}

	@Override
	public PrepMouleValue recherchePrepMouleParReference(Long reference) {
		
		return vPrepMouleDomaine.recherchePrepMouleParReference(reference);
	}

}