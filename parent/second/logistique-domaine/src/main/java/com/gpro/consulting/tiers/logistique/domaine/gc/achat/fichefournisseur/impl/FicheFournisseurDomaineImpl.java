package com.gpro.consulting.tiers.logistique.domaine.gc.achat.fichefournisseur.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommercialeReport;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.RechercheMulticritereFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.fichefournisseur.value.ResultatRechecheFicheFournisseurValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.DetailsReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.RechercheMulticritereReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ResultatRechecheReglementAchatCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.TypeReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ResultatRechecheReglementCompletValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientComparator;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.fichefournisseur.IFicheFournisseurDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.ficheclient.IFicheClientDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.inverse.IReglementInverseAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.inverse.IReglementInversePersistance;

/**
 * Implementation of {@link IFicheClientDomaine}
 * 
 * @author Wahid Gazzah
 * @since 17/08/2016
 *
 */

@Component
public class FicheFournisseurDomaineImpl implements IFicheFournisseurDomaine {

	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;

	@Autowired
	private IReceptionAchatPersistance bonReceptionPersistance;

	@Autowired
	private IReglementAchatPersistance reglementAchatPersistance;

	
	@Autowired
	private IReglementInverseAchatPersistance reglementInversePersistance;
	
//	@Autowired
	//private ISoldeFournisseurPersistance soldeClientPersistance;

	private final static String FACTURE_TYPE_AVOIRE = "avoir";
	private final static String SPACE = " ";

