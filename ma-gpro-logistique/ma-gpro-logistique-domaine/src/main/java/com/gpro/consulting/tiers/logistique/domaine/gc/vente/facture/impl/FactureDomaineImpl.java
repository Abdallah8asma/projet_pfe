package com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reglement.value.ElementReglementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.RechercheMulticritereFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ResultatRechecheFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.TaxeFactureValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.IReceptionAchatDomaineGC;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.IFactureDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.vente.facture.utilities.DetFactureVenteFaconComparator;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reglement.IElementReglementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.ITaxeFacturePersistance;

/**
 * Implementation of {@link IFactureDomaine}
 * 
 * @author Wahid Gazzah
 * @since 29/02/2016
 *
 */

@Component
public class FactureDomaineImpl implements IFactureDomaine {

	private static final Logger logger = LoggerFactory.getLogger(FactureDomaineImpl.class);

	private static final String SEPARATOR = "-";
	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 4L;
	private static final Long TAXE_ID_TIMBRE = 3L;

	private static final String FACTURE_TYPE_AVOIRE = "Avoir";

	private static final String TYPE_DECLARER = "declarer";
	@Autowired
	private IFacturePersistance facturePersistance;

	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private ITaxeFacturePersistance taxeFacturePersistance;

	@Autowired
	private IChoixRouleauPersistance choixRouleauPersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IDetFactureVentePersistance detFactureVentePersistance;

	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	@Autowired
	private IFicheFaconDomaine ficheFaconDomaine;

	@Autowired
	private IMiseDomaine miseDomaine;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	private IProduitSerialisablePersistance produitSerialisablePersistance;

	@Autowired
	private IPrixClientPersistance prixClientPersistance;

	
	@Autowired
	private IElementReglementPersistance elementReglementPersistance;
	
	
	@Autowired
	private IReceptionAchatDomaineGC receptionAchatDomaine;
	
