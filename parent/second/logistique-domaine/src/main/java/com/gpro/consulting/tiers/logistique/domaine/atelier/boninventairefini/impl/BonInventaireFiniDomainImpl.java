package com.gpro.consulting.tiers.logistique.domaine.atelier.boninventairefini.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetMensuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.BonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.RechercheMulticritereBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.boninventairefini.value.ResultatRechecheBonInventaireFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.boninventairefini.IBonInventaireFiniDomain;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonsortiefini.IBonSortieFiniDomain;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetMensuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.facon.IFicheFaconDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.boninventairefini.IBonInventaireFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IChoixRouleauPersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.IDetLivraisonVentePersistance;

/**
 * Implementation of {@link IBonSortieFiniDomain}
 * 
 * @author Ghazi Atroussi
 * @since 18/12/2016
 *
 */
@Component
public class BonInventaireFiniDomainImpl implements IBonInventaireFiniDomain {

	private static final Logger logger = LoggerFactory.getLogger(BonInventaireFiniDomainImpl.class);

	private static final Double ZERO = 0D;
	private static final String MAILLE = "maille";
	private static final String KG = "kg";
	private static final String M = "m";
	private static final String EMPTY = "";
	private static final String SEPARATOR = "-";

	@Autowired
	private IBonInventaireFiniPersistance bonInventaireFiniPersistance;

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
	public List<RouleauFiniValue> validateBonInventaireFini(List<String> barreCodeList, Long id) {

		List<RouleauFiniValue> listeRouleauFini = rouleauFiniPersistance
				.getRouleauFiniInventaireListByBarreCodeList(barreCodeList, id);
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
	public String createBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue) {

		if (bonSortieFiniValue.getReference() == null || bonSortieFiniValue.getReference().equals("")) {
			bonSortieFiniValue.setReference(getNumeroBonSotie(Calendar.getInstance()));
			bonSortieFiniValue.getReference().concat(getNumeroBonSotie(Calendar.getInstance()));

		}

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

		String idBonSortieFiniSaved = bonInventaireFiniPersistance.createBonInventaireFini(bonSortieFiniValue);

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
	public BonInventaireFiniValue getBonInventaireFiniById(Long id) {

		return bonInventaireFiniPersistance.getBonInventaireFiniById(id);
	}

	@Override
	public ResultatRechecheBonInventaireFiniValue rechercherMultiCritere(RechercheMulticritereBonInventaireFiniValue request) {

		return bonInventaireFiniPersistance.rechercherMultiCritere(request);
	}

	@Override
	public String updateBonInventaireFini(BonInventaireFiniValue bonSortieFiniValue) {

		List<RouleauFiniValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();

		// Put NULL to bonsortieId for each RouleauFini deleted from
		// BonSortieFini
		BonInventaireFiniValue oldBonSortieFiniValue = bonInventaireFiniPersistance
				.getBonInventaireFiniById(bonSortieFiniValue.getId());

		for (RouleauFiniValue rouleauFini : oldBonSortieFiniValue.getListeRouleauFini()) {

			if (!listeRouleauFini.contains(rouleauFini)) {
				rouleauFini.setBonInventaire(null);
				rouleauFini.setDateInventaire(null);
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

		String idBonSortieFiniSaved = bonInventaireFiniPersistance.updateBonInventaireFini(bonSortieFiniValue);

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
	public void deleteBonInventaire(Long id) {

		BonInventaireFiniValue bonSortieFiniValue = bonInventaireFiniPersistance.getBonInventaireFiniById(id);

		if (bonSortieFiniValue != null) {
			List<RouleauFiniValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();
			for (RouleauFiniValue rouleauFiniValue : listeRouleauFini) {
				rouleauFiniValue.setBonInventaire(null);
				rouleauFiniValue.setDateInventaire(null);
				rouleauFiniPersistance.updateRouleauFini(rouleauFiniValue);
			}

			bonSortieFiniValue.setListeRouleauFini(null);
			bonInventaireFiniPersistance.updateBonInventaireFini(bonSortieFiniValue);
			bonInventaireFiniPersistance.deleteBonInventaireFini(id);
		}

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
	private String getNumeroBonSotie(final Calendar pDateBonSortie) {

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

		int idMensuel = (anneActuelle - 2016) + moisActuel;

		vGuichetValeur.setId(new Long(idMensuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonSortieCourante(new Long(vNumGuichetBonSortie + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetierMensuelDomaine.modifierGuichetBonSortieMensuel(vGuichetValeur);
		return vNumBonSortie.toString();

	}

	private boolean valueOfBoolean(Boolean value) {
		if (value == null)
			return false;
		else
			return value;
	}

	
}
	

