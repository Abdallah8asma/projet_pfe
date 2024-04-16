package com.gpro.consulting.tiers.logistique.persistance.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;

/**
 * BonStock Persistance interface
 * 
 * @author Samer Hassen
 * @since 21/05/2020
 *
 */
public interface IBonStockPersistance {

	public ResultatRechecheBonStockValue rechercherMultiCritere(
			RechercheMulticritereBonStockValue request);

	public String createBonStock(BonStockValue bonLivraisonValue);

	public BonStockValue getBonStockById(Long id);

	public String updateBonStock(BonStockValue bonLivraisonValue);

	public void deleteBonStock(Long id);
	
	
	public List<BonStockValue> getProduitElementList(List<String> refBonStockList);

	public BonStockValue getByReference(String reference);

	public List<BonStockValue> getByClientId(Long clientId);
	
	List<BonStockValue> getTraitementFaconElementList(List<String> refBonStockList);
	





}