	@Override
	public ResultatRechecheFactureValue rechercherMultiCritere(RechercheMulticritereFactureValue request) {

		/*
		 * if(request.getDateFactureMin() != null) {
		 * 
		 * Calendar date = Calendar.getInstance(); date.set(Calendar.YEAR, 2020);
		 * date.set(Calendar.MONTH, 0); date.set(Calendar.DAY_OF_MONTH, 1);
		 * 
		 * date.set(Calendar.HOUR, 0); date.set(Calendar.MINUTE, 0);
		 * date.set(Calendar.MILLISECOND, 0);
		 * 
		 * request.setDateFactureMin(date); }
		 * 
		 */

		return facturePersistance.rechercherMultiCritere(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createFacture(FactureVenteValue factureValue) {

		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;

		Double assietteFodec = ZERO;
		Double assietteTVA = ZERO;

		Double montantHTaxeTotal = ZERO;
		Double montantTaxesTotal = ZERO;

		Double montantRemiseTotal = ZERO;
		Double metrageTotal = ZERO;

		// String FACTURE_TYPE_AVOIRE = "Avoir";
		boolean modeRemiseEstTotal = false;
		
		
		/*** Debut Verififcation Facture avant Creation  **/
		
		String codeError = verifierFactureAvantCreation(factureValue);
		
		if(codeError != null)
			return codeError;
		
		factureValue.setDateIntroduction(Calendar.getInstance());
		
		
		if(factureValue.getDeclarer() == null) {
			factureValue.setDeclarer(true);
		}

		
		if (((factureValue.getReference() != null && factureValue.getReference().equals(""))
				|| factureValue.getReference() == null)) {
			if (factureValue.getType() != null) {
         
				factureValue.setReference(getCurrentReferenceByTypeFactureAndDeclarer(factureValue.getType(),factureValue.getDeclarer(), factureValue.getDate(), true));
       
               
               /*
				 * if (factureValue.getType().equalsIgnoreCase(FACTURE_TYPE_AVOIRE)) {
				 * factureValue.setReference(this.getNumeroAvoir(factureValue.getDate())); }
				 * else {
				 * factureValue.setReference(this.getNumeroFacture(factureValue.getDate())); }
				 */

			}
		} else

		{
			if (factureValue.getRefAvantChangement() != null
					&& factureValue.getReference().equals(factureValue.getRefAvantChangement())) {
				this.getCurrentReferenceByTypeFactureAndDeclarer(factureValue.getType(),factureValue.getDeclarer(), factureValue.getDate(), true);
			}

		}
		
		
		if(factureValue.getType().equals(FACTURE_TYPE_AVOIRE)) {
			
			factureValue.setDeclarer(true);
		}

		if (factureValue.getGroupeClientId() == null && factureValue.getPartieIntId() != null) {
			PartieInteresseValue pi = partieInteresseePersistance.getById(factureValue.getPartieIntId());
			factureValue.setGroupeClientId(pi.getGroupeClientId());
			factureValue.setTypePartieInteressee(pi.getTypePartieInteressee());
		}

		if (factureValue.getInfoLivraison() != null && estNonVide(factureValue.getInfoLivraison())) {
			String infoSortieSplitted[] = factureValue.getInfoLivraison().split(SEPARATOR);

			String firstRefBonSortie = infoSortieSplitted[FIRST_INDEX];

			List<String> refBonSortieList = new ArrayList<String>();
			refBonSortieList.add(firstRefBonSortie);

			List<BonSortieFiniValue> bonSortieFiniList = bonSortieFiniPersistance
					.getListByBonSortieList(refBonSortieList);

			if (bonSortieFiniList != null) {
				if (bonSortieFiniList.size() > 0) {
					factureValue.setPartieIntId(bonSortieFiniList.get(FIRST_INDEX).getPartieInt());
				}
			}
		}

		if (factureValue.getTotalPourcentageRemise() != null && factureValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}
		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (DetFactureVenteValue detFactureVente : factureValue.getListDetFactureVente()) {

			if (detFactureVente.getQuantite() != null & detFactureVente.getPrixUnitaireHT() != null) {
				if (detFactureVente.getPrixUnitaireHT() != null) {
					detFactureVente.setPrixTotalHT(detFactureVente.getQuantite() * detFactureVente.getPrixUnitaireHT());
				}
				// MAJ Qte Produit
				if (detFactureVente.getProduitId() != null) {

					ProduitValue produitValue = produitPersistance.rechercheProduitById(detFactureVente.getProduitId());
					if (produitValue.isSerialisable())
						detFactureVente.setSerialisable(true);
					
					detFactureVente.setTaxeId(produitValue.getIdTaxe());
					

					Double qte = (produitValue.getQuantite() - detFactureVente.getQuantite());
					produitValue.setQuantite(qte);

					//Calcul TVA
					
					//TODO GASH -10092020
	                // Modification - Introduire Remise dans Assiette de TVA
					// Verification Round 
					
					if (detFactureVente.getRemise()==null)
					detFactureVente.setRemise(0D);
					Double totalApresRemise=detFactureVente.getPrixTotalHT()*(1-detFactureVente.getRemise()/100);
					
					
					
					
					
					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
						produitTaxeMap.put(produitValue.getIdTaxe(), totalApresRemise);

					} else {
						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())+totalApresRemise;
						produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

					}
					produitPersistance.modifierProduit(produitValue);

					if (detFactureVente.isSerialisable() && detFactureVente.getProduitsSerialisable() != null) {
						String numeroSeries = "";

						for (ProduitSerialisableValue ps : detFactureVente.getProduitsSerialisable()) {

							ps.setClientId(factureValue.getPartieIntId());
							ps.setDateFacture(factureValue.getDate());
							
						/*	if(factureValue.getType().equalsIgnoreCase(FACTURE_TYPE_AVOIRE)) {
								
								ps.setNumFactureAvoir(factureValue.getReference());
								ps.setFactureAvoirAncien(addReference(ps.getFactureAvoirAncien(),factureValue.getReference()));
								
							}else
								
							{
								ps.setNumFacture(factureValue.getReference());
								ps.setFactureAncien(addReference(ps.getFactureAncien(),factureValue.getReference()));
							
								
							}
							*/

							// TODO veifier prix
							// ps.setPrixVente(factureValue.getPrixTTC());

							numeroSeries += ps.getNumSerie();
							numeroSeries += "&";

							produitSerialisablePersistance.modifierProduitSerialisable(ps);

						}

						if (numeroSeries != null && numeroSeries.length() > 0
								&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
							numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
							detFactureVente.setNumeroSeries(numeroSeries);
						}

					}

				}
			}

			if (detFactureVente.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detFactureVente.getPrixTotalHT();
			}
			if (detFactureVente.getQuantite() != null && (factureValue.getNatureLivraison() != null
					&& !factureValue.getNatureLivraison().equals("FACON"))) {
				metrageTotal = metrageTotal + detFactureVente.getQuantite();
			}
			if (!modeRemiseEstTotal) {
				if (detFactureVente.getRemise() != null && detFactureVente.getPrixTotalHT() != null) {
					montantRemiseTotal += (detFactureVente.getPrixTotalHT() * detFactureVente.getRemise()) / 100;
				}
			}

			if (detFactureVente.getChoix() != null) {

				Long choix = null;
				try {
					choix = Long.parseLong(detFactureVente.getChoix());
				} catch (NumberFormatException e) {
					// logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detFactureVente.setChoix(choixRouleau.getDesignation());

				}
			}

		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * factureValue.getTotalPourcentageRemise()) / 100;
		}
		Map<Long, TaxeFactureValue> taxeFactureIdTaxeMap = new HashMap<Long, TaxeFactureValue>();
		for (TaxeFactureValue taxeFacture : factureValue.getListTaxeFacture()) {
			Long key = taxeFacture.getId();
			if (taxeFactureIdTaxeMap.get(key) == null) {
				taxeFactureIdTaxeMap.put(taxeFacture.getTaxeId(), taxeFacture);
			}

		}
		// Ajout by Ghazi Atroussi 16/11/2016
		montantHTaxeTotal = montantHTaxeTotal - montantRemiseTotal;
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() != null) {
				assietteFodec = montantHTaxeTotal;
				montantTaxeFodec = (assietteFodec * taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage()) / 100;

				montantTaxeFodec = 	(double)Math.round(montantTaxeFodec * 1000) / 1000 ;
				
				taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		// TVA variable selon produit

	/*	for (Long taxe : taxeFactureIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeFactureIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}
	*/	
		
		
		for (Long taxe : taxeFactureIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe)) {
					
					if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
						
						montantTaxeTVA = (produitTaxeMap.get(taxe) + produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() /100 )* taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}else 
						
					{
						
						montantTaxeTVA = produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}
					
					montantTaxeTVA = 	(double)Math.round(montantTaxeTVA * 1000) / 1000 ;
				}
					

				taxeFactureIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}

		// modifier par samer le 01.04.20
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE) && !factureValue.getType().equals(FACTURE_TYPE_AVOIRE)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		Iterator it = taxeFactureIdTaxeMap.entrySet().iterator();
		List<TaxeFactureValue> listTaxeFacture = new ArrayList<TaxeFactureValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeFactureValue> pair = (Map.Entry<Long, TaxeFactureValue>) it.next();

			listTaxeFacture.add(pair.getValue());

			it.remove();
		}

		factureValue.setListTaxeFacture(listTaxeFacture);

		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		if(!factureValue.isForcerCalculMontant()) {
			
			montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
			montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
			montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
			metrageTotal = 	(double)Math.round(metrageTotal * 1000) / 1000 ;
			montantTTCTotal = 	(double)Math.round(montantTTCTotal * 1000) / 1000 ;
			
			
			factureValue.setMontantHTaxe(montantHTaxeTotal);
			factureValue.setMontantRemise(montantRemiseTotal);
			factureValue.setMontantTaxe(montantTaxesTotal);
			// factureValue.setMontantTTC(montantTTCTotal);
			factureValue.setMetrageTotal(metrageTotal);

			/***
			 * Si Client .type exonoré Alors TVA=0 au niveau de Facture et Bon de livraison
			 ***/
			// PartieInteresseValue pi =
			// partieInteresseePersistance.getPartieInteresseById(factureValue.getPartieIntId());

			if (factureValue.getTypePartieInteressee() != null
					&& factureValue.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {

				factureValue.setMontantTTC(montantHTaxeTotal);

			} else {
				factureValue.setMontantTTC(montantTTCTotal);
				
			}
			
		}
	

		
      if(factureValue.getTauxConversion()!=null)
			factureValue.setMontantConverti(factureValue.getTauxConversion()*montantTTCTotal);
      else
    	  factureValue.setMontantConverti(ZERO);
		
		
		String factureId =  facturePersistance.createFacture(factureValue);
		
		
		
		if(factureValue.getType().equals(FACTURE_TYPE_AVOIRE) && factureValue.isAvecRetourStock())
			  createReceptionRetourFromFacture(factureValue);
			   
			
			
			
		return factureId;
		
	}

		private void createReceptionRetourFromFacture(FactureVenteValue factureValue) {
			
			
			ReceptionAchatValue recpetionRetour = receptionAchatDomaine.validerFactureAvoirRetour(factureValue.getReference());
			
			recpetionRetour.setType(IConstanteCommerciale.RECEPTION_ACHAT_TYPE_RETOUR);
			
			
			recpetionRetour.setReference(receptionAchatDomaine.getCurrentReference(Calendar.getInstance(), true));

			
			receptionAchatDomaine.create(recpetionRetour);
		
	}

		private String verifierFactureAvantCreation(FactureVenteValue factureValue) {
		
			
			
			//Debut Test Existence Facture avec le meme BL 
			if(factureValue.getType().equals("Normal") ) {
				
				
				if(factureValue.getInfoLivraison() != null && facturePersistance.existeBLexacte(factureValue.getInfoLivraison()))
					return IConstanteCommerciale.CODE_ERROR_DUPLICATE_BL_IN_FACTURE;
				
				
			}
			
			
			
		return null;
	}

		private String addReference(String blAncien, String numBonLivraison) {
		
		if(blAncien == null) {
			
			return numBonLivraison;
		}
		else {
			
			String ch = blAncien + "&"+ numBonLivraison;
			
			return ch;
		}
			
	}

	@Override
	public FactureVenteValue getFactureById(Long id) {

		FactureVenteValue factureVenteValue = facturePersistance.getFactureById(id);

		if (factureVenteValue.getListDetFactureVente() != null) {

			Collections.sort(factureVenteValue.getListDetFactureVente(), new DetFactureVenteComparator());

//			if(factureVenteValue.getNatureLivraison().equals("FINI")){
//				Collections.sort(factureVenteValue.getListDetFactureVente(), new DetFactureVenteComparator());
//			}else{ //FACON
//				
//					for (DetFactureVenteValue detFactureVenteValue : factureVenteValue.getListDetFactureVente()) {
//						if(detFactureVenteValue.getFicheId()!=null){
//							FicheFaconValue ficheFacon =  ficheFaconDomaine.getById(detFactureVenteValue.getFicheId());
//							detFactureVenteValue.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
//						}
//					}
//				Collections.sort(factureVenteValue.getListDetFactureVente(), new DetFactureVenteFaconComparator());
//			}
		}
		return factureVenteValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateFacture(FactureVenteValue factureValue) {
		
		
		if (factureValue.getReference() != null && factureValue.getRefAvantChangement() != null
				&& factureValue.getType() != null

				&& !factureValue.getReference().equals(factureValue.getRefAvantChangement())) {

			getCurrentReferenceByTypeFactureAndDeclarer(factureValue.getType(),factureValue.getDeclarer(), factureValue.getDate(), true);
					
		}

		String FACTURE_TYPE_AVOIRE = "Avoir";

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

		if (factureValue.getInfoLivraison() != null && estNonVide(factureValue.getInfoLivraison())) {
			String infoSortieSplitted[] = factureValue.getInfoLivraison().split(SEPARATOR);

			String firstRefBonSortie = infoSortieSplitted[FIRST_INDEX];

			List<String> refBonSortieList = new ArrayList<String>();
			refBonSortieList.add(firstRefBonSortie);

			List<BonSortieFiniValue> bonSortieFiniList = bonSortieFiniPersistance
					.getListByBonSortieList(refBonSortieList);

			if (bonSortieFiniList != null) {
				if (bonSortieFiniList.size() > 0) {
					factureValue.setPartieIntId(bonSortieFiniList.get(FIRST_INDEX).getPartieInt());
				}
			}
		}

		if (factureValue.getGroupeClientId() == null && factureValue.getPartieIntId() != null)
			factureValue.setGroupeClientId(
					partieInteresseePersistance.getById(factureValue.getPartieIntId()).getGroupeClientId());

		if (factureValue.getTotalPourcentageRemise() != null && factureValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}
		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (DetFactureVenteValue detFactureVente : factureValue.getListDetFactureVente()) {

			if (detFactureVente.getQuantite() != null & detFactureVente.getPrixUnitaireHT() != null) {
				detFactureVente.setPrixTotalHT(detFactureVente.getQuantite() * detFactureVente.getPrixUnitaireHT());
			}

			if (detFactureVente.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detFactureVente.getPrixTotalHT();
			}
			if (detFactureVente.getQuantite() != null && (factureValue.getNatureLivraison() != null
					&& !factureValue.getNatureLivraison().equals("FACON"))) {
				metrageTotal = metrageTotal + detFactureVente.getQuantite();
			}
			if (!modeRemiseEstTotal) {
				if (detFactureVente.getRemise() != null && detFactureVente.getPrixTotalHT() != null) {
					montantRemiseTotal += (detFactureVente.getPrixTotalHT() * detFactureVente.getRemise()) / 100;
				}
			}

			if (detFactureVente.getChoix() != null) {

				Long choix = null;
				try {
					choix = Long.parseLong(detFactureVente.getChoix());
				} catch (NumberFormatException e) {
					// logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detFactureVente.setChoix(choixRouleau.getDesignation());
				}
			}

			
			// Calcul TVA Selon Produit 
			
			
			//Calcul TVA
			
			//TODO GASH -10092020
            // Modification - Introduire Remise dans Assiette de TVA
			// Verification Round 
			
			if (detFactureVente.getProduitId() != null) {

				ProduitValue produitValue = produitPersistance.rechercheProduitById(detFactureVente.getProduitId());
				
				detFactureVente.setTaxeId(produitValue.getIdTaxe());
				
			
			if (detFactureVente.getRemise()==null)
			detFactureVente.setRemise(0D);
			
			if(detFactureVente.getPrixTotalHT() == null)
				detFactureVente.setPrixTotalHT(ZERO);
			
			Double totalApresRemise=detFactureVente.getPrixTotalHT()*(1-detFactureVente.getRemise()/100);
			
			
			
			
			
			if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
				produitTaxeMap.put(produitValue.getIdTaxe(), totalApresRemise);

			} else {
				Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())+totalApresRemise;
				produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

			}

			}
			
			
			
			
			
			
		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * factureValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeFactureValue> taxeFactureIdTaxeMap = new HashMap<Long, TaxeFactureValue>();
		for (TaxeFactureValue taxeFacture : factureValue.getListTaxeFacture()) {
			Long key = taxeFacture.getId();
			if (taxeFactureIdTaxeMap.get(key) == null) {
				taxeFactureIdTaxeMap.put(taxeFacture.getTaxeId(), taxeFacture);
			}

		}
		// Ajout by Ghazi Atroussi 16/11/2016
		montantHTaxeTotal = montantHTaxeTotal - montantRemiseTotal;
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() != null) {
				assietteFodec = montantHTaxeTotal;
				montantTaxeFodec = (assietteFodec * taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage()) / 100;
				montantTaxeFodec = 	(double)Math.round(montantTaxeFodec * 1000) / 1000 ;
				taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

	/*	for (Long taxe : taxeFactureIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeFactureIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}
		*/
		
		
		
		for (Long taxe : taxeFactureIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				if (produitTaxeMap.containsKey(taxe)) {
					
					if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_FODEC)) {
						
						montantTaxeTVA = (produitTaxeMap.get(taxe) + produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).getPourcentage() /100 )* taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}else 
						
					{
						
						montantTaxeTVA = produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;
						
					}
					
					montantTaxeTVA = 	(double)Math.round(montantTaxeTVA * 1000) / 1000 ;
				}
					

				taxeFactureIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}
		

		// modifier par samer le 01.04.20 afin de ne pas calculer le taxe de timbre dans
		// les factures Avoir
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE) && !factureValue.getType().equals(FACTURE_TYPE_AVOIRE)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		List<TaxeFactureValue> listTaxeFacture = new ArrayList<TaxeFactureValue>();
		Iterator it = taxeFactureIdTaxeMap.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<Long, TaxeFactureValue> pair = (Map.Entry<Long, TaxeFactureValue>) it.next();

			listTaxeFacture.add(pair.getValue());

			it.remove();
		}

		factureValue.setListTaxeFacture(listTaxeFacture);

		Double montantTTC = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;
		
		if(!factureValue.isForcerCalculMontant()) {
			
			montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
			montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
			montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
			metrageTotal = 	(double)Math.round(metrageTotal * 1000) / 1000 ;
			montantTTC = 	(double)Math.round(montantTTC * 1000) / 1000 ;
			

			factureValue.setMontantHTaxe(montantHTaxeTotal);
			factureValue.setMontantRemise(montantRemiseTotal);
			factureValue.setMontantTaxe(montantTaxesTotal);
			factureValue.setMontantTTC(montantTTC);
			factureValue.setMetrageTotal(metrageTotal);
			
			/***
			 * Si Client .type exonoré Alors TVA=0 au niveau de Facture et Bon de livraison
			 ***/
			// PartieInteresseValue pi =
			// partieInteresseePersistance.getPartieInteresseById(factureValue.getPartieIntId());

			if (factureValue.getTypePartieInteressee() != null
					&& factureValue.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {

				factureValue.setMontantTTC(montantHTaxeTotal);

			} else {
				factureValue.setMontantTTC(montantTTC);
			}

			
		}

		
	
		if(factureValue.getTauxConversion() != null && montantTTC != null )
		factureValue.setMontantConverti(factureValue.getTauxConversion()*montantTTC);
		
		
	

		return facturePersistance.updateFacture(factureValue);
	}

	@Override
	public void deleteFacture(Long id) {
		
		
		FactureVenteValue facture = facturePersistance.getFactureById(id);

		facturePersistance.deleteFacture(id);
		
		deleteElementReglementByRefFacture(facture.getReference());
		
	}
	
	
	
	
	private void deleteElementReglementByRefFacture(String reference) {
		 List<ElementReglementValue> list = elementReglementPersistance.getByRefFactureExact(reference);
		 
		 for(ElementReglementValue element : list) {
			 
			 elementReglementPersistance.deleteElementReglementById(element.getId());
			 
		 }
		
	}

	@Override
	public FactureVenteValue getFactureByReference(String reference) {

		return facturePersistance.getFactureByReference(reference);
	}

	@Override
	public List<FactureVenteVue> getListFactureRefByClient(Long clientId) {

		List<FactureVenteVue> facturelist = facturePersistance.getRefFactureByClientId(clientId);

		return facturelist;
	}

	@Override
	public List<FactureVenteVue> getListFactureRefByClientAll() {

		List<FactureVenteVue> facturelist = facturePersistance.getRefFactureByClientIdAll();

		return facturelist;
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refFactureList, Long factureVenteId,
			Long clientId) {

		List<FactureVenteValue> listFactureVenteValue = facturePersistance.getProduitElementList(refFactureList);
		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();
		
		
		if(listFactureVenteValue.size() > 0)
			listProduitElementValue.setIdDepot(listFactureVenteValue.get(0).getIdDepot());

		for (FactureVenteValue factureVente : listFactureVenteValue) {

			for (DetFactureVenteValue detFactureVente : factureVente.getListDetFactureVente()) {

				if (detFactureVente.getProduitId() != null) {
					ProduitValue produit = produitPersistance.rechercheProduitById(detFactureVente.getProduitId());
					if (produit != null) {
						detFactureVente.setProduitDesignation(produit.getDesignation());
						detFactureVente.setProduitReference(produit.getReference());
						
						
						//detFactureVente.setPrixUnitaireHT(produit.getPrixUnitaire());
						//detFactureVente.setPrixTotalHT(produit.getPrixUnitaire() * detFactureVente.getQuantite());

						if (clientId != null) {

							PrixClientValue resRechPrixProduit = prixClientPersistance.rechcherchePrixClientMC(
									new RecherchePrixClientValue(clientId, detFactureVente.getProduitId()));

							if (resRechPrixProduit != null && resRechPrixProduit.getPrixvente() != null) {

								detFactureVente.setPrixUnitaireHT(resRechPrixProduit.getPrixvente());

								detFactureVente.setPrixTotalHT(
										resRechPrixProduit.getPrixvente() * detFactureVente.getQuantite());
							}

						}

					}
				}

				if (factureVenteId != null) {
					DetFactureVenteValue detFactureVenteValue = detFactureVentePersistance.getByFactureVenteAndProduit(
							factureVenteId, detFactureVente.getProduitId(), detFactureVente.getChoix());

					if (detFactureVenteValue != null) {

						boolean detailIdNotExist = true;
						for (DetFactureVenteValue detail : listDetFactureVente) {
							if (detail.getId() == detFactureVenteValue.getId())
								detailIdNotExist = false;
						}

						if (detailIdNotExist) {
							detFactureVente.setId(detFactureVenteValue.getId());
							detFactureVente.setRemise(detFactureVenteValue.getRemise());
							detFactureVente.setQuantite(detFactureVenteValue.getQuantite());

							detFactureVente.setPrixUnitaireHT(detFactureVenteValue.getPrixUnitaireHT());

							detFactureVente.setPrixTotalHT(
									detFactureVenteValue.getPrixUnitaireHT() * detFactureVente.getQuantite());
						}

					}
				}

				listDetFactureVente.add(detFactureVente);
			}
		}

		listProduitElementValue.setListDetFactureVente(listDetFactureVente);

		return listProduitElementValue;

	}

	private void updateProduitSerialisable(Long id) {
		FactureVenteValue livraisonVente = facturePersistance.getFactureById(id);

		for (DetFactureVenteValue detailLivraisonVente : livraisonVente.getListDetFactureVente()) {

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

	/**
	 * Récuperer le numéro de la facture à partir du Guichet facture
	 * 
	 * Le Numéro de la facture est unique pour une facture OSIRIS et doit respect le
	 * format:
	 * 
	 * - AAAA : année du Bon Reception. - NNNNNN : un numéro incrémental au sein de
	 * l'année civile.
	 * 
	 */

	/*
	 * private String getNumeroFacture(final Calendar pDateBonFacture) {
	 * 
	 * Long vNumGuichetFacture =
	 * this.guichetAnnuelDomaine.getNextNumFactureReference(); //** Année courante.
	 * int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR); //** Format du
	 * numero de la Bon Reception= AAAA-NN. StringBuilder vNumFacture = new
	 * StringBuilder(""); vNumFacture.append(vAnneeCourante); //TODO //
	 * if(vNumGuichetFacture<100) // vNumFacture.append("0"); vNumFacture.append(
	 * vNumGuichetFacture); //** Inserer une nouvelle valeur dans Guichet
	 * BonReception. GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
	 * 
	 * 
	 * Calendar cal = Calendar.getInstance(); int anneActuelle =
	 * cal.get(Calendar.YEAR);
	 * 
	 * int idAnnuel = (anneActuelle - 2016) +1;
	 * 
	 * vGuichetValeur.setId(new Long(idAnnuel)); vGuichetValeur.setAnnee(new
	 * Long(vAnneeCourante)); vGuichetValeur.setNumReferenceFactureCourant(new Long(
	 * vNumGuichetFacture + 1L)); //** Modification de la valeur en base du numéro.
	 * this.guichetAnnuelDomaine .modifierGuichetFactureAnnuel(vGuichetValeur);
	 * return vNumFacture.toString(); }
	 */

	private String getNumeroFacture(final Calendar pDateBonFacture, boolean increment) {

		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroFacture = currentGuichetAnnuel.getNumReferenceFactureCourant();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");

		vNumFacture.append(currentGuichetAnnuel.getPrefixeFacture());

		if (numeroFacture > 0 && numeroFacture < 10) {
			vNumFacture.append("000");
		} else if (numeroFacture >= 10 && numeroFacture < 100) {
			vNumFacture.append("00");
		}

		else if (numeroFacture >= 100 && numeroFacture < 1000) {
			vNumFacture.append("0");
		}

		vNumFacture.append(numeroFacture);

		currentGuichetAnnuel.setNumReferenceFactureCourant(new Long(numeroFacture + 1L));

		/** Modification de la valeur en base du numéro. */
		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetFactureAnnuel(currentGuichetAnnuel);

		return vNumFacture.toString();
	}

	private String getNumeroAvoir(final Calendar pDateBonFacture, boolean increment) {

		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroAvoir = currentGuichetAnnuel.getNumReferenceAvoirCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		//int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");

		vNumFacture.append(currentGuichetAnnuel.getPrefixeAvoir());

		if (numeroAvoir > 0 && numeroAvoir < 10) {
			vNumFacture.append("000");
		} else if (numeroAvoir >= 10 && numeroAvoir < 100) {
			vNumFacture.append("00");
		}

		else if (numeroAvoir >= 100 && numeroAvoir < 1000) {
			vNumFacture.append("0");
		}

		vNumFacture.append(numeroAvoir);

		currentGuichetAnnuel.setNumReferenceAvoirCourante(new Long(numeroAvoir + 1L));

		/** Modification de la valeur en base du numéro. */
		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetAvoirAnnuel(currentGuichetAnnuel);

		return vNumFacture.toString();

	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@Override
	public ListProduitElementValue getArticleAvoir(Long clientId) {

		List<DetFactureVenteValue> listDetFactureVente = new ArrayList<DetFactureVenteValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		RechercheMulticritereProduitValue requetRechProduit = new RechercheMulticritereProduitValue();

		requetRechProduit.setRetour("oui");

		ResultatRechecheProduitValue reultat = produitPersistance.rechercherProduitMultiCritere(requetRechProduit);

		for (ProduitValue element : reultat.getProduitValues()) {

			DetFactureVenteValue detFactureVente = new DetFactureVenteValue();
			detFactureVente.setQuantite(1d);

			detFactureVente.setProduitId(element.getId());

			detFactureVente.setProduitDesignation(element.getDesignation());
			detFactureVente.setProduitReference(element.getReference());
			detFactureVente.setPrixUnitaireHT(element.getPrixUnitaire());
			detFactureVente.setPrixTotalHT(element.getPrixUnitaire() * detFactureVente.getQuantite());

			if (clientId != null) {

				PrixClientValue resRechPrixProduit = prixClientPersistance.rechcherchePrixClientMC(
						new RecherchePrixClientValue(clientId, detFactureVente.getProduitId()));

				if (resRechPrixProduit != null && resRechPrixProduit.getPrixvente() != null) {

					detFactureVente.setPrixUnitaireHT(resRechPrixProduit.getPrixvente());

					detFactureVente.setPrixTotalHT(resRechPrixProduit.getPrixvente() * detFactureVente.getQuantite());
				}

			}

			listDetFactureVente.add(detFactureVente);

		}

		listProduitElementValue.setListDetFactureVente(listDetFactureVente);

		return listProduitElementValue;
	}

	@Override
	public String getCurrentReference(String type, Calendar instance, boolean increment) {
		if (type.equals(FACTURE_TYPE_AVOIRE))

			return getNumeroAvoir(instance, increment);

		else
			
			return getNumeroFacture(instance, increment);
	}
	
	
	
	
	public String getCurrentReferenceNotDeclarer(String type, Calendar instance, boolean increment) {
		if (type.equals(FACTURE_TYPE_AVOIRE))

			return getNumeroAvoir(instance, increment);

		else
			return getNumeroFactureNotDeclarer(instance, increment);
		
		
			
	}
	

	private String getNumeroFactureNotDeclarer(Calendar instance, boolean increment) {


		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroFacture = currentGuichetAnnuel.getNumReferenceFactureNDCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");

		vNumFacture.append(currentGuichetAnnuel.getPrefixeFAND());

		if (numeroFacture > 0 && numeroFacture < 10) {
			vNumFacture.append("000");
		} else if (numeroFacture >= 10 && numeroFacture < 100) {
			vNumFacture.append("00");
		}

		else if (numeroFacture >= 100 && numeroFacture < 1000) {
			vNumFacture.append("0");
		}

		vNumFacture.append(numeroFacture);

		currentGuichetAnnuel.setNumReferenceFactureNDCourante(new Long(numeroFacture + 1L));

		/** Modification de la valeur en base du numéro. */
		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetFANDAnnuel(currentGuichetAnnuel);

		return vNumFacture.toString();
	}
	
	@Override
	public String getCurrentReferenceByTypeFactureAndDeclarer(String type, boolean declarer, Calendar instance,
			boolean increment) {
		
		
		
		if (declarer)
			return getCurrentReference(type, instance, increment) ;
		
		else
			return getCurrentReferenceNotDeclarer(type, instance, increment) ;
		
	
		
	}

	@Override
	public ResultatRechecheFactureValue rechercherMultiCritereAvecDetail(RechercheMulticritereFactureValue request) {
		// TODO Auto-generated method stub
		return facturePersistance.rechercherMultiCritereAvecDetail(request);
	}
	

	
	
	
}
