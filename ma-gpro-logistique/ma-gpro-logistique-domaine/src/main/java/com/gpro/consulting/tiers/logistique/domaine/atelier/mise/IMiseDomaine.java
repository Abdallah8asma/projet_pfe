package com.gpro.consulting.tiers.logistique.domaine.atelier.mise;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;

// TODO: Auto-generated Javadoc
/**
 * Interface des méthodes CRUD du Bon de reception.
 *
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IMiseDomaine {

	/**
	 * Méthode de création d'un bon de reception et de l'inserer dans la base de
	 * données avec toutes ses associations.
	 *
	 * @param pMiseValue
	 *            the mise value
	 * @return the string
	 */
	public String creerMise(MiseValue pMiseValue);

	/**
	 * Méthode de suppression d'un Bon de reception par id.
	 *
	 * @param pId
	 *            the id
	 */
	public void supprimerMise(Long pId);

	/**
	 * Méthode de modification d'un bon de reception par id retournant un objet
	 * Entité.
	 *
	 * @param pMise
	 *            the mise
	 * @return the string
	 */
	public String modifierMise(MiseValue pMise);

	/**
	 * Méthode de recherche d'un bon de reception par id retournant un objet
	 * Valeur.
	 *
	 * @param pId
	 *            the id
	 * @return the mise value
	 */
	public MiseValue rechercheMiseParId(Long pId);

	/**
	 * Rechercher mise multi critere.
	 *
	 * @param pRechercheMiseMulitCritere
	 *            the recherche mise mulit critere
	 * @return the resultat recherche mise value
	 */
	public ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue pRechercheMiseMulitCritere);

	/**
	 * Lister mise.
	 *
	 * @return the list
	 */
	public List<MiseValue> listerMise();

	/**
	 * Gets the mise by reference.
	 *
	 * @param referenceMise
	 *            the reference mise
	 * @return the mise by reference
	 */
	public List<MiseValue> getMiseByReference(String referenceMise);

	/**
	 * Gets the traitement mise by id.
	 *
	 * @param id
	 *            the id
	 * @return the traitement mise by id
	 */
	public TraitementMiseValue getTraitementMiseById(Long id);

	/**
	 * Gets the list ref mise par ref BR.
	 *
	 * @param referenceBR
	 *            the reference BR
	 * @return the list ref mise par ref BR
	 */
	public List<String> getListRefMiseParRefBR(String referenceBR);

	/**
	 * List ref mise par ref BR.
	 *
	 * @param referenceBR
	 *            the reference BR
	 * @return the string
	 */
	public String listRefMiseParRefBR(String referenceBR);

	  public MiseValue rechercheMiseParReference(String pId);

	  public List<MiseValue> getReferenceMise();
}
