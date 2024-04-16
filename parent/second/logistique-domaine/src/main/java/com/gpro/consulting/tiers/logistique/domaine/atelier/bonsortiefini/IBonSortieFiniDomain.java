package com.gpro.consulting.tiers.logistique.domaine.atelier.bonsortiefini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniOptimizedValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;

// TODO: Auto-generated Javadoc
/**
 * BonSortieFini Domaine interface.
 *
 * @author Wahid Gazzah
 * @since 08/01/2016
 */
public interface IBonSortieFiniDomain {

	/**
	 * Validate bon sortie fini.
	 *
	 * @param barreCodeList the barre code list
	 * @param id the id
	 * @return the list
	 */
	public List<RouleauFiniValue> validateBonSortieFini(List<String> barreCodeList, Long id);
	
	/**
	 * Creates the bon sortie fini.
	 *
	 * @param bonSortieFiniValue the bon sortie fini value
	 * @return the string
	 */
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	/**
	 * Gets the bon sortie fini by id.
	 *
	 * @param id the id
	 * @return the bon sortie fini by id
	 */
	public BonSortieFiniValue getBonSortieFiniById(Long id);

	/**
	 * Rechercher multi critere.
	 *
	 * @param request the request
	 * @return the resultat recheche bon sortie fini value
	 */
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(RechercheMulticritereBonSortieFiniValue request);

	/**
	 * Update bon sortie fini.
	 *
	 * @param bonSortieFiniValue the bon sortie fini value
	 * @return the string
	 */
	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	/**
	 * Delete bon sortie fini.
	 *
	 * @param id the id
	 */
	public void deleteBonSortieFini(Long id);

	/**
	 * Gets the produit element list.
	 *
	 * @param refBonSortieList the ref bon sortie list
	 * @param livraisonVenteId the livraison vente id
	 * @return the produit element list
	 */
	public ListProduitElementValue getProduitElementList(List<String> refBonSortieList, Long livraisonVenteId);

	/**
	 * Gets the list bon sortie fini ref.
	 *
	 * @return the list bon sortie fini ref
	 */
	public List<String> getListBonSortieFiniRef();


	/**
	 * Gets the trait facon element list.
	 *
	 * @param refBonLivraisonList the ref bon livraison list
	 * @param factureVenteId the facture vente id
	 * @return the trait facon element list
	 */
	// Added on 03/10/2016, by Zeineb Medimagh
	public ListTraitFaconElementValue getTraitFaconElementList(
			List<String> refBonLivraisonList, Long factureVenteId);



	public List<BonSortieFiniOptimizedValue> getBonSortieEnCours();
	
	public BonSortieFiniOptimizedValue getBonSortieFiniOptimizedById(Long id);
	
	public List<BonSortieFiniValue> getListByBonSortieList(List<String> refBonSortieList);

	public ListProduitElementValue getProduitElementListByOF(List<String> refBonSortieList, Long livraisonVenteId);

	public List<String> getListBonSortieFiniRefByClientId(Long clientId);

}
