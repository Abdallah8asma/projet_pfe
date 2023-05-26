package com.gpro.consulting.tiers.logistique.service.gc.achat.facture.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheDetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.IDetFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IDetFactureAchatService;

/**
 * The Class DetFactureAchatServiceImpl.
 * 
 * @author Hamdi Etteieb
 * @since 24/09/2018
 */
@Service
@Transactional
public class DetFactureAchatServiceImpl implements IDetFactureAchatService {

	/** The det facture achat domaine. */
	@Autowired
	private IDetFactureAchatDomaine detFactureAchatDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IDetFactureAchatService#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.DetFactureAchatValue)
	 */
	@Override
	public String create(DetFactureAchatValue detFactureAchatValue) {

		return detFactureAchatDomaine.create(detFactureAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IDetFactureAchatService#getById(java.lang.Long)
	 */
	@Override
	public DetFactureAchatValue getById(Long id) {

		return detFactureAchatDomaine.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IDetFactureAchatService#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.DetFactureAchatValue)
	 */
	@Override
	public String update(DetFactureAchatValue detFactureAchatValue) {

		return detFactureAchatDomaine.update(detFactureAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IDetFactureAchatService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		detFactureAchatDomaine.delete(id);
	}

	@Override
	public ResultatRechecheDetFactureAchatValue rechercherMultiCritere(
			RechercheMulticritereDetFactureAchatValue request) {
		// TODO Auto-generated method stub
		return detFactureAchatDomaine.rechercherMultiCritere(request);
	}

}
