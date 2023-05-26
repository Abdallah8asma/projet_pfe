package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.ResultatRechecheLivraisonVenteFnReportingVue;

/**
 * BonLivraison Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonLivraisonPersistance {

	public ResultatRechecheBonLivraisonValue rechercherMultiCritereComplet(
			RechercheMulticritereBonLivraisonValue request);
	
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request);

	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public LivraisonVenteValue getBonLivraisonById(Long id);

	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public void deleteBonLivraison(Long id);
	
	public List<LivraisonVenteValue> getAll();
	
	public List<LivraisonVenteValue> getProduitElementList(List<String> refBonLivraisonList);

	public LivraisonVenteValue getByReference(String reference);

	public List<LivraisonVenteValue> getByClientId(Long clientId);
	
	public List<LivraisonVenteVue> getReferenceBLByClientId(Long clientId);

	public ResultatRechecheBonLivraisonValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request);

	List<LivraisonVenteValue> getTraitementFaconElementList(List<String> refBonLivraisonList);
	
	//Added on 21/11/2016, by Zeineb Medimagh
	
	public Double getSommeMontHT(RechercheMulticritereBonLivraisonValue request);
	
	//Added on 22/11/2016, by Zeineb Medimagh
	
	public Double getSommeMontHTFactures(RechercheMulticritereBonLivraisonValue request);

	public List<LivraisonVenteFactureVue> getListBLByClientId(Long clientId);

	public List<LivraisonVenteVue> getReferenceBLByClientIdAndDeclare(Long idClient);

	public List<LivraisonVenteValue> getByGroupeClientId(Long clientId);

	public boolean existeBC(String reference);

	public List<LivraisonVenteValue> getByIdReglement(Long id);

	public LivraisonVenteValue getByInfoSortie(String infoSortie);
	
	public List<LivraisonVenteValue> getByClientIdOptimiser(Long clientId);

	public List<LivraisonVenteValue> getByGroupeClientIdOptimiser(Long groupeClientId);
}
