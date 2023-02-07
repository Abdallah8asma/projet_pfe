package com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.fn.report.reporting.RechercheMulticritereFnReportingtValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.MarcheValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ValiderBLPassagerValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IBonLivraisonDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities.DetLivraisonVenteComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteValidateComparatorByOrdre;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteValidateFaconComparator;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IBonCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 * Implementation of {@link IBonLivraisonDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonLivraisonDomaineImpl implements IBonLivraisonDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonDomaineImpl.class);

	private static final String SEPARATOR = "-";

	private static final String SEPARATOR_NUMERO_SERIE = "&";

	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;

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
	
	@Autowired
	private IReceptionAchatPersistance receptionAchatPersistance;
	
	
	@Autowired
	private IBonCommandePersistance bonCommandePersistance;
	
	
	@Autowired
	private IElementReglementPersistance elementReglementPersistance;

	
	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;
	
	
	@Override
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(RechercheMulticritereBonLivraisonValue request) {

		return bonLivraisonPersistance.rechercherMultiCritere(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonLivraison2(LivraisonVenteValue bonLivraisonValue) {

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
			bonLivraisonValue.setReference(getNumeroBonLivraisonFromGuichetMensuel(Calendar.getInstance()));
			// bonLivraisonValue.getReference().concat(getNumeroBonLivraison(Calendar.getInstance()));

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

		for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListDetLivraisonVente()) {

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
					// logger.error("NumberFormatException: " + e.getMessage());
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

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();

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
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);
		bonLivraisonValue.setMontantTTC(montantTTCTotal);

		bonLivraisonValue.setMetrageTotal(metrageTotal);

		return bonLivraisonPersistance.createBonLivraison(bonLivraisonValue);
	}

	@Override
	public LivraisonVenteValue getBonLivraisonById(Long id) {

		LivraisonVenteValue livraisonVenteValue = bonLivraisonPersistance.getBonLivraisonById(id);

		if (livraisonVenteValue.getListDetLivraisonVente() != null) {
			if (livraisonVenteValue.getNatureLivraison().equals("FINI")) {

				for (DetLivraisonVenteValue element : livraisonVenteValue.getListDetLivraisonVente()) {

					element.setQuantiteAncien(element.getQuantite());

					if (element.isSerialisable()) {

						
						element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),element.getProduitId()));

						/*
						 * RechercheMulticritereProduitSerialisableValue
						 * pRechercheProduitSerialisableMulitCritere = new
						 * RechercheMulticritereProduitSerialisableValue();
						 * pRechercheProduitSerialisableMulitCritere.setProduitId(element.getProduitId()
						 * ); pRechercheProduitSerialisableMulitCritere
						 * .setNumBonLivraison(livraisonVenteValue.getReference());
						 * 
						 * element.setProduitsSerialisable(produitSerialisablePersistance
						 * .rechercherProduitSerialisableMultiCritere(
						 * pRechercheProduitSerialisableMulitCritere) .getProduitSerialisableValues());
						 */

					}

				}
				//commente par samer le 09.06.20 afin de rendre le meme ordre de saisie
				Collections.sort(livraisonVenteValue.getListDetLivraisonVente(), new DetLivraisonVenteComparator());
			} else { // FACON

				for (DetLivraisonVenteValue element : livraisonVenteValue.getListDetLivraisonVente()) {

					element.setQuantiteAncien(element.getQuantite());

					if (element.getFicheId() != null) {
						FicheFaconValue ficheFacon = ficheFaconDomaine.getById(element.getFicheId());
						element.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));

					}

				}
				// commente par samer le 09.06.20 afin de rendre le meme ordre de saisie
				//Collections.sort(livraisonVenteValue.getListDetLivraisonVente(),new DetLivraisonVenteFaconComparator());
						
			}
		}

		return livraisonVenteValue;
	}

	private List<String> getNumerosSerieList(String numeroSeries) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
           String type = "";
        
        
        if(bonLivraisonValue.getDeclare() != null && bonLivraisonValue.getDeclare() == true)
        	type = "declarer";
        else
         	type = "non-declare";
		
		if (bonLivraisonValue.getReference() != null && bonLivraisonValue.getRefAvantChangement() != null

				&& !bonLivraisonValue.getReference().equals(bonLivraisonValue.getRefAvantChangement())) {

			 getCurrentReferenceByType(type, Calendar.getInstance(), true);

		}
		
		

		if (bonLivraisonValue.getGroupeClientId() == null && bonLivraisonValue.getPartieIntId() != null)
			bonLivraisonValue.setGroupeClientId(
					partieInteresseePersistance.getById(bonLivraisonValue.getPartieIntId()).getGroupeClientId());

		
		
		
		
		BaseInfoValue baseInfo = baseInfoPersistance.getClientActif() ;
		
		
		if(Boolean.TRUE.equals(baseInfo.getContrainteModificationBl())) {
			
			String msgVerifierContrainteModificationBL = verifierContrainteModificationBL(bonLivraisonValue);
			
			if(msgVerifierContrainteModificationBL != null) return msgVerifierContrainteModificationBL;
			
			
		}
		
	
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
		
		Long maxOrdre = getMaxOrdre(bonLivraisonValue.getListDetLivraisonVente());
		List<DetLivraisonVenteValue> vListDetails = new ArrayList<DetLivraisonVenteValue>();
		for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListDetLivraisonVente()) {
			
			if(detLivraisonVente.getPrixUnitaireHT() == null)
				detLivraisonVente.setPrixUnitaireHT(ZERO);

			
			if(detLivraisonVente.getId() == null && detLivraisonVente.getFicheId() == null) {
				maxOrdre ++;
				detLivraisonVente.setFicheId(maxOrdre);
			}

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
				if (detLivraisonVente.getId() != null && bonLivraisonValue.getStock()!= null  && bonLivraisonValue.getStock() == true
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
				if (detLivraisonVente.getId() == null && bonLivraisonValue.getStock() != null &&  bonLivraisonValue.getStock() == true
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

				
				//Calcul TVA
				
				//TODO GASH -10092020
                // Modification - Introduire Remise dans Assiette de TVA
				// Verification Round 
				
				if (detLivraisonVente.getRemise()==null)
					detLivraisonVente.setRemise(0D);
				Double totalApresRemise=detLivraisonVente.getPrixTotalHT()*(1-detLivraisonVente.getRemise()/100);
				
				
				
				
				
				if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
					produitTaxeMap.put(produitValue.getIdTaxe(), totalApresRemise);

				} else {
					Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
							+ totalApresRemise;
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

						DetLivraisonVenteValue vDetails = new DetLivraisonVenteValue();
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

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();
		//

		// System.out.println("##### TEST DE TAXE ######## ");
		// System.out.println("##### List Size :
		// "+bonLivraisonValue.getListTaxeLivraison().size());

		if (bonLivraisonValue.getListTaxeLivraison() != null) {
			for (TaxeLivraisonValue taxeLivraison : bonLivraisonValue.getListTaxeLivraison()) {
				Long key = taxeLivraison.getId();
				if (taxeLivraisonIdTaxeMap.get(key) == null) {
					taxeLivraisonIdTaxeMap.put(taxeLivraison.getTaxeId(), taxeLivraison);
				}

			}
		}

		// System.out.println("#### taxeLivraisonIdTaxeMap size : " +
		// taxeLivraisonIdTaxeMap.size());
		// System.out.println("### Fin de Test Taxre listes ");

		montantHTaxeTotal = montantHTaxeTotal - montantRemiseTotal;
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() != null) {
				assietteFodec = montantHTaxeTotal;
				montantTaxeFodec = (assietteFodec * taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage()) / 100;
				montantTaxeFodec = 	(double)Math.round(montantTaxeFodec * 1000) / 1000 ;
				taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				
				
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		// TVA variable selon produit

	/*	for (Long taxe : taxeLivraisonIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeLivraisonIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}
		*/
		
		for (Long taxe : taxeLivraisonIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe)) {
					
					if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
						
						montantTaxeTVA = (produitTaxeMap.get(taxe) + produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() /100 )* taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}else 
						
					{
						
   					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}
					
					montantTaxeTVA = 	(double)Math.round(montantTaxeTVA * 1000) / 1000 ;
				}

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

		// System.out.println("##### Liste Taxe apres traitements : " +
		// listTaxeLivraison.size());

		bonLivraisonValue.setListTaxeLivraison(listTaxeLivraison);

		Double montantTTC = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;
		
		
		montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
		montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
		montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
		metrageTotal = 	(double)Math.round(metrageTotal * 1000) / 1000 ;
		montantTTC = 	(double)Math.round(montantTTC * 1000) / 1000 ;
		
		

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
		
		if(bonLivraisonValue.getTauxConversion() != null && montantTTC !=null )
		bonLivraisonValue.setMontantConverti(bonLivraisonValue.getTauxConversion()*montantTTC);
		
		
		//Si une livraison correspant a une seule commande
		if(estNonVide(bonLivraisonValue.getRefCommande()) && !bonLivraisonValue.getRefCommande().contains(SEPARATOR)) {
			
			updateCommandeVenteWithInfoBL(bonLivraisonValue.getRefCommande(),bonLivraisonValue);
			
		}

		return bonLivraisonPersistance.updateBonLivraison(bonLivraisonValue);
	}

	private String verifierContrainteModificationBL(LivraisonVenteValue bonLivraisonValue) {
		
		if(bonLivraisonValue.getReference() != null && facturePersistance.existeBL(bonLivraisonValue.getReference()))
			return IConstanteCommerciale.CODE_ERROR_EDITION_BL_HAS_FACTURE;
		
		
		if(bonLivraisonValue.getReference() != null && receptionAchatPersistance.existeBL(bonLivraisonValue.getReference()))
			return IConstanteCommerciale.CODE_ERROR_EDITION_BL_HAS_BON_RETOUR;
		
		
		return null;
		
	}

	private Long getMaxOrdre(List<DetLivraisonVenteValue> listDetLivraisonVente) {
		
		Long max = new Long(0);
		for (DetLivraisonVenteValue detLivraisonVente :listDetLivraisonVente) {
			if(detLivraisonVente.getFicheId() != null && detLivraisonVente.getFicheId()> max)
				max = detLivraisonVente.getFicheId();
		}
		// TODO Auto-generated method stub
		return max;
	}

	private void updateStockAfterDeleteDetLivraisonVente(LivraisonVenteValue bonLivraisonValue) {

		if (bonLivraisonValue.getStock() != null && bonLivraisonValue.getStock() == true
				&& bonLivraisonValue.getListSuppDetLivraisonVente() != null
				&& bonLivraisonValue.getListSuppDetLivraisonVente().size() > 0) {

			for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListSuppDetLivraisonVente()) {

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
		LivraisonVenteValue livraisonVente = bonLivraisonPersistance.getBonLivraisonById(id);

		for (DetLivraisonVenteValue detailLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

			if (detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {

					prodSerialisable.setClientId(null);
					prodSerialisable.setNumBonLivraison(null);
					prodSerialisable.setDateVente(null);

					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);

				}

			}
		}

	}

	private void updateProduitSerialisable(LivraisonVenteValue lv) {
		LivraisonVenteValue livraisonVente = lv;

		for (DetLivraisonVenteValue detailLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

			if (detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {

					prodSerialisable.setClientId(null);
					prodSerialisable.setNumBonLivraison(null);
					prodSerialisable.setDateVente(null);

					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);

				}

			}
		}

	}

	private void updateProduitSerialisableIfUpdateBL(LivraisonVenteValue lv) {
		LivraisonVenteValue livraisonVente = lv;

		for (DetLivraisonVenteValue detailLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

			if (detailLivraisonVente.isSerialisable() && detailLivraisonVente.getProduitsSerialisable() != null) {

				String numeroSeries = "";

				for (ProduitSerialisableValue prodSerialisable : detailLivraisonVente.getProduitsSerialisable()) {

					/** Si un nouveau produit serialisable a été ajouté **/

					if (prodSerialisable.getNumBonLivraison() == null) {

						prodSerialisable.setClientId(lv.getPartieIntId());
						prodSerialisable.setNumBonLivraison(lv.getReference());
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
		}

	}

	@Override
	public void deleteBonLivraison(Long id) {

		LivraisonVenteValue livraisonVente = this.getBonLivraisonById(id);

		updateProduitSerialisable(livraisonVente);

		updateStockAfterDeleteBonLivraison(livraisonVente);
		
		
		
		

		bonLivraisonPersistance.deleteBonLivraison(id);
		
		
		deleteElementReglementByRefBL(livraisonVente.getReference());
	}

	

	private void deleteElementReglementByRefBL(String reference) {
		 List<ElementReglementValue> list = elementReglementPersistance.getByRefBLExact(reference);
		 
		 for(ElementReglementValue element : list) {
			 
			 elementReglementPersistance.deleteElementReglementById(element.getId());
			 
		 }
		
	}

	private void updateStockAfterDeleteBonLivraison(LivraisonVenteValue lv) {
		LivraisonVenteValue bonLivraisonValue = lv;

		for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListDetLivraisonVente()) {

			if (detLivraisonVente.getProduitId() != null && detLivraisonVente.getQuantite() != null) {

				ProduitValue produitValue = produitService.rechercheProduitById(detLivraisonVente.getProduitId());

				if (produitValue != null && bonLivraisonValue.getStock()!=null && bonLivraisonValue.getStock() == true) {
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
	public ListProduitElementValue getProduitElementList(List<String> refBonLivraisonList, Long factureVenteId) {

		List<LivraisonVenteValue> listLivraisonVenteValue = bonLivraisonPersistance
				.getProduitElementList(refBonLivraisonList);

		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listLivraisonVenteValue.size() > 0) {
			listProduitElementValue.setPartieIntId(listLivraisonVenteValue.get(FIRST_INDEX).getPartieIntId());
			listProduitElementValue.setDateLivrison(listLivraisonVenteValue.get(FIRST_INDEX).getDate());
			// Added By Ghazi on 25/05/2018
			listProduitElementValue.setIdMarche(listLivraisonVenteValue.get(FIRST_INDEX).getMarcheId());
			listProduitElementValue.setIdDepot(listLivraisonVenteValue.get(FIRST_INDEX).getIdDepot());
			
			listProduitElementValue.setRefCommande(listLivraisonVenteValue.get(FIRST_INDEX).getRefCommande());

		}

		for (LivraisonVenteValue livraisonVente : listLivraisonVenteValue) {

			for (DetLivraisonVenteValue detLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<Long, String>, List<DetLivraisonVenteValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<DetLivraisonVenteValue>>();

		for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
			Long produitKey = detail.getProduitId();
			String choixKey = detail.getChoix();

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<DetLivraisonVenteValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ProduitValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<DetLivraisonVenteValue>> pair = (Map.Entry<Map<Long, String>, List<DetLivraisonVenteValue>>) it
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
			
			List<String> refMiseList = new ArrayList<String>();

			for (DetLivraisonVenteValue detail : pair.getValue()) {
				
				//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
				element.setFicheId(detail.getFicheId());

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
				
				
				if(detail.getNumeroOF() != null)
					
					{
					
				
					String[] arrayString =  StringUtils.split(detail.getNumeroOF(), ", ") ;
					List<String> ofList =new ArrayList<String>(Arrays.asList(arrayString)) ;
					
					for(String ch : ofList) {
						
						if( !refMiseList.contains(ch)) 
                         refMiseList.add(detail.getNumeroOF());

					}
			

					}
				

				// TODO ENRICHIR WITH INFO PROD SERIALISABLE
				// TODO REMPLIR LIST PRODSERIALISABLE

			}
			
			if(refMiseList.size() > 0) {
				
				
				  element.setNumeroOF(StringUtils.join(refMiseList, ", "));

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

				DetLivraisonVenteValue detail = pair.getValue().get(0);
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
							for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
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
		
			//Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparator());
			//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
			Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparatorByOrdre());
			
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
	public List<String> getListBonLivraisonRef() {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<String> listBonLivraison = new ArrayList<String>();

		List<LivraisonVenteValue> bonLivraisonlist = bonLivraisonPersistance.getAll();

		for (LivraisonVenteValue livraisonVente : bonLivraisonlist) {
			if (livraisonVente.getReference() != null && livraisonVente.getReference().length() != 0)
				listBonLivraison.add(livraisonVente.getReference());
		}

		listBonLivraison.removeAll(listBonLivraisonToRemove);

		return listBonLivraison;
	}

	@Override
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient) {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<LivraisonVenteVue> bonLivraisonlist = bonLivraisonPersistance.getReferenceBLByClientId(idClient);

List<LivraisonVenteVue> bonLivraisonlistFinal = new ArrayList<>();
		
		for(LivraisonVenteVue liv :bonLivraisonlist ) {
			
			if(!listBonLivraisonToRemove.contains(liv.getReferenceBL()))
				bonLivraisonlistFinal.add(liv);
		}

		
		return bonLivraisonlistFinal;
		
		//bonLivraisonlist.removeAll(listBonLivraisonToRemove);

		//return bonLivraisonlist;
	}

	@Override
	public LivraisonVenteValue getByReference(String reference) {

		return bonLivraisonPersistance.getByReference(reference);
	}

	private String getNumeroBonLivraisonFromGuichetMensuel(final Calendar pDateBonLiv) {

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumBonLivraisonReference();
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
		vGuichetValeur.setNumReferenceBonLivraisonCourant(new Long(vNumGuichetBonLiv + 1L));

		/** Modification de la valeur en base du numéro. */
		this.guichetierMensuelDomaine.modifierGuichetBonLivraisonMensuel(vGuichetValeur);

		return vNumBonLiv.toString();

	}

	private String getNumeroBonLivraisonFromGuichetAnnuel(final Calendar pDateBonFacture) {

		Long vNumGuichetBL = this.guichetAnnuelDomaine.getNextNumBLReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");
		vNumFacture.append(vAnneeCourante);
		if (vNumGuichetBL < 100)
			vNumFacture.append("0");
		vNumFacture.append(vNumGuichetBL);
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) + 1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonLivraisonCourante(new Long(vNumGuichetBL + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine.modifierGuichetBLAnnuel(vGuichetValeur);
		return vNumFacture.toString();
	}

	@Override
	public ResultatRechecheBonLivraisonValue getLivraisonByFnReportingRegionDate(
			RechercheMulticritereFnReportingtValue request) {
		ResultatRechecheBonLivraisonValue bonLivraisonlist = bonLivraisonPersistance
				.getLivraisonByFnReportingRegionDate(request);

		Set<LivraisonVenteValue> list = new TreeSet<LivraisonVenteValue>();
		for (LivraisonVenteValue bonLivElement : bonLivraisonlist.getList()) {
			LivraisonVenteValue vueBonLiv = new LivraisonVenteValue();
			vueBonLiv.setMontantHTaxe(bonLivElement.getMontantHTaxe());
			list.add(vueBonLiv);
			bonLivraisonlist.setList(list);
		}
		return bonLivraisonlist;
	}

	@Override
	public List<LivraisonVenteValue> getAll() {
		return bonLivraisonPersistance.getAll();
	}

	@Override
	public ListTraitFaconElementValue getTraitementFaconElementList(List<String> refBonLivraisonList,
			Long factureVenteId) {

		List<LivraisonVenteValue> listLivraisonVenteValue = bonLivraisonPersistance
				.getTraitementFaconElementList(refBonLivraisonList);

		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();

		ListTraitFaconElementValue listTraitFaconElementValue = new ListTraitFaconElementValue();

		if (listLivraisonVenteValue.size() > 0) {
			listTraitFaconElementValue.setPartieIntId(listLivraisonVenteValue.get(FIRST_INDEX).getPartieIntId());
			listTraitFaconElementValue.setDateLivraison(listLivraisonVenteValue.get(FIRST_INDEX).getDate());
		}

		for (LivraisonVenteValue livraisonVente : listLivraisonVenteValue) {
			if (livraisonVente.getListDetLivraisonVente() != null) {
				for (DetLivraisonVenteValue detLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

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
		return val != null && !"".equals(val) && !"undefined".equals(val);
	}

	// @Override
	// public String createBonLivraison(LivraisonVenteValue bonLivraisonValue) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue) {

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
		
		//verification contrainte unique de info sortie
		if(bonLivraisonValue.getInfoSortie() != null) {
			
			
			LivraisonVenteValue blrechByInfoSortie =  bonLivraisonPersistance.getByInfoSortie(bonLivraisonValue.getInfoSortie());
		
		     if(blrechByInfoSortie != null) {
			  
			     	return IConstanteCommerciale.CODE_ERROR_CREATION_BL_BS_EXSIT;
			  
		     }
		
		}
		
		
		
        String type = "";
        
        
        if(bonLivraisonValue.getDeclare() != null && bonLivraisonValue.getDeclare() == true)
        	type = "declarer";
        else
         	type = "non-declare";
		

		if (bonLivraisonValue.getReference() == null || bonLivraisonValue.getReference().equals("")) {

			bonLivraisonValue.setReference(getCurrentReferenceByType(type,Calendar.getInstance(), true));

			// bonLivraisonValue.setReference(getNumeroBonLivraisonFromGuichetAnnuel(Calendar.getInstance()));

			// bonLivraisonValue.setReference(getNumeroBonLivraisonFromGuichetMensuel(Calendar.getInstance()));
			// bonLivraisonValue.getReference().concat(getNumeroBonLivraison(Calendar.getInstance()));
		} else if (bonLivraisonValue.getRefAvantChangement() != null
				&& bonLivraisonValue.getReference().equals(bonLivraisonValue.getRefAvantChangement())) {
			this.getCurrentReferenceByType(type,bonLivraisonValue.getDate(), true);
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
		List<DetLivraisonVenteValue> vListDetails = new ArrayList<DetLivraisonVenteValue>();
		
		
		Long ordreDetailLivraison = new Long(0);

		for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListDetLivraisonVente()) {
			
			if(detLivraisonVente.getPrixUnitaireHT() == null)
				detLivraisonVente.setPrixUnitaireHT(ZERO);
			
			
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

					if (bonLivraisonValue.getStock()!=null && bonLivraisonValue.getStock() == true) {
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

					}

					//TODO GASH -10092020
                    // Modification - Introduire Remise dans Assiette de TVA
					// Verification Round 
					
					if (detLivraisonVente.getRemise()==null)
						detLivraisonVente.setRemise(0D);
					Double totalApresRemise=detLivraisonVente.getPrixTotalHT()*(1-detLivraisonVente.getRemise()/100);
					
					
					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
						produitTaxeMap.put(produitValue.getIdTaxe(),totalApresRemise);

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
								+ totalApresRemise;
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

						DetLivraisonVenteValue vDetails = new DetLivraisonVenteValue();
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

							// ProduitSerialisableValue produitSerialisable =
							// produitSerialisablePersistance.rechercheProduitSerialisableByNumeroSerie(ps.getNumSerie());

							if (ps.isChecked() && ps.getClientId() == null) {

								ps.setClientId(bonLivraisonValue.getPartieIntId());
								ps.setDateVente(bonLivraisonValue.getDate());
								ps.setNumBonLivraison(bonLivraisonValue.getReference());

								// TODO veifier prix
								ps.setPrixVente(detLivraisonVente.getPrixTTC());

								numeroSeries += ps.getNumSerie();
								numeroSeries += "&";

								produitSerialisablePersistance.modifierProduitSerialisable(ps);

							}

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
					// logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detLivraisonVente.setChoix(choixRouleau.getDesignation());

				}
			}

			ordreDetailLivraison++;
			detLivraisonVente.setFicheId(ordreDetailLivraison);
			vListDetails.add(detLivraisonVente);
			
			

		}

		bonLivraisonValue.setListDetLivraisonVente(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonLivraisonValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeLivraisonValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeLivraisonValue>();
		// modification
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

				montantTaxeFodec = 	(double)Math.round(montantTaxeFodec * 1000) / 1000 ;
				
				taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		// modification
		// TVA variable selon produit

/*		for (Long taxe : taxeLivraisonIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeLivraisonIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}
		
		*/
		
		
		for (Long taxe : taxeLivraisonIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe)) {
					
					if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
						
						montantTaxeTVA = (produitTaxeMap.get(taxe) + produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() /100 )* taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;
					
			
					}else 
						
					{
						
						montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}
					
					montantTaxeTVA = 	(double)Math.round(montantTaxeTVA * 1000) / 1000 ;
				}
					

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
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;
		
		
		
		montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
		montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
		montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
		metrageTotal = 	(double)Math.round(metrageTotal * 1000) / 1000 ;
		montantTTCTotal = 	(double)Math.round(montantTTCTotal * 1000) / 1000 ;
		
		

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

		
	
		
		if(bonLivraisonValue.getTauxConversion() !=null && montantTTCTotal != null)
		bonLivraisonValue.setMontantConverti(bonLivraisonValue.getTauxConversion()*montantTTCTotal);
		
		else 
			bonLivraisonValue.setMontantConverti(ZERO);
		
		//Si une livraison correspant a une seule commande
		if(estNonVide(bonLivraisonValue.getRefCommande()) && !bonLivraisonValue.getRefCommande().contains(SEPARATOR)) {
			
			updateCommandeVenteWithInfoBL(bonLivraisonValue.getRefCommande(),bonLivraisonValue);
			
		}

		// System.out.println("Uniteeeeee:::::::::::"+bonLivraisonValue.getListDetLivraisonVente().get(0).getUnite());
		return bonLivraisonPersistance.createBonLivraison(bonLivraisonValue);

	}

	private void updateCommandeVenteWithInfoBL(String refCommande, LivraisonVenteValue bonLivraisonValue) {
		
		
		
		CommandeVenteValue commandeVente = bonCommandePersistance.getByReference(refCommande);
		
		
		if(commandeVente != null) {
			
			for(ProduitCommandeValue cm : commandeVente.getListProduitCommandes()) {
				
				cm.setQuantiteLivree(getQteLivree(cm.getProduitId(),bonLivraisonValue.getListDetLivraisonVente()));
			}
			
			
			bonCommandePersistance.update(commandeVente);
		}
		
	}

	private Double getQteLivree(Long produitId, List<DetLivraisonVenteValue> listDetLivraisonVente) {
		
		for(DetLivraisonVenteValue det : listDetLivraisonVente) {
			
			if(det.getProduitId().equals(produitId))
				return  det.getQuantite();
			
		}
		
		
		return null;
	}

	@Override
	public List<LivraisonVenteFactureVue> getAllListBonLivraisonRefByClient(Long idClient) {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<LivraisonVenteFactureVue> bonLivraisonlist = bonLivraisonPersistance.getListBLByClientId(idClient);

		List<LivraisonVenteFactureVue> result = new ArrayList<LivraisonVenteFactureVue>();

		for (LivraisonVenteFactureVue livraison : bonLivraisonlist) {

			if (livraison.getMarcheId() != null) {
				MarcheValue marche = marcheDomaine.getById(livraison.getMarcheId());
				livraison.setMarche(marche.getDesignation());
			}

			if (!listBonLivraisonToRemove.contains(livraison.getReference()))
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

	@Override
	public List<LivraisonVenteVue> getListBonLivraisonRefByClientAndDeclare(Long idClient) {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();
		
		//List<FactureVenteValue> factureVenteList = facturePersistance.getAll();


		List<FactureVenteValue> factureVenteList = facturePersistance.getAllInfoLivraisonByClientIdAndType(idClient,IConstanteCommerciale.FACTURE_TYPE_NORMAL);

		for (FactureVenteValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<LivraisonVenteVue> bonLivraisonlist = bonLivraisonPersistance.getReferenceBLByClientIdAndDeclare(idClient);
		
		List<LivraisonVenteVue> bonLivraisonlistFinal = new ArrayList<>();
		
		for(LivraisonVenteVue liv :bonLivraisonlist ) {
			
			if(!listBonLivraisonToRemove.contains(liv.getReferenceBL()))
				bonLivraisonlistFinal.add(liv);
		}

		
		return bonLivraisonlistFinal;
		
		//bonLivraisonlist.removeAll(listBonLivraisonToRemove);

		//return bonLivraisonlist;
	}

	@Override
	public String getCurrentReference(Calendar instance, boolean increment) {
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroBL = currentGuichetAnnuel.getNumReferenceBonLivraisonCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBL = new StringBuilder("");

		if (currentGuichetAnnuel.getPrefixeBL() != null)
			vNumBL.append(currentGuichetAnnuel.getPrefixeBL());

		if (numeroBL > 0 && numeroBL < 10) {
			vNumBL.append("000");
		} else if (numeroBL >= 10 && numeroBL < 100) {
			vNumBL.append("00");
		}

		else if (numeroBL >= 100 && numeroBL < 1000) {
			vNumBL.append("0");
		}

		vNumBL.append(numeroBL);

		currentGuichetAnnuel.setNumReferenceBonLivraisonCourante(new Long(numeroBL + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBLAnnuel(currentGuichetAnnuel);

		return vNumBL.toString();
	}

	@Override
	public ListProduitElementValue getProduitElementListForPassager(ValiderBLPassagerValue request) {


    	//List<LivraisonVenteValue> listLivraisonVenteValue = bonLivraisonPersistance.getProduitElementList(refBonLivraisonList);
				
		
		RechercheMulticritereBonLivraisonValue requestRechMC = new RechercheMulticritereBonLivraisonValue();
		
		requestRechMC.setPartieIntId(request.getPiId());
		requestRechMC.setDateLivraison(request.getDate());
		
		Set<LivraisonVenteValue> listLivraisonVenteValue = bonLivraisonPersistance.rechercherMultiCritereComplet(requestRechMC).getList();
	

		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listLivraisonVenteValue.size() > 0) {
			LivraisonVenteValue liv = listLivraisonVenteValue.iterator().next();
			
			listProduitElementValue.setPartieIntId(liv.getPartieIntId());
			listProduitElementValue.setDateLivrison(liv.getDate());
			listProduitElementValue.setIdMarche(liv.getMarcheId());
			
		//	listProduitElementValue.setPartieIntId(listLivraisonVenteValue.get(FIRST_INDEX).getPartieIntId());
		//	listProduitElementValue.setDateLivrison(listLivraisonVenteValue.get(FIRST_INDEX).getDate());
			// Added By Ghazi on 25/05/2018
		//	listProduitElementValue.setIdMarche(listLivraisonVenteValue.get(FIRST_INDEX).getMarcheId());

		}

		for (LivraisonVenteValue livraisonVente : listLivraisonVenteValue) {

			for (DetLivraisonVenteValue detLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<Long, String>, List<DetLivraisonVenteValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<DetLivraisonVenteValue>>();

		for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
			Long produitKey = detail.getProduitId();
			String choixKey = detail.getChoix();

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<DetLivraisonVenteValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ProduitValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<DetLivraisonVenteValue>> pair = (Map.Entry<Map<Long, String>, List<DetLivraisonVenteValue>>) it
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
			element.setFactureVenteId(request.getFactureId());
			// el
			Double sommeQuantite = ZERO;
			Long sommeNombreColis = ZERO_LONG;

			String numeroSeries = "";
			
			Double moyenneRemise = ZERO;
			Double moyennePrixUnitaire= ZERO;

			for (DetLivraisonVenteValue detail : pair.getValue()) {
				
				//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
				element.setFicheId(detail.getFicheId());

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
				
				
				//calucule moyenne remise et moyenne prix uitaire
				
				if(detail.getRemise() != null) moyenneRemise += detail.getRemise();
					
				if(detail.getPrixUnitaireHT() != null) moyennePrixUnitaire += detail.getPrixUnitaireHT();
				
				
				

			}
			
			int nbr = pair.getValue().size();
			
			element.setRemise(moyenneRemise / nbr );
			element.setPrixUnitaireHT(moyennePrixUnitaire / nbr);
			

			if (numeroSeries.length() > 0 && numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
				numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
				element.setNumeroSeries(numeroSeries);
				element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(numeroSeries,element.getProduitId()));
			}

			// TODO ENRICHIR WITH INFO PROD SERIALISABLE

			element.setQuantite(sommeQuantite);
			element.setNombreColis(sommeNombreColis);

			if (pair.getValue().size() > 0) {

				DetLivraisonVenteValue detail = pair.getValue().get(0);
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
					if (request.getFactureId() != null) {

						DetFactureVenteValue detFactureVenteValue = detFactureVentePersistance
								.getByFactureVenteAndProduit(request.getFactureId(), element.getProduitId(),
										element.getChoix());

						if (detFactureVenteValue != null) {

							element.setDescription(detFactureVenteValue.getDescription());

							boolean detailIdNotExist = true;
							for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
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
		
			//Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparator());
			//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
			Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparatorByOrdre());
			
		}

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetFactureVente(listDetFactureVente);

		return listProduitElementValue;
	}

	@Override
	public String getCurrentReferenceByType(String type, Calendar instance, boolean b) {
		
		
		if(type.equals("declarer"))
			
		{
			
			return getCurrentReference(instance,b);
			
		}else
			
			
		{
			
			return getCurrentReferenceNonDeclare(instance,b);
			
		}
		

	}

	private String getCurrentReferenceNonDeclare(Calendar instance, boolean increment) {
		
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroBL = currentGuichetAnnuel.getNumReferenceBonLivraisonNDCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBL = new StringBuilder("");

		if (currentGuichetAnnuel.getPrefixeBLND() != null)
			vNumBL.append(currentGuichetAnnuel.getPrefixeBLND());

		if (numeroBL > 0 && numeroBL < 10) {
			vNumBL.append("000");
		} else if (numeroBL >= 10 && numeroBL < 100) {
			vNumBL.append("00");
		}

		else if (numeroBL >= 100 && numeroBL < 1000) {
			vNumBL.append("0");
		}

		vNumBL.append(numeroBL);

		currentGuichetAnnuel.setNumReferenceBonLivraisonNDCourante(new Long(numeroBL + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBLNDAnnuel(currentGuichetAnnuel);

		return vNumBL.toString();
	}

	@Override
	public ListProduitElementValue getProduitElementListByOF(List<String> refBonLivraisonList, Long factureVenteId) {


		List<LivraisonVenteValue> listLivraisonVenteValue = bonLivraisonPersistance
				.getProduitElementList(refBonLivraisonList);

		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listLivraisonVenteValue.size() > 0) {
			listProduitElementValue.setPartieIntId(listLivraisonVenteValue.get(FIRST_INDEX).getPartieIntId());
			listProduitElementValue.setDateLivrison(listLivraisonVenteValue.get(FIRST_INDEX).getDate());
			// Added By Ghazi on 25/05/2018
			listProduitElementValue.setIdMarche(listLivraisonVenteValue.get(FIRST_INDEX).getMarcheId());
			listProduitElementValue.setIdDepot(listLivraisonVenteValue.get(FIRST_INDEX).getIdDepot());

		}

		for (LivraisonVenteValue livraisonVente : listLivraisonVenteValue) {

			for (DetLivraisonVenteValue detLivraisonVente : livraisonVente.getListDetLivraisonVente()) {

				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<String, String>, List<DetLivraisonVenteValue>> mapDetLivraison = new HashMap<Map<String, String>, List<DetLivraisonVenteValue>>();

		for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
			//Long produitKey = detail.getProduitId();
			
			String produitKey = detail.getNumeroOF();
			String choixKey = detail.getChoix();

			Map<String, String> map = new HashMap<String, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<DetLivraisonVenteValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ProduitValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<String, String>, List<DetLivraisonVenteValue>> pair = (Map.Entry<Map<String, String>, List<DetLivraisonVenteValue>>) it
					.next();

			DetFactureVenteValue element = new DetFactureVenteValue();

			//Long produitId = null;
			String produitId = null;
			String choix = null;

			Map<String, String> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<String, String> produitIdchoixPair = (Map.Entry<String, String>) produitIdchoixIt.next();
			produitId = produitIdchoixPair.getKey();
			choix = produitIdchoixPair.getValue();

			//element.setProduitId(produitId);
			
			element.setNumeroOF(produitId);
			element.setChoix(choix);
			element.setFactureVenteId(factureVenteId);
			// el
			Double sommeQuantite = ZERO;
			Long sommeNombreColis = ZERO_LONG;

			String numeroSeries = "";
			
			List<String> refMiseList = new ArrayList<String>();

			for (DetLivraisonVenteValue detail : pair.getValue()) {
				
				//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
				element.setFicheId(detail.getFicheId());

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
				
				
			/*	if(detail.getNumeroOF() != null)
					
					{
					
				
					String[] arrayString =  StringUtils.split(detail.getNumeroOF(), ", ") ;
					List<String> ofList =new ArrayList<String>(Arrays.asList(arrayString)) ;
					
					for(String ch : ofList) {
						
						if( !refMiseList.contains(ch)) 
                         refMiseList.add(detail.getNumeroOF());

					}
			

					}
			*/
				

				// TODO ENRICHIR WITH INFO PROD SERIALISABLE
				// TODO REMPLIR LIST PRODSERIALISABLE

			}
			
		/*	if(refMiseList.size() > 0) {
				
				
				  element.setNumeroOF(StringUtils.join(refMiseList, ", "));

			}
			*/

			if (numeroSeries.length() > 0 && numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
				numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
				element.setNumeroSeries(numeroSeries);
				element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(numeroSeries,element.getProduitId()));
			}

			// TODO ENRICHIR WITH INFO PROD SERIALISABLE

			element.setQuantite(sommeQuantite);
			element.setNombreColis(sommeNombreColis);

			if (pair.getValue().size() > 0) {

				DetLivraisonVenteValue detail = pair.getValue().get(0);
				if (detail != null) {
					element.setUnite(detail.getUnite());
					element.setRemise(detail.getRemise());
					// TODO changed
					element.setPrixUnitaireHT(detail.getPrixUnitaireHT());
					
					
					element.setProduitId(detail.getProduitId());
				}
			}

			if (element.getProduitId() != null) {
				produitValue = produitService.rechercheProduitById(element.getProduitId());
				if (produitValue != null) {

					element.setSerialisable(produitValue.isSerialisable());
					if (factureVenteId != null) {

					/*	DetFactureVenteValue detFactureVenteValue = detFactureVentePersistance
								.getByFactureVenteAndProduit(factureVenteId, element.getProduitId(),
										element.getChoix());
						*/
						
						DetFactureVenteValue detFactureVenteValue = detFactureVentePersistance
								.getByFactureVenteAndNumeroOF(factureVenteId, element.getNumeroOF(),
										element.getChoix());
						
						
						
						
						

						if (detFactureVenteValue != null) {
							
							//element.setFactureVenteId(detFactureVenteValue.getId());

							element.setDescription(detFactureVenteValue.getDescription());

							boolean detailIdNotExist = true;
						/*	for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
								if (detail.getId() == detFactureVenteValue.getId())
									detailIdNotExist = false;
							}
							*/

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
		
			//Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparator());
			//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
			Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparatorByOrdre());
			
		}

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetFactureVente(listDetFactureVente);

		return listProduitElementValue;
	}

}
