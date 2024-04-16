/**
 * 
 */
package com.gpro.consulting.tiers.logistique.service.atelier.bonReception.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.ITraitementDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.bonReception.ITraitementService;

/**
 * Implementation des services des Traitements
 * 
 * @author Ameni
 */
@Service
@Transactional
public class TraitementServiceImpl implements ITraitementService {

	/** Service Domaine */
	@Autowired
	private ITraitementDomaine vTraitementDomaine;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String creerTraitement(TraitementValue pTraitementValue) {
		return vTraitementDomaine.creerTraitement(pTraitementValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void supprimerTraitement(Long pId) {
		vTraitementDomaine.supprimerTraitement(pId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifierTraitement(TraitementValue pTraitementValue) {
		return vTraitementDomaine.modifierTraitement(pTraitementValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TraitementValue> listeTraitement() {
		return vTraitementDomaine.listeTraitement();
	}

}
