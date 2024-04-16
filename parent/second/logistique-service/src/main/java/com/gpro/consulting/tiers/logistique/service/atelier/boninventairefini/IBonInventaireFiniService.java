package com.gpro.consulting.tiers.logistique.service.atelier.boninventairefini;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;

/**
 * BonSortieFini Service Interface
 * 
 * @author Ghazi ATROUSSI
 * @since 18/12/2016
 *
 */
public interface IBonInventaireFiniService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<RouleauFiniValue> validateBonInventaireFini(List<String> barreCodeList, Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public BonInventaireFiniValue getBonInventaireFiniById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonInventaireFiniValue rechercherMultiCritere(RechercheMulticritereBonInventaireFiniValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonInventaireFini(Long id);

}
