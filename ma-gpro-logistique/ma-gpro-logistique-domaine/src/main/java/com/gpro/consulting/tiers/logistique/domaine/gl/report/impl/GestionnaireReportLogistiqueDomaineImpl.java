package com.gpro.consulting.tiers.logistique.domaine.gl.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.ClientProduitLogistiqueReportingElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.ClientProduitLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FicheFaconReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FicheFaconReportValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FiniFaconLogistiqueReportingElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.FiniFaconLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.IConstanteLogistiqueReport;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.RechercheMulticritereLogistiqueReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.SousFamilleLogistiqueReportingElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.reporting.SousFamilleLogistiqueReportingReportListValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.ITraitementFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.report.IGestionnaireReportLogistiqueDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gl.facon.IFicheFaconPersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportLogistiqueDomaine}
 * 
 * @author Zeineb Medimagh
 * @since 19/10/2016
 *
 */

@Component
public class GestionnaireReportLogistiqueDomaineImpl implements IGestionnaireReportLogistiqueDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportLogistiqueDomaineImpl.class);

	@Autowired
	private IRouleauFiniPersistance rouleauFiniPersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	private ISousFamilleProduitPersistance sousFamillePersistance;
	
	@Autowired
	private IFicheFaconPersistance ficheFaconPersistance;
	
	@Autowired
	private ITraitementFaconDomaine traitementFaconPersistance;
	
	@Autowired
	private IMiseDomaine miseDomaine;

	@Override
	public ClientProduitLogistiqueReportingReportListValue getListClientProduitLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException {

		ClientProduitLogistiqueReportingReportListValue report = new ClientProduitLogistiqueReportingReportListValue();

		
		report.setFileName(IConstanteLogistiqueReport.REPORT_NAME_LOGISTIQUE_CLIENT_PRODUIT_REPORTING);
		report.setReportStream(
				new FileInputStream(IConstanteLogistiqueReport.PATH_JRXML_LOGISTIQUE_REPORTING_CLIENT_PRODUIT));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_FN_CLIENT_PATH",
				IConstanteLogistiqueReport.PATH_JASPER_LOGISTIQUE_REPORTING_CLIENT_PRODUIT);

		report.setParams(params);

		enrichissementClientProduitReportList(report, request);

		
		ArrayList<ClientProduitLogistiqueReportingReportListValue> dataList = new ArrayList<ClientProduitLogistiqueReportingReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementClientProduitReportList(ClientProduitLogistiqueReportingReportListValue report,
			RechercheMulticritereLogistiqueReportingtValue request) {

		List<RouleauFiniValue> listRouleauxFini = rouleauFiniPersistance.rechercherMultiCritereReporting(request);

		// <produitId, Map<Metrage,
		Map<Long, Map<Double, Double>> mapByProduitId = new HashMap<Long, Map<Double, Double>>();
		Map<Long, Map<Double,Double>> mapByClientId = new HashMap<Long, Map<Double, Double>>();

		if (request.getTypeRapport() != null) {
			if (request.getTypeRapport().equals("Produit")) {

				for (RouleauFiniValue rouleauFiniValue : listRouleauxFini) {

					Double metrageTotal = 0D;
					Double poidsTotal = 0D;

					Long currentProduitId = rouleauFiniValue.getProduitId();

					if (currentProduitId != null) {
						
						Map<Double,Double> values = new HashMap<Double, Double>();

							if (mapByProduitId.containsKey(currentProduitId)) {
								
								values = mapByProduitId.get(currentProduitId);
								Iterator itDetails = values.entrySet().iterator();
								
								if (rouleauFiniValue.getPoids() != null) {
									
									while(itDetails.hasNext()){
										Map.Entry<Double, Double> valuesCourante = (Map.Entry<Double, Double>) itDetails.next();
									
										metrageTotal = valuesCourante.getKey();
										poidsTotal = valuesCourante.getValue(); //Récupérer le poids
										
									}
										poidsTotal+=rouleauFiniValue.getPoids();
										
										values.clear();
										values.put(metrageTotal, poidsTotal);
										mapByProduitId.remove(currentProduitId);
										mapByProduitId.put(currentProduitId, values);
									
								}
								
								if(rouleauFiniValue.getMetrage()!=null){
									
									while(itDetails.hasNext()){
										Map.Entry<Double, Double> valuesCourante = (Map.Entry<Double, Double>) itDetails.next();
									
										metrageTotal = valuesCourante.getKey();
										poidsTotal = valuesCourante.getValue(); //Récupérer le poids
										
									}
										metrageTotal+=rouleauFiniValue.getMetrage();
										
										values.clear();
										values.put(metrageTotal, poidsTotal);
										mapByProduitId.remove(currentProduitId);
										mapByProduitId.put(currentProduitId, values);
								}
							}else{
								
								if(rouleauFiniValue.getMetrage()!=null){
									//poids =0D
									values.put(rouleauFiniValue.getMetrage(), 0D);
									mapByProduitId.put(currentProduitId,values );
								}
								
								if(rouleauFiniValue.getPoids()!= null){
								
									//Metrage =0D
									values.put(0D, rouleauFiniValue.getPoids());
									mapByProduitId.put(currentProduitId,values );
								}
								
							}
						}

					}

			} else if (request.getTypeRapport().equals("Client")) {
				
				for (RouleauFiniValue rouleauFiniValue : listRouleauxFini) {

					Double metrageTotal = 0D;
					Double poidsTotal = 0D;

					Long currentCLientId = rouleauFiniValue.getPartieInteresseeId();

					if (currentCLientId != null) {

						Map<Double,Double> values = new HashMap<Double, Double>();

						if (mapByClientId.containsKey(currentCLientId)) {
							
							values = mapByClientId.get(currentCLientId);
							
							Iterator itDetails = values.entrySet().iterator();
							
							if (rouleauFiniValue.getPoids() != null) {
								
								while(itDetails.hasNext()){
									Map.Entry<Double, Double> valuesCourante = (Map.Entry<Double, Double>) itDetails.next();
								
									metrageTotal = valuesCourante.getKey();
									poidsTotal = valuesCourante.getValue(); //Récupérer le poids
								}
									
									poidsTotal+=rouleauFiniValue.getPoids();
									
									values.clear();
									values.put(metrageTotal, poidsTotal);
									
									mapByClientId.remove(currentCLientId);
									mapByClientId.put(currentCLientId, values);
								
							}
							
							if(rouleauFiniValue.getMetrage()!=null){
								
								while(itDetails.hasNext()){
									Map.Entry<Double, Double> valuesCourante = (Map.Entry<Double, Double>) itDetails.next();
								
									metrageTotal = valuesCourante.getKey();
									poidsTotal = valuesCourante.getValue(); //Récupérer le poids
								}	
									
								metrageTotal+=rouleauFiniValue.getMetrage();
									
								values.clear();
								values.put(metrageTotal, poidsTotal);
								
								mapByClientId.remove(currentCLientId);
								mapByClientId.put(currentCLientId, values);
								
							}
						}else{
							
							if(rouleauFiniValue.getMetrage()!=null){
								//poids =0D
								values.put(rouleauFiniValue.getMetrage(), 0D);
								mapByClientId.put(currentCLientId,values );
							}
							
							if(rouleauFiniValue.getPoids()!= null){
								//Metrage =0D
								values.put(0D, rouleauFiniValue.getPoids());
								mapByClientId.put(currentCLientId,values );
							}
						}
					}
				}
			} 

			// Remplir le sub-report

			List<ClientProduitLogistiqueReportingElementValue> listDetails = new ArrayList<ClientProduitLogistiqueReportingElementValue>();

			if (request.getTypeRapport().equals("Produit")) {

				report.setCritere("Produit");
				
				Iterator it = mapByProduitId.entrySet().iterator();

				while (it.hasNext()) {

					ClientProduitLogistiqueReportingElementValue elementListe = new ClientProduitLogistiqueReportingElementValue();

					// Map <produitId, <metrage,poids>>
					Map.Entry<Long, Map<Double, Double>> pair = (Map.Entry<Long, Map<Double,Double>>) it.next();

					Long currentProduitId = pair.getKey();
					ProduitValue produit = produitPersistance.rechercheProduitById(currentProduitId);

					elementListe.setProduitAbreviation(produit.getDesignation());
					elementListe.setProduitReference(produit.getReference());
					
					Map<Double, Double> values = mapByProduitId.get(currentProduitId);
					
					Iterator itDetails = values.entrySet().iterator();
					
					while (itDetails.hasNext()) {
						Map.Entry<Double, Double> valuesCourantes = (Map.Entry<Double,Double>) itDetails.next();
						
						elementListe.setMetrage(valuesCourantes.getKey());
						elementListe.setPoids(valuesCourantes.getValue());
					}

					elementListe.setTypeRapport(request.getTypeRapport());
					listDetails.add(elementListe);
				}

			} else if (request.getTypeRapport().equals("Client")) {

				report.setCritere("Client");
				Iterator it = mapByClientId.entrySet().iterator();

				while (it.hasNext()) {

					ClientProduitLogistiqueReportingElementValue elementListe = new ClientProduitLogistiqueReportingElementValue();

					// Map <clientId, poids>
					Map.Entry<Long, Map<Double,Double>> pair = (Map.Entry<Long, Map<Double,Double>>) it.next();

					Long currentClientId = pair.getKey();
					PartieInteresseValue client = partieInteresseePersistance.getById(currentClientId);
					elementListe.setProduitAbreviation(client.getAbreviation());
					elementListe.setProduitReference(client.getReference());

					Map<Double, Double> values = mapByClientId.get(currentClientId);
					
					Iterator itDetails = values.entrySet().iterator();
					
					while (itDetails.hasNext()) {
						Map.Entry<Double, Double> valuesCourantes = (Map.Entry<Double,Double>) itDetails.next();
						
						elementListe.setMetrage(valuesCourantes.getKey());
						elementListe.setPoids(valuesCourantes.getValue());
					}

					elementListe.setTypeRapport(request.getTypeRapport());
					listDetails.add(elementListe);
				}
				
			}
		

			Collections.sort(listDetails);
			report.setListDetails(listDetails);
			report.setTypeRapport(request.getTypeRapport());
			report.setDateMin(request.getDateMin());
			report.setDateMax(request.getDateMax());
		}
	}

	@Override
	public SousFamilleLogistiqueReportingReportListValue getListSousFamillesLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException {

		SousFamilleLogistiqueReportingReportListValue report = new SousFamilleLogistiqueReportingReportListValue();
		
		report.setFileName(IConstanteLogistiqueReport.REPORT_NAME_LOGISTIQUE_SOUS_FAMILLE_REPORTING);
		report.setReportStream(
				new FileInputStream(IConstanteLogistiqueReport.PATH_JRXML_LOGISTIQUE_REPORTING_SOUS_FAMILLE));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_SOUS_FAMILLE_PATH",
				IConstanteLogistiqueReport.PATH_JASPER_LOGISTIQUE_REPORTING_SOUS_FAMILLE);

		report.setParams(params);

		enrichissementSousFamilleReportList(report, request);

		ArrayList<SousFamilleLogistiqueReportingReportListValue> dataList = new ArrayList<SousFamilleLogistiqueReportingReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementSousFamilleReportList(SousFamilleLogistiqueReportingReportListValue report,
			RechercheMulticritereLogistiqueReportingtValue request) {

		List<RouleauFiniValue> listRouleauxFini = rouleauFiniPersistance.rechercherMultiCritereReporting(request);

		Double mailleMetrageTotal = 0D; // metrage de la sous famille maille
		Double maillePoidsTotal = 0D; // poids de la sous famille maille

		Double cHTMetrageTotal = 0D; // metrage de la sous famille chaine et
										// trame
		Double cHTPoidsTotal = 0D; // poids de la sous famille chaine et trame

		for (RouleauFiniValue rouleauFiniValue : listRouleauxFini) {

			ProduitValue produit = produitPersistance.rechercheProduitById(rouleauFiniValue.getProduitId());
			
			Long idSousFamille = produit.getSousFamilleId();

			if (idSousFamille != null) {

				SousFamilleProduitValue sousFamilleProduit = sousFamillePersistance
						.rechercheSousFamilleProduitById(idSousFamille);

				if (rouleauFiniValue.getMetrage() != null) {
					
					if (sousFamilleProduit != null && sousFamilleProduit.getDesignation().equalsIgnoreCase("MAILLE")) {
						mailleMetrageTotal += rouleauFiniValue.getMetrage();
					} else if (sousFamilleProduit != null
							&& sousFamilleProduit.getDesignation().equalsIgnoreCase("CHAINE ET TRAME")) {
						cHTMetrageTotal += rouleauFiniValue.getMetrage();
					}
				}

				if (rouleauFiniValue.getPoids() != null) {

					if (sousFamilleProduit != null && sousFamilleProduit.getDesignation().equalsIgnoreCase("MAILLE")) {
						maillePoidsTotal += rouleauFiniValue.getPoids();
					} else if (sousFamilleProduit != null
							&& sousFamilleProduit.getDesignation().equalsIgnoreCase("CHAINE ET TRAME")) {
						cHTPoidsTotal += rouleauFiniValue.getPoids();
					}
				}
			}
		}

		// Remplir le sub-report

		List<SousFamilleLogistiqueReportingElementValue> listDetails = new ArrayList<SousFamilleLogistiqueReportingElementValue>();

		SousFamilleLogistiqueReportingElementValue element = new SousFamilleLogistiqueReportingElementValue();
		
		element.setMetrageCHT(cHTMetrageTotal);
		element.setMetrageMaille(mailleMetrageTotal);
		element.setPoidsCHT(cHTPoidsTotal);
		element.setPoidsMaille(maillePoidsTotal);
		
		listDetails.add(element);
		
		report.setListDetails(listDetails);
		report.setCritere("Maille/Chaine Et Trame");
		report.setDateMax(request.getDateMax());
		report.setDateMin(request.getDateMin());
		
	}

	@Override
	public FiniFaconLogistiqueReportingReportListValue getListFiniFaconLogistiqueReport(
			RechercheMulticritereLogistiqueReportingtValue request) throws IOException {

		FiniFaconLogistiqueReportingReportListValue report = new FiniFaconLogistiqueReportingReportListValue();
		
		report.setFileName(IConstanteLogistiqueReport.REPORT_NAME_LOGISTIQUE_FINI_FACON_REPORTING);
		report.setReportStream(
				new FileInputStream(IConstanteLogistiqueReport.PATH_JRXML_LOGISTIQUE_REPORTING_FINI_FACON));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_FINI_FACON_PATH",
				IConstanteLogistiqueReport.PATH_JASPER_LOGISTIQUE_REPORTING_FINI_FACON);

		report.setParams(params);

		enrichissementFiniFaconReportList(report, request);

		ArrayList<FiniFaconLogistiqueReportingReportListValue> dataList = new ArrayList<FiniFaconLogistiqueReportingReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	
	private void enrichissementFiniFaconReportList(FiniFaconLogistiqueReportingReportListValue report,
			RechercheMulticritereLogistiqueReportingtValue request) {
		
		List<RouleauFiniValue> listRouleauxFini = rouleauFiniPersistance.rechercherMultiCritereReporting(request);

		Double finiMetrageTotal = 0D; // metrage du type fini
		Double finiPoidsTotal = 0D; // poids du type fini

		Double faconMetrageTotal = 0D; // metrage du type facon
		Double faconPoidsTotal = 0D; // poids du type facon

		for (RouleauFiniValue rouleauFiniValue : listRouleauxFini) {

				if (rouleauFiniValue.getMetrage() != null) {
					
					if (rouleauFiniValue.isFini()) {
						finiMetrageTotal += rouleauFiniValue.getMetrage();
					} else //type facon 
					{
						faconMetrageTotal += rouleauFiniValue.getMetrage();
					}
				}

				if (rouleauFiniValue.getPoids() != null) {

					if (rouleauFiniValue.isFini()) {
						finiPoidsTotal += rouleauFiniValue.getPoids();
					} else //type facon
					{
						faconPoidsTotal += rouleauFiniValue.getPoids();
					}
				}
			}

		// Remplir le sub-report

		List<FiniFaconLogistiqueReportingElementValue> listDetails = new ArrayList<FiniFaconLogistiqueReportingElementValue>();

		FiniFaconLogistiqueReportingElementValue element = new FiniFaconLogistiqueReportingElementValue();
		
		element.setMetrageFini(finiMetrageTotal);
		element.setMetrageFacon(faconMetrageTotal);
		element.setPoidsFini(finiPoidsTotal);
		element.setPoidsFacon(faconPoidsTotal);
		
		listDetails.add(element);
		
		report.setListDetails(listDetails);
		report.setCritere("Fini / Façon");
		report.setDateMax(request.getDateMax());
		report.setDateMin(request.getDateMin());
		
	}

	@Override
	public FicheFaconReportValue getFicheFaconLogistiqueReport(Long id) throws IOException {


		FicheFaconReportValue report = new FicheFaconReportValue();
		
		report.setFileName(IConstanteLogistiqueReport.REPORT_NAME_LOGISTIQUE_FICHE_FACON_REPORTING);
		report.setReportStream(
				new FileInputStream(IConstanteLogistiqueReport.PATH_JRXML_LOGISTIQUE_REPORTING_FICHE_FACON));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_FICHE_FACON_PATH",
				IConstanteLogistiqueReport.PATH_JASPER_LOGISTIQUE_REPORTING_FICHE_FACON);

		report.setParams(params);

		
		enrichissementFicheFaconReportList(report, id);

		ArrayList<FicheFaconReportValue> dataList = new ArrayList<FicheFaconReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	
	private void enrichissementFicheFaconReportList(FicheFaconReportValue report, Long id) {
		
		FicheFaconValue ficheFacon = ficheFaconPersistance.getById(id);
		
		if(ficheFacon!= null){
			
			if(ficheFacon.getProduitId()!=null){
				
				ProduitValue produit= produitPersistance.rechercheProduitById(ficheFacon.getProduitId());
				
				report.setDesignationProduit(produit.getDesignation());
				report.setReferenceProduit(produit.getReference());
				report.setCodeCouleur(produit.getCodeCouleur());
			}
			
			if(ficheFacon.getPartieIntId()!=null){
				
				PartieInteresseValue client = partieInteresseePersistance.getById(ficheFacon.getPartieIntId());
				
				report.setClient(client.getAbreviation());
			}
			
			report.setReferenceBR(ficheFacon.getRefBonReception());
			report.setMethodeTeinture(ficheFacon.getMethodeTeinture());
			report.setObservations(ficheFacon.getObservations());
			
			List<FicheFaconReportElementValue> traitementList = new ArrayList<FicheFaconReportElementValue>();
			if(ficheFacon.getListeTraitementsFiche()!=null){
				for (TraitementFicheValue traitementFiche : ficheFacon.getListeTraitementsFiche()) {
					
					FicheFaconReportElementValue element = new FicheFaconReportElementValue();
					TraitementFaconValue traitementFacon = traitementFaconPersistance.getById(traitementFiche.getTraitementId());
					
					if(traitementFacon!= null){
						element.setDesignationTraitement(traitementFacon.getDesignation());
					}
					
					//Prix Unitaire depuis traitementFiche
					element.setPu(traitementFiche.getPu());
					
					traitementList.add(element);
				}
			}
			report.setTraitementList(traitementList);
			
			report.setListRefMise(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
		
		}
		
	}
}
