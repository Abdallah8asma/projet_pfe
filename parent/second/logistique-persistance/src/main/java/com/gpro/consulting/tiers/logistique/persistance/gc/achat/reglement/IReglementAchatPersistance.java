package com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;

/**
 * Reglement Achat Persistance interface
 * 
 * @author samer hassen
 * @since 13/04/2020
 *
 */
public interface IReglementAchatPersistance {

	public String create(ReglementAchatValue reglement);

	public ReglementAchatValue getById(Long id);

	public String update(ReglementAchatValue reglement);

	public void delete(Long id);

	public List<ReglementAchatValue> getAll();

	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);
	
	public ResultatRechecheReglementAchatCompletValue rechercherMultiCritereComplet(
			RechercheMulticritereReglementAchatValue request);

	public List<ReglementAchatValue> getByFournisseurId(Long clientId);
	
	public List<ReglementAchatValue> listeRefReglementCache();
	
	public TypeReglementAchatValue getTypeReglementById(Long typeReglementId);

	public List<ReglementAchatValue> getByGroupeFournisseurId(Long clientId);
}
