package com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.IBonCommandeAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.bonCommande.IBonCommandeAchatService;

/**
 * @author Hamdi Etteieb
 * @since 16/11/2016
 *
 */

@Component
public class BonCommandeAchatServiceImpl implements IBonCommandeAchatService {

	@Autowired
	private IBonCommandeAchatDomaine bonCommandeDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IBonCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.CommandeAchatValue)
	 */
	@Override
	public String create(CommandeAchatValue bonCommandeValue) {
		return bonCommandeDomaine.create(bonCommandeValue);
	}

	@Override
	public CommandeAchatValue getById(Long id) {
		return bonCommandeDomaine.getById(id);
	}

	@Override
	public String update(CommandeAchatValue bonCommandeValue) {
		return bonCommandeDomaine.update(bonCommandeValue);
	}

	@Override
	public void delete(Long id) {
		bonCommandeDomaine.delete(id);
	}

	@Override
	public List<CommandeAchatValue> getAll() {
		return bonCommandeDomaine.getAll();
	}

	@Override
	public ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeAchatValue request) {
		return bonCommandeDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<CommandeAchatValue> getListBonCommandeRefByClient(Long idClient) {

		return bonCommandeDomaine.getListBonCommandeRefByClient(idClient);
	}

	@Override
	public String getCurrentReferenceByType(String type,Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return bonCommandeDomaine.getCurrentReferenceByType(type,instance,b);
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long receptionAchatId) {
		return bonCommandeDomaine.getProduitElementList(refBonCommandesList,receptionAchatId);
	}

	@Override
	public String getCurrentReferenceMensuel(Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return  bonCommandeDomaine.getCurrentReferenceMensuel(instance,b);
	}

}
