package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonStockDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IBonStockService;

/**
 * implementation of {@link IBonStockService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class BonStockServiceImpl implements IBonStockService{
	
	private static final Logger logger = LoggerFactory.getLogger(BonStockServiceImpl.class);

	@Autowired
	private IBonStockDomaine bonStockDomaine;
	


	@Override
	public ResultatRechecheBonStockValue rechercherMultiCritere(
			RechercheMulticritereBonStockValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);
		
		return bonStockDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String createBonStock(BonStockValue bonLivraisonValue) {
		
		return bonStockDomaine.createBonStock(bonLivraisonValue);
	}

	@Override
	public BonStockValue getBonStockById(Long id) {
		
		return bonStockDomaine.getBonStockById(id);
	}

	@Override
	public String updateBonStock(BonStockValue bonLivraisonValue) {
		
		return bonStockDomaine.updateBonStock(bonLivraisonValue);
	}

	@Override
	public void deleteBonStock(Long id) {
		
		bonStockDomaine.deleteBonStock(id);
	}

	@Override
	public ListProduitElementValue getProduitElementList(
			List<String> refBonStockList, Long factureVenteId) {
		
		return bonStockDomaine.getProduitElementList(refBonStockList, factureVenteId);
	}



	@Override
	public List<String> getListBonStockRef() {
		return bonStockDomaine.getListBonStockRef();
	}

	

	@Override
	public ListTraitFaconElementValue getTraitementFaconElementList(List<String> refBonStockList,
			Long factureVenteId) {
		
		return bonStockDomaine.getTraitementFaconElementList(refBonStockList, factureVenteId);
	}




	@Override
	public String getCurrentReference(Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return bonStockDomaine.getCurrentReference(instance,b);
	}

	@Override
	public BonStockValue validerFactureAvoirRetour(String reference) {
		// TODO Auto-generated method stub
		return bonStockDomaine.validerFactureAvoirRetour(reference);
	}

	@Override
	public BonStockValue validerBR(String reference) {
		// TODO Auto-generated method stub
		return bonStockDomaine.validerBR(reference);
	}


	
}
