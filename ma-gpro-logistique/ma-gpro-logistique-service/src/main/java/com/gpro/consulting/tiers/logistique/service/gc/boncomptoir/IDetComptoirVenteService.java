package com.gpro.consulting.tiers.logistique.service.gc.boncomptoir;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereDetComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetBonComptoirValue;

/**
 * BonComptoir Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetComptoirVenteService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(DetComptoirVenteValue detComptoirVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public DetComptoirVenteValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetComptoirVenteValue detComptoirVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheDetBonComptoirValue rechercherMultiCritereDetComptoir(
			RechercheMulticritereDetComptoirValue request);

}
