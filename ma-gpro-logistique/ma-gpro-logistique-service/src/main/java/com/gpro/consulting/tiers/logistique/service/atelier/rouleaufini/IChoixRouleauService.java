package com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;

/**
 * ChoixRouleau Service Interface
 * 
 * @author Wahid Gazzah
 * @since 08/02/2012
 *
 */
public interface IChoixRouleauService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ChoixRouleauValue getChoixRouleauById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<ChoixRouleauValue> listeChoixRouleau();
}
