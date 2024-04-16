package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheDetailBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;

/**
 * DetLivraisonVente Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetLivraisonVentePersistance {
	
	public String create(DetLivraisonVenteValue detLivraisonVenteValue);

	public DetLivraisonVenteValue getById(Long id);

	public String update(DetLivraisonVenteValue detLivraisonVenteValue);

	public void delete(Long id);

	public DetLivraisonVenteValue getBylivraisonVenteAndProduit(
			Long livraisonVenteId, Long produitId, String choix);
	
	public ResultatRechecheDetailBonLivraisonValue getDetailLivraisonByFnReportingClientProduitDate(
			RechercheMulticritereFnReportingtValue request);
	
	public void setTraitementPU(Long id , Double nouveauPU, Long idFiche);
	
	public ResultatRechecheDetBonLivraisonValue getResultatRechercheMcDetLivraisonValue(
			RechercheMulticritereDetLivraisonValue request);

	public List<ProduitValue> rechercherTop10Article(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> rechercherTop10Client(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> rechercheChiffreAffaireByClient(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> rechercheChiffreAffaireByCSousFamille(
			RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> rechercheChiffreAffaireByFamille(
			RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> rechercheChiffreAffaireByGroupe(RechercheMulticritereDetLivraisonValue request);

	public List<ResultBestElementValue> rechercherTop10Groupe(RechercheMulticritereDetLivraisonValue request);

	public DetLivraisonVenteValue getBylivraisonVenteAndOF(Long livraisonVenteId, String numeroOF, String choix);
	
	
}
