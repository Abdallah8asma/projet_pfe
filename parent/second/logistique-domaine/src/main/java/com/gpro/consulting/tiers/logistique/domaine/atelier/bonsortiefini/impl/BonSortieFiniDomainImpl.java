package com.gpro.consulting.tiers.logistique.domaine.atelier.bonsortiefini.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniOptimizedValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ChoixRouleauValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.FicheFaconValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.facon.value.TraitementFicheValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.IBonReceptionDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonsortiefini.IBonSortieFiniDomain;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities.DetLivraisonVenteFaconValidateComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.bonlivraison.utilities.DetLivraisonVenteValidateComparator;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;

/**
 * Implementation of {@link IBonSortieFiniDomain}
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class BonSortieFiniDomainImpl implements IBonSortieFiniDomain {

	private static final Logger logger = LoggerFactory.getLogger(BonSortieFiniDomainImpl.class);

	private static final Double ZERO = 0D;
	private static final String MAILLE = "maille";
	private static final String KG = "kg";
	private static final String M = "m";
	private static final String EMPTY = "";
	private static final String SEPARATOR = "-";

	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private IRouleauFiniPersistance rouleauFiniPersistance;

	@Autowired
	private IRouleauFiniDomaine rouleauFiniDomaine;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;

	@Autowired
	private IChoixRouleauPersistance choixRouleauPersistance;

	@Autowired
	private IGuichetMensuelDomaine guichetierMensuelDomaine;
	
	@Autowired
	private IMiseDomaine miseDomaine;
	
	@Autowired
	private IFicheFaconDomaine ficheFaconDomaine;
	

	@Override
	public List<RouleauFiniValue> validateBonSortieFini(List<String> barreCodeList, Long id) {

		List<RouleauFiniValue> listeRouleauFini = rouleauFiniPersistance
				.getRouleauFiniListByBarreCodeList(barreCodeList, id);
		for (RouleauFiniValue elementrouleau : listeRouleauFini) {
			ProduitValue produit = produitPersistance.rechercheProduitById(elementrouleau.getProduitId());
			if (produit != null) {
				elementrouleau.setProduitReference(produit.getReference());
				elementrouleau.setProduitIdDesignation(produit.getDesignation());
				elementrouleau.setProduitIdComposition(produit.getComposition());
				if (produit.getSousFamilleId() != null) {
					SousFamilleProduitValue sousFamille = sousFamilleProduitPersistance
							.rechercheSousFamilleProduitById(produit.getSousFamilleId());
					if (sousFamille != null) {
						elementrouleau.setProduitIdSousFamille(sousFamille.getDesignation());
					}
				}
			}
		}
		return listeRouleauFini;
	}

	@Override
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {

		if (bonSortieFiniValue.getReference() == null || bonSortieFiniValue.getReference().equals("")) {
			bonSortieFiniValue.setReference(getNumeroBonSotie(Calendar.getInstance(),true));
			//bonSortieFiniValue.getReference().concat(getNumeroBonSotie(Calendar.getInstance()));

		}
		
/*		
     else if(bonSortieFiniValue.getRefAvantChangement() != null && bonSortieFiniValue.getReference().equals(bonSortieFiniValue.getRefAvantChangement())) {
				 
				this.getNumeroBonSotie(Calendar.getInstance(),true);
				
				
           }
*/
		List<RouleauFiniValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();

		Map<Long, Double> metrageMap = new HashMap<Long, Double>();

		Double metrageTotal = ZERO;

		if (listeRouleauFini != null) {
			// retrieve the old METRAGE data for each element from
			// listeRouleauFini
			for (RouleauFiniValue rouleauFini : listeRouleauFini) {

				rouleauFini.setDateSortie(bonSortieFiniValue.getDateSortie());

				if (rouleauFini.getId() != null) {

					if (valueOfBoolean(rouleauFini.isMetrageModif())) {

						RouleauFiniValue oldRouleauFini = rouleauFiniPersistance
								.rechercheRouleauFiniParId(rouleauFini.getId());

						metrageMap.put(rouleauFini.getId(),
								(oldRouleauFini.getMetrage() != null) ? oldRouleauFini.getMetrage() : null);

						Double oldMetrage = oldRouleauFini.getMetrage();
						Double newMetrage = (oldMetrage != null && oldMetrage > rouleauFini.getMetrage())
								? (oldMetrage - rouleauFini.getMetrage()) : null;

						// verify newMetrageModif
						// if newMetrage = null AND oldMetrageModif=false AND
						// newMetrageModif=true -> metrageModif=false
						if (newMetrage == null && !valueOfBoolean(oldRouleauFini.isMetrageModif())) {
							rouleauFini.setMetrageModif(false);
						}

					}

					if (rouleauFini.getMetrage() != null) {

						metrageTotal = metrageTotal + rouleauFini.getMetrage();
					}
				}
			}
		}

		bonSortieFiniValue.setMetrageTotal(metrageTotal);

		String idBonSortieFiniSaved = bonSortieFiniPersistance.createBonSortieFini(bonSortieFiniValue);

		if (listeRouleauFini != null) {

			for (RouleauFiniValue rouleauFini : listeRouleauFini) {

				rouleauFini.setBonSortie(Long.valueOf(idBonSortieFiniSaved).longValue());
				rouleauFini.setDateSortie(bonSortieFiniValue.getDateSortie());

				// create a new rouleauFini if:
				// rouleauFini.metrageModif is true
				// newMetrage not null,
				// and the oldMetrage > rouleauFini.metrage
				if (rouleauFini.getId() != null) {
					if (valueOfBoolean(rouleauFini.isMetrageModif())) {

						Double oldMetrage = metrageMap.get(rouleauFini.getId());
						Double newMetrage = (oldMetrage != null && oldMetrage > rouleauFini.getMetrage())
								? (oldMetrage - rouleauFini.getMetrage()) : null;

						if (newMetrage != null) {
							RouleauFiniValue newRouleauFini = new RouleauFiniValue();
							copyDataFromRouleauFiniValueParent(rouleauFini, newRouleauFini);

							// set the new METRAGE value
							newRouleauFini.setMetrage(newMetrage);
							newRouleauFini.setDateIntroduction(bonSortieFiniValue.getDateSortie());

							// infoModif pattern:
							// oldInfoModifIfExist-rouleauFiniParentReference/rouleauFiniParentMetrage
							String infoModif = (newRouleauFini.getInfoModif() != null)
									? (newRouleauFini.getInfoModif() + "-")
									: "" + rouleauFini.getReference() + "/" + oldMetrage;

							newRouleauFini.setInfoModif(infoModif);
							rouleauFiniDomaine.createRouleauFini(newRouleauFini);
						}

					}
				}
			}

		}

		return idBonSortieFiniSaved;
	}

	private void copyDataFromRouleauFiniValueParent(RouleauFiniValue rouleauFiniValue,
			RouleauFiniValue newRouleauFini) {

		newRouleauFini.setChoix(rouleauFiniValue.getChoix());
		newRouleauFini.setEmplacement(rouleauFiniValue.getEmplacement());
		newRouleauFini.setPoids(rouleauFiniValue.getPoids());
		newRouleauFini.setReferenceMise(rouleauFiniValue.getReferenceMise());
		newRouleauFini.setVersion(rouleauFiniValue.getVersion());
		newRouleauFini.setLaize(rouleauFiniValue.getLaize());
		newRouleauFini.setProduitId(rouleauFiniValue.getProduitId());
		newRouleauFini.setPartieInteresseeId(rouleauFiniValue.getPartieInteresseeId());
		newRouleauFini.setEntrepot(rouleauFiniValue.getEntrepot());
		newRouleauFini.setFini(rouleauFiniValue.isFini());
		newRouleauFini.setInfo(rouleauFiniValue.getInfo());

	}

	@Override
	public BonSortieFiniValue getBonSortieFiniById(Long id) {

		return bonSortieFiniPersistance.getBonSortieFiniById(id);
	}

	@Override
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(RechercheMulticritereBonSortieFiniValue request) {

		return bonSortieFiniPersistance.rechercherMultiCritere(request);
	}

	@Override
	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {

		List<RouleauFiniValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();

		// Put NULL to bonsortieId for each RouleauFini deleted from
		// BonSortieFini
		BonSortieFiniValue oldBonSortieFiniValue = bonSortieFiniPersistance
				.getBonSortieFiniById(bonSortieFiniValue.getId());

		for (RouleauFiniValue rouleauFini : oldBonSortieFiniValue.getListeRouleauFini()) {

			if (!listeRouleauFini.contains(rouleauFini)) {
				rouleauFini.setBonSortie(null);
				rouleauFini.setDateSortie(null);
				rouleauFiniPersistance.updateRouleauFini(rouleauFini);
			}
		}

		Map<Long, Double> metrageMap = new HashMap<Long, Double>();

		Double metrageTotal = ZERO;

		if (listeRouleauFini != null) {
			// retrieve the old METRAGE data for each element from
			// listeRouleauFini
			for (RouleauFiniValue rouleauFini : listeRouleauFini) {

				if (rouleauFini != null) {

					if (valueOfBoolean(rouleauFini.isMetrageModif())) {

						RouleauFiniValue oldRouleauFini = rouleauFiniPersistance
								.rechercheRouleauFiniParId(rouleauFini.getId());

						metrageMap.put(rouleauFini.getId(),
								(oldRouleauFini.getMetrage() != null) ? oldRouleauFini.getMetrage() : null);

						Double oldMetrage = oldRouleauFini.getMetrage();
						Double newMetrage = (oldMetrage != null && oldMetrage > rouleauFini.getMetrage())
								? (oldMetrage - rouleauFini.getMetrage()) : null;

						// verify newMetrageModif
						// if newMetrage = null AND oldMetrageModif=false AND
						// newMetrageModif=true -> metrageModif=false
						if (newMetrage == null && !valueOfBoolean(oldRouleauFini.isMetrageModif())) {
							rouleauFini.setMetrageModif(false);
						}
					}
					if (rouleauFini.getMetrage() != null) {

						metrageTotal = metrageTotal + rouleauFini.getMetrage();
					}
					// metrageTotal = metrageTotal + rouleauFini.getMetrage();
				}

			}
		}

		bonSortieFiniValue.setMetrageTotal(metrageTotal);

		String idBonSortieFiniSaved = bonSortieFiniPersistance.updateBonSortieFini(bonSortieFiniValue);

		if (listeRouleauFini != null) {

			for (RouleauFiniValue rouleauFini : listeRouleauFini) {

				rouleauFini.setBonSortie(Long.valueOf(idBonSortieFiniSaved).longValue());
				rouleauFini.setDateSortie(bonSortieFiniValue.getDateSortie());

				// create a new rouleauFini if:
				// rouleauFini.metrageModif is true
				// newMetrage not null,
				// and the oldMetrage > rouleauFini.metrage
				if (rouleauFini.getId() != null) {
					if (valueOfBoolean(rouleauFini.isMetrageModif())) {

						Double oldMetrage = metrageMap.get(rouleauFini.getId());
						Double newMetrage = (oldMetrage != null && oldMetrage > rouleauFini.getMetrage())
								? (oldMetrage - rouleauFini.getMetrage()) : null;

						if (newMetrage != null) {
							RouleauFiniValue newRouleauFini = new RouleauFiniValue();
							copyDataFromRouleauFiniValueParent(rouleauFini, newRouleauFini);

							// set the new METRAGE value
							newRouleauFini.setMetrage(newMetrage);
							newRouleauFini.setDateIntroduction(bonSortieFiniValue.getDateSortie());

							// infoModif pattern:
							// oldInfoModifIfExist-rouleauFiniParentReference/rouleauFiniParentMetrage
							String infoModif = (newRouleauFini.getInfoModif() != null)
									? (newRouleauFini.getInfoModif() + "-")
									: "" + rouleauFini.getReference() + "/" + oldMetrage;

							newRouleauFini.setInfoModif(infoModif);
							rouleauFiniDomaine.createRouleauFini(newRouleauFini);
						}

					}
				}
			}

		}

		return idBonSortieFiniSaved;
	}

	@Override
	public void deleteBonSortieFini(Long id) {

		BonSortieFiniValue bonSortieFiniValue = bonSortieFiniPersistance.getBonSortieFiniById(id);

		if (bonSortieFiniValue != null) {
			List<RouleauFiniValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();
			for (RouleauFiniValue rouleauFiniValue : listeRouleauFini) {
				rouleauFiniValue.setBonSortie(null);
				rouleauFiniValue.setDateSortie(null);
				rouleauFiniPersistance.updateRouleauFini(rouleauFiniValue);
			}

			bonSortieFiniValue.setListeRouleauFini(null);
			bonSortieFiniPersistance.updateBonSortieFini(bonSortieFiniValue);
			bonSortieFiniPersistance.deleteBonSortieFini(id);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonSortieList, Long livraisonVenteId) {

		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniPersistance.getListByBonSortieList(refBonSortieList);

		List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

				listeRouleauFini.add(rouleau);
			}
		}

		Map<Map<Long, Long>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<Long, Long>, List<RouleauFiniValue>>();

		for (RouleauFiniValue rouleau : listeRouleauFini) {
			Long produitKey = rouleau.getProduitId();
			Long choixKey = rouleau.getChoix();

			Map<Long, Long> map = new HashMap<Long, Long>();

			map.put(produitKey, choixKey);

			if (mapRouleau.get(map) == null) {

				mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
			}

			mapRouleau.get(map).add(rouleau);

		}

		ProduitValue produitValue = null;

		Iterator it = mapRouleau.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		while (it.hasNext()) {

			Map.Entry<Map<Long, Long>, List<RouleauFiniValue>> pair = (Map.Entry<Map<Long, Long>, List<RouleauFiniValue>>) it
					.next();

			DetLivraisonVenteValue element = new DetLivraisonVenteValue();

			Long produitId = null;
			Long choixId = null;

			Map<Long, Long> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<Long, Long> produitIdchoixIdPair = (Map.Entry<Long, Long>) produitIdchoixIdIt.next();
			produitId = produitIdchoixIdPair.getKey();
			choixId = produitIdchoixIdPair.getValue();

			element.setProduitId(produitId);

			if (choixId != null) {
				ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choixId);
				if (choixRouleau != null)
					element.setChoix(choixRouleau.getDesignation());
			}

			Double sommeMetrage = ZERO;
			Double sommePoids = ZERO;
			
			List<String> refMiseList = new ArrayList<String>();
			
		//	String referenceMises = "";
			
			
			for (RouleauFiniValue rouleau : pair.getValue()) {
				// Somme des metrages
				if (rouleau.getMetrage() != null) {
					sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
				}
				if (rouleau.getPoids() != null) {
					sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
				}
				
			
				
				if(rouleau.getReferenceMise() != null &&  !refMiseList.contains(rouleau.getReferenceMise()))
					refMiseList.add(rouleau.getReferenceMise());
				
					
			}
			
		
			if(refMiseList.size() > 0) {
				
				
				  element.setNumeroOF(StringUtils.join(refMiseList, ", "));

			}
			    

			// Qte livree = La somme des metrage des rouleaux de produit
			element.setQuantite(sommeMetrage);
			if (sommePoids > ZERO)
				element.setQuantite(sommePoids);

			if (element.getProduitId() != null) {
				produitValue = produitPersistance.rechercheProduitById(element.getProduitId());
				if (produitValue != null) {

				/*	SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
							.rechercheSousFamilleProduitById(produitValue.getSousFamilleId());
					if (sousFamilleProduitValue != null) {
						String designation = (sousFamilleProduitValue.getDesignation() == null) ? EMPTY
								: sousFamilleProduitValue.getDesignation().toLowerCase();
						element.setUnite((designation.equalsIgnoreCase(MAILLE)) ? KG : M);
					}*/
					
					
					element.setUnite(produitValue.getUnite());

					if (livraisonVenteId != null) {
						DetLivraisonVenteValue detLivraisonVenteValue = detLivraisonVentePersistance
								.getBylivraisonVenteAndProduit(livraisonVenteId, element.getProduitId(), element.getChoix());
						if (detLivraisonVenteValue != null) {

							boolean detailIdNotExist = true;
							for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
								if (detail.getId() == detLivraisonVenteValue.getId())
									detailIdNotExist = false;
							}

							if (detailIdNotExist) {
								element.setId(detLivraisonVenteValue.getId());
							}

							element.setLivraisonVenteId(detLivraisonVenteValue.getLivraisonVenteId());
							element.setRemise(detLivraisonVenteValue.getRemise());
							
							element.setFicheId(detLivraisonVenteValue.getFicheId());
						}
					}

					element.setProduitDesignation(produitValue.getDesignation());
					element.setProduitReference(produitValue.getReference());
					element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
					if (produitValue.getPrixUnitaire() != null)
						element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
				}
			}

			element.setNombreColis(Long.valueOf(pair.getValue().size()));

			listDetLivraisonVente.add(element);

			it.remove();
		}

		if(listDetLivraisonVente.size()>0){
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
		}
		

		ListProduitElementValue result = new ListProduitElementValue();

		result.setNombreResultaRechercher(listDetLivraisonVente.size());
		result.setDateSortie(dateSortie);
		result.setListDetLivraisonVente(listDetLivraisonVente);

		return result;

	}

	@Override
	public List<String> getListBonSortieFiniRef() {

		List<String> listBonSortieFiniToRemove = new ArrayList<String>();

		List<LivraisonVenteValue> LivraisonVenteList = bonLivraisonPersistance.getAll();

		for (LivraisonVenteValue livraisonVente : LivraisonVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoSortie() != null) {

				infoSortieSplitted = livraisonVente.getInfoSortie().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonSortieFiniToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<String> listBonSortieFini = new ArrayList<String>();

		List<BonSortieFiniValue> bonSortieFinilist = bonSortieFiniPersistance.getAll();

		for (BonSortieFiniValue bonSortieFini : bonSortieFinilist) {
			if (bonSortieFini.getReference() != null && bonSortieFini.getReference().length() != 0)
				listBonSortieFini.add(bonSortieFini.getReference());
		}

		listBonSortieFini.removeAll(listBonSortieFiniToRemove);

		return listBonSortieFini;
	}

	/**
	 * Récuperer le numéro du Bon de receptionà partir du Guichet Bon Reception
	 * 
	 * Le Numéro Bon Reception est unique pour une Bon Reception OSIRIS et doit
	 * respect le format:
	 * 
	 * - AAAA : année du Bon Reception. - NNNNNN : un numéro incrémental au sein
	 * de l'année civile.
	 * 
	 * @param pDateBonReception
	 *            la date de la Bon Reception
	 * @return le numéro du prochaine Bon Reception à insérer
	 */
	private String getNumeroBonSotie(final Calendar pDateBonSortie,final boolean increment) {

		Long vNumGuichetBonSortie = this.guichetierMensuelDomaine.getNextNumBonSortieReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonSortie.get(Calendar.YEAR);
		int moisActuel = pDateBonSortie.get(Calendar.MONTH) + 1;
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonSortie = new StringBuilder("");
		vNumBonSortie.append(vAnneeCourante);
		vNumBonSortie.append(String.format("%02d", moisActuel));
		vNumBonSortie.append(String.format("%04d", vNumGuichetBonSortie));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();

		/** idMensuel = (annuelcourante - 2016) + moisCourant */

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idMensuel = (anneActuelle - 2016)*12 + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonSortieCourante(new Long(vNumGuichetBonSortie + 1L));
		/** Modification de la valeur en base du numéro. */
		
		if(increment)
		this.guichetierMensuelDomaine.modifierGuichetBonSortieMensuel(vGuichetValeur);
		
		
		return vNumBonSortie.toString();

	}

	private boolean valueOfBoolean(Boolean value) {
		if (value == null)
			return false;
		else
			return value;
	}

	// Added on 03/10/2016, by Zeineb Medimagh

	public ListTraitFaconElementValue getTraitFaconElementList(List<String> refBonSortieList, Long livraisonVenteId) {

		// Récupérer les objets bon sorties à partir de la liste de référence
		// des bon de sortie
		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniPersistance.getListByBonSortieList(refBonSortieList);

		List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {

			// Construire la date de sortie
			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			// Récupérer la liste des rouleaux fini par bon de sortie
			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {
				
				listeRouleauFini.add(rouleau);
			}
		}

		// Map <refMise, Map<Poids, nbreRouleaux>>
		Map<String, Map<Double,Long>> mapMisePoids = new HashMap<String, Map<Double,Long>>();

		// Map <refMise, Map<Metrage, nbreRouleaux>>
		Map<String, Map<Double,Long>> mapMiseMetrage = new HashMap<String, Map<Double,Long>>();
		String unite = null;
		
		// Un rouleau soit contient un poids soit un métrage
		// Une mise possède 1-->* rouleaux
		// Un rouleau contient la référence de la mise
		for (RouleauFiniValue rouleauFiniValue : listeRouleauFini) {

			// Récupérer la référence de la mise mappée avec le rouleau courant
			String refMise = rouleauFiniValue.getReferenceMise();

			// Type de rouleau qui possède un poids uniquement
			if (rouleauFiniValue.getPoids() != null) {
				
				unite ="kg";
				if (mapMisePoids.containsKey(refMise)) {

					// Récupérer le poids total et le nbreRouleaux de la mise actuellement enregistrées
					// Poids mise = somme des poids de ses rouleaux
					
					Iterator it = mapMisePoids.entrySet().iterator();
					
					Map.Entry<String, Map<Double, Long>> mapMisePoidsCourant = (Map.Entry<String, Map<Double, Long>>) it.next();
					
					//donneesMise = <Metrage, nbreRouleaux>
					Map<Double, Long> donneesMiseElement = mapMisePoidsCourant.getValue();
					
					Iterator itDetail = donneesMiseElement.entrySet().iterator();
					Map.Entry<Double, Long> donneesMiseElementCourant = (Map.Entry<Double, Long>) itDetail.next();
					
					Double poidsMise = (Double) donneesMiseElementCourant.getKey(); // Récupérer le poids enregistré
					Long nbreRouleauxMise = donneesMiseElementCourant.getValue(); // Récupérer nbreRouleaux enregistrés
					
					// Ajouter le poids du rouleau courant au metrage total de la mise
					poidsMise += rouleauFiniValue.getPoids();

					//Augmenter le nbre de rouleaux par mise
					nbreRouleauxMise++;
					
					//Mettre à jour donneesMise (poids et nbreRouleaux)et le mapper à la référenceMiseCourante
					Map<Double, Long> newElementContent = new HashMap <Double, Long>();
					newElementContent.put(poidsMise, nbreRouleauxMise);
					mapMisePoids.put(refMise, newElementContent);
				} else {
					// Le rouleau courant ayant la referenceMise courante n'est pas encore ajouté à mapMisePoids
					// On initialise le poids total de la nouvelle mise au poids du rouleau courant
					// On initialise le nbreRouleaux à 1
					
					Map<Double, Long> initDonneesMise = new HashMap <Double, Long>();
					initDonneesMise.put(rouleauFiniValue.getPoids(), new Long(1));
					mapMisePoids.put(refMise, initDonneesMise);
					

				}
				
			}

			// Type de rouleau qui possède un métrage uniquement
			if (rouleauFiniValue.getMetrage() != null) {

				unite="m";
				if (mapMiseMetrage.containsKey(refMise)) {

					// Récupérer le poids total de la mise actuellement enregistrée
					// Métrage mise = somme des métrage de ses rouleaux
					Iterator it = mapMiseMetrage.entrySet().iterator();
					while(it.hasNext()){
						Map.Entry<String, Map<Double, Long>> mapMiseMetrageCourant = (Map.Entry<String, Map<Double, Long>>) it.next();
						if(mapMiseMetrageCourant.getKey().equals(refMise)){
							//System.out.println("---equal refMise---");
							//donneesMise = <Metrage, nbreRouleaux>
							Map<Double, Long> donneesMiseElement = mapMiseMetrageCourant.getValue();
							
							Iterator itDetail = donneesMiseElement.entrySet().iterator();
							Map.Entry<Double, Long> donneesMiseElementCourant = (Map.Entry<Double, Long>) itDetail.next();
							
							Double metrageMise = (Double) donneesMiseElementCourant.getKey(); // Récupérer le metrage enregistré
							Long nbreRouleauxMise = donneesMiseElementCourant.getValue(); // Récupérer nbreRouleaux enregistrés
							
							// Ajouter le poids du rouleau courant au metrage total de la mise
							metrageMise += rouleauFiniValue.getMetrage();
	
							//Augmenter le nbre de rouleaux par mise
							nbreRouleauxMise++;
							
							//Mettre à jour donneesMise (poids et nbreRouleaux)et le mapper à la référenceMiseCourante
							Map<Double, Long> newElementContent = new HashMap <Double, Long>();
							newElementContent.put(metrageMise, nbreRouleauxMise);
							mapMiseMetrage.put(refMise, newElementContent);
							
						}
					}
				}
				 else {
					// Le rouleau courant ayant la referenceMise courante n'est pas encore ajouté à mapMiseMetrage
					// On initialise le metrage total de la nouvelle mise au metrage du rouleau courant
					// On initialise le nbreRouleaux à 1
					Map<Double, Long> initDonneesMise = new HashMap <Double, Long>();
					initDonneesMise.put(rouleauFiniValue.getMetrage(), new Long(1));
					mapMiseMetrage.put(refMise, initDonneesMise);
					
				}
			}
		}
		
		//Après calcul poids/metrage de chaque mise, remplir une liste detLivraisonVenteValue
		
		ListTraitFaconElementValue resultat= new ListTraitFaconElementValue();
		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		
		
		//Parcourir la liste des mises ayant un poids calculé
		
		Iterator itPoids = mapMisePoids.entrySet().iterator();
		
		//Regrouper par bon de reception à partir de la liste des mises
		// Une mise peut avoir 1 -> * bon reception
		Map<String,Map<Double,Long>> mapBonReception = new HashMap<String,Map<Double,Long>>();
		
		while (itPoids.hasNext()) {
			Map.Entry<String, Map<Double,Long>> elementCourantMapPoids = (Map.Entry< String, Map<Double,Long>>) itPoids.next();
			
			String refMise= elementCourantMapPoids.getKey(); // Key : referenceMise
			Map<Double,Long> donneesMiseCourantes = new HashMap<Double, Long>();
			
			
			//Récupérer la mise courante
			MiseValue miseCourante = miseDomaine.getMiseByReference(refMise).get(0);
			
			//Récupérer refBonReception
			String refBonReception = miseCourante.getRefBonreception();
			
			//Si le bonReception est déja enregistré, on somme la quantité des mises relatives à ce bonReception
			if(mapBonReception.containsKey(refBonReception)){
			
				//Récupérer le poids de la mise courante
				donneesMiseCourantes = elementCourantMapPoids.getValue(); // Value : Map < Poids, nbreRouleaux>
				Iterator itMapMiseValues = donneesMiseCourantes.entrySet().iterator(); //Itérator pour  Map < Poids, nbreRouleaux>
				Map.Entry<Double,Long> donneesCourantesMiseCourantes = (Map.Entry<Double,Long>) itMapMiseValues.next();
				
				Double poidsCourant = donneesCourantesMiseCourantes.getKey();
				
				//Récupérer le poids enregistré dans mapBonReception
				Map<Double,Long> donneesBonReceptionCourantes = mapBonReception.get(refBonReception); // Value : Map < Poids, nbreRouleaux>
				Iterator itMapBonReceptionValues = donneesBonReceptionCourantes.entrySet().iterator(); //Itérator pour  Map < Poids, nbreRouleaux> de bonReception
				Map.Entry<Double,Long> donneesCourantesBonReceptionCourantes = (Map.Entry<Double,Long>) itMapBonReceptionValues.next();
				
				Double poidsEnregistre= donneesCourantesBonReceptionCourantes.getKey(); // Récupérer le poid enregistré dans key
				Long nbRouleauxEnregitre = donneesCourantesBonReceptionCourantes.getValue(); // Récupérer le nbreRouleaux enregistré dans value
				
				//Faire la somme du poidsEnregistré + poidsCourant de la mise pour le même bonReception
				poidsEnregistre+=poidsCourant;
				
				//Persister le changement dans la Map
				donneesBonReceptionCourantes.put(poidsEnregistre, nbRouleauxEnregitre);
				mapBonReception.put(refBonReception, donneesBonReceptionCourantes);
				
			}else{
				mapBonReception.put(refBonReception, donneesMiseCourantes);
			}
			
		}
		
		
		//Parcourir la liste des mises ayant un métrage calculé
		
		Iterator itMetrage = mapMiseMetrage.entrySet().iterator();
		
		while (itMetrage.hasNext()) {
			Map.Entry<String, Map<Double,Long>> elementCourantMapMetrage = (Map.Entry< String, Map<Double,Long>>) itMetrage.next();
			
			String refMise= elementCourantMapMetrage.getKey(); // Key : referenceMise
			Map<Double,Long> donneesMiseCourantes = new HashMap<Double, Long>();
			
			//Récupérer la mise courante
			MiseValue miseCourante = miseDomaine.getMiseByReference(refMise).get(0);
			
			//Récupérer refBonReception
			String refBonReception = miseCourante.getRefBonreception();
			
			//Si le bonReception est déja enregistré, on somme la quantité des mises relatives à ce bonReception
			donneesMiseCourantes = elementCourantMapMetrage.getValue(); // Value : Map < Metrage, nbreRouleaux>
			
			if(mapBonReception.containsKey(refBonReception)){
			
				//Récupérer le metrage de la mise courante
				
				Iterator itMapMiseValues = donneesMiseCourantes.entrySet().iterator(); //Itérator pour  Map < Metrage, nbreRouleaux>
				Map.Entry<Double,Long> donneesCourantesMiseCourantes = (Map.Entry<Double,Long>) itMapMiseValues.next();
				
				Double metrageCourant = donneesCourantesMiseCourantes.getKey();
				
				//Récupérer le poids enregistré dans mapBonReception
				Map<Double,Long> donneesBonReceptionCourantes = mapBonReception.get(refBonReception); // Value : Map < Metrage, nbreRouleaux>
				Iterator itMapBonReceptionValues = donneesBonReceptionCourantes.entrySet().iterator(); //Itérator pour  Map < Metrage, nbreRouleaux> de bonReception
				Map.Entry<Double,Long> donneesCourantesBonReceptionCourantes = (Map.Entry<Double,Long>) itMapBonReceptionValues.next();
				
				Double metrageEnregistre= donneesCourantesBonReceptionCourantes.getKey(); // Récupérer le Metrage enregistré dans key
				Long nbRouleauxEnregitre = donneesCourantesBonReceptionCourantes.getValue(); // Récupérer le nbreRouleaux enregistré dans value
				
				donneesBonReceptionCourantes.remove(metrageEnregistre);
				//Faire la somme du metrageEnregistre + metrageCourant de la mise pour le même bonReception
				metrageEnregistre+=metrageCourant;
				
				//Persister le changement dans la Map
				donneesBonReceptionCourantes.put(metrageEnregistre, nbRouleauxEnregitre);
				mapBonReception.put(refBonReception, donneesBonReceptionCourantes);
				
			}else{
				mapBonReception.put(refBonReception, donneesMiseCourantes);
			}
		}
		
		//Traitement commun (poids / metrage)
		//Parcourir la liste des bons de reception 
		// Et remplir la liste des traitements
		Iterator it = mapBonReception.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, Map<Double,Long>> elementCourantMapBonReception = (Map.Entry<String, Map<Double,Long>>) it.next();
			
			String refBonReception = elementCourantMapBonReception.getKey(); 
			Map<Double,Long> donneesMapBonReception = elementCourantMapBonReception.getValue();
			
			Iterator itDetails = donneesMapBonReception.entrySet().iterator();
			String concatinatedListRefMiseParBR = miseDomaine.listRefMiseParRefBR(refBonReception);
			
			while(itDetails.hasNext()){
				Map.Entry<Double, Long> donneesCourantesMapBonReception = (Map.Entry<Double, Long>) itDetails.next();
				
				Double qteCourante = donneesCourantesMapBonReception.getKey();
				Long nbRouleauxCourant = donneesCourantesMapBonReception.getValue();
				
				//Récupérer la ficheFacon à partir de refBonReception
				List<FicheFaconValue> ficheFaconResult = ficheFaconDomaine.getFicheByRefBonReception(refBonReception);
				
				if(ficheFaconResult.size()>0){
					FicheFaconValue ficheFacon= ficheFaconDomaine.getFicheByRefBonReception(refBonReception).get(0);
					
					//Récupérer liste des traitements attachés à la fiche façon
					List<TraitementFicheValue> listTraitementFiche = ficheFacon.getListeTraitementsFiche();
					
					// Chaque ligne détail livraison doit contenir chaque traitement
					// Le poid correspond à celui de la mise
										
					for (TraitementFicheValue traitementFicheValue : listTraitementFiche) {
						
						DetLivraisonVenteValue elementLivraison = new DetLivraisonVenteValue();
						
						//Remplir les champs details livraison de chaque mise pour chaque traitement
						elementLivraison.setTraitementFaconId(traitementFicheValue.getTraitementId());
						elementLivraison.setQuantite(qteCourante); 
						elementLivraison.setNombreColis(nbRouleauxCourant); 
						elementLivraison.setUnite(unite); 
						elementLivraison.setFicheId(ficheFacon.getId());
					
						elementLivraison.setRefMiseList(concatinatedListRefMiseParBR);
						
						if(traitementFicheValue.getPu() != null){
							elementLivraison.setPrixUnitaireHT(traitementFicheValue.getPu());
						}
						if(traitementFicheValue.getPu() != null && elementLivraison.getQuantite()!=null){
							elementLivraison.setPrixTotalHT(traitementFicheValue.getPu()*elementLivraison.getQuantite());
						}
						
						listDetLivraisonVente.add(elementLivraison);
					}
				}
			}
			
		}
			
		if(listDetLivraisonVente.size()>0){
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteFaconValidateComparator());
		}
		
		resultat.setNombreResultaRechercher(listDetLivraisonVente.size());
		resultat.setDateSortie(dateSortie);
		resultat.setListDetLivraisonVente(listDetLivraisonVente);
	
		return resultat;

	}

	@Override
	public List<BonSortieFiniOptimizedValue> getBonSortieEnCours() {
		// TODO Auto-generated method stub
		return bonSortieFiniPersistance.getBonSortieEnCours();
	}

	@Override
	public BonSortieFiniOptimizedValue getBonSortieFiniOptimizedById(Long id) {
		// TODO Auto-generated method stub
		return bonSortieFiniPersistance.getBonSortieFiniOptimizedById(id);
	}

	@Override
	public List<BonSortieFiniValue> getListByBonSortieList(List<String> refBonSortieList) {
		// TODO Auto-generated method stub
		return bonSortieFiniPersistance.getListByBonSortieList(refBonSortieList);
	}

	@Override
	public ListProduitElementValue getProduitElementListByOF(List<String> refBonSortieList, Long livraisonVenteId) {


		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniPersistance.getListByBonSortieList(refBonSortieList);

		List<RouleauFiniValue> listeRouleauFini = new ArrayList<RouleauFiniValue>();

		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			for (RouleauFiniValue rouleau : bonSortie.getListeRouleauFini()) {

				listeRouleauFini.add(rouleau);
			}
		}

		Map<Map<String, Long>, List<RouleauFiniValue>> mapRouleau = new HashMap<Map<String, Long>, List<RouleauFiniValue>>();

		for (RouleauFiniValue rouleau : listeRouleauFini) {
			
			//Long produitKey = rouleau.getProduitId();
			
			
			String produitKey = rouleau.getReferenceMise() ;
			
			Long choixKey = rouleau.getChoix();

			Map<String, Long> map = new HashMap<String, Long>();

			map.put(produitKey, choixKey);

			if (mapRouleau.get(map) == null) {

				mapRouleau.put(map, new ArrayList<RouleauFiniValue>());
			}

			mapRouleau.get(map).add(rouleau);

		}

		ProduitValue produitValue = null;

		Iterator it = mapRouleau.entrySet().iterator();
		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		
		
		Long ordre = new Long(1);
		
		while (it.hasNext()) {

			Map.Entry<Map<String, Long>, List<RouleauFiniValue>> pair = (Map.Entry<Map<String, Long>, List<RouleauFiniValue>>) it
					.next();

			DetLivraisonVenteValue element = new DetLivraisonVenteValue();

			//Long produitId = null;
			
			String produitId = null;
			
			Long choixId = null;

			Map<String, Long> produitIdchoixIdMap = pair.getKey();
			Iterator produitIdchoixIdIt = produitIdchoixIdMap.entrySet().iterator();

			Map.Entry<String, Long> produitIdchoixIdPair = (Map.Entry<String, Long>) produitIdchoixIdIt.next();
			produitId = produitIdchoixIdPair.getKey();
			choixId = produitIdchoixIdPair.getValue();

		//	element.setProduitId(produitId);
			
			
			element.setNumeroOF(produitId);

			if (choixId != null) {
				ChoixRouleauValue choixRouleau = choixRouleauPersistance.getChoixRouleauById(choixId);
				if (choixRouleau != null)
					element.setChoix(choixRouleau.getDesignation());
			}

			Double sommeMetrage = ZERO;
			Double sommePoids = ZERO;
			
			List<String> refMiseList = new ArrayList<String>();
			
		//	String referenceMises = "";
			
			
			for (RouleauFiniValue rouleau : pair.getValue()) {
				// Somme des metrages
				if (rouleau.getMetrage() != null) {
					sommeMetrage = rouleau.getMetrage().doubleValue() + sommeMetrage;
				}
				if (rouleau.getPoids() != null) {
					sommePoids = rouleau.getPoids().doubleValue() + sommePoids;
				}
				
				
				element.setProduitId(rouleau.getProduitId());
				
			
				
			//	if(rouleau.getReferenceMise() != null &&  !refMiseList.contains(rouleau.getReferenceMise()))
				//	refMiseList.add(rouleau.getReferenceMise());
				
					
			}
			
		
		/*	if(refMiseList.size() > 0) {
				
				
				  element.setNumeroOF(StringUtils.join(refMiseList, ", "));

			}
			*/
			    

			// Qte livree = La somme des metrage des rouleaux de produit
			element.setQuantite(sommeMetrage);
			if (sommePoids > ZERO)
				element.setQuantite(sommePoids);

			if (element.getProduitId() != null) {
				produitValue = produitPersistance.rechercheProduitById(element.getProduitId());
				if (produitValue != null) {

				/*	SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
							.rechercheSousFamilleProduitById(produitValue.getSousFamilleId());
					if (sousFamilleProduitValue != null) {
						String designation = (sousFamilleProduitValue.getDesignation() == null) ? EMPTY
								: sousFamilleProduitValue.getDesignation().toLowerCase();
						element.setUnite((designation.equalsIgnoreCase(MAILLE)) ? KG : M);
					}*/
					
					
					element.setUnite(produitValue.getUnite());

					if (livraisonVenteId != null) {
						DetLivraisonVenteValue detLivraisonVenteValue = detLivraisonVentePersistance
								.getBylivraisonVenteAndOF(livraisonVenteId, element.getNumeroOF(), element.getChoix());
						if (detLivraisonVenteValue != null) {

							boolean detailIdNotExist = true;
							for (DetLivraisonVenteValue detail : listDetLivraisonVente) {
								if (detail.getId() == detLivraisonVenteValue.getId())
									detailIdNotExist = false;
							}

							if (detailIdNotExist) {
								element.setId(detLivraisonVenteValue.getId());
							}

							element.setLivraisonVenteId(detLivraisonVenteValue.getLivraisonVenteId());
							element.setRemise(detLivraisonVenteValue.getRemise());
							
							element.setFicheId(detLivraisonVenteValue.getFicheId());
						}
					}

					element.setProduitDesignation(produitValue.getDesignation());
					element.setProduitReference(produitValue.getReference());
					element.setPrixUnitaireHT(produitValue.getPrixUnitaire());
					if (produitValue.getPrixUnitaire() != null)
						element.setPrixTotalHT(produitValue.getPrixUnitaire() * element.getQuantite());
				}
			}

			element.setNombreColis(Long.valueOf(pair.getValue().size()));
			
			
			element.setFicheId(ordre);

			listDetLivraisonVente.add(element);
			
			
			ordre++;

			it.remove();
		}

		if(listDetLivraisonVente.size()>0){
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
		}
		

		ListProduitElementValue result = new ListProduitElementValue();

		result.setNombreResultaRechercher(listDetLivraisonVente.size());
		result.setDateSortie(dateSortie);
		result.setListDetLivraisonVente(listDetLivraisonVente);

		return result;

	}

	@Override
	public List<String> getListBonSortieFiniRefByClientId(Long clientId) {


		List<String> listBonSortieFiniToRemove = new ArrayList<String>();
		
		RechercheMulticritereBonLivraisonValue requestBl = new RechercheMulticritereBonLivraisonValue();
		
		requestBl.setPartieIntId(clientId);

		Set<LivraisonVenteValue> LivraisonVenteList = bonLivraisonPersistance.rechercherMultiCritere(requestBl).getList();

		for (LivraisonVenteValue livraisonVente : LivraisonVenteList) {

			String infoSortieSplitted[];

			if (livraisonVente.getInfoSortie() != null) {

				infoSortieSplitted = livraisonVente.getInfoSortie().split(SEPARATOR);

				for (int index = 0; index < infoSortieSplitted.length; index++) {

					listBonSortieFiniToRemove.add(infoSortieSplitted[index]);

				}
			}

		}

		List<String> listBonSortieFini = new ArrayList<String>();

		RechercheMulticritereBonSortieFiniValue requestBs = new RechercheMulticritereBonSortieFiniValue();
		requestBs.setPartieInt(clientId);
		
		Set<BonSortieFiniValue> bonSortieFinilist = bonSortieFiniPersistance.rechercherMultiCritereOptimized(requestBs).getList();

		for (BonSortieFiniValue bonSortieFini : bonSortieFinilist) {
			if (bonSortieFini.getReference() != null && bonSortieFini.getReference().length() != 0)
				listBonSortieFini.add(bonSortieFini.getReference());
		}

		listBonSortieFini.removeAll(listBonSortieFiniToRemove);

		return listBonSortieFini;
	}
}
	

