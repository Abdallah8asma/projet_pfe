package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.BanqueValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IBanquePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IBanquePartieInteresseeService;

/**
 * The Class BanquePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class BanquePartieInteresseeServiceImpl implements IBanquePartieInteresseeService {
  @Autowired
  IBanquePartieInteresseeDomaine categoriePartieInteresseeDomaine;

  /*
   * methode de creation cathegorie Partie Interessée
   */
  @Override
  public String creerBanquePartieInteressee(BanqueValue pBanqueValue) {

    return categoriePartieInteresseeDomaine.creerBanquePartieInteressee(pBanqueValue);
  }

  /******************* supprimer categorie partie interesse ************************/

  @Override
  public void supprimerBanquePartieInteressee(Long pBanqueValue) {
    categoriePartieInteresseeDomaine.supprimerBanquePartieInteressee(pBanqueValue);
  }

  /******************* modifier categorie partie interesse ************************/
  @Override
  public String modifierBanquePartieInteressee(BanqueValue pBanqueValue) {
    return categoriePartieInteresseeDomaine.modifierBanquePartieInteressee(pBanqueValue);
  }

  /******************* recherche categorie partie interesse par Id ************************/

  @Override
  public BanqueValue rechercheBanquePartieInteresseeParId(BanqueValue pBanqueValue) {
    return categoriePartieInteresseeDomaine.rechercheBanquePartieInteresseeParId(pBanqueValue);
  }

  @Override
  public List < BanqueValue > listeBanquePartieInteressee() {
    return categoriePartieInteresseeDomaine.listeBanquePartieInteressee();
  }
  
}
