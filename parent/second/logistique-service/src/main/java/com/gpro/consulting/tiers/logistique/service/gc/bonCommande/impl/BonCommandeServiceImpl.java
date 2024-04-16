package com.gpro.consulting.tiers.logistique.service.gc.bonCommande.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IBonCommandeDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.bonCommande.IBonCommandeService;

/**
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class BonCommandeServiceImpl implements IBonCommandeService {

	@Autowired
	private IBonCommandeDomaine bonCommandeDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IBonCommandeDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.CommandeVenteValue)
	 */
	@Override
	public String create(CommandeVenteValue bonCommandeValue) {
		return bonCommandeDomaine.create(bonCommandeValue);
	}

	@Override
	public CommandeVenteValue getById(Long id) {
		return bonCommandeDomaine.getById(id);
	}

	@Override
	public String update(CommandeVenteValue bonCommandeValue) {
		// TODO Auto-generated method stub
		return bonCommandeDomaine.update(bonCommandeValue);
	}

	@Override
	public void delete(Long id) {
		bonCommandeDomaine.delete(id);
	}

	@Override
	public List<CommandeVenteValue> getAll() {
		return bonCommandeDomaine.getAll();
	}

	@Override
	public ResultatRechecheBonCommandeValue rechercherMultiCritere(RechercheMulticritereBonCommandeValue request) {
		return bonCommandeDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<CommandeVenteValue> getListBonCommandeRefByClient(Long idClient) {

		return bonCommandeDomaine.getListBonCommandeRefByClient(idClient);
	}

	@Override
	public String getCurrentReferenceByType(String type,Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return bonCommandeDomaine.getCurrentReferenceByType(type,instance,b);
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long livraisonVenteId) {
		// TODO Auto-generated method stub
		return bonCommandeDomaine.getProduitElementList(refBonCommandesList,livraisonVenteId);
	}

}
