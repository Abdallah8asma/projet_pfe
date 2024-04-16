package com.gpro.consulting.tiers.logistique.service.atelier.boninventairefini.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.boninventairefini.IBonInventaireFiniDomain;
import com.gpro.consulting.tiers.logistique.service.atelier.boninventairefini.IBonInventaireFiniService;
import com.gpro.consulting.tiers.logistique.service.atelier.bonsortiefini.IBonSortieFiniService;

/**
 * Implementation of {@link IBonSortieFiniService}
 * 
 * @author Ghazi Atroussi
 * @since 18/12/2016
 *
 */
@Service
@Transactional
public class BonInventaireFiniServiceImpl implements IBonInventaireFiniService{
	
	private static final Logger logger = LoggerFactory.getLogger(BonInventaireFiniServiceImpl.class);
	
	@Autowired
	private IBonInventaireFiniDomain bonInventaireFiniDomain;

	@Override
	public String createBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue) {
		
		//logger.info("createBonSortieFini: Delegating request to Domain layer.");
		
		return bonInventaireFiniDomain.createBonInventaireFini(bonSortieFiniValue);
	}

	@Override
	public BonInventaireFiniValue getBonInventaireFiniById(Long id) {
		
		//logger.info("getBonSortieFiniById: Delegating request to Domain layer to retrieve BonSortieFiniValue with id {}"+id);

		return bonInventaireFiniDomain.getBonInventaireFiniById(id);
	}

	@Override
	public List<RouleauFiniValue> validateBonInventaireFini(List<String> barreCodeList, Long id) {

		
		return bonInventaireFiniDomain.validateBonInventaireFini(barreCodeList, id);
	}

	@Override
	public ResultatRechecheBonInventaireFiniValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireFiniValue request) {
		
		return bonInventaireFiniDomain.rechercherMultiCritere(request);
	}

	@Override
	public String updateBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue) {

		return bonInventaireFiniDomain.updateBonInventaireFini(bonSortieFiniValue);
	}

	@Override
	public void deleteBonInventaireFini(Long id) {
		
		bonInventaireFiniDomain.deleteBonInventaire(id);
	}

}
