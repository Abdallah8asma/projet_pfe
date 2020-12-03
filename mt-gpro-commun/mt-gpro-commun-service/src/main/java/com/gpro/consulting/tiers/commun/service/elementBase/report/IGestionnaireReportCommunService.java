package com.gpro.consulting.tiers.commun.service.elementBase.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;

/**
 * Commun GestionnaireReport Service Interface
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public interface IGestionnaireReportCommunService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitsReportListValue getListProduitsReport(
			RechercheMulticritereProduitValue request) throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitsReportListValue getListProduitsReportSpecial(
			RechercheMulticritereProduitValue request) throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	ArticleReportValue getArticleReport(Long id) throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisReportValue getListArticleReport(
			RecherecheMulticritereArticleValue request) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	ProduitReportValue getProduitReport(Long id) throws IOException;

}
