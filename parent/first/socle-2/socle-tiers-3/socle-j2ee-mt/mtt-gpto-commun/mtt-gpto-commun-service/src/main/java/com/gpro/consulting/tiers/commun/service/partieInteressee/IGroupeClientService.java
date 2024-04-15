package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;

public interface IGroupeClientService {
  /************** creer categorie partie interesse ***************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerGroupeClient(GroupeClientValue pGroupeClientValue);

  /********************** supprimer categorie partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerGroupeClient(Long pGroupeClientValue);

  /********************** modifier categorie partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierGroupeClient(GroupeClientValue pGroupeClientValue);

  /********************** recherche categorie partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public GroupeClientValue rechercheGroupeClientParId(GroupeClientValue pGroupeClientValue);

  /********************** afficher liste categorie partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < GroupeClientValue > listeGroupeClient();

}
