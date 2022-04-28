package com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommercialeReport;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.RechercheMulticritereFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.ficheclient.value.ResultatRechecheFicheClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.RechercheMulticritereFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.fichegroupe.value.ResultatRechecheFicheGroupeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.DetailsReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.RechercheMulticritereReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.TypeReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientComparator;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value.FicheGroupeClientComparator;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.fichegroupeclient.value.FicheGroupeClientElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient.IFicheClientDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IReglementInversePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.soldeClient.ISoldeClientPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;

/**
 * Implementation of {@link IFicheClientDomaine}
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */

@Component
public class FicheClientDomaineImpl implements IFicheClientDomaine {

	@Autowired
	private IFacturePersistance facturePersistance;

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;

	@Autowired
	private IReglementPersistance reglementPersistance;

	@Autowired
	private ISoldeClientPersistance soldeClientPersistance;
	
	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;
	
	@Autowired
	private IReglementInversePersistance reglementInversePersistance;

	private final static String FACTURE_TYPE_AVOIRE = "avoir";
	private final static String SPACE = " ";

	@Override
	public ResultatRechecheFicheClientValue rechercherMultiCritere(RechercheMulticritereFicheClientValue request) {

		Double debitTotal = IConstanteCommercialeReport.ZEROD;
		Double creditTotal = IConstanteCommercialeReport.ZEROD;
		Double soldeClient = IConstanteCommercialeReport.ZEROD;
		Double soldeInitial = IConstanteCommercialeReport.ZEROD;

		ResultatRechecheFicheClientValue result = new ResultatRechecheFicheClientValue();
		List<FicheClientElementValue> listElements = new ArrayList<FicheClientElementValue>();

		if (request != null) {

			RechercheMulticritereFactureValue requestFacture = new RechercheMulticritereFactureValue();
			
			
		
			requestFacture.setPartieIntId(request.getClientId());			
			requestFacture.setDateFactureMax(request.getDateMax());
			requestFacture.setDateFactureMin(request.getDateMin());
			
			requestFacture.setDeclarerecherche(request.getDeclarerRech());
			
			ResultatRechecheFactureValue resultFacture = facturePersistance.rechercherMultiCritere(requestFacture);
			
			
		



			RechercheMulticritereReglementValue requestReglement = new RechercheMulticritereReglementValue();
			requestReglement.setPartieIntId(request.getClientId());
			requestReglement.setDateReglementMax(request.getDateMax());
			
			requestReglement.setDeclarerRech(request.getDeclarerRech());
			
			
			//cas de date demarrage 
			
			BaseInfoValue baseInfo  = baseInfoPersistance.getClientActif() ; 
			
			if(baseInfo.getDateDemarrage() != null)
			{
				requestReglement.setDateReglementMin(baseInfo.getDateDemarrage());
			}
			
			else
				
			{
				requestReglement.setDateReglementMin(request.getDateMin());
			}
			
	
			
			
		
			ResultatRechecheReglementCompletValue resultReglement = reglementPersistance
					.rechercherMultiCritereComplet(requestReglement);

			if (request.getClientId() != null) {

				SoldeClientValue sc = soldeClientPersistance.getByClientId(request.getClientId());

				if (sc != null) {

					if (sc.getSoldeInitial() != null && sc.getChiffreAffaireTmp() != null
							&& sc.getReglementTmp() != null && sc.getSoldeInitialNonDeclaree() != null) {
						
						if (request.getDeclarerRech() != null) {
							

							if (request.getDeclarerRech().equals("")) {
								
								soldeInitial = sc.getSoldeInitial() +  sc.getSoldeInitialNonDeclaree() + sc.getChiffreAffaireTmp() - sc.getReglementTmp();
								

							} else if (request.getDeclarerRech().equals("oui")) {
								
								soldeInitial = sc.getSoldeInitial() + sc.getChiffreAffaireTmp() - sc.getReglementTmp();

							} else if (request.getDeclarerRech().equals("non")) {
								
								soldeInitial = sc.getSoldeInitialNonDeclaree() + sc.getChiffreAffaireTmp() - sc.getReglementTmp();

							}

						}else
						{
							soldeInitial = sc.getSoldeInitial() + sc.getChiffreAffaireTmp() - sc.getReglementTmp();
							
						}

					

						
						
						
						
						result.setSoldeInitial(soldeInitial);
					}
				}
			}
			
			
			//requestReglement.setDeclarerRech(null);
			ResultatRechecheReglementCompletValue resultReglementInverse = null ;
			
			
			//if(request.getDeclarerRech()  != null && (request.getDeclarerRech().equals("") ||  request.getDeclarerRech().equals("oui")  )) {
				
				resultReglementInverse = reglementInversePersistance.rechercherMultiCritereComplet(requestReglement);
			//}
			
					
			
	

			Double credit = IConstanteCommercialeReport.ZEROD;
			Double debit = IConstanteCommercialeReport.ZEROD;
			String libelle = "";

			Calendar dateIfNotExist = Calendar.getInstance();
			dateIfNotExist.set(1900, 0, 1); // 1er janvier 1900!

			for (FactureVenteValue facture : resultFacture.getList()) {

				FicheClientElementValue element = new FicheClientElementValue();
				
				
				if(facture.getType().equals("Normal"))
					element.setType(FicheClientElementValue.TYPE_FACTURE);
				else
					element.setType(FicheClientElementValue.TYPE_AVOIR);

				element.setDate(facture.getDate());
				element.setReferenceComparator("F" + facture.getReference());

				libelle = "Facture N° " + facture.getReference();

				if (facture.getType() != null && facture.getType().equalsIgnoreCase(FACTURE_TYPE_AVOIRE)) {

					libelle = "Avoir" + SPACE + facture.getReference() + SPACE + "Sur facture" + SPACE
							+ facture.getInfoLivraison();

					element.setCredit(facture.getMontantTTC());
					element.setDebit(debit);

				} else {

					element.setCredit(credit);
					element.setDebit(facture.getMontantTTC());
				}

				element.setLibelle(libelle);

				if (element.getDebit() != null) {

					debitTotal = debitTotal + element.getDebit();
				}
				
				if (element.getCredit() != null) {

					creditTotal = creditTotal + element.getCredit();
				}

				if (facture.getDate() == null) {
					element.setDate(dateIfNotExist);
				}

				listElements.add(element);
			}
			
			
	      if(request.getTypeRapport().equals("ficheClient") || request.getTypeRapport().equals("releveClient")){
				
				RechercheMulticritereBonLivraisonValue requestBL = new RechercheMulticritereBonLivraisonValue();
				requestBL.setPartieIntId(request.getClientId());
				requestBL.setDateLivraisonMax(request.getDateMax());
				requestBL.setDateLivraisonMin(request.getDateMin());
				requestBL.setAvecFacture("nonFacture");
				
				requestBL.setDeclare(request.getDeclarerRech());
				ResultatRechecheBonLivraisonValue resultBL = bonLivraisonPersistance.rechercherMultiCritere(requestBL);
				
				
				for (LivraisonVenteValue bl : resultBL.getList()) {

					FicheClientElementValue element = new FicheClientElementValue();
					
					
				
					element.setType(FicheClientElementValue.TYPE_BL);

					element.setCredit(credit);
					element.setDebit(bl.getMontantTTC());
					element.setDate(bl.getDate());
					element.setReferenceComparator("L" + bl.getReference());

					libelle = "BL N° " + bl.getReference();
					element.setLibelle(libelle);

					if (element.getDebit() != null) {

						debitTotal = debitTotal + element.getDebit();
					}

					if (bl.getDate() == null) {
						element.setDate(dateIfNotExist);
					}

					listElements.add(element);
				}
				
			}

		

			for (ReglementValue reglement : resultReglement.getList()) {

				

				if (reglement.getListDetailsReglement() != null) {

					if (reglement.getListDetailsReglement().size() > 0) {
						
						
						for(DetailsReglementValue detail : reglement.getListDetailsReglement()) {
							
							
							
							FicheClientElementValue element = new FicheClientElementValue();
							
							element.setType(FicheClientElementValue.TYPE_REGLEMENT);

							//element.setCredit(reglement.getMontantTotal());
							element.setCredit(detail.getMontant());
							
							element.setDebit(debit);
							element.setDate(reglement.getDate());
							element.setReferenceComparator("R" + reglement.getReference()  + " TR N° "+detail.getReference());

							//libelle = "Payement N° " + reglement.getReference();
							
							libelle = "Payement N° " + reglement.getReference() + " TR N° "+detail.getReference() + " ";
							
							
							
							
							//	DetailsReglementValue detail = reglement.getListDetailsReglement().iterator().next();

							String type = SPACE;

							if (detail.getTypeReglementId() != null) {

								TypeReglementValue typeReglement = reglementPersistance
										.getTypeReglementById(detail.getTypeReglementId());

								if (typeReglement != null) {

									type = typeReglement.getDesignation();
								}
							}
	                        String refFacture ="";
	                        if(detail.getRefFacture()!=null)
	                        	refFacture=detail.getRefFacture();
							libelle = libelle + detail.getBanque() + SPACE + type + SPACE + detail.getNumPiece() + SPACE
									+ refFacture;
							
							
							element.setLibelle(libelle);

							if (element.getCredit() != null) {

								creditTotal = creditTotal + element.getCredit();
							}

							if (reglement.getDate() == null) {
								element.setDate(dateIfNotExist);
							}

							listElements.add(element);
							
							
						}

				
					}
				}

		
			}
			
			
			
			
			if(resultReglementInverse != null) {
				for (ReglementValue reglement : resultReglementInverse.getList()) {

					

					if (reglement.getListDetailsReglement() != null) {

						if (reglement.getListDetailsReglement().size() > 0) {
							
							
							for(DetailsReglementValue detail : reglement.getListDetailsReglement()) {
								
								
								
								FicheClientElementValue element = new FicheClientElementValue();
								
								element.setType(FicheClientElementValue.TYPE_REGLEMENT_INVERSE);

								//element.setCredit(reglement.getMontantTotal());
								//element.setCredit(detail.getMontant());
								//element.setDebit(debit);
							
								
								element.setCredit(credit);
								element.setDebit(detail.getMontant());
								
								
								element.setDate(reglement.getDate());
								element.setReferenceComparator("RINV" + reglement.getReference()  + " TR N° "+detail.getReference());

								//libelle = "Payement N° " + reglement.getReference();
								
								libelle = "Payement Inv N° " + reglement.getReference() + " TR N° "+detail.getReference() + " ";
								
								
								
								
								//	DetailsReglementValue detail = reglement.getListDetailsReglement().iterator().next();

								String type = SPACE;

								if (detail.getTypeReglementId() != null) {

									TypeReglementValue typeReglement = reglementPersistance
											.getTypeReglementById(detail.getTypeReglementId());

									if (typeReglement != null) {

										type = typeReglement.getDesignation();
									}
								}
		                        String refFacture ="";
		                        if(detail.getRefFacture()!=null)
		                        	refFacture=detail.getRefFacture();
								libelle = libelle + detail.getBanque() + SPACE + type + SPACE + detail.getNumPiece() + SPACE
										+ refFacture;
								
								
								element.setLibelle(libelle);

							
								
								if (element.getDebit() != null) {

									debitTotal = debitTotal + element.getDebit();
								}

								if (reglement.getDate() == null) {
									element.setDate(dateIfNotExist);
								}

								listElements.add(element);
								
								
							}

					
						}
					}

			
				}
				
			}

		
			
			
			
			

			soldeClient = debitTotal - creditTotal + soldeInitial;

			//System.out.println("-------------------------debitTotal: " + debitTotal);
			//System.out.println("-------------------------creditTotal: " + creditTotal);
			//System.out.println("------------------FicheClientComparator-------soldeInitial: " + soldeInitial);
			//System.out.println("-------------------------soldeClient: " + soldeClient);

			result.setCreditTotal(creditTotal);
			result.setDebitTotal(debitTotal);
			result.setSoldeClient(soldeClient);

			Collections.sort(listElements, new FicheClientComparator());
			result.setListElements(listElements);
			result.setNombreResultaRechercher(listElements.size());
		}

		return result;
	}

