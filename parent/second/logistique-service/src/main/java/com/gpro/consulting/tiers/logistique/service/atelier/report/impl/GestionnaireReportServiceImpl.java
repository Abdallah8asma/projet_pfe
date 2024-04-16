package com.gpro.consulting.tiers.logistique.service.atelier.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gpro.consulting.tiers.logistique.domaine.atelier.report.IGestionnaireReportDomaine;
import com.gpro.consulting.tiers.logistique.service.atelier.report.IGestionnaireReportService;

/**
 * Implementation of {@link IGestionnaireReportService}
 * 
 * @author Wahid Gazzah
 * @since 17/12/2015
 *
 */
@Service
@Transactional
public class GestionnaireReportServiceImpl implements IGestionnaireReportService{

	@Autowired
	private IGestionnaireReportDomaine gestionnaireReportDomaine;

	@Override
	public BonReceptionReportValue getBonReceptionReportParId(Long id) throws IOException {
		
		return gestionnaireReportDomaine.getBonReceptionParId(id);
	}

	@Override
	public EtiquetteRouleauFiniReportValue getEtiquetteRouleauFiniReportParId(Long id) throws IOException {
		return gestionnaireReportDomaine.getEtiquetteRouleauFiniReportParId(id);
	}

  /**
   	* Génération du report de l'inventaire
   	* 
   	* (non-Javadoc)
   	* @see com.gpro.consulting.tiers.logistique.service.atelier.report.IGestionnaireReportService#getInventaireReportValue(com.gpro.consulting.tiers.logistique.coordination.rouleaufini.value.CritereRechercheRouleauStandardValue)
   */
	@Override
	public InventaireReportValue getInventaireReportValue(CritereRechercheRouleauStandardValue pCritereRechercheRouleauStandard) throws IOException {
		return gestionnaireReportDomaine.getInventaireReportValue(pCritereRechercheRouleauStandard);
	}

	@Override
	public BonSortieFinieReportValue getBonsortieFinieReportValue(Long id, String avecMise) throws IOException {
		
		return gestionnaireReportDomaine.getBonsortieFinieReportValue(id, avecMise);
	}

	@Override
	public BonSortieFinieReportListValue getListBonSortieReport(RechercheMulticritereBonSortieFiniValue request) throws IOException {

		return gestionnaireReportDomaine.getListBonSortieReport(request);
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.logistique.service.atelier.report.IGestionnaireReportService#getBonInventaireReportValue(java.lang.Long, java.lang.String)
	 */
	@Override
	public BonInventaireReportValue getBonInventaireReportValue(Long id, String avecMise) throws IOException {
		return gestionnaireReportDomaine.getBonInventaireReportValue(id,avecMise);
	}

	@Override
	public FicheColisReportValue genererListEtiquetteRouleauReport(RechercheMulticritereRouleauFiniValue request)
			throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportDomaine.genererListEtiquetteRouleauReport(request);
	}

	@Override
	public InventaireReportValue getInventaireByOFReportValue(
			CritereRechercheRouleauStandardValue critereRechercheRouleauStandard)  throws IOException{
		// TODO Auto-generated method stub
		return gestionnaireReportDomaine.getInventaireByOFReportValue(critereRechercheRouleauStandard);
	}
	
}