	@Override
	public ResultatRechecheFicheFournisseurValue rechercherMultiCritere(RechercheMulticritereFicheFournisseurValue request) {

		Double debitTotal = IConstanteCommercialeReport.ZEROD;
		Double creditTotal = IConstanteCommercialeReport.ZEROD;
		Double soldeClient = IConstanteCommercialeReport.ZEROD;
		Double soldeInitial = IConstanteCommercialeReport.ZEROD;

		ResultatRechecheFicheFournisseurValue result = new ResultatRechecheFicheFournisseurValue();
		List<FicheClientElementValue> listElements = new ArrayList<FicheClientElementValue>();

		if (request != null) {

			RechercheMulticritereFactureAchatValue requestFacture = new RechercheMulticritereFactureAchatValue();
			
			
		
			requestFacture.setPartieIntId(request.getClientId());			
			requestFacture.setDateFactureMax(request.getDateMax());
			requestFacture.setDateFactureMin(request.getDateMin());
			ResultatRechecheFactureAchatValue resultFacture = factureAchatPersistance.rechercherMultiCritere(requestFacture);

			RechercheMulticritereBonReceptionAchatValue requestBL = new RechercheMulticritereBonReceptionAchatValue();
			requestBL.setPartieInteresseId(request.getClientId().toString());
			//requestBL.setPartieIntId(request.getClientId());
			requestBL.setDateLivraisonDu(request.getDateMax());
			requestBL.setDateLivraisonA(request.getDateMax());
			//requestBL.setDateLivraisonMax(request.getDateMax());
			//requestBL.setDateLivraisonMin(request.getDateMin());
			
			requestBL.setFacture("non");
			
			//requestBL.setAvecFacture("nonFacture");
			ResultatRechecheBonReceptionAchatValue resultBL = bonReceptionPersistance.rechercherMultiCritere(requestBL);

			RechercheMulticritereReglementAchatValue requestReglement = new RechercheMulticritereReglementAchatValue();
			requestReglement.setPartieIntId(request.getClientId());
			requestReglement.setDateReglementMax(request.getDateMax());
			requestReglement.setDateReglementMin(request.getDateMin());
			ResultatRechecheReglementAchatCompletValue resultReglement = reglementAchatPersistance
					.rechercherMultiCritereComplet(requestReglement);

			if (request.getClientId() != null) {

				//TODO
				SoldeClientValue sc = null;
			//	SoldeClientValue sc = soldeClientPersistance.getByClientId(request.getClientId());

				if (sc != null) {

					if (sc.getSoldeInitial() != null && sc.getChiffreAffaireTmp() != null
							&& sc.getReglementTmp() != null) {

						soldeInitial = sc.getSoldeInitial() + sc.getChiffreAffaireTmp() - sc.getReglementTmp();

						result.setSoldeInitial(soldeInitial);
					}
				}
			}
			
			
			ResultatRechecheReglementAchatCompletValue resultReglementInverse = reglementInversePersistance
					.rechercherMultiCritereComplet(requestReglement);

			Double credit = IConstanteCommercialeReport.ZEROD;
			Double debit = IConstanteCommercialeReport.ZEROD;
			String libelle = "";

			Calendar dateIfNotExist = Calendar.getInstance();
			dateIfNotExist.set(1900, 0, 1); // 1er janvier 1900!

			for (FactureAchatValue facture : resultFacture.getList()) {

				FicheClientElementValue element = new FicheClientElementValue();

				element.setDate(facture.getDate());
				element.setReferenceComparator("F" + facture.getReference());

				libelle = "Facture N° " + facture.getReference();

				if (facture.getType() != null && facture.getType().equalsIgnoreCase(FACTURE_TYPE_AVOIRE)) {

					String refFactures=SPACE;
					if (facture.getInfoLivraison()!=null) 
					{
						refFactures=facture.getInfoLivraison();
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

			for (ReceptionAchatValue bl : resultBL.getListReceptionAchat()) {

				FicheClientElementValue element = new FicheClientElementValue();

				element.setCredit(credit);
				element.setDebit(bl.getMontantTTC());
				element.setDate(bl.getDateLivraison());
				element.setReferenceComparator("L" + bl.getReference());

				libelle = "BR N° " + bl.getReference();
				element.setLibelle(libelle);

				if (element.getDebit() != null) {

					debitTotal = debitTotal + element.getDebit();
				}

				if (bl.getDateLivraison() == null) {
					element.setDate(dateIfNotExist);
				}

				listElements.add(element);
			}

			for (ReglementAchatValue reglement : resultReglement.getList()) {

				FicheClientElementValue element = new FicheClientElementValue();

				element.setCredit(reglement.getMontantTotal());
				element.setDebit(debit);
				element.setDate(reglement.getDate());
				element.setReferenceComparator("R" + reglement.getReference());

				libelle = "Payement N° " + reglement.getReference();

				if (reglement.getListDetailsReglement() != null) {

					if (reglement.getListDetailsReglement().size() > 0) {

						DetailsReglementAchatValue detail = reglement.getListDetailsReglement().iterator().next();

						String type = SPACE;

						if (detail.getTypeReglementId() != null) {

							TypeReglementAchatValue typeReglement = reglementAchatPersistance
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
			
			
			
			
			
			
			for (ReglementAchatValue reglement : resultReglementInverse.getList()) {

				FicheClientElementValue element = new FicheClientElementValue();

				element.setCredit(reglement.getMontantTotal());
				element.setDebit(debit);
				element.setDate(reglement.getDate());
				element.setReferenceComparator("R" + reglement.getReference());

				libelle = "Payement Inv N° " + reglement.getReference();

				if (reglement.getListDetailsReglement() != null) {

					if (reglement.getListDetailsReglement().size() > 0) {

						DetailsReglementAchatValue detail = reglement.getListDetailsReglement().iterator().next();

						String type = SPACE;

						if (detail.getTypeReglementId() != null) {

							TypeReglementAchatValue typeReglement = reglementAchatPersistance
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

		
				
				if (element.getDebit() != null) {

					debitTotal = debitTotal + element.getDebit();
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

			Collections.sort(listElements, new FicheClientComparator());
			result.setListElements(listElements);
			result.setNombreResultaRechercher(listElements.size());
		}

		return result;
	}



}
