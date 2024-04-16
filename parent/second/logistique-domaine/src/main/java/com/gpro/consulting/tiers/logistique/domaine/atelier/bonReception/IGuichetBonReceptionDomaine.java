package com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;

// TODO: Auto-generated Javadoc
/**
 * Générateur de référence du Bon Reception.
 *
 * @author rkhaskho
 */
public interface IGuichetBonReceptionDomaine {

	
	  /**
	   * Méthode de récupération du numéro du bon de reception dans la persistance.
	   * 
	   * @return le numéro du prochain bon de reception 
	   */
	  public Long getNextNumReference();

	  /**
	   * Méthode de modification d'un numéro de référence dans le guichet du bon de reception pour l'année
	   * courante..
	   * @param pGuichetValeur le guichet à modifier
	   * @return l'année du guichet
	   */
	  public Long modifierGuichetBonReception(GuichetBonReceptionValue pGuichetValeur);


}
