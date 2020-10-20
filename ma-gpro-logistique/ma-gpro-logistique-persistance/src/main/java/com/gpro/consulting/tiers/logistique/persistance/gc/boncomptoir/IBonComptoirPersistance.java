package com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir;

import java.util.List;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;


/**
 * BonComptoir Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonComptoirPersistance {

	public ResultatRechecheBonComptoirValue rechercherMultiCritere(
			RechercheMulticritereBonComptoirValue request);

	public String createBonComptoir(ComptoirVenteValue bonComptoirValue);

	public ComptoirVenteValue getBonComptoirById(Long id);

	public String updateBonComptoir(ComptoirVenteValue bonComptoirValue);

	public void deleteBonComptoir(Long id);
	
	public List<ComptoirVenteValue> getAll();
	
	public List<ComptoirVenteValue> getProduitElementList(List<String> refBonComptoirList);

	public ComptoirVenteValue getByReference(String reference);

	public List<ComptoirVenteValue> getByClientId(Long clientId);
	


	List<ComptoirVenteValue> getTraitementFaconElementList(List<String> refBonComptoirList);
	
	//Added on 21/11/2016, by Zeineb Medimagh
	
	public Double getSommeMontHT(RechercheMulticritereBonComptoirValue request);
	
	//Added on 22/11/2016, by Zeineb Medimagh
	
	public Double getSommeMontHTFactures(RechercheMulticritereBonComptoirValue request);

	public ResultatRechecheBonComptoirValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request);

	public List<LivraisonVenteFactureVue> getListBLByClientId(Long idClient);


}
