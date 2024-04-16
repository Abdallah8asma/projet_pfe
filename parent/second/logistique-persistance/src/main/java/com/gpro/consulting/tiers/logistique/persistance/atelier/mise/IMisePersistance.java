package com.gpro.consulting.tiers.logistique.persistance.atelier.mise;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;

/**
 * Interface des méthodes CRUD du mise
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IMisePersistance {

	/**
	 * Méthode de création d'une partie intéressée et de l'inserer dans la base
	 * de données avec toutes ses associations.
	 * 
	 * @param pPartieInteresseValue
	 * @return
	 */
	public String creerMise(MiseValue pMiseValue);

	/**
	 * Méthode de suppression d'un Bon de reception par id.
	 * 
	 * @param pId
	 */
	public void supprimerMise(Long pId);

	/**
	 * Méthode de modification d'un bon de reception par id retournant un objet
	 * Entité.
	 * 
	 * @param pBonReception
	 * @return
	 */
	public String modifierMise(MiseValue pMise);

	/**
	 * Méthode de recherche d'un bon de reception par id retournant un objet
	 * Valeur.
	 * 
	 * @param pBonReception
	 * @return
	 */
	public MiseValue rechercheMiseParId(Long pMise);

	ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue pRechercheMiseMulitCritere);

	 public List<MiseValue> listerMise();
	 
	 public List<MiseValue> getMiseByReference(String referenceMise);
	 
	 public TraitementMiseValue getTraitementMiseById(Long id);
	
	 public List<String> getListRefMiseParRefBR(String referenceBR);
	 
	 public List<MiseValue> getReferenceMise();
	 
	 public MiseValue rechercheMiseParReference(String pMise);
}
