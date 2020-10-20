package com.gpro.consulting.tiers.logistique.service.gc.achat.facture.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.ITaxeFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.ITaxeFactureAchatService;

/**
 * The Class TaxeFactureAchatServiceImpl.
 * 
 * @author Hamdi Etteieb
 * @since 24/09/2018
 */
@Service
@Transactional
public class TaxeFactureAchatServiceImpl implements ITaxeFactureAchatService {

	/** The taxe facture domaine. */
	@Autowired
	private ITaxeFactureAchatDomaine taxeFactureDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * ITaxeFactureAchatService#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.TaxeFactureAchatValue)
	 */
	@Override
	public String create(TaxeFactureAchatValue TaxeFactureAchatValue) {

		return taxeFactureDomaine.create(TaxeFactureAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * ITaxeFactureAchatService#getById(java.lang.Long)
	 */
	@Override
	public TaxeFactureAchatValue getById(Long id) {

		return taxeFactureDomaine.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * ITaxeFactureAchatService#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.TaxeFactureAchatValue)
	 */
	@Override
	public String update(TaxeFactureAchatValue TaxeFactureAchatValue) {

		return taxeFactureDomaine.update(TaxeFactureAchatValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * ITaxeFactureAchatService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {

		taxeFactureDomaine.delete(id);
	}

}
