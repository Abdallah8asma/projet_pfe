package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;

/**
 * BonLivraison Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetLivraisonVenteDomaine {

	public String create(DetLivraisonVenteValue detLivraisonVenteValue);

	public DetLivraisonVenteValue getById(Long id);

	public String update(DetLivraisonVenteValue detLivraisonVenteValue);

	public void delete(Long id);
	
	public ResultatRechecheDetBonLivraisonValue rechercherMultiCritereDetLivraison(
			RechercheMulticritereDetLivraisonValue request);
	
}
