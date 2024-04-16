package com.gpro.consulting.tiers.logistique.domaine.gs.transfert.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitSerialisableValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IPrixClientDomaine;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitSerialisablePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.BonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.DetBonTransfertComparator;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.DetBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.RechercheMulticritereBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.transfert.value.ResultatRechecheBonTransfertValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.IMarcheDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.transfert.IBonTransfertDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.facture.IFactureAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.achat.reception.IReceptionAchatPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.ITaxeLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IDetFactureVentePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.vente.facture.IFacturePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.transfert.IBonTransfertPersistance;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 * Implementation of {@link IBonTransfertDomaine}
 * 
 * @author Samer Hasse
 * @since 04/09/2020
 *
 */

@Component
public class BonTransfertDomaineImpl implements IBonTransfertDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonTransfertDomaineImpl.class);

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
	private IBonTransfertPersistance bonTransfertPersistance;

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
	public ResultatRechecheBonTransfertValue rechercherMultiCritere(RechercheMulticritereBonTransfertValue request) {

		return bonTransfertPersistance.rechercherMultiCritere(request);
	}

	@Override
	public BonTransfertValue getBonTransfertById(Long id) {

		BonTransfertValue bonTransfertValue = bonTransfertPersistance.getBonTransfertById(id);

		if (bonTransfertValue.getListDetBonTransfert() != null) {

			for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

				if (element.isSerialisable()) {

					element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),
							element.getProduitId()));

				}

			}

			Collections.sort(bonTransfertValue.getListDetBonTransfert(), new DetBonTransfertComparator());
		}

		return bonTransfertValue;
	}

	private List<String> getNumerosSerieList(String numeroSeries) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateBonTransfert(BonTransfertValue bonTransfertValue) {

		if (bonTransfertValue.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_SORTIE))

			return updateBonTransfertSortie(bonTransfertValue);

		else

		if (bonTransfertValue.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_RECEPTION))

			return updateBonTransfertReception(bonTransfertValue);

		else
			return IConstanteCommerciale.CODE_ERROR_EDITION_BON_TRANSFERT_INVALIDE_TYPE;

	}

	private String updateBonTransfertReception(BonTransfertValue bonTransfertValue) {
		for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

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
				} else
					element.setNumeroSeries(null);

			}

		}

		return bonTransfertPersistance.updateBonTransfert(bonTransfertValue);
	}

	private String updateBonTransfertSortie(BonTransfertValue bonTransfertValue) {
		
		
		
		BonTransfertValue btReception = bonTransfertPersistance.getBonTransfertByReferenceSortie(bonTransfertValue.getReference());
		
		if(btReception != null)
			return IConstanteCommerciale.CODE_ERROR_EDITION_BON_TRANSFERT_SORTIE_BTS_HAS_ALREADY_BEEN_RECEIVED;
					
					
					

		for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

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
				} else
					element.setNumeroSeries(null);

			}

		}

		return bonTransfertPersistance.updateBonTransfert(bonTransfertValue);
	}

	@Override
	public void deleteBonTransfert(Long id) {

		bonTransfertPersistance.deleteBonTransfert(id);
	}

	private Set<ProduitSerialisableValue> getListProduitSerialisableParNumerSeries(String numeroSeries,
			Long produitId) {
		if (numeroSeries == null)
			return new HashSet<ProduitSerialisableValue>();
		String numero[] = numeroSeries.split(SEPARATOR_NUMERO_SERIE);

		List<String> listNumeroSeries = new ArrayList<>();

		Collections.addAll(listNumeroSeries, numero);

		return produitSerialisablePersistance.getByNumeroSerieListEnrichi(listNumeroSeries, produitId);
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonTransfert(BonTransfertValue bonTransfertValue) {

		if ((bonTransfertValue.getReference() == null || bonTransfertValue.getReference().equals(""))
				&& bonTransfertValue.getType() != null) {

			bonTransfertValue
					.setReference(getCurrentReference(bonTransfertValue.getType(), Calendar.getInstance(), true));

		} else if (bonTransfertValue.getRefAvantChangement() != null
				&& bonTransfertValue.getReference().equals(bonTransfertValue.getRefAvantChangement())) {
			this.getCurrentReference(bonTransfertValue.getType(), bonTransfertValue.getDate(), true);
		}

		if (bonTransfertValue.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_SORTIE))

			return createBonTransfertSortie(bonTransfertValue);

		else

		if (bonTransfertValue.getType().equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_RECEPTION))

			return createBonTransfertReception(bonTransfertValue);

		else
			return IConstanteCommerciale.CODE_ERROR_CREATION_BON_TRANSFERT_INVALIDE_TYPE;

		/*
		 * ArrayList<String> list = new ArrayList<String>(); list.add("one");
		 * list.add("two"); list.add("three");
		 * 
		 * String listString = StringUtils.join("&", list);
		 */

	}

	private String createBonTransfertSortie(BonTransfertValue bonTransfertValue) {

		for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

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
				}else
				{
					element.setNumeroSeries(null);
				}
				

			}

		}

		return bonTransfertPersistance.createBonTransfert(bonTransfertValue);
	}

	private String createBonTransfertReception(BonTransfertValue bonTransfertValue) {

		for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

			if (element.isSerialisable() && element.getProduitsSerialisable() != null) {

				String numeroSeries = "";

				for (ProduitSerialisableValue prodSerialisable : element.getProduitsSerialisable()) {

					numeroSeries += prodSerialisable.getNumSerie();
					numeroSeries += "&";

					prodSerialisable.setMagasinId(bonTransfertValue.getIdDepotDestination());

					prodSerialisable.setHistoriqueBTreception(addReference(prodSerialisable.getHistoriqueBTreception(),
							bonTransfertValue.getReference()));

					produitSerialisablePersistance.modifierProduitSerialisable(prodSerialisable);

				}

				if (numeroSeries != null && numeroSeries.length() > 0
						&& numeroSeries.charAt(numeroSeries.length() - 1) == '&') {
					numeroSeries = numeroSeries.substring(0, numeroSeries.length() - 1);
					element.setNumeroSeries(numeroSeries);
				} else

					element.setNumeroSeries(null);

			}

			// update Produit depot
			String resUpdateProduitDepot = updateProduitDepot(element.getProduitId(), element.getQuantite(),
					bonTransfertValue.getIdDepotOrigine(), bonTransfertValue.getIdDepotDestination());

			if (!resUpdateProduitDepot.equals(IConstanteCommerciale.OK))
				return resUpdateProduitDepot;

		}

		updateQteRecuForBTsortieWithCheckingConformite(bonTransfertValue);

		return bonTransfertPersistance.createBonTransfert(bonTransfertValue);
	}

	private void updateQteRecuForBTsortieWithCheckingConformite(BonTransfertValue bonTransfertValue) {

		bonTransfertValue.setStatusAuto(IConstanteCommerciale.BON_TRANSFERT_STATUS_CONFORME);

		BonTransfertValue btSortie = bonTransfertPersistance
				.getBonTransfertByReference(bonTransfertValue.getReferenceSortie());

		btSortie.setStatusAuto(IConstanteCommerciale.BON_TRANSFERT_STATUS_CONFORME);

		btSortie.setReferenceReception(bonTransfertValue.getReference());

		for (DetBonTransfertValue element : btSortie.getListDetBonTransfert()) {

			Double qteRecu = getQteRecu(bonTransfertValue.getListDetBonTransfert(), element.getProduitId());

			element.setQuantiteRecu(qteRecu);

			if (qteRecu == null || !element.getQuantite().equals(qteRecu)) {
				bonTransfertValue.setStatusAuto(IConstanteCommerciale.BON_TRANSFERT_STATUS_NON_CONFORME);
				btSortie.setStatusAuto(IConstanteCommerciale.BON_TRANSFERT_STATUS_NON_CONFORME);

			} else {

				if (element.isSerialisable() && !checkNumeroSerieConformite(element.getNumeroSeries(),
						getNumeroSerie(bonTransfertValue.getListDetBonTransfert(), element.getProduitId()))) {

					bonTransfertValue.setStatusAuto(IConstanteCommerciale.BON_TRANSFERT_STATUS_NON_CONFORME);
					btSortie.setStatusAuto(IConstanteCommerciale.BON_TRANSFERT_STATUS_NON_CONFORME);

				}

			}

		}

		bonTransfertPersistance.updateBonTransfert(btSortie);

	}

	private boolean checkNumeroSerieConformite(String numeroSeriesSortie, String numeroSerieReception) {

		if (numeroSeriesSortie == null || numeroSerieReception == null)
			return false;

		// String[] numSortie = numeroSeriesSortie.split("&");

		// String[] numReception = numeroSerieReception.split("&");

		List<String> listNumeroSeriesSortie = new ArrayList<>();

		Collections.addAll(listNumeroSeriesSortie, numeroSeriesSortie.split("&"));

		List<String> listNumeroSeriesReception = new ArrayList<>();

		Collections.addAll(listNumeroSeriesReception, numeroSerieReception.split("&"));

		if (listNumeroSeriesSortie.size() != listNumeroSeriesReception.size())
			return false;

		for (String num : listNumeroSeriesSortie) {

			if (!listNumeroSeriesReception.contains(num))
				return false;
		}

		for (String num : listNumeroSeriesReception) {

			if (!listNumeroSeriesSortie.contains(num))
				return false;
		}

		return true;
	}

	private Double getQteRecu(List<DetBonTransfertValue> listDetBonTransfert, Long produitId) {
		// TODO Auto-generated method stub
		for (DetBonTransfertValue element : listDetBonTransfert) {

			if (element.getProduitId().equals(produitId))

				return element.getQuantite();

		}

		return null;
	}

	private String getNumeroSerie(List<DetBonTransfertValue> listDetBonTransfert, Long produitId) {
		// TODO Auto-generated method stub
		for (DetBonTransfertValue element : listDetBonTransfert) {

			if (element.getProduitId().equals(produitId))

				return element.getNumeroSeries();

		}

		return null;
	}

	private String updateProduitDepot(Long produitId, Double quantite, Long idDepotOrigine, Long idDepotDestination) {

		RechercheMulticritereProduitDepotValue request = new RechercheMulticritereProduitDepotValue();

		request.setIdDepot(idDepotOrigine);
		request.setIdProduit(produitId);

		ResultatRechercheProduitDepotValue produitDepot = produitDepotPersistance.rechercheMulticritere(request);

		if (produitDepot.getProduitdepotvalues().size() == 0 ||

				(produitDepot.getProduitdepotvalues().size() > 0
						&& produitDepot.getProduitdepotvalues().iterator().next().getQuantite() < quantite)) {

			return IConstanteCommerciale.CODE_ERROR_CREATION_BON_TRANSFERT_RECEPTION_INVALIDE_STOCK_IN_MAGASIN_ORIGINE;

		} else {
			ProduitDepotValue vProduitDepot = produitDepot.getProduitdepotvalues().iterator().next();
			vProduitDepot.setQuantite(vProduitDepot.getQuantite() - quantite);
			produitDepotPersistance.modifier(vProduitDepot);

			// update stock destination

			request.setIdDepot(idDepotDestination);

			produitDepot = produitDepotPersistance.rechercheMulticritere(request);

			if (produitDepot.getProduitdepotvalues().size() == 0) {

				ProduitDepotValue vProdDepDestination = new ProduitDepotValue();

				vProdDepDestination.setIdDepot(idDepotDestination);
				vProdDepDestination.setIdProduit(produitId);
				vProdDepDestination.setQuantite(quantite);

				produitDepotPersistance.create(vProdDepDestination);

			} else {
				ProduitDepotValue vProdDepDestination = produitDepot.getProduitdepotvalues().iterator().next();
				vProdDepDestination.setQuantite(vProdDepDestination.getQuantite() + quantite);
				produitDepotPersistance.modifier(vProdDepDestination);

			}

		}

		return IConstanteCommerciale.OK;

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
	public String getCurrentReference(String type, Calendar instance, boolean increment) {

		if (type.equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_SORTIE)) {

			return getCurrentReferenceSortie(instance, increment);

		}

		else

			// if(type.equals(IConstanteCommerciale.BON_TRANSFERT_TYPE_RECEPTION)) {

			return getCurrentReferenceReception(instance, increment);

		// }

	}

	private String getCurrentReferenceReception(Calendar instance, boolean increment) {
		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroBL = currentGuichetAnnuel.getNumBonTransfertReceptionCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBL = new StringBuilder("");

		if (currentGuichetAnnuel.getPrefixeBonTransfertReception() != null)
			vNumBL.append(currentGuichetAnnuel.getPrefixeBonTransfertReception());

		if (numeroBL > 0 && numeroBL < 10) {
			vNumBL.append("000");
		} else if (numeroBL >= 10 && numeroBL < 100) {
			vNumBL.append("00");
		}

		else if (numeroBL >= 100 && numeroBL < 1000) {
			vNumBL.append("0");
		}

		vNumBL.append(numeroBL);

		currentGuichetAnnuel.setNumBonTransfertReceptionCourante(new Long(numeroBL + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBonTransfertReceptionAnnuel(currentGuichetAnnuel);

		return vNumBL.toString();

	}

	private String getCurrentReferenceSortie(Calendar instance, boolean increment) {

		GuichetAnnuelValue currentGuichetAnnuel = guichetAnnuelDomaine.getCurrentGuichetAnnuel();

		Long numeroBL = currentGuichetAnnuel.getNumBonTransfertSortieCourante();

		// Long vNumGuichetFacture =
		// this.guichetAnnuelDomaine.getNextNumAvoirReference();
		/** Année courante. */
		// int vAnneeCourante = pDateBonFacture.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBL = new StringBuilder("");

		if (currentGuichetAnnuel.getPrefixeBonTransfertSortie() != null)
			vNumBL.append(currentGuichetAnnuel.getPrefixeBonTransfertSortie());

		if (numeroBL > 0 && numeroBL < 10) {
			vNumBL.append("000");
		} else if (numeroBL >= 10 && numeroBL < 100) {
			vNumBL.append("00");
		}

		else if (numeroBL >= 100 && numeroBL < 1000) {
			vNumBL.append("0");
		}

		vNumBL.append(numeroBL);

		currentGuichetAnnuel.setNumBonTransfertSortieCourante(new Long(numeroBL + 1L));

		/** Modification de la valeur en base du numéro. */

		if (increment)
			this.guichetAnnuelDomaine.modifierGuichetBonTransfertSortieAnnuel(currentGuichetAnnuel);

		return vNumBL.toString();
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

	@Override
	public BonTransfertValue getBonTransfertByReference(String reference) {

		BonTransfertValue bonTransfertValue = bonTransfertPersistance.getBonTransfertByReference(reference);

		if (bonTransfertValue != null && bonTransfertValue.getListDetBonTransfert() != null) {

			for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

				if (element.isSerialisable()) {

					element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),
							element.getProduitId()));

				}

			}

			Collections.sort(bonTransfertValue.getListDetBonTransfert(), new DetBonTransfertComparator());
		}

		return bonTransfertValue;
	}

	@Override
	public BonTransfertValue validerBTsortieByReference(String reference) {
		BonTransfertValue bonTransfertValue = bonTransfertPersistance.validerBTsortieByReference(reference);

		if (bonTransfertValue != null && bonTransfertValue.getListDetBonTransfert() != null) {

			for (DetBonTransfertValue element : bonTransfertValue.getListDetBonTransfert()) {

				if (element.isSerialisable()) {

					element.setProduitsSerialisable(getListProduitSerialisableParNumerSeries(element.getNumeroSeries(),
							element.getProduitId()));

				}

			}

			Collections.sort(bonTransfertValue.getListDetBonTransfert(), new DetBonTransfertComparator());
		}

		return bonTransfertValue;
	}

}
