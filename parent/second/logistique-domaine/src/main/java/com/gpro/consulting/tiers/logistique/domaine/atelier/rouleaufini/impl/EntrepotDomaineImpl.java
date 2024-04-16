package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IEntrepotDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IEntrepotPersistance;

/**
 * Implementation of {@link IEntrepotDomaine}
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */

@Component
public class EntrepotDomaineImpl implements IEntrepotDomaine{
	
	@Autowired
	private IEntrepotPersistance entrepotPersitance;

	@Override
	public ResultatRechecheEntrepotValue rechercherMultiCritere(
			RechercheMulticritereEntrepotValue request) {
		
		return entrepotPersitance.rechercherMultiCritere(request);
	}

	@Override
	public EntrepotValue getEntrepotById(Long id) {
		
		return entrepotPersitance.getEntrepotById(id);
	}

	@Override
	public String updateEntrepot(EntrepotValue request) {
		
		return entrepotPersitance.updateEntrepot(request);
	}

	@Override
	public void deleteEntrepot(Long id) {
		
		entrepotPersitance.deleteEntrepot(id);
	}

	@Override
	public String createEntrepot(EntrepotValue request) {
		
		return entrepotPersitance.createEntrepot(request);
	}

	@Override
	public List<EntrepotValue> listeEntrepot() {
		return entrepotPersitance.listeEntrepot();
	}
}
