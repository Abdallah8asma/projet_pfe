package com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.impl;

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
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.service.elementBase.IProduitService;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.CommandeVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.RechercheMulticritereBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.ResultatRechecheBonCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.boncommande.value.TaxeCommandeValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.boncommande.IBonCommandeDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities.DetLivraisonVenteComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gc.boncommande.IBonCommandePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;

/**
 * @author Zeineb Medimagh
 * @since 16/11/2016
 *
 */

@Component
public class BonCommandeDomaineImpl implements IBonCommandeDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonCommandeDomaineImpl.class);
	private static final String SEPARATOR = "-";

	private static final Double ZERO_D = 0D;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TIMBRE = 3L;

	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;
	// private static final Double ZERO = 0D;

	@Autowired
	private IBonCommandePersistance bonCommandePersistance;

	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Override
	public String create(CommandeVenteValue bonCommandeValue) {

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

		Double montantTaxeFodec = ZERO_D;
		Double montantTaxeTVA = ZERO_D;
		Double montantTaxeTimbre = ZERO_D;

		Double assietteFodec = ZERO_D;

		Double montantHTaxeTotal = ZERO_D;
		Double montantTaxesTotal = ZERO_D;
		Double montantRemiseTotal = ZERO_D;
		
		boolean modeRemiseEstTotal = false;

		// System.out.println(" list taxe:
		// "+bonCommandeValue.getListProduitTaxes().size());

		//logger.info("----- bonCommandeValue.getReference() ----------" + bonCommandeValue.getReference());

		if ((bonCommandeValue.getReference() != null && bonCommandeValue.getReference().equals(""))
				|| bonCommandeValue.getReference() == null) {

			bonCommandeValue.setReference(this.getCurrentReferenceByType(bonCommandeValue.getType(),bonCommandeValue.getDateIntroduction(),true));

			//logger.info("----- auto reference ----------" + bonCommandeValue.getReference());

		}	else
			
		{
			 if(bonCommandeValue.getRefAvantChangement() != null && bonCommandeValue.getReference().equals(bonCommandeValue.getRefAvantChangement())) {
				 this.getCurrentReferenceByType(bonCommandeValue.getType(),bonCommandeValue.getDateIntroduction(),true);
           }
			
		}

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (ProduitCommandeValue produitCommande : bonCommandeValue.getListProduitCommandes()) {

			if (produitCommande.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitCommande.getQuantite();
			}

			if (produitCommande.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			
			}

			produitCommande.setDateLivraison(bonCommandeValue.getDateLivraison());

			ProduitValue produitValue = produitService.rechercheProduitById(produitCommande.getProduitId());
			produitCommande.setTaxeId(produitValue.getIdTaxe());
			Double totalProduitCommande = (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			
		
			
			
			//Calcul TVA
			
			//TODO GASH -10092020
            // Modification - Introduire Remise dans Assiette de TVA
			// Verification Round 
			
			if (produitCommande.getRemise()==null)
				produitCommande.setRemise(0D);
			Double totalApresRemise=totalProduitCommande*(1-produitCommande.getRemise()/100);
			
			if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {

				produitTaxeMap.put(produitValue.getIdTaxe(), totalApresRemise);

			} else {
				Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe()) + totalApresRemise;
				produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

			}
			
			
			if (!modeRemiseEstTotal) {
				if (produitCommande.getRemise() != null && totalProduitCommande != null) {
					montantRemiseTotal += (totalProduitCommande * produitCommande.getRemise()) / 100;
				}
			}

		}

		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonCommandeValue.getTotalPourcentageRemise()) / 100;
		}
		// Calcul des taxes

		Map<Long, TaxeCommandeValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeCommandeValue>();
		// modification
		for (TaxeCommandeValue taxeLivraison : bonCommandeValue.getListProduitTaxes()) {
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
		List<TaxeCommandeValue> listTaxeLivraison = new ArrayList<TaxeCommandeValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeCommandeValue> pair = (Map.Entry<Long, TaxeCommandeValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}

		bonCommandeValue.setListProduitTaxes(listTaxeLivraison);

		bonCommandeValue.setMontantRemise(montantRemiseTotal);
		bonCommandeValue.setQuantite(quantiteCommandeVenteTotal);
		bonCommandeValue.setPrixTotal(coutCommandeVenteTotal);
		bonCommandeValue.setMontantTaxe(montantTaxesTotal);
		// System.out.println("prix total: "+bonCommandeValue.getPrixTotal()+"
		// montantTaxesTotal: "+montantTaxesTotal);
		Double montantTTC = bonCommandeValue.getPrixTotal() + montantTaxesTotal -montantRemiseTotal ;
		// System.out.println("montantTTC: "+montantTTC);
		bonCommandeValue.setMontantTTC(montantTTC);
		
		
		
		if(bonCommandeValue.getTauxConversion()!=null)
		bonCommandeValue.setMontantConverti(bonCommandeValue.getTauxConversion()*montantTTC);
		else
			bonCommandeValue.setMontantConverti(ZERO_D)	;
		
		
		
		
		PartieInteresseValue pi = partieInteresseePersistance.getById(bonCommandeValue.getPartieIntersseId());
		if (pi.getTypePartieInteressee() != null
				&& pi.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {

			bonCommandeValue.setMontantTTC(bonCommandeValue.getPrixTotal() -montantRemiseTotal);

		} else {
			bonCommandeValue.setMontantTTC(montantTTC);
		}
		
		
		return bonCommandePersistance.create(bonCommandeValue);
	}

	@Override
	public CommandeVenteValue getById(Long id) {
		return bonCommandePersistance.getById(id);
	}

	@Override
	public String update(CommandeVenteValue bonCommandeValue) {

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
		
		boolean modeRemiseEstTotal = false;

		Map<Long, Double> produitTaxeMap = new HashMap<Long, Double>();
		for (ProduitCommandeValue produitCommande : bonCommandeValue.getListProduitCommandes()) {

			if (produitCommande.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitCommande.getQuantite();
			}

			if (produitCommande.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
			}

			ProduitValue produitValue = produitService.rechercheProduitById(produitCommande.getProduitId());
			produitCommande.setTaxeId(produitValue.getIdTaxe());
			Double totalProduitCommande = (produitCommande.getPrixUnitaire() * produitCommande.getQuantite());
		
			
			if (produitCommande.getRemise()==null)
				produitCommande.setRemise(0D);
			Double totalApresRemise=totalProduitCommande*(1-produitCommande.getRemise()/100);
			
			if (!produitTaxeMap.containsKey(produitValue.getIdTaxe())) {

				produitTaxeMap.put(produitValue.getIdTaxe(), totalApresRemise);

			} else {
				Double assietteValue = produitTaxeMap.get(produitValue.getIdTaxe()) + totalApresRemise;
				produitTaxeMap.put(produitValue.getIdTaxe(), assietteValue);

			}
			
			
			if (!modeRemiseEstTotal) {
				if (produitCommande.getRemise() != null && totalProduitCommande != null) {
					montantRemiseTotal += (totalProduitCommande * produitCommande.getRemise()) / 100;
				}
			}
			

			produitCommande.setDateLivraison(bonCommandeValue.getDateLivraison());
		}
		
		if (modeRemiseEstTotal) {
			montantRemiseTotal = (montantHTaxeTotal * bonCommandeValue.getTotalPourcentageRemise()) / 100;
		}

		bonCommandeValue.setQuantite(quantiteCommandeVenteTotal);
		bonCommandeValue.setPrixTotal(coutCommandeVenteTotal);

		// Calcul des taxes

		Map<Long, TaxeCommandeValue> taxeLivraisonIdTaxeMap = new HashMap<Long, TaxeCommandeValue>();
		// modification
		for (TaxeCommandeValue taxeLivraison : bonCommandeValue.getListProduitTaxes()) {
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
		List<TaxeCommandeValue> listTaxeLivraison = new ArrayList<TaxeCommandeValue>();
		while (it.hasNext()) {

			Map.Entry<Long, TaxeCommandeValue> pair = (Map.Entry<Long, TaxeCommandeValue>) it.next();

			listTaxeLivraison.add(pair.getValue());

			it.remove();
		}
		bonCommandeValue.setMontantRemise(montantRemiseTotal);
		
		
		bonCommandeValue.setListProduitTaxes(listTaxeLivraison);
		bonCommandeValue.setMontantTaxe(montantTaxesTotal);
		Double montantTTC = bonCommandeValue.getPrixTotal() + montantTaxesTotal - montantRemiseTotal;
		bonCommandeValue.setMontantTTC(montantTTC);
		

		bonCommandeValue.setMontantConverti(bonCommandeValue.getTauxConversion()*montantTTC);
	
		
		PartieInteresseValue pi = partieInteresseePersistance.getById(bonCommandeValue.getPartieIntersseId());
		if (pi.getTypePartieInteressee() != null
				&& pi.getTypePartieInteressee().equals(IConstante.PI_TYPE_EXONORE)) {

			bonCommandeValue.setMontantTTC(bonCommandeValue.getPrixTotal() - montantRemiseTotal);

		} else {
			bonCommandeValue.setMontantTTC(montantTTC);
		}

		return bonCommandePersistance.update(bonCommandeValue);
	}

	@Override
	public void delete(Long id) {
		bonCommandePersistance.delete(id);
	}

	@Override
	public List<CommandeVenteValue> getAll() {
		return bonCommandePersistance.getAll();
	}

	@Override
	public ResultatRechecheBonCommandeValue rechercherMultiCritere(RechercheMulticritereBonCommandeValue request) {
		return bonCommandePersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<CommandeVenteValue> getListBonCommandeRefByClient(Long idClient) {

		List<String> listBonCommandeToRemove = new ArrayList<String>();

		List<LivraisonVenteValue> livraisonVenteList = bonLivraisonPersistance.getByClientIdOptimiser(idClient);

		for (LivraisonVenteValue commandeVente : livraisonVenteList) {

			String infoSortieSplitted[];

			if (commandeVente.getRefCommande() != null) {

				infoSortieSplitted = commandeVente.getRefCommande().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonCommandeToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<CommandeVenteValue> bonCommandelist = bonCommandePersistance.getReferenceBCByClientId(idClient);
		
		
		List<CommandeVenteValue> bonCommandelistFinal = new ArrayList<CommandeVenteValue>();
		
		for(CommandeVenteValue cv : bonCommandelist)
		{
			
			if(cv.getReference() != null && !listBonCommandeToRemove.contains(cv.getReference()))	{
				bonCommandelistFinal.add(cv);
			}
		}


		return bonCommandelistFinal;
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
	/*
	private String getNumeroBonCommande(final Calendar pDateBonCommande) {

		Long vNumGuichetBonCommande = this.guichetAnnuelPersistance.getNextNumBonCommandeReference();

		int vAnneeCourante = pDateBonCommande.get(Calendar.YEAR);
	
		StringBuilder vNumBonCommande = new StringBuilder("");
		vNumBonCommande.append(vAnneeCourante);
		vNumBonCommande.append(vNumGuichetBonCommande);

		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) + 1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceCommandeCourante(new Long(vNumGuichetBonCommande + 1L));

		this.guichetAnnuelPersistance.modifierGuichetBonCommandeAnnuel(vGuichetValeur);
		return vNumBonCommande.toString();
	}*/

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
		
		Long numeroBonCommande = currentGuichetAnnuel.getNumReferenceCommandeCourante() ;
		
		
		
	//	Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
	//	int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonCommande = new StringBuilder("");
		
		if(currentGuichetAnnuel.getPrefixeBC() != null)
		vNumBonCommande.append(currentGuichetAnnuel.getPrefixeBC());
		
    	if( numeroBonCommande >0 && numeroBonCommande<10) {
    		vNumBonCommande.append("000");
    	}
    	else if(numeroBonCommande >=10 && numeroBonCommande<100)
    		{vNumBonCommande.append("00");}
    	
    	
    	else if(numeroBonCommande >=100 && numeroBonCommande<1000)
		{vNumBonCommande.append("0");}
    	
    	
    	
    	vNumBonCommande.append( numeroBonCommande);
		

		currentGuichetAnnuel.setNumReferenceCommandeCourante(new Long(numeroBonCommande + 1L));
				
		
		
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetAnnuelDomaine
				.modifierGuichetBCAnnuel(currentGuichetAnnuel);
		
		
		
		return vNumBonCommande.toString();
	}
	
	public String getCurrentReferenceDevis(Calendar instance, boolean increment) {
	GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();
		
		Long numeroBonCommande = currentGuichetAnnuel.getNumReferenceDevisCourante() ;
		
		
		
	//	Long vNumGuichetFacture = this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
	//	int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonCommande = new StringBuilder("");
		
		if(currentGuichetAnnuel.getPrefixeDevis() != null)
		vNumBonCommande.append(currentGuichetAnnuel.getPrefixeDevis());
		
    	if( numeroBonCommande >0 && numeroBonCommande<10) {
    		vNumBonCommande.append("000");
    	}
    	else if(numeroBonCommande >=10 && numeroBonCommande<100)
    		{vNumBonCommande.append("00");}
    	
    	
    	else if(numeroBonCommande >=100 && numeroBonCommande<1000)
		{vNumBonCommande.append("0");}
    	
    	
    	
    	vNumBonCommande.append( numeroBonCommande);
		

		currentGuichetAnnuel.setNumReferenceDevisCourante(new Long(numeroBonCommande + 1L));
				
		
		
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetAnnuelDomaine
				.modifierGuichetDevisAnnuel(currentGuichetAnnuel);
		
		
		
		return vNumBonCommande.toString();
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonCommandesList, Long livraisonVenteId) {
		
		
		
		
		List<CommandeVenteValue> listCommandeVenteValue = bonCommandePersistance
				.getProduitElementList(refBonCommandesList);

		List<ProduitCommandeValue> listDetCommandeValue = new ArrayList<ProduitCommandeValue>();

		ListProduitElementValue listProduitElementValue = new ListProduitElementValue();

		if (listCommandeVenteValue.size() > 0) {
	
			listProduitElementValue.setPartieIntId(listCommandeVenteValue.get(FIRST_INDEX).getPartieIntersseId());
			listProduitElementValue.setDateLivraison(listCommandeVenteValue.get(FIRST_INDEX).getDateLivraison());
		
			

		}

		for (CommandeVenteValue commandeVente : listCommandeVenteValue) {

			for (ProduitCommandeValue detCommandeVente : commandeVente.getListProduitCommandes()) {

				listDetCommandeValue.add(detCommandeVente);
			}
		}

		Map<Map<Long, String>, List<ProduitCommandeValue>> mapDetLivraison = new HashMap<Map<Long, String>, List<ProduitCommandeValue>>();

		for (ProduitCommandeValue detail : listDetCommandeValue) {
			Long produitKey = detail.getProduitId();
			
			String choixKey = "CH1";
		//	String choixKey = detail.getChoix();

			Map<Long, String> map = new HashMap<Long, String>();

			map.put(produitKey, choixKey);

			if (mapDetLivraison.get(map) == null) {

				mapDetLivraison.put(map, new ArrayList<ProduitCommandeValue>());
			}

			mapDetLivraison.get(map).add(detail);
		}

		ProduitValue produitValue = null;

		Iterator it = mapDetLivraison.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetFactureVente = new ArrayList<DetLivraisonVenteValue>();
		
		
		Long ordre = new Long(0);
		
		while (it.hasNext()) {

			Map.Entry<Map<Long, String>, List<ProduitCommandeValue>> pair = (Map.Entry<Map<Long, String>, List<ProduitCommandeValue>>) it
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
			element.setLivraisonVenteId(livraisonVenteId);
			// el
			Double sommeQuantite = ZERO;
			Long sommeNombreColis = ZERO_LONG;

			String numeroSeries = "";

			for (ProduitCommandeValue detail : pair.getValue()) {
				
				if (detail.getQuantite() != null) {
					sommeQuantite = detail.getQuantite() + sommeQuantite;
				}


			}

		


			element.setQuantite(sommeQuantite);
			element.setNombreColis(sommeNombreColis);

			if (pair.getValue().size() > 0) {

				ProduitCommandeValue detail = pair.getValue().get(0);
				if (detail != null) {
					
				
					element.setPrixUnitaireHT(detail.getPrixUnitaire());
				}
			}

			if (element.getProduitId() != null) {
				produitValue = produitService.rechercheProduitById(element.getProduitId());
				if (produitValue != null) {

					element.setSerialisable(produitValue.isSerialisable());
			

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

}
