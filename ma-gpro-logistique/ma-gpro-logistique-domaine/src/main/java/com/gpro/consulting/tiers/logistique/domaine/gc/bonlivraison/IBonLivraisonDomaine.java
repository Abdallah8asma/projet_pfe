package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ValiderBLPassagerValue;

/**
 * BonLivraison Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonLivraisonDomaine {

	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request);

	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public LivraisonVenteValue getBonLivraisonById(Long id);

	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public void deleteBonLivraison(Long id);

	public ListProduitElementValue getProduitElementList(
			List<String> refBonLivraisonList, Long factureVenteId);

	public List<String> getListBonLivraisonRef();

	public LivraisonVenteValue getByReference(String refBL);
	
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient);
	
	public ResultatRechecheBonLivraisonValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request);
	
	public List<LivraisonVenteValue> getAll();
	
	//Added on 11/10/2016 by Zeineb Medimagh
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonLivraisonList, Long factureVenteId);

	String createBonLivraison2(LivraisonVenteValue bonLivraisonValue);

	public List<LivraisonVenteFactureVue> getAllListBonLivraisonRefByClient(
			Long idClient);

	public List<LivraisonVenteVue> getListBonLivraisonRefByClientAndDeclare(Long idClient);

	public String getCurrentReference(Calendar instance, boolean b);

	public ListProduitElementValue getProduitElementListForPassager(ValiderBLPassagerValue request);

	public String getCurrentReferenceByType(String type, Calendar instance, boolean b);

	public ListProduitElementValue getProduitElementListByOF(List<String> refBonLivraisonList, Long factureVenteId);
}
