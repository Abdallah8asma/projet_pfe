package com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.impl;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ValiderBLPassagerValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IBonLivraisonDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IBonLivraisonService;

/**
 * implementation of {@link IBonLivraisonService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class BonLivraisonServiceImpl implements IBonLivraisonService{
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonServiceImpl.class);

	@Autowired
	private IBonLivraisonDomaine bonLivraisonDomaine;

	@Override
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);
		
		return bonLivraisonDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
		return bonLivraisonDomaine.createBonLivraison(bonLivraisonValue);
	}

	@Override
	public LivraisonVenteValue getBonLivraisonById(Long id) {
		
		return bonLivraisonDomaine.getBonLivraisonById(id);
	}

	@Override
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
		return bonLivraisonDomaine.updateBonLivraison(bonLivraisonValue);
	}

	@Override
	public void deleteBonLivraison(Long id) {
		
		bonLivraisonDomaine.deleteBonLivraison(id);
	}

	@Override
	public ListProduitElementValue getProduitElementList(
			List<String> refBonLivraisonList, Long factureVenteId) {
		
		return bonLivraisonDomaine.getProduitElementList(refBonLivraisonList, factureVenteId);
	}

	@Override
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient) {
		
		return bonLivraisonDomaine.getListBonLivraisonRefByClient(idClient);
	}

	@Override
	public List<String> getListBonLivraisonRef() {
		return bonLivraisonDomaine.getListBonLivraisonRef();
	}

	@Override
	public List<LivraisonVenteValue> getAll() {
		return bonLivraisonDomaine.getAll();
	}

	@Override
	public ListTraitFaconElementValue getTraitementFaconElementList(List<String> refBonLivraisonList,
			Long factureVenteId) {
		
		return bonLivraisonDomaine.getTraitementFaconElementList(refBonLivraisonList, factureVenteId);
	}

	@Override
	public List<LivraisonVenteFactureVue> getAllListBonLivraisonRefByClient(Long idClient) {
		
		return bonLivraisonDomaine.getAllListBonLivraisonRefByClient(idClient);
	}

	@Override
	public List<LivraisonVenteVue> getListBonLivraisonRefByClientAndDeclare(Long idClient) {
		// TODO Auto-generated method stub
		return bonLivraisonDomaine.getListBonLivraisonRefByClientAndDeclare(idClient);
	}

	@Override
	public String getCurrentReference(Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return bonLivraisonDomaine.getCurrentReference(instance,b);
	}

	@Override
	public ListProduitElementValue getProduitElementListForPassager(ValiderBLPassagerValue request) {
		// TODO Auto-generated method stub
		return  bonLivraisonDomaine.getProduitElementListForPassager(request);
	}

	@Override
	public String getCurrentReferenceByType(String type, Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return  bonLivraisonDomaine.getCurrentReferenceByType(type,instance,b);
	}

	@Override
	public ListProduitElementValue getProduitElementListByOF(List<String> refBonLivraisonList, Long factureVenteId) {
		
		return bonLivraisonDomaine.getProduitElementListByOF(refBonLivraisonList, factureVenteId);
	}

	@Override
	public LivraisonVenteValue getByReference(String reference) {
		// TODO Auto-generated method stub
		return  bonLivraisonDomaine.getByReference(reference);
	}
	
}
