package com.gpro.consulting.tiers.commun.service.cache;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.GrosseurValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.TypeArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.UniteArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.FamilleValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;


/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
public interface IGestionnaireCacheService {

  /** Initialisation */
  @Transactional
  public void init();

  /**
   * La liste des patiesReferentielles
   * 
   * @return
   */
  @Transactional
  @Cacheable(value = "ListTypeArticle")
  public List < TypeArticleValue> getListeTypeArticle();

  @Transactional
  @Cacheable(value = "listeFamilleArticleCache")
  public List < FamilleArticleValue> getListFamilleArticle();

  @Transactional
  @Cacheable(value = "ListSousFamilleArticle")
  public List < SousFamilleArticleValue> getListSousFamilleArticle();

  @Transactional
  @Cacheable(value = "listeSitePartieInteresseeCache")
  public List < SiteValue> getListSitePartieInteressee();

  @Transactional
  @Cacheable(value = "listeUniteArticleCache")
  public List < UniteArticleValue> getListUniteArticle();

  @Transactional
  @Cacheable(value = "listeMatiereArticleCache")
  public List < MatiereArticleValue> getListMatiereArticle();

  @Transactional
  @Cacheable(value = "listeMetrageArticleCache")
  public List <MetrageValue> getListMetrageArticle();
 
  @Transactional
  @Cacheable(value = "listeGrosseurArticleCache")
  public List <GrosseurValue> getListGrosseurArticle();
 
  @Transactional
  @Cacheable(value = "listeTypeDocumentCache")
  public List < TypeDocumentValue> getListTypeDocument();

  @Transactional
  @Cacheable(value = "listeFamillePiCache")
  public List < FamilleValue> getListFamillePi();

  @Transactional
  @Cacheable(value = "listeCategoriePiCache")
  public List < CategorieValue> getListCategoriePi();

  @Transactional
  @Cacheable(value = "listeTypePiCache")
  public List < TypeValue> getListTypePi();

  @Transactional
  @Cacheable(value = "listeDeviseCache")
  public List < DeviseValue> getListDevise();

  @Transactional
  @Cacheable(value = "listeFamilleProduitCache")
  public List <FamilleProduitValue> getListFamilleProduit();

  @Transactional
  @Cacheable(value = "listeSousFamilleProduitCache")
  public List <SousFamilleProduitValue> getListSousFamilleProduit();

  @Transactional
  @Cacheable(value = "listeCouleurProduitCache")
  public List <CouleurValue> getListCouleurProduit();
  
  @Transactional
  @Cacheable(value = "listePartieInteresseeCache")
  public List <PartieInteresseValue> getListPartieInteressee();
  
  /**
   * Retourne la liste des patieReferentielles
   * 
   * @return
   */
  @Transactional
  @Cacheable(value = "rechercherArticleParId")
  public Map<String, String> rechercherArticleParId(Long pIdSousFamille, Long pIdSite);
  
  @Transactional
  @Cacheable(value = "rechercherProduitParId")
  public Map<String, String> rechercherProduitParId(Long pIdSousFamille, Long pIdSite, Long pIdPI);
  
  
  @Transactional
  @Cacheable(value = "rechercherPartieInteresseeParId")
  public Map<String, String> rechercherPartieInteresseeParId(Long pIdSousFamille, Long pIdCategorie, Long pIdType);

  /**
   * Rafraichir les tables du referentiel
   * 
   * @return
   */
  @Transactional
  public String reloadReferentiel();
  
  
  @Transactional
  @Cacheable(value = "rechFamEtSousFamParId")
  public Map<String, String> rechFamEtSousFamParId(Long pIdSousFamille);  
  
  @Transactional
  @Cacheable(value = "rechercheTailleParId")
  public String rechercheTailleParId(Long pIdTaill); 
  
  @Transactional
  @Cacheable(value = "rechercheColorParId")
  public String rechercheColorParId(Long pIdColor);
  
  
  
  

}
