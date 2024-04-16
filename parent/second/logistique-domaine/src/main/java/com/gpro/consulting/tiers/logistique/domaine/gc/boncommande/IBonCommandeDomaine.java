package com.gpro.consulting.tiers.logistique.domaine.gc.boncommande;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;

/**
 * BonCommande Domaine interface
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IBonCommandeDomaine {

	public String create(CommandeVenteValue bonCommandeValue);

	public CommandeVenteValue getById(Long id);

	public String update(CommandeVenteValue bonCommandeValue);

	public void delete(Long id);

	public List<CommandeVenteValue> getAll();

	public ResultatRechecheBonCommandeValue rechercherMultiCritere(RechercheMulticritereBonCommandeValue request);

	public List<CommandeVenteValue> getListBonCommandeRefByClient(Long idClient);

	public String getCurrentReferenceByType(String type,Calendar instance, boolean b);

	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long livraisonVenteId);
}
