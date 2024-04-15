package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.BoutiqueValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereBoutiqueValue;


public interface IBoutiqueService {
  /************************** ajouter Boutique ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerBoutique(BoutiqueValue pBoutiqueValue);

  /********************** supprimer Boutique *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerBoutique(Long pId);

  /********************** modifier Boutique *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierBoutique(BoutiqueValue pBoutiqueValue);

  /********************** recherche Boutique ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public BoutiqueValue rechercheBoutiqueParId(BoutiqueValue pBoutiqueValue);

  /****************** afficher liste Boutique **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < BoutiqueValue > listeBoutique();
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<BoutiqueValue> rechercheMulticritere(RechercheMulticritereBoutiqueValue request);
}
