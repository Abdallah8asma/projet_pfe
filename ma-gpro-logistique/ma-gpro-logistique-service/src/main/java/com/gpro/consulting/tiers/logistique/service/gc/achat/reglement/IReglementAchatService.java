package com.gpro.consulting.tiers.logistique.service.gc.achat.reglement;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;

/**
 * Reglement Achat Service interface
 * 
 * @author Samer Hassen
 * @since 14/04/20
 */
public interface IReglementAchatService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ReglementAchatValue reglement);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementAchatValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ReglementAchatValue reglement);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReglementAchatValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheReglementAchatValue rechercherMultiCritere(
			RechercheMulticritereReglementAchatValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ValidateReglementResultValue validateByFournisseurId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReglementAchatValue> listeRefReglementCache();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefFactureNonRegleValue> getRefFactureNonRegleByFournisseurId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByFournisseurId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ValidateReglementResultValue validateByGroupeFournisseurId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReferenceMensuelByDate(Calendar stringToCalendar, boolean b);

}
