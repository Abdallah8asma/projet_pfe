package com.gpro.consulting.tiers.logistique.service.gc.reglement;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.FactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.LivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureAvoirNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefFactureNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.RefLivraisonNonRegleValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.validate.value.ValidateReglementResultValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementValue;

/**
 * Reglement Service interface
 * 
 * @author Wahid Gazzah
 * @since 01/07/2016
 */
public interface IReglementService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ReglementValue reglement);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReglementValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ReglementValue reglement);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReglementValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheReglementValue rechercherMultiCritere(
			RechercheMulticritereReglementValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ValidateReglementResultValue validateByClientId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ReglementValue> listeRefReglementCache();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefFactureNonRegleValue> getRefFactureNonRegleByClientId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByClientId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ValidateReglementResultValue validateByGroupeClientId(Long clientId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefFactureNonRegleValue> getRefFactureNonRegleByGroupeId(Long groupeId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefLivraisonNonRegleValue> getRefBLNonRegleByGroupeId(Long groupeId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(Calendar instance, boolean b);



	public String getCurrentReferenceByDateAndDeclaree(Calendar stringToCalendar, boolean declarer, boolean increment);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefFactureAvoirNonRegleValue> getRefFactureAvoirNonRegleByClientId(Long clientId);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<RefFactureAvoirNonRegleValue> getRefFactureAvoirNonRegleByGroupeId(Long groupeId);


}
