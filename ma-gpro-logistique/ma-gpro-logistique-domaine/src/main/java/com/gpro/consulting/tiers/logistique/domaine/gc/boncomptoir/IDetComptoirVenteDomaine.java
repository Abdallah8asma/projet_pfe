package com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereDetComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetBonComptoirValue;

/**
 * BonComptoir Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetComptoirVenteDomaine {

	public String create(DetComptoirVenteValue detComptoirVenteValue);

	public DetComptoirVenteValue getById(Long id);

	public String update(DetComptoirVenteValue detComptoirVenteValue);

	public void delete(Long id);
	
	public ResultatRechecheDetBonComptoirValue rechercherMultiCritereDetComptoir(
			RechercheMulticritereDetComptoirValue request);
	
}
