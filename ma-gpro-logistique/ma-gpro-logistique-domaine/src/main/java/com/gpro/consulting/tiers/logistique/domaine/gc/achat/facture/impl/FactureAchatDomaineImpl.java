package com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.impl;

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
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.PrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherchePrixClientValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IPrixClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ListProduitAchatElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.RechercheMulticritereFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.ResultatRechecheFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.TaxeFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.report.vente.facture.value.BLReportElementRecapValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.IFactureAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.utilities.DetFactureAchatComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonStockDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IDetFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IElementReglementAchatPersistance;

@Component
public class FactureAchatDomaineImpl implements IFactureAchatDomaine {

	private static final Logger logger = LoggerFactory.getLogger(FactureAchatDomaineImpl.class);

	private static final String SEPARATOR = "-";
	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TIMBRE = 3L;
	private static final Long TAXE_ID_TIMBRE_2 = 7L;
	private static final Long TAXE_ID_TIMBRE_3 = 8L;
	
	private static final String FACTURE_TYPE_AVOIRE = "Avoir";

	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;

	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private IChoixRouleauPersistance choixRouleauPersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IDetFactureAchatPersistance detFactureAchatPersistance;

	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;
	
	@Autowired
	private IPrixClientPersistance prixClientPersistance;
	
	@Autowired
	private IElementReglementAchatPersistance elementReglementAchatPersistance;
	
	@Autowired
	private IBonStockDomaine bonStockDomaine;
	
	@Autowired
	private IArticlePersistance articlePersistance;
	
	@Autowired
	private IGuichetMensuelDomaine guichetierMensuelDomaine;
	
