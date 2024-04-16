package com.gpro.consulting.tiers.logistique.service.gc.reception.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reception.IReceptionDomaineGC;
import com.gpro.consulting.tiers.logistique.service.gc.reception.IReceptionService;


/**
 *
 */

@Component
public class ReceptionServiceImpl implements IReceptionService{
	
	private static final Logger logger = LoggerFactory.getLogger(ReceptionServiceImpl.class);

	@Autowired
	private IReceptionDomaineGC receptionDomaineGC;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IBonReceptionDomaine#create(com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ReceptionVenteValue)
	 */
	@Override
	public String create(ReceptionVenteValue bonReceptionValue) {
		return receptionDomaineGC.create(bonReceptionValue);
	}

	@Override
	public ReceptionVenteValue getById(Long id) {
		return receptionDomaineGC.getById(id);
	}

	@Override
	public String update(ReceptionVenteValue bonReceptionValue) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.update(bonReceptionValue);
	}

	@Override
	public void delete(Long id) {
		receptionDomaineGC.delete(id);
	}

	@Override
	public List<ReceptionVenteValue> getAll() {
		return receptionDomaineGC.getAll();
	}

	@Override
	public ResultatRechecheBonReceptionValue rechercherMultiCritere(RechercheMulticritereBonReceptionValue request) {
		return receptionDomaineGC.rechercherMultiCritere(request);
	}
	

}
