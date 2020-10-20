package com.gpro.consulting.tiers.commun.service.elementBase;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereRemiseValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RemiseValue;


public interface IRemiseService {
  /************************** ajouter Remise ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerRemise(RemiseValue pRemiseValue);

  /********************** supprimer Remise *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerRemise(Long pId);

  /********************** modifier Remise *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierRemise(RemiseValue pRemiseValue);

  /********************** recherche Remise ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public RemiseValue rechercheRemiseParId(RemiseValue pRemiseValue);

  /****************** afficher liste Remise **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < RemiseValue > listeRemise();
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<RemiseValue> rechercheMulticritere(RechercheMulticritereRemiseValue request);
}
