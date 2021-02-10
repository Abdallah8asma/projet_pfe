package com.gpro.consulting.tiers.logistique.service.gc.achat.reception.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.IReceptionAchatDomaineGC;
import com.gpro.consulting.tiers.logistique.service.gc.achat.reception.IReceptionAchatService;

/**
 * The Class ReceptionAchatServiceImpl.
 * 
 * @author Hamdi Etteieb
 * @since 23/09/2018
 */

@Component
public class ReceptionAchatServiceImpl implements IReceptionAchatService {

	/** The reception domaine GC. */
	@Autowired
	private IReceptionAchatDomaineGC receptionDomaineGC;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.
	 * IBonReceptionDomaine#create(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.boncommande.value.ReceptionAchatValue)
	 */
	@Override
	public String create(ReceptionAchatValue bonReceptionValue) {
		return receptionDomaineGC.create(bonReceptionValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.reception.
	 * IReceptionAchatService#getById(java.lang.Long)
	 */
	@Override
	public ReceptionAchatValue getById(Long id) {
		return receptionDomaineGC.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.reception.
	 * IReceptionAchatService#update(com.gpro.consulting.tiers.logistique.
	 * coordination.gc.achat.reception.value.ReceptionAchatValue)
	 */
	@Override
	public String update(ReceptionAchatValue bonReceptionValue) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.update(bonReceptionValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.reception.
	 * IReceptionAchatService#delete(java.lang.Long)
	 */
	@Override
	public String delete(Long id) {
		return receptionDomaineGC.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.reception.
	 * IReceptionAchatService#getAll()
	 */
	@Override
	public List<ReceptionAchatValue> getAll() {
		return receptionDomaineGC.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.service.gc.achat.reception.
	 * IReceptionAchatService#rechercherMultiCritere(com.gpro.consulting.tiers.
	 * logistique.coordination.gc.achat.reception.value.
	 * RechercheMulticritereBonReceptionAchatValue)
	 */
	@Override
	public ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionAchatValue request) {
		return receptionDomaineGC.rechercherMultiCritere(request);
	}

	@Override
	public List<BonReceptionVue> getListBonReceptionRefByFournisseur(Long idFournisseur) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getListBonReceptionRefByFournisseur(idFournisseur);
	}

	@Override
	public List<ReceptionAchatFactureVue> getAllListBonReceptionRefByFournisseur(Long idFournisseur) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getAllListBonReceptionRefByFournisseur(idFournisseur);
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonReceptionList, Long factureAchatId) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getProduitElementList(refBonReceptionList,factureAchatId);
	}

	@Override
	public String getCurrentReference(Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getCurrentReference(instance,b);
	}

	@Override
	public ReceptionAchatValue validerFactureAvoirRetour(String reference) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.validerFactureAvoirRetour(reference);
	}

	@Override
	public ReceptionAchatValue validerBL(String reference) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.validerBL(reference);
	}

	@Override
	public String getCurrentReferenceMensuel(Calendar instance, boolean b) {
		return receptionDomaineGC.getCurrentReferenceMensuel(instance,b);
	}

	@Override
	public String getCurrentReferenceMensuelByType(String type, Calendar instance, boolean b) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getCurrentReferenceMensuelByType(type,instance,b);
	}

	@Override
	public List<BonReceptionVue> getListBonReceptionRefByFournisseurDeclarer(Long idFournisseur) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getListBonReceptionRefByFournisseurDeclarer(idFournisseur);
	}

	@Override
	public ListProduitElementValue getProduitElementListWithoutRegroupement(List<String> refBonReceptionList,
			Long factureAchatId) {
		return receptionDomaineGC.getProduitElementListWithoutRegroupement(refBonReceptionList,factureAchatId);
	}

	@Override
	public ReceptionAchatValue getByReference(String reference) {
		// TODO Auto-generated method stub
		return receptionDomaineGC.getByReference(reference);
	}

}
