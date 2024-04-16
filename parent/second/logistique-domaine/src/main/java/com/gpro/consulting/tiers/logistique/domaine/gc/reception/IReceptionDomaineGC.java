package com.gpro.consulting.tiers.logistique.domaine.gc.reception;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;

/**
 * Hajer AMRI
 *
 */
public interface IReceptionDomaineGC {


	public String create(ReceptionVenteValue bonReceptionValue);

	public ReceptionVenteValue getById(Long id);

	public String update(ReceptionVenteValue bonReceptionValue);

	public void delete(Long id);
	
	public List<ReceptionVenteValue> getAll();
	
	public ResultatRechecheBonReceptionValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionValue request);
}
