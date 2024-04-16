package com.gpro.consulting.tiers.logistique.service.gc.achat.facture.impl;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ListProduitAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.IFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.facture.IFactureAchatService;

/**
 * The Class FactureServiceImpl.
 * 
 * @author Hamdi Etteieb
 * @since 24/09/2018
 */
@Service
@Transactional
public class FactureAchatServiceImpl implements IFactureAchatService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(FactureAchatServiceImpl.class);

	/** The facture domaine. */
	@Autowired
	private IFactureAchatDomaine factureDomaine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#rechercherMultiCritere(com.gpro.consulting.tiers.
	 * logistique.coordination.gc.achat.facture.value.
	 * RechercheMulticritereFactureAchatValue)
	 */
	@Override
	public ResultatRechecheFactureAchatValue rechercherMultiCritere(RechercheMulticritereFactureAchatValue request) {

		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);

		return factureDomaine.rechercherMultiCritere(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#createFacture(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.FactureAchatValue)
	 */
	@Override
	public String createFacture(FactureAchatValue FactureValue) {

		return factureDomaine.createFacture(FactureValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#getFactureById(java.lang.Long)
	 */
	@Override
	public FactureAchatValue getFactureById(Long id) {

		return factureDomaine.getFactureById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#updateFacture(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.facture.value.FactureAchatValue)
	 */
	@Override
	public String updateFacture(FactureAchatValue FactureValue) {

		return factureDomaine.updateFacture(FactureValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#deleteFacture(java.lang.Long)
	 */
	@Override
	public void deleteFacture(Long id) {

		factureDomaine.deleteFacture(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#getListFactureRefByClient(java.lang.Long)
	 */
	@Override
	public List<FactureAchatVue> getListFactureRefByClient(Long idClient) {
		return factureDomaine.getListFactureRefByClient(idClient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.facture.
	 * IFactureAchatService#getProduitElementList(java.util.List,
	 * java.lang.Long)
	 */
	@Override
	public ListProduitAchatElementValue getProduitElementList(List<String> refFactureList, Long factureAchatId) {
		return factureDomaine.getProduitElementList(refFactureList, factureAchatId);
	}

	@Override
	public String getCurrentReference(String type,Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return factureDomaine.getCurrentReference(type,instance,b);
	}

	@Override
	public ListProduitElementValue getArticleAvoir(Long clientId) {
		// TODO Auto-generated method stub
		return factureDomaine.getArticleAvoir(clientId);
	}

	
	@Override
	public String getCurrentReferenceMensuel(String type, Calendar instance, boolean b) {
		return factureDomaine.getCurrentReferenceMensuel(type,instance,b);
	}

	@Override
	public String getCurrentReferenceMensuelDeclarer(String type, boolean declarer, Calendar instance, boolean b) {
		return factureDomaine.getCurrentReferenceMensuelDeclarer(type,declarer,instance,b);
	}

}
