package com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini;

import java.util.List;

import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.ResultatRechecheLivraisonVenteFnReportingVue;

/**
 * BonSortieFini Persistance interface
 * 
 * @author Ghazi Atroussi
 * @since 18/12/2016
 *
 */
public interface IBonInventaireFiniPersistance {

	public String createBonInventaireFini(BonInventaireFiniValue request);

	public BonInventaireFiniValue getBonInventaireFiniById(Long id);

	public ResultatRechecheBonInventaireFiniValue rechercherMultiCritere(RechercheMulticritereBonInventaireFiniValue request);

	public String updateBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue);

	public void deleteBonInventaireFini(Long id);



	public List<BonInventaireFiniValue> getAll();

	
	
}
