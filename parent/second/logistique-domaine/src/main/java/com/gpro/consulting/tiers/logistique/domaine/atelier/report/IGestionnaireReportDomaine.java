package com.gpro.consulting.tiers.logistique.domaine.atelier.report;

import java.io.IOException;

import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value.BonReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.boninventaire.BonInventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieFinieReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieFinieReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.inventaire.value.InventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.rouleaufini.value.EtiquetteRouleauFiniReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;

/**
 * Gestionnaire Report Domaine
 * 
 * @author Wahid Gazzah
 * @since 17/12/2015
 *
 */
public interface IGestionnaireReportDomaine {

	public BonReceptionReportValue getBonReceptionParId(Long id) throws IOException;

	public EtiquetteRouleauFiniReportValue getEtiquetteRouleauFiniReportParId(Long id) throws IOException;

	public InventaireReportValue getInventaireReportValue(CritereRechercheRouleauStandardValue pCritereRechercheRouleauStandard) throws IOException;

	public BonSortieFinieReportValue getBonsortieFinieReportValue(Long id, String avecMise) throws IOException;

	public BonSortieFinieReportListValue getListBonSortieReport(RechercheMulticritereBonSortieFiniValue request) throws IOException;
	
	public BonInventaireReportValue getBonInventaireReportValue(Long id, String avecMise) throws IOException ;

	public FicheColisReportValue genererListEtiquetteRouleauReport(RechercheMulticritereRouleauFiniValue request)throws IOException ;

	public InventaireReportValue getInventaireByOFReportValue(
			CritereRechercheRouleauStandardValue critereRechercheRouleauStandard) throws IOException;
	
}
