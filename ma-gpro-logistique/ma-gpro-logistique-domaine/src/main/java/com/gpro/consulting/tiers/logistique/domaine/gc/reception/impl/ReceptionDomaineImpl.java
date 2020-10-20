package com.gpro.consulting.tiers.logistique.domaine.gc.reception.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ProduitReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ReceptionVenteValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.RechercheMulticritereBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.reception.value.ResultatRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.RechercheMulticritereProduitDepotValue;
import com.gpro.consulting.tiers.logistique.coordination.produitdepot.value.ResultatRechercheProduitDepotValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.reception.IReceptionDomaineGC;
import com.gpro.consulting.tiers.logistique.persistance.gc.guichet.IGuichetAnnuelPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gc.reception.IReceptionPersistance;
import com.gpro.consulting.tiers.logistique.persistance.produitdepot.IProduitDepotPersistance;

/**
 *
 */

@Component
public class ReceptionDomaineImpl implements IReceptionDomaineGC {

	private static final Logger logger = LoggerFactory.getLogger(ReceptionDomaineImpl.class);

	private static final Double ZERO_D = 0D;

	@Autowired
	private IReceptionPersistance receptionPersistance;

	@Autowired
	private IGuichetAnnuelPersistance guichetAnnuelPersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IProduitDepotPersistance produitDepotPersistance;

	@Override
	public String create(ReceptionVenteValue bonReceptionValue) {

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

		//logger.info("----- bonReceptionValue.getReference() ----------" + bonReceptionValue.getReference());

		if ((bonReceptionValue.getReference() != null && bonReceptionValue.getReference().equals(""))
				|| bonReceptionValue.getReference() == null) {

			bonReceptionValue.setReference(this.getNumeroBonReception(bonReceptionValue.getDateIntroduction()));

			//logger.info("----- auto reference ----------" + bonReceptionValue.getReference());

		}

		for (ProduitReceptionValue produitReception : bonReceptionValue.getListProduitReceptions()) {

			if (produitReception.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitReception.getQuantite();

				// MAJ Qte Produit
				if (produitReception.getProduitId() != null) {

					ProduitValue produitValue = produitPersistance
							.rechercheProduitById(produitReception.getProduitId());
					Double qte = 0D;
					if (produitValue.getQuantite() != null) {
						qte = produitValue.getQuantite() + produitReception.getQuantite();
					} else {
						qte = produitReception.getQuantite();
					}

					produitValue.setQuantite(qte);
					produitPersistance.modifierProduit(produitValue);

					if (bonReceptionValue.getIdDepot() != null) {

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

				}

			}

			if (produitReception.getPrixUnitaire() != null && produitReception.getQuantite() != null) {
				coutCommandeVenteTotal += (produitReception.getPrixUnitaire() * produitReception.getQuantite());
			}

			produitReception.setDateLivraison(bonReceptionValue.getDateLivraison());
		}

		bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		return receptionPersistance.create(bonReceptionValue);
	}

	@Override
	public ReceptionVenteValue getById(Long id) {
		return receptionPersistance.getById(id);
	}

	@Override
	public String update(ReceptionVenteValue bonReceptionValue) {

		Double quantiteCommandeVenteTotal = ZERO_D;
		Double coutCommandeVenteTotal = ZERO_D;

		for (ProduitReceptionValue produitReception : bonReceptionValue.getListProduitReceptions()) {

			if (produitReception.getQuantite() != null) {

				quantiteCommandeVenteTotal += produitReception.getQuantite();
			}

			if (produitReception.getPrixUnitaire() != null) {
				coutCommandeVenteTotal += (produitReception.getPrixUnitaire() * produitReception.getQuantite());
			}

			produitReception.setDateLivraison(bonReceptionValue.getDateLivraison());

			// TODO Reception update MAJ des quantités

		}

		bonReceptionValue.setQuantite(quantiteCommandeVenteTotal);
		bonReceptionValue.setPrixTotal(coutCommandeVenteTotal);

		return receptionPersistance.update(bonReceptionValue);
	}

	@Override
	public void delete(Long id) {
		receptionPersistance.delete(id);
	}

	@Override
	public List<ReceptionVenteValue> getAll() {
		return receptionPersistance.getAll();
	}

	@Override
	public ResultatRechecheBonReceptionValue rechercherMultiCritere(RechercheMulticritereBonReceptionValue request) {
		return receptionPersistance.rechercherMultiCritere(request);
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
	private String getNumeroBonReception(final Calendar pDateBonCommande) {

		Long vNumGuichetBonCommande = this.guichetAnnuelPersistance.getNextNumBonCommandeReference();
		/** Année courante. */
		int vAnneeCourante = pDateBonCommande.get(Calendar.YEAR);
		/** Format du numero du Bon commande= AAAA-NN. */
		StringBuilder vNumBonCommande = new StringBuilder("");
		vNumBonCommande.append(vAnneeCourante);
		vNumBonCommande.append(vNumGuichetBonCommande);
		/** Inserer une nouvelle valeur dans Guichet BonCommande. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) + 1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceCommandeCourante(new Long(vNumGuichetBonCommande + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelPersistance.modifierGuichetBonCommandeAnnuel(vGuichetValeur);
		return vNumBonCommande.toString();
	}

}
