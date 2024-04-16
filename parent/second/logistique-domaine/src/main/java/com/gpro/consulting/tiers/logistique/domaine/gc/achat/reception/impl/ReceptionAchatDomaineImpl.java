package com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.DetFactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.facture.value.FactureAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.BonReceptionVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ProduitReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatFactureVue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.RechercheMulticritereBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ResultatRechecheBonReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.TaxeReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reglement.value.ElementReglementAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.DetFactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.FactureVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.vente.facture.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.facture.utilities.DetFactureAchatValidateComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.reception.IReceptionAchatDomaineGC;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IDetFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reglement.IElementReglementAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMagasinPersistance;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 * 
 * @author Hamdi etteieb
 * @since 23/09/208
 *
 */

@Component
public class ReceptionAchatDomaineImpl implements IReceptionAchatDomaineGC {

	private static final Logger logger = LoggerFactory.getLogger(ReceptionAchatDomaineImpl.class);

	private static final Double ZERO_D = 0D;

	private static final Double ZERO = 0D;

	private static final Long ZERO_LONG = 0L;

	private static final String SEPARATOR = "-";
	
	private static final String SEPARATOR_NUMERO_SERIE = "&";

	private static final int FIRST_INDEX = 0;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;

	@Autowired
	private IReceptionAchatPersistance receptionAchatPersistance;

	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IProduitDepotPersistance produitDepotPersistance;

	@Autowired
	private IProduitSerialisablePersistance produitSerialisablePersistance;

	@Autowired
	private IFactureAchatPersistance factureAchatPersistance;

	@Autowired
	private IDetFactureAchatPersistance detFactureAchatPersistance;

	@Autowired
	private IFacturePersistance facturePersistance;
	
	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;
	

	@Autowired
	private IMagasinPersistance magasinPersistance ;
	
	
	@Autowired
	private IElementReglementAchatPersistance elementReglementAchatPersistance;
	
	@Autowired
	private IArticlePersistance articlePersistance;
	
	@Autowired
	private IGuichetMensuelDomaine guichetierMensuelDomaine;
	

	@Override
	public String create(ReceptionAchatValue bonReceptionValue) {

		
		if(bonReceptionValue.getType().equals("Retour"))
			return createReceptionRetour(bonReceptionValue);
	
		
		return createReceptionAchat(bonReceptionValue);
		
		
	}
	
	
	
	private String createReceptionRetour(ReceptionAchatValue bonReceptionValue) {

		

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

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

		// logger.info("----- bonReceptionValue.getReference() ----------" +
		// bonReceptionValue.getReference());

		if ((bonReceptionValue.getReference() != null && bonReceptionValue.getReference().equals(""))
				|| bonReceptionValue.getReference() == null) {

			//bonReceptionValue.setReference(this.getCurrentReference(bonReceptionValue.getDateIntroduction(), true));
			bonReceptionValue.setReference(getReferenceReceptionFromGuichetMensuel(Calendar.getInstance(),true));  

			// logger.info("----- auto reference ----------" +
			// bonReceptionValue.getReference());

		} else

		{
			if (bonReceptionValue.getRefAvantChangement() != null
					&& bonReceptionValue.getReference().equals(bonReceptionValue.getRefAvantChangement())) {
				
				this.getReferenceReceptionFromGuichetMensuel(Calendar.getInstance(),true);
				//this.getCurrentReference(bonReceptionValue.getDateIntroduction(), true);
			}

		}

		if (bonReceptionValue.getTotalPourcentageRemise() != null
				&& bonReceptionValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		List<ProduitReceptionAchatValue> vListDetails = new ArrayList<ProduitReceptionAchatValue>();


		for (ProduitReceptionAchatValue produitReception : bonReceptionValue.getListProduitReceptions()) {
			
			ArticleValue articleValue=new ArticleValue();
			articleValue.setId(produitReception.getProduitId());
			
			
			if (produitReception.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitReception.getQuantite();

				
			
				// MAJ Qte Produit
				if (produitReception.getProduitId() != null) {

					//ProduitValue produitValue = produitPersistance.rechercheProduitById(produitReception.getProduitId());
					
					
					ArticleValue produitValue=articlePersistance.rechercheArticleParId(articleValue);
					
					produitReception.setTaxeId(produitValue.getIdTaxe());

					if (produitReception.getPrixUnitaire() != null && produitReception.getQuantite() != null) {
						coutCommandeVenteTotal += (produitReception.getPrixUnitaire() * produitReception.getQuantite());
					}

					if (produitReception.getPrixUnitaire() != null) {
						Double prixTotal = produitReception.getQuantite() * produitReception.getPrixUnitaire();
						produitReception.setPrixTotalHT(convertisseur(prixTotal, 4));
					}

					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
						produitTaxeMap.put(produitValue.getIdTaxe(), produitReception.getPrixTotalHT());

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
								+ produitReception.getPrixTotalHT();
						produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

					}

					if (produitReception.getPrixTotalHT() != null) {
						montantHTaxeTotal = montantHTaxeTotal + produitReception.getPrixTotalHT();
					}
					/*
					 * if (produitReception.getQuantite() != null &&
					 * !bonReceptionValue.getNatureLivraison().equals("FACON")) { metrageTotal =
					 * metrageTotal + produitReception.getQuantite(); }
					 */
					if (!modeRemiseEstTotal) {
						if (produitReception.getRemise() != null && produitReception.getPrixTotalHT() != null) {
							montantRemiseTotal += (produitReception.getPrixTotalHT() * produitReception.getRemise())
									/ 100;
						}
					}

			/*		if (produitValue.isSerialisable())
						produitReception.setSerialisable(true);
					Double qte = 0D;
					if (produitValue.getQuantite() != null) {
						qte = produitValue.getQuantite() + produitReception.getQuantite();
					} else {
						qte = produitReception.getQuantite();
					}

					produitValue.setQuantite(qte);
					
					//produitPersistance.modifierProduit(produitValue);
					
					articlePersistance.modifierArticle(articleValue);

					*/
					
					//ArticleSerialisableValue n'existe pas
					
					if (produitReception.getProduitsSerialisable() != null) {

						String numeroSeries = "";

						for (ProduitSerialisableValue produitSerialisable : produitReception
								.getProduitsSerialisable()) {

						   //update prodouit serialisable	
							if(produitSerialisable.getId() != null) {
								
								produitSerialisable.setBlAncien(addReference(produitSerialisable.getBlAncien(),produitSerialisable.getNumBonLivraison()));
								produitSerialisable.setBrRetour(addReference(produitSerialisable.getBrRetour(),bonReceptionValue.getReference()));
								produitSerialisable.setNumFactureAvoir(addReference(produitSerialisable.getFactureAvoirAncien(),produitSerialisable.getNumFactureAvoir()));
								produitSerialisable.setNumFacture(addReference(produitSerialisable.getFactureAncien(),produitSerialisable.getNumFacture()));
								
								produitSerialisable.setClientId(null);
								produitSerialisable.setNumBonLivraison(null);
								produitSerialisable.setNumFacture(null);
								produitSerialisable.setNumFactureAvoir(null);
								

								produitSerialisablePersistance.modifierProduitSerialisable(produitSerialisable);
								
							}
				

							numeroSeries += produitSerialisable.getNumSerie();
							numeroSeries += "&";

						}

						if (numeroSeries != null && numeroSeries.length() > 0
								&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
							numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
							produitReception.setNumeroSeries(numeroSeries);
						}
					}


					if (estNonVide(bonReceptionValue.getIdDepot() )) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonReceptionValue.getIdDepot());
						request.setIdProduit(produitReception.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot == null || produitDepot.getProduitdepotvalues().size() == 0) {

							// Creation

							ProduitDepotValue vProduitDepot = new ProduitDepotValue();

							vProduitDepot.setIdDepot(bonReceptionValue.getIdDepot());
							vProduitDepot.setIdProduit(produitReception.getProduitId());
							vProduitDepot.setQuantite(produitReception.getQuantite());

							produitDepotPersistance.create(vProduitDepot);

						} else {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(produitReception.getQuantite() + vProduitDepot.getQuantite());
							produitDepotPersistance.modifier(vProduitDepot);

						}

					}

					/*
					 * if (produitReception.getChoix() != null) {
					 * 
					 * Long choix = null; try { choix = Long.parseLong(produitReception.getChoix());
					 * } catch (NumberFormatException e) { // logger.error("NumberFormatException: "
					 * + e.getMessage()); }
					 * 
					 * if (choix != null) { ChoixRouleauValue choixRouleau =
					 * choixRouleauPersistance.getChoixRouleauById(choix); if (choixRouleau != null)
					 * detLivraisonVente.setChoix(choixRouleau.getDesignation());
					 * 
					 * } }
					 * 
					 */

					vListDetails.add(produitReception);

				}

			}

