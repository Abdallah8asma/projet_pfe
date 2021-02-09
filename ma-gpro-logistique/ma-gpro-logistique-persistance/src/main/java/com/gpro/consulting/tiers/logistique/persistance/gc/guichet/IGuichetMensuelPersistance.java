package com.gpro.consulting.tiers.logistique.persistance.gc.guichet;

import java.util.Calendar;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;

public interface IGuichetMensuelPersistance {

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
	  public Long getNextNumBonLivraisonReference();
	
	
	public Long getNextNumBonSortieReference();

	  /**
	   * Méthode de modification d'un numéro de référence dans le guichet  pour l'année
	   * courante..
	   * @param pGuichetValeur le guichet à modifier
	   * @return l'année du guichet
	   */
	  public Long modifierGuichetBonLivraisonMensuel(GuichetMensuelValue pGuichetValeur);

	  public Long modifierGuichetBonSortieMensuel(GuichetMensuelValue pGuichetValeur);


	public Long getNextNumBonCommandeReference();


	public Long modifierGuichetBonCommandeMensuel(GuichetMensuelValue pGuichetValeur);


	public Long modifierGuichetFactureAvoirMensuel(GuichetMensuelValue pGuichetValeur);


	public Long getNextNumfactureAvoirReference(Calendar pDateBonLiv);


	public Long modifierGuichetFactureMensuel(GuichetMensuelValue pGuichetValeur);


	public Long getNextNumfactureReference(Calendar c);


	public Long modifierGuichetBonReceptionMensuel(GuichetMensuelValue pGuichetValeur);


	public Long getNextNumBonReceptionReference(Calendar c);





	public GuichetMensuelValue getCurrentGuichetMensuel();
	

	public String getPrefix();


	public String getPrefixBonReception(Calendar c);
	public String getPrefixFacture(Calendar c);
	public String getPrefixFactureAvoir(Calendar c);


	public Long modifierGuichetBonReceptionNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumBonReceptionReferenceNonDeclarer(Calendar c);


	public String getPrefixBonReceptionNonDeclarer(Calendar c);


	public Long getNextNumfactureAchatReferenceNondeclarer(Calendar c);


	public String getPrefixFactureAchatNondeclarer(Calendar c);


	public Long modifierGuichetFactureAchatNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur);


	
	
}
