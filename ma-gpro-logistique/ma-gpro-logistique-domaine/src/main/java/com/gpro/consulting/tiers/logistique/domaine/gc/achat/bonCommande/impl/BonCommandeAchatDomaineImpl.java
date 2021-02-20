package com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.impl;

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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IUniteArticleDomaine;
import com.gpro.consulting.tiers.commun.service.elementBase.IArticleService;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.CommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ProduitCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.RechercheMulticritereBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.ResultatRechecheBonCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.boncommande.value.TaxeCommandeAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.achat.reception.value.ReceptionAchatValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.IBonCommandeAchatDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities.DetLivraisonVenteComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.boncommande.IBonCommandeAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetAnnuelPersistance;

/**
 * The Class BonCommandeAchatDomaineImpl.
 * 
 * @author Hamdi Etteieb
 */
@Component
public class BonCommandeAchatDomaineImpl implements IBonCommandeAchatDomaine {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(BonCommandeAchatDomaineImpl.class);

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "-";

	/** The Constant ZERO_D. */
	private static final Double ZERO_D = 0D;
	

	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;

	/** The Constant TAXE_ID_FODEC. */
	private static final Long TAXE_ID_FODEC = 1L;

	/** The Constant TAXE_ID_TIMBRE. */
	private static final Long TAXE_ID_TIMBRE = 3L;

	// private static final Double ZERO = 0D;

	/** The bon commande persistance. */
	@Autowired
	private IBonCommandeAchatPersistance bonCommandePersistance;

	/** The guichet annuel Domaine. */
	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	/** The produit service. */
	@Autowired
	private IProduitService produitService;

	/** The bon livraison persistance. */
	@Autowired
	private IReceptionAchatPersistance receptionAchatPersistance;
	
	@Autowired
	private IArticleService articleService;
	
	
	@Autowired
	private IGuichetMensuelDomaine guichetierMensuelDomaine;
	
	@Autowired
	private IUniteArticleDomaine uniteArticleDomaine;
	
	
	

