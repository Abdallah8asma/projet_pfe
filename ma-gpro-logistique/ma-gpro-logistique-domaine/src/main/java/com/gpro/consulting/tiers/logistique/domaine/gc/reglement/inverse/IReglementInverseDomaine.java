package com.gpro.consulting.tiers.logistique.domaine.gc.reglement.inverse;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;

/**
 * Reglement Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 *
 */
public interface IReglementInverseDomaine {

	public String create(ReglementValue reglement);

	public ReglementValue getById(Long id);

	public String update(ReglementValue reglement);

	public void delete(Long id);

	public List<ReglementValue> getAll();

	public ResultatRechecheReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);

	public ValidateReglementResultValue validateByClientId(Long clientId);
	
	public List<ReglementValue> listeRefReglementCache();
	
	public List<RefFactureNonRegleValue> getRefFactureNonRegleByClientId(Long clientId);
	
	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByClientId(Long clientId);

	public ValidateReglementResultValue validateByGroupeClientId(Long clientId);

	public List<RefFactureNonRegleValue> getRefFactureNonRegleByGroupeId(Long groupeId);

	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByGroupeId(Long groupeId);

	public String getCurrentReference(Calendar instance, boolean b);

	public String getCurrentReferenceMensuelByDate(Calendar stringToCalendar, boolean b);
}
