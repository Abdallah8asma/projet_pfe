package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;

/**
 * Entrepot Persistance Domaine
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public interface IEntrepotDomaine {
	
	public ResultatRechecheEntrepotValue rechercherMultiCritere(RechercheMulticritereEntrepotValue request);

	public List<EntrepotValue> listeEntrepot();
	
	public EntrepotValue getEntrepotById(Long id);

	public String updateEntrepot(EntrepotValue request);

	public void deleteEntrepot(Long id);

	public String createEntrepot(EntrepotValue request);

}
