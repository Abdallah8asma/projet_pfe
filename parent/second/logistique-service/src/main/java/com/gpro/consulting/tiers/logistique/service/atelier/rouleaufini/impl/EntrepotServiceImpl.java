package com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IEntrepotDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IEntrepotService;

/**
 * implementation of {@link IEntrepotService}
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
@Service
@Transactional
public class EntrepotServiceImpl implements IEntrepotService{

	@Autowired
	private IEntrepotDomaine entrepotDomaine;
	

	@Override
	public List<EntrepotValue> listeEntrepot() {

		return entrepotDomaine.listeEntrepot();
	}
	@Override
	public ResultatRechecheEntrepotValue rechercherMultiCritere(RechercheMulticritereEntrepotValue request) {
		
		return entrepotDomaine.rechercherMultiCritere(request);
	}

	@Override
	public EntrepotValue getEntrepotById(Long id) {
		
		return entrepotDomaine.getEntrepotById(id);
	}

	@Override
	public String updateEntrepot(EntrepotValue request) {
		
		return entrepotDomaine.updateEntrepot(request);
	}

	@Override
	public void deleteEntrepot(Long id) {
		
		entrepotDomaine.deleteEntrepot(id);
	}

	@Override
	public String createEntrepot(EntrepotValue request) {
		
		return entrepotDomaine.createEntrepot(request);
	}

}
