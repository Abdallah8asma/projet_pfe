package com.gpro.consulting.tiers.logistique.persistance.gc.reglement;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;

/**
 * Reglement Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */
public interface IReglementPersistance {

	public String create(ReglementValue reglement);

	public ReglementValue getById(Long id);

	public String update(ReglementValue reglement);

	public void delete(Long id);

	public List<ReglementValue> getAll();

	public ResultatRechecheReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);
	
	public ResultatRechecheReglementCompletValue rechercherMultiCritereComplet(
			RechercheMulticritereReglementValue request);

	public List<ReglementValue> getByClientId(Long clientId);
	
	public List<ReglementValue> listeRefReglementCache();
	
	public TypeReglementValue getTypeReglementById(Long typeReglementId);

	public List<ReglementValue> getByGroupeClientId(Long clientId);
	
	public ResultBestElementValue getReglementByMonth(RechercheMulticritereReglementValue request);

}
