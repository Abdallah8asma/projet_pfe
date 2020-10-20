package com.gpro.consulting.tiers.logistique.service.atelier.bonReception.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.IBonReceptionDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.bonReception.IBonReceptionService;

/**
 * Implementation des services du bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
@Service
@Transactional
public class BonReceptionServiceImpl implements IBonReceptionService {

  /** Service Domaine */
  @Autowired
  private IBonReceptionDomaine vBonReceptionDomaine;

  /**
   * {@inheritDoc}
   */
  @Override
  public String creerBonReception(BonReceptionValue pBonReceptionValue) {

    return vBonReceptionDomaine.creerBonReception(pBonReceptionValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void supprimerBonReception(Long pId) {
    vBonReceptionDomaine.supprimerBonReception(pId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String modifierBonReception(BonReceptionValue pBonReception) {
    return vBonReceptionDomaine.modifierBonReception(pBonReception);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BonReceptionValue rechercheBonReceptionParId(BonReceptionValue pBonReception) {

    return vBonReceptionDomaine.rechercheBonReceptionParId(pBonReception);
  }

  
  /**
   * {@inheritDoc}
   */
  @Override
  public ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    RechercheMulticritereBonReceptionValue pRechercheBonReceptionMulitCritere) {
    return vBonReceptionDomaine.rechercherBonReceptionMultiCritere(pRechercheBonReceptionMulitCritere);
  }

  
  @Override
  public BonReceptionValue rechercheBonReceptionByReference(String reference) {
	
	return vBonReceptionDomaine.rechercheBonReceptionByReference(reference);
  }

@Override
public List<BonReceptionValue> getAll() {
	return vBonReceptionDomaine.getAll();
}

}
