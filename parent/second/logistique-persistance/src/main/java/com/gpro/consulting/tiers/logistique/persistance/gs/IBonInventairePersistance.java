package com.gpro.consulting.tiers.logistique.persistance.gs;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;

/**
 * BonInventaire Persistance interface
 * 
 * @author Samer Hassen
 * @since 21/05/2020
 *
 */
public interface IBonInventairePersistance {

	public ResultatRechecheBonInventaireValue rechercherMultiCritere(
			RechercheMulticritereBonInventaireValue request);

	public String createBonInventaire(BonInventaireValue bonInventaireValue);

	public BonInventaireValue getBonInventaireById(Long id);

	public String updateBonInventaire(BonInventaireValue bonInventaireValue);

	public void deleteBonInventaire(Long id);
	
	
 
 
 	
 	





}
