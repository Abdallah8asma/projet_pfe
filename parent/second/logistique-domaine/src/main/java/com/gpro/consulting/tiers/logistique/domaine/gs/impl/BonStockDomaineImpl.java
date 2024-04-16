package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteValidateComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteValidateFaconComparator;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonStockDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.utilities.DetBonStockComparator;
import com.gpro.consulting.tiers.logistique.domaine.gs.utilities.DetBonStockFaconComparator;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IBonStockPersistance;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 * Implementation of {@link IBonStockDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonStockDomaineImpl implements IBonStockDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonStockDomaineImpl.class);

	private static final String SEPARATOR = "-";

	private static final String SEPARATOR_NUMERO_SERIE = "&";

	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;
	
	
	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;
	
	@Autowired
	private IReceptionAchatPersistance receptionAchatPersistance;
	


	@Autowired
	private IBonStockPersistance bonStockPersistance;

	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private ITaxeLivraisonPersistance taxeLivraisonPersistance;

	@Autowired
	private IChoixRouleauPersistance choixRouleauPersistance;

	// @Autowired
	// private IproduitService produitService;
	//
	@Autowired
	private IDetFactureVentePersistance detFactureVentePersistance;

	@Autowired
	private IFacturePersistance facturePersistance;

	@Autowired
	private IMiseDomaine miseDomaine;

	@Autowired
	private IPrixClientDomaine prixClientDomaine;

	@Autowired
	private IGuichetMensuelDomaine guichetierMensuelDomaine;

	@Autowired
	private IFicheFaconDomaine ficheFaconDomaine;

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IProduitDepotPersistance produitDepotPersistance;

	@Autowired
	private IMarcheDomaine marcheDomaine;

	@Autowired
	private IProduitSerialisablePersistance produitSerialisablePersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	@Override
	public ResultatRechecheBonStockValue rechercherMultiCritere(RechercheMulticritereBonStockValue request) {

		return bonStockPersistance.rechercherMultiCritere(request);
	}



	@Override
	public BonStockValue getBonStockById(Long id) {

		BonStockValue bonStockValue = bonStockPersistance.getBonStockById(id);

		if (bonStockValue.getListDetBonStock() != null) {
		

				for (DetBonStockValue element : bonStockValue.getListDetBonStock()) {

					element.setQuantiteAncien(element.getQuantite());

					if (element.isSerialisable()) {

						
						element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),element.getProduitId()));

						/*
						 * RechercheMulticritereProduitSerialisableValue
						 * pRechercheProduitSerialisableMulitCritere = new
						 * RechercheMulticritereProduitSerialisableValue();
						 * pRechercheProduitSerialisableMulitCritere.setProduitId(element.getProduitId()
						 * ); pRechercheProduitSerialisableMulitCritere
						 * .setNumBonStock(livraisonVenteValue.getReference());
						 * 
						 * element.setProduitsSerialisable(produitSerialisablePersistance
						 * .rechercherProduitSerialisableMultiCritere(
						 * pRechercheProduitSerialisableMulitCritere) .getProduitSerialisableValues());
						 */

					}

				

			
			} 
				
				Collections.sort(bonStockValue.getListDetBonStock(), new DetBonStockComparator());
		}

		return bonStockValue;
	}

	private List<String> getNumerosSerieList(String numeroSeries) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateBonStock(BonStockValue bonLivraisonValue) {

		if (bonLivraisonValue.getGroupeClientId() == null && bonLivraisonValue.getPartieIntId() != null)
			bonLivraisonValue.setGroupeClientId(
					partieInteresseePersistance.getById(bonLivraisonValue.getPartieIntId()).getGroupeClientId());

		updateProduitSerialisableIfUpdateBL(bonLivraisonValue);

		updateStockAfterDeleteDetLivraisonVente(bonLivraisonValue);

		// System.out.println("==> bonLivraisonValue "+bonLivraisonValue);
		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;

		Double assietteFodec = ZERO;
		Double assietteTVA = ZERO;

		Double montantHTaxeTotal = ZERO;
		Double montantTaxesTotal = ZERO;

		Double montantRemiseTotal = ZERO;
		Double metrageTotal = ZERO;

		boolean modeRemiseEstTotal = false;

		if (bonLivraisonValue.getInfoSortie() != null && estNonVide(bonLivraisonValue.getInfoSortie())) {
			String infoSortieSplitted[] = bonLivraisonValue.getInfoSortie().split(SEPARATOR);

			String firstRefBonSortie = infoSortieSplitted[FIRST_INDEX];

			List<String> refBonSortieList = new ArrayList<String>();
			refBonSortieList.add(firstRefBonSortie);

			List<BonSortieFiniValue> bonSortieFiniList = bonSortieFiniPersistance
					.getListByBonSortieList(refBonSortieList);

			if (bonSortieFiniList != null) {
				if (bonSortieFiniList.size() > 0) {
					bonLivraisonValue.setPartieIntId(bonSortieFiniList.get(FIRST_INDEX).getPartieInt());
				}
			}
		}

		if (bonLivraisonValue.getTotalPourcentageRemise() != null
				&& bonLivraisonValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}
		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		// System.out.println("######
		// bonLivraisonValue.getListDetLivraisonVente() :
		// "+bonLivraisonValue.getListDetLivraisonVente());
		List<DetBonStockValue> vListDetails = new ArrayList<DetBonStockValue>();
		for (DetBonStockValue detLivraisonVente : bonLivraisonValue.getListDetBonStock()) {

			// Vérifier si la quantité reçu < quantité disponible
			if (detLivraisonVente.isErrorQte()) {
				detLivraisonVente.setQuantite(null);
			}

			if (detLivraisonVente.getQuantite() != null & detLivraisonVente.getPrixUnitaireHT() != null) {
				detLivraisonVente
						.setPrixTotalHT(detLivraisonVente.getQuantite() * detLivraisonVente.getPrixUnitaireHT());
			}

			/**************************
			 * TEST SUR LA VARIABLE STOCK
			 ***********************/
			// MAJ Qte Produit
			if (detLivraisonVente.getProduitId() != null) {

				ProduitValue produitValue = produitService.rechercheProduitById(detLivraisonVente.getProduitId());
				if (produitValue.isSerialisable())
					detLivraisonVente.setSerialisable(true);

				/** Debut update stock si qte a ete modifie **/
				if (detLivraisonVente.getId() != null && bonLivraisonValue.getStock() == true
						&& detLivraisonVente.getQuantite() != null && detLivraisonVente.getQuantiteAncien() != null
						&& !detLivraisonVente.getQuantite().equals(detLivraisonVente.getQuantiteAncien())) {

					Double qte = (produitValue.getQuantite() - detLivraisonVente.getQuantite()
							+ detLivraisonVente.getQuantiteAncien());
					produitValue.setQuantite(qte);
					produitService.modifierProduit(produitValue);
					// MAJ des DEPOTS

					if (bonLivraisonValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonLivraisonValue.getIdDepot());
						request.setIdProduit(detLivraisonVente.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() - detLivraisonVente.getQuantite()
									+ detLivraisonVente.getQuantiteAncien());
							produitDepotPersistance.modifier(vProduitDepot);
						}
					}
				}
				/** Fin update stock si qte a ete modifie **/

				/** Debut update stock si un produit a ete ajoute **/
				if (detLivraisonVente.getId() == null && bonLivraisonValue.getStock() == true
						&& detLivraisonVente.getQuantite() != null) {

					Double qte = (produitValue.getQuantite() - detLivraisonVente.getQuantite());
					produitValue.setQuantite(qte);
					produitService.modifierProduit(produitValue);
					// MAJ des DEPOTS

					if (bonLivraisonValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonLivraisonValue.getIdDepot());
						request.setIdProduit(detLivraisonVente.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() - detLivraisonVente.getQuantite());
							produitDepotPersistance.modifier(vProduitDepot);
						}
					}
				}
				/** Fin update stock si un prod a ete ajoute **/

				/************************
				 * conversion des prix et quantites cas conversion=true
				 **************/
				/************************
				 * Appliqué uniquement pour les nouveaux Articles
				 **************/

				if (detLivraisonVente.getNouveau() != null && detLivraisonVente.getNouveau() == true) {
					if (detLivraisonVente.getConversion() != null && detLivraisonVente.getConversion() == true) {

						detLivraisonVente.setPrixUnitaireHTConversion(detLivraisonVente.getPrixUnitaireHT());
						detLivraisonVente.setPrixUnitaireHT(produitValue.getPrixUnitaire());
						detLivraisonVente.setQuantiteConversion(detLivraisonVente.getQuantite());
						detLivraisonVente.setUniteSupplementaire(produitValue.getUniteSupplementaire());
						if (produitValue.getPrixUnitaire() != null && produitValue.getPrixUnitaire() != ZERO) {

							Double vQuantite = detLivraisonVente.getPrixTotalHT() / produitValue.getPrixUnitaire();

							detLivraisonVente.setQuantite(vQuantite);

						}

					}
				}

				if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
					produitTaxeMap.put(produitValue.getIdTaxe(), detLivraisonVente.getPrixTotalHT());

				} else {
					Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
							+ detLivraisonVente.getPrixTotalHT();
					produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

				}

				if (detLivraisonVente.getNouveau() != null && detLivraisonVente.getNouveau() == true)

				{
					if ((produitValue.getFondSupplementaire() != null && produitValue.getFondSupplementaire() == true)
							|| (produitValue.getFond() != null && produitValue.getFond() == true)) {

						Long pIdProduit = ZERO_LONG;

						if (produitValue.getFondSupplementaire() == true)
							pIdProduit = 5000L;
						else
							pIdProduit = 5001L;
						ProduitValue vProduitValue = produitService.rechercheProduitById(pIdProduit);

						DetBonStockValue vDetails = new DetBonStockValue();
						vDetails.setProduitId(pIdProduit);
						vDetails.setQuantite(detLivraisonVente.getQuantite());
						vDetails.setPrixUnitaireHT(vProduitValue.getPrixUnitaire());
						vDetails.setPrixTotalHT(detLivraisonVente.getQuantite() * vProduitValue.getPrixUnitaire());
						vDetails.setUnite(vProduitValue.getUnite());
						vListDetails.add(vDetails);

						if (vDetails.getPrixTotalHT() != null) {
							montantHTaxeTotal = montantHTaxeTotal + vDetails.getPrixTotalHT();
						}

					}
				}

				if (detLivraisonVente.getPrixUnitaireHT() != null) {
					Double prixTotal = detLivraisonVente.getQuantite() * detLivraisonVente.getPrixUnitaireHT();
					detLivraisonVente.setPrixTotalHT(convertisseur(prixTotal, 4));
				}

			}
			/////////////////////////////////////

			if (detLivraisonVente.getPrixTotalHT() != null) {
				montantHTaxeTotal += detLivraisonVente.getPrixTotalHT();
			}
			if (detLivraisonVente.getQuantite() != null && bonLivraisonValue.getNatureLivraison() != null && !bonLivraisonValue.getNatureLivraison().equals("FACON")) {
				metrageTotal = metrageTotal + detLivraisonVente.getQuantite();
			}

			if (!modeRemiseEstTotal) {
				if (detLivraisonVente.getRemise() != null && detLivraisonVente.getPrixTotalHT() != null) {
					montantRemiseTotal += (detLivraisonVente.getPrixTotalHT() * detLivraisonVente.getRemise()) / 100;
				}
			}
			if (detLivraisonVente.getChoix() != null) {

				Long choix = null;
				try {
					choix = Long.parseLong(detLivraisonVente.getChoix());
				} catch (NumberFormatException e) {
					// logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detLivraisonVente.setChoix(choixRouleau.getDesignation());
				}
			}
			vListDetails.add(detLivraisonVente);
		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonLivraisonValue.getTotalPourcentageRemise()) / 100;
		}



		Double montantTTC = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);
		bonLivraisonValue.setMetrageTotal(metrageTotal);

		/***
		 * Si Client .type exonoré Alors TVA=0 au niveau de Facture et Bon de livraison
		 ***/
		// PartieInteresseValue pi =
		// partieInteresseePersistance.getPartieInteresseById(bonLivraisonValue.getPartieIntId());

		if (bonLivraisonValue.getTypePartieInteressee() != null
				&& bonLivraisonValue.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {

			bonLivraisonValue.setMontantTTC(montantHTaxeTotal);

		} else {
			bonLivraisonValue.setMontantTTC(montantTTC);
		}

		return bonStockPersistance.updateBonStock(bonLivraisonValue);
	}

	private void updateStockAfterDeleteDetLivraisonVente(BonStockValue bonLivraisonValue) {

		if (
				bonLivraisonValue.getListSuppDetBonStock() != null
				&& bonLivraisonValue.getListSuppDetBonStock().size() > 0) {

			for (DetBonStockValue detLivraisonVente : bonLivraisonValue.getListSuppDetBonStock()) {

				if (detLivraisonVente.getId() != null && detLivraisonVente.getProduitId() != null
						&& detLivraisonVente.getQuantite() != null) {

					ProduitValue produitValue = produitService.rechercheProduitById(detLivraisonVente.getProduitId());

					Double qte = (produitValue.getQuantite() + detLivraisonVente.getQuantite());
					produitValue.setQuantite(qte);
					produitService.modifierProduit(produitValue);
					// MAJ des DEPOTS

					if (bonLivraisonValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonLivraisonValue.getIdDepot());
						request.setIdProduit(detLivraisonVente.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() + detLivraisonVente.getQuantite());
							produitDepotPersistance.modifier(vProduitDepot);
						}
					}
				}

			}

		}

	}

	private void updateProduitSerialisable(Long id) {
	/*	BonStockValue livraisonVente = bonStockPersistance.getBonStockById(id);

		for (DetBonStockValue detailLivraisonVente : livraisonVente.getListDetBonStock()) {

			if (detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {

					prodSerialisable.setClientId(null);
					prodSerialisable.setNumBonStock(null);
					prodSerialisable.setDateVente(null);

					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);

				}

			}
		}
*/
	}

	private void updateProduitSerialisable(BonStockValue lv) {
		/*BonStockValue livraisonVente = lv;

		for (DetBonStockValue detailLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

			if (detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {

					prodSerialisable.setClientId(null);
					prodSerialisable.setNumBonStock(null);
					prodSerialisable.setDateVente(null);

					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);

				}

			}
		}*/

	}

	private void updateProduitSerialisableIfUpdateBL(BonStockValue lv) {
		
		/*
		BonStockValue livraisonVente = lv;

		for (DetBonStockValue detailLivraisonVente : livraisonVente.getListDetBonStock()) {

			if (detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {

				String numeroSeries = "";

				for (ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {

					//** Si un nouveau produit serialisable a été ajouté 

					if (prodSerialisable.getNumBonStock() == null) {

						prodSerialisable.setClientId(lv.getPartieIntId());
						prodSerialisable.setNumBonStock(lv.getReference());
						prodSerialisable.setDateVente(lv.getDate());

					}

					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);

					numeroSeries += prodSerialisable.getNumSerie();
					numeroSeries += "&";

				}

				if (numeroSeries != null && numeroSeries.length() > 0
						&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
					numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
					detailLivraisonVente.setNumeroSeries(numeroSeries);
				}

			}
		}*/

	}

	@Override
	public void deleteBonStock(Long id) {

		BonStockValue livraisonVente = this.getBonStockById(id);

		updateProduitSerialisable(livraisonVente);

		updateStockAfterDeleteBonStock(livraisonVente);

		bonStockPersistance.deleteBonStock(id);
	}

	private void updateStockAfterDeleteBonStock(BonStockValue lv) {
		BonStockValue bonLivraisonValue = lv;

		for (DetBonStockValue detLivraisonVente : bonLivraisonValue.getListDetBonStock()) {

			if (detLivraisonVente.getProduitId() != null && detLivraisonVente.getQuantite() != null) {

				ProduitValue produitValue = produitService.rechercheProduitById(detLivraisonVente.getProduitId());

				if (produitValue != null) {
					Double qte = (produitValue.getQuantite() + detLivraisonVente.getQuantite());
					produitValue.setQuantite(qte);
					produitService.modifierProduit(produitValue);

					if (bonLivraisonValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonLivraisonValue.getIdDepot());
						request.setIdProduit(detLivraisonVente.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() + detLivraisonVente.getQuantite());

							produitDepotPersistance.modifier(vProduitDepot);
						}
					}

				}

			}

		}

	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonStockList, Long factureVenteId) {

		List<BonStockValue> listBonStockValue = bonStockPersistance
				.getProduitElementList(refBonStockList);

		List<DetBonStockValue> listDetLivraisonVente = new ArrayList<DetBonStockValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listBonStockValue.size() > 0) {
			listProduitElementValue.setPartieIntId(listBonStockValue.get(FIRST_INDEX).getPartieIntId());
			listProduitElementValue.setDateLivrison(listBonStockValue.get(FIRST_INDEX).getDate());
			// Added By Ghazi on 25/05/2018
			listProduitElementValue.setIdMarche(listBonStockValue.get(FIRST_INDEX).getMarcheId());

		}

		for (BonStockValue livraisonVente : listBonStockValue) {

			for (DetBonStockValue detLivraisonVente : livraisonVente.getListDetBonStock()) {

				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<Long, String>, List<DetBonStockValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<DetBonStockValue>>();

		for (DetBonStockValue detail : listDetLivraisonVente) {
			Long produitKey = detail.getProduitId();
			String choixKey = detail.getChoix();

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<DetBonStockValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ProduitValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<DetBonStockValue>> pair = (Map.Entry<Map<Long, String>, List<DetBonStockValue>>) it
					.next();

			DetFactureVenteValue element = new DetFactureVenteValue();

			Long produitId = null;
			String choix = null;

			Map<Long, String> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<Long, String> produitIdchoixPair = (Map.Entry<Long, String>) produitIdchoixIt.next();
			produitId = produitIdchoixPair.getKey();
			choix = produitIdchoixPair.getValue();

			element.setProduitId(produitId);
			element.setChoix(choix);
			element.setFactureVenteId(factureVenteId);
			// el
			Double sommeQuantite = ZERO;
			Long sommeNombreColis = ZERO_LONG;

			String numeroSeries = "";

			for (DetBonStockValue detail : pair.getValue()) {

				if (detail.getQuantite() != null) {
					sommeQuantite = detail.getQuantite() + sommeQuantite;
				}

				if (detail.getNombreColis() != null) {
					sommeNombreColis = detail.getNombreColis() + sommeNombreColis;
				}

				if (detail.isSerialisable()) {
					numeroSeries += detail.getNumeroSeries();
					numeroSeries += "&";
				}

				// TODO ENRICHIR WITH INFO PROD SERIALISABLE
				// TODO REMPLIR LIST PRODSERIALISABLE

			}

			if (numeroSeries.length() > 0 && numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
				numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
				element.setNumeroSeries(numeroSeries);
				element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(numeroSeries,element.getProduitId()));
			}

			// TODO ENRICHIR WITH INFO PROD SERIALISABLE

			element.setQuantite(sommeQuantite);
			element.setNombreColis(sommeNombreColis);

			if (pair.getValue().size() > 0) {

				DetBonStockValue detail = pair.getValue().get(0);
				if (detail != null) {
					element.setUnite(detail.getUnite());
					element.setRemise(detail.getRemise());
					// TODO changed
					element.setPrixUnitaireHT(detail.getPrixUnitaireHT());
				}
			}

			if (element.getProduitId() != null) {
				produitValue = produitService.rechercheProduitById(element.getProduitId());
				if (produitValue != null) {

					element.setSerialisable(produitValue.isSerialisable());
					if (factureVenteId != null) {

						DetFactureVenteValue detFactureVenteValue = detFactureVentePersistance
								.getByFactureVenteAndProduit(factureVenteId, element.getProduitId(),
										element.getChoix());

						if (detFactureVenteValue != null) {

							element.setDescription(detFactureVenteValue.getDescription());

							boolean detailIdNotExist = true;
							for (DetBonStockValue detail : listDetLivraisonVente) {
								if (detail.getId() == detFactureVenteValue.getId())
									detailIdNotExist = false;
							}

							if (detailIdNotExist) {
								element.setId(detFactureVenteValue.getId());
								element.setRemise(detFactureVenteValue.getRemise());
							}

						}
					}

					element.setProduitDesignation(produitValue.getDesignation());
					element.setProduitReference(produitValue.getReference());

					/*** appel fonction rechercheMC prix special *****/

					/******/
					// TO O DO A changer
					// Commented

					if (element.getPrixUnitaireHT() == null && produitValue.getPrixUnitaire() != null)
						element.setPrixUnitaireHT(produitValue.getPrixUnitaire());

					if (element.getPrixUnitaireHT() != null && element.getQuantite() != null) {
						element.setPrixTotalHT(element.getPrixUnitaireHT() * element.getQuantite());
					}

				}
			}

			element.setNombreColis(Long.valueOf(pair.getValue().size()));

			listDetFactureVente.add(element);

			it.remove();

		}

		if (listDetFactureVente.size() > 0) {
			Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparator());
		}

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetFactureVente(listDetFactureVente);

		return listProduitElementValue;
	}

	private Set<ProduitSerialisableValue> getListProduitSerialisableParNumerSeries(String numeroSeries,Long produitId) {
		
		if(numeroSeries == null) return new HashSet<ProduitSerialisableValue>();
		
		String numero[] = numeroSeries.split(SEPARATOR_NUMERO_SERIE);

		List<String> listNumeroSeries = new ArrayList<>();

		Collections.addAll(listNumeroSeries, numero);

		return produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,produitId);
	}

	@Override
	public List<String> getListBonStockRef() {

     return null;
	}



	@Override
	public BonStockValue getByReference(String reference) {

		return bonStockPersistance.getByReference(reference);
	}







	@Override
	public ListTraitFaconElementValue getTraitementFaconElementList(List<String> refBonStockList,
			Long factureVenteId) {

		List<BonStockValue> listBonStockValue = bonStockPersistance
				.getTraitementFaconElementList(refBonStockList);

		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();

		ListTraitFaconElementValue listTraitFaconElementValue = new ListTraitFaconElementValue();

		if (listBonStockValue.size() > 0) {
			listTraitFaconElementValue.setPartieIntId(listBonStockValue.get(FIRST_INDEX).getPartieIntId());
			listTraitFaconElementValue.setDateLivraison(listBonStockValue.get(FIRST_INDEX).getDate());
		}

		for (BonStockValue livraisonVente : listBonStockValue) {
			if (livraisonVente.getListDetBonStock() != null) {
				for (DetBonStockValue detLivraisonVente : livraisonVente.getListDetBonStock()) {

					DetFactureVenteValue detFactureVenteValue = new DetFactureVenteValue();

					detFactureVenteValue.setNombreColis(detLivraisonVente.getNombreColis());
					detFactureVenteValue.setPrixTotalHT(detLivraisonVente.getPrixTotalHT());
					detFactureVenteValue.setPrixUnitaireHT(detLivraisonVente.getPrixUnitaireHT());
					detFactureVenteValue.setQuantite(detLivraisonVente.getQuantite());
					detFactureVenteValue.setRemise(detLivraisonVente.getRemise());
					detFactureVenteValue.setTraitementFaconId(detLivraisonVente.getTraitementFaconId());
					detFactureVenteValue.setUnite(detLivraisonVente.getUnite());
					detFactureVenteValue.setFicheId(detLivraisonVente.getFicheId());

					FicheFaconValue ficheFacon = ficheFaconDomaine.getById(detFactureVenteValue.getFicheId());
					detFactureVenteValue
							.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));

					listDetFactureVente.add(detFactureVenteValue);
				}
			}

		}

		if (listDetFactureVente.size() > 0) {
			Collections.sort(listDetFactureVente, new DetFactureVenteValidateFaconComparator());
		}

		listTraitFaconElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listTraitFaconElementValue.setListDetFactureVente(listDetFactureVente);

		return listTraitFaconElementValue;
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	// @Override
	// public String createBonStock(BonStockValue bonLivraisonValue) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonStock(BonStockValue bonLivraisonValue) {

		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;

		Double assietteFodec = ZERO;
		Double assietteTVA = ZERO;

		Double montantHTaxeTotal = ZERO;
		Double montantTaxesTotal = ZERO;
		Double montantRemiseTotal = ZERO;
		Double metrageTotal = ZERO;

		boolean modeRemiseEstTotal = false;
		// System.out.println("stock etat: "+bonLivraisonValue.getStock());

		if (bonLivraisonValue.getReference() == null || bonLivraisonValue.getReference().equals("")) {

			bonLivraisonValue.setReference(getCurrentReference(Calendar.getInstance(), true));

			// bonLivraisonValue.setReference(getNumeroBonStockFromGuichetAnnuel(Calendar.getInstance()));

			// bonLivraisonValue.setReference(getNumeroBonStockFromGuichetMensuel(Calendar.getInstance()));
			// bonLivraisonValue.getReference().concat(getNumeroBonStock(Calendar.getInstance()));
		} else if (bonLivraisonValue.getRefAvantChangement() != null
				&& bonLivraisonValue.getReference().equals(bonLivraisonValue.getRefAvantChangement())) {
			this.getCurrentReference(bonLivraisonValue.getDate(), true);
		}

		// Forcer la date

		if (bonLivraisonValue.getDate() == null)
			bonLivraisonValue.setDate(Calendar.getInstance());

		if (bonLivraisonValue.getGroupeClientId() == null && bonLivraisonValue.getPartieIntId() != null)
			bonLivraisonValue.setGroupeClientId(
					partieInteresseePersistance.getById(bonLivraisonValue.getPartieIntId()).getGroupeClientId());

		if (bonLivraisonValue.getInfoSortie() != null && estNonVide(bonLivraisonValue.getInfoSortie())) {
			String infoSortieSplitted[] = bonLivraisonValue.getInfoSortie().split(SEPARATOR);

			String firstRefBonSortie = infoSortieSplitted[FIRST_INDEX];

			List<String> refBonSortieList = new ArrayList<String>();
			refBonSortieList.add(firstRefBonSortie);

			List<BonSortieFiniValue> bonSortieFiniList = bonSortieFiniPersistance
					.getListByBonSortieList(refBonSortieList);

			if (bonSortieFiniList != null) {
				if (bonSortieFiniList.size() > 0) {
					bonLivraisonValue.setPartieIntId(bonSortieFiniList.get(FIRST_INDEX).getPartieInt());
				}
			}
		}

		if (bonLivraisonValue.getTotalPourcentageRemise() != null
				&& bonLivraisonValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		// Added By Ghazi on 23/05/2018
		List<DetBonStockValue> vListDetails = new ArrayList<DetBonStockValue>();

		for (DetBonStockValue detLivraisonVente : bonLivraisonValue.getListDetBonStock()) {
			// Sortir du Stock Negatif
			// Vérifier si la quantité reçu < quantité disponible
			// if(detLivraisonVente.isErrorQte()){
			// detLivraisonVente.setQuantite(null);
			// }
			// conversion des Doubles en 3 chiffres aprés virgule

			if (detLivraisonVente.getQuantite() != null)
				detLivraisonVente.setQuantite(convertisseur(detLivraisonVente.getQuantite(), 4));

			if (detLivraisonVente.getQuantiteConversion() != null)
				detLivraisonVente.setQuantiteConversion(convertisseur(detLivraisonVente.getQuantite(), 4));

			if (detLivraisonVente.getPrixUnitaireHT() != null) {
				detLivraisonVente.setPrixUnitaireHT(convertisseur(detLivraisonVente.getPrixUnitaireHT(), 4));
			}
			if (detLivraisonVente.getPrixUnitaireHTConversion() != null) {
				detLivraisonVente.setPrixUnitaireHT(convertisseur(detLivraisonVente.getPrixUnitaireHTConversion(), 4));
			}

			// fin des conversion

			if (detLivraisonVente.getQuantite() != null) {

				if (detLivraisonVente.getPrixUnitaireHT() != null) {
					Double prixTotal = detLivraisonVente.getQuantite() * detLivraisonVente.getPrixUnitaireHT();
					detLivraisonVente.setPrixTotalHT(convertisseur(prixTotal, 4));
				}
				if (detLivraisonVente.getUnite() != null) {
					detLivraisonVente.setUnite(detLivraisonVente.getUnite());
				}

				/************************
				 * TEST SUR LA VARIABLE STOCK
				 **************/
				// MAJ Qte Produit
				if (detLivraisonVente.getProduitId() != null) {

					ProduitValue produitValue = produitService.rechercheProduitById(detLivraisonVente.getProduitId());
					if (produitValue.isSerialisable())
						detLivraisonVente.setSerialisable(true);

					/************************
					 * conversion des prix et quantites cas conversion=true
					 **************/

					if (detLivraisonVente.getConversion() != null && detLivraisonVente.getConversion() == true) {

						detLivraisonVente.setPrixUnitaireHTConversion(detLivraisonVente.getPrixUnitaireHT());
						detLivraisonVente.setPrixUnitaireHT(produitValue.getPrixUnitaire());
						detLivraisonVente.setQuantiteConversion(detLivraisonVente.getQuantite());
						detLivraisonVente.setUniteSupplementaire(produitValue.getUniteSupplementaire());

						if (produitValue.getPrixUnitaire() != null && produitValue.getPrixUnitaire() != ZERO) {

							Double vQuantite = detLivraisonVente.getPrixTotalHT() / produitValue.getPrixUnitaire();

							detLivraisonVente.setQuantite(vQuantite);

						}

					}

					//if (bonLivraisonValue.getStock() == true) {
						Double qte = (produitValue.getQuantite() - detLivraisonVente.getQuantite());
						produitValue.setQuantite(qte);
						produitService.modifierProduit(produitValue);

						if (bonLivraisonValue.getIdDepot() != null) {

							RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

							request.setIdDepot(bonLivraisonValue.getIdDepot());
							request.setIdProduit(detLivraisonVente.getProduitId());

							ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
									.rechercheMulticritere(request);
							// System.out.println("####### produitDepot.getProduitdepotvalues().size() : "+
							// produitDepot.getProduitdepotvalues().size());

							if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
									&& produitDepot.getProduitdepotvalues().size() > 0) {
								ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator()
										.next();
								vProduitDepot
										.setQuantite(vProduitDepot.getQuantite() - detLivraisonVente.getQuantite());

								// System.out.println("#### vProduitDepot : " + vProduitDepot.toString());

								produitDepotPersistance.modifier(vProduitDepot);
							}
						}

					//}

					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
						produitTaxeMap.put(produitValue.getIdTaxe(), detLivraisonVente.getPrixTotalHT());

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
								+ detLivraisonVente.getPrixTotalHT();
						produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

					}

					// Ajout de Fond

					if ((produitValue.getFondSupplementaire() != null && produitValue.getFondSupplementaire() == true)
							|| (produitValue.getFond() != null && produitValue.getFond() == true)) {

						Long pIdProduit = ZERO_LONG;

						if (produitValue.getFondSupplementaire() == true)
							pIdProduit = 5000L;
						else
							pIdProduit = 5001L;
						ProduitValue vProduitValue = produitService.rechercheProduitById(pIdProduit);

						DetBonStockValue vDetails = new DetBonStockValue();
						vDetails.setProduitId(pIdProduit);
						vDetails.setQuantite(detLivraisonVente.getQuantite());
						vDetails.setPrixUnitaireHT(vProduitValue.getPrixUnitaire());
						vDetails.setPrixTotalHT(detLivraisonVente.getQuantite() * vProduitValue.getPrixUnitaire());
						vDetails.setUnite(vProduitValue.getUnite());
						vListDetails.add(vDetails);

						if (vDetails.getPrixTotalHT() != null) {
							montantHTaxeTotal = montantHTaxeTotal + vDetails.getPrixTotalHT();
						}

					}

					if (detLivraisonVente.isSerialisable() && detLivraisonVente.getProduitsSerialisable() != null) {

						String numeroSeries = "";

						for (ProduitSerialisableValue ps : detLivraisonVente.getProduitsSerialisable()) {
							
							numeroSeries += ps.getNumSerie();
							numeroSeries += "&";
							
							ps.setHistoriqueBSsortie(addReference(ps.getHistoriqueBSsortie(), bonLivraisonValue.getReference()));
							
							
							//ps.setNumBonStock(bonLivraisonValue.getReference());
							
							produitSerialisablePersistance.modifierProduitSerialisable(ps);

							// ProduitSerialisableValue produitSerialisable =
							// produitSerialisablePersistance.rechercheProduitSerialisableByNumeroSerie(ps.getNumSerie());

					/*		if (ps.isChecked() && ps.getClientId() == null) {

								ps.setClientId(bonLivraisonValue.getPartieIntId());
								ps.setDateVente(bonLivraisonValue.getDate());
								ps.setNumBonStock(bonLivraisonValue.getReference());

								// TODO veifier prix
								ps.setPrixVente(detLivraisonVente.getPrixTTC());

								numeroSeries += ps.getNumSerie();
								numeroSeries += "&";

								produitSerialisablePersistance.modifierProduitSerialisable(ps);

							}
							
						*/

						}

						if (numeroSeries != null && numeroSeries.length() > 0
								&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
							numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
							detLivraisonVente.setNumeroSeries(numeroSeries);
						}else {
							detLivraisonVente.setNumeroSeries(null);
						}

					}

				}

			}

			if (detLivraisonVente.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detLivraisonVente.getPrixTotalHT();
			}
			if (detLivraisonVente.getQuantite() != null &&  bonLivraisonValue.getNatureLivraison()!= null && !bonLivraisonValue.getNatureLivraison().equals("FACON")) {
				metrageTotal = metrageTotal + detLivraisonVente.getQuantite();
			}

			if (!modeRemiseEstTotal) {
				if (detLivraisonVente.getRemise() != null && detLivraisonVente.getPrixTotalHT() != null) {
					montantRemiseTotal += (detLivraisonVente.getPrixTotalHT() * detLivraisonVente.getRemise()) / 100;
				}
			}

			if (detLivraisonVente.getChoix() != null) {

				Long choix = null;
				try {
					choix = Long.parseLong(detLivraisonVente.getChoix());
				} catch (NumberFormatException e) {
					// logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detLivraisonVente.setChoix(choixRouleau.getDesignation());

				}
			}

			vListDetails.add(detLivraisonVente);

		}

		bonLivraisonValue.setListDetBonStock(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonLivraisonValue.getTotalPourcentageRemise()) / 100;
		}

		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);

		bonLivraisonValue.setMetrageTotal(metrageTotal);

		/***
		 * Si Client .type exonoré Alors TVA=0 au niveau de Facture et Bon de livraison
		 ***/
		// PartieInteresseValue pi =
		// partieInteresseePersistance.getPartieInteresseById(bonLivraisonValue.getPartieIntId());

		if (bonLivraisonValue.getTypePartieInteressee() != null
				&& bonLivraisonValue.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {

			bonLivraisonValue.setMontantTTC(montantHTaxeTotal);

		} else {
			bonLivraisonValue.setMontantTTC(montantTTCTotal);
		}

		// System.out.println("Uniteeeeee:::::::::::"+bonLivraisonValue.getListDetLivraisonVente().get(0).getUnite());
		return bonStockPersistance.createBonStock(bonLivraisonValue);

	}



	private Double convertisseur(Double d, int nbchiffre) {

		Double result = d;
		if (d != null) {
			String number = d.toString();
			int point = number.lastIndexOf('.');
			point += nbchiffre;
			String subnumber = "";
			if (number.length() >= point) {
				subnumber = number.substring(0, point);
			} else
				return result;
			try {
				result = Double.parseDouble(subnumber);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
	}



	@Override
	public String getCurrentReference(Calendar instance, boolean increment) {
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroBL = currentGuichetAnnuel.getNumBonStockCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBL = new StringBuilder("");

		if (currentGuichetAnnuel.getPrefixeBonStock() != null)
			vNumBL.append(currentGuichetAnnuel.getPrefixeBonStock());

		if (numeroBL > 0 && numeroBL < 10) {
			vNumBL.append("000");
		} else if (numeroBL >= 10 && numeroBL < 100) {
			vNumBL.append("00");
		}

		else if (numeroBL >= 100 && numeroBL < 1000) {
			vNumBL.append("0");
		}

		vNumBL.append(numeroBL);

		currentGuichetAnnuel.setNumBonStockCourante(new Long(numeroBL + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBonStockAnnuel(currentGuichetAnnuel);

		return vNumBL.toString();
	}
	
	@Override
	public BonStockValue validerFactureAvoirRetour(String reference) {
		BonStockValue bonStockValue = new BonStockValue();
		
		FactureAchatValue factureAvoir = factureAchatPersistance.getFactureByReference(reference);
		
		if(factureAvoir != null && factureAvoir.getNature() != null && factureAvoir.getType().equals(IConstanteCommerciale.FACTURE_TYPE_AVOIR) && factureAvoir.getNature().equals(IConstanteCommerciale.FACTURE_NATURE_RETOUR)){
			
			bonStockValue.setRefAvoirRetour(factureAvoir.getReference());	
			bonStockValue.setPartieIntId(factureAvoir.getPartieIntId());
			bonStockValue.setRefFacture(factureAvoir.getInfoLivraison());
			bonStockValue.setIdDepot(factureAvoir.getIdDepot());
			
			 List<DetBonStockValue> listDetBonStock = new ArrayList<>();
			
			for(DetFactureAchatValue det : factureAvoir.getListDetFactureAchat()) {
				DetBonStockValue detBonStockValue = new DetBonStockValue();
				
				detBonStockValue.setProduitId(det.getProduitId());
				detBonStockValue.setQuantite(det.getQuantite());
				detBonStockValue.setPrixUnitaireHT(det.getPrixUnitaireHT());
				detBonStockValue.setSerialisable(det.isSerialisable());
				
				if(det.isSerialisable() && det.getNumeroSeries() != null ) {
					
					String numSeries[]  = det.getNumeroSeries().split("&");
					
					List<String> listNumeroSeries = new ArrayList<>();
					
					Collections.addAll(listNumeroSeries, numSeries);
					
					detBonStockValue.setProduitsSerialisable(produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,detBonStockValue.getProduitId()));
					
				}
				
				listDetBonStock.add(detBonStockValue);
			}
			
		
			bonStockValue.setDateConcernee(factureAvoir.getDate());
			bonStockValue.setListDetBonStock(listDetBonStock);
			
		}
		
		else
			
			
		{
			
			bonStockValue.setRefAvoirRetour(null);	
			
		}
	
	 return bonStockValue;
	}

	@Override
	public BonStockValue validerBR(String reference) {
		
		
		
		BonStockValue bonStockValue = new BonStockValue();
		
		ReceptionAchatValue receptionAchatValue = receptionAchatPersistance.getByReference(reference);
		
		if(receptionAchatValue != null ){
			
			bonStockValue.setRefBR(receptionAchatValue.getReference());	
			bonStockValue.setPartieIntId(receptionAchatValue.getPartieIntersseId());
			bonStockValue.setIdDepot(receptionAchatValue.getIdDepot());
			
			 List<DetBonStockValue> listDetBonStockValue = new ArrayList<>();
			
			for(ProduitReceptionAchatValue det : receptionAchatValue.getListProduitReceptions()) {
				
				DetBonStockValue detBonStockValue = new DetBonStockValue();
				
				detBonStockValue.setProduitId(det.getProduitId());
				detBonStockValue.setQuantite(det.getQuantite());
				detBonStockValue.setPrixUnitaireHT(det.getPrixUnitaire());
				detBonStockValue.setSerialisable(det.isSerialisable());
				
				if(det.isSerialisable() && det.getNumeroSeries() != null ) {
					
					String numSeries[]  = det.getNumeroSeries().split("&");
					
					List<String> listNumeroSeries = new ArrayList<>();
					
					Collections.addAll(listNumeroSeries, numSeries);
					
					detBonStockValue.setProduitsSerialisable(produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,detBonStockValue.getProduitId()));
					
				}
				
				listDetBonStockValue.add(detBonStockValue);
			}
			
			bonStockValue.setDateConcernee(receptionAchatValue.getDateIntroduction());
			bonStockValue.setListDetBonStock(listDetBonStockValue);
			
		}
		
	
	
	 return bonStockValue;
	}
	
	private String addReference(String refAncien, String refAjouter) {

		if (refAncien == null) {

			return refAjouter;
		} else {

			if (refAjouter != null) {
				String ch = refAncien + "&" + refAjouter;

				return ch;
			} else {
				return refAncien;
			}

		}

	}

}
