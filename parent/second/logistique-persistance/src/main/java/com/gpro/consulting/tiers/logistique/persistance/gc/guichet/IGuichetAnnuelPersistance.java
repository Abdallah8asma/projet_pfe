package com.gpro.consulting.tiers.logistique.persistance.gc.guichet;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;

public interface IGuichetAnnuelPersistance {

	/**
	 * Méthode de récupération du numéro du bon de sortie dans la persistance.
	 * 
	 * @return le numéro du prochain bon de sortie
	 */

	/**
	 * Méthode de récupération du numéro du bon de livraison dans la persistance.
	 * 
	 * @return le numéro du prochain bon de livraison
	 */

	public Long getNextNumBonComptoirReference();

	public Long getNextNumBonCommandeReference();

	public Long getNextNumFactureReference();

	public Long getNextNumBLReference();

	public Long getNextNumAvoirReference();

	public Long getNextNumReglementReference();

	/**
	 * Méthode de modification d'un numéro de référence dans le guichet pour l'année
	 * courante..
	 * 
	 * @param pGuichetValeur le guichet à modifier
	 * @return l'année du guichet
	 */
	public Long modifierGuichetFactureAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetAvoirAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetBonCommandeAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetReglementAnnuel(GuichetAnnuelValue pGuichetValeur);

	public GuichetAnnuelValue getById(Long id);

	public String modifierGuichetAnnuel(GuichetAnnuelValue pGuichetAnnuelValue);

	public List<GuichetAnnuelValue> getAll();

	public Long modifierGuichetBLAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetBonComptoirAnnuel(GuichetAnnuelValue pGuichetValeur);

	public GuichetAnnuelValue getCurrentGuichetAnnuel();

	public Long modifierGuichetBRAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetFactureAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long getNextNumReglementAchatReference();

	public Long modifierGuichetReglementAchatAnnuel(GuichetAnnuelValue vGuichetValeur);

	public Long modifierGuichetBCAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetBCAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetDevisAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetDevisAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetFactureAvoirAchatAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetBonStockAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetBonInventaireAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetBonTransfertReceptionAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetBonTransfertSortieAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	// stock

	public Long getNextNumBonMouvementEntre();

	public Long getNextNumBonMouvementSortie();

	public Long modifierGuichetBonMouvementEntreAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetBonMouvementSortieAnnuel(GuichetAnnuelValue pGuichetValeur);

	public Long modifierGuichetBLNDAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetFANDAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public GuichetAnnuelValue getCurrentGuichetAnnuel(Calendar c);

	public Long modifierGuichetReglementInverseAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

	public Long modifierGuichetReglementNonDeclarerAnnuel(GuichetAnnuelValue currentGuichetAnnuel);

}
