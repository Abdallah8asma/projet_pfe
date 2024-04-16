package com.gpro.consulting.tiers.logistique.persistance.gc.boncommande;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;

/**
 * BonCommande Persistance interface
 * 
  *@author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IBonCommandePersistance {

	public String create(CommandeVenteValue bonCommandeValue);

	public CommandeVenteValue getById(Long id);

	public String update(CommandeVenteValue bonCommandeValue);

	public void delete(Long id);
	
	public List<CommandeVenteValue> getAll();
	
	public ResultatRechecheBonCommandeValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeValue request);
	
	public List<CommandeVenteValue> getReferenceBCByClientId(Long clientId);
	
	public CommandeVenteValue getByReference(String reference);
	
	
	
	public List<CommandeVenteValue> getByIdReglement(Long id);

	public List<CommandeVenteValue> getProduitElementList(List<String> refBonCommandesList);
}
