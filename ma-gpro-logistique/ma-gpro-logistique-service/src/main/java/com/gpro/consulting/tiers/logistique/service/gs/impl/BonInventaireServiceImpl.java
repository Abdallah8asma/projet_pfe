package com.gpro.consulting.tiers.logistique.service.gs.impl;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonInventaireDomaine;
import com.gpro.consulting.tiers.logistique.service.gs.IBonInventaireService;

/**
 * implementation of {@link IBonInventaireService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class BonInventaireServiceImpl implements IBonInventaireService{
	
	private static final Logger logger = LoggerFactory.getLogger(BonInventaireServiceImpl.class);

	@Autowired
	private IBonInventaireDomaine bonInventaireDomaine;
	


	@Override
	public ResultatRechecheBonInventaireValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);
		
		return bonInventaireDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String createBonInventaire(BonInventaireValue bonInventaireValue) {
		
		return bonInventaireDomaine.createBonInventaire(bonInventaireValue);
	}

	@Override
	public BonInventaireValue getBonInventaireById(Long id) {
		
		return bonInventaireDomaine.getBonInventaireById(id);
	}

	@Override
	public String updateBonInventaire(BonInventaireValue bonInventaireValue) {
		
		return bonInventaireDomaine.updateBonInventaire(bonInventaireValue);
	}

	@Override
	public void deleteBonInventaire(Long id) {
		
		bonInventaireDomaine.deleteBonInventaire(id);
	}

	  


	@Override
	public String getCurrentReference(Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return bonInventaireDomaine.getCurrentReference(instance,b);
	}
 

	
}
