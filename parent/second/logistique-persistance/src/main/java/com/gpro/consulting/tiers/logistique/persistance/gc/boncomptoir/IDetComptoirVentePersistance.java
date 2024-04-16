package com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereDetComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheDetailBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;

/**
 * DetComptoirVente Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetComptoirVentePersistance {
	
	public String create(DetComptoirVenteValue detComptoirVenteValue);

	public DetComptoirVenteValue getById(Long id);

	public String update(DetComptoirVenteValue detComptoirVenteValue);

	public void delete(Long id);

	public DetComptoirVenteValue getBylivraisonVenteAndProduit(
			Long livraisonVenteId, Long produitId, String choix);
	
	public ResultatRechecheDetailBonComptoirValue getDetailComptoirByFnReportingClientProduitDate(
			RechercheMulticritereFnReportingtValue request);
	
	public void setTraitementPU(Long id , Double nouveauPU, Long idFiche);
	
	public ResultatRechecheDetBonComptoirValue getResultatRechercheMcDetComptoirValue(
			RechercheMulticritereDetComptoirValue request);
	
	
}
