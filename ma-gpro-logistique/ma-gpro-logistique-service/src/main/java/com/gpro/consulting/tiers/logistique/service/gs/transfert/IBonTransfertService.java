package com.gpro.consulting.tiers.logistique.service.gs.transfert;

import java.util.Calendar;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;

/**
 * BonTransfert Service Interface
 * 
 * @author samer
 * @since 
 *
 */
public interface IBonTransfertService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonTransfertValue rechercherMultiCritere(
			RechercheMulticritereBonTransfertValue request);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	//@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonTransfert(BonTransfertValue bonTransfertValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public BonTransfertValue getBonTransfertById(Long id);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonTransfert(BonTransfertValue bonTransfertValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonTransfert(Long id);
 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(String type,Calendar instance, boolean b);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonTransfertValue getBonTransfertByReference(String reference);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonTransfertValue validerBTsortieByReference(String reference);
	 
	
}
