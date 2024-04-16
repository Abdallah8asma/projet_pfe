package com.gpro.consulting.tiers.logistique.service.gc.bonCommande;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;

/**
 * BonCommande Service interface
 * 
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IBonCommandeService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(CommandeVenteValue bonCommandeValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public CommandeVenteValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(CommandeVenteValue bonCommandeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<CommandeVenteValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheBonCommandeValue rechercherMultiCritere(RechercheMulticritereBonCommandeValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<CommandeVenteValue> getListBonCommandeRefByClient(Long idClient);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceByType(String type,Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long livraisonVenteId);
}