	@Override
	public ResultatRechecheFactureAchatValue rechercherMultiCritere(RechercheMulticritereFactureAchatValue request) {

		return factureAchatPersistance.rechercherMultiCritere(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createFacture(FactureAchatValue factureValue) {

		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;
		Double montantTaxeTimbre2 = ZERO;
		Double montantTaxeTimbre3 = ZERO;

		Double assietteFodec = ZERO;

		Double montantHTaxeTotal = ZERO;
		Double montantTaxesTotal = ZERO;

		Double montantRemiseTotal = ZERO;
		Double metrageTotal = ZERO;

		String FACTURE_TYPE_AVOIRE = "Avoir";
		boolean modeRemiseEstTotal = false;
		
		if(factureValue.getDate() == null)
		factureValue.setDate(Calendar.getInstance());

		//logger.info("####  Facture  " + factureValue.getDate());
		if (((factureValue.getReference() != null && factureValue.getReference().equals(""))
				|| factureValue.getReference() == null)) {
			if (factureValue.getType() != null) {
				
				
				//factureValue.setReference(getCurrentReference(factureValue.getType(), factureValue.getDate(), true));
				factureValue.setReference(getCurrentReferenceMensuelDeclarer(factureValue.getType(),factureValue.isDeclarer(),factureValue.getDate(),true)); 
				
				
				
				
				
				/*if (factureValue.getType().equalsIgnoreCase(FACTURE_TYPE_AVOIRE)) {
				//	factureValue.setReference(this.getNumeroAvoirAchat(factureValue.getDate()));
					factureValue.setReference(this.getCurrentReference(factureValue.getType(),factureValue.getDate(),true));
				} else {
					factureValue.setReference(this.getCurrentReference(factureValue.getType(),factureValue.getDate(),true));
				}*/

			}
		}else
			
		{
			
			
			 if(factureValue.getRefAvantChangement() != null && factureValue.getReference().equals(factureValue.getRefAvantChangement())) {
				 
					this.getCurrentReferenceMensuelDeclarer(factureValue.getType(),factureValue.isDeclarer(),factureValue.getDate(), true); 
				 
				 //this.getCurrentReference(factureValue.getType(),factureValue.getDate(),true);
           }
			
		}
		
	   if(factureValue.getType().equals(FACTURE_TYPE_AVOIRE)) {
			
			factureValue.setDeclarer(true);
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
		for (DetFactureAchatValue detFactureAchat : factureValue.getListDetFactureAchat()) {

			if (detFactureAchat.getQuantite() != null && detFactureAchat.getPrixUnitaireHT() != null) {
				if (detFactureAchat.getPrixUnitaireHT() != null) {
					detFactureAchat.setPrixTotalHT(detFactureAchat.getQuantite() * detFactureAchat.getPrixUnitaireHT());
				}
				// MAJ Qte Produit
			if (detFactureAchat.getProduitId() != null) {
				
                     ArticleValue produitValue=articlePersistance.getArticleParId(detFactureAchat.getProduitId());
                     
					//ProduitValue produitValue = produitPersistance.rechercheProduitById(detFactureAchat.getProduitId());
					
                     if(detFactureAchat.getTaxeId() == null)
					    detFactureAchat.setTaxeId(produitValue.getIdTaxe());
                     
                     
						detFactureAchat.setSerialisable(produitValue.isSerialisable());
						
						
						//Ajouter par samer le 08.09.20  pour calculer la taxe apres la remise.
					 /*	if(detFactureAchat.getRemise() != null) {
							
							Double mantantRemise = (detFactureAchat.getPrixTotalHT() *  detFactureAchat.getRemise())/100 ;
							Double prixTotalHTApresRemise = detFactureAchat.getPrixTotalHT() - mantantRemise ;
							
							detFactureAchat.setpr
						}
						*/	
						
						
				//	Double qte = (produitValue.getQuantite() - detFactureAchat.getQuantite());
				//	produitValue.setQuantite(qte);

						
						if (detFactureAchat.getRemise()==null)
							detFactureAchat.setRemise(0D);
						Double totalApresRemise=detFactureAchat.getPrixTotalHT()*(1-detFactureAchat.getRemise()/100);
						
						
						
						
						
						if (!produitTaxeMap.containsKey(detFactureAchat.getTaxeId())) {
							produitTaxeMap.put(detFactureAchat.getTaxeId(), totalApresRemise);

						} else {
							// TODO ERREUR
							Double assietteValue = produitTaxeMap.get(detFactureAchat.getTaxeId())
									+ totalApresRemise;
							produitTaxeMap.put(detFactureAchat.getTaxeId(), assietteValue);

						}
						
		
						
						
				/*		
					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
						produitTaxeMap.put(produitValue.getIdTaxe(), detFactureAchat.getPrixTotalHT());

					} else {
						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())	+ detFactureAchat.getPrixTotalHT();;
						produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

					}*/
				//	produitPersistance.modifierProduit(produitValue);

				}
				
				
			}

			if (detFactureAchat.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detFactureAchat.getPrixTotalHT();
			}
			if (detFactureAchat.getQuantite() != null && (factureValue.getNatureLivraison() != null
					&& !factureValue.getNatureLivraison().equals("FACON"))) {
				metrageTotal = metrageTotal + detFactureAchat.getQuantite();
			}
			if (!modeRemiseEstTotal) {
				if (detFactureAchat.getRemise() != null && detFactureAchat.getPrixTotalHT() != null) {
					montantRemiseTotal += (detFactureAchat.getPrixTotalHT() * detFactureAchat.getRemise()) / 100;
				}
			}

			if (detFactureAchat.getChoix() != null) {

				Long choix = null;
				try {
					choix = Long.parseLong(detFactureAchat.getChoix());
				} catch (NumberFormatException e) {
					//logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detFactureAchat.setChoix(choixRouleau.getDesignation());

				}
			}

		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * factureValue.getTotalPourcentageRemise()) / 100;
		}
		Map<Long, TaxeFactureAchatValue> taxeFactureIdTaxeMap = new HashMap<Long, TaxeFactureAchatValue>();
		for (TaxeFactureAchatValue taxeFacture : factureValue.getListTaxeFacture()) {
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

				taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		// TVA variable selon produit

		for (Long taxe : taxeFactureIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_TIMBRE_2 && taxe != TAXE_ID_TIMBRE_3 && taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				
				/*if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeFactureIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;*/
				
				
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

		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}
		
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE_2)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).getMontant() != null) {
				montantTaxeTimbre2 = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).setMontant(montantTaxeTimbre2);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre2;
			}
		}
		
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE_3)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).getMontant() != null) {
				montantTaxeTimbre3 = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).setMontant(montantTaxeTimbre3);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre3;
			}
		}

		Iterator it = taxeFactureIdTaxeMap.entrySet().iterator();
		List<TaxeFactureAchatValue> listTaxeFacture = new ArrayList<TaxeFactureAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeFactureAchatValue> pair = (Map.Entry<Long, TaxeFactureAchatValue>) it.next();

			listTaxeFacture.add(pair.getValue());

			it.remove();
		}

		factureValue.setListTaxeFacture(listTaxeFacture);

		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;


	
		
		
		if(!factureValue.isForcerCalculMontant())
		{
			
			
			montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
			montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
			montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
			metrageTotal = 	(double)Math.round(metrageTotal * 1000) / 1000 ;
			montantTTCTotal = 	(double)Math.round(montantTTCTotal * 1000) / 1000 ;
			
			
			factureValue.setMontantHTaxe(montantHTaxeTotal);
			factureValue.setMontantRemise(montantRemiseTotal);
			factureValue.setMontantTaxe(montantTaxesTotal);
			factureValue.setMontantTTC(montantTTCTotal);
			factureValue.setMetrageTotal(metrageTotal);
			
		}
		
		
		
       String factureId =  factureAchatPersistance.createFacture(factureValue);
		
		
		
		if(factureValue.getType().equals(FACTURE_TYPE_AVOIRE) && factureValue.isAvecRetourStock())
			  createBonStockSortieFromFactureAvoir(factureValue);
			   
			
			
			
		return factureId;
		
		
		
	}

	private void createBonStockSortieFromFactureAvoir(FactureAchatValue factureValue) {
		
		BonStockValue bonStock = bonStockDomaine.validerFactureAvoirRetour(factureValue.getReference());
		
		bonStock.setType(IConstanteCommerciale.BON_STOCK_TYPE_SORTIE);
		
		
		bonStock.setReference(bonStockDomaine.getCurrentReference(Calendar.getInstance(), true));

		
		bonStockDomaine.createBonStock(bonStock);
	}

	@Override
	public FactureAchatValue getFactureById(Long id) {

		FactureAchatValue factureAchatValue = factureAchatPersistance.getFactureById(id);

		if (factureAchatValue.getListDetFactureAchat() != null) {

			Collections.sort(factureAchatValue.getListDetFactureAchat(), new DetFactureAchatComparator());

			// if(factureAchatValue.getNatureLivraison().equals("FINI")){
			// Collections.sort(factureAchatValue.getListDetFactureAchat(), new
			// DetFactureAchatComparator());
			// }else{ //FACON
			//
			// for (DetFactureAchatValue detFactureAchatValue :
			// factureAchatValue.getListDetFactureAchat()) {
			// if(detFactureAchatValue.getFicheId()!=null){
			// FicheFaconValue ficheFacon =
			// ficheFaconDomaine.getById(detFactureAchatValue.getFicheId());
			// detFactureAchatValue.setRefMiseList(miseDomaine.listRefMiseParRefBR(ficheFacon.getRefBonReception()));
			// }
			// }
			// Collections.sort(factureAchatValue.getListDetFactureAchat(), new
			// DetFactureAchatFaconComparator());
			// }
		}
		return factureAchatValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateFacture(FactureAchatValue factureValue) {

		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;
		Double montantTaxeTimbre2 = ZERO;
		Double montantTaxeTimbre3 = ZERO;

		Double assietteFodec = ZERO;

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

		if (factureValue.getTotalPourcentageRemise() != null && factureValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}
		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (DetFactureAchatValue detFactureAchat : factureValue.getListDetFactureAchat()) {
			
			
			
			
			
			
			

			if (detFactureAchat.getQuantite() != null & detFactureAchat.getPrixUnitaireHT() != null) {
				detFactureAchat.setPrixTotalHT(detFactureAchat.getQuantite() * detFactureAchat.getPrixUnitaireHT());
			}

			
			//Ajouter par samer le 11.09.20  pour calculer la taxe apres la remise.
			if (detFactureAchat.getProduitId() != null) {
				
				
                     ArticleValue produitValue=articlePersistance.getArticleParId(detFactureAchat.getProduitId());
				//ProduitValue produitValue = produitPersistance.rechercheProduitById(detFactureAchat.getProduitId());
				
                     
                     if(detFactureAchat.getTaxeId() == null)
				    detFactureAchat.setTaxeId(produitValue.getIdTaxe());
                     
                     
                     
					detFactureAchat.setSerialisable(produitValue.isSerialisable());
					
					
			
		
					
					if (detFactureAchat.getRemise()==null)
						detFactureAchat.setRemise(0D);
					Double totalApresRemise=detFactureAchat.getPrixTotalHT()*(1-detFactureAchat.getRemise()/100);
					
					
					
					
					
					if (!produitTaxeMap.containsKey(detFactureAchat.getTaxeId())) {
						produitTaxeMap.put(detFactureAchat.getTaxeId(), totalApresRemise);

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(detFactureAchat.getTaxeId())
								+ totalApresRemise;
						produitTaxeMap.put(detFactureAchat.getTaxeId(), assietteValue);

					}
					

			}
			
			
			
			
			
			
			
			
			
			
			if (detFactureAchat.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detFactureAchat.getPrixTotalHT();
			}
			if (detFactureAchat.getQuantite() != null && (factureValue.getNatureLivraison() != null
					&& !factureValue.getNatureLivraison().equals("FACON"))) {
				metrageTotal = metrageTotal + detFactureAchat.getQuantite();
			}
			if (!modeRemiseEstTotal) {
				if (detFactureAchat.getRemise() != null && detFactureAchat.getPrixTotalHT() != null) {
					montantRemiseTotal += (detFactureAchat.getPrixTotalHT() * detFactureAchat.getRemise()) / 100;
				}
			}

			if (detFactureAchat.getChoix() != null) {

				Long choix = null;
				try {
					choix = Long.parseLong(detFactureAchat.getChoix());
				} catch (NumberFormatException e) {
					//logger.error("NumberFormatException: " + e.getMessage());
				}

				if (choix != null) {
					ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choix);
					if (choixRouleau != null)
						detFactureAchat.setChoix(choixRouleau.getDesignation());
				}
			}

		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * factureValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeFactureAchatValue> taxeFactureIdTaxeMap = new HashMap<Long, TaxeFactureAchatValue>();
		for (TaxeFactureAchatValue taxeFacture : factureValue.getListTaxeFacture()) {
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

				taxeFactureIdTaxeMap.get(TAXE_ID_FODEC).setMontant(montantTaxeFodec);
				montantTaxesTotal = montantTaxesTotal + montantTaxeFodec;
			}
		}

		for (Long taxe : taxeFactureIdTaxeMap.keySet()) {
			if (taxe != TAXE_ID_TIMBRE && taxe != TAXE_ID_TIMBRE_2 && taxe != TAXE_ID_TIMBRE_3 &&  taxe != TAXE_ID_FODEC) {
				montantTaxeTVA = ZERO;
				/*if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeFactureIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeFactureIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;*/
				
				
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
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}
		
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE_2)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).getMontant() != null) {
				montantTaxeTimbre2 = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_2).setMontant(montantTaxeTimbre2);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre2;
			}
		}
		
		if (taxeFactureIdTaxeMap.containsKey(TAXE_ID_TIMBRE_3)) {
			if (taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).getMontant() != null) {
				montantTaxeTimbre3 = taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).getMontant();

				taxeFactureIdTaxeMap.get(TAXE_ID_TIMBRE_3).setMontant(montantTaxeTimbre3);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre3;
			}
		}

		List<TaxeFactureAchatValue> listTaxeFacture = new ArrayList<TaxeFactureAchatValue>();
		Iterator it = taxeFactureIdTaxeMap.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<Long, TaxeFactureAchatValue> pair = (Map.Entry<Long, TaxeFactureAchatValue>) it.next();

			listTaxeFacture.add(pair.getValue());

			it.remove();
		}

		factureValue.setListTaxeFacture(listTaxeFacture);

		Double montantTTC = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;
		
		
		if(!factureValue.isForcerCalculMontant())
		{
			
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
			
		}

		

		return factureAchatPersistance.updateFacture(factureValue);
	}

	@Override
	public void deleteFacture(Long id) {

		
		FactureAchatValue factureAchat = factureAchatPersistance.getFactureById(id);
		factureAchatPersistance.deleteFacture(id);
		deleteElementReglementAchatByRefFacture(factureAchat.getReference());
		
	}
	
	
	private void deleteElementReglementAchatByRefFacture(String reference) {
		
		
		 List<ElementReglementAchatValue> list = elementReglementAchatPersistance.getByRefFactureExact(reference);
		 
		 for(ElementReglementAchatValue element : list) {
			 
			 elementReglementAchatPersistance.deleteElementReglementById(element.getId());
			 
		 }
		
	}

	@Override
	public FactureAchatValue getFactureByReference(String reference) {

		return factureAchatPersistance.getFactureByReference(reference);
	}

	@Override
	public List<FactureAchatVue> getListFactureRefByClient(Long idClient) {

		List<FactureAchatVue> facturelist = factureAchatPersistance.getRefFactureByClientId(idClient);

		return facturelist;
	}

	@Override
	public ListProduitAchatElementValue getProduitElementList(List<String> refFactureList, Long factureAchatId) {

		List<FactureAchatValue> listFactureAchatValue = factureAchatPersistance.getProduitElementList(refFactureList);
		List<DetFactureAchatValue> listDetFactureAchat = new ArrayList<DetFactureAchatValue>();

		ListProduitAchatElementValue listProduitElementValue = new ListProduitAchatElementValue();
		
		if(listFactureAchatValue.size() > 0)
			listProduitElementValue.setIdDepot(listFactureAchatValue.get(0).getIdDepot());

		for (FactureAchatValue factureAchat : listFactureAchatValue) {

			for (DetFactureAchatValue detFactureAchat : factureAchat.getListDetFactureAchat()) {
				

				if (detFactureAchat.getProduitId() != null) {
					
					 ArticleValue produit=articlePersistance.getArticleParId(detFactureAchat.getProduitId());
					//ProduitValue produit = produitPersistance.rechercheProduitById(detFactureAchat.getProduitId());
					
					
					if (produit != null) {
						
					
						detFactureAchat.setProduitDesignation(produit.getDesignation());
						detFactureAchat.setProduitReference(produit.getRef());
						
						if(detFactureAchat.getPrixUnitaireHT() == null && detFactureAchat.getPrixTotalHT() == null) {
							
							
							
							detFactureAchat.setPrixUnitaireHT(produit.getPu());
							detFactureAchat.setPrixTotalHT(produit.getPu() * detFactureAchat.getQuantite());	
							
							
						}
						
					
					}
				}

				if (factureAchatId != null) {
					DetFactureAchatValue detFactureAchatValue = detFactureAchatPersistance.getByFactureAchatAndProduit(
							factureAchatId, detFactureAchat.getProduitId(), detFactureAchat.getChoix());

					if (detFactureAchatValue != null) {
						
				
					
						detFactureAchat.setProduitDesignation(detFactureAchatValue.getProduitDesignation());
						detFactureAchat.setProduitReference(detFactureAchatValue.getProduitReference());

						boolean detailIdNotExist = true;
						for (DetFactureAchatValue detail : listDetFactureAchat) {
							if (detail.getId() == detFactureAchatValue.getId())
								detailIdNotExist = false;
						}

						if (detailIdNotExist) {
							detFactureAchat.setId(detFactureAchatValue.getId());
							detFactureAchat.setRemise(detFactureAchatValue.getRemise());
							detFactureAchat.setQuantite(detFactureAchatValue.getQuantite());

							detFactureAchat.setPrixUnitaireHT(detFactureAchatValue.getPrixUnitaireHT());
							detFactureAchat.setPrixTotalHT(
									detFactureAchatValue.getPrixUnitaireHT() * detFactureAchat.getQuantite());
						}

					}
				}
				listDetFactureAchat.add(detFactureAchat);
			}
		}

		listProduitElementValue.setListDetFactureAchat(listDetFactureAchat);

		return listProduitElementValue;

	}

	/**
	 * Récuperer le numéro de la facture à partir du Guichet facture
	 * 
	 * Le Numéro de la facture est unique pour une facture OSIRIS et doit
	 * respect le format:
	 * 
	 * - AAAA : année du Bon Reception. - NNNNNN : un numéro incrémental au sein
	 * de l'année civile.
	 * 
	 */


	private String getNumeroAvoirAchat(final Calendar pDateBonFacture) {

		Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");
		vNumFacture.append(vAnneeCourante);
		vNumFacture.append(vNumGuichetFacture);
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) + 1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceAvoirCourante(new Long(vNumGuichetFacture + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine.modifierGuichetAvoirAnnuel(vGuichetValeur);
		return vNumFacture.toString();
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@Override
	public String getCurrentReference(String type, Calendar instance, boolean increment) {
		
		
		
		if (type.equals(FACTURE_TYPE_AVOIRE))

			return getNumeroAvoir(instance, increment);

		else
			return getNumeroFacture(instance, increment);
		
		

		
	}

	private String getNumeroFacture(Calendar instance, boolean increment) {

		
		
		
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();
		
		Long numeroFacture = currentGuichetAnnuel.getNumReferenceFactureAchatCourante() ;
		
	//	Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
	//	int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");
		
		
		if(currentGuichetAnnuel.getPrefixeFactureAchat() != null)
		vNumFacture.append(currentGuichetAnnuel.getPrefixeFactureAchat());
		
    	if( numeroFacture >0 && numeroFacture<10) {
    		vNumFacture.append("000");
    	}
    	else if(numeroFacture >=10 && numeroFacture<100)
    		{vNumFacture.append("00");}
    	
    	
    	else if(numeroFacture >=100 && numeroFacture<1000)
		{vNumFacture.append("0");}
    	
    	
    	
		vNumFacture.append( numeroFacture);
		

		currentGuichetAnnuel.setNumReferenceFactureAchatCourante(new Long(numeroFacture + 1L));
				
		
		
		/** Modification de la valeur en base du numéro. */
		if(increment)
		this.guichetAnnuelDomaine
				.modifierGuichetFactureAchatAnnuel(currentGuichetAnnuel);
		
		
		
		return vNumFacture.toString();
	}

	private String getNumeroAvoir(Calendar instance, boolean increment) {

		
		
		
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();
		
		Long numeroFacture = currentGuichetAnnuel.getNumFactureAvoirAchatCourante() ;
		
	//	Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
	//	int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFacture = new StringBuilder("");
		
		
		if(currentGuichetAnnuel.getPrefixeFactureAvoirAchat() != null)
		vNumFacture.append(currentGuichetAnnuel.getPrefixeFactureAvoirAchat());
		
    	if( numeroFacture >0 && numeroFacture<10) {
    		vNumFacture.append("000");
    	}
    	else if(numeroFacture >=10 && numeroFacture<100)
    		{vNumFacture.append("00");}
    	
    	
    	else if(numeroFacture >=100 && numeroFacture<1000)
		{vNumFacture.append("0");}
    	
    	
    	
		vNumFacture.append( numeroFacture);
		

		currentGuichetAnnuel.setNumFactureAvoirAchatCourante(new Long(numeroFacture + 1L));
				
		
		
		/** Modification de la valeur en base du numéro. */
		if(increment)
		this.guichetAnnuelDomaine
				.modifierGuichetFactureAvoirAchatAnnuel(currentGuichetAnnuel);
		
		
		
		return vNumFacture.toString();
	}

	@Override
	public ListProduitElementValue getArticleAvoir(Long clientId) {


		List<DetFactureAchatValue> listDetFactureVente = new ArrayList<DetFactureAchatValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		//RechercheMulticritereProduitValue requetRechProduit = new RechercheMulticritereProduitValue();
		RecherecheMulticritereArticleValue requetRechProduit=new RecherecheMulticritereArticleValue();
		//requetRechProduit.setRetour("oui");
		
		
		ResultatRechecheArticleValue reultat=articlePersistance.rechercherArticleMultiCritere(requetRechProduit);
		//ResultatRechecheProduitValue reultat = produitPersistance.rechercherProduitMultiCritere(requetRechProduit);

		for (ArticleValue element : reultat.getArticleValues()) {

			DetFactureAchatValue detFactureVente = new DetFactureAchatValue();
			detFactureVente.setQuantite(1d);

			detFactureVente.setProduitId(element.getId());

			detFactureVente.setProduitDesignation(element.getDesignation());
			detFactureVente.setProduitReference(element.getRef());
			detFactureVente.setPrixUnitaireHT(element.getPu());
			detFactureVente.setPrixTotalHT(element.getPu() * detFactureVente.getQuantite());

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

		listProduitElementValue.setListDetFactureAchat(listDetFactureVente);

		return listProduitElementValue;
	}

	@Override
	public BLReportElementRecapValue getDepenseFacturebyMonth(RechercheMulticritereFactureAchatValue request) {
		
		return factureAchatPersistance.getDepenseFacturebyMonth(request);
	}
	
	
	private String getReferenceFactureFromGuichetMensuel(final String typeFacture , final Calendar pDateBonLiv , final boolean increment) {
		
		if(typeFacture.equals(FACTURE_TYPE_AVOIRE))
			return getReferenceFactureAvoirFromGuichetMensuel(pDateBonLiv, increment) ;
		else
			return getReferenceFactureNormalFromGuichetMensuel(pDateBonLiv, increment) ;
		
		
		
	}
	
	
	private String getReferenceFactureNormalFromGuichetMensuel( final Calendar pDateBonLiv , final boolean increment) {
		

			

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumfactureReference(pDateBonLiv); 
		String vNumGuichetPrefix=this.guichetierMensuelDomaine.getPrefixFacture(pDateBonLiv);
		int vAnneeCourante = pDateBonLiv.get(Calendar.YEAR);
		int moisActuel = pDateBonLiv.get(Calendar.MONTH) + 1;

		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonLiv = new StringBuilder("");
	
		vNumBonLiv.append(vNumGuichetPrefix);
		//vNumBonLiv.append(vAnneeCourante);
		//vNumBonLiv.append(String.format("%02d", moisActuel));
		vNumBonLiv.append(String.format("%04d", vNumGuichetBonLiv));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
		/** idMensuel = (annuelcourante - 2016) + moisCourant */

		//Calendar cal = Calendar.getInstance();
		int anneActuelle = pDateBonLiv.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceFactureCourante(new Long(vNumGuichetBonLiv + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetFactureMensuel(vGuichetValeur);  
		

		return vNumBonLiv.toString();

	}

	private String getReferenceFactureAvoirFromGuichetMensuel(final Calendar pDateBonLiv , final boolean increment) {
		

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumfactureAvoirReference(pDateBonLiv); 
		String vNumGuichetPrefix=this.guichetierMensuelDomaine.getPrefixFactureAvoir(pDateBonLiv);
		int vAnneeCourante = pDateBonLiv.get(Calendar.YEAR);
		int moisActuel = pDateBonLiv.get(Calendar.MONTH) + 1;

		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonLiv = new StringBuilder("");
		vNumBonLiv.append(vNumGuichetPrefix);
		//vNumBonLiv.append(vAnneeCourante);
		//vNumBonLiv.append(String.format("%02d", moisActuel));
		vNumBonLiv.append(String.format("%04d", vNumGuichetBonLiv));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
		/** idMensuel = (annuelcourante - 2016) + moisCourant */

	//	Calendar cal = Calendar.getInstance();
		int anneActuelle = pDateBonLiv.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceAvoirCourante(new Long(vNumGuichetBonLiv + 1L));     

		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetFactureAvoirMensuel(vGuichetValeur);  
		

		return vNumBonLiv.toString();

	}
	
	
	
	

	@Override
	public String getCurrentReferenceMensuel(String type, Calendar instance, boolean b) {
		
		if (type.equals(FACTURE_TYPE_AVOIRE))

			return getReferenceFactureAvoirFromGuichetMensuel(instance, b);

		else
			return getReferenceFactureFromGuichetMensuel(type,instance, b);
		
		

		
		
	}

	@Override
	public String getCurrentReferenceMensuelDeclarer(String type, boolean declarer, Calendar instance, boolean increment) {
	
		
		if (declarer)
			return getReferenceFactureFromGuichetMensuel(type, instance, increment) ;
		
		else
			return getCurrentReferenceNotDeclarer(type, instance, increment) ;
		
	
		
		
	}

	private String getCurrentReferenceNotDeclarer(String type, Calendar instance, boolean increment) {
		if (type.equals(FACTURE_TYPE_AVOIRE))

			return getReferenceFactureAvoirFromGuichetMensuel(instance, increment);

		else
			return getReferenceFactureAchatFromGuichetMensuelNotDeclarer(instance, increment);
		
		
	}

	private String getReferenceFactureAchatFromGuichetMensuelNotDeclarer(Calendar pDate, boolean increment) {

		Long vNumGuichetFacAchat = this.guichetierMensuelDomaine.getNextNumfactureAchatReferenceNondeclarer(pDate); 
		String vNumGuichetPrefix=this.guichetierMensuelDomaine.getPrefixFactureAchatNondeclarer(pDate);
		int vAnneeCourante = pDate.get(Calendar.YEAR);
		int moisActuel = pDate.get(Calendar.MONTH) + 1;

		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumFactNonDec = new StringBuilder("");
	
		vNumFactNonDec.append(vNumGuichetPrefix);
		//vNumBonLiv.append(vAnneeCourante);
		//vNumBonLiv.append(String.format("%02d", moisActuel));
		vNumFactNonDec.append(String.format("%04d", vNumGuichetFacAchat));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
		/** idMensuel = (annuelcourante - 2016) + moisCourant */

		//Calendar cal = Calendar.getInstance();
		int anneActuelle = pDate.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceFactureAchatNonDeclarerCourante(new Long(vNumGuichetFacAchat + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetFactureAchatNonDeclarerMensuel(vGuichetValeur);  
		

		return vNumFactNonDec.toString();
		
		
	}
}
