package com.gpro.consulting.tiers.logistique.service.gc.boncomptoir.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.IBonComptoirDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.boncomptoir.IBonComptoirService;

/**
 * implementation of {@link IBonComptoirService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class BonComptoirServiceImpl implements IBonComptoirService {

	private static final Logger logger = LoggerFactory.getLogger(BonComptoirServiceImpl.class);

	@Autowired
	private IBonComptoirDomaine bonComptoirDomaine;

	@Override
	public ResultatRechecheBonComptoirValue rechercherMultiCritere(RechercheMulticritereBonComptoirValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);

		return bonComptoirDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String createBonComptoir(ComptoirVenteValue bonComptoirValue) {

		return bonComptoirDomaine.createBonComptoir(bonComptoirValue);
	}

	@Override
	public ComptoirVenteValue getBonComptoirById(Long id) {

		return bonComptoirDomaine.getBonComptoirById(id);
	}

	@Override
	public String updateBonComptoir(ComptoirVenteValue bonComptoirValue) {

		return bonComptoirDomaine.updateBonComptoir(bonComptoirValue);
	}

	@Override
	public void deleteBonComptoir(Long id) {

		bonComptoirDomaine.deleteBonComptoir(id);
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonComptoirList, Long factureVenteId) {

		return bonComptoirDomaine.getProduitElementList(refBonComptoirList, factureVenteId);
	}

	@Override
	public List<LivraisonVenteVue> getListBonComptoirRefByClient(Long idClient) {

		return bonComptoirDomaine.getListBonComptoirRefByClient(idClient);
	}

	@Override
	public List<String> getListBonComptoirRef() {
		return bonComptoirDomaine.getListBonComptoirRef();
	}

	@Override
	public List<ComptoirVenteValue> getAll() {
		return bonComptoirDomaine.getAll();
	}

	@Override
	public ListTraitFaconElementValue getTraitementFaconElementList(List<String> refBonComptoirList,
			Long factureVenteId) {

		return bonComptoirDomaine.getTraitementFaconElementList(refBonComptoirList, factureVenteId);
	}

	@Override
	public List<LivraisonVenteFactureVue> getAllListBonComptoirRefByClient(Long idClient) {

		return bonComptoirDomaine.getAllListBonComptoirRefByClient(idClient);
	}

}
