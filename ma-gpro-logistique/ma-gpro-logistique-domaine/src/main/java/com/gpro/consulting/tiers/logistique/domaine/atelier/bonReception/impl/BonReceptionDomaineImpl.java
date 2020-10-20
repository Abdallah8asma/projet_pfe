package com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.IBonReceptionDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.IGuichetBonReceptionDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.cache.IGestionnaireLogistiqueCacheDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IBonReceptionPersistance;

/**
 * Implémentation des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */

@Component
public class BonReceptionDomaineImpl implements IBonReceptionDomaine {
  /** Service Persisatncce */
  @Autowired
  private IBonReceptionPersistance vBonReceptionPersitance;   
/** Service Guichet */
  @Autowired
  private IGuichetBonReceptionDomaine guichetBonReceptionDomaine;
  
  @Autowired
  private IGestionnaireLogistiqueCacheDomaine gestionnaireLogistiqueCacheDomaine;
  
  /** Logger */
  private static final Logger LOGGER = LoggerFactory
			.getLogger(BonReceptionDomaineImpl.class);
  /**
   * {@inheritDoc}
   */
  @Override
  public String creerBonReception(BonReceptionValue pBonReceptionValue) {
	  
//LOGGER.info("B--RefBR "+  pBonReceptionValue.getReference());
	  pBonReceptionValue.setReference(getNumeroBonReception(Calendar.getInstance()));
//LOGGER.info("A--RefBR "+  pBonReceptionValue.getReference());
	
      pBonReceptionValue.getReference().concat(getNumeroBonReception(Calendar.getInstance()));
      
   // Set fini attribute to TRUE if the Client is STIT, the STIT id is fixed to 1
      if(pBonReceptionValue.getPartieInteressee().equals(IConstanteLogistique.STIT_CLIENT_ID)){
    	  pBonReceptionValue.setFini(true);
      }else{
    	  pBonReceptionValue.setFini(false);
      }
      
    return vBonReceptionPersitance.creerBonReception(pBonReceptionValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void supprimerBonReception(Long pId) {
    vBonReceptionPersitance.supprimerBonReception(pId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String modifierBonReception(BonReceptionValue pBonReception) {
	  
	  // Set fini attribute to TRUE if the Client is STIT, the STIT id is fixed to 1
      if(pBonReception.getPartieInteressee().equals(IConstanteLogistique.STIT_CLIENT_ID)){
    	  pBonReception.setFini(true);
      }else{
    	  pBonReception.setFini(false);
      }
      
    return vBonReceptionPersitance.modifierBonReception(pBonReception);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BonReceptionValue rechercheBonReceptionParId(BonReceptionValue pBonReception) {

    return vBonReceptionPersitance.rechercheBonReceptionParId(pBonReception);
  }
  
  
  /**
   * {@inheritDoc}
   */
  @Override
  public ResultatRechecheBonReceptionValue rechercherBonReceptionMultiCritere(
    RechercheMulticritereBonReceptionValue pRechercheBonReceptionMulitCritere) {
    return vBonReceptionPersitance.rechercherBonReceptionMultiCritere(pRechercheBonReceptionMulitCritere);
  }
/**
   * Récuperer le numéro du Bon de receptionà partir du Guichet Bon Reception
   * 
   * Le Numéro Bon Reception est unique pour une Bon Reception OSIRIS et doit respect le format:
   * 
   * - AAAA : année du Bon Reception. - NNNNNN : un numéro incrémental au sein de l'année civile.
   * 
   * @param pDateBonReception
   *          la date de la Bon Reception
   * @return le numéro du prochaine Bon Reception à insérer
   */
  private String getNumeroBonReception(final Calendar pDateBonReception) {

    Long vNumGuichetBonReception = this.guichetBonReceptionDomaine.getNextNumReference();
    /** Année courante. */
    int vAnneeCourante = pDateBonReception.get(Calendar.YEAR);
    /** Format du numero de la Bon Reception= AAAA-NNNNNN. */
    StringBuilder vNumBonReception = new StringBuilder("");
    vNumBonReception.append(vAnneeCourante);
    vNumBonReception.append("-");
    vNumBonReception.append(String.format("%06d", vNumGuichetBonReception));
    /** Inserer une nouvelle valeur dans Guichet BonReception. */
    GuichetBonReceptionValue vGuichetValeur = new GuichetBonReceptionValue();
    vGuichetValeur.setAnnee(new Long(vAnneeCourante));
    vGuichetValeur.setNumReferenceCourant(new Long(vNumGuichetBonReception + 1L));

    /** Modification de la valeur en base du numéro. */
    this.guichetBonReceptionDomaine.modifierGuichetBonReception(vGuichetValeur);

    return vNumBonReception.toString();

  }

	@Override
	public BonReceptionValue rechercheBonReceptionByReference(String reference) {
		
		return vBonReceptionPersitance.rechercheBonReceptionByReference(reference);
	}

	@Override
	public List<BonReceptionValue> getAll() {
		return vBonReceptionPersitance.getAll();
	}

}
