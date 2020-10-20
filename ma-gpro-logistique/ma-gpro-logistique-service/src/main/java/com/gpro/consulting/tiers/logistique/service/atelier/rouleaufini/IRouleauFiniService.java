package com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;

/**
 * RouleauFini Service Interface
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */
public interface IRouleauFiniService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheRouleauFiniValue rechercherMultiCritere(RechercheMulticritereRouleauFiniValue request);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(RechercheMulticritereRouleauFiniStandardValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public RouleauFiniValue getRouleauFiniById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateRouleauFini(RouleauFiniValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteRouleauFini(Long valueOf);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createRouleauFini(RouleauFiniValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheRouleauFiniValue getRouleauFiniByInfoModif(String infoModif);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListRefMiseRouleauNonSortie();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListCodeBarreByRefMiseAndIdBSisNull(String refMise);
	
}
