package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.inverse.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.inverse.IReglementInverseAchatDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reglement.inverse.IReglementInverseAchatService;
import com.gpro.consulting.tiers.logistique.service.gc.reglement.IReglementService;

/**
 * implementation of {@link IReglementService}
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */

@Service
@Transactional
public class ReglementInverseAchatServiceImpl implements IReglementInverseAchatService{

	private static final Logger logger = LoggerFactory.getLogger(ReglementInverseAchatServiceImpl.class);

	@Autowired
	private IReglementInverseAchatDomaine reglementDomaine;
	
	@Override
	public String create(ReglementAchatValue reglement) {
		
		//logger.info("Delegating request to Domaine layer.");
		
		return reglementDomaine.create(reglement);
	}

	@Override
	public ReglementAchatValue getById(Long id) {
		
		//logger.info("Delegating request id {} to Domaine layer.",id);
		
		return reglementDomaine.getById(id);
	}

	@Override
	public String update(ReglementAchatValue reglement) {
		
		//logger.info("Delegating request to Domaine layer.");
		
		return reglementDomaine.update(reglement);
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("Delegating request id {} to Domaine layer.",id);
		
		reglementDomaine.delete(id);
	}

	@Override
	public List<ReglementAchatValue> getAll() {
		
		//logger.info("Delegating request to Domaine layer.");
		
		return reglementDomaine.getAll();
	}

	@Override
	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request) {
		
		return reglementDomaine.rechercherMultiCritere(request);
	}

	@Override
	public ValidateReglementResultValue validateByFournisseurId(Long clientId) {
		
		//logger.info("Delegating request clientId {} to Domaine layer.",clientId);
		
		return reglementDomaine.validateByFournisseurId(clientId);
	}

	
	@Override
	public List<ReglementAchatValue> listeRefReglementCache() {
		
		//logger.info("Delegating request to Domaine layer.");
		
		return reglementDomaine.listeRefReglementCache();
	}
	
	@Override
	public List< RefFactureNonRegleValue>  getRefFactureNonRegleByFournisseurId(Long clientId) {
		
		//logger.info("Delegating request clientId {} to Domaine layer.",clientId);
		
		return reglementDomaine.getRefFactureNonRegleByFournisseurId(clientId);
	}
	
	@Override
	public List< RefLivraisonNonRegleValue> getRefBLNonRegleByFournisseurId(Long clientId) {
		
		//logger.info("Delegating request clientId {} to Domaine layer.",clientId);
		
		return reglementDomaine.getRefBLNonRegleByFournisseurId(clientId);
	}

	@Override
	public ValidateReglementResultValue validateByGroupeFournisseurId(Long clientId) {
		return reglementDomaine.validateByGroupeFournisseurId(clientId);
	}

	@Override
	public String getCurrentReference(Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return reglementDomaine.getCurrentReference(instance,b);
	}

	@Override
	public String getCurrentReferenceMensuelByDate(Calendar stringToCalendar, boolean b) {
		// TODO Auto-generated method stub
		return reglementDomaine.getCurrentReferenceMensuelByDate(stringToCalendar,b);
	}
}
