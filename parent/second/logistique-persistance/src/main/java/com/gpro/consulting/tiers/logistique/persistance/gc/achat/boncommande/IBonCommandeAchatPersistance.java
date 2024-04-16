package com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;

/**
 * BonCommande Persistance interface
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IBonCommandeAchatPersistance {

	public String create(CommandeAchatValue bonCommandeValue);

	public CommandeAchatValue getById(Long id);

	public String update(CommandeAchatValue bonCommandeValue);

	public void delete(Long id);

	public List<CommandeAchatValue> getAll();

	public ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeAchatValue request);

	public List<CommandeAchatValue> getReferenceBCByClientId(Long clientId);

	public List<CommandeAchatValue> getProduitElementList(List<String> refBonCommandesList);
}
