package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;

/**
 * Entrepot Persistance Interface
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public interface IEntrepotPersistance {
	
	public ResultatRechecheEntrepotValue rechercherMultiCritere(RechercheMulticritereEntrepotValue request);

	public List<EntrepotValue> listeEntrepot();
	
	public String createEntrepot(EntrepotValue request);

	public void deleteEntrepot(Long id);

	public String updateEntrepot(EntrepotValue request);

	public EntrepotValue getEntrepotById(Long id);

}
