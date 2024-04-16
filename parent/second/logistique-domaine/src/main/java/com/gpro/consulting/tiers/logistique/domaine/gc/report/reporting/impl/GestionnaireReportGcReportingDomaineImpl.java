package com.gpro.consulting.tiers.logistique.domaine.gc.report.reporting.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RegionValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IRegionPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommercialeReport;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.RechercheMulticritereSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.ResultatRechecheSituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.gcReporting.situation.value.SituationReportingValueComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.reporting.IGestionnaireReportGcReportingDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.reporting.situation.ISituationReportingDomaine;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 *
 */

@Component
public class GestionnaireReportGcReportingDomaineImpl implements IGestionnaireReportGcReportingDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGcReportingDomaineImpl.class);
	
	@Autowired
	private ISituationReportingDomaine situationReportingDomaine;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;
	
	@Autowired
	private IRegionPersistance regionPersistance;
	
	@Override
	public SituationReportingReportListValue getListSituationReport(
			RechercheMulticritereSituationReportingValue request,Long solde)
			throws IOException {
		
		SituationReportingReportListValue report = new SituationReportingReportListValue();
		
		report.setFileName(IConstanteCommercialeReport.REPORT_NAME_SITUATION_REPORTING);
		report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_SITUATION_REPORTING));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);
		params.put("SUBREPORT_SITUATION_PATH", "C://ERP/Lib/STIT_Reporting/situation/situation_sub_report.jasper");
		
		report.setParams(params);
		
		ResultatRechecheSituationReportingValue result = situationReportingDomaine.rechercherMultiCritere(request);
		
		enrichmentSituationReportList(report, result, request,solde);

		ArrayList<SituationReportingReportListValue> dataList = new ArrayList<SituationReportingReportListValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return report;
	}

	private void enrichmentSituationReportList(
			SituationReportingReportListValue report,
			ResultatRechecheSituationReportingValue result,
			RechercheMulticritereSituationReportingValue request,
			Long solde) {
		
		report.setPartieIntId(request.getPartieIntId());
		if(request.getPartieIntId() != null){
			
			PartieInteresseValue client = partieInteresseePersistance.getById(request.getPartieIntId());
			
			if(client != null){
				
				report.setClientAbreviation(client.getAbreviation());
				report.setClientReference(client.getReference());
				if(client.getRegionId() != null){
					RegionValue region = regionPersistance.getById(client.getRegionId());
					if(region != null){
						report.setRegionDesignation(region.getDesignation());
					}
				}
			}
		}
		
		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());
		
		report.setSoldeMin(request.getSoldeMin());
		report.setSoldeMax(request.getSoldeMax());

		if (solde==2) {
			
			//soldeAcuel
			List<SituationReportingValue> listSituation = new ArrayList<SituationReportingValue>();
			
			if (listSituation!=null && !listSituation.isEmpty()) {
				
			
			for (SituationReportingValue elt : result.getListSituationReporting()) {
				
				if (elt.getSoldeActuel()> 0.0) {
					
					listSituation.add(elt);
					//Collections.sort(listSituation, new SituationReportingValueComparator());

				}

			}
			}
			
			Collections.sort(listSituation, new SituationReportingValueComparator());

			report.getListSituation().addAll(listSituation);

		}else{
			
			Collections.sort(result.getListSituationReporting(), new SituationReportingValueComparator());

		report.getListSituation().addAll(result.getListSituationReporting());
		}
	}

	@Override
	public SituationReportingReportListValue getListSituationAchatReport(
			RechercheMulticritereSituationReportingValue request, Long solde)throws IOException {
SituationReportingReportListValue report = new SituationReportingReportListValue();
		
		report.setFileName(IConstanteCommercialeReport.REPORT_NAME_SITUATION_REPORTING);
		report.setReportStream(new FileInputStream(IConstanteCommercialeReport.PATH_JRXML_SITUATION_ACHAT_REPORTING));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(IConstanteCommercialeReport.PATH_LOGO, IConstanteCommercialeReport.LOGO_STIT);
		params.put("SUBREPORT_SITUATION_PATH", "C://ERP/Lib/STIT_Reporting/situationAchat/situation_sub_report.jasper");
		
		report.setParams(params);
		
		ResultatRechecheSituationReportingValue result = situationReportingDomaine.rechercherMultiCritereAchat(request);
		
		enrichmentSituationReportList(report, result, request,solde);

		ArrayList<SituationReportingReportListValue> dataList = new ArrayList<SituationReportingReportListValue>();
		dataList.add(report);
   
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
      
		return report;
	}

}
