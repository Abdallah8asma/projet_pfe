package com.gpro.consulting.tiers.logistique.service.atelier.cache.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ModePaiementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.cache.IGestionnaireLogistiqueCacheDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.bonReception.ITraitementService;
import com.gpro.consulting.tiers.logistique.service.atelier.cache.IGestionnaireLogistiqueCacheService;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IChoixRouleauService;
import com.gpro.consulting.tiers.logistique.service.atelier.rouleaufini.IEntrepotService;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.ITypeReglementAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.bonlivraison.IModePaiementService;
import com.gpro.consulting.tiers.logistique.service.gc.typeReglement.ITypeReglementService;
import com.gpro.consulting.tiers.logistique.service.gl.facon.ITraitementFaconService;
import com.gpro.consulting.tiers.logistique.service.gl.machine.IMachineService;

/**
 * @author Ameni
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GestionnaireLogistiqueCacheServiceImpl implements IGestionnaireLogistiqueCacheService {

	/** Date initialisation */
	private static Calendar dateInit;

	/****** invocation du service du Domaine ******/
	@Autowired
	private IGestionnaireLogistiqueCacheDomaine gestionnaireLogistiqueCacheDomaine;
	/** service Entrepot ******/
	@Autowired
	private IEntrepotService entrepotService;
	/** service ChoixRouleau ******/
	@Autowired
	private IChoixRouleauService choixRouleauservice;
	/** service modePaiement ******/
	@Autowired
	private IModePaiementService modePaiementService;
	/** service typeReglement ******/
	@Autowired
	private ITypeReglementService typeReglementService;
	
	/** service typeReglement Achat ******/
	@Autowired
	private ITypeReglementAchatService typeReglementAchatService;
	
	
	/** service machine ******/
	@Autowired
	private IMachineService machineService;
	/** service traitement ******/
	@Autowired
	private ITraitementService traitementService;
	
	/** service traitement Façon ******/
	@Autowired
	private ITraitementFaconService traitementFaconService;
	
	
	
	/** Instance Singleton */
	private static GestionnaireLogistiqueCacheServiceImpl INSTANCE = new GestionnaireLogistiqueCacheServiceImpl();

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireLogistiqueCacheServiceImpl.class);

	/** liste des Clients */
	private List<PartieInteresseCacheValue> listeClient = new ArrayList<PartieInteresseCacheValue>();

	/** liste des Produits */
	private List<ProduitValue> listeProduit = new ArrayList<ProduitValue>();
	
	/** liste des SousfAmilleProduit */
	private List<SousFamilleProduitValue> listSousFamilleProduit = new ArrayList<SousFamilleProduitValue>();
	
	/** liste entrepots */
	private List<EntrepotValue> listeEntrepot;

	/** liste choixRouleau */
	private List<ChoixRouleauValue> listeChoixRouleau;
	
	/** liste Traitement */
	private List<TraitementValue> listTraitement;
	
	/** liste Machine */
	private List<MachineValue> listMachine;
	
	/** liste TypeReglement */
	private List<TypeReglementValue> listTypeReglement;
	
	
	/** liste TypeReglement Achat */
	private List<TypeReglementAchatValue> listTypeReglementAchat;
	

	/** liste ModePaiement */
	private List<ModePaiementValue> listModePaiement;
	
	private Map<Long, PartieInteresseValue> mapClientById;
	private Map<Long, ProduitValue> mapPtoduitById;
	
	/**
	   * Added by Zeineb Medimagh
	   * 29/09/2016
	   */
	
	/** liste Traitement */
	private List<TraitementFaconValue> listTraitementFacon;
	

	@Autowired
	private EhCacheCacheManager cacheManager;

	/** Instationation du singleton */
	public static synchronized IGestionnaireLogistiqueCacheService getInstance() {
		return INSTANCE;

	}

	private GestionnaireLogistiqueCacheServiceImpl() {

	}

	@Override
	public void init() {
		dateInit = Calendar.getInstance();

		//LOGGER.info("Init ClientCache");
		List<PartieInteresseCacheValue> listPartieInteresseCacheValues = gestionnaireLogistiqueCacheDomaine
				.getListePartieInteressee();
		initListPartieInteresseValeur(listPartieInteresseCacheValues);

		//LOGGER.info("Init ProduitCache");
		List<ProduitValue> listProduitCacheValues = gestionnaireLogistiqueCacheDomaine
				.getListeProduit();
		initListProduitValeur(listProduitCacheValues);

		//LOGGER.info("Init SousFamilleProduit");
		List<SousFamilleProduitValue> listSousFamilleProduitCacheValues = gestionnaireLogistiqueCacheDomaine
				.getListeSousFamilleProduit();
		initSousFamilleProduitValeur();
		
		
		//LOGGER.info("Init Entrepot");
		initEntrepotValeur();
		
		//LOGGER.info("Init ChoixRouleau");
		initChoixRouleauValeur();
		
		//LOGGER.info("Init Traitement");
		initTraitementValeur();
		
		//LOGGER.info("Init Machine");
		initMachineValeur();
		
		//LOGGER.info("Init TypeReglement");
		initTypeReglementValeur();
		
		//LOGGER.info("Init TypeReglement Achat");
		initTypeReglementAchatValeur();
		
		//LOGGER.info("Init ModePaiement");
		initModePaiementValeur();
		
		//LOGGER.info("Init MapClientById");
		initMapClientById();

		//LOGGER.info("Init MapProduitById");
		initMapProduitById();
		
		//LOGGER.info("Init Traitement Façon");
		initTraitementValeur();
		
	
	}

	private void initSousFamilleProduitValeur() {
		listSousFamilleProduit = gestionnaireLogistiqueCacheDomaine.getListeSousFamilleProduit();
		
	}

	private void initChoixRouleauValeur() {
		listeChoixRouleau = choixRouleauservice.listeChoixRouleau();
		
	}

	private void initEntrepotValeur() {
		listeEntrepot = entrepotService.listeEntrepot();
		
	}

	private void initListPartieInteresseValeur(
			List<PartieInteresseCacheValue> listPartieInteresseCacheValues) {
		for (PartieInteresseCacheValue partieInteresse : listPartieInteresseCacheValues) {
			//LOGGER.info("---- listPartieInteresseCacheValues "+listPartieInteresseCacheValues.size());
			if (partieInteresse.getFamillePartieInteressee() != null
					&& partieInteresse.getFamillePartieInteressee().equals(1L))
				listeClient.add(partieInteresse);
			
		}

	}

	private void initListProduitValeur(List<ProduitValue> listProduitCacheValues) {
		listeProduit = gestionnaireLogistiqueCacheDomaine.getListeProduit();
	}

	
	private void initModePaiementValeur() {
		listModePaiement = modePaiementService.getAll();
		
	}

	private void initTypeReglementValeur() {
		listTypeReglement = typeReglementService.getAll();
		
	}

	private void initTypeReglementAchatValeur() {
		listTypeReglementAchat = typeReglementAchatService.getAll();
		
	}
	
	private void initMachineValeur() {
		listMachine = machineService.getAll();
		
	}

	private void initTraitementValeur() {
		listTraitement	= traitementService.listeTraitement();	
	}
	
	
	private void initTraitementFaconValeur() {
		listTraitementFacon	= traitementFaconService.getAll();
	}
	
		
	@Override
	public Map<String, String> rechercherDesignationParId(Long pIdClient, Long pIdProduit, Long pIdEntrepot, Long pIdChoix) {

		Map<String, String> mapA = new HashMap<String, String>();

		//LOGGER.info("Transformation ID => Designation");
		// Client
		for (PartieInteresseCacheValue client : this.getListeClient()) {

			if (client.getId().equals(pIdClient)) {
				// returne client Designation
				mapA.put("client", client.getAbreviation());
			}
		}
		// Produit
		for (ProduitValue produit : this.getListeProduit()) {

			if (produit.getId().equals(pIdProduit)) {
				// returne produit Designation, reference, Composition, sousFamilleProduit
				mapA.put("produit", produit.getDesignation());
				mapA.put("produitRef", produit.getReference());
				mapA.put("produitComposition", produit.getComposition());
				// TODO à revoir
				for (SousFamilleProduitValue sousFamilleProduit : this
						.getListSousFamilleProduit()) {
					// SousFamille
					if (sousFamilleProduit.getId().equals(produit.getSousFamilleId())) {
						// returne sousFamilleProduit Designation
						mapA.put("produitType", sousFamilleProduit.getDesignation());
					}
				}
			}
		}
		// entrepot
		for ( EntrepotValue entrepot: listeEntrepot) {

			if (entrepot.getId().equals(pIdEntrepot)) {
				// returne entrepot Designation
				mapA.put("entrepot", entrepot.getDesignation());
			}
		}
		// choixRouleau
		for ( ChoixRouleauValue choix: this.getListChoixRouleau()) {

			if (choix.getId().equals(pIdChoix)) {
				// returne entrepot Designation
				mapA.put("choix", choix.getDesignation());
			}
		}
		return mapA;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String reloadLogistiqueCache() {
		// Get all the active caches
		List<String> caches = new ArrayList(cacheManager.getCacheNames().size());

		Collection<String> cacheNames = cacheManager.getCacheNames();
		Iterator<String> iter = cacheNames.iterator();

		while (iter.hasNext()) {
			// If the cache name has been passed from the request then flush it

			String cacheName = (String) iter.next();

			cacheManager.getCache(cacheName).clear();

			caches.add(cacheName);
		}
		init();
		return "Done";

	}
	
	private void initMapClientById() {
		mapClientById = gestionnaireLogistiqueCacheDomaine.mapClientById();
	}

	
	private void initMapProduitById() {
		mapPtoduitById = gestionnaireLogistiqueCacheDomaine.mapProduitById();
	}
	

	/**
	 * Accesseur en lecture de l'attribut listeClient.
	 * 
	 * @return the listeClient
	 */
	public List<PartieInteresseCacheValue> getListeClient() {
		return listeClient;
	}

	/**
	 * @param listeClient
	 *            the listeClient to set
	 */
	public void setListeClient(List<PartieInteresseCacheValue> listeClient) {
		this.listeClient = listeClient;
	}

	/**
	 * Accesseur en lecture de l'attribut cacheManager.
	 * 
	 * @return the cacheManager
	 */
	public EhCacheCacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param cacheManager
	 *            the cacheManager to set
	 */
	public void setCacheManager(EhCacheCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * Accesseur en lecture de l'attribut listeProduit.
	 * 
	 * @return the listeProduit
	 */
	public List<ProduitValue> getListeProduit() {
		return listeProduit;
	}

	/**
	 * @param listeProduit
	 *            the listeProduit to set
	 */
	public void setListeProduit(List<ProduitValue> listeProduit) {
		this.listeProduit = listeProduit;
	}

	@Override
	public List<EntrepotValue> getListEntrepot() {
		return listeEntrepot;
	}

	@Override
	public List<ChoixRouleauValue> getListChoixRouleau() {
		return listeChoixRouleau;
	}

	@Override
	public List<SousFamilleProduitValue> getListSousFamilleProduit() {
		return listSousFamilleProduit;
	}

	@Override
	public Map<Long, PartieInteresseValue> mapClientById() {
		
		//LOGGER.info("--retrive  mapClientById, service cache layer");
		
		return mapClientById;
	}

	@Override
	public Map<Long, ProduitValue> mapPtoduitById() {
		
		//LOGGER.info("--retrive  mapPtoduitById, service cache layer");
		
		return mapPtoduitById;
	}

	public List<ModePaiementValue> getListModePaiement() {
		return listModePaiement;
	}

	public List<TraitementValue> getListTraitement() {
		return listTraitement;
	}

	
	public List<MachineValue> getListMachine() {
		return listMachine;
	}

	public List<TypeReglementValue> getListTypeReglement() {
		return listTypeReglement;
	}

	
	
	
	/**
	   * Added by Zeineb Medimagh
	   * 29/09/2016
	   */
	
	@Override
	public List<TraitementFaconValue> getListTraitementFacon() {
		// TODO Auto-generated method stub
		return listTraitementFacon;
	}

	@Override
	public List<TypeReglementAchatValue> getListTypeReglementAchat() {
		// TODO Auto-generated method stub
		return listTypeReglementAchat;
	}

	

}
