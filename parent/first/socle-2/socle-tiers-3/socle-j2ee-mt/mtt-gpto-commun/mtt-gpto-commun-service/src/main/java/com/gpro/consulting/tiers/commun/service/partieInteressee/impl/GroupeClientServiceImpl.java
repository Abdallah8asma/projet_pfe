package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IGroupeClientDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IGroupeClientService;

/**
 * The Class GroupeClientImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class GroupeClientServiceImpl implements IGroupeClientService {
  @Autowired
  IGroupeClientDomaine groupeClientDomaine;

  /*
   * methode de creation cathegorie Partie Interessée
   */
  @Override
  public String creerGroupeClient(GroupeClientValue pGroupeClientValue) {

    return groupeClientDomaine.creerGroupeClient(pGroupeClientValue);
  }

  /******************* supprimer categorie partie interesse ************************/

  @Override
  public void supprimerGroupeClient(Long pGroupeClientValue) {
    groupeClientDomaine.supprimerGroupeClient(pGroupeClientValue);
  }

  /******************* modifier categorie partie interesse ************************/
  @Override
  public String modifierGroupeClient(GroupeClientValue pGroupeClientValue) {
    return groupeClientDomaine.modifierGroupeClient(pGroupeClientValue);
  }

  /******************* recherche categorie partie interesse par Id ************************/

  @Override
  public GroupeClientValue rechercheGroupeClientParId(GroupeClientValue pGroupeClientValue) {
    return groupeClientDomaine.rechercheGroupeClientParId(pGroupeClientValue);
  }

  @Override
  public List < GroupeClientValue > listeGroupeClient() {
    return groupeClientDomaine.listeGroupeClient();
  }
  
}
