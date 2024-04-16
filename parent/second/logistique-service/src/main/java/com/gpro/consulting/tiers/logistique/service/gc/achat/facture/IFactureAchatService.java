package com.gpro.consulting.tiers.logistique.service.gc.achat.facture;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ListProduitAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;

/**
 * Facture Service Interface
 * 
 * @author Hamdi etteieb
 * @since 24/09/2018
 *
 */
public interface IFactureAchatService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFactureAchatValue rechercherMultiCritere(RechercheMulticritereFactureAchatValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createFacture(FactureAchatValue factureValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureAchatValue getFactureById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateFacture(FactureAchatValue factureValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteFacture(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FactureAchatVue> getListFactureRefByClient(Long idClient);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitAchatElementValue getProduitElementList(List<String> refFactureList, Long factureAchatId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(String type,Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getArticleAvoir(Long clientId);
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceMensuel(String type, Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceMensuelDeclarer(String type, boolean declarer, Calendar instance, boolean b);
}
