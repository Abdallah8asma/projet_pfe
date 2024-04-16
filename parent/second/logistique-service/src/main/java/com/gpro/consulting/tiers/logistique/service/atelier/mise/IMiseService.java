package com.gpro.consulting.tiers.logistique.service.atelier.mise;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;


/**
 * Interface des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IMiseService {

  /**
   * Méthode de création d'un bon de reception et de l'inserer dans la base de données avec toutes
   * ses associations.
   * 
   * @param pMiseValue
   * @return
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerMise(MiseValue pMiseValue);

  /**
   * Méthode de suppression d'un Bon de reception par id.
   * 
   * @param pId
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerMise(Long pId);

  /**
   * Méthode de modification d'un bon de reception par id
   * 
   * @param pMise
   * @return
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierMise(MiseValue pMise);

  /**
   * Méthode de recherche d'un bon de reception par id retournant un objet Valeur.
   * 
   * @param pMise
   * @return
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public MiseValue rechercheMiseParId(Long pMise);
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue pRechercheMiseMulitCritere) ;
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<MiseValue> listerMise();
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public TraitementMiseValue getTraitementMiseById(Long id);
 
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public String listRefMiseParRefBR(String referenceBR);

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<MiseValue> getReferenceMise();
  
  
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public MiseValue rechercheMiseParReference(String reference);
  

}
