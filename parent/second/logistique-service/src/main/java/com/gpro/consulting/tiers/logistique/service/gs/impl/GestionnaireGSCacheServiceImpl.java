package com.gpro.consulting.tiers.logistique.service.gs.impl;

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

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IGestionnaireCacheDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IEmplacementService;
import com.gpro.consulting.tiers.logistique.service.gs.IGestionnaireGSCacheService;
import com.gpro.consulting.tiers.logistique.service.gs.IMagasinService;
import com.gpro.consulting.tiers.logistique.service.gs.IRaisonMouvementService;



/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GestionnaireGSCacheServiceImpl implements IGestionnaireGSCacheService {

	/** Date initialisation */
	private static Calendar dateInit;
	/** Service magasinService */
	@Autowired
	private IMagasinService magasinService;
	/*****service emplacement*********/
    @Autowired
    private IEmplacementService emplacementService;
    /*****service raison******/
    @Autowired
    private IRaisonMouvementService raisonMouvementService;
    /******liste PI******/
	@Autowired
	private IGestionnaireCacheDomaine gestionnaireCacheDomaine;
	
	
	/** Instance Singleton */
	private static GestionnaireGSCacheServiceImpl INSTANCE = new GestionnaireGSCacheServiceImpl();

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GestionnaireGSCacheServiceImpl.class);

	/** Liste des magasin */
	private List<MagasinValue> listeMagasin;
	private List<EmplacementValue> listeEmplacement;
	private List<RaisonMouvementStockValue> listeRaison;
	private List<PartieInteresseCacheValue> listeClient=new ArrayList<PartieInteresseCacheValue>();
	private List<PartieInteresseCacheValue> listeFournisseur=new ArrayList<PartieInteresseCacheValue>();
	private List<PartieInteresseCacheValue> listeSousTraitant=new ArrayList<PartieInteresseCacheValue>();
	
	@Autowired
	private EhCacheCacheManager cacheManager;

	/** Instationation du singleton */
	public static synchronized IGestionnaireGSCacheService getInstance() {
		return INSTANCE;

	}

	public void init() {
		dateInit = Calendar.getInstance();
		List<PartieInteresseCacheValue> listPartieInteresseCacheValues=gestionnaireCacheDomaine.getListePartieInteressee();
		
		//LOGGER.info("Init Categorie");
		initMagasinValue();
        intiEmplacement();
        initRaison();
	    initListPartieInteresse(listPartieInteresseCacheValues);
		
	}

	/**
	 * @param listPartieInteresseCacheValues
	 */
	private void initListPartieInteresse(
			List<PartieInteresseCacheValue> listPartieInteresseCacheValues) {
		for(PartieInteresseCacheValue partieInteresse: listPartieInteresseCacheValues){
			if(partieInteresse.getFamillePartieInteressee()!=null){
			if(partieInteresse.getFamillePartieInteressee().equals(1L))
				listeClient.add(partieInteresse);
			else if(partieInteresse.getFamillePartieInteressee().equals(2L)){
				listeFournisseur.add(partieInteresse);	
			}else if(partieInteresse.getFamillePartieInteressee().equals(3L)){
				listeSousTraitant.add(partieInteresse);
			}
			}
		}
		
	}


	private void initRaison() {
		listeRaison=raisonMouvementService.listeRaisonMouvementStock();
		
	}

	private void intiEmplacement() {
		listeEmplacement=emplacementService.listeEmplacementValue();
		
	}

	/**
   * 
   */
	private void initMagasinValue() {
		listeMagasin = magasinService.listeMagasin();
	}

	
	/**
	 * {@inheritDoc}
	 */
	public String reloadGestionStock() {
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
		return "Refresh G.Stock Done";

	}

	@Override
	public List<MagasinValue> getListeMagasin() {
		return listeMagasin;
	}

	public EhCacheCacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(EhCacheCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setListeMagasin(List<MagasinValue> listeMagasin) {
		this.listeMagasin = listeMagasin;
	}

	@Override
	public List<EmplacementValue> getListeEmplacement() {
		return listeEmplacement;
	}

	@Override
	public List<RaisonMouvementStockValue> getListeRaison() {
		return listeRaison;
	}

	public void setListeEmplacement(List<EmplacementValue> listeEmplacement) {
		this.listeEmplacement = listeEmplacement;
	}

	public void setListeRaison(List<RaisonMouvementStockValue> listeRaison) {
		this.listeRaison = listeRaison;
	}

	@Override
	public List<PartieInteresseCacheValue> getListeClient() {
		return listeClient;
	}

	@Override
	public List<PartieInteresseCacheValue> getListeFournisseur() {
		return listeFournisseur;
	}

	public void setListeClient(List<PartieInteresseCacheValue> listeClient) {
		this.listeClient = listeClient;
	}

	public void setListeFournisseur(List<PartieInteresseCacheValue> listeFournisseur) {
		this.listeFournisseur = listeFournisseur;
	}

	@Override
	public List<PartieInteresseCacheValue> getListeSousTraitant() {
		return listeSousTraitant;
	}

	public void setListeSousTraitant(
			List<PartieInteresseCacheValue> listeSousTraitant) {
		this.listeSousTraitant = listeSousTraitant;
	}

	@Override
	// Rechercher bon mouvement par id
	public Map<String, String> rechercherBonMouvementParId(Long pIdRaison) {

		Map<String, String> mapA = new HashMap<String, String>();


		if(this.getListeRaison()!=null)
		{
			for (RaisonMouvementStockValue raison : this.getListeRaison()) {

				if (raison.getId().equals(pIdRaison)) {
					// returne sousFamille Designation
					mapA.put("raison", raison.getDesignation());
				}
			}
			
		}
		
		return mapA;
	}
	
	@Override
	// Rechercher client par id
	public String rechercherclientId(Long idClient) {

		Map<String, String> mapA = new HashMap<String, String>();

		for (PartieInteresseCacheValue client : this.getListeClient()) {

			if (client.getId().equals(idClient)) {
				return client.getAbreviation();
			}
		}
		return null;
	}
	
	
}
