package com.gpro.consulting.tiers.commun.domaine.elementBase.report;

import java.io.IOException;

import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;

/**
 * GestionnaireReportCommunDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public interface IGestionnaireReportCommunDomaine {

	public ProduitsReportListValue getListProduitsReport(
			RechercheMulticritereProduitValue request) throws IOException;
	
	public ProduitsReportListValue getListProduitsReportSpecial(
			RechercheMulticritereProduitValue request) throws IOException;
	
	

	public ArticleReportValue getArticleReport(Long id) throws IOException;

	public ArticleReportListValue getListArticleReport(RecherecheMulticritereArticleValue request) throws IOException;

	public FicheColisReportValue getColisReport(RecherecheMulticritereArticleValue request) throws IOException;
	
	public ProduitReportValue getProduitReport(Long id) throws IOException;
	

}
