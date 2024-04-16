package com.gpro.consulting.tiers.logistique.rest.gc.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheProduitBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheDetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IProduitCommandeService;

/**
 * Bon de livraison Controller
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Controller
@RequestMapping("/produitCommande")
public class ProduitCommandeRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ProduitCommandeRestImpl.class);

	@Autowired
	private IProduitCommandeService produitCommandeService;

	@Autowired
	private IGestionnaireLogistiqueCacheService gestionnaireLogistiqueCacheService;

	// @RequestMapping(value = "/rechercheMulticritere", method =
	// RequestMethod.POST, produces = "application/json")
	// public @ResponseBody ResultatRechecheBonLivraisonValue
	// rechercherMultiCritere(@RequestBody
	// RechercheMulticritereBonLivraisonValue request) {
	//
	// logger.info("rechercheMulticritere: Delegating request {} to service
	// layer.", request);
	//
	// ResultatRechecheBonLivraisonValue result =
	// bonLivraisonService.rechercherMultiCritere(request);
	//
	// if( result != null){
	//
	// Map<Long, PartieInteresseValue> mapClientById =
	// gestionnaireLogistiqueCacheService.mapClientById();
	// Map<Long, MarcheValue> mapMarcheById = new HashMap<Long, MarcheValue>();
	// Map<Long, ModePaiementValue> mapModePaiementById = new HashMap<Long,
	// ModePaiementValue>();
	//
	// List<MarcheValue> marcheList = marcheService.getAll();
	// List<ModePaiementValue> modePaiementList = modePaiementService.getAll();
	//
	// for(MarcheValue marche : marcheList){
	// Long key = marche.getId();
	// if (mapMarcheById.get(key) == null) {
	// mapMarcheById.put(marche.getId(), marche);
	// }
	// }
	//
	// for(ModePaiementValue modePaiement : modePaiementList){
	// Long key = modePaiement.getId();
	// if (mapModePaiementById.get(key) == null) {
	// mapModePaiementById.put(modePaiement.getId(), modePaiement);
	// }
	// }
	//
	// for(ProduitCommandeValue livraisonVenteValue : result.getList()){
	// if(livraisonVenteValue != null){
	//
	// PartieInteresseValue client =
	// mapClientById.get(livraisonVenteValue.getPartieIntId());
	// MarcheValue marche =
	// mapMarcheById.get(livraisonVenteValue.getMarcheId());
	// ModePaiementValue modePaiement =
	// mapModePaiementById.get(livraisonVenteValue.getModepaiementId());
	//
	// if(client != null){
	// livraisonVenteValue.setPartieIntAbreviation(client.getAbreviation());
	// }
	// if(marche != null){
	// livraisonVenteValue.setMarche(marche.getDesignation());
	// }
	// if(modePaiement != null){
	// livraisonVenteValue.setModepaiement(modePaiement.getDesignation());
	// }
	// }
	// }
	// }
	//
	// return result;
	// }

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody ProduitCommandeValue produitCommandeValue) {

		//logger.info("produitCommande - create: Delegating request to Service layer.");

		return this.produitCommandeService.create(produitCommandeValue);
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ProduitCommandeValue getBonLivraisonById(@PathVariable Long id) {

		//logger.info("produitCommande - getById: Delegating request id {} to service layer.", id);

		ProduitCommandeValue produitCommandeValue = produitCommandeService.getById(id);

		if (produitCommandeValue != null) {
			Map<Long, PartieInteresseValue> mapClientById = gestionnaireLogistiqueCacheService.mapClientById();
			PartieInteresseValue client = mapClientById.get(produitCommandeValue.getPartieIntersseAbbreviation());
			if (client != null) {
				produitCommandeValue.setPartieIntersseAbbreviation(client.getAbreviation());
			}
		}

		return produitCommandeValue;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String updateBonLivraison(@RequestBody ProduitCommandeValue produitCommandeValue) {

		return this.produitCommandeService.update(produitCommandeValue);
	}

	@RequestMapping(value = "/deleteBonLivraison:{id}", method = RequestMethod.DELETE)
	public void deleteBonLivraison(@PathVariable Long id) {

		produitCommandeService.delete(id);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ProduitCommandeValue> getAll() {

		//logger.info("getAll");

		return produitCommandeService.getAll();
	}
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheProduitBonCommandeValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereProduitBonCommandeValue request) {
		request.setOptimized(RechercheMulticritereProduitBonCommandeValue.checkForOptimization(request));
		ResultatRechecheProduitBonCommandeValue result = produitCommandeService
				.rechercherMultiCritere(request);

		
		return  result;
	}

}
