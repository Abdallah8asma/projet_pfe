package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.GuichetRouleauFiniValue;

/**
 * Générateur de référence du Rouleau Fini
 * @author rkhaskho
 *
 */
public interface IGuichetRouleauFiniDomaine {

	
	  /**
	   * Méthode de récupération du numéro du Rouleau Fini dans la persistance.
	   * 
	   * @return le numéro du prochain bon de reception 
	   */
	  public Long getNextNumReference();
	  
	  public Integer getPrefixe();

	  /**
	   * Méthode de modification d'un numéro de référence dans le guichet du Rouleau Fini pour l'année
	   * courante..
	   * @param pGuichetValeur le guichet à modifier
	   * @return l'année du guichet
	   */
	  public Long modifierGuichetBonReception(GuichetRouleauFiniValue pGuichetValeur);


}