	/**
	 * Creates the.
	 *
	 * @param bonCommandeValue
	 *            the bon commande value
	 * @return the string
	 */
	@Override
	public String create(CommandeAchatValue bonCommandeValue) {

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

		Double montantTaxeFodec = ZERO_D;
		Double montantTaxeTVA = ZERO_D;
		Double montantTaxeTimbre = ZERO_D;

		Double assietteFodec = ZERO_D;

		Double montantHTaxeTotal = ZERO_D;
		Double montantTaxesTotal = ZERO_D;
		Double montantRemiseTotal = ZERO_D;

		// System.out.println(" list taxe:
		// "+bonCommandeValue.getListProduitTaxes().size());

		//logger.info("----- bonCommandeValue.getReference() ----------" + bonCommandeValue.getReference());

		if ((bonCommandeValue.getReference() != null && bonCommandeValue.getReference().equals(""))
				|| bonCommandeValue.getReference() == null) {

			//bonCommandeValue.setReference(this.getCurrentReferenceByType(bonCommandeValue.getType(),bonCommandeValue.getDateIntroduction(),true));
			
			
			bonCommandeValue.setReference(this.getReferenceBonCommandeAchatFromGuichetMensuel(Calendar.getInstance(),true));  
			

			//logger.info("----- auto reference ----------" + bonCommandeValue.getReference());

		}else
			
		{
			 if(bonCommandeValue.getRefAvantChangement() != null && bonCommandeValue.getReference().equals(bonCommandeValue.getRefAvantChangement())) {
				 
				this.getReferenceBonCommandeAchatFromGuichetMensuel(Calendar.getInstance(),true);
				 //this.getCurrentReferenceByType(bonCommandeValue.getType(),bonCommandeValue.getDateIntroduction(),true);
				
           }
			
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (ProduitCommandeAchatValue produitCommande : bonCommandeValue.getListProduitCommandes()) {

			if (produitCommande.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitCommande.getQuantite();
			}

			if (produitCommande.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			}

			produitCommande.setDateLivraison(bonCommandeValue.getDateLivraison());
			
			//ProduitValue produitValue = produitService.rechercheProduitById(produitCommande.getProduitId());
			
			ArticleValue articleValue=new ArticleValue();
			articleValue.setId(produitCommande.getProduitId());
			
			
			ArticleValue produitValue=articleService.rechercheArticleParId(articleValue);
			
			
			if(produitCommande.getTaxeId() == null)
			produitCommande.setTaxeId(produitValue.getIdTaxe());
			
			
			
			Double totalProduitCommande = (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {

				produitTaxeMap.put(produitValue.getIdTaxe(), totalProduitCommande);
				

			} else {
				Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe()) + totalProduitCommande;
				produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

			}

		}

		// Calcul des taxes

		Map<Long, TaxeCommandeAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeCommandeAchatValue>();
		// modification
		for (TaxeCommandeAchatValue taxeLivraison : bonCommandeValue.getListProduitTaxes()) {
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
				montantTaxeTVA = ZERO_D;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeLivraisonIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}

		// Fin de calcul TVA
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		Iterator it = taxeLivraisonIdTaxeMap.entrySet().iterator();
		List<TaxeCommandeAchatValue> listTaxeLivraison = new ArrayList<TaxeCommandeAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeCommandeAchatValue> pair = (Map.Entry<Long, TaxeCommandeAchatValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}

		bonCommandeValue.setListProduitTaxes(listTaxeLivraison);

		bonCommandeValue.setQuantite(quantiteCommandeVenteTotal);
		bonCommandeValue.setPrixTotal(coutCommandeVenteTotal);
		bonCommandeValue.setMontantTaxe(montantTaxesTotal);
		// System.out.println("prix total: "+bonCommandeValue.getPrixTotal()+"
		// montantTaxesTotal: "+montantTaxesTotal);
		Double montantTTC = bonCommandeValue.getPrixTotal() + montantTaxesTotal;
		// System.out.println("montantTTC: "+montantTTC);
		bonCommandeValue.setMontantTTC(montantTTC);
		return bonCommandePersistance.create(bonCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.
	 * IBonCommandeAchatDomaine#getById(java.lang.Long)
	 */
	@Override
	public CommandeAchatValue getById(Long id) {
		return bonCommandePersistance.getById(id);
	}

	/**
	 * Update.
	 *
	 * @param bonCommandeValue
	 *            the bon commande value
	 * @return the string
	 */
	@Override
	public String update(CommandeAchatValue bonCommandeValue) {

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

		//
		Double montantTaxeFodec = ZERO_D;
		Double montantTaxeTVA = ZERO_D;
		Double montantTaxeTimbre = ZERO_D;

		Double assietteFodec = ZERO_D;

		Double montantHTaxeTotal = ZERO_D;
		Double montantTaxesTotal = ZERO_D;

		Double montantRemiseTotal = ZERO_D;

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (ProduitCommandeAchatValue produitCommande : bonCommandeValue.getListProduitCommandes()) {

			if (produitCommande.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitCommande.getQuantite();
			}

			if (produitCommande.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			}

			//ProduitValue produitValue = produitService.rechercheProduitById(produitCommande.getProduitId());
			
			
			ArticleValue articleValue =new ArticleValue();
			articleValue.setId(produitCommande.getProduitId());
			ArticleValue produitValue=articleService.rechercheArticleParId(articleValue);
			
			
	
	
			if(produitCommande.getTaxeId() == null)
			produitCommande.setTaxeId(produitValue.getIdTaxe());
			
		
			Double totalProduitCommande = (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {

				produitTaxeMap.put(produitValue.getIdTaxe(), totalProduitCommande);

			} else {
				Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe()) + totalProduitCommande;
				produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

			}

			produitCommande.setDateLivraison(bonCommandeValue.getDateLivraison());
		}

		

		// Calcul des taxes

		Map<Long, TaxeCommandeAchatValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeCommandeAchatValue>();
		// modification
		for (TaxeCommandeAchatValue taxeLivraison : bonCommandeValue.getListProduitTaxes()) {
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
				montantTaxeTVA = ZERO_D;
				if (produitTaxeMap.containsKey(taxe))
					montantTaxeTVA = produitTaxeMap.get(taxe) * taxeLivraisonIdTaxeMap.get(taxe).getPourcentage() / 100;

				taxeLivraisonIdTaxeMap.get(taxe).setMontant(montantTaxeTVA);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTVA;

			}

		}

		// Fin de calcul TVA
		if (taxeLivraisonIdTaxeMap.containsKey(TAXE_ID_TIMBRE)) {
			if (taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant() != null) {
				montantTaxeTimbre = taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).getMontant();

				taxeLivraisonIdTaxeMap.get(TAXE_ID_TIMBRE).setMontant(montantTaxeTimbre);
				montantTaxesTotal = montantTaxesTotal + montantTaxeTimbre;
			}
		}

		Iterator it = taxeLivraisonIdTaxeMap.entrySet().iterator();
		List<TaxeCommandeAchatValue> listTaxeLivraison = new ArrayList<TaxeCommandeAchatValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeCommandeAchatValue> pair = (Map.Entry<Long, TaxeCommandeAchatValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}

		bonCommandeValue.setListProduitTaxes(listTaxeLivraison);
		
		bonCommandeValue.setQuantite(quantiteCommandeVenteTotal);
		bonCommandeValue.setPrixTotal(coutCommandeVenteTotal);

		bonCommandeValue.setMontantTaxe(montantTaxesTotal);
		Double montantTTC = bonCommandeValue.getPrixTotal() + montantTaxesTotal;
		bonCommandeValue.setMontantTTC(montantTTC);

		return bonCommandePersistance.update(bonCommandeValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.
	 * IBonCommandeAchatDomaine#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		bonCommandePersistance.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.
	 * IBonCommandeAchatDomaine#getAll()
	 */
	@Override
	public List<CommandeAchatValue> getAll() {
		return bonCommandePersistance.getAll();
	}

	/**
	 * Rechercher multi critere.
	 *
	 * @param request
	 *            the request
	 * @return the resultat recheche bon commande value
	 */
	@Override
	public ResultatRechecheBonCommandeAchatValue rechercherMultiCritere(
			RechercheMulticritereBonCommandeAchatValue request) {
		return bonCommandePersistance.rechercherMultiCritere(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.logistique.domaine.gc.achat.bonCommande.
	 * IBonCommandeAchatDomaine#getListBonCommandeRefByClient(java.lang.Long)
	 */
	@Override
	public List<CommandeAchatValue> getListBonCommandeRefByClient(Long idClient) {

		List<String> listBonCommandeToRemove = new ArrayList<String>();

		List<ReceptionAchatValue> receptionAchatList = receptionAchatPersistance.getAll();

		for (ReceptionAchatValue commandeVente : receptionAchatList) {

			String infoSortieSplitted[];

			if (commandeVente.getRefCommande() != null) {

				infoSortieSplitted = commandeVente.getRefCommande().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonCommandeToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<CommandeAchatValue> bonCommandelist = bonCommandePersistance.getReferenceBCByClientId(idClient);
		
		List<CommandeAchatValue> listeFinal = new ArrayList<CommandeAchatValue>();
		
		for(CommandeAchatValue vC : bonCommandelist) {
			
			if(!listBonCommandeToRemove.contains(vC.getReference()))
				listeFinal.add(vC) ;
			
		}
		

		//bonCommandelist.removeAll(listBonCommandeToRemove);

		return listeFinal;
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
	 * @param pDateBonCommande
	 *            the date bon commande
	 * @return the numero bon commande
	 */


	@Override
	public String getCurrentReferenceByType(String type,Calendar instance, boolean increment) {

	 if(type != null )
	 {
		 if(type.equals(IConstanteCommerciale.COMMANDE_TYPE_COMMANDE)) return getCurrentReferenceCommande(instance, increment);
		 else
			 if(type.equals(IConstanteCommerciale.COMMANDE_TYPE_DEVIS)) return getCurrentReferenceDevis(instance, increment);
	 }
	 
	 
	 
	 return "";
	}
	
	

	
	public String getCurrentReferenceCommande(Calendar instance, boolean increment) {

		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();
		
		Long numeroBonCommandeAchat = currentGuichetAnnuel.getNumReferenceCommandeAchatCourante() ;
		
		
		
	//	Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
	//	int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonCommandeAchat = new StringBuilder("");
		
		if(currentGuichetAnnuel.getPrefixeBcAchat() != null)
		vNumBonCommandeAchat.append(currentGuichetAnnuel.getPrefixeBcAchat());
		
    	if( numeroBonCommandeAchat >0 && numeroBonCommandeAchat<10) {
    		vNumBonCommandeAchat.append("000");
    	}
    	else if(numeroBonCommandeAchat >=10 && numeroBonCommandeAchat<100)
    		{vNumBonCommandeAchat.append("00");}
    	
    	
    	else if(numeroBonCommandeAchat >=100 && numeroBonCommandeAchat<1000)
		{vNumBonCommandeAchat.append("0");}
    	
    	
    	
    	vNumBonCommandeAchat.append( numeroBonCommandeAchat);
		

		currentGuichetAnnuel.setNumReferenceCommandeAchatCourante(new Long(numeroBonCommandeAchat + 1L));
				
		
		
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetAnnuelDomaine
				.modifierGuichetBCAchatAnnuel(currentGuichetAnnuel);
		
		
		
		return vNumBonCommandeAchat.toString();
	}
	
	public String getCurrentReferenceDevis(Calendar instance, boolean increment) {

		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();
		
		Long numeroBonCommandeAchat = currentGuichetAnnuel.getNumReferenceDevisAchatCourante() ;
		
		
		
	//	Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
	//	int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonCommandeAchat = new StringBuilder("");
		
		if(currentGuichetAnnuel.getPrefixeDevisAchat() != null)
		vNumBonCommandeAchat.append(currentGuichetAnnuel.getPrefixeDevisAchat());
		
    	if( numeroBonCommandeAchat >0 && numeroBonCommandeAchat<10) {
    		vNumBonCommandeAchat.append("000");
    	}
    	else if(numeroBonCommandeAchat >=10 && numeroBonCommandeAchat<100)
    		{vNumBonCommandeAchat.append("00");}
    	
    	
    	else if(numeroBonCommandeAchat >=100 && numeroBonCommandeAchat<1000)
		{vNumBonCommandeAchat.append("0");}
    	
    	
    	
    	vNumBonCommandeAchat.append( numeroBonCommandeAchat);
		

		currentGuichetAnnuel.setNumReferenceDevisAchatCourante(new Long(numeroBonCommandeAchat + 1L));
				
		
		
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetAnnuelDomaine
				.modifierGuichetDevisAchatAnnuel(currentGuichetAnnuel);
		
		
		
		return vNumBonCommandeAchat.toString();
	}

	/*
	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long receptionAchatId) {

		
		
		List<CommandeAchatValue> listCommandeVenteValue = bonCommandePersistance
				.getProduitElementList(refBonCommandesList);

		List<ProduitCommandeAchatValue> listDetCommandeValue = new ArrayList<ProduitCommandeAchatValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listCommandeVenteValue.size() > 0) {
	
			listProduitElementValue.setPartieIntId(listCommandeVenteValue.get(FIRST_INDEX).getPartieIntersseId());
			listProduitElementValue.setDateLivraison(listCommandeVenteValue.get(FIRST_INDEX).getDateLivraison());
		
			

		}

		for (CommandeAchatValue commandeVente : listCommandeVenteValue) {

			for (ProduitCommandeAchatValue detCommandeVente : commandeVente.getListProduitCommandes()) {

				listDetCommandeValue.add(detCommandeVente);
			}
		}

		Map<Map<Long, String>, List<ProduitCommandeAchatValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<ProduitCommandeAchatValue>>();

		for (ProduitCommandeAchatValue detail : listDetCommandeValue) {
			Long produitKey = detail.getProduitId();
			
			String choixKey = "CH1";
		//	String choixKey = detail.getChoix();

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<ProduitCommandeAchatValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ArticleValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetFactureVente = new ArrayList<DetLivraisonVenteValue>();
		
		
		Long ordre = new Long(0);
		
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<ProduitCommandeAchatValue>> pair = (Map.Entry<Map<Long, String>, List<ProduitCommandeAchatValue>>) it
					.next();

			DetLivraisonVenteValue element = new DetLivraisonVenteValue();

			Long produitId = null;
			String choix = null;

			Map<Long, String> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<Long, String> produitIdchoixPair = (Map.Entry<Long, String>) produitIdchoixIt.next();
			produitId = produitIdchoixPair.getKey();
			choix = produitIdchoixPair.getValue();

			element.setProduitId(produitId);
			element.setChoix(choix);
			element.setLivraisonVenteId(receptionAchatId);
			// el
			Double sommeQuantite = ZERO;
			Long sommeNombreColis = ZERO_LONG;

			String numeroSeries = "";

			for (ProduitCommandeAchatValue detail : pair.getValue()) {
				
				if (detail.getQuantite() != null) {
					sommeQuantite = detail.getQuantite() + sommeQuantite;
				}


			}

		


			element.setQuantite(sommeQuantite);
			element.setNombreColis(sommeNombreColis);

			if (pair.getValue().size() > 0) {

				ProduitCommandeAchatValue detail = pair.getValue().get(0);
				if (detail != null) {
					
				
					element.setPrixUnitaireHT(detail.getPrixUnitaire());
				}
			}

			ArticleValue articleValue=new ArticleValue();
			articleValue.setId(element.getProduitId());
			
			if (element.getProduitId() != null) {
				
				//produitValue = produitService.rechercheProduitById(element.getProduitId());
				
				produitValue=articleService.rechercheArticleParId(articleValue);
				
				if (produitValue != null) {

					element.setSerialisable(produitValue.isSerialisable());
			

					element.setProduitDesignation(produitValue.getDesignation());
					element.setProduitReference(produitValue.getRef());
					element.setTaxeId(produitValue.getIdTaxe());
					
				
				
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
			
			
			ordre++;
			
			element.setFicheId(ordre);
			

			listDetFactureVente.add(element);

			it.remove();

		}

		if (listDetFactureVente.size() > 0) {
		
			//Collections.sort(listDetFactureVente, new DetFactureVenteValidateComparator());
			//ajuter par samer le 09.06.20 afin d'utiliser le meme ordre de saisie BL
			Collections.sort(listDetFactureVente, new DetLivraisonVenteComparator());
			
		}

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetLivraisonVente(listDetFactureVente);

		return listProduitElementValue;
	}
	*/
	
	
	
	
	
	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long receptionAchatId) {

		
		
		List<CommandeAchatValue> listCommandeVenteValue = bonCommandePersistance
				.getProduitElementList(refBonCommandesList);

		List<ProduitCommandeAchatValue> listDetCommandeValue = new ArrayList<ProduitCommandeAchatValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listCommandeVenteValue.size() > 0) {
	
			listProduitElementValue.setPartieIntId(listCommandeVenteValue.get(FIRST_INDEX).getPartieIntersseId());
			listProduitElementValue.setDateLivraison(listCommandeVenteValue.get(FIRST_INDEX).getDateLivraison());
		
			

		}
		
		String choixKey = "CH1";
		
		Long ordre = new Long(0);	
		
		List<DetLivraisonVenteValue> listDetFactureVente = new ArrayList<DetLivraisonVenteValue>();


		for (CommandeAchatValue commandeVente : listCommandeVenteValue) {

			for (ProduitCommandeAchatValue element : commandeVente.getListProduitCommandes()) {

				//listDetCommandeValue.add(detCommandeVente);
				
			
				
				if (element.getProduitId() != null) {
					
					
					
					DetLivraisonVenteValue det = new DetLivraisonVenteValue();
					
					det.setFicheId(ordre);
					
					det.setChoix(choixKey);

					det.setProduitId(element.getProduitId());
					det.setTaxeId(element.getTaxeId());
					det.setUnite(element.getUnite());
					
					det.setPrixUnitaireHT(element.getPrixUnitaire());
					det.setQuantite(element.getQuantite());
					
					
					if (element.getPrixUnitaire() != null && element.getQuantite() != null)
						det.setPrixTotalHT(element.getPrixUnitaire() * element.getQuantite() );

				
					
					ArticleValue articleValue=new ArticleValue();
					articleValue.setId(element.getProduitId());
					
					//produitValue = produitService.rechercheProduitById(element.getProduitId());
					
					ArticleValue produitValue=articleService.rechercheArticleParId(articleValue);
					
					if (produitValue != null) {



						det.setProduitDesignation(produitValue.getDesignation());
						det.setProduitReference(produitValue.getRef());
					
					}
					
					ordre++;
				
				
				listDetFactureVente.add(det);
								
				}
					
		
				
			}
		}
		
	

		listProduitElementValue.setNombreResultaRechercher(listDetFactureVente.size());
		listProduitElementValue.setListDetLivraisonVente(listDetFactureVente);

		return listProduitElementValue;
	}
	
	
	private String getReferenceBonCommandeAchatFromGuichetMensuel(final Calendar pDateBonLiv , final boolean increment) {

		Long vNumGuichetBonLiv = this.guichetierMensuelDomaine.getNextNumBonCommandeReference();
		String vNumGuichetPrefix=this.guichetierMensuelDomaine.getPrefix();

		/** Année courante. */
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

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016) * 12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		
		vGuichetValeur.setNumReferenceBonCommandeCourante(new Long(vNumGuichetBonLiv + 1L));  
	

		/** Modification de la valeur en base du numéro. */
	

		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetBonCommandeMensuel(vGuichetValeur);
		
		

		return vNumBonLiv.toString();

	}

	@Override
	public String getCurrentReferenceMensuel(Calendar instance, boolean b) {
		
		return this.getReferenceBonCommandeAchatFromGuichetMensuel(instance, b);
	}

}
