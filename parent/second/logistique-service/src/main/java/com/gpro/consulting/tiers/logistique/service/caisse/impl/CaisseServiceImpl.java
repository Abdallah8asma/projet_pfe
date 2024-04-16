package com.gpro.consulting.tiers.logistique.service.caisse.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.CaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheCaisseValue;
import com.gpro.consulting.tiers.logistique.domaine.caisse.ICaisseDomaine;
import com.gpro.consulting.tiers.logistique.service.caisse.ICaisseService;

/**
 * Implementation of {@link CaisseServiceImpl}
 * 
 * @author Hamdi etteieb
 * @since 16/08/2018
 * 
 */
@Service
@Transactional
public class CaisseServiceImpl implements ICaisseService {

	private static final Logger logger = LoggerFactory.getLogger(CaisseServiceImpl.class);

	@Autowired
	private ICaisseDomaine caisseDomain;

	@Override
	public String create(CaisseValue caisseValue) {

		//logger.info("create: Delegating request to Domain layer.");

		return caisseDomain.create(caisseValue);
	}

	@Override
	public CaisseValue getById(Long id) {

		//logger.info("getBonSortieFiniById: Delegating request to Domain layer to retrieve BonSortieFiniValue with id {}"+ id);
				

		return caisseDomain.getById(id);
	}

	@Override
	public ResultatRechercheCaisseValue rechercherMultiCritere(RechercheMulticritereCaisseValue request) {

		return caisseDomain.rechercherMultiCritere(request);
	}

	@Override
	public String update(CaisseValue bonSortieFiniValue) {

		return caisseDomain.update(bonSortieFiniValue);
	}

	@Override
	public void delete(Long id) {

		caisseDomain.delete(id);
	}

	@Override
	public List<CaisseValue> getAll() {
		// TODO Auto-generated method stub
		return caisseDomain.getAll();
	}

	@Override
	public List<CaisseValue> getCAissesNonCloture() {
		// TODO Auto-generated method stub
		return caisseDomain.getCAisseNonCloture();
	}

}
