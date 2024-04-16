package com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;

/**
 * BonCommande Service interface
 * 
 * @author Hamdi Etteieb
 * @since 16/09/2018
 *
 */
public interface IBonCommandeAchatService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(CommandeAchatValue bonCommandeValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public CommandeAchatValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(CommandeAchatValue bonCommandeValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<CommandeAchatValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeAchatValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<CommandeAchatValue> getListBonCommandeRefByClient(Long idClient);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceByType(String type,Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long receptionAchatId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceMensuel(Calendar instance, boolean b);
}
