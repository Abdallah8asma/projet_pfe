package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SocieteValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereSocieteValue;


public interface ISocieteService {
  /************************** ajouter Societe ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerSociete(SocieteValue pSocieteValue);

  /********************** supprimer Societe *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerSociete(Long pId);

  /********************** modifier Societe *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierSociete(SocieteValue pSocieteValue);

  /********************** recherche Societe ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public SocieteValue rechercheSocieteParId(SocieteValue pSocieteValue);

  /****************** afficher liste Societe **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < SocieteValue > listeSociete();
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<SocieteValue> rechercheMulticritere(RechercheMulticritereSocieteValue request);
}
