package com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;

/**
 * BonCommande Domaine interface
 * 
 * @author HamdiEtteieb
 * @since 16/11/2016
 *
 */
public interface IBonCommandeAchatDomaine {

	public String create(CommandeAchatValue bonCommandeValue);

	public CommandeAchatValue getById(Long id);

	public String update(CommandeAchatValue bonCommandeValue);

	public void delete(Long id);

	public List<CommandeAchatValue> getAll();

	public ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeAchatValue request);

	public List<CommandeAchatValue> getListBonCommandeRefByClient(Long idClient);

	public String getCurrentReferenceByType(String type,Calendar instance, boolean b);

	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long receptionAchatId);

	public String getCurrentReferenceMensuel(Calendar instance, boolean b);
}
