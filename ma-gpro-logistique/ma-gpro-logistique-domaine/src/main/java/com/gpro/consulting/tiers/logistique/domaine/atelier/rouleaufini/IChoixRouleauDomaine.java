package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;

/**
 * ChoixRouleau Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
public interface IChoixRouleauDomaine {

	public ChoixRouleauValue getChoixRouleauById(Long id);

	public List<ChoixRouleauValue> listeChoixRouleau();
}