	@Override
	public ResultatRechecheFicheGroupeClientValue rechercherMultiCritereGroupeClient(
			RechercheMulticritereFicheGroupeClientValue request) {
		Double debitTotal = IConstanteCommercialeReport.ZEROD;
		Double creditTotal = IConstanteCommercialeReport.ZEROD;
		Double soldeClient = IConstanteCommercialeReport.ZEROD;
		Double soldeInitial = IConstanteCommercialeReport.ZEROD;

		ResultatRechecheFicheGroupeClientValue result = new ResultatRechecheFicheGroupeClientValue();
		List<FicheGroupeClientElementValue> listElements = new ArrayList<FicheGroupeClientElementValue>();

		if (request != null) {

			RechercheMulticritereFactureValue requestFacture = new RechercheMulticritereFactureValue();
			
			
			
			requestFacture.setGroupeClientId(request.getGroupeClientId());		
			requestFacture.setDateFactureMax(request.getDateMax());
			requestFacture.setDateFactureMin(request.getDateMin());
			ResultatRechecheFactureValue resultFacture = facturePersistance.rechercherMultiCritere(requestFacture);

			RechercheMulticritereBonLivraisonValue requestBL = new RechercheMulticritereBonLivraisonValue();
			
			requestBL.setGroupeClientId(request.getGroupeClientId());
			requestBL.setDateLivraisonMax(request.getDateMax());
			requestBL.setDateLivraisonMin(request.getDateMin());
			requestBL.setAvecFacture("nonFacture");
			ResultatRechecheBonLivraisonValue resultBL = bonLivraisonPersistance.rechercherMultiCritere(requestBL);

			RechercheMulticritereReglementValue requestReglement = new RechercheMulticritereReglementValue();

			requestReglement.setGroupeClientId(request.getGroupeClientId());
			requestReglement.setDateReglementMax(request.getDateMax());
			requestReglement.setDateReglementMin(request.getDateMin());
			ResultatRechecheReglementCompletValue resultReglement = reglementPersistance
					.rechercherMultiCritereComplet(requestReglement);

		/*	if (request.getGroupeClientId() != null) {

				SoldeClientValue sc = soldeClientPersistance.getByClientId(request.getClientId());

				if (sc != null) {

					if (sc.getSoldeInitial() != null && sc.getChiffreAffaireTmp() != null
							&& sc.getReglementTmp() != null) {

						soldeInitial = sc.getSoldeInitial() + sc.getChiffreAffaireTmp() - sc.getReglementTmp();

						result.setSoldeInitial(soldeInitial);
					}
				}
			}*/

			Double credit = IConstanteCommercialeReport.ZEROD;
			Double debit = IConstanteCommercialeReport.ZEROD;
			String libelle = "";

			Calendar dateIfNotExist = Calendar.getInstance();
			dateIfNotExist.set(1900, 0, 1); // 1er janvier 1900!

			for (FactureVenteValue facture : resultFacture.getList()) {

				FicheGroupeClientElementValue element = new FicheGroupeClientElementValue();

				element.setDate(facture.getDate());
				element.setReferenceComparator("F" + facture.getReference());

				libelle = "Facture N° " + facture.getReference();

				if (facture.getType() != null && facture.getType().equalsIgnoreCase(FACTURE_TYPE_AVOIRE)) {

					String refFactures=SPACE;
					if (facture.getRefFactures()!=null) 
					{
						refFactures=facture.getRefFactures();
					}
						
					libelle = "Avoir" + SPACE + facture.getReference() + SPACE + "Sur facture" + SPACE
							+ refFactures;

					element.setCredit(facture.getMontantTTC());
					element.setDebit(debit);

				} else {

					element.setCredit(credit);
					element.setDebit(facture.getMontantTTC());
				}

				element.setLibelle(libelle);

				if (element.getDebit() != null) {

					debitTotal = debitTotal + element.getDebit();
				}

				if (element.getCredit() != null) {

					creditTotal = creditTotal + element.getCredit();
				}
				
				if (facture.getDate() == null) {
					element.setDate(dateIfNotExist);
				}

				listElements.add(element);
			}

			for (LivraisonVenteValue bl : resultBL.getList()) {

				FicheGroupeClientElementValue element = new FicheGroupeClientElementValue();

				element.setCredit(credit);
				element.setDebit(bl.getMontantTTC());
				element.setDate(bl.getDate());
				element.setReferenceComparator("L" + bl.getReference());

				libelle = "BL N° " + bl.getReference();
				element.setLibelle(libelle);

				if (element.getDebit() != null) {

					debitTotal = debitTotal + element.getDebit();
				}

				if (bl.getDate() == null) {
					element.setDate(dateIfNotExist);
				}

				listElements.add(element);
			}

			for (ReglementValue reglement : resultReglement.getList()) {

				FicheGroupeClientElementValue element = new FicheGroupeClientElementValue();

				element.setCredit(reglement.getMontantTotal());
				element.setDebit(debit);
				element.setDate(reglement.getDate());
				element.setReferenceComparator("R" + reglement.getReference());

				libelle = "Payement N° " + reglement.getReference();

				if (reglement.getListDetailsReglement() != null) {

					if (reglement.getListDetailsReglement().size() > 0) {

						DetailsReglementValue detail = reglement.getListDetailsReglement().iterator().next();

						String type = SPACE;

						if (detail.getTypeReglementId() != null) {

							TypeReglementValue typeReglement = reglementPersistance
									.getTypeReglementById(detail.getTypeReglementId());

							if (typeReglement != null) {

								type = typeReglement.getDesignation();
							}
						}
                        String refFacture ="";
                        if(detail.getRefFacture()!=null)
                        	refFacture=detail.getRefFacture();
						libelle = libelle + detail.getBanque() + SPACE + type + SPACE + detail.getNumPiece() + SPACE
								+ refFacture;
					}
				}

				element.setLibelle(libelle);

				if (element.getCredit() != null) {

					creditTotal = creditTotal + element.getCredit();
				}

				if (reglement.getDate() == null) {
					element.setDate(dateIfNotExist);
				}

				listElements.add(element);
			}

			soldeClient = debitTotal - creditTotal + soldeInitial;

			//System.out.println("-------------------------debitTotal: " + debitTotal);
			//System.out.println("-------------------------creditTotal: " + creditTotal);
			//System.out.println("------------------FicheClientComparator-------soldeInitial: " + soldeInitial);
			//System.out.println("-------------------------soldeClient: " + soldeClient);

			result.setCreditTotal(creditTotal);
			result.setDebitTotal(debitTotal);
			result.setSoldeClient(soldeClient);

			Collections.sort(listElements, new FicheGroupeClientComparator());
			result.setListElements(listElements);
			result.setNombreResultaRechercher(listElements.size());
		}

		return result;
	}

}
