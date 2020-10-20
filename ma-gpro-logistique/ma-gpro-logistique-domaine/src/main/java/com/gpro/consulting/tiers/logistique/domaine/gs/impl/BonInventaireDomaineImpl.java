package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonInventaireDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.utilities.DetBonInventaireComparator;
import com.gpro.consulting.tiers.logistique.domaine.gs.utilities.DetBonStockComparator;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.entity.GuichetAnnuelEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IBonInventairePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.DetBonInventaireEntity;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 * Implementation of {@link IBonInventaireDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonInventaireDomaineImpl implements IBonInventaireDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonInventaireDomaineImpl.class);

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
	private IBonInventairePersistance bonInventairePersistance;

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
	private IProduitDomaine produitDomaine;

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
	public ResultatRechecheBonInventaireValue rechercherMultiCritere(RechercheMulticritereBonInventaireValue request) {

		return bonInventairePersistance.rechercherMultiCritere(request);
	}



	@Override
	public BonInventaireValue getBonInventaireById(Long id) {

		BonInventaireValue bonInventaireValue = bonInventairePersistance.getBonInventaireById(id);
		
		 
		if (bonInventaireValue.getListDetBonInventaire() != null) {
			 
				for (DetBonInventaireValue element : bonInventaireValue.getListDetBonInventaire()) {

					

					if (element.isSerialisable()) {
						 
						
						element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),element.getProduitId()));

						

					}

				

			
			} 
				
				Collections.sort(bonInventaireValue.getListDetBonInventaire(), new DetBonInventaireComparator());
		}

		return bonInventaireValue;
	}

	private List<String> getNumerosSerieList(String numeroSeries) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateBonInventaire(BonInventaireValue bonInventaireValue) {
		
		

		for (DetBonInventaireValue element : bonInventaireValue.getListDetBonInventaire()) {
			
			 
			
			if (element.isSerialisable() && element.getProduitsSerialisable() != null) {
				 
				
				String numeroSeries = "";

				for (ProduitSerialisableValue prodSerialisable : element.getProduitsSerialisable()) {

			

					numeroSeries += prodSerialisable.getNumSerie();
					numeroSeries += "&";

				}

				if (numeroSeries != null && numeroSeries.length() > 0
						&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
					numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
					element.setNumeroSeries(numeroSeries);
				}
				else
					element.setNumeroSeries(null);
				 
				

			}

		

	
	} 

		return bonInventairePersistance.updateBonInventaire(bonInventaireValue);
	}

 
 
	@Override
	public void deleteBonInventaire(Long id) {

		 
		bonInventairePersistance.deleteBonInventaire(id);
	}

 

	 

	private Set<ProduitSerialisableValue> getListProduitSerialisableParNumerSeries(String numeroSeries,Long produitId) {
		if(numeroSeries == null) return new HashSet<ProduitSerialisableValue>();
		String numero[] = numeroSeries.split(SEPARATOR_NUMERO_SERIE);

		List<String> listNumeroSeries = new ArrayList<>();

		Collections.addAll(listNumeroSeries, numero);

		return produitSerialisablePersistance.getByNumeroSerieListEnrichi(listNumeroSeries,produitId);
	}

	




	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonInventaire(BonInventaireValue bonInventaireValue) {
		
		if (bonInventaireValue.getReference() == null || bonInventaireValue.getReference().equals("")) {

			bonInventaireValue.setReference(getCurrentReference(Calendar.getInstance(), true));

			// bonLivraisonValue.setReference(getNumeroBonStockFromGuichetAnnuel(Calendar.getInstance()));

			// bonLivraisonValue.setReference(getNumeroBonStockFromGuichetMensuel(Calendar.getInstance()));
			// bonLivraisonValue.getReference().concat(getNumeroBonStock(Calendar.getInstance()));
		} else if (bonInventaireValue.getRefAvantChangement() != null
				&& bonInventaireValue.getReference().equals(bonInventaireValue.getRefAvantChangement())) {
			this.getCurrentReference(bonInventaireValue.getDate(), true);
		}

	for (DetBonInventaireValue element : bonInventaireValue.getListDetBonInventaire()) {

			

			if (element.isSerialisable() && element.getProduitsSerialisable() != null) {
				
				
				String numeroSeries = "";

				for (ProduitSerialisableValue prodSerialisable : element.getProduitsSerialisable()) {

			

					numeroSeries += prodSerialisable.getNumSerie();
					numeroSeries += "&";

				}

				if (numeroSeries != null && numeroSeries.length() > 0
						&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
					numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
					element.setNumeroSeries(numeroSeries);
				}else {
					
					element.setNumeroSeries(null);
				}
				 
				

			}

		

	
	} 
		 
		return bonInventairePersistance.createBonInventaire(bonInventaireValue);

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

		Long numeroBL = currentGuichetAnnuel.getNumBonInventaireCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBL = new StringBuilder("");
		
		 
		
		if (currentGuichetAnnuel.getPrefixeBonInventaire() != null)
			vNumBL.append(currentGuichetAnnuel.getPrefixeBonInventaire());

		if (numeroBL > 0 && numeroBL < 10) {
			vNumBL.append("000");
		} else if (numeroBL >= 10 && numeroBL < 100) {
			vNumBL.append("00");
		}

		else if (numeroBL >= 100 && numeroBL < 1000) {
			vNumBL.append("0");
		}

		vNumBL.append(numeroBL);

		currentGuichetAnnuel.setNumBonInventaireCourante(new Long(numeroBL + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBonInventaireAnnuel(currentGuichetAnnuel);

		return vNumBL.toString();
	}
	


}
