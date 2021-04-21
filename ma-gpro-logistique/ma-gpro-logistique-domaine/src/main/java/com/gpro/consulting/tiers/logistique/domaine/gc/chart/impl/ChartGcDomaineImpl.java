package com.gpro.consulting.tiers.logistique.domaine.gc.chart.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.utils.value.DateUtilsValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.domaine.utils.IUtilsDomaine;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereDetLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RegelementReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.FactureReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.IFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reglement.IReglementAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.report.IGestionnaireReportAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.chart.IChartGcDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.impl.GuichetAnnuelDomaineImpl;
import com.gpro.consulting.tiers.logistique.domaine.gc.reglement.IDetailsReglementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.report.IGestionnaireReportGcDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.entity.DetailsReglementAchatEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.entity.DetailsReglementEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

/**
 * @author Samer
 *
 */
@Component
public class ChartGcDomaineImpl implements IChartGcDomaine {

	@Autowired
	private IGestionnaireReportGcDomaine gestionnaireReportGcDomaine;

	@Autowired
	IBonLivraisonPersistance bonLivraisonPersistance;

	@Autowired
	IFacturePersistance facturePersistance;

	@Autowired
	IUtilsDomaine utilsDomaine;

	@Autowired
	IGestionnaireReportAchatDomaine gestionnaireReportAchatDomaine;

	@Autowired
	com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.IProduitReceptionAchatDomaine produitReceptionAchatDomaine;

	@Autowired
	IFactureAchatDomaine factureAchatDomaine;

	@Autowired
	IReglementAchatDomaine reglementAchatDomaine;

	@Autowired
	IPartieInteresseeDomaine partieInteresseeDomaine;

	@Autowired
	IDetailsReglementDomaine detailsReglementDomaine;
	
	@Autowired
	IGuichetMensuelDomaine guichetMensuelDomaine;

	@Override
	public List<BLReportElementRecapValue> getChiffreAffaireBLbyMonth(RechercheMulticritereBonLivraisonValue request) {

		List<BLReportElementRecapValue> list = new ArrayList<>();

		List<DateUtilsValue> dateUtilsList = utilsDomaine.getDateDebAndMaxGroupByMonth(
				new DateUtilsValue(request.getDateIntroductionMin(), request.getDateIntroductionMax()));

		for (DateUtilsValue dateUtils : dateUtilsList) {

			request.setDateLivraisonMin(dateUtils.getDateMin());
			request.setDateLivraisonMax(dateUtils.getDateMax());

			BLReportElementRecapValue element = gestionnaireReportGcDomaine.getBLReportElementRecapValue(request);

			list.add(element);
		}

		return list;
	}

	@Override
	public List<FactureReportElementRecapValue> getChiffreAffaireFactureByMonth(
			RechercheMulticritereFactureValue request) {

		List<FactureReportElementRecapValue> list = new ArrayList<>();

		List<DateUtilsValue> dateUtilsList = utilsDomaine.getDateDebAndMaxGroupByMonth(
				new DateUtilsValue(request.getDateFactureMin(), request.getDateFactureMax()));

		for (DateUtilsValue dateUtils : dateUtilsList) {

			request.setDateFactureMin(dateUtils.getDateMin());
			request.setDateFactureMax(dateUtils.getDateMax());

			// request.setType("Normal");

			FactureReportElementRecapValue element = gestionnaireReportGcDomaine
					.getFactureReportElementRecapValue(request);
			
			
			GuichetMensuelValue guichet =  guichetMensuelDomaine.getByAnneeAndMois(element.getYear(),element.getMonth());
			
			if(guichet != null)
				element.setCibleCA(guichet.getCibleCA());
			

			list.add(element);
		}

		return list;
	}

	private boolean estNonVide(String val) {

		return val != null && !"".equals(val) && !"undefined".equals(val);
	}

	@Override
	public List<RegelementReportElementRecapValue> getReglementByMonth(RechercheMulticritereReglementValue request) {

		List<RegelementReportElementRecapValue> list = new ArrayList<>();

		List<DateUtilsValue> dateUtilsList = utilsDomaine.getDateDebAndMaxGroupByMonth(
				new DateUtilsValue(request.getDateReglementMin(), request.getDateReglementMax()));

		for (DateUtilsValue dateUtils : dateUtilsList) {

			request.setDateReglementMin(dateUtils.getDateMin());
			request.setDateReglementMax(dateUtils.getDateMax());

			// request.setType("Normal");

			RegelementReportElementRecapValue element = gestionnaireReportGcDomaine.getReglementReportValue(request);

			list.add(element);
		}

		return list;
	}

