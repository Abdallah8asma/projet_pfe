package com.gpro.consulting.tiers.logistique.domaine.gs;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;

/**
 * BonStock Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonStockDomaine {

	public ResultatRechecheBonStockValue rechercherMultiCritere(
			RechercheMulticritereBonStockValue request);

	public String createBonStock(BonStockValue bonLivraisonValue);

	public BonStockValue getBonStockById(Long id);

	public String updateBonStock(BonStockValue bonLivraisonValue);

	public void deleteBonStock(Long id);

	public ListProduitElementValue getProduitElementList(
			List<String> refBonStockList, Long factureVenteId);

	public List<String> getListBonStockRef();

	public BonStockValue getByReference(String refBL);

	
	//Added on 11/10/2016 by Zeineb Medimagh
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonStockList, Long factureVenteId);





	public String getCurrentReference(Calendar instance, boolean b);

	public BonStockValue validerFactureAvoirRetour(String reference);

	public BonStockValue validerBR(String reference);
}
