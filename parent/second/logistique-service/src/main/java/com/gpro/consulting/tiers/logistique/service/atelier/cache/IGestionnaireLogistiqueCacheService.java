/**
 * 
 */
package com.gpro.consulting.tiers.logistique.service.atelier.cache;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
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

/**
 * @author Ameni
 *
 */
public interface IGestionnaireLogistiqueCacheService {

	/** Initialisation */
	  @Transactional
	  public void init();
	  
	  @Transactional
	  //@Cacheable(value = "ListeClientCache")
	  public List < PartieInteresseCacheValue> getListeClient();
	  
	  @Transactional
	  //@Cacheable(value = "ListeProduitCache")
	  public List < ProduitValue> getListeProduit();
	  
	  @Transactional
	  @Cacheable(value = "listeSousFamilleProduitCache")
	  public List <SousFamilleProduitValue> getListSousFamilleProduit();
	  
	  @Transactional
	  @Cacheable(value = "listeEntrepotCache")
	  public List < EntrepotValue> getListEntrepot();

	  @Transactional
	  @Cacheable(value = "listeChoixRouleauCache")
	  public List < ChoixRouleauValue> getListChoixRouleau();
	  
	  @Transactional
	  @Cacheable(value = "listeTraitementCache")
	  public List < TraitementValue> getListTraitement();
	  
	  @Transactional
	  @Cacheable(value = "listeMachineCache")
	  public List < MachineValue> getListMachine();
	  
	  @Transactional
	  @Cacheable(value = "listeTypeReglementCache")
	  public List < TypeReglementValue> getListTypeReglement();
	  
	  @Transactional
	  @Cacheable(value = "listeTypeReglementAchatCache")
	  public List < TypeReglementAchatValue> getListTypeReglementAchat();
	  
	  @Transactional
	  @Cacheable(value = "listeModePaiementCache")
	  public List < ModePaiementValue> getListModePaiement();
	  
	  
	 
	  /**
	   * Added by Zeineb Medimagh
	   * 29/09/2016
	   */
	  
	  @Transactional
	  @Cacheable(value = "listeTraitementFaconCache")
	  public List < TraitementFaconValue> getListTraitementFacon();
	  
	  /**
	   * Rafraichir les tables du referentiel
	   * 
	   * @return
	   */
	  @Transactional
	  public String reloadLogistiqueCache();

	  
	  /**
	   * Methode de conversion Id => Designation
	   * 
	   * @return map<id,designation>
	   */
	  @Transactional
	  @Cacheable(value = "rechercherDesignationId")
	  public Map<String, String> rechercherDesignationParId(Long pIdClient, Long pIdProduit,Long pIdEntrepot, Long pIdChoix);
	  
	  @Transactional
	  @Cacheable(value = "mapClientById")
	  public Map<Long, PartieInteresseValue> mapClientById();
	  
	  @Transactional
	  @Cacheable(value = "mapPtoduitById")
	  public Map<Long, ProduitValue> mapPtoduitById();
}
