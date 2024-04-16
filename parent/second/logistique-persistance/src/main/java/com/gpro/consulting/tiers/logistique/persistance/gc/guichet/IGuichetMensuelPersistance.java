package com.gpro.consulting.tiers.logistique.persistance.gc.guichet;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;

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


	public Long getNextNumReglementAchat(Calendar c);


	public String getPrefixReglementAchat(Calendar c);


	public Long modifierGuichetReglementAchatMensuel(GuichetMensuelValue vGuichetValeur);
	
	
	
	
	public Long getNextNumDetReglementAchat(Calendar c);


	public String getPrefixDetReglementAchat(Calendar c);


	public Long modifierGuichetDetReglementAchatMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumDetReglement(Calendar c);


	public String getPrefixDetReglement(Calendar c);


	public Long modifierGuichetDetReglementMensuel(GuichetMensuelValue vGuichetValeur);


	public GuichetMensuelValue getByAnneeAndMois(Long year, Long month);


	public GuichetMensuelValue getById(Long id);


	public String update(GuichetMensuelValue guichetMensuelValue);


	public String create(GuichetMensuelValue guichetMensuelValue);


	public void deleteById(Long id);


	public List<GuichetMensuelValue> rechercheMultiCritere(RechercheMulticritereGuichetMensuelValue request);


	public Long getNextNumReglementInverseAchat(Calendar c);


	public String getPrefixReglementInverseAchat(Calendar c);


	public Long modifierGuichetReglementInverseAchatMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumDetReglementInverseAchat(Calendar c);


	public String getPrefixDetReglementInverseAchat(Calendar c);


	public Long modifierGuichetDetReglementInverseAchatMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumDetReglementInverse(Calendar c);


	public String getPrefixDetReglementInverse(Calendar c);


	public Long modifierGuichetDetReglementInverseMensuel(GuichetMensuelValue vGuichetValeur);


	public String getPrefixReglementInverse(Calendar c);


	public Long getNextNumReglementInverse(Calendar c);


	public Long modifierGuichetReglementInverseMensuel(GuichetMensuelValue vGuichetValeur);


	
	
}
