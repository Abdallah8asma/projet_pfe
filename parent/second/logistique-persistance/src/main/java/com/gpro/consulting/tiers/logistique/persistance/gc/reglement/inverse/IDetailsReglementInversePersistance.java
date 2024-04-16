package com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.echeancier.RechercheMulticritereDetailReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheElementReglementValue;

/**
 * Details Reglement Persistance interface
 * 
 * @author Samer Hassen
 * @since 27/03/2020
 *
 */
public interface IDetailsReglementInversePersistance {



	public ResultatRechecheElementReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);

	public ResultBestElementValue rechercherMultiCritereReglementEchusDuJours(Calendar instance, boolean b,
			Long boutiqueId);

	public Double getMontantPayer(RechercheMulticritereDetailReglementValue reqDetailReglement);

	public DetailsReglementValue getById(Long detailReglementId);

	public String update(DetailsReglementValue detailsReglementValue);

}
