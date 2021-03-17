package com.gpro.consulting.tiers.logistique.service.gc.vente.facture.impl;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IFactureDomaine;
import com.gpro.consulting.tiers.logistique.service.gc.vente.facture.IFactureService;

/**
 * implementation of {@link IFactureService}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
@Service
@Transactional
public class FactureServiceImpl implements IFactureService{
	
	private static final Logger logger = LoggerFactory.getLogger(FactureServiceImpl.class);

	@Autowired
	private IFactureDomaine factureDomaine;

	@Override
	public ResultatRechecheFactureValue rechercherMultiCritere(
			RechercheMulticritereFactureValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request {} to Domaine layer.", request);
		
		return factureDomaine.rechercherMultiCritere(request);
	}

	@Override
	public String createFacture(FactureVenteValue FactureValue) {
		
		return factureDomaine.createFacture(FactureValue);
	}

	@Override
	public FactureVenteValue getFactureById(Long id) {
		
		return factureDomaine.getFactureById(id);
	}

	@Override
	public String updateFacture(FactureVenteValue FactureValue) {
		
		return factureDomaine.updateFacture(FactureValue);
	}

	@Override
	public void deleteFacture(Long id) {
		
		factureDomaine.deleteFacture(id);
	}

	@Override
	public List<FactureVenteVue> getListFactureRefByClient(Long idClient) {
		return factureDomaine.getListFactureRefByClient(idClient);
	}

	@Override
	public ListProduitElementValue getProduitElementList(
			List<String> refFactureList, Long factureVenteId ,Long clientId) {
		return factureDomaine.getProduitElementList(refFactureList, factureVenteId,clientId);
	}
	
	
	@Override
	public List<FactureVenteVue> getListFactureRefByClientAll() {
		return factureDomaine.getListFactureRefByClientAll();
	}

	

	@Override
	public ListProduitElementValue getArticleAvoir(Long clientId) {
		// TODO Auto-generated method stub
		return factureDomaine.getArticleAvoir(clientId);
	}

	@Override
	public String getCurrentReference(String type, Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return factureDomaine.getCurrentReference(type,instance,b);
	}



	@Override
	public String getCurrentReferenceByTypeFactureAndDeclarer(String type, boolean declarer, Calendar instance,
			boolean b) {
		// TODO Auto-generated method stub
		return factureDomaine.getCurrentReferenceByTypeFactureAndDeclarer(type,declarer,instance,b);
	}

	@Override
	public FactureVenteValue getFactureByReference(String reference) {
		// TODO Auto-generated method stub
		return factureDomaine.getFactureByReference(reference);
	}

	@Override
	public ResultatRechecheFactureValue rechercherMultiCritereAvecDetail(RechercheMulticritereFactureValue request) {
		// TODO Auto-generated method stub
		return factureDomaine.rechercherMultiCritereAvecDetail(request);
	}
	
	
	

}
