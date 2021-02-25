package com.gpro.consulting.tiers.logistique.service.gc.vente.facture;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;

/**
 * Facture Service Interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface IFactureService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFactureValue rechercherMultiCritere(
			RechercheMulticritereFactureValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createFacture(FactureVenteValue factureValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureVenteValue getFactureById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateFacture(FactureVenteValue factureValue);

	//@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteFacture(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FactureVenteVue> getListFactureRefByClient(Long idClient);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(
			List<String> refFactureList, Long factureVenteId,Long clientId);
    
	@Transactional(readOnly = true, rollbackFor = Exception.class)

	public List<FactureVenteVue> getListFactureRefByClientAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getArticleAvoir(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(String type,Calendar instance, boolean b);
	


	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String getCurrentReferenceByTypeFactureAndDeclarer(String type, boolean declarer, Calendar instance,
			boolean b);
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public FactureVenteValue getFactureByReference(String reference);

	public ResultatRechecheFactureValue rechercherMultiCritereAvecDetail(RechercheMulticritereFactureValue request);
}
