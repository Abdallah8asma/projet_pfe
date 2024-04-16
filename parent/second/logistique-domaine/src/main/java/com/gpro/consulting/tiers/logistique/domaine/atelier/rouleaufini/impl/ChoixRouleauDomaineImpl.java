package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IChoixRouleauDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;

/**
 * Implementation of {@link IChoixRouleauDomaine}
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class ChoixRouleauDomaineImpl implements IChoixRouleauDomaine{
	
	@Autowired
	private IChoixRouleauPersistance choixRouleauPersitance;

	@Override
	public ChoixRouleauValue getChoixRouleauById(Long id) {
		return choixRouleauPersitance.getChoixRouleauById(id);
	}

	@Override
	public List<ChoixRouleauValue> listeChoixRouleau() {
		return choixRouleauPersitance.listeChoixRouleau();
	}

}
