package com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereEntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheEntrepotValue;

/**
 * Entrepot Service Interface
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public interface IEntrepotService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheEntrepotValue rechercherMultiCritere(RechercheMulticritereEntrepotValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public EntrepotValue getEntrepotById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateEntrepot(EntrepotValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteEntrepot(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createEntrepot(EntrepotValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<EntrepotValue> listeEntrepot();
}
