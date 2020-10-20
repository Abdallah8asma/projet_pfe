package com.gpro.consulting.tiers.logistique.persistance.gc.reception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;

/**
 * BonCommande Persistance interface
 * 
  *@author Zeineb Medimagh
 * @since 16/11/2016
 *
 */
public interface IReceptionPersistance {

	public String create(ReceptionVenteValue bonCommandeValue);

	public ReceptionVenteValue getById(Long id);

	public String update(ReceptionVenteValue bonCommandeValue);

	public void delete(Long id);
	
	public List<ReceptionVenteValue> getAll();
	
	public ResultatRechecheBonReceptionValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionValue request);
}
