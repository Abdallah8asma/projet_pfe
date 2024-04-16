package com.gpro.consulting.tiers.logistique.service.atelier.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

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
 * GestionnaireReport Service Interface
 * 
 * @author Wahid Gazzah
 * @since 17/12/2015
 *
 */
public interface IGestionnaireReportService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonReceptionReportValue getBonReceptionReportParId(Long id) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtiquetteRouleauFiniReportValue getEtiquetteRouleauFiniReportParId(Long id) throws IOException;
	  
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public InventaireReportValue getInventaireReportValue(CritereRechercheRouleauStandardValue pCritereRechercheRouleauStandard) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonSortieFinieReportValue getBonsortieFinieReportValue(Long id, String avecMise) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonSortieFinieReportListValue getListBonSortieReport(RechercheMulticritereBonSortieFiniValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public BonInventaireReportValue getBonInventaireReportValue(Long id, String avecMise) throws IOException ;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisReportValue genererListEtiquetteRouleauReport(RechercheMulticritereRouleauFiniValue request)throws IOException ;
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public InventaireReportValue getInventaireByOFReportValue(
			CritereRechercheRouleauStandardValue critereRechercheRouleauStandard) throws IOException;
}
