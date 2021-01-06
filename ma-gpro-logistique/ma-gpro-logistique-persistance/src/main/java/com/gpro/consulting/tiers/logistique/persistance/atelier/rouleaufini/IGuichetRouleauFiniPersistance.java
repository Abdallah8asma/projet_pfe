package com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.GuichetRouleauFiniValue;

/**
 * Générateur de référence du Bon Reception
 * @author rkhaskho
 *
 */
public interface IGuichetRouleauFiniPersistance {

	
	  /**
	   * Méthode de récupération du numéro du Rouleau Fini dans la persistance.
	   * 
	   * @return le numéro du prochain bon de reception 
	   */
	  public Long getNextNumReference();

	  /**
	   * Méthode de modification d'un numéro de référence dans le guichet du Rouleau Fini pour l'année
	   * courante..
	   * @param pGuichetValeur le guichet à modifier
	   * @return l'année du guichet
	   */
	  public Long modifierGuichetRouleauFini(GuichetRouleauFiniValue pGuichetValeur);

	public Integer getPrefixe();


}
