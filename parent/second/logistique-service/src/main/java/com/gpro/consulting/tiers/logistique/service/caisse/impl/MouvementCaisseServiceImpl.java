package com.gpro.consulting.tiers.logistique.service.caisse.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.caisse.value.MouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.RechercheMulticritereMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.coordination.caisse.value.ResultatRechercheMouvementCaisseValue;
import com.gpro.consulting.tiers.logistique.domaine.caisse.IMouvementCaisseDomaine;
import com.gpro.consulting.tiers.logistique.service.caisse.IMouvementCaisseService;

// TODO: Auto-generated Javadoc
/**
 * Implementation of {@link MouvementMouvementCaisseServiceImpl}.
 *
 * @author Hamdi etteieb
 * @since 16/08/2018
 */
@Service
@Transactional
public class MouvementCaisseServiceImpl implements IMouvementCaisseService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MouvementCaisseServiceImpl.class);

	/** The MouvementCaisse domain. */
	@Autowired
	private IMouvementCaisseDomaine mouvementCaisseDomain;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.caisse.
	 * IMouvementCaisseService#create(com.gpro.consulting.tiers.logistique.
	 * coordination.caisse.value.MouvementCaisseValue)
	 */
	@Override
	public String create(MouvementCaisseValue MouvementCaisseValue) {

		//logger.info("create: Delegating request to Domain layer.");

		return mouvementCaisseDomain.create(MouvementCaisseValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.caisse.
	 * IMouvementCaisseService#getById(java.lang.Long)
	 */
	@Override
	public MouvementCaisseValue getById(Long id) {

		return mouvementCaisseDomain.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.caisse.
	 * IMouvementCaisseService#rechercherMultiCritere(com.gpro.consulting.tiers.
	 * logistique.coordination.caisse.value.
	 * RechercheMulticritereMouvementCaisseValue)
	 */
	@Override
	public ResultatRechercheMouvementCaisseValue rechercherMultiCritere(
			RechercheMulticritereMouvementCaisseValue request) {

		return mouvementCaisseDomain.rechercherMultiCritere(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.caisse.
	 * IMouvementCaisseService#update(com.gpro.consulting.tiers.logistique.
	 * coordination.caisse.value.MouvementCaisseValue)
	 */
	@Override
	public String update(MouvementCaisseValue mvtValue) {

		return mouvementCaisseDomain.update(mvtValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.caisse.
	 * IMouvementCaisseService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		mouvementCaisseDomain.delete(id);
	}

	@Override
	public List<MouvementCaisseValue> getAll() {
		return mouvementCaisseDomain.getAll();
	}
}
