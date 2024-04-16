package com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.DetComptoirVenteValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.RechercheMulticritereBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.boncomptoir.value.ResultatRechecheBonComptoirValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncomptoir.IBonComptoirDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteValidateComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteValidateFaconComparator;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncomptoir.IBonComptoirPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;
/**
 * Implementation of {@link IBonComptoirDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonComptoirDomaineImpl implements IBonComptoirDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonComptoirDomaineImpl.class);

	private static final String SEPARATOR = "-";
	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;
	
	

	@Autowired
	private IBonComptoirPersistance bonComptoirPersistance;

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
	public ResultatRechecheBonComptoirValue rechercherMultiCritere(RechercheMulticritereBonComptoirValue request) {

		return bonComptoirPersistance.rechercherMultiCritere(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonComptoir2(ComptoirVenteValue bonLivraisonValue) {

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

		if (bonLivraisonValue.getReference() == null || bonLivraisonValue.getReference().equals("")) {
			bonLivraisonValue.setReference(getNumeroBonComptoirFromGuichetMensuel(Calendar.getInstance()));
			// bonLivraisonValue.getReference().concat(getNumeroBonComptoir(Calendar.getInstance()));

		}

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

		for (DetComptoirVenteValue detLivraisonVente : bonLivraisonValue.getListDetComptoirVente()) {

			// Vérifier si la quantité reçu < quantité disponible
			if (detLivraisonVente.isErrorQte()) {
				detLivraisonVente.setQuantite(null);
			}

			if (detLivraisonVente.getQuantite() != null) {

				if (detLivraisonVente.getPrixUnitaireHT() != null) {
					detLivraisonVente
							.setPrixTotalHT(detLivraisonVente.getQuantite() * detLivraisonVente.getPrixUnitaireHT());
				}

				// MAJ Qte Produit
				if (detLivraisonVente.getProduitId() != null) {

					ProduitValue produitValue = produitService.rechercheProduitById(detLivraisonVente.getProduitId());
					Double qte = (produitValue.getQuantite() - detLivraisonVente.getQuantite());
					produitValue.setQuantite(qte);

					produitService.modifierProduit(produitValue);

				}

			}

			if (detLivraisonVente.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detLivraisonVente.getPrixTotalHT();
			}
			if (detLivraisonVente.getQuantite() != null && !bonLivraisonValue.getNatureLivraison().equals("FACON")) {
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
					//logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detLivraisonVente.setChoix(choixRouleau.getDesignation());

				}
			}

		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonLivraisonValue.getTotalPourcentageRemise()) / 100;
		}

	/*	Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();

		for (TaxeLivraisonValue taxeLivraison : bonLivraisonValue.getListTaxeLivraison()) {
			Long key = taxeLivraison.getId();
			if (taxeLivraisonIdTaxeMap.get(key) == null) {
				taxeLivraisonIdTaxeMap.put(taxeLivraison.getTaxeId(), taxeLivraison);
			}
		}
		// Ajout by Ghazi Atroussi 16/11/2016
		montantHTaxeTotal = montantHTaxeTotal - montantRemiseTotal;
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() != null) {
				assietteFodec = montantHTaxeTotal;
				montantTaxeFodec = (assietteFodec * taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage()) / 100;

				taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TVA)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getPourcentage() != null) {
				if (assietteFodec != ZERO) {
					assietteTVA = assietteFodec + montantTaxeFodec;
				} else {
					assietteTVA = montantHTaxeTotal;
				}

				montantTaxeTVA = (assietteTVA * taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).getPourcentage()) / 100;

				taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;
			}
		}

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		Iterator it = taxeLivraisonIdTaxeMap.entrySet().iterator();
		List<TaxeLivraisonValue> listTaxeLivraison = new ArrayList<TaxeLivraisonValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeLivraisonValue> pair = (Map.Entry<Long, TaxeLivraisonValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}

		bonLivraisonValue.setListTaxeLivraison(listTaxeLivraison);
	*/
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		
		
	
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);
		bonLivraisonValue.setMontantTTC(montantTTCTotal);
		

		
		
		bonLivraisonValue.setMetrageTotal(metrageTotal);
		
		
		

		return bonComptoirPersistance.createBonComptoir(bonLivraisonValue);
	}

	@Override
	public ComptoirVenteValue getBonComptoirById(Long id) {

		ComptoirVenteValue livraisonVenteValue = bonComptoirPersistance.getBonComptoirById(id);

		if (livraisonVenteValue.getListDetComptoirVente() != null) {
			if (livraisonVenteValue.getNatureLivraison().equals("FINI")) {
				
				for (DetComptoirVenteValue element : livraisonVenteValue.getListDetComptoirVente()) {
					
					if(element.isSerialisable()) {
						RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere = new RechercheMulticritereProduitSerialisableValue();
						pRechercheProduitSerialisableMulitCritere.setProduitId(element.getProduitId());
						//pRechercheProduitSerialisableMulitCritere.setNumBonComptoir(livraisonVenteValue.getReference());
						
						
						
						element.setProduitsSerialisable(produitSerialisablePersistance.rechercherProduitSerialisableMultiCritere(pRechercheProduitSerialisableMulitCritere).getProduitSerialisableValues());  	
					}
					
				}
				
				
				// COMMENT BY SAMER	 Collections.sort(livraisonVenteValue.getListDetComptoirVente(), new DetLivraisonVenteComparator());
			} else { // FACON

				for (DetComptoirVenteValue element : livraisonVenteValue.getListDetComptoirVente()) {
					if (element.getFicheId() != null) {
						FicheFaconValue ficheFacon = ficheFaconDomaine.getById(element.getFicheId());
						element.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
						
						
					}
					
				
					
					
				
				}
			// COMMENT BY SAMER	Collections.sort(livraisonVenteValue.getListDetComptoirVente(),	new DetLivraisonVenteFaconComparator());
					
			}
		}

		return livraisonVenteValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateBonComptoir(ComptoirVenteValue bonLivraisonValue) {
		
		
		updateProduitSerialisable(bonLivraisonValue.getId());

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
		List<DetComptoirVenteValue> vListDetails = new ArrayList<DetComptoirVenteValue>();
		for (DetComptoirVenteValue detLivraisonVente : bonLivraisonValue.getListDetComptoirVente()) {

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
				if(produitValue.isSerialisable())detLivraisonVente.setSerialisable(true);

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

			/*	if (bonLivraisonValue.getStock() == true) {
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
				
			*/	
				
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

						DetComptoirVenteValue vDetails = new DetComptoirVenteValue();
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

			}
			/////////////////////////////////////

			if (detLivraisonVente.getPrixTotalHT() != null) {
				montantHTaxeTotal += detLivraisonVente.getPrixTotalHT();
			}
			if (detLivraisonVente.getQuantite() != null && !bonLivraisonValue.getNatureLivraison().equals("FACON")) {
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
					//logger.error("NumberFormatException: " + e.getMessage());
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

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();
		//

		//System.out.println("##### TEST DE TAXE ########  ");
		// System.out.println("##### List Size :
		// "+bonLivraisonValue.getListTaxeLivraison().size());

	/*	if (bonLivraisonValue.getListTaxeLivraison() != null) {
			for (TaxeLivraisonValue taxeLivraison : bonLivraisonValue.getListTaxeLivraison()) {
				Long key = taxeLivraison.getId();
				if (taxeLivraisonIdTaxeMap.get(key) == null) {
					taxeLivraisonIdTaxeMap.put(taxeLivraison.getTaxeId(), taxeLivraison);
				}

			}
		} */

		//System.out.println("####  taxeLivraisonIdTaxeMap  size :  " + taxeLivraisonIdTaxeMap.size());
		//System.out.println("###  Fin de Test Taxre listes ");

		montantHTaxeTotal = montantHTaxeTotal - montantRemiseTotal;
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() != null) {
				assietteFodec = montantHTaxeTotal;
				montantTaxeFodec = (assietteFodec * taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage()) / 100;

				taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		// TVA variable selon produit

		for (Long taxe : taxeLivraisonIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeLivraisonIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}

		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {

				montantTaxeTimbre = taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);

				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		List<TaxeLivraisonValue> listTaxeLivraison = new ArrayList<TaxeLivraisonValue>();
		Iterator it = taxeLivraisonIdTaxeMap.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<Long, TaxeLivraisonValue> pair = (Map.Entry<Long, TaxeLivraisonValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}

		//System.out.println("#####  Liste Taxe apres traitements :    " + listTaxeLivraison.size());

	// COMMENT BY SAMER		bonLivraisonValue.setListTaxeLivraison(listTaxeLivraison);

		Double montantTTC = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);
		bonLivraisonValue.setMetrageTotal(metrageTotal);
		
		

		
		/*** Si Client .type exonoré Alors TVA=0 au niveau de Facture et Bon de livraison ***/ 
	PartieInteresseValue pi = partieInteresseePersistance.getPartieInteresseById(bonLivraisonValue.getPartieIntId());
				
		if(pi.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {
			
			bonLivraisonValue.setMontantTTC(montantHTaxeTotal);
			
		}else
		{
			bonLivraisonValue.setMontantTTC(montantTTC);
		}
		
		
	

		return bonComptoirPersistance.updateBonComptoir(bonLivraisonValue);
	}

	private void updateProduitSerialisable(Long id) {
		ComptoirVenteValue livraisonVente = bonComptoirPersistance.getBonComptoirById(id);
		
		for(DetComptoirVenteValue detailLivraisonVente :livraisonVente.getListDetComptoirVente()) {
			
			if(detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {
				
				for(ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {
					
					prodSerialisable.setClientId(null);
			// COMMENT BY SAMER			prodSerialisable.setNumBonComptoir(null);
					prodSerialisable.setDateVente(null);
					
					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);
					
				}
				
			}
		}
		
	}

	@Override
	public void deleteBonComptoir(Long id) {
		
		updateProduitSerialisable(id);

		bonComptoirPersistance.deleteBonComptoir(id);
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonComptoirList, Long factureVenteId) {

		List<ComptoirVenteValue> listComptoirVenteValue = bonComptoirPersistance
				.getProduitElementList(refBonComptoirList);

		List<DetComptoirVenteValue> listDetLivraisonVente = new ArrayList<DetComptoirVenteValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listComptoirVenteValue.size() > 0) {
			listProduitElementValue.setPartieIntId(listComptoirVenteValue.get(FIRST_INDEX).getPartieIntId());
			listProduitElementValue.setDateLivrison(listComptoirVenteValue.get(FIRST_INDEX).getDate());
			// Added By Ghazi on 25/05/2018
			listProduitElementValue.setIdMarche(listComptoirVenteValue.get(FIRST_INDEX).getMarcheId());

		}

		for (ComptoirVenteValue livraisonVente : listComptoirVenteValue) {

			for (DetComptoirVenteValue detLivraisonVente : livraisonVente.getListDetComptoirVente()) {

				
				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<Long, String>, List<DetComptoirVenteValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<DetComptoirVenteValue>>();

		for (DetComptoirVenteValue detail : listDetLivraisonVente) {
			Long produitKey = detail.getProduitId();
			String choixKey = detail.getChoix();

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<DetComptoirVenteValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ProduitValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<DetComptoirVenteValue>> pair = (Map.Entry<Map<Long, String>, List<DetComptoirVenteValue>>) it
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

			for (DetComptoirVenteValue detail : pair.getValue()) {

				if (detail.getQuantite() != null) {
					sommeQuantite = detail.getQuantite() + sommeQuantite;
				}

				if (detail.getNombreColis() != null) {
					sommeNombreColis = detail.getNombreColis() + sommeNombreColis;
				}
				
				if(detail.isSerialisable()) {
					numeroSeries+=detail.getNumeroSeries();
					numeroSeries+="-";
				}
				
				//TODO ENRICHIR WITH INFO PROD SERIALISABLE
				//TODO REMPLIR LIST PRODSERIALISABLE 
				
			}
			
			   if (numeroSeries.length() > 0 && numeroSeries.charAt(numeroSeries.length() - 1) == '-') {
			    	numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
			    	element.setNumeroSeries(numeroSeries);
			    	element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(numeroSeries,element.getProduitId()));
		        }
			    
			
			
			
			
			//TODO ENRICHIR WITH INFO PROD SERIALISABLE

			element.setQuantite(sommeQuantite);
			element.setNombreColis(sommeNombreColis);

			if (pair.getValue().size() > 0) {

				DetComptoirVenteValue detail = pair.getValue().get(0);
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

					if (factureVenteId != null) {

						DetFactureVenteValue detFactureVenteValue = detFactureVentePersistance
								.getByFactureVenteAndProduit(factureVenteId, element.getProduitId(),
										element.getChoix());

						if (detFactureVenteValue != null) {

							boolean detailIdNotExist = true;
							for (DetComptoirVenteValue detail : listDetLivraisonVente) {
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
		String numero[] = numeroSeries.split(SEPARATOR);
		
		List<String> listNumeroSeries = new ArrayList<>();
		
		 Collections.addAll( listNumeroSeries, numero );
		
		
		return produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,produitId);
	}

	@Override
	public List<String> getListBonComptoirRef() {

		List<String> listBonComptoirToRemove = new ArrayList<String>();

		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonComptoirToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<String> listBonComptoir = new ArrayList<String>();

		List<ComptoirVenteValue> bonLivraisonlist = bonComptoirPersistance.getAll();

		for (ComptoirVenteValue livraisonVente : bonLivraisonlist) {
			if (livraisonVente.getReference() != null && livraisonVente.getReference().length() != 0)
				listBonComptoir.add(livraisonVente.getReference());
		}

		listBonComptoir.removeAll(listBonComptoirToRemove);

		return listBonComptoir;
	}

	@Override
	public List<LivraisonVenteVue> getListBonComptoirRefByClient(Long idClient) {

		List<String> listBonComptoirToRemove = new ArrayList<String>();

		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonComptoirToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

	// COMMENT BY SAMER		List<LivraisonVenteVue> bonLivraisonlist = bonComptoirPersistance.getReferenceBLByClientId(idClient);

   // COMMENT BY SAMER		bonLivraisonlist.removeAll(listBonComptoirToRemove);

  //COMMENT BY SAMER return bonLivraisonlist;
	//COMMENT BY SAMER set null by sam	
	return null;
	}

	@Override
	public ComptoirVenteValue getByReference(String reference) {

		return bonComptoirPersistance.getByReference(reference);
	}

	private String getNumeroBonComptoirFromGuichetMensuel(final Calendar pDateBonLiv) {

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumBonComptoirReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonLiv.get(Calendar.YEAR);
		int moisActuel = pDateBonLiv.get(Calendar.MONTH) + 1;

		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonLiv = new StringBuilder("");
		vNumBonLiv.append(vAnneeCourante);
		vNumBonLiv.append(String.format("%02d", moisActuel));
		vNumBonLiv.append(String.format("%04d", vNumGuichetBonLiv));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
		/** idMensuel = (annuelcourante - 2016) + moisCourant */

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonComptoirCourant(new Long(vNumGuichetBonLiv + 1L));

		/** Modification de la valeur en base du numéro. */
		this.guichetierMensuelDomaine.modifierGuichetBonComptoirMensuel(vGuichetValeur);

		return vNumBonLiv.toString();

	}
	
	private String getNumeroBonComptoirFromGuichetAnnuel(final Calendar pDateBonFacture) {

		Long vNumGuichetBL = this.guichetAnnuelDomaine.getNextNumBonComptoirReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");
		vNumFacture.append(vAnneeCourante);
		if(vNumGuichetBL<100)
		vNumFacture.append("0");
		vNumFacture.append( vNumGuichetBL);
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) +1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonComptoirCourante(new Long(
				vNumGuichetBL + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetBonComptoirAnnuel(vGuichetValeur);
		return vNumFacture.toString();
	}

	@Override
	public ResultatRechecheBonComptoirValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request) {
		ResultatRechecheBonComptoirValue bonLivraisonlist = bonComptoirPersistance
				.getLivraisonByFnReportingRegionDate(request);

		Set<ComptoirVenteValue> list = new TreeSet<ComptoirVenteValue>();
		for (ComptoirVenteValue bonLivElement : bonLivraisonlist.getList()) {
			ComptoirVenteValue vueBonLiv = new ComptoirVenteValue();
			vueBonLiv.setMontantHTaxe(bonLivElement.getMontantHTaxe());
			list.add(vueBonLiv);
			bonLivraisonlist.setList(list);
		}
		return bonLivraisonlist;
	}

	@Override
	public List<ComptoirVenteValue> getAll() {
		return bonComptoirPersistance.getAll();
	}

	@Override
	public ListTraitFaconElementValue getTraitementFaconElementList(List<String> refBonComptoirList,
			Long factureVenteId) {

		List<ComptoirVenteValue> listComptoirVenteValue = bonComptoirPersistance
				.getTraitementFaconElementList(refBonComptoirList);

		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();

		ListTraitFaconElementValue listTraitFaconElementValue = new ListTraitFaconElementValue();

		if (listComptoirVenteValue.size() > 0) {
			listTraitFaconElementValue.setPartieIntId(listComptoirVenteValue.get(FIRST_INDEX).getPartieIntId());
			listTraitFaconElementValue.setDateLivraison(listComptoirVenteValue.get(FIRST_INDEX).getDate());
		}

		for (ComptoirVenteValue livraisonVente : listComptoirVenteValue) {
			if (livraisonVente.getListDetComptoirVente() != null) {
				for (DetComptoirVenteValue detLivraisonVente : livraisonVente.getListDetComptoirVente()) {

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
	// public String createBonComptoir(ComptoirVenteValue bonLivraisonValue) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonComptoir(ComptoirVenteValue bonLivraisonValue) {

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
			
			bonLivraisonValue.setReference(getNumeroBonComptoirFromGuichetAnnuel(Calendar.getInstance()));
			
			//bonLivraisonValue.setReference(getNumeroBonComptoirFromGuichetMensuel(Calendar.getInstance()));
			// bonLivraisonValue.getReference().concat(getNumeroBonComptoir(Calendar.getInstance()));
		}
		
		bonLivraisonValue.setDateClotue(Calendar.getInstance());

		// Forcer la date

		if (bonLivraisonValue.getDate() == null)
			bonLivraisonValue.setDate(Calendar.getInstance());

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
		List<DetComptoirVenteValue> vListDetails = new ArrayList<DetComptoirVenteValue>();

		for (DetComptoirVenteValue detLivraisonVente : bonLivraisonValue.getListDetComptoirVente()) {
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
					if(produitValue.isSerialisable())detLivraisonVente.setSerialisable(true);

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

				/*	if (bonLivraisonValue.getStock() == true) {
						Double qte = (produitValue.getQuantite() - detLivraisonVente.getQuantite());
						produitValue.setQuantite(qte);
						produitService.modifierProduit(produitValue);

						if (bonLivraisonValue.getIdDepot() != null) {

							RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

							request.setIdDepot(bonLivraisonValue.getIdDepot());
							request.setIdProduit(detLivraisonVente.getProduitId());

							ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
									.rechercheMulticritere(request);
							System.out.println("#######  produitDepot.getProduitdepotvalues().size() : "
									+ produitDepot.getProduitdepotvalues().size());
							if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
									&& produitDepot.getProduitdepotvalues().size() > 0) {
								ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator()
										.next();
								vProduitDepot
										.setQuantite(vProduitDepot.getQuantite() - detLivraisonVente.getQuantite());

								System.out.println("####  vProduitDepot  :  " + vProduitDepot.toString());

								produitDepotPersistance.modifier(vProduitDepot);
							}
						}

					}
					
					*/

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

						DetComptoirVenteValue vDetails = new DetComptoirVenteValue();
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
					
					
					if(detLivraisonVente.isSerialisable() && detLivraisonVente.getProduitsSerialisable() != null) {
						
						String numeroSeries = "";
						
						for(ProduitSerialisableValue ps : detLivraisonVente.getProduitsSerialisable()) {
							
						//	ProduitSerialisableValue produitSerialisable =	produitSerialisablePersistance.rechercheProduitSerialisableByNumeroSerie(ps.getNumSerie());
							
							
							if(ps.isChecked() && ps.getClientId() == null ) {
								
								ps.setClientId(bonLivraisonValue.getPartieIntId());
								ps.setDateVente(bonLivraisonValue.getDate());
							//	ps.setNumBonComptoir(bonLivraisonValue.getReference());
								
								//TODO veifier prix
								ps.setPrixVente(detLivraisonVente.getPrixTTC());
								
								
								numeroSeries+=ps.getNumSerie();
								numeroSeries+="-";
								
								produitSerialisablePersistance.modifierProduitSerialisable(ps);
								
								
							}
							
							
						}
						
					    if (numeroSeries != null && numeroSeries.length() > 0 && numeroSeries.charAt(numeroSeries.length() - 1) == '-') {
					    	numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
					    	detLivraisonVente.setNumeroSeries(numeroSeries);
				        }
						
					    
					  
					}
				

				}

			}

			if (detLivraisonVente.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detLivraisonVente.getPrixTotalHT();
			}
			if (detLivraisonVente.getQuantite() != null && !bonLivraisonValue.getNatureLivraison().equals("FACON")) {
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
					//logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detLivraisonVente.setChoix(choixRouleau.getDesignation());

				}
			}

			vListDetails.add(detLivraisonVente);

		}

		bonLivraisonValue.setListDetComptoirVente(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonLivraisonValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();
		// modification
/*	for (TaxeLivraisonValue taxeLivraison : bonLivraisonValue.getListTaxeLivraison()) {
			Long key = taxeLivraison.getId();
			if (taxeLivraisonIdTaxeMap.get(key) == null) {
				taxeLivraisonIdTaxeMap.put(taxeLivraison.getTaxeId(), taxeLivraison);
			}
		}
		
	
		// Ajout by Ghazi Atroussi 16/11/2016
		montantHTaxeTotal = montantHTaxeTotal - montantRemiseTotal;
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() != null) {
				assietteFodec = montantHTaxeTotal;
				montantTaxeFodec = (assietteFodec * taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage()) / 100;

				taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		// modification
		// TVA variable selon produit

		for (Long taxe : taxeLivraisonIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeLivraisonIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}

		// if(produitTaxeMap.containsKey(TAXE_ID_TVA)){
		// if(produitTaxeMap.get(TAXE_ID_TVA).valueOf(FIRST_INDEX) != null){
		// for(double i:produitTaxeMap.values()){
		// if(assietteFodec != ZERO){
		// assietteTVA = assietteFodec +i ;
		// }else{
		// assietteTVA = i;
		// }
		//
		// montantTaxeTVA = montantTaxeTVA+( assietteTVA *
		// produitTaxeMap.get(TAXE_ID_TVA).valueOf(FIRST_INDEX) ) / 100 ;
		//
		// taxeLivraisonIdTaxeMap.get(TAXE_ID_TVA).setMontant(montantTaxeTVA);
		// montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;
		// }
		// }
		// }

		// Fin de calcul TVA
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		Iterator it = taxeLivraisonIdTaxeMap.entrySet().iterator();
		List<TaxeLivraisonValue> listTaxeLivraison = new ArrayList<TaxeLivraisonValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeLivraisonValue> pair = (Map.Entry<Long, TaxeLivraisonValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}

		bonLivraisonValue.setListTaxeLivraison(listTaxeLivraison);
		
		
		
		
		*/
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);

		bonLivraisonValue.setMetrageTotal(metrageTotal);
		
		/*** Si Client .type exonoré Alors TVA=0 au niveau de Facture et Bon de livraison ***/ 
		PartieInteresseValue pi = partieInteresseePersistance.getPartieInteresseById(bonLivraisonValue.getPartieIntId());
		
		if(pi.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {
			
			bonLivraisonValue.setMontantTTC(montantHTaxeTotal);
			
		}else
		{
			bonLivraisonValue.setMontantTTC(montantTTCTotal);
		}
		
		
		
		// System.out.println("Uniteeeeee:::::::::::"+bonLivraisonValue.getListDetLivraisonVente().get(0).getUnite());
		return bonComptoirPersistance.createBonComptoir(bonLivraisonValue);

	}

	@Override
	public List<LivraisonVenteFactureVue> getAllListBonComptoirRefByClient(Long idClient) {

		List<String> listBonComptoirToRemove = new ArrayList<String>();

		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonComptoirToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<LivraisonVenteFactureVue> bonLivraisonlist = bonComptoirPersistance.getListBLByClientId(idClient);

		List<LivraisonVenteFactureVue> result = new ArrayList<LivraisonVenteFactureVue>();

		for (LivraisonVenteFactureVue livraison : bonLivraisonlist) {

			if (livraison.getMarcheId() != null) {
				MarcheValue marche = marcheDomaine.getById(livraison.getMarcheId());
				livraison.setMarche(marche.getDesignation());
			}

			if (!listBonComptoirToRemove.contains(livraison.getReference()))
				result.add(livraison);

		}
		return result;
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

}
