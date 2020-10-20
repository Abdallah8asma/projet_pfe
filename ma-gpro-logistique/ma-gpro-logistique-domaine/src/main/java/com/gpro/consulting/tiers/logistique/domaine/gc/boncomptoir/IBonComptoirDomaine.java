package com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir;

import java.util.List;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;

/**
 * BonComptoir Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonComptoirDomaine {

	public ResultatRechecheBonComptoirValue rechercherMultiCritere(
			RechercheMulticritereBonComptoirValue request);

	public String createBonComptoir(ComptoirVenteValue bonComptoirValue);

	public ComptoirVenteValue getBonComptoirById(Long id);

	public String updateBonComptoir(ComptoirVenteValue bonComptoirValue);

	public void deleteBonComptoir(Long id);

	public ListProduitElementValue getProduitElementList(
			List<String> refBonComptoirList, Long factureVenteId);

	public List<String> getListBonComptoirRef();

	public ComptoirVenteValue getByReference(String refBL);
	
	public List<LivraisonVenteVue> getListBonComptoirRefByClient(Long idClient);
	
	public ResultatRechecheBonComptoirValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request);
	
	public List<ComptoirVenteValue> getAll();
	
	//Added on 11/10/2016 by Zeineb Medimagh
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonComptoirList, Long factureVenteId);

	String createBonComptoir2(ComptoirVenteValue bonComptoirValue);

	public List<LivraisonVenteFactureVue> getAllListBonComptoirRefByClient(
			Long idClient);
}
