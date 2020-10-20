package com.gpro.consulting.tiers.logistique.service.gs;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;

/**
 * BonInventaire Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonInventaireService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonInventaireValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireValue request);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	//@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonInventaire(BonInventaireValue bonInventaireValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public BonInventaireValue getBonInventaireById(Long id);

	//commente par samer le 17.03.20 //a cause d'exception  ne peut pas exécuter nextval() dans une transaction en lecture seule
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonInventaire(BonInventaireValue bonInventaireValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonInventaire(Long id);
 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String getCurrentReference(Calendar instance, boolean b);
	 
	
}
