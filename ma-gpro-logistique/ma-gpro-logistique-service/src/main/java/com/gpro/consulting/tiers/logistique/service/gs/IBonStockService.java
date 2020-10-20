package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;

/**
 * BonStock Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonStockService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonStockValue rechercherMultiCritere(
			RechercheMulticritereBonStockValue request);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	//@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonStock(BonStockValue bonLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public BonStockValue getBonStockById(Long id);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonStock(BonStockValue bonLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonStock(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(
			List<String> refBonStockList, Long factureVenteId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListBonStockRef();
	


	
	//Added on 11/10/2016 by Zeineb Medimagh
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonStockList, Long factureVenteId);


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(Calendar instance, boolean b);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonStockValue validerFactureAvoirRetour(String reference);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonStockValue validerBR(String reference);
	
}
