package com.gpro.consulting.tiers.logistique.domaine.atelier.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.report.value.ColisValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.FicheColisReportValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ReceptionTraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RouleauEcruValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value.BonReceptionReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value.ReceptionTraitementReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonReception.value.RouleauEcruReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.boninventaire.BonInventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieFinieReportListValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieFinieReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.BonSortieReportElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.RouleauFiniReportGroupedByProduitValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.bonsortiefini.value.RouleauFiniReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.inventaire.value.InventaireReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.report.rouleaufini.value.EtiquetteRouleauFiniReportValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ElementResultatRechecheRouleauStandardComparator;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ElementResultatRechecheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.EntrepotValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.cache.IGestionnaireLogistiqueCacheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.report.IGestionnaireReportDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IChoixRouleauDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IEntrepotDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.IBonReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.ITraitementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.IBonInventaireFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;

/**
 * Implementation of {@link IGestionnaireReportDomaine}
 * 
 * @author Wahid Gazzah
 * @since 17/12/2015
 *
 */
@Component
public class GestionnaireReportDomaineImpl implements IGestionnaireReportDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportDomaineImpl.class);

	private static final Double SOMME_ZERO = 0D;

	private static final String REPORTNAME_BONRECEPTION = "bon de reception";
	private static final String REPORTNAME_BONSORTIE = "bon de sortie";
	private static final String REPORTNAME_ETIQUETTEROULEAUFINI = "Etiquette Rouleau Fini";
	private static final String REPORTNAME_INVENTAIRE = "Inventaire";
	private static final String REPORTNAME_BONSORTIE_LIST = "bons des sortie";

	@Autowired
	private IBonReceptionPersistance bonReceptionPersitance;

	@Autowired
	private IRouleauFiniPersistance rouleauFiniPersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private ITraitementPersistance traitementPersistance;

	@Autowired
	private ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	private IRouleauFiniDomaine rouleauFiniDomaine;

	@Autowired
	private IEntrepotDomaine entrepotDomaine;

	@Autowired
	private IChoixRouleauDomaine choixRouleauDomaine;

	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private IGestionnaireLogistiqueCacheDomaine gestionnaireLogistiqueCacheDomaine;

	@Autowired
	private IBonInventaireFiniPersistance bonInventaireFiniPersistance;

	@Override
	public BonReceptionReportValue getBonReceptionParId(Long id) throws IOException {

		BonReceptionReportValue bonReceptionReportValue = new BonReceptionReportValue();

		// enrechissement des param du report
		bonReceptionReportValue.setFileName(REPORTNAME_BONRECEPTION);
		bonReceptionReportValue
				.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonReception/bon_reception_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logoSTIT.jpg");

		params.put("SUBREPORT_ROULEAUX_PATH", "C://ERP/Lib/STIT_BonReception/bon_reception_rouleau_sub_report.jasper");
		params.put("SUBREPORT_TRAITEMENT_PATH",
				"C://ERP/Lib/STIT_BonReception/bon_reception_traitement_sub_report.jasper");

		bonReceptionReportValue.setParams(params);

		BonReceptionValue bonReceptionValue = bonReceptionPersitance.rechercheBonReceptionParId(id);

		// enrichissement du report VALUE to REPORT VALUE
		enrichmentReport(bonReceptionValue, bonReceptionReportValue);

		// traitement
		PartieInteresseValue partieInteresseValueTrouve = null;
		ProduitValue produitValue = null;

		if (bonReceptionReportValue != null) {
			if (bonReceptionReportValue.getProduit() != null) {
				PartieInteresseValue partieInteresseValue = new PartieInteresseValue();
				partieInteresseValue.setId(Long.valueOf(bonReceptionReportValue.getPartieInteressee()));
				partieInteresseValueTrouve = partieInteresseePersistance
						.recherchePartieInteresseeParId(partieInteresseValue);
				produitValue = produitPersistance
						.rechercheProduitById(Long.valueOf(bonReceptionReportValue.getProduit()));
			}
		}

		if (partieInteresseValueTrouve != null) {
			bonReceptionReportValue.setDesignationpartieInteresse(partieInteresseValueTrouve.getAbreviation());
		} else {
			bonReceptionReportValue.setDesignationpartieInteresse(null);
		}

		if (produitValue != null) {
			bonReceptionReportValue.setDesignationProduit(produitValue.getDesignation());

			SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
					.rechercheSousFamilleProduitById(produitValue.getSousFamilleId());

			if (sousFamilleProduitValue != null) {
				bonReceptionReportValue.setSousFamilleDesignation(sousFamilleProduitValue.getDesignation());
			}

		} else {
			bonReceptionReportValue.setDesignationProduit(null);
			bonReceptionReportValue.setSousFamilleDesignation(null);
		}

		ArrayList<BonReceptionReportValue> dataBonReceptionList = new ArrayList<BonReceptionReportValue>();
		dataBonReceptionList.add(bonReceptionReportValue);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataBonReceptionList);

		bonReceptionReportValue.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonReceptionReportValue;
	}

	@Override
	public EtiquetteRouleauFiniReportValue getEtiquetteRouleauFiniReportParId(Long id) throws IOException {

		EtiquetteRouleauFiniReportValue etiquetteRouleauFiniReportValue = new EtiquetteRouleauFiniReportValue();

		RouleauFiniValue rouleauFinivalue = rouleauFiniPersistance.rechercheRouleauFiniParId(id);

		if (rouleauFinivalue.isFini() == null) {
			rouleauFinivalue.setFini(false);
		}
		if (rouleauFinivalue.isFini()) {
			etiquetteRouleauFiniReportValue.setReportStream(new FileInputStream(
					"C://ERP/Lib/STIT_EtiquetteRouleauFini/fini/etiquette_rouleau_fini_report.jrxml"));

			// enrechissement des param du report
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("p_PathLogo", "/report/logoSTIT.jpg");
			etiquetteRouleauFiniReportValue.setParams(params);

		} else {
			etiquetteRouleauFiniReportValue.setReportStream(new FileInputStream(
					"C://ERP/Lib/STIT_EtiquetteRouleauFini/facon/etiquette_rouleau_fini_report.jrxml"));
		}

		etiquetteRouleauFiniReportValue.setFileName(REPORTNAME_ETIQUETTEROULEAUFINI);

		// enrichissement du report VALUE to REPORT VALUE
		etiquetteRouleauFiniReportValue.setCodeBarre(rouleauFinivalue.getReference());
		etiquetteRouleauFiniReportValue.setMetrage(rouleauFinivalue.getMetrage());
		etiquetteRouleauFiniReportValue.setPoids(rouleauFinivalue.getPoids());
		etiquetteRouleauFiniReportValue.setLaize(rouleauFinivalue.getLaize());
		etiquetteRouleauFiniReportValue.setReferenceMise(rouleauFinivalue.getReferenceMise());

		// traitement
		PartieInteresseValue partieInteresseValueTrouve = null;
		ProduitValue produitValue = null;

		if (etiquetteRouleauFiniReportValue != null) {
			if (rouleauFinivalue.getProduitId() != null) {
				PartieInteresseValue partieInteresseValue = new PartieInteresseValue();
				partieInteresseValue.setId(rouleauFinivalue.getPartieInteresseeId());
				partieInteresseValueTrouve = partieInteresseePersistance
						.recherchePartieInteresseeParId(partieInteresseValue);
				produitValue = produitPersistance.rechercheProduitById(rouleauFinivalue.getProduitId());
			}
		}

		if (partieInteresseValueTrouve != null) {
			etiquetteRouleauFiniReportValue.setPartieInteresse(partieInteresseValueTrouve.getAbreviation());
		}

		if (produitValue != null) {
			etiquetteRouleauFiniReportValue.setProduit(produitValue.getDesignation());
			etiquetteRouleauFiniReportValue.setComposition(produitValue.getComposition());
			etiquetteRouleauFiniReportValue.setReference(produitValue.getReference());
		}

		if (rouleauFinivalue.getChoix() != null) {
			ChoixRouleauValue choixRouleau = choixRouleauDomaine.getChoixRouleauById(rouleauFinivalue.getChoix());
			if (choixRouleau != null) {
				etiquetteRouleauFiniReportValue.setChoix(choixRouleau.getDesignation());
			}
		}

		ArrayList<EtiquetteRouleauFiniReportValue> dataEtiquetteRouleauList = new ArrayList<EtiquetteRouleauFiniReportValue>();
		dataEtiquetteRouleauList.add(etiquetteRouleauFiniReportValue);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(
				dataEtiquetteRouleauList);

		etiquetteRouleauFiniReportValue.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return etiquetteRouleauFiniReportValue;
	}

	/**
	  * Report Inventaire
	  *  (non-Javadoc)
	  * @throws IOException  
	  * @see com.gpro.consulting.tiers.logistique.domaine.report.IGestionnaireReportDomaine#getInventaireReportValue(com.gpro.consulting.tiers.logistique.coordination.rouleaufini.value.CritereRechercheRouleauStandardValue)
	  */
	@Override
	public InventaireReportValue getInventaireReportValue(CritereRechercheRouleauStandardValue critereRechercheRouleauStandard) throws IOException{
		/** Instantiation du report */
		InventaireReportValue inventaireReport = new InventaireReportValue();
   
	   // enrechissement des param du report
	   inventaireReport.setFileName(REPORTNAME_INVENTAIRE);
	   inventaireReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_Inventaire/inventaire_report.jrxml"));
	   
	   HashMap<String, Object> params = new HashMap<String, Object>();
	   /** Liste des paramètres : logo et critères de recherche*/
	   params.put("p_PathLogo", "C:\\ERP\\logos_clients\\logo_client.png");
	   params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_Inventaire/inventaire_sub_report.jasper");
	   
	   inventaireReport.setParams(params);
	   
	   /** Rechercher les rouleaux selon les critères de recherche */
	   ResultatRechecheRouleauStandardValue resultatRechecheRouleauStandard = rouleauFiniDomaine.rechercherRouleauInventaire(critereRechercheRouleauStandard);
	   //System.out.println("---- ** resultatRechecheRouleauStandard---"+resultatRechecheRouleauStandard);
		// enrichissement du report VALUE
	   if(critereRechercheRouleauStandard.getEntrepot() != null){
		   EntrepotValue entrepot = entrepotDomaine.getEntrepotById(critereRechercheRouleauStandard.getEntrepot());
		   if(entrepot != null){
			   inventaireReport.setEntrepot(entrepot.getDesignation());
		   }
	   }
	   
	  
	   
	    if(critereRechercheRouleauStandard.getClient() != null){
	    	PartieInteresseValue partieInteresseValue = new PartieInteresseValue();
	    	partieInteresseValue.setId(critereRechercheRouleauStandard.getClient());
	    	PartieInteresseValue partieInteresseValueTrouve = partieInteresseePersistance.recherchePartieInteresseeParId(partieInteresseValue);
		    if(partieInteresseValueTrouve != null){
		    	inventaireReport.setClient(partieInteresseValueTrouve.getAbreviation());
			}
	    }
	   
	    ProduitValue produitValue = null;
	    
	    List<ElementResultatRechecheRouleauStandardValue> elementsListToSet = new ArrayList<ElementResultatRechecheRouleauStandardValue>();
		   //System.out.println("---- ** resultatRechecheRouleauStandard.getListeElementResultatRechecheRouleauStandardValue()---"+resultatRechecheRouleauStandard.getListeElementResultatRechecheRouleauStandardValue());

	    List<ElementResultatRechecheRouleauStandardValue> elements = resultatRechecheRouleauStandard.getListeElementResultatRechecheRouleauStandardValue();
	    
	    
	    // Alimentation de la liste sub report: avec application des criteres de recherche sur Produit
	    for(ElementResultatRechecheRouleauStandardValue element : elements){
	    	boolean exist = true;
	      	if(element.getIdProduit() != null){
	      		
				produitValue = produitPersistance.rechercheProduitById(element.getIdProduit());

				if(produitValue != null){
					
					element.setDesignation(produitValue.getDesignation());
					element.setReferenceProduit(produitValue.getReference());
					element.setPrixUnitaire(produitValue.getPrixUnitaire());
					
					if(produitValue.getPrixUnitaire() != null && element.getMetrage()!= null){
						element.setPrixTotal(produitValue.getPrixUnitaire() * element.getMetrage());
					}
					
					//chang. de .contains() par .equals()
					if(estNonVide(critereRechercheRouleauStandard.getDesignationQuiContient() )){
						exist = (produitValue.getDesignation().toUpperCase().equals(
								critereRechercheRouleauStandard.getDesignationQuiContient().toUpperCase()));
					}
					
				}
	    	}
	      	
	      	// Application des criteres de recherche sur Produit
	      	if( 
	      		((critereRechercheRouleauStandard.getNombreColieA() != null && element.getNombreColis() <= critereRechercheRouleauStandard.getNombreColieA())
	      					||(critereRechercheRouleauStandard.getNombreColieA() == null))
	      		&&(((critereRechercheRouleauStandard.getNombreColieDu() != null && element.getNombreColis() >= critereRechercheRouleauStandard.getNombreColieDu())
	    	      			||(critereRechercheRouleauStandard.getNombreColieDu() == null)))
	      		&&(((critereRechercheRouleauStandard.getMetrageA() != null && element.getMetrage() <= critereRechercheRouleauStandard.getMetrageA())
	    	      			||(critereRechercheRouleauStandard.getMetrageA() == null)))
	      		&&(((critereRechercheRouleauStandard.getMetrageDu() != null && element.getMetrage() >= critereRechercheRouleauStandard.getMetrageDu())
	    	      			||(critereRechercheRouleauStandard.getMetrageDu() == null)))
	    	      			
	    	    &&(exist)
	      	){
	      		 if(estNonVide(critereRechercheRouleauStandard.getReferenceProduit())){
	       			if (critereRechercheRouleauStandard.getReferenceProduit().equals(element.getReferenceProduit())) {
	       				
	       				elementsListToSet.add(element);
	       				
	       			}
	       			else if (critereRechercheRouleauStandard.getDesignationQuiContient().equals(element.getDesignation())) {
	       				elementsListToSet.add(element);
					}
	      		 

	      	}else{
   				elementsListToSet.add(element);
  		 }
	      	
	    }
	    }
	    if(critereRechercheRouleauStandard.getOrderBy() != null){
	    	ElementResultatRechecheRouleauStandardComparator comparator = new ElementResultatRechecheRouleauStandardComparator();
	    	comparator.setOrderBy(critereRechercheRouleauStandard.getOrderBy());
	    	Collections.sort(elementsListToSet, comparator);
	    }
	    
	    
	   
	    
	    inventaireReport.setElementsList(elementsListToSet);
	    
	    inventaireReport.setEmplacement(critereRechercheRouleauStandard.getEmplacement());
	    inventaireReport.setMetrageA((critereRechercheRouleauStandard.getMetrageA()!= null) ?critereRechercheRouleauStandard.getMetrageA().toString():"");
	    inventaireReport.setMetrageDu((critereRechercheRouleauStandard.getMetrageDu() != null) ?critereRechercheRouleauStandard.getMetrageDu().toString():"");
	    inventaireReport.setNombreColieA((critereRechercheRouleauStandard.getNombreColieA()!=null)?critereRechercheRouleauStandard.getNombreColieA().toString():"");
	    inventaireReport.setNombreColieDu((critereRechercheRouleauStandard.getNombreColieDu() != null) ?critereRechercheRouleauStandard.getNombreColieDu().toString():"");
		
	    inventaireReport.setDateEtat(critereRechercheRouleauStandard.getDateEtat());
	    inventaireReport.setOrderBy(critereRechercheRouleauStandard.getOrderBy());
	    inventaireReport.setDesignationQuiContient(critereRechercheRouleauStandard.getDesignationQuiContient());
	    inventaireReport.setReferenceProduit(critereRechercheRouleauStandard.getReferenceProduit());

	   
		ArrayList<InventaireReportValue> dataInventaireReportList = new ArrayList<InventaireReportValue>();
		dataInventaireReportList.add(inventaireReport);
  
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataInventaireReportList);
		
		inventaireReport.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
	   
	   return inventaireReport;
	}
	
	
	
	private boolean estNonVide(String val) {
	    return val != null && !"".equals(val)  && !"undefined".equals(val);
	}


	@Override
	public BonSortieFinieReportValue getBonsortieFinieReportValue(Long id, String avecMise) throws IOException {

		BonSortieFinieReportValue bonSortieFinieReportValue = new BonSortieFinieReportValue();

		// enrechissement des param du report
		bonSortieFinieReportValue.setFileName(REPORTNAME_BONSORTIE);
		bonSortieFinieReportValue
				.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonSortieFini/bonsortiefini_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "C:\\ERP\\logos_clients\\logo_client.png");

		params.put("SUBREPORT_BONSORTIE_PATH", "C://ERP/Lib/STIT_BonSortieFini/bonsortiefini_sub_report.jasper");

		bonSortieFinieReportValue.setParams(params);

		BonSortieFiniValue bonSortieFiniValue = bonSortieFiniPersistance.getBonSortieFiniById(id);

		// enrichissement du REPORT VALUE & traitement
		bonSortieFinieReportValue.setDateSortie(bonSortieFiniValue.getDateSortie());
		bonSortieFinieReportValue.setNbColis(bonSortieFiniValue.getNbColis());
		bonSortieFinieReportValue.setReference(bonSortieFiniValue.getReference());
		bonSortieFinieReportValue.setType(bonSortieFiniValue.getType());

		/** Grouper par produit id */
		Map<Long, List<RouleauFiniValue>> mapRouleau = new HashMap<Long, List<RouleauFiniValue>>();
		for (RouleauFiniValue rouleau : bonSortieFiniValue.getListeRouleauFini()) {
			Long key = rouleau.getProduitId();
			if (mapRouleau.get(key) == null) {
				mapRouleau.put(key, new ArrayList<RouleauFiniValue>());
			}
			mapRouleau.get(key).add(rouleau);
		}

		Iterator it = mapRouleau.entrySet().iterator();
		List<RouleauFiniReportGroupedByProduitValue> listRouleauFiniReportGrouppedByProduit = new ArrayList<>();
		while (it.hasNext()) {
			// Création d'un nouveau Elément
			RouleauFiniReportGroupedByProduitValue element = new RouleauFiniReportGroupedByProduitValue();

			Map.Entry<Long, List<RouleauFiniValue>> pair = (Map.Entry<Long, List<RouleauFiniValue>>) it.next();
			/** Enrichissement d'un element */
			// avec mise decide si on affiche le rapport avec Mise ou Pas
			element.setAvecMise(avecMise);

			// Produit
			element.setProduitId(pair.getKey());

			if (element.getProduitId() != null) {
				ProduitValue produitValue = produitPersistance.rechercheProduitById(element.getProduitId());

				if (produitValue != null) {

					SousFamilleProduitValue sousFamilleProduitValue = null;

					if (produitValue.getSousFamilleId() != null) {
						sousFamilleProduitValue = sousFamilleProduitPersistance
								.rechercheSousFamilleProduitById(produitValue.getSousFamilleId());
					}

					if (sousFamilleProduitValue != null) {
						element.setType(sousFamilleProduitValue.getDesignation());
					}

					element.setRefTissu(produitValue.getReference());
					element.setComposition(produitValue.getComposition());
					element.setTissu(produitValue.getDesignation());
				}
			}

			Double totalMetrage = SOMME_ZERO;
			Double totalPoids = SOMME_ZERO;

			List<RouleauFiniReportValue> listRouleauFiniReportValue = new ArrayList<RouleauFiniReportValue>();

			for (RouleauFiniValue rouleauFiniValue : pair.getValue()) {
				RouleauFiniReportValue rouleauFiniReportValue = new RouleauFiniReportValue();

				if (rouleauFiniValue.getMetrage() != null) {
					totalMetrage = rouleauFiniValue.getMetrage().doubleValue() + totalMetrage;
				}
				if (rouleauFiniValue.getPoids() != null) {
					totalPoids = rouleauFiniValue.getPoids().doubleValue() + totalPoids;
				}

				element.setTotalMetrage(totalMetrage);
				element.setTotalPoids(totalPoids);

				if (rouleauFiniValue.getChoix() != null) {
					ChoixRouleauValue choixRouleau = choixRouleauDomaine
							.getChoixRouleauById(rouleauFiniValue.getChoix());
					if (choixRouleau != null) {
						rouleauFiniReportValue.setChoix(choixRouleau.getDesignation());
					}
				}
				rouleauFiniReportValue.setLaize(rouleauFiniValue.getLaize());
				rouleauFiniReportValue.setMetrage(rouleauFiniValue.getMetrage());
				rouleauFiniReportValue.setMise(rouleauFiniValue.getReferenceMise());
				rouleauFiniReportValue.setPoids(rouleauFiniValue.getPoids());
				rouleauFiniReportValue.setRefRouleau(rouleauFiniValue.getReference());
				// avec mise decide si on affiche le rapport avec Mise ou Pas
				rouleauFiniReportValue.setAvecMise(avecMise);
				listRouleauFiniReportValue.add(rouleauFiniReportValue);
			}

			element.setList(listRouleauFiniReportValue);

			listRouleauFiniReportGrouppedByProduit.add(element);
			it.remove();
		}

		PartieInteresseValue partieInteresseValueTrouve = null;

		if (bonSortieFiniValue.getPartieInt() != null) {
			PartieInteresseValue partieInteresseValue = new PartieInteresseValue();
			partieInteresseValue.setId(Long.valueOf(bonSortieFiniValue.getPartieInt()));
			partieInteresseValueTrouve = partieInteresseePersistance
					.recherchePartieInteresseeParId(partieInteresseValue);
		}

		if (partieInteresseValueTrouve != null) {
			bonSortieFinieReportValue.setClient(partieInteresseValueTrouve.getAbreviation());
		} else {
			bonSortieFinieReportValue.setClient(null);
		}

		bonSortieFinieReportValue.setListeRouleauFini(listRouleauFiniReportGrouppedByProduit);

		ArrayList<BonSortieFinieReportValue> dataBonSortieFinieReportList = new ArrayList<BonSortieFinieReportValue>();
		dataBonSortieFinieReportList.add(bonSortieFinieReportValue);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(
				dataBonSortieFinieReportList);
		bonSortieFinieReportValue.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonSortieFinieReportValue;

	}

	/**
	 * Set the data needs from {@link BonReceptionValue} to
	 * {@link BonReceptionReportValue} .
	 * 
	 * @param bonReceptionValue
	 * @param bonReceptionReportValue
	 */
	private void enrichmentReport(BonReceptionValue bonReceptionValue,
			BonReceptionReportValue bonReceptionReportValue) {

		bonReceptionReportValue.setId(bonReceptionValue.getId());
		bonReceptionReportValue.setBcClient(bonReceptionValue.getBcClient());
		bonReceptionReportValue.setDateIntroduction(bonReceptionValue.getDateIntroduction());
		bonReceptionReportValue.setDateLivraison(bonReceptionValue.getDateLivraison());
		bonReceptionReportValue.setEtat(bonReceptionValue.getEtat());
		bonReceptionReportValue.setLaizeFini(bonReceptionValue.getLaizeFini());
		bonReceptionReportValue.setMetrageTrouve(bonReceptionValue.getMetrageTrouve());
		bonReceptionReportValue.setNombreRouleau(bonReceptionValue.getNombreRouleau());
		bonReceptionReportValue.setObservations(bonReceptionValue.getObservations());
		bonReceptionReportValue.setPartieInteressee(bonReceptionValue.getPartieInteressee().toString());
		bonReceptionReportValue.setPoidsTrouve(bonReceptionValue.getPoidsTrouve());
		bonReceptionReportValue.setProduit(bonReceptionValue.getProduit().toString());
		bonReceptionReportValue.setReference(bonReceptionValue.getReference());

		List<RouleauEcruReportValue> listeRouleauxEcru = new ArrayList<RouleauEcruReportValue>();
		List<ReceptionTraitementReportValue> listeTraitements = new ArrayList<ReceptionTraitementReportValue>();

		for (RouleauEcruValue rouleauEcruValue : bonReceptionValue.getListeRouleauxEcru()) {

			RouleauEcruReportValue rouleauEcruReportValue = new RouleauEcruReportValue();
			rouleauEcruReportValue.setMetrageAnnonce(rouleauEcruValue.getMetrageAnnonce());
			rouleauEcruReportValue.setPoidsAnnonce(rouleauEcruValue.getPoidsAnnonce());
			rouleauEcruReportValue.setReference(rouleauEcruValue.getReference());

			listeRouleauxEcru.add(rouleauEcruReportValue);
		}

		for (ReceptionTraitementValue receptionTraitementValue : bonReceptionValue.getListeTraitements()) {

			TraitementValue traitementValue = traitementPersistance
					.getTraitementParId(receptionTraitementValue.getTraitementId());

			ReceptionTraitementReportValue receptionTraitementReportValue = new ReceptionTraitementReportValue();
			receptionTraitementReportValue.setObservations(receptionTraitementValue.getObservations());

			if (traitementValue != null) {
				receptionTraitementReportValue.setTraitementObservation(traitementValue.getDesignation());
			}

			listeTraitements.add(receptionTraitementReportValue);
		}

		bonReceptionReportValue.setListeRouleauxEcru(listeRouleauxEcru);
		bonReceptionReportValue.setListeTraitements(listeTraitements);
	}

	@Override
	public BonSortieFinieReportListValue getListBonSortieReport(RechercheMulticritereBonSortieFiniValue request)
			throws IOException {

		BonSortieFinieReportListValue bonlivraisonReportList = new BonSortieFinieReportListValue();

		// enrechissement des param du report
		bonlivraisonReportList.setFileName(REPORTNAME_BONSORTIE_LIST);
		bonlivraisonReportList
				.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonSortieFiniList/bon_sorties_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logoSTIT.jpg");
		params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_BonSortieFiniList/bon_sorties_sub_report.jasper");

		bonlivraisonReportList.setParams(params);

		ResultatRechecheBonSortieFiniValue result = bonSortieFiniPersistance.rechercherMultiCritere(request);

		List<BonSortieFiniValue> bonSortieFiniList = new ArrayList<BonSortieFiniValue>(result.getList());

		// enrichissement du report
		enrichmentBonSortieFiniReportList(bonlivraisonReportList, bonSortieFiniList, request);

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		if (request.getPartieInt() != null) {
			Long clientId = request.getPartieInt();
			if (clientIdMap.containsKey(clientId)) {
				bonlivraisonReportList.setPartieInt(clientIdMap.get(clientId).getAbreviation());
			}
		}

		ArrayList<BonSortieFinieReportListValue> dataList = new ArrayList<BonSortieFinieReportListValue>();
		dataList.add(bonlivraisonReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		bonlivraisonReportList.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonlivraisonReportList;
	}

	private void enrichmentBonSortieFiniReportList(BonSortieFinieReportListValue bonlivraisonReportList,
			List<BonSortieFiniValue> bonSortieFiniList, RechercheMulticritereBonSortieFiniValue request) {

		List<BonSortieReportElementValue> productList = new ArrayList<BonSortieReportElementValue>();

		Map<Long, PartieInteresseValue> clientIdMap = gestionnaireLogistiqueCacheDomaine.mapClientById();

		for (BonSortieFiniValue livraisonVente : bonSortieFiniList) {
			BonSortieReportElementValue bonLivraisonSortieReportElement = new BonSortieReportElementValue();

			Long clientId = null;
			if (livraisonVente.getPartieInt() != null) {
				clientId = livraisonVente.getPartieInt();
				if (clientIdMap.containsKey(clientId)) {
					bonLivraisonSortieReportElement.setPartieIntDesignation(clientIdMap.get(clientId).getAbreviation());
				}
			}

			bonLivraisonSortieReportElement.setReference(livraisonVente.getReference());
			bonLivraisonSortieReportElement.setDateSortie(livraisonVente.getDateSortie());
			bonLivraisonSortieReportElement.setObservation(livraisonVente.getObservation());
			bonLivraisonSortieReportElement.setNbColis(livraisonVente.getNbColis());
			bonLivraisonSortieReportElement.setMetrageTotal(livraisonVente.getMetrageTotal());
			bonLivraisonSortieReportElement.setType(livraisonVente.getType());

			productList.add(bonLivraisonSortieReportElement);
		}

		bonlivraisonReportList.setProductList(productList);

		bonlivraisonReportList.setReference(request.getReference());
		bonlivraisonReportList.setDateSortieMin(request.getDateSortieMin());
		bonlivraisonReportList.setDateSortieMax(request.getDateSortieMax());
		bonlivraisonReportList.setType(request.getType());
		bonlivraisonReportList.setPartieIntId(request.getPartieInt());
		bonlivraisonReportList.setRempli(request.getRempli());

	}

	/********************* Bon inventaire ************************/

	@Override
	public BonInventaireReportValue getBonInventaireReportValue(Long id, String avecMise) throws IOException {

		BonInventaireReportValue bonSortieFinieReportValue = new BonInventaireReportValue();

		// enrechissement des param du report
		bonSortieFinieReportValue.setFileName(REPORTNAME_BONSORTIE);
		bonSortieFinieReportValue
				.setReportStream(new FileInputStream("C://ERP/Lib/STIT_BonInventaire/bonInventaire_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("p_PathLogo", "/report/logoSTIT.jpg");

		params.put("SUBREPORT_BONSORTIE_PATH", "C://ERP/Lib/STIT_BonInventaire/bonInventaire_sub_report.jasper");

		bonSortieFinieReportValue.setParams(params);

		BonInventaireFiniValue bonInventaireFiniValue = bonInventaireFiniPersistance.getBonInventaireFiniById(id);

		// enrichissement du REPORT VALUE & traitement
		bonSortieFinieReportValue.setDateSortie(bonInventaireFiniValue.getDateSortie());
		bonSortieFinieReportValue.setNbColis(bonInventaireFiniValue.getNbColis());
		bonSortieFinieReportValue.setReference(bonInventaireFiniValue.getReference());
		// bonSortieFinieReportValue.setType(bonInventaireFiniValue.g.getType());

		/** Grouper par produit id */
		Map<Long, List<RouleauFiniValue>> mapRouleau = new HashMap<Long, List<RouleauFiniValue>>();
		for (RouleauFiniValue rouleau : bonInventaireFiniValue.getListeRouleauFini()) {
			Long key = rouleau.getProduitId();
			if (mapRouleau.get(key) == null) {
				mapRouleau.put(key, new ArrayList<RouleauFiniValue>());
			}
			mapRouleau.get(key).add(rouleau);
		}

		Iterator it = mapRouleau.entrySet().iterator();
		List<RouleauFiniReportGroupedByProduitValue> listRouleauFiniReportGrouppedByProduit = new ArrayList<>();
		while (it.hasNext()) {
			// Création d'un nouveau Elément
			RouleauFiniReportGroupedByProduitValue element = new RouleauFiniReportGroupedByProduitValue();

			Map.Entry<Long, List<RouleauFiniValue>> pair = (Map.Entry<Long, List<RouleauFiniValue>>) it.next();
			/** Enrichissement d'un element */
			// avec mise decide si on affiche le rapport avec Mise ou Pas
			element.setAvecMise(avecMise);

			// Produit
			element.setProduitId(pair.getKey());

			if (element.getProduitId() != null) {
				ProduitValue produitValue = produitPersistance.rechercheProduitById(element.getProduitId());

				if (produitValue != null) {

					SousFamilleProduitValue sousFamilleProduitValue = null;

					if (produitValue.getSousFamilleId() != null) {
						sousFamilleProduitValue = sousFamilleProduitPersistance
								.rechercheSousFamilleProduitById(produitValue.getSousFamilleId());
					}

					if (sousFamilleProduitValue != null) {
						element.setType(sousFamilleProduitValue.getDesignation());
					}

					element.setRefTissu(produitValue.getReference());
					element.setComposition(produitValue.getComposition());
					element.setTissu(produitValue.getDesignation());
				}
			}

			Double totalMetrage = SOMME_ZERO;
			Double totalPoids = SOMME_ZERO;

			List<RouleauFiniReportValue> listRouleauFiniReportValue = new ArrayList<RouleauFiniReportValue>();

			for (RouleauFiniValue rouleauFiniValue : pair.getValue()) {
				RouleauFiniReportValue rouleauFiniReportValue = new RouleauFiniReportValue();

				if (rouleauFiniValue.getMetrage() != null) {
					totalMetrage = rouleauFiniValue.getMetrage().doubleValue() + totalMetrage;
				}
				if (rouleauFiniValue.getPoids() != null) {
					totalPoids = rouleauFiniValue.getPoids().doubleValue() + totalPoids;
				}

				element.setTotalMetrage(totalMetrage);
				element.setTotalPoids(totalPoids);

				if (rouleauFiniValue.getChoix() != null) {
					ChoixRouleauValue choixRouleau = choixRouleauDomaine
							.getChoixRouleauById(rouleauFiniValue.getChoix());
					if (choixRouleau != null) {
						rouleauFiniReportValue.setChoix(choixRouleau.getDesignation());
					}
				}
				rouleauFiniReportValue.setLaize(rouleauFiniValue.getLaize());
				rouleauFiniReportValue.setMetrage(rouleauFiniValue.getMetrage());
				rouleauFiniReportValue.setMise(rouleauFiniValue.getReferenceMise());
				rouleauFiniReportValue.setPoids(rouleauFiniValue.getPoids());
				rouleauFiniReportValue.setRefRouleau(rouleauFiniValue.getReference());
				// avec mise decide si on affiche le rapport avec Mise ou Pas
				rouleauFiniReportValue.setAvecMise(avecMise);
				listRouleauFiniReportValue.add(rouleauFiniReportValue);
			}

			element.setList(listRouleauFiniReportValue);

			listRouleauFiniReportGrouppedByProduit.add(element);
			it.remove();
		}

		// PartieInteresseValue partieInteresseValueTrouve = null;
		//
		// if(bonInventaireFiniValue.getPartieInt() != null){
		// PartieInteresseValue partieInteresseValue = new
		// PartieInteresseValue();
		// partieInteresseValue.setId(Long.valueOf(bonInventaireFiniValue.getPartieInt()
		// ));
		// partieInteresseValueTrouve =
		// partieInteresseePersistance.recherchePartieInteresseeParId(partieInteresseValue);
		// }
		//

		// if(partieInteresseValueTrouve !=null){
		// bonSortieFinieReportValue.setClient(partieInteresseValueTrouve.getAbreviation());
		// }else{
		// bonSortieFinieReportValue.setClient(null);
		// }

		bonSortieFinieReportValue.setListeRouleauFini(listRouleauFiniReportGrouppedByProduit);

		ArrayList<BonInventaireReportValue> dataBonSortieFinieReportList = new ArrayList<BonInventaireReportValue>();
		dataBonSortieFinieReportList.add(bonSortieFinieReportValue);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(
				dataBonSortieFinieReportList);
		bonSortieFinieReportValue.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return bonSortieFinieReportValue;

	}

	@Override
	public FicheColisReportValue genererListEtiquetteRouleauReport(RechercheMulticritereRouleauFiniValue request)
			throws IOException {
		FicheColisReportValue report = new FicheColisReportValue();

		report.setFileName("colis");
		
		
		report.setReportStream(new FileInputStream("C://ERP/Lib/STIT_EtiquetteRouleauFini/list/etiquette_rouleau_fini_list_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

	
		
		
		ResultatRechecheRouleauFiniValue result = rouleauFiniPersistance.rechercherMultiCritere(request);
		
		List<ColisValue> list = new ArrayList<ColisValue>();
			for (RouleauFiniValue rouleauFinivalue :result.getList()){
				
				
				
        	    ColisValue etiquetteRouleauFiniReportValue=new ColisValue();
        	    
        	    
        	    
        		etiquetteRouleauFiniReportValue.setProduitReference(rouleauFinivalue.getReference());
        		
        		etiquetteRouleauFiniReportValue.setPoidsNet(rouleauFinivalue.getMetrage());
        		
        		
        		
        		etiquetteRouleauFiniReportValue.setCouleurDesignation(rouleauFinivalue.getReferenceMise());
        		
        		


        		// traitement
        		PartieInteresseValue partieInteresseValueTrouve = null;
        		ProduitValue produitValue = null;

        
        			if (rouleauFinivalue.getProduitId() != null) {
        				PartieInteresseValue partieInteresseValue = new PartieInteresseValue();
        				partieInteresseValue.setId(rouleauFinivalue.getPartieInteresseeId());
        				partieInteresseValueTrouve = partieInteresseePersistance
        						.recherchePartieInteresseeParId(partieInteresseValue);
        				produitValue = produitPersistance.rechercheProduitById(rouleauFinivalue.getProduitId());
        			}
        		

        		if (partieInteresseValueTrouve != null) {
        			etiquetteRouleauFiniReportValue.setCarton(partieInteresseValueTrouve.getAbreviation());
        		}

        		if (produitValue != null) {
        			etiquetteRouleauFiniReportValue.setProduitDesignation(produitValue.getDesignation());
        			//etiquetteRouleauFiniReportValue.setComposition(produitValue.getComposition());
        			etiquetteRouleauFiniReportValue.setQuantiteDesignation(produitValue.getReference());
        		}

        		if (rouleauFinivalue.getChoix() != null) {
        			ChoixRouleauValue choixRouleau = choixRouleauDomaine.getChoixRouleauById(rouleauFinivalue.getChoix());
        			if (choixRouleau != null) {
        				etiquetteRouleauFiniReportValue.setChoix(choixRouleau.getDesignation());
        			}
        		}
        	    
        	    
        	            	    
        			    
        	 
        	
        	list.add(etiquetteRouleauFiniReportValue);
        }
		
		
		report.setColisList(list);    
		ArrayList<FicheColisReportValue> dataList = new ArrayList<FicheColisReportValue>();
		dataList.add(report);


		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public InventaireReportValue getInventaireByOFReportValue (
			CritereRechercheRouleauStandardValue critereRechercheRouleauStandard)  throws IOException {/** Instantiation du report */
		InventaireReportValue inventaireReport = new InventaireReportValue();
		   
		   // enrechissement des param du report
		   inventaireReport.setFileName(REPORTNAME_INVENTAIRE);
		   inventaireReport.setReportStream(new FileInputStream("C://ERP/Lib/STIT_InventaireByOF/inventaire_report.jrxml"));
		   
		   HashMap<String, Object> params = new HashMap<String, Object>();
		   /** Liste des paramètres : logo et critères de recherche*/
		   params.put("p_PathLogo", "C:\\ERP\\logos_clients\\logo_client.png");
		   params.put("SUBREPORT_INVENTAIRE_PATH", "C://ERP/Lib/STIT_InventaireByOF/inventaire_sub_report.jasper");
		   
		   inventaireReport.setParams(params);
		   
		   /** Rechercher les rouleaux selon les critères de recherche */
		   ResultatRechecheRouleauStandardValue resultatRechecheRouleauStandard = rouleauFiniDomaine.rechercherRouleauInventaireByOF(critereRechercheRouleauStandard);
		   //System.out.println("---- ** resultatRechecheRouleauStandard---"+resultatRechecheRouleauStandard);
			// enrichissement du report VALUE
		   if(critereRechercheRouleauStandard.getEntrepot() != null){
			   EntrepotValue entrepot = entrepotDomaine.getEntrepotById(critereRechercheRouleauStandard.getEntrepot());
			   if(entrepot != null){
				   inventaireReport.setEntrepot(entrepot.getDesignation());
			   }
		   }
		   
		  
		   
		    if(critereRechercheRouleauStandard.getClient() != null){
		    	PartieInteresseValue partieInteresseValue = new PartieInteresseValue();
		    	partieInteresseValue.setId(critereRechercheRouleauStandard.getClient());
		    	PartieInteresseValue partieInteresseValueTrouve = partieInteresseePersistance.recherchePartieInteresseeParId(partieInteresseValue);
			    if(partieInteresseValueTrouve != null){
			    	inventaireReport.setClient(partieInteresseValueTrouve.getAbreviation());
				}
		    }
		   
		    ProduitValue produitValue = null;
		    
		    List<ElementResultatRechecheRouleauStandardValue> elementsListToSet = new ArrayList<ElementResultatRechecheRouleauStandardValue>();
			   //System.out.println("---- ** resultatRechecheRouleauStandard.getListeElementResultatRechecheRouleauStandardValue()---"+resultatRechecheRouleauStandard.getListeElementResultatRechecheRouleauStandardValue());

		    List<ElementResultatRechecheRouleauStandardValue> elements = resultatRechecheRouleauStandard.getListeElementResultatRechecheRouleauStandardValue();
		    
		    
		    // Alimentation de la liste sub report: avec application des criteres de recherche sur Produit
		    for(ElementResultatRechecheRouleauStandardValue element : elements){
		    	boolean exist = true;
		      	if(element.getIdProduit() != null){
		      		
					produitValue = produitPersistance.rechercheProduitById(element.getIdProduit());

					if(produitValue != null){
						
						element.setDesignation(produitValue.getDesignation());
						element.setReferenceProduit(produitValue.getReference());
						element.setPrixUnitaire(produitValue.getPrixUnitaire());
						
						if(produitValue.getPrixUnitaire() != null && element.getMetrage()!= null){
							element.setPrixTotal(produitValue.getPrixUnitaire() * element.getMetrage());
						}
						
						//chang. de .contains() par .equals()
						if(estNonVide(critereRechercheRouleauStandard.getDesignationQuiContient() )){
							exist = (produitValue.getDesignation().toUpperCase().equals(
									critereRechercheRouleauStandard.getDesignationQuiContient().toUpperCase()));
						}
						
					}
		    	}
		      	
		      	// Application des criteres de recherche sur Produit
		      	if( 
		      		((critereRechercheRouleauStandard.getNombreColieA() != null && element.getNombreColis() <= critereRechercheRouleauStandard.getNombreColieA())
		      					||(critereRechercheRouleauStandard.getNombreColieA() == null))
		      		&&(((critereRechercheRouleauStandard.getNombreColieDu() != null && element.getNombreColis() >= critereRechercheRouleauStandard.getNombreColieDu())
		    	      			||(critereRechercheRouleauStandard.getNombreColieDu() == null)))
		      		&&(((critereRechercheRouleauStandard.getMetrageA() != null && element.getMetrage() <= critereRechercheRouleauStandard.getMetrageA())
		    	      			||(critereRechercheRouleauStandard.getMetrageA() == null)))
		      		&&(((critereRechercheRouleauStandard.getMetrageDu() != null && element.getMetrage() >= critereRechercheRouleauStandard.getMetrageDu())
		    	      			||(critereRechercheRouleauStandard.getMetrageDu() == null)))
		    	      			
		    	    &&(exist)
		      	){
		      		 if(estNonVide(critereRechercheRouleauStandard.getReferenceProduit())){
		       			if (critereRechercheRouleauStandard.getReferenceProduit().equals(element.getReferenceProduit())) {
		       				
		       				elementsListToSet.add(element);
		       				
		       			}
		       			else if (critereRechercheRouleauStandard.getDesignationQuiContient().equals(element.getDesignation())) {
		       				elementsListToSet.add(element);
						}
		      		 

		      	}else{
	   				elementsListToSet.add(element);
	  		 }
		      	
		    }
		    }
		    if(critereRechercheRouleauStandard.getOrderBy() != null){
		    	ElementResultatRechecheRouleauStandardComparator comparator = new ElementResultatRechecheRouleauStandardComparator();
		    	comparator.setOrderBy(critereRechercheRouleauStandard.getOrderBy());
		    	Collections.sort(elementsListToSet, comparator);
		    }
		    
		    
		   
		    
		    inventaireReport.setElementsList(elementsListToSet);
		    
		    inventaireReport.setEmplacement(critereRechercheRouleauStandard.getEmplacement());
		    inventaireReport.setMetrageA((critereRechercheRouleauStandard.getMetrageA()!= null) ?critereRechercheRouleauStandard.getMetrageA().toString():"");
		    inventaireReport.setMetrageDu((critereRechercheRouleauStandard.getMetrageDu() != null) ?critereRechercheRouleauStandard.getMetrageDu().toString():"");
		    inventaireReport.setNombreColieA((critereRechercheRouleauStandard.getNombreColieA()!=null)?critereRechercheRouleauStandard.getNombreColieA().toString():"");
		    inventaireReport.setNombreColieDu((critereRechercheRouleauStandard.getNombreColieDu() != null) ?critereRechercheRouleauStandard.getNombreColieDu().toString():"");
			
		    inventaireReport.setDateEtat(critereRechercheRouleauStandard.getDateEtat());
		    inventaireReport.setOrderBy(critereRechercheRouleauStandard.getOrderBy());
		    inventaireReport.setDesignationQuiContient(critereRechercheRouleauStandard.getDesignationQuiContient());
		    inventaireReport.setReferenceProduit(critereRechercheRouleauStandard.getReferenceProduit());

		   
			ArrayList<InventaireReportValue> dataInventaireReportList = new ArrayList<InventaireReportValue>();
			dataInventaireReportList.add(inventaireReport);
	  
			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataInventaireReportList);
			
			inventaireReport.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		   
		   return inventaireReport;
	}

}
