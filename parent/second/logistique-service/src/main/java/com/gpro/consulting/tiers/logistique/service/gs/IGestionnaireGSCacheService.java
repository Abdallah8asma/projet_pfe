package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
public interface IGestionnaireGSCacheService {

  /** Initialisation */
  @Transactional
  public void init();

  /**
   * La liste des patiesReferentielles
   * 
   * @return
   */
  @Transactional
  @Cacheable(value = "ListeMagasinCache")
  public List < MagasinValue> getListeMagasin();

  /***liste emplacement***/
  @Transactional
  @Cacheable(value = "ListeEmplacementCache")
  public List < EmplacementValue> getListeEmplacement();;
  
  /****liste raison stock****/
  @Transactional
  @Cacheable(value = "ListeRaisonCache")
  public List < RaisonMouvementStockValue> getListeRaison();
  
  @Transactional
  //@Cacheable(value = "ListeClientCache")
  public List < PartieInteresseCacheValue> getListeClient();
  
  @Transactional
 // @Cacheable(value = "ListeFournisseurCache")
  public List < PartieInteresseCacheValue> getListeFournisseur();
  
  @Transactional
  // @Cacheable(value = "ListeFournisseurCache")
   public List < PartieInteresseCacheValue> getListeSousTraitant();
   
  /**
   * Retourne la liste des patieReferentielles
   * 
   * @return
   */
  /*@Transactional
  @Cacheable(value = "rechercherArticleParId")
  public Map<String, String> rechercherArticleParId(Long pIdSousFamille, Long pIdSite);
  */
  @Transactional
  @Cacheable(value = "rechercherBonMouvementParId")
  public Map<String, String> rechercherBonMouvementParId(Long id);
  
  
  /**
   * Rafraichir les tables de la G.Stock
   * 
   * @return
   */
  @Transactional
  public String reloadGestionStock();

  @Transactional
  @Cacheable(value = "rechercherClientParId")
  public String rechercherclientId(Long idClient);


}
