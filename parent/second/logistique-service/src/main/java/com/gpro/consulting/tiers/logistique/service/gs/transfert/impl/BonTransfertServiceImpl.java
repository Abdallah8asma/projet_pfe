package com.gpro.consulting.tiers.logistique.service.gs.transfert.impl;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.transfert.IBonTransfertDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.transfert.IBonTransfertService;

/**
 * implementation of {@link IBonTransfertService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class BonTransfertServiceImpl implements IBonTransfertService{
	
	private static final Logger logger = LoggerFactory.getLogger(BonTransfertServiceImpl.class);

	@Autowired
	private IBonTransfertDomaine bonTransfertDomaine;
	


	@Override
	public ResultatRechecheBonTransfertValue rechercherMultiCritere(
			RechercheMulticritereBonTransfertValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);
		
		return bonTransfertDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String createBonTransfert(BonTransfertValue bonTransfertValue) {
		
		return bonTransfertDomaine.createBonTransfert(bonTransfertValue);
	}

	@Override
	public BonTransfertValue getBonTransfertById(Long id) {
		
		return bonTransfertDomaine.getBonTransfertById(id);
	}

	@Override
	public String updateBonTransfert(BonTransfertValue bonTransfertValue) {
		
		return bonTransfertDomaine.updateBonTransfert(bonTransfertValue);
	}

	@Override
	public void deleteBonTransfert(Long id) {
		
		bonTransfertDomaine.deleteBonTransfert(id);
	}

	  


	@Override
	public String getCurrentReference(String type,Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return bonTransfertDomaine.getCurrentReference(type,instance,b);
	}

	@Override
	public BonTransfertValue getBonTransfertByReference(String reference) {
		// TODO Auto-generated method stub
		return bonTransfertDomaine.getBonTransfertByReference(reference);
	}

	@Override
	public BonTransfertValue validerBTsortieByReference(String reference) {
		// TODO Auto-generated method stub
		return bonTransfertDomaine.validerBTsortieByReference(reference);
	}
 

	
}
