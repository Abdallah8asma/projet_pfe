package com.gpro.consulting.tiers.logistique.domaine.gc.guichet;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.RechercheMulticritereGuichetMensuelValue;

public interface IGuichetMensuelDomaine {
   
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


	public Long getNextNumBonComptoirReference();


	public void modifierGuichetBonComptoirMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumBonCommandeReference();

	public Long modifierGuichetBonCommandeMensuel(GuichetMensuelValue pGuichetValeur);
	
	public Long getNextNumBonReceptionReference(Calendar pDateBonLiv);

	public Long modifierGuichetBonReceptionMensuel(GuichetMensuelValue pGuichetValeur);
	
	public Long getNextNumfactureReference(Calendar pDateBonLiv);	
	public Long modifierGuichetFactureMensuel(GuichetMensuelValue pGuichetValeur);
	
	public Long modifierGuichetFactureAvoirMensuel(GuichetMensuelValue pGuichetValeur);


	public GuichetMensuelValue getCurrentGuichetMensuel();


	public String getPrefix();
	
	public String getPrefixFacture(Calendar pDateBonLiv);
	


	public String getPrefixBonReception(Calendar pDateBonLiv);


	public Long modifierGuichetBonReceptionNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumBonReceptionReferenceNonDeclarer(Calendar pDateBonLiv);


	public String getPrefixBonReceptionNonDeclarer(Calendar pDateBonLiv);


	public Long getNextNumfactureAchatReferenceNondeclarer(Calendar pDate);


	public String getPrefixFactureAchatNondeclarer(Calendar pDate);


	public Long modifierGuichetFactureAchatNonDeclarerMensuel(GuichetMensuelValue vGuichetValeur);


	public Long getNextNumfactureAvoirReference(Calendar pDateBonLiv);


	public String getPrefixFactureAvoir(Calendar pDateBonLiv);


	public GuichetMensuelValue getByAnneeAndMois(Long year, Long month);


	public GuichetMensuelValue getById(Long id);


	public String update(GuichetMensuelValue guichetMensuelValue);


	public String create(GuichetMensuelValue guichetMensuelValue);


	public void deleteById(Long id);


	public List<GuichetMensuelValue> rechercheMultiCritere(RechercheMulticritereGuichetMensuelValue request);



	
	
}
