package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.inverse;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;

/**
 * Reglement Achat Domaine interface
 * 
 * @author Samer Hassen
 * @since 13/04/20
 *
 */
public interface IReglementInverseAchatDomaine {

	public String create(ReglementAchatValue reglement);

	public ReglementAchatValue getById(Long id);

	public String update(ReglementAchatValue reglement);

	public void delete(Long id);

	public List<ReglementAchatValue> getAll();

	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	public ValidateReglementResultValue validateByFournisseurId(Long clientId);
	
	public List<ReglementAchatValue> listeRefReglementCache();
	
	public List<RefFactureNonRegleValue> getRefFactureNonRegleByFournisseurId(Long clientId);
	
	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByFournisseurId(Long clientId);

	public ValidateReglementResultValue validateByGroupeFournisseurId(Long clientId);

	public BLReportElementRecapValue getReglementAchatbyMonth(RechercheMulticritereReglementValue request);

	public String getCurrentReference(Calendar instance, boolean b);

	public String getCurrentReferenceMensuelByDate(Calendar stringToCalendar, boolean b);
}