			produitReception.setDateLivraison(bonReceptionValue.getDateLivraison());
		}

		bonReceptionValue.setListProduitReceptions(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonReceptionValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeReceptionAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeReceptionAchatValue>();
		// modification
		
		if(bonReceptionValue.getListTaxeReceptionAchat() != null)
		for (TaxeReceptionAchatValue taxeLivraison : bonReceptionValue.getListTaxeReceptionAchat()) {
			// Long key = taxeLivraison.getId();
			Long key = taxeLivraison.getTaxeId();
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
		List<TaxeReceptionAchatValue> listTaxeReceptionAchat = new ArrayList<TaxeReceptionAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeReceptionAchatValue> pair = (Map.Entry<Long, TaxeReceptionAchatValue>) it.next();

			listTaxeReceptionAchat.add(pair.getValue());

			it.remove();
		}

		bonReceptionValue.setListTaxeReceptionAchat(listTaxeReceptionAchat);
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonReceptionValue.setMontantHTaxe(montantHTaxeTotal);
		bonReceptionValue.setMontantRemise(montantRemiseTotal);
		bonReceptionValue.setMontantTaxe(montantTaxesTotal);
		bonReceptionValue.setMontantTTC(montantTTCTotal);
		bonReceptionValue.setMetrageTotal(metrageTotal);

		bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);
		
		return receptionAchatPersistance.create(bonReceptionValue);
	}



	private String addReference(String blAncien, String numBonLivraison) {
		
		if(blAncien == null) {
			
			return numBonLivraison;
		}
		else {
			
			if(numBonLivraison != null) {
				String ch = blAncien + "&"+ numBonLivraison;
				
				return ch;
			}else
			{
				return blAncien;
			}
		
		}
			
	}



	private String createReceptionAchat(ReceptionAchatValue bonReceptionValue) {
		

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

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

		// logger.info("----- bonReceptionValue.getReference() ----------" +
		// bonReceptionValue.getReference());
		
		
		
		
		String type = "";
		
		if(bonReceptionValue.getDateIntroduction() == null)
			bonReceptionValue.setDateIntroduction(Calendar.getInstance());
		
		
		if(bonReceptionValue.getFacture() != null &&  bonReceptionValue.getFacture() == true)
			type = "declarer";
		else
			type = "non-declarer";
		

		if ((bonReceptionValue.getReference() != null && bonReceptionValue.getReference().equals(""))
				|| bonReceptionValue.getReference() == null) {

			//bonReceptionValue.setReference(this.getCurrentReference(bonReceptionValue.getDateIntroduction(), true));
			

			bonReceptionValue.setReference(getCurrentReferenceMensuelByType(type,bonReceptionValue.getDateIntroduction(),true));  
			
			// logger.info("----- auto reference ----------" +
			// bonReceptionValue.getReference());

		} else

		{
			if (bonReceptionValue.getRefAvantChangement() != null
					&& bonReceptionValue.getReference().equals(bonReceptionValue.getRefAvantChangement())) {
				//this.getCurrentReference(bonReceptionValue.getDateIntroduction(), true);
				this.getCurrentReferenceMensuelByType(type,bonReceptionValue.getDateIntroduction(), true);  
			}

		}
		
		MagasinValue magasin = null;
		
		//if(bonReceptionValue.getIdDepot() != null)
		//      magasin = magasinPersistance.rechercheMagasinParId(bonReceptionValue.getIdDepot()) ;
		
		
		
		
		

		if (bonReceptionValue.getTotalPourcentageRemise() != null
				&& bonReceptionValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		List<ProduitReceptionAchatValue> vListDetails = new ArrayList<ProduitReceptionAchatValue>();

		for (ProduitReceptionAchatValue produitReception : bonReceptionValue.getListProduitReceptions()) {
			

			if (produitReception.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitReception.getQuantite();

				// MAJ Qte Produit
				if (produitReception.getProduitId() != null) {
					
				

					//ProduitValue produitValue = produitPersistance.rechercheProduitById(produitReception.getProduitId());
					
					ArticleValue produitValue=articlePersistance.getArticleParId(produitReception.getProduitId());
					
					
					if(produitReception.getTaxeId() == null)
					produitReception.setTaxeId(produitValue.getIdTaxe());
					
					

					if (produitReception.getPrixUnitaire() != null && produitReception.getQuantite() != null) {
						coutCommandeVenteTotal += (produitReception.getPrixUnitaire() * produitReception.getQuantite());
					}

					if (produitReception.getPrixUnitaire() != null) {
						Double prixTotal = produitReception.getQuantite() * produitReception.getPrixUnitaire();
						produitReception.setPrixTotalHT(convertisseur(prixTotal, 4));
					}

					
					
					
					//TODO GASH -10092020
                    // Modification - Introduire Remise dans Assiette de TVA
					// Verification Round 
					
					if (produitReception.getRemise()==null)
						produitReception.setRemise(0D);
					Double totalApresRemise=produitReception.getPrixTotalHT()*(1-produitReception.getRemise()/100);
					
					
					
					
					
					if (!produitTaxeMap.containsKey(produitReception.getTaxeId())) {
						produitTaxeMap.put(produitReception.getTaxeId(), totalApresRemise);

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(produitReception.getTaxeId())
								+ totalApresRemise;
						produitTaxeMap.put(produitReception.getTaxeId(), assietteValue);

					}

					if (produitReception.getPrixTotalHT() != null) {
						montantHTaxeTotal = montantHTaxeTotal + produitReception.getPrixTotalHT();
					}
					/*
					 * if (produitReception.getQuantite() != null &&
					 * !bonReceptionValue.getNatureLivraison().equals("FACON")) { metrageTotal =
					 * metrageTotal + produitReception.getQuantite(); }
					 */
					if (!modeRemiseEstTotal) {
						if (produitReception.getRemise() != null && produitReception.getPrixTotalHT() != null) {
							montantRemiseTotal += (produitReception.getPrixTotalHT() * produitReception.getRemise())
									/ 100;
						}
					}

		


					/*
					 * if (produitReception.getChoix() != null) {
					 * 
					 * Long choix = null; try { choix = Long.parseLong(produitReception.getChoix());
					 * } catch (NumberFormatException e) { // logger.error("NumberFormatException: "
					 * + e.getMessage()); }
					 * 
					 * if (choix != null) { ChoixRouleauValue choixRouleau =
					 * choixRouleauPersistance.getChoixRouleauById(choix); if (choixRouleau != null)
					 * detLivraisonVente.setChoix(choixRouleau.getDesignation());
					 * 
					 * } }
					 * 
					 */

					vListDetails.add(produitReception);

				}

			}

			produitReception.setDateLivraison(bonReceptionValue.getDateLivraison());
		}

		bonReceptionValue.setListProduitReceptions(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonReceptionValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeReceptionAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeReceptionAchatValue>();
		// modification
		for (TaxeReceptionAchatValue taxeLivraison : bonReceptionValue.getListTaxeReceptionAchat()) {
			// Long key = taxeLivraison.getId();
			Long key = taxeLivraison.getTaxeId();
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
		List<TaxeReceptionAchatValue> listTaxeReceptionAchat = new ArrayList<TaxeReceptionAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeReceptionAchatValue> pair = (Map.Entry<Long, TaxeReceptionAchatValue>) it.next();

			listTaxeReceptionAchat.add(pair.getValue());

			it.remove();
		}

		bonReceptionValue.setListTaxeReceptionAchat(listTaxeReceptionAchat);
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;
		
		
		
		montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
		montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
		montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
		metrageTotal = 	(double)Math.round(metrageTotal * 1000) / 1000 ;
		coutCommandeVenteTotal = 	(double)Math.round(coutCommandeVenteTotal * 1000) / 1000 ;
		montantTTCTotal = 	(double)Math.round(montantTTCTotal * 1000) / 1000 ;
		

		bonReceptionValue.setMontantHTaxe(montantHTaxeTotal);
		bonReceptionValue.setMontantRemise(montantRemiseTotal);
		bonReceptionValue.setMontantTaxe(montantTaxesTotal);
		bonReceptionValue.setMontantTTC(montantTTCTotal);
		bonReceptionValue.setMetrageTotal(metrageTotal);

		bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		return receptionAchatPersistance.create(bonReceptionValue);
	}
	

	@Override
	public ReceptionAchatValue getById(Long id) {
		ReceptionAchatValue receptionAchat = receptionAchatPersistance.getById(id);

		for (ProduitReceptionAchatValue element : receptionAchat.getListProduitReceptions()) {

			if (element.isSerialisable()) {
				/*RechercheMulticritereProduitSerialisableValue pRechercheProduitSerialisableMulitCritere = new RechercheMulticritereProduitSerialisableValue();
				pRechercheProduitSerialisableMulitCritere.setProduitId(element.getProduitId());
				pRechercheProduitSerialisableMulitCritere.setNumBonReception(receptionAchat.getReference());

				
				element.setProduitsSerialisable(produitSerialisablePersistance
						.rechercherProduitSerialisableMultiCritere(pRechercheProduitSerialisableMulitCritere)
						.getProduitSerialisableValues());*/
				
				element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),element.getProduitId()));

				
				
				
				
			}

			element.setQuantiteAncien(element.getQuantite());

		}

		return receptionAchat;
	}

	@Override
	public String update(ReceptionAchatValue bonReceptionValue) {
		
		
		
		
		
		if(bonReceptionValue.getType().equals("Retour"))
			return updateReceptionRetour(bonReceptionValue);
	
		
		return updateReceptionAchat(bonReceptionValue);
		
		
	}

	private String updateReceptionAchat(ReceptionAchatValue bonReceptionValue) {

		

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

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
		
		
	    MagasinValue magasin = null;
		



		if (bonReceptionValue.getTotalPourcentageRemise() != null
				&& bonReceptionValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		List<ProduitReceptionAchatValue> vListDetails = new ArrayList<ProduitReceptionAchatValue>();

		for (ProduitReceptionAchatValue produitReception : bonReceptionValue.getListProduitReceptions()) {

			if (produitReception.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitReception.getQuantite();
			}

			if (produitReception.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitReception.getPrixUnitaire() * produitReception.getQuantite());
			}

			produitReception.setDateLivraison(bonReceptionValue.getDateLivraison());

			// TODO Reception update MAJ des quantités
			

			if (produitReception.getProduitId() != null) {



					//ProduitValue produitValue = produitPersistance.rechercheProduitById(produitReception.getProduitId());
				ArticleValue produitValue=articlePersistance.getArticleParId(produitReception.getProduitId());
					
				if(produitReception.getTaxeId() == null)
					produitReception.setTaxeId(produitValue.getIdTaxe());

					if (produitReception.getPrixUnitaire() != null) {
						Double prixTotal = produitReception.getQuantite() * produitReception.getPrixUnitaire();
						produitReception.setPrixTotalHT(convertisseur(prixTotal, 4));
					}
					
	
					//calcul TVA 
					
					//TODO GASH -10092020
                    // Modification - Introduire Remise dans Assiette de TVA
					// Verification Round 
					
					if (produitReception.getRemise()==null)
						produitReception.setRemise(0D);
					Double totalApresRemise=produitReception.getPrixTotalHT()*(1-produitReception.getRemise()/100);
					
					
					
					
					
					if (!produitTaxeMap.containsKey(produitReception.getTaxeId())) {
						produitTaxeMap.put(produitReception.getTaxeId(), totalApresRemise);

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(produitReception.getTaxeId())
								+ totalApresRemise;
						produitTaxeMap.put(produitReception.getTaxeId(), assietteValue);

					}
					//
					
//TODO 
//					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
//						produitTaxeMap.put(produitValue.getIdTaxe(), produitReception.getPrixTotalHT());
//
//					} else {
//						// TODO ERREUR
//						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
//								+ produitReception.getPrixTotalHT();
//						produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);
//
//					}

					if (produitReception.getPrixTotalHT() != null) {
						montantHTaxeTotal = montantHTaxeTotal + produitReception.getPrixTotalHT();
					}
					/*
					 * if (produitReception.getQuantite() != null &&
					 * !bonReceptionValue.getNatureLivraison().equals("FACON")) { metrageTotal =
					 * metrageTotal + produitReception.getQuantite(); }
					 */
					if (!modeRemiseEstTotal) {
						if (produitReception.getRemise() != null && produitReception.getPrixTotalHT() != null) {
							montantRemiseTotal += (produitReception.getPrixTotalHT() * produitReception.getRemise())
									/ 100;
						}
					}

				

			}

	

			vListDetails.add(produitReception);

		}

		// bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		// bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		bonReceptionValue.setListProduitReceptions(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonReceptionValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeReceptionAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeReceptionAchatValue>();
		// modification
		for (TaxeReceptionAchatValue taxeLivraison : bonReceptionValue.getListTaxeReceptionAchat()) {
			// Long key = taxeLivraison.getId();
			Long key = taxeLivraison.getTaxeId();
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
		List<TaxeReceptionAchatValue> listTaxeReceptionAchat = new ArrayList<TaxeReceptionAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeReceptionAchatValue> pair = (Map.Entry<Long, TaxeReceptionAchatValue>) it.next();

			listTaxeReceptionAchat.add(pair.getValue());

			it.remove();
		}

		bonReceptionValue.setListTaxeReceptionAchat(listTaxeReceptionAchat);
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;
		
		
		montantHTaxeTotal = 	(double)Math.round(montantHTaxeTotal * 1000) / 1000 ;
		montantRemiseTotal = 	(double)Math.round(montantRemiseTotal * 1000) / 1000 ;
		montantTaxesTotal = 	(double)Math.round(montantTaxesTotal * 1000) / 1000 ;
		metrageTotal = 	        (double)Math.round(metrageTotal * 1000) / 1000 ;
		coutCommandeVenteTotal = 	(double)Math.round(coutCommandeVenteTotal * 1000) / 1000 ;
		montantTTCTotal = 	(double)Math.round(montantTTCTotal * 1000) / 1000 ;

		bonReceptionValue.setMontantHTaxe(montantHTaxeTotal);
		bonReceptionValue.setMontantRemise(montantRemiseTotal);
		bonReceptionValue.setMontantTaxe(montantTaxesTotal);
		bonReceptionValue.setMontantTTC(montantTTCTotal);
		bonReceptionValue.setMetrageTotal(metrageTotal);

		bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		return receptionAchatPersistance.update(bonReceptionValue);
	}



	private String updateReceptionRetour(ReceptionAchatValue bonReceptionValue) {

		

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

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

		updateStockAfterDeleteDetReceptionAchat(bonReceptionValue);

		// TODO optimisation
		/** récupération de l'ancien ReceptionAchatValue **/
		ReceptionAchatValue receptionAchatAncien = this.getById(bonReceptionValue.getId());

		for (ProduitReceptionAchatValue produitReceptionAchatValue : receptionAchatAncien.getListProduitReceptions()) {

			if (produitReceptionAchatValue.isSerialisable()
					&& produitReceptionAchatValue.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : produitReceptionAchatValue.getProduitsSerialisable()) {

					/**
					 * SI un produit serialisable a été supprimé lors de la modification et cette
					 * produit n'est pas livré
					 **/
					if (notIn(prodSerialisable, bonReceptionValue) && prodSerialisable.getNumBonLivraison() == null) {

						produitSerialisablePersistance.supprimerProduitSerialisable(prodSerialisable.getId());
					}

				}

			}
		}

		if (bonReceptionValue.getTotalPourcentageRemise() != null
				&& bonReceptionValue.getTotalPourcentageRemise() != 0) {
			modeRemiseEstTotal = true;
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();

		List<ProduitReceptionAchatValue> vListDetails = new ArrayList<ProduitReceptionAchatValue>();

		for (ProduitReceptionAchatValue produitReception : bonReceptionValue.getListProduitReceptions()) {

			if (produitReception.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitReception.getQuantite();
			}

			if (produitReception.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitReception.getPrixUnitaire() * produitReception.getQuantite());
			}

			produitReception.setDateLivraison(bonReceptionValue.getDateLivraison());

			// TODO Reception update MAJ des quantités

			if (produitReception.getProduitId() != null) {

				/** Debut update stock si qte a ete modifie **/

				if (produitReception.getId() != null && produitReception.getQuantite() != null
						&& produitReception.getQuantiteAncien() != null
						&& !produitReception.getQuantite().equals(produitReception.getQuantiteAncien())) {

					ProduitValue produitValue = produitPersistance
							.rechercheProduitById(produitReception.getProduitId());

					Double qte = (produitValue.getQuantite() - produitReception.getQuantiteAncien()
							+ produitReception.getQuantite());
					produitValue.setQuantite(qte);
					produitPersistance.modifierProduit(produitValue);
					// MAJ des DEPOTS

					if (bonReceptionValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonReceptionValue.getIdDepot());
						request.setIdProduit(produitReception.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() - produitReception.getQuantiteAncien()
									+ produitReception.getQuantite());
							produitDepotPersistance.modifier(vProduitDepot);
						}
					}
				}
				/** Fin update stock si qte a ete modifie **/

				/** Debut update stock si un nouveau produit a ete ajoute **/

				if (produitReception.getId() == null && produitReception.getQuantite() != null) {

					ProduitValue produitValue = produitPersistance
							.rechercheProduitById(produitReception.getProduitId());

					if (produitValue.isSerialisable())
						produitReception.setSerialisable(true);

					Double qte = (produitValue.getQuantite() + produitReception.getQuantite());

					produitValue.setQuantite(qte);
					produitPersistance.modifierProduit(produitValue);
					// MAJ des DEPOTS

					if (bonReceptionValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonReceptionValue.getIdDepot());
						request.setIdProduit(produitReception.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() + produitReception.getQuantite());

							produitDepotPersistance.modifier(vProduitDepot);
						}

						else

						{

							ProduitDepotValue vProduitDepot = new ProduitDepotValue();

							vProduitDepot.setIdDepot(bonReceptionValue.getIdDepot());
							vProduitDepot.setIdProduit(produitReception.getProduitId());
							vProduitDepot.setQuantite(produitReception.getQuantite());

							produitDepotPersistance.create(vProduitDepot);

						}
					}
				}
				/** Fin update stock si un nouveau produit a ete ajoute **/

				if (produitReception.getProduitId() != null) {

					ProduitValue produitValue = produitPersistance
							.rechercheProduitById(produitReception.getProduitId());
					produitReception.setTaxeId(produitValue.getIdTaxe());

					if (produitReception.getPrixUnitaire() != null) {
						Double prixTotal = produitReception.getQuantite() * produitReception.getPrixUnitaire();
						produitReception.setPrixTotalHT(convertisseur(prixTotal, 4));
					}

					if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {
						produitTaxeMap.put(produitValue.getIdTaxe(), produitReception.getPrixTotalHT());

					} else {
						// TODO ERREUR
						Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe())
								+ produitReception.getPrixTotalHT();
						produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

					}

					if (produitReception.getPrixTotalHT() != null) {
						montantHTaxeTotal = montantHTaxeTotal + produitReception.getPrixTotalHT();
					}
					/*
					 * if (produitReception.getQuantite() != null &&
					 * !bonReceptionValue.getNatureLivraison().equals("FACON")) { metrageTotal =
					 * metrageTotal + produitReception.getQuantite(); }
					 */
					if (!modeRemiseEstTotal) {
						if (produitReception.getRemise() != null && produitReception.getPrixTotalHT() != null) {
							montantRemiseTotal += (produitReception.getPrixTotalHT() * produitReception.getRemise())
									/ 100;
						}
					}

				}

			}

			if (produitReception.getProduitsSerialisable() != null) {

				String numeroSeries = "";

				for (ProduitSerialisableValue produitSerialisable : produitReception.getProduitsSerialisable()) {

					/** Si un nouveau numéro de serie a été ajoutée **/
					if (produitSerialisable.getId() == null && produitSerialisable.getNumSerie() != null) {

						// System.out.println("un nouveau numéro de serie a été ajoutée");
						// System.out.println(produitSerialisable.getNumSerie() );

					/*	produitSerialisable.setProduitId(produitReception.getProduitId());
						produitSerialisable.setPrixAchat(produitReception.getPrixUnitaire());
						produitSerialisable.setFournisseurId(bonReceptionValue.getPartieIntersseId());
						produitSerialisable.setDateAchat(bonReceptionValue.getDateLivraison());
						produitSerialisable.setNumBonReception(bonReceptionValue.getReference());

						produitSerialisablePersistance.creerProduitSerialisable(produitSerialisable);
                    */
					}

					numeroSeries += produitSerialisable.getNumSerie();
					numeroSeries += "&";

				}

				if (numeroSeries != null && numeroSeries.length() > 0
						&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
					numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
					produitReception.setNumeroSeries(numeroSeries);
				}

			}

			vListDetails.add(produitReception);

		}

		// bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		// bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		bonReceptionValue.setListProduitReceptions(vListDetails);

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonReceptionValue.getTotalPourcentageRemise()) / 100;
		}

		Map<Long, TaxeReceptionAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeReceptionAchatValue>();
		// modification
		for (TaxeReceptionAchatValue taxeLivraison : bonReceptionValue.getListTaxeReceptionAchat()) {
			// Long key = taxeLivraison.getId();
			Long key = taxeLivraison.getTaxeId();
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
		List<TaxeReceptionAchatValue> listTaxeReceptionAchat = new ArrayList<TaxeReceptionAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeReceptionAchatValue> pair = (Map.Entry<Long, TaxeReceptionAchatValue>) it.next();

			listTaxeReceptionAchat.add(pair.getValue());

			it.remove();
		}

		bonReceptionValue.setListTaxeReceptionAchat(listTaxeReceptionAchat);
		// Modifie by Ghazi Atroussi 16/11/2016
		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonReceptionValue.setMontantHTaxe(montantHTaxeTotal);
		bonReceptionValue.setMontantRemise(montantRemiseTotal);
		bonReceptionValue.setMontantTaxe(montantTaxesTotal);
		bonReceptionValue.setMontantTTC(montantTTCTotal);
		bonReceptionValue.setMetrageTotal(metrageTotal);

		bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		return receptionAchatPersistance.update(bonReceptionValue);
	}



	private void updateStockAfterDeleteDetReceptionAchat(ReceptionAchatValue bonReceptionValue) {

		// if(bonReceptionValue.getStock()!=null
		// && bonReceptionValue.getStock() == true

		if (bonReceptionValue.getListSuppDetReceptionAchat() != null
				&& bonReceptionValue.getListSuppDetReceptionAchat().size() > 0) {

			for (ProduitReceptionAchatValue detReceptionAchat : bonReceptionValue.getListSuppDetReceptionAchat()) {

				if (detReceptionAchat.getId() != null && detReceptionAchat.getProduitId() != null
						&& detReceptionAchat.getQuantite() != null) {

					ProduitValue produitValue = produitPersistance
							.rechercheProduitById(detReceptionAchat.getProduitId());

					Double qte = (produitValue.getQuantite() - detReceptionAchat.getQuantite());
					produitValue.setQuantite(qte);
					produitPersistance.modifierProduit(produitValue);
					// MAJ des DEPOTS

					if (bonReceptionValue.getIdDepot() != null) {

						RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

						request.setIdDepot(bonReceptionValue.getIdDepot());
						request.setIdProduit(detReceptionAchat.getProduitId());

						ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
								.rechercheMulticritere(request);

						if (produitDepot != null && produitDepot.getProduitdepotvalues() != null
								&& produitDepot.getProduitdepotvalues().size() > 0) {
							ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
							vProduitDepot.setQuantite(vProduitDepot.getQuantite() - detReceptionAchat.getQuantite());
							produitDepotPersistance.modifier(vProduitDepot);
						}
					}
				}

			}

		}

	}

	private boolean notIn(ProduitSerialisableValue prodSerialisable, ReceptionAchatValue bonReceptionValue) {

		for (ProduitReceptionAchatValue produitReception : bonReceptionValue.getListProduitReceptions()) {

			if (produitReception.isSerialisable() && produitReception.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue vProdSerialisable : produitReception.getProduitsSerialisable()) {

					if (prodSerialisable.getNumSerie() != null && vProdSerialisable.getNumSerie() != null
							&& prodSerialisable.getNumSerie().equals(vProdSerialisable.getNumSerie())) {

						return false;

					}

				}

			}

		}

		return true;
	}

	@Override
	public String delete(Long id) {

		

		receptionAchatPersistance.delete(id);
		
		return "OK";

	}
	
	
	private void deleteElementReglementAchatByRefBR(String reference) {
		
		
		 List<ElementReglementAchatValue> list = elementReglementAchatPersistance.getByRefBLExact(reference);
		 
		 for(ElementReglementAchatValue element : list) {
			 
			 elementReglementAchatPersistance.deleteElementReglementById(element.getId());
			 
		 }
		
	}

	private void updateStockAfterDeleteBonReceptionAchat(ReceptionAchatValue receptionAchat) {

		ReceptionAchatValue receptionAchatValue = receptionAchat;

		for (ProduitReceptionAchatValue detReceptionAchat : receptionAchatValue.getListProduitReceptions()) {

			if (detReceptionAchat.getProduitId() != null && detReceptionAchat.getQuantite() != null) {

				ProduitValue produitValue = produitPersistance.rechercheProduitById(detReceptionAchat.getProduitId());

				// A verifier stock
				// if (produitValue != null && bonLivraisonValue.getStock() == true) {

				if (produitValue != null) {
					Double qte = (produitValue.getQuantite() - detReceptionAchat.getQuantite());
					produitValue.setQuantite(qte);
					produitPersistance.modifierProduit(produitValue);

					if (receptionAchatValue.getIdDepot() != null) {

						/*
						 * RechercheMulticritereProduitDepotValue request = new
						 * RechercheMulticritereProduitDepotValue();
						 * 
						 * request.setIdDepot(receptionAchatValue.getIdDepot());
						 * request.setIdProduit(detReceptionAchat.getProduitId());
						 * 
						 * ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance
						 * .rechercheMulticritere(request);
						 * 
						 * if (produitDepot != null && produitDepot.getProduitdepotvalues() != null &&
						 * produitDepot.getProduitdepotvalues().size() > 0) { ProduitDepotValue
						 * vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
						 * vProduitDepot.setQuantite(vProduitDepot.getQuantite() -
						 * detReceptionAchat.getQuantite());
						 * 
						 * produitDepotPersistance.modifier(vProduitDepot); }
						 * 
						 * 
						 */

						ProduitDepotValue produitDepot = produitDepotPersistance.getByProduitAndDepot(
								detReceptionAchat.getProduitId(), receptionAchatValue.getIdDepot());

						if (produitDepot != null) {

							produitDepot.setQuantite(produitDepot.getQuantite() - detReceptionAchat.getQuantite());

							produitDepotPersistance.modifier(produitDepot);
						}

					}

				}

			}

		}

	}

	private void updateProduitSerialisable(ReceptionAchatValue receptionAchatValue) {

		for (ProduitReceptionAchatValue produitReceptionAchatValue : receptionAchatValue.getListProduitReceptions()) {

			if (produitReceptionAchatValue.isSerialisable()
					&& produitReceptionAchatValue.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : produitReceptionAchatValue.getProduitsSerialisable()) {

					if (prodSerialisable.getNumBonLivraison() == null) {
						produitSerialisablePersistance.supprimerProduitSerialisable(prodSerialisable.getId());
					}

				}

			}
		}

	}

	private boolean hasBonLivraison(ReceptionAchatValue receptionAchatValue) {

		for (ProduitReceptionAchatValue produitReceptionAchatValue : receptionAchatValue.getListProduitReceptions()) {

			if (produitReceptionAchatValue.isSerialisable()
					&& produitReceptionAchatValue.getProduitsSerialisable() != null) {

				for (ProduitSerialisableValue prodSerialisable : produitReceptionAchatValue.getProduitsSerialisable()) {

					if (prodSerialisable.getNumBonLivraison() != null) {

						return true;

					}

				}

			}
		}

		return false;

	}

	@Override
	public List<ReceptionAchatValue> getAll() {
		return receptionAchatPersistance.getAll();
	}

	@Override
	public ResultatRechecheBonReceptionAchatValue rechercherMultiCritere(
			RechercheMulticritereBonReceptionAchatValue request) {
		return receptionAchatPersistance.rechercherMultiCritere(request);
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

	@Override
	public List<BonReceptionVue> getListBonReceptionRefByFournisseur(Long idFournisseur) {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		List<FactureAchatValue> factureVenteList = factureAchatPersistance.getAll();

		for (FactureAchatValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<BonReceptionVue> bonLivraisonlist = receptionAchatPersistance.getReferenceBRByFournisseurId(idFournisseur);
		
		
		List<BonReceptionVue> bonLivraisonlistFinal = new ArrayList<BonReceptionVue>();
		
		for(BonReceptionVue br : bonLivraisonlist) {
			
			if(!listBonLivraisonToRemove.contains(br.getReferenceBR()))
				bonLivraisonlistFinal.add(br) ;
				
		}

	//	bonLivraisonlist.removeAll(listBonLivraisonToRemove);

		return bonLivraisonlistFinal;
	}

	@Override
	public List<ReceptionAchatFactureVue> getAllListBonReceptionRefByFournisseur(Long idFournisseur) {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		List<FactureAchatValue> factureVenteList = factureAchatPersistance.getAll();

		for (FactureAchatValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<ReceptionAchatFactureVue> bonLivraisonlist = receptionAchatPersistance
				.getListBRByFournisseurId(idFournisseur);

		List<ReceptionAchatFactureVue> result = new ArrayList<ReceptionAchatFactureVue>();

		for (ReceptionAchatFactureVue livraison : bonLivraisonlist) {

			/*
			 * if (livraison.getMarcheId() != null) { MarcheValue marche =
			 * marcheDomaine.getById(livraison.getMarcheId());
			 * livraison.setMarche(marche.getDesignation()); }
			 * 
			 * 
			 */

			if (!listBonLivraisonToRemove.contains(livraison.getReference()))
				result.add(livraison);

		}
		return result;
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonReceptionList, Long factureAchatId) {

		List<ReceptionAchatValue> listLivraisonVenteValue = receptionAchatPersistance
				.getProduitElementList(refBonReceptionList);

		List<ProduitReceptionAchatValue> listDetLivraisonVente = new ArrayList<ProduitReceptionAchatValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listLivraisonVenteValue.size() > 0) {
			listProduitElementValue.setPartieIntId(listLivraisonVenteValue.get(FIRST_INDEX).getPartieIntersseId());
			listProduitElementValue.setDateLivrison(listLivraisonVenteValue.get(FIRST_INDEX).getDateLivraison());
			listProduitElementValue.setIdDepot(listLivraisonVenteValue.get(FIRST_INDEX).getIdDepot());
			// Added By Ghazi on 25/05/2018
			// listProduitElementValue.setIdMarche(listLivraisonVenteValue.get(FIRST_INDEX).getMarcheId());

		}

		for (ReceptionAchatValue livraisonVente : listLivraisonVenteValue) {

			for (ProduitReceptionAchatValue detLivraisonVente : livraisonVente.getListProduitReceptions()) {

				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<Long, String>, List<ProduitReceptionAchatValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<ProduitReceptionAchatValue>>();

		for (ProduitReceptionAchatValue detail : listDetLivraisonVente) {
			Long produitKey = detail.getProduitId();
			// String choixKey = detail.getChoix();

			String choixKey = "CH1";

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<ProduitReceptionAchatValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		//ProduitValue produitValue = null;
		ArticleValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetFactureAchatValue> listDetFactureVente = new ArrayList<DetFactureAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<ProduitReceptionAchatValue>> pair = (Map.Entry<Map<Long, String>, List<ProduitReceptionAchatValue>>) it
					.next();

			DetFactureAchatValue element = new DetFactureAchatValue();

			Long produitId = null;
			String choix = null;

			Map<Long, String> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<Long, String> produitIdchoixPair = (Map.Entry<Long, String>) produitIdchoixIt.next();
			produitId = produitIdchoixPair.getKey();
			choix = produitIdchoixPair.getValue();

			element.setProduitId(produitId);
			element.setChoix(choix);
			element.setFactureAchatId(factureAchatId);
			// el
			Double sommeQuantite = ZERO;
			Long sommeNombreColis = ZERO_LONG;
		
			
			String numeroSeries = "";

			for (ProduitReceptionAchatValue detail : pair.getValue()) {

				if (detail.getQuantite() != null) {
					sommeQuantite = detail.getQuantite() + sommeQuantite;
				}

				/*
				 * if (detail.getNombreColis() != null) { sommeNombreColis =
				 * detail.getNombreColis() + sommeNombreColis; }
				 */

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

				ProduitReceptionAchatValue detail = pair.getValue().get(0);
				if (detail != null) {
					// element.setUnite(detail.getUnite());
					// element.setRemise(detail.getRemise());
					// TODO changed
					// element.setPrixUnitaireHT(detail.getPrixUnitaireHT());

					element.setPrixUnitaireHT(detail.getPrixUnitaire());
					
					element.setRemise(detail.getRemise());
				}
			}
			
			//TODO A VERIFIER PAR SAMER CAS (pair.getValue().size() == 1 || pair.getValue().size() > 1)  10.09.20
			

			if (element.getProduitId() != null) {
				
				//produitValue = produitPersistance.rechercheProduitById(element.getProduitId());
				produitValue=articlePersistance.getArticleParId(element.getProduitId());
				
				if (produitValue != null) {

					element.setSerialisable(produitValue.isSerialisable());
					
					
					element.setProduitDesignation(produitValue.getDesignation());
					element.setProduitReference(produitValue.getRef());

					if (factureAchatId != null) {

						DetFactureAchatValue detFactureVenteValue = detFactureAchatPersistance
								.getByFactureAchatAndProduit(factureAchatId, element.getProduitId(),
										element.getChoix());

						if (detFactureVenteValue != null) {
							
							
							
							element.setProduitDesignation(detFactureVenteValue.getProduitDesignation());
							element.setProduitReference(detFactureVenteValue.getProduitReference());

							boolean detailIdNotExist = true;
							for (ProduitReceptionAchatValue detail : listDetLivraisonVente) {
								if (detail.getId() == detFactureVenteValue.getId())
									detailIdNotExist = false;
							}

							if (detailIdNotExist) {
								element.setId(detFactureVenteValue.getId());
								element.setRemise(detFactureVenteValue.getRemise());
								
							
							}

						}
					}

					

					/*** appel fonction rechercheMC prix special *****/

					/******/
					// TO O DO A changer
					// Commented

					if (element.getPrixUnitaireHT() == null && produitValue.getPu() != null)
						element.setPrixUnitaireHT(produitValue.getPu());

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
			Collections.sort(listDetFactureVente, new DetFactureAchatValidateComparator());
		}

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetFactureAchat(listDetFactureVente);

		return listProduitElementValue;
	}

	private Set<ProduitSerialisableValue> getListProduitSerialisableParNumerSeries(String numeroSeries,Long produitId) {
		String numero[] = numeroSeries.split(SEPARATOR_NUMERO_SERIE);

		List<String> listNumeroSeries = new ArrayList<>();

		Collections.addAll(listNumeroSeries, numero);

		return produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,produitId);
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

		Long numeroBonReception = currentGuichetAnnuel.getNumReferenceBonReceptionCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonReception = new StringBuilder("");

		if (currentGuichetAnnuel.getPrefixeBonReception() != null)
			vNumBonReception.append(currentGuichetAnnuel.getPrefixeBonReception());

		if (numeroBonReception > 0 && numeroBonReception < 10) {
			vNumBonReception.append("000");
		} else if (numeroBonReception >= 10 && numeroBonReception < 100) {
			vNumBonReception.append("00");
		}

		else if (numeroBonReception >= 100 && numeroBonReception < 1000) {
			vNumBonReception.append("0");
		}

		vNumBonReception.append(numeroBonReception);

		currentGuichetAnnuel.setNumReferenceBonReceptionCourante(new Long(numeroBonReception + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBRAnnuel(currentGuichetAnnuel);

		return vNumBonReception.toString();
	}

	@Override
	public ReceptionAchatValue validerFactureAvoirRetour(String reference) {
		ReceptionAchatValue receptionAchatValue = new ReceptionAchatValue();
		
		FactureVenteValue factureAvoir = facturePersistance.getFactureByReference(reference);
		
		if(factureAvoir != null && factureAvoir.getNature() != null && factureAvoir.getType().equals(IConstanteCommerciale.FACTURE_TYPE_AVOIR) && factureAvoir.getNature().equals(IConstanteCommerciale.FACTURE_NATURE_RETOUR)){
			
			receptionAchatValue.setRefAvoirRetour(factureAvoir.getReference());	
			receptionAchatValue.setPartieIntersseId(factureAvoir.getPartieIntId());
			receptionAchatValue.setRefFacture(factureAvoir.getInfoLivraison());
			receptionAchatValue.setIdDepot(factureAvoir.getIdDepot());
			//receptionAchatValue.setIdDepot(factureAvoir.getf);
			
			 List<ProduitReceptionAchatValue> listProduitReceptions = new ArrayList<>();
			
			for(DetFactureVenteValue det : factureAvoir.getListDetFactureVente()) {
				ProduitReceptionAchatValue prodReception = new ProduitReceptionAchatValue();
				
				prodReception.setProduitId(det.getProduitId());
				prodReception.setQuantite(det.getQuantite());
				prodReception.setPrixUnitaire(det.getPrixUnitaireHT());
				prodReception.setSerialisable(det.isSerialisable());
				
				if(det.isSerialisable() && det.getNumeroSeries() != null ) {
					
					String numSeries[]  = det.getNumeroSeries().split("&");
					
					List<String> listNumeroSeries = new ArrayList<>();
					
					Collections.addAll(listNumeroSeries, numSeries);
					
					prodReception.setProduitsSerialisable(produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,prodReception.getProduitId()));
					
				}
				
				listProduitReceptions.add(prodReception);
			}
			
			receptionAchatValue.setDateLivraison(factureAvoir.getDate());
			receptionAchatValue.setListProduitReceptions(listProduitReceptions);
			
		}
		
		else
			
			
		{
			
			receptionAchatValue.setRefAvoirRetour(null);	
			
		}
	
	 return receptionAchatValue;
	}



	@Override
	public ReceptionAchatValue validerBL(String reference) {
		ReceptionAchatValue receptionAchatValue = new ReceptionAchatValue();
		
		LivraisonVenteValue bonLivraison = bonLivraisonPersistance.getByReference(reference);
		
		if(bonLivraison != null ){
			
			receptionAchatValue.setRefBL(bonLivraison.getReference());	
			receptionAchatValue.setPartieIntersseId(bonLivraison.getPartieIntId());
			receptionAchatValue.setIdDepot(bonLivraison.getIdDepot());
			
			 List<ProduitReceptionAchatValue> listProduitReceptions = new ArrayList<>();
			
			for(DetLivraisonVenteValue det : bonLivraison.getListDetLivraisonVente()) {
				ProduitReceptionAchatValue prodReception = new ProduitReceptionAchatValue();
				
				prodReception.setProduitId(det.getProduitId());
				prodReception.setQuantite(det.getQuantite());
				prodReception.setPrixUnitaire(det.getPrixUnitaireHT());
				prodReception.setSerialisable(det.isSerialisable());
				
				if(det.isSerialisable() && det.getNumeroSeries() != null ) {
					
					String numSeries[]  = det.getNumeroSeries().split("&");
					
					List<String> listNumeroSeries = new ArrayList<>();
					
					Collections.addAll(listNumeroSeries, numSeries);
					
					prodReception.setProduitsSerialisable(produitSerialisablePersistance.getByNumeroSerieList(listNumeroSeries,prodReception.getProduitId()));
					
				}
				
				listProduitReceptions.add(prodReception);
			}
			
			receptionAchatValue.setDateLivraison(bonLivraison.getDate());
			receptionAchatValue.setListProduitReceptions(listProduitReceptions);
			
		}
		
	
	
	 return receptionAchatValue;
	}

	private String getReferenceReceptionFromGuichetMensuel(final Calendar pDateBonLiv , final boolean increment) {

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumBonReceptionReference(pDateBonLiv); 
		String vNumGuichetPrefix=this.guichetierMensuelDomaine.getPrefixBonReception(pDateBonLiv);
		
		
		
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
		vGuichetValeur.setNumReferenceBonReceptionCourante(new Long(vNumGuichetBonLiv + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetBonReceptionMensuel(vGuichetValeur); 
		
		

		return vNumBonLiv.toString();

	}
	
	
	private String getReferenceReceptionFromGuichetMensuelNonDeclarer(final Calendar pDateBonLiv , final boolean increment) {

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumBonReceptionReferenceNonDeclarer(pDateBonLiv); 
		String vNumGuichetPrefix=this.guichetierMensuelDomaine.getPrefixBonReceptionNonDeclarer(pDateBonLiv);
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
		vGuichetValeur.setNumReferenceBonReceptionNonDeclarerCourante(new Long(vNumGuichetBonLiv + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetBonReceptionNonDeclarerMensuel(vGuichetValeur); 
		
		

		return vNumBonLiv.toString();

	}

	


	@Override
	public String getCurrentReferenceMensuel(Calendar instance, boolean b) {
		return this.getReferenceReceptionFromGuichetMensuel(instance, b);
	}



	@Override
	public String getCurrentReferenceMensuelByType(String type, Calendar instance, boolean b) {
		
		if(type.equals("declarer"))
			return getReferenceReceptionFromGuichetMensuel(instance, b);
		else
			
			return getReferenceReceptionFromGuichetMensuelNonDeclarer(instance, b);
	
	}



	@Override
	public List<BonReceptionVue> getListBonReceptionRefByFournisseurDeclarer(Long idFournisseur) {


		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		List<FactureAchatValue> factureVenteList = factureAchatPersistance.getAll();

		for (FactureAchatValue livraisonVente : factureVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoLivraison() != null) {

				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonLivraisonToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<BonReceptionVue> bonLivraisonlist = receptionAchatPersistance.getReferenceBRByFournisseurIdDeclarer(idFournisseur);

		bonLivraisonlist.removeAll(listBonLivraisonToRemove);

		return bonLivraisonlist;
	}
	   private boolean estNonVide(Long val) {
				return val != null && !"".equals(val) && !"undefined".equals(val) && !"null".equals(val);
			}



	@Override
	public ListProduitElementValue getProduitElementListWithoutRegroupement(List<String> refBonReceptionList,
			Long factureAchatId) {


		List<ReceptionAchatValue> listLivraisonVenteValue = receptionAchatPersistance
				.getProduitElementList(refBonReceptionList);

		List<ProduitReceptionAchatValue> listDetLivraisonVente = new ArrayList<ProduitReceptionAchatValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listLivraisonVenteValue.size() > 0) {
			listProduitElementValue.setPartieIntId(listLivraisonVenteValue.get(FIRST_INDEX).getPartieIntersseId());
			listProduitElementValue.setDateLivrison(listLivraisonVenteValue.get(FIRST_INDEX).getDateLivraison());
			listProduitElementValue.setIdDepot(listLivraisonVenteValue.get(FIRST_INDEX).getIdDepot());
			
			listProduitElementValue.setRefexterne(listLivraisonVenteValue.get(FIRST_INDEX).getRefexterne());
			
			// Added By Ghazi on 25/05/2018
			// listProduitElementValue.setIdMarche(listLivraisonVenteValue.get(FIRST_INDEX).getMarcheId());

		}

		for (ReceptionAchatValue livraisonVente : listLivraisonVenteValue) {

			for (ProduitReceptionAchatValue detLivraisonVente : livraisonVente.getListProduitReceptions()) {

				listDetLivraisonVente.add(detLivraisonVente);
			}
		}

		Map<Map<Long, String>, List<ProduitReceptionAchatValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<ProduitReceptionAchatValue>>();

		
		Double sommeQuantite = ZERO;
		
		String choixKey = "CH1";
		
		List<DetFactureAchatValue> listDetFactureVente = new ArrayList<DetFactureAchatValue>();
		
		
		
		for (ProduitReceptionAchatValue detail : listDetLivraisonVente) {
			
			
			DetFactureAchatValue element = new DetFactureAchatValue();
			
			
			
			 element.setChoix(choixKey);
			//element.setFactureAchatId(factureAchatId);
			
					
			element.setProduitId(detail.getProduitId());
			
			element.setProduitReference(detail.getReferenceArticle());
			element.setProduitDesignation(detail.getDesignation());
			
			element.setRemise(detail.getRemise());
					
			element.setQuantite(detail.getQuantite());
			
			element.setPrixUnitaireHT(detail.getPrixUnitaire());
			
			element.setPrixTotalHT(detail.getPrixTotalHT());
			
			
			element.setUnite(detail.getUnite());
			

			if (element.getPrixUnitaireHT() != null && element.getQuantite() != null) {
				element.setPrixTotalHT(element.getPrixUnitaireHT() * element.getQuantite());
			}
			
			
			element.setTaxeId(detail.getTaxeId());
			
			
		
			
			element.setNumeroSeries(detail.getNumeroSeries());
			
			if(detail.getNumeroSeries() != null )
				element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(detail.getNumeroSeries(),element.getProduitId()));
			
	
			if (detail.getQuantite() != null) {
				sommeQuantite = detail.getQuantite() + sommeQuantite;
			}
			
			
			listDetFactureVente.add(element);
			

		}



		if (listDetFactureVente.size() > 0) {
			Collections.sort(listDetFactureVente, new DetFactureAchatValidateComparator());
		}

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetFactureAchat(listDetFactureVente);

		return listProduitElementValue;
	}



	@Override
	public ReceptionAchatValue getByReference(String reference) {
		// TODO Auto-generated method stub
		return receptionAchatPersistance.getByReference(reference);
	}

}
