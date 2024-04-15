package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IPartieInteresseeService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service("partieInteresseeService")
@Transactional(rollbackFor = Exception.class)
public class PartieInteresseeServiceeImpl implements IPartieInteresseeService {

  @Autowired
  IPartieInteresseeDomaine partieInteresseeDomaine;

  /*
   * methode de creation Partie Interessée
   */
  @Override
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
    return this.partieInteresseeDomaine.creerPartieInteressee(pPartieInteresseValue);
  }

  /**************** supprimer partie interessee *******************/
  @Override
  public void supprimerPartieInteressee(Long pId) {
    this.partieInteresseeDomaine.supprimerPartieInteressee(pId);
  }

  /**************** modifier partie interessee *******************/
  @Override
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
    return partieInteresseeDomaine.modifierPartieInteressee(pPartieInteresseValue);
  }

  /**************** recherche partie interessee Par Id *******************/

  @Override
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue) {
    return this.partieInteresseeDomaine.recherchePartieInteresseeParId(pPartieInteresseValue);
  }

  /**************** liste partie interessee *******************/

  @Override
  public List < PartieInteresseValue > listePartieInteressee() {
    return this.partieInteresseeDomaine.listePartieInteressee();
  }

  /******************* recherche multi critere ********************/
  @Override
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {
    return this.partieInteresseeDomaine.rechercherPartieInteresseMultiCritere(pRecherchePartieInteresseMulitCritere);
  }


  /********liste pi cache (Abreviation et id)********/
	@Override
	public List<PartieInteresseCacheValue> listePartieInteresseeCache() {
		   return partieInteresseeDomaine.listePartieInteresseeCache();
	}

	@Override
	public PartieInteresseValue getById(Long id) {
		
		return partieInteresseeDomaine.getById(id);
	}

	@Override
	public String getCurrentReferenceByFamille(Long id ,boolean increment) {
		// TODO Auto-generated method stub
		return partieInteresseeDomaine.getCurrentReferenceByFamille(id , increment);
	}
	
	

}
