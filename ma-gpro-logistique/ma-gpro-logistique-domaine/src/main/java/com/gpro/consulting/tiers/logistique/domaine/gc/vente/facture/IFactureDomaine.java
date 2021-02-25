package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;

/**
 * Facture Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface IFactureDomaine {

	public ResultatRechecheFactureValue rechercherMultiCritere(
			RechercheMulticritereFactureValue request);

	public String createFacture(FactureVenteValue factureValue);

	public FactureVenteValue getFactureById(Long id);

	public String updateFacture(FactureVenteValue factureValue);

	public void deleteFacture(Long id);
	
	public FactureVenteValue getFactureByReference(String reference);

	public List<FactureVenteVue> getListFactureRefByClient(Long idClient);
	
	public ListProduitElementValue getProduitElementList(
			List<String> refFactureList, Long factureVenteId,Long clientId);

	public List<FactureVenteVue> getListFactureRefByClientAll();

	public ListProduitElementValue getArticleAvoir(Long clientId);

	public String getCurrentReference(String type, Calendar instance, boolean b);

	public String getCurrentReferenceByTypeFactureAndDeclarer(String type, boolean declarer, Calendar instance,
			boolean b);

	public ResultatRechecheFactureValue rechercherMultiCritereAvecDetail(RechercheMulticritereFactureValue request);
	
}