	@Override
	public List<ProduitValue> getTop10Article(RechercheMulticritereDetLivraisonValue request) {

		// List<ProduitValue> list = new ArrayList<>();

		// List<ProduitValue> result =
		// gestionnaireReportGcDomaine.getTop10ArticleReportValue(request);

		return gestionnaireReportGcDomaine.getTop10ArticleReportValue(request);

	}

	@Override
	public List<ResultBestElementValue> getTop10Client(RechercheMulticritereDetLivraisonValue request) {

		return gestionnaireReportGcDomaine.getTop10ClientReportValue(request);
	}

	@Override
	public List<ResultBestElementValue> getChiffreAffaireByClient(RechercheMulticritereDetLivraisonValue request) {

		return gestionnaireReportGcDomaine.getChiffreAffaireByClient(request);
	}

	@Override
	public List<ResultBestElementValue> getChiffreAffaireBySousFamille(RechercheMulticritereDetLivraisonValue request) {

		return gestionnaireReportGcDomaine.getChiffreAffaireBySousFamille(request);

	}

	@Override
	public List<ResultBestElementValue> getChiffreAffaireByFamille(RechercheMulticritereDetLivraisonValue request) {

		return gestionnaireReportGcDomaine.getChiffreAffaireByFamille(request);

	}

	@Override
	public List<BLReportElementRecapValue> getDepenseBRbyMonth(RechercheMulticritereBonReceptionAchatValue request) {

		List<BLReportElementRecapValue> list = new ArrayList<>();

		List<DateUtilsValue> dateUtilsList = utilsDomaine.getDateDebAndMaxGroupByMonth(
				new DateUtilsValue(request.getDateLivraisonDu(), request.getDateLivraisonA()));

		for (DateUtilsValue dateUtils : dateUtilsList) {

			request.setDateLivraisonDu(dateUtils.getDateMin());
			request.setDateLivraisonA(dateUtils.getDateMax());

			BLReportElementRecapValue element = gestionnaireReportAchatDomaine.getDepenseBRbyMonth(request);

			element.setDebut(dateUtils.getDateMin());

			element.setFin(dateUtils.getDateMax());

			element.setMois(dateUtils.getDateMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));

			list.add(element);
		}

