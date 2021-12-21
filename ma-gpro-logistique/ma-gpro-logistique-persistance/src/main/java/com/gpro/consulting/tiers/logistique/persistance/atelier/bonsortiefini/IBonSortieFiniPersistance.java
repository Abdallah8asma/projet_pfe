package com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniOptimizedValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.ResultatRechecheLivraisonVenteFnReportingVue;

/**
 * BonSortieFini Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
public interface IBonSortieFiniPersistance {

	public String createBonSortieFini(BonSortieFiniValue request);

	public BonSortieFiniValue getBonSortieFiniById(Long id);

	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(RechercheMulticritereBonSortieFiniValue request);

	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	public void deleteBonSortieFini(Long id);

	public List<BonSortieFiniValue> getListByBonSortieList(List<String> refBonSortieList);

	public List<BonSortieFiniValue> getAll();

	
	public ResultatRechecheBonSortieFiniValue getBSByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request);

	public List<BonSortieFiniOptimizedValue> getBonSortieEnCours();
	
	public BonSortieFiniOptimizedValue getBonSortieFiniOptimizedById(Long id);
	
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritereOptimized(RechercheMulticritereBonSortieFiniValue request);

}
