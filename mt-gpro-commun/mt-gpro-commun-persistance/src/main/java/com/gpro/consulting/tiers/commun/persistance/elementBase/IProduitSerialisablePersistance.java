package com.gpro.consulting.tiers.commun.persistance.elementBase;
import java.util.List;
import java.util.Set;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitSerialisableValue;

/**
 * The Interface IProduitSerialisablePersistance.
 * @author med
 */
public interface IProduitSerialisablePersistance {
	
	/**
	 * Creer ProduitSerialisable.
	 *
	 * @param pProduitSerialisableValue the ProduitSerialisable value
	 * @return the string
	 */
	public  String creerProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue);
	
	/**
	 * Supprimer ProduitSerialisable.
	 *
	 * @param pProduitSerialisableId the ProduitSerialisable id
	 */
	public  void supprimerProduitSerialisable(Long pProduitSerialisableId);
	
	/**
	 * Modifier ProduitSerialisable.
	 *
	 * @param pProduitSerialisableValue the ProduitSerialisable value
	 * @return the string
	 */
	public String modifierProduitSerialisable(ProduitSerialisableValue pProduitSerialisableValue);
	
	/**
	 * Recherche ProduitSerialisable by id.
	 *
	 * @param pProduitSerialisableId the ProduitSerialisable id
	 * @return the ProduitSerialisable value
	 */
	public ProduitSerialisableValue rechercheProduitSerialisableById(Long pProduitSerialisableId);
	
	/**
	 * Liste ProduitSerialisable.
	 *
	 * @return the list 
	 */
	public List<ProduitSerialisableValue> listeProduitSerialisable();
	
	
	public ProduitSerialisableValue rechercheProduitSerialisableByNumeroSerieAndProduitId(String numSerie,Long ProduitId);
	
	//public ProduitSerialisableValue rechercheProduitSerialisableByNumeroSerie(String numSerie);
	
	
	//recherche multi criteres
	
	 public ResultatRechecheProduitSerialisableValue rechercherProduitSerialisableMultiCritere(RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere);

	//public Set<ProduitSerialisableValue> getByNumeroSerieList(List<String> listNumeroSeries);
	
	
	public Set<ProduitSerialisableValue> getByNumeroSerieList(List<String> listNumeroSeries,Long produitId);

	Set<ProduitSerialisableValue> getByNumeroSerieListEnrichi(List<String> listNumeroSeries, Long idProduit);
	
}
