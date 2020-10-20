package com.gpro.consulting.tiers.logistique.domaine.atelier.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.commun.service.elementBase.ISousFamilleProduitService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.cache.IGestionnaireLogistiqueCacheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.ITaxeDomaine;

/**
 * @author Ameni
 *
 */

@Component
public class GestionnaireLogistiqueCacheDomaineImpl implements IGestionnaireLogistiqueCacheDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireLogistiqueCacheDomaineImpl.class);

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IPartieInteresseeService partieInteresseService;

	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;

	@Autowired
	private ITaxeDomaine taxeDomaine;
	
	@Autowired
	private IGroupeClientService groupeClientService;

	@Override
	public List<PartieInteresseCacheValue> getListePartieInteressee() {
		return partieInteresseService.listePartieInteresseeCache();

	}

	@Override
	public List<ProduitValue> getListeProduit() {
		// Changed By Ghazi on 23/05/2018

		List<ProduitValue> list = produitService.listeProduit();

		for (ProduitValue produit : list) {

			if (produit.getIdTaxe() != null) {
				TaxeValue taxe = taxeDomaine.getById(produit.getIdTaxe());
				if (taxe != null) {
					produit.setTauxTVA(taxe.getValeur());
				}

			}

		}

		return list;
	}

	@Override
	public List<SousFamilleProduitValue> getListeSousFamilleProduit() {
		return sousFamilleProduitService.listeSousFamilleProduit();
	}

	@Override
	public Map<Long, PartieInteresseValue> mapClientById() {

		//logger.info("--retrive  mapClientById, domain cache layer");

		List<PartieInteresseValue> clientList = partieInteresseService.listePartieInteressee();

		Map<Long, PartieInteresseValue> map = new HashMap<Long, PartieInteresseValue>();

		for (PartieInteresseValue client : clientList) {
			map.put(client.getId(), client);
		}

		return map;
	}

	@Override
	public Map<Long, ProduitValue> mapProduitById() {

		//logger.info("--retrive  mapProduitById, domain cache layer");

		List<ProduitValue> produitList = produitService.listeProduit();

		Map<Long, ProduitValue> map = new HashMap<Long, ProduitValue>();

		for (ProduitValue produit : produitList) {
			map.put(produit.getId(), produit);
		}

		return map;
	}

	@Override
	public Map<Long, GroupeClientValue> mapGroupePIById() {
		// TODO Auto-generated method stub
		 List < GroupeClientValue > listeGroupe =  groupeClientService.listeGroupeClient();
		 
			Map<Long, GroupeClientValue> map = new HashMap<Long, GroupeClientValue>();

			for (GroupeClientValue groupe : listeGroupe) {
				map.put(groupe.getId(), groupe);
			}

			return map;
	}
}
