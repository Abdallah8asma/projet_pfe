package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.MetrageValue;


public interface IMetrageService {

  /************************** ajouter Metrage ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerMetrage(MetrageValue pMetrageValue);

  /********************** supprimer Metrage *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerMetrage(Long long1);

  /********************** modifier Metrage *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierMetrage(MetrageValue pMetrageValue);

  /********************** recherche Metrage ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public MetrageValue rechercheMetrageParId(MetrageValue pMetrageValue);

  /****************** afficher liste Metrage **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < MetrageValue > listeMetrage();
}
