package com.gpro.consulting.tiers.commun.service.partieInteressee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;

public interface IBanquePartieInteresseeService {
  /************** creer categorie partie interesse ***************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerBanquePartieInteressee(BanqueValue pBanqueValue);

  /********************** supprimer categorie partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerBanquePartieInteressee(Long pBanqueValue);

  /********************** modifier categorie partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierBanquePartieInteressee(BanqueValue pBanqueValue);

  /********************** recherche categorie partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public BanqueValue rechercheBanquePartieInteresseeParId(BanqueValue pBanqueValue);

  /********************** afficher liste categorie partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < BanqueValue > listeBanquePartieInteressee();

}