		return list;

	}

	@Override
	public List<BLReportElementRecapValue> getDepenseFacturebyMonth(RechercheMulticritereFactureAchatValue request) {

		List<BLReportElementRecapValue> list = new ArrayList<>();

		List<DateUtilsValue> dateUtilsList = utilsDomaine.getDateDebAndMaxGroupByMonth(
				new DateUtilsValue(request.getDateFactureMin(), request.getDateFactureMax()));

		for (DateUtilsValue dateUtils : dateUtilsList) {

			request.setDateFactureMin(dateUtils.getDateMin());
			request.setDateFactureMax(dateUtils.getDateMax());

			BLReportElementRecapValue element = factureAchatDomaine.getDepenseFacturebyMonth(request);

			element.setDebut(dateUtils.getDateMin());

			element.setFin(dateUtils.getDateMax());

			element.setMois(dateUtils.getDateMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));

			list.add(element);
		}

		return list;

	}

	DetailsReglementAchatEntity aa;

	@Override
	public List<BLReportElementRecapValue> getReglementAchatByMonth(RechercheMulticritereReglementValue request) {

		List<BLReportElementRecapValue> list = new ArrayList<>();

		List<DateUtilsValue> dateUtilsList = utilsDomaine.getDateDebAndMaxGroupByMonth(
				new DateUtilsValue(request.getDateReglementMin(), request.getDateReglementMax()));

		for (DateUtilsValue dateUtils : dateUtilsList) {

			request.setDateReglementMin(dateUtils.getDateMin());
			request.setDateReglementMax(dateUtils.getDateMax());

			BLReportElementRecapValue element = reglementAchatDomaine.getReglementAchatbyMonth(request);

			element.setDebut(dateUtils.getDateMin());

			element.setFin(dateUtils.getDateMax());

			element.setMois(dateUtils.getDateMin().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));

			list.add(element);
		}

		return list;
	}

	@Override
	public List<ResultBestElementValue> getTop10ArticleAchat(RechercheMulticritereProduitReceptionAchatValue request) {

		return produitReceptionAchatDomaine.getTop10ArticleAchat(request);
	}

	@Override
	public List<ResultBestElementValue> getTop3Fournisseur(RechercheMulticritereProduitReceptionAchatValue request) {

		return produitReceptionAchatDomaine.getTop3Fournisseur(request);
	}

	@Override
	public List<ResultBestElementValue> getDepenseFournisseur(RechercheMulticritereProduitReceptionAchatValue request) {

		return produitReceptionAchatDomaine.getDepenseFournisseur(request);
	}

	@Override
	public Map<String, ResultBestElementValue> getEtiquettes(RechercheMulticritereDetLivraisonValue request) {

		Map<String, ResultBestElementValue> vResult = new HashMap<String, ResultBestElementValue>();

		/**** Meilleur client du mois actuel ******/
		List<ResultBestElementValue> top10Client = getTop10Client(request);

		if (top10Client.size() > 0) {
			ResultBestElementValue resTopClt=top10Client.get(0);
			resTopClt.setMois(request.getDateDe().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));
			vResult.put("topClientByMonth", resTopClt);
		}
			

		/********** Ca bl du mois Actuel ************/

		RechercheMulticritereBonLivraisonValue rmBonLiv = new RechercheMulticritereBonLivraisonValue();

		rmBonLiv.setBoutiqueId(request.getBoutiqueId());
		rmBonLiv.setDateIntroductionMin(request.getDateDe());
		rmBonLiv.setDateIntroductionMax(request.getDateA());

		List<BLReportElementRecapValue> caBlByMonth = getChiffreAffaireBLbyMonth(rmBonLiv);

		ResultBestElementValue resultCaBlByMonth = new ResultBestElementValue();
		
		if (caBlByMonth.size() > 0) {
			resultCaBlByMonth.setMontant(caBlByMonth.get(0).getMontantTTC());

			resultCaBlByMonth.setMois(caBlByMonth.get(0).getMois());
		}

		

		vResult.put("caBlByMonth", resultCaBlByMonth);

		/********** Ca Facture du mois Actuel ************/

		RechercheMulticritereFactureValue rmBonLivFact = new RechercheMulticritereFactureValue();

		rmBonLivFact.setBoutiqueId(request.getBoutiqueId());
		rmBonLivFact.setDateFactureMin(request.getDateDe());
		rmBonLivFact.setDateFactureMax(request.getDateA());

		List<FactureReportElementRecapValue> caFactureByMonth = getChiffreAffaireFactureByMonth(rmBonLivFact);

		ResultBestElementValue resultCaFactureByMonth = new ResultBestElementValue();

		if (caFactureByMonth.size() > 0) {
			resultCaFactureByMonth.setMontant(caFactureByMonth.get(0).getMontantTTC());

			resultCaFactureByMonth.setMois(caFactureByMonth.get(0).getMois());
		}
		

		vResult.put("CaFactureByMonth", resultCaFactureByMonth);

		/************* reglement du mois actuel ********************/

		RechercheMulticritereReglementValue rmReg = new RechercheMulticritereReglementValue();
		rmReg.setBoutiqueId(request.getBoutiqueId());
		rmReg.setDateReglementMin(request.getDateDe());
		rmReg.setDateReglementMax(request.getDateA());

		List<RegelementReportElementRecapValue> regByMonth = getReglementByMonth(rmReg);

		ResultBestElementValue resulReglementByMonth = new ResultBestElementValue();
		
		if (regByMonth.size() > 0) {
			resulReglementByMonth.setMontant(regByMonth.get(0).getMontantRegement());

			resulReglementByMonth.setMois(regByMonth.get(0).getMois());
		}

		

		vResult.put("ReglementByMonth", resulReglementByMonth);

		/************************ nbr client total ****************************/
		ResultBestElementValue nbClient = new ResultBestElementValue();

		nbClient.setNbTotal(partieInteresseeDomaine.nbPartieIntByFamille(1L, request.getBoutiqueId()));

		vResult.put("nbClient", nbClient);

		/************************ nbr Fournisseur total ****************************/
		ResultBestElementValue nbFournisseur = new ResultBestElementValue();

		nbFournisseur.setNbTotal(partieInteresseeDomaine.nbPartieIntByFamille(2L, request.getBoutiqueId()));

		vResult.put("nbFournisseur", nbFournisseur);

		/******************* Reglement Echus De jours ******************/

		RechercheMulticritereReglementValue rmRegValue = new RechercheMulticritereReglementValue();

		ResultBestElementValue regEchus = detailsReglementDomaine
				.rechercherMultiCritereReglementEchusDuJours(Calendar.getInstance(), false, request.getBoutiqueId());

		vResult.put("ReglementEchus", regEchus);

		return vResult;

	}
	
	@Override
	public ResultBestElementValue getEtiquetteMeilleurClient(RechercheMulticritereDetLivraisonValue request) {
		List<ResultBestElementValue> top10Client = getTop10Client(request);

		ResultBestElementValue resTopClt=new ResultBestElementValue();
		if (top10Client.size() > 0) {
			 resTopClt=top10Client.get(0);
			resTopClt.setMois(request.getDateDe().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE));
			
		}
		return resTopClt;
	}

	@Override
	public ResultBestElementValue getEtiquetteCAFacture(RechercheMulticritereDetLivraisonValue request) {
		RechercheMulticritereBonLivraisonValue rmBonLiv = new RechercheMulticritereBonLivraisonValue();

		rmBonLiv.setBoutiqueId(request.getBoutiqueId());
		rmBonLiv.setDateIntroductionMin(request.getDateDe());
		rmBonLiv.setDateIntroductionMax(request.getDateA());

		List<BLReportElementRecapValue> caBlByMonth = getChiffreAffaireBLbyMonth(rmBonLiv);

		ResultBestElementValue resultCaBlByMonth = new ResultBestElementValue();
		
		if (caBlByMonth.size() > 0) {
			resultCaBlByMonth.setMontant(caBlByMonth.get(0).getMontantTTC());

			resultCaBlByMonth.setMois(caBlByMonth.get(0).getMois());
		}

		

		return resultCaBlByMonth;
		
	}

	@Override
	public ResultBestElementValue getEtiquetteReglement(RechercheMulticritereDetLivraisonValue request) {
		RechercheMulticritereReglementValue rmReg = new RechercheMulticritereReglementValue();
 		rmReg.setDateReglementMin(request.getDateDe());
		rmReg.setDateReglementMax(request.getDateA());
		
		
		
		 
			return gestionnaireReportGcDomaine.getReglementReportValueOptimise(rmReg);

			
		  
		 
	}

	@Override
	public ResultBestElementValue getEtiquetteNbrClient(RechercheMulticritereDetLivraisonValue request) {
		ResultBestElementValue nbClient = new ResultBestElementValue();

		nbClient.setNbTotal(partieInteresseeDomaine.nbPartieIntByFamille(1L, request.getBoutiqueId()));

		return nbClient;
	}

	@Override
	public ResultBestElementValue getEtiquetteNbrFournisseur(RechercheMulticritereDetLivraisonValue request) {
		ResultBestElementValue nbFournisseur = new ResultBestElementValue();

		nbFournisseur.setNbTotal(partieInteresseeDomaine.nbPartieIntByFamille(2L, request.getBoutiqueId()));

		return nbFournisseur;
	}

	@Override
	public ResultBestElementValue getEtiquetteReglementEchus(RechercheMulticritereDetLivraisonValue request) {
		RechercheMulticritereReglementValue rmRegValue = new RechercheMulticritereReglementValue();

		ResultBestElementValue regEchus = detailsReglementDomaine
				.rechercherMultiCritereReglementEchusDuJours(Calendar.getInstance(), false, request.getBoutiqueId());

		return regEchus;
	}
	
	@Override
	public List<ResultBestElementValue> getChiffreAffairebyGroupe(RechercheMulticritereDetLivraisonValue request) {

		return gestionnaireReportGcDomaine.getChiffreAffairebyGroupe(request);
	}

	@Override
	public List<ResultBestElementValue> getTop10Groupe(RechercheMulticritereDetLivraisonValue request) {


		return gestionnaireReportGcDomaine.getTop10Groupe(request);
	}

	@Override
	public List<FactureReportElementRecapValue> getDifferenceChiffreAffaireVenteAchatByMonth(
			RechercheMulticritereFactureValue request) {
		
		RechercheMulticritereFactureAchatValue rechFactAchat = new RechercheMulticritereFactureAchatValue();
		rechFactAchat.setDateFactureMin((Calendar)request.getDateFactureMin().clone());
		rechFactAchat.setDateFactureMax((Calendar)request.getDateFactureMax().clone());
		rechFactAchat.setType(request.getType());
		
		
		List<FactureReportElementRecapValue> listChiffreAffaireFactureByMonth =  this.getChiffreAffaireFactureByMonth(request) ;
		
		List<BLReportElementRecapValue> listDepense = this.getDepenseFacturebyMonth(rechFactAchat) ;
		
		for(int i=0 ; i<listChiffreAffaireFactureByMonth.size() ; i++) {
			
			Double difMontantTTC  = 	listChiffreAffaireFactureByMonth.get(i).getMontantTTC() - listDepense.get(i).getMontantTTC();
			
			Double difMontantHT  = 	listChiffreAffaireFactureByMonth.get(i).getMontantHTaxe() - listDepense.get(i).getMontantHTaxe();
			
			listChiffreAffaireFactureByMonth.get(i).setMontantTTC(difMontantTTC);
			listChiffreAffaireFactureByMonth.get(i).setMontantHTaxe(difMontantHT);
		}
		
		return listChiffreAffaireFactureByMonth;
	}

}
