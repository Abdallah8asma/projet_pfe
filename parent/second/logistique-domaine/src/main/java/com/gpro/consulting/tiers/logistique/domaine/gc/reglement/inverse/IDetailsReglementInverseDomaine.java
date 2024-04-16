package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse;

import java.util.Calendar;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;

/**
 * Element Reglement Domaine interface
 * 
 * @author Samer Hassen
 * @since 25/03/2020
 *
 */
public interface IDetailsReglementInverseDomaine {



	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);

	public ResultBestElementValue rechercherMultiCritereReglementEchusDuJours(Calendar instance, boolean b,Long boutiqueId);

	public Double getMontantPayer(RechercheMulticritereDetailReglementValue reqDetailReglement);

	public DetailsReglementValue getById(Long detailReglementId);

	public String update(DetailsReglementValue detailsReglementValue);


}
