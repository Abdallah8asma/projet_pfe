package com.gpro.consulting.tiers.logistique.service.gc.reception;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;


/**
 * BonCommande Service interface
 * 
 * Hajer AMRI 16/03/2017
 */
public interface IReceptionService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ReceptionVenteValue bonReceptionValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReceptionVenteValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ReceptionVenteValue bonReceptionValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReceptionVenteValue> getAll();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheBonReceptionValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionValue request);
}
