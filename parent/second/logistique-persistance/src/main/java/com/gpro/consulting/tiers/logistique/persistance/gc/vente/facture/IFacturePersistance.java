package com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;

/**
 * Facture Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */
public interface IFacturePersistance {

	public ResultatRechecheFactureValue rechercherMultiCritere(
			RechercheMulticritereFactureValue request);

	public String createFacture(FactureVenteValue factureValue);

	public FactureVenteValue getFactureById(Long id);

	public String updateFacture(FactureVenteValue factureValue);

	public void deleteFacture(Long id);
	
	public List<FactureVenteValue> getAll();

	public FactureVenteValue getFactureByReference(String reference);

	public List<FactureVenteValue> getByClientId(Long clientId);
	
	public List<FactureVenteVue> getRefFactureByClientId(Long clientId);
	
	public List<FactureVenteValue> getProduitElementList(List<String> refFactureList);
	
	public boolean existeBL(String referenceBL);
	
	//Added on 22/11/2016 , by Zeineb Medimagh
	public Double getSommeMontHT(Long PIId , Calendar dateMin, Calendar dateMax);
	
	public Double getSommeMontHTFactAvoir(Long PIId , Calendar dateMin, Calendar dateMax);

	List<FactureVenteVue> getRefFactureByClientIdAll();

	public List<FactureVenteValue> getByGroupeClientId(Long clientId);

	public List<FactureVenteValue> getAllInfoLivraisonByClientIdAndType(Long idClient, String factureTypeNormal);

	public boolean existeBLexacte(String infoLivraison);

	public List<FactureVenteValue> getByIdReglement(Long id);
	
	public ResultatRechecheFactureValue rechercherMultiCritereAvecDetail(
			RechercheMulticritereFactureValue request);
	
	public List<FactureVenteValue> getByClientIdOptimiser(Long clientId);
	
	public List<FactureVenteValue> getByGroupeClientIdOptimiser(Long groupeClientId);
	public List<FactureVenteValue> getByClientAvoirIdOptimiser(Long clientId);
	
	public List<FactureVenteValue> getByGroupeClientAvoirIdOptimiser(Long groupeClientId);

}
