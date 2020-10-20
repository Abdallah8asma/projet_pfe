package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;

/**
 * BonLivraison Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetLivraisonVenteService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(DetLivraisonVenteValue detLivraisonVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public DetLivraisonVenteValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetLivraisonVenteValue detLivraisonVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheDetBonLivraisonValue rechercherMultiCritereDetLivraison(
			RechercheMulticritereDetLivraisonValue request);

}
