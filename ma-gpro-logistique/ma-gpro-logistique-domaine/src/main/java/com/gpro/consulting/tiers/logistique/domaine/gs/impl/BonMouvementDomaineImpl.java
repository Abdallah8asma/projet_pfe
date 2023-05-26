package com.gpro.consulting.tiers.logistique.domaine.gs.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.logistique.coordination.gc.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.domaine.elementBase.IArticleDomaine;
import com.gpro.consulting.tiers.logistique.coordination.gs.IConstante;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockSupprime;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockSupprimeElement;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.domaine.gc.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gs.IBonMouvementDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IFicheSuiveusePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IBonMouvementPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IEntiteStockPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IMouvementPersistance;

@Component
public class BonMouvementDomaineImpl implements IBonMouvementDomaine {

	private final static Long ZEROL = 0L;
	private final static Double ZEROD = 0D;
	private final static String STANDARD = "STANDARD";
	private final static String LOT = "LOT";
	private final static String ENTREE = "ENTREE";
	private static final String TYPE_RESERVATION = "RESERVATION";

	@Autowired
	private IBonMouvementPersistance bonMouvementPersistance;

	@Autowired
	private IEntiteStockPersistance entiteStockPersistance;

	@Autowired
	private IArticleDomaine articleDomaine;

	// @Autowired
	// private IOrdreFabricationPersistance ordreFabricationPersistance;

	@Autowired
	private IMouvementPersistance mouvementPersistance;
	
	@Autowired 
	private IFicheSuiveusePersistance ficheSuiveusePersistance;
	
	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BonMouvementDomaineImpl.class);

	@Override
	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
			RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue) {

		return bonMouvementPersistance.rechercherBonMouvementMultiCritere(pRechercheMulticritereMouvementStockValue);
	}

	@Override
	public String creerBonMouvement(BonMouvementStockValue bonMouvementStock) {
		
		if(bonMouvementStock.getDate() == null)
			bonMouvementStock.setDate(Calendar.getInstance());
			

		BonMouvementStockValue request = new BonMouvementStockValue();

		if (bonMouvementStock.getType() != null) {

			switch (bonMouvementStock.getType()) {
			case IConstante.TYPE_MVT_ENT:
				String numeroBonEntree = this.getNumeroBonMouvementEntre(bonMouvementStock.getDate());
				bonMouvementStock.setNumero(numeroBonEntree);
				request = enrichirBonEntree(bonMouvementStock);
				
				//request.setNumero(numeroBonEntree);
			
				break;

			case IConstante.TYPE_MVT_SORT:
				
			

				if (bonMouvementStock.isSoustractionSortie()){

					request = enrichirCreationBonSortieSoustractionStock(bonMouvementStock);
					
					
				} else {

					request = enrichirBonSortie(bonMouvementStock);
				}
				request.setNumero(this.getNumeroBonMouvementSortie(bonMouvementStock.getDate()));
				break;

			case IConstante.TYPE_MVT_RES:
				request = enrichirReservation(bonMouvementStock);
				break;

			default:
				break;

			}
		}

		return bonMouvementPersistance.creerBonMouvement(request);
	}

	public Double calculerEquivalenceCone(EntiteStockValue pEntiteStock) {
		Double vEquivalenceCone = new Double(0);
		return vEquivalenceCone;
	}

	public Double calculerPrixMoyenPondere(EntiteStockValue pEntiteStock, MouvementStockValue pMouvementStock) {
		Double vPMP = new Double(0);
		return vPMP;
	}
	
	// enrichirBonEntree
	public BonMouvementStockValue enrichirBonEntree(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListMouvementResultat = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {

			// A changer
			ArticleValue vArticleValue = new ArticleValue();

			vArticleValue.setId(mouvement.getIdArticle());
			vArticleValue = articleDomaine.rechercheArticleParId(vArticleValue);

			EntiteStockValue vEntiteStock = new EntiteStockValue();

	
			// Creation de l'entité stock si elle n'existe pas
			
			if(vArticleValue != null && mouvement.getQuantiteReelle() != null) {
				
				
				vEntiteStock.setNumeroBonEntree(pBonMouvementStockValue.getNumero());
				vEntiteStock.setDateEntree(pBonMouvementStockValue.getDate());
				
				
				vEntiteStock.setArticle(mouvement.getIdArticle());
				vEntiteStock.setReferenceArticle(vArticleValue.getRef());
				vEntiteStock.setLibelleArticle(vArticleValue.getDesignation());
				
				
				vEntiteStock.setMagasin(mouvement.getIdMagasin());
				vEntiteStock.setPmp(new Double(0));
				vEntiteStock.setPrixUnitaire(new Double(0));
				vEntiteStock.setEmplacement(IConstante.VIDE);
				
				vEntiteStock.setQteActuelle(new Double(0));
				vEntiteStock.setQteReservee(new Double(0));
				
				vEntiteStock.setQteActuelle(mouvement.getQuantiteReelle());
				
				vEntiteStock.setQteEntree(mouvement.getQuantiteReelle());
				
				vEntiteStock.setPrixUnitaire(mouvement.getPrixUnitaire());
				
				
				vEntiteStock.setReferenceLot(mouvement.getLot());
				
				
				vEntiteStock.setEmplacement(mouvement.getEmplacement());
				
				

				mouvement.setEntiteStock(vEntiteStock.getId());
				mouvement.setEntiteStockValue(vEntiteStock);
				
				
				
				
				
				vListMouvementResultat.add(mouvement);
				
			}


		
		}
		vBonMouvementStockValue.setMouvements(vListMouvementResultat);
		return vBonMouvementStockValue;
	}

	// enrichirBonEntree
	public BonMouvementStockValue enrichirBonEntreeAncien(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListMouvementResultat = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {

			// A changer
			ArticleValue vArticleValue = new ArticleValue();

			vArticleValue.setId(mouvement.getIdArticle());
			vArticleValue = articleDomaine.rechercheArticleParId(vArticleValue);

			EntiteStockValue vEntiteStock = new EntiteStockValue();

			if (vArticleValue.getMethodeGestion() != null && vArticleValue.getMethodeGestion().equals(STANDARD)) {
				vEntiteStock = entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
						mouvement.getIdMagasin());
			}
			if (vArticleValue.getMethodeGestion() != null && vArticleValue.getMethodeGestion().equals(LOT)
					&& mouvement.getLot() != null) {
				vEntiteStock = entiteStockPersistance.rechercheEntiteStockByLotMagasin(mouvement.getIdArticle(),
						mouvement.getLot(), mouvement.getIdMagasin());

				System.out.println("-------------------------LOT" + vEntiteStock);

			}
			if (vArticleValue.getMethodeGestion() != null && vArticleValue.getMethodeGestion().equals(ENTREE)
					&& mouvement.getLot() != null) {
				vEntiteStock = entiteStockPersistance.rechercheEntiteStockByLotDateMagasin(mouvement.getIdArticle(),
						mouvement.getLot(), mouvement.getIdMagasin(), pBonMouvementStockValue.getDate());
				System.out.println("-------------------------ENTREE" + vEntiteStock);

			}

			System.out.println("-------------------------after tests standard lot entree" + vEntiteStock);

			// Creation de l'entité stock si elle n'existe pas
			if (vEntiteStock == null || vEntiteStock.getArticle() == null) {
				System.out.println("-------------------------vEntiteStock=null---------------------------------------");

				vEntiteStock = new EntiteStockValue();
				System.out.println("-------------------------vEntiteStock=null---mouvement.getIdArticle()"
						+ mouvement.getIdArticle());

				vEntiteStock.setArticle(mouvement.getIdArticle());
				vEntiteStock.setMagasin(mouvement.getIdMagasin());
				vEntiteStock.setPmp(new Double(0));
				vEntiteStock.setPrixUnitaire(new Double(0));
				vEntiteStock.setEmplacement(IConstante.VIDE);
				if (mouvement.getTypeArticle().equals(3L)) {
					vEntiteStock.setConeReserve(new Long(0));
					vEntiteStock.setConesActuel(new Long(0));
					vEntiteStock.setFinconeActuel(new Long(0));
					vEntiteStock.setFinconeReserve(new Long(0));
					vEntiteStock.setPoidsActuel(new Double(0));
					vEntiteStock.setPoidsReserve(new Double(0));
					vEntiteStock.setEquivalenceCone(new Double(0));
				} else {
					vEntiteStock.setQteActuelle(new Double(0));
					vEntiteStock.setQteReservee(new Double(0));
					if (mouvement.getTypeArticle().equals(2L)) {
						vEntiteStock.setRouleauxActuel(new Long(0));
						vEntiteStock.setRouleauxReserve(new Long(0));
					}
				}
				// entiteStockPersistance.creerEntiteStock(vEntiteStock);
				// if (!vArticleValue.getMethodeGestion().equals(STANDARD)) {
				// if (mouvement.getLot() != null) {
				// vEntiteStock.setReferenceLot(mouvement.getLot());
				// vEntiteStock.setDateLot(pBonMouvementStockValue.getDate());
				// }
				// if (vArticleValue.getMethodeGestion().equals(ENTREE))
				// vEntiteStock.setDateEntree(pBonMouvementStockValue.getDate());
				// }

				System.out.println("-------------------------vEntiteStock=null" + vEntiteStock);

			}
			System.out.println("---------------After---------vEntiteStock=null" + vEntiteStock);

			// // Mise à jour de l'entité Stock
			// vEntiteStock =
			// entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
			// mouvement.getIdMagasin());
			if (mouvement.getTypeArticle().equals(3L)) {
				if (mouvement.getConesReel() != null)
					vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() + mouvement.getConesReel());
				if (mouvement.getFinconesReel() != null) {
					vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() + mouvement.getFinconesReel());
				}
				if (vEntiteStock.getPoidsActuel() != null && mouvement.getPoidsReel() != null) {
					vEntiteStock.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() + mouvement.getPoidsReel()));
				}
				vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));

			} else {
				if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null) {

					vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() + mouvement.getQuantiteReelle());

					if (mouvement.getTypeArticle().equals(2L)) {
						vEntiteStock
								.setRouleauxActuel(vEntiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());
					}
				}

			}
			// ArticleValue ar
			if (vEntiteStock.getEmplacement() != null && !vEntiteStock.getEmplacement().equals(IConstante.VIDE))
				vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + IConstante.SEPARATEUR);
			vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + mouvement.getEmplacement());
			vEntiteStock.setPrixUnitaire(mouvement.getPrixUnitaire());
			vEntiteStock.setPmp(calculerPrixMoyenPondere(vEntiteStock, mouvement));
			// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
			System.out.println(
					"------------before mouvement.setEntiteStock-------------vEntiteStock=null" + vEntiteStock);

			mouvement.setEntiteStock(vEntiteStock.getId());
			mouvement.setEntiteStockValue(vEntiteStock);
			// mouvement.setPrixTotal(prixTotal);
			vListMouvementResultat.add(mouvement);
		}
		vBonMouvementStockValue.setMouvements(vListMouvementResultat);
		return vBonMouvementStockValue;
	}

	// Calcul modifié par Ghazi le 27/11/2016
	private BonMouvementStockValue enrichirReservation(BonMouvementStockValue bonMouvementStock) {

		BonMouvementStockValue request = bonMouvementStock;
		Set<MouvementStockValue> listMouvementStock = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : request.getMouvements()) {
			System.out.println("-- Mouvement----" + mouvement.toString());
			//LOGGER.info("---------entitéStock ID---------- " + mouvement.getEntiteStock());
			EntiteStockValue entiteStock = entiteStockPersistance.rechercheEntiteStockParId(mouvement.getEntiteStock());

			if (entiteStock != null) {
				//LOGGER.info("---------entiteStock ! null---------- ");

				if (mouvement.getTypeArticle() != null) {

					if (mouvement.getTypeArticle().equals(3L)) {

						if (mouvement.getConesReel() != null && entiteStock.getConesActuel() != null) {

							if (mouvement
									.getConesReel() <= (entiteStock.getConesActuel() - entiteStock.getConeReserve())) {

								entiteStock.setConeReserve(entiteStock.getConeReserve() + mouvement.getConesReel());
							}
						}
						if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
								&& entiteStock.getFinconeActuel() != null && entiteStock.getPoidsActuel() != null) {

							if (mouvement.getFinconesReel() <= (entiteStock.getFinconeActuel()
									- entiteStock.getFinconeReserve())) {
								entiteStock.setFinconeReserve(
										entiteStock.getFinconeReserve() + mouvement.getFinconesReel());
								entiteStock.setPoidsActuel(
										new Double(entiteStock.getPoidsReserve() + mouvement.getPoidsReel()));
								entiteStock.setEquivalenceCone(calculerEquivalenceCone(entiteStock));
							}
						}

					} else {

						if (entiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null) {
							if ((entiteStock.getQteActuelle() - entiteStock.getQteReservee()) >= mouvement
									.getQuantiteReelle()) {
								entiteStock
										.setQteReservee(entiteStock.getQteReservee() + mouvement.getQuantiteReelle());
							}
						}
						if (mouvement.getTypeArticle().equals(2L)) {

							if (entiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null) {
								if ((entiteStock.getRouleauxActuel() - entiteStock.getRouleauxReserve()) >= mouvement
										.getNbRouleauxReel()) {
									entiteStock.setRouleauxReserve(
											entiteStock.getRouleauxReserve() + mouvement.getNbRouleauxReel());
								}
							}
						}
					}

					mouvement.setEntiteStockValue(entiteStock);
					listMouvementStock.add(mouvement);
				}
			}

			// OrdreFabricationValue OF =
			// ordreFabricationPersistance.rechercheOrdreFabricationParId(request.getOfId());
			//
			// if (OF != null) {
			// request.setPartieintId(OF.getPartieInterresId());
			// }
		}

		//LOGGER.info("------------listMouvementStock.size: " + listMouvementStock.size());

		request.setMouvements(listMouvementStock);

		return request;

	}

	@Override
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement) {

		BonMouvementStockValue vBonMouvementStockValue = new BonMouvementStockValue();
		// Enrichissement du bon de mouvement Entrée

		if (pBonMouvement.getType().equals(IConstante.TYPE_MVT_ENT))
			vBonMouvementStockValue = enrichirBonEntreeModification(pBonMouvement);

		if (pBonMouvement.getType().equals(IConstante.TYPE_MVT_SORT))
			vBonMouvementStockValue = enrichirBonSortieModification(pBonMouvement);

		if (pBonMouvement.getType().equals(IConstante.TYPE_MVT_RES)) {
			vBonMouvementStockValue = pBonMouvement;
		}
		return bonMouvementPersistance.modifierBonMouvement(vBonMouvementStockValue);
	}

	@Override
	public List<BonMouvementStockValue> listeBonMouvement() {

		return bonMouvementPersistance.listeBonMouvement();
	}

	@Override
	public void supprimerBonMouvement(Long pId) {

		BonMouvementStockValue bonMouvement = this.rechercheBonMouvementParId(pId);
		
		//System.out.println("---Domaine supprimerBonMouvementStock--bonMouvement:"+bonMouvement);


		if (bonMouvement.getType().equals(IConstante.TYPE_MVT_ENT)
				|| bonMouvement.getType().equals(IConstante.TYPE_MVT_SORT)) {
			for (MouvementStockValue mouvement : bonMouvement.getMouvements()) {

				EntiteStockValue entiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvement.getEntiteStock());

//				if (mouvement.getTypeArticle().equals(3L)) {
//
//					if (mouvement.getType().equals(IConstante.TYPE_MVT_ENT)) {
//
//						entiteStock.setConesActuel(entiteStock.getConesActuel() - mouvement.getConesReel());
//						entiteStock.setFinconeActuel(entiteStock.getFinconeActuel() - mouvement.getFinconesReel());
//						entiteStock.setPoidsActuel(entiteStock.getPoidsActuel() - mouvement.getPoidsReel());
//
//					} else if (mouvement.getType().equals(IConstante.TYPE_MVT_SORT)) {
//
//						entiteStock.setConesActuel(entiteStock.getConesActuel() + mouvement.getConesReel());
//						entiteStock.setFinconeActuel(entiteStock.getFinconeActuel() + mouvement.getFinconesReel());
//						entiteStock.setPoidsActuel(entiteStock.getPoidsActuel() + mouvement.getPoidsReel());
//					}
//
//				} else {
//
//					if (mouvement.getType().equals(IConstante.TYPE_MVT_ENT)) {
//						entiteStock.setQteActuelle(entiteStock.getQteActuelle() - mouvement.getQuantiteReelle());
//
//						if (mouvement.getTypeArticle().equals(2L)) {
//							entiteStock
//									.setRouleauxActuel(entiteStock.getRouleauxActuel() - mouvement.getNbRouleauxReel());
//						}
//					} else if (mouvement.getType().equals(IConstante.TYPE_MVT_SORT)) {
//						entiteStock.setQteActuelle(entiteStock.getQteActuelle() + mouvement.getQuantiteReelle());
//
//						if (mouvement.getTypeArticle().equals(2L)) {
//							entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());
//						}
//					}
//				}

				entiteStockPersistance.modifierEntiteStock(entiteStock);
			}

		}

		bonMouvementPersistance.supprimerBonMouvement(pId);

	}

	@Override
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type) {

		return bonMouvementPersistance.getByTypeBonMouvement(type);
	}

	@Override
	public BonMouvementStockValue rechercheBonMouvementParId(Long pBonMouvementId) {

		BonMouvementStockValue bonMouvement = bonMouvementPersistance.rechercheBonMouvementParId(pBonMouvementId);

		if (bonMouvement != null) {
			if (bonMouvement.getMouvements() != null) {
				for (MouvementStockValue mouvement : bonMouvement.getMouvements()) {

					if (mouvement.getEntiteStock() != null) {

						EntiteStockValue entiteStock = entiteStockPersistance
								.rechercheEntiteStockParId(mouvement.getEntiteStock());

						if (entiteStock != null) {
							mouvement.setLot(entiteStock.getReferenceLot());
							mouvement.setDateEntree(entiteStock.getDateEntree());
							mouvement.setQuantiteAct(entiteStock.getQteActuelle());

							if (entiteStock.getQteActuelle() != null && entiteStock.getQteReservee() != null) {
								mouvement.setQuantiteLibre(entiteStock.getQteActuelle() - entiteStock.getQteReservee());
							}

							// mouvement.setReservationOF(
							// calculerReservationOF(entiteStock.getId(),
							// bonMouvement.getOfId()));
							// mouvement.setQteReservee(mouvement.getQuantiteReelle());

							// if (mouvement.getBesoinOF() != null &&
							// mouvement.getQteOF() != null) {
							// mouvement.setBu(mouvement.getBesoinOF() /
							// mouvement.getQteOF());
							// }
						}
					}
				}
			}
		}
		//System.out.println("---Domaine supprimerBonMouvementStock-rechercherById---bonMouvement:"+bonMouvement);

		return bonMouvement;
	}

	public BonMouvementStockValue enrichirBonSortie(BonMouvementStockValue pBonMouvementStockValue) {
		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListeMouvement = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			// MouvementStockValue mouvement;
			EntiteStockValue vEntiteStock = entiteStockPersistance
					.rechercheEntiteStockParId(mouvement.getEntiteStock());

			if (mouvement.getTypeArticle().equals(3L)) {
				if (mouvement.getConesReel() != null && vEntiteStock.getConesActuel() != null)
					if (mouvement.getConesReel() <= vEntiteStock.getConesActuel())
						vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() - mouvement.getConesReel());
				// TODO Else exception
				if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
						&& vEntiteStock.getFinconeActuel() != null && vEntiteStock.getPoidsActuel() != null) {
					if (mouvement.getFinconesReel() <= vEntiteStock.getFinconeActuel()) {
						vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() - mouvement.getFinconesReel());
						vEntiteStock
								.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() - mouvement.getPoidsReel()));
						vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
					}
				}

			} else {
				if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null)
					if (vEntiteStock.getQteActuelle() >= mouvement.getQuantiteReelle())
						vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() - mouvement.getQuantiteReelle());
				if (mouvement.getTypeArticle().equals(2L)) {
					if (vEntiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null)
						if (vEntiteStock.getRouleauxActuel() >= mouvement.getNbRouleauxReel())
							vEntiteStock.setRouleauxActuel(
									vEntiteStock.getRouleauxActuel() - mouvement.getNbRouleauxReel());

				}
			}
			// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
			mouvement.setEntiteStockValue(vEntiteStock);

			vListeMouvement.add(mouvement);
		}

		vBonMouvementStockValue.setMouvements(vListeMouvement);
		return vBonMouvementStockValue;

	}

	public BonMouvementStockValue enrichirBonEntreeModification(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListMouvementResultat = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			if (mouvement.getNouveau() == null || (mouvement.getNouveau() == true)) {

				EntiteStockValue vEntiteStock = entiteStockPersistance
						.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(), mouvement.getIdMagasin());

				// Creation de l'entité stock si elle n'existe pas
				if (vEntiteStock == null) {

					vEntiteStock = new EntiteStockValue();
					vEntiteStock.setArticle(mouvement.getIdArticle());
					vEntiteStock.setMagasin(mouvement.getIdMagasin());
					vEntiteStock.setPmp(new Double(0));
					vEntiteStock.setPrixUnitaire(new Double(0));
					vEntiteStock.setEmplacement(IConstante.VIDE);

					if (mouvement.getTypeArticle().equals(3L)) {

						vEntiteStock.setConeReserve(new Long(0));
						vEntiteStock.setConesActuel(new Long(0));
						vEntiteStock.setFinconeActuel(new Long(0));
						vEntiteStock.setFinconeReserve(new Long(0));
						vEntiteStock.setPoidsActuel(new Double(0));
						vEntiteStock.setPoidsReserve(new Double(0));
						vEntiteStock.setEquivalenceCone(new Double(0));

					} else {

						vEntiteStock.setQteActuelle(new Double(0));
						vEntiteStock.setQteReservee(new Double(0));

						if (mouvement.getTypeArticle().equals(2L)) {

							vEntiteStock.setRouleauxActuel(new Long(0));
							vEntiteStock.setRouleauxReserve(new Long(0));
						}
					}
					// entiteStockPersistance.creerEntiteStock(vEntiteStock);

				}

				// // Mise à jour de l'entité Stock
				// vEntiteStock =
				// entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
				// mouvement.getIdMagasin());

				if (mouvement.getTypeArticle().equals(3L)) {
					if (mouvement.getConesReel() != null)
						vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() + mouvement.getConesReel());
					if (mouvement.getFinconesReel() != null) {
						vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() + mouvement.getFinconesReel());
						vEntiteStock
								.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() + mouvement.getPoidsReel()));
						vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
					}

				} else {

					vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() + mouvement.getQuantiteReelle());
					if (mouvement.getTypeArticle().equals(2L)) {
						vEntiteStock
								.setRouleauxActuel(vEntiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());

					}
				}
				// ArticleValue ar
				if (!vEntiteStock.getEmplacement().equals(IConstante.VIDE))
					vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + IConstante.SEPARATEUR);
				vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + mouvement.getEmplacement());
				vEntiteStock.setPrixUnitaire(mouvement.getPrixUnitaire());
				vEntiteStock.setPmp(calculerPrixMoyenPondere(vEntiteStock, mouvement));
				// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
				mouvement.setEntiteStock(vEntiteStock.getId());
				mouvement.setEntiteStockValue(vEntiteStock);
				// mouvement.setPrixTotal(prixTotal);

			}
			vListMouvementResultat.add(mouvement);
		}
		vBonMouvementStockValue.setMouvements(vListMouvementResultat);

		this.updateEntiteStockAfterDeleteMvtEntree(pBonMouvementStockValue.getListeMouvementsSupprimes());

		return vBonMouvementStockValue;
	}

	private void updateEntiteStockAfterDeleteMvtEntree(List<MouvementStockSupprime> listeMouvementsSupprimes) {

		for (MouvementStockSupprime mouvementStockSupprime : listeMouvementsSupprimes) {

			for (MouvementStockSupprimeElement mouvementStockSupprimeElement : mouvementStockSupprime
					.getListeElementsSupprimes()) {

				System.out.println("mouvementStockSupprimeElement---" + mouvementStockSupprimeElement.toString());

				EntiteStockValue entiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvementStockSupprimeElement.getEntiteStockId());

				if (entiteStock != null) {

					// ConesAct & finConeAct & PoidsActu MAJ pour type 3 (Fil à
					// coudre)
					if (mouvementStockSupprime.getTypeArticle().equals(3L)) {

						entiteStock.setConesActuel(
								entiteStock.getConesActuel() - mouvementStockSupprimeElement.getConesReel());
						entiteStock.setFinconeActuel(
								entiteStock.getFinconeActuel() - mouvementStockSupprimeElement.getFinconesReel());
						entiteStock.setPoidsActuel(
								entiteStock.getPoidsActuel() - mouvementStockSupprimeElement.getPoidsReel());
					}

					else {

						// QteActuelle MAJ pour type 1 & 2 (Fourniture & Tissu)
						entiteStock.setQteActuelle(
								entiteStock.getQteActuelle() - mouvementStockSupprimeElement.getQteReelle());
						// TODO A verifier
						// QteRouleaux MAJ pour type 2 (Tissu)
						if (mouvementStockSupprime.getTypeArticle().equals(2L)) {

							System.out.println("entite stock - rouleauxActuel ----" + entiteStock.getRouleauxActuel());
							System.out.println("entite stock - getNbRouleauxReel ----"
									+ mouvementStockSupprimeElement.getNbRouleauxReel());

							entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel()
									- mouvementStockSupprimeElement.getNbRouleauxReel());
						}

					}

					this.entiteStockPersistance.modifierEntiteStock(entiteStock);
				}

			}
		}
	}

	public BonMouvementStockValue enrichirBonSortieModification(BonMouvementStockValue pBonMouvementStockValue) {
		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListeMouvement = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			// MouvementStockValue mouvement;
			if (mouvement.getNouveau() == null || mouvement.getNouveau() == true) {
				EntiteStockValue vEntiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvement.getEntiteStock());

				if (mouvement.getTypeArticle().equals(3L)) {
					if (mouvement.getConesReel() != null && vEntiteStock.getConesActuel() != null)
						if (mouvement.getConesReel() <= vEntiteStock.getConesActuel())
							vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() - mouvement.getConesReel());
					// TODO Else exception
					if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
							&& vEntiteStock.getFinconeActuel() != null && vEntiteStock.getPoidsActuel() != null) {
						if (mouvement.getFinconesReel() <= vEntiteStock.getFinconeActuel()) {
							vEntiteStock
									.setFinconeActuel(vEntiteStock.getFinconeActuel() - mouvement.getFinconesReel());
							vEntiteStock.setPoidsActuel(
									new Double(vEntiteStock.getPoidsActuel() - mouvement.getPoidsReel()));
							vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
						}
					}

				} else {
					if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null)

						if (vEntiteStock.getQteActuelle() >= mouvement.getQuantiteReelle())
							vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() - mouvement.getQuantiteReelle());

					if (mouvement.getTypeArticle().equals(2L)) {
						if (vEntiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null)
							if (vEntiteStock.getRouleauxActuel() >= mouvement.getNbRouleauxReel())
								vEntiteStock.setRouleauxActuel(
										vEntiteStock.getRouleauxActuel() - mouvement.getNbRouleauxReel());

					}
				}

				// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
				mouvement.setEntiteStockValue(vEntiteStock);

			}
			vListeMouvement.add(mouvement);
		}

		this.updateEntiteStockAfterDeleteMvtSortie(pBonMouvementStockValue.getListeMouvementsSupprimes());

		vBonMouvementStockValue.setMouvements(vListeMouvement);

		return vBonMouvementStockValue;

	}

	private void updateEntiteStockAfterDeleteMvtSortie(List<MouvementStockSupprime> listeMouvementsSupprimes) {

		for (MouvementStockSupprime mouvementStockSupprime : listeMouvementsSupprimes) {

			for (MouvementStockSupprimeElement mouvementStockSupprimeElement : mouvementStockSupprime
					.getListeElementsSupprimes()) {

				EntiteStockValue entiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvementStockSupprimeElement.getEntiteStockId());

				if (entiteStock != null) {

					// ConesAct & finConeAct & PoidsActu MAJ pour type 3 (Fil à
					// coudre)
					if (mouvementStockSupprime.getTypeArticle().equals(3L)) {

						entiteStock.setConesActuel(
								entiteStock.getConesActuel() - mouvementStockSupprimeElement.getConesReel());
						entiteStock.setFinconeActuel(
								entiteStock.getFinconeActuel() - mouvementStockSupprimeElement.getFinconesReel());
						entiteStock.setPoidsActuel(
								entiteStock.getPoidsActuel() - mouvementStockSupprimeElement.getPoidsReel());
					}

					else {

						// QteActuelle MAJ pour type 1 & 2 (Fourniture & Tissu)
						entiteStock.setQteActuelle(
								entiteStock.getQteActuelle() + mouvementStockSupprimeElement.getQteReelle());
						// TODO A verifier
						// QteRouleaux MAJ pour type 2 (Tissu)
						if (mouvementStockSupprime.getTypeArticle().equals(2L)) {
							entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel()
									+ mouvementStockSupprimeElement.getNbRouleauxReel());
						}

					}

					this.entiteStockPersistance.modifierEntiteStock(entiteStock);
				}

			}
		}

	}

	// Added on 09/11/2016, by Zeineb Medimagh
	@Override
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type) {
		return bonMouvementPersistance.getListeNumerosParType(type);
	}

	// @Override
	// public List<MouvementOfVue> getListeMouvementsSortie(Long bonMouvementId)
	// {
	//
	// BonMouvementStockValue bonMouvement =
	// bonMouvementPersistance.rechercheBonMouvementParId(bonMouvementId);
	//
	// List<MouvementOfVue> listMouvements = new ArrayList<MouvementOfVue>();
	//
	// for (MouvementStockValue mouvement : bonMouvement.getMouvements()) {
	//
	// EntiteStockValue entiteStock =
	// entiteStockPersistance.rechercheEntiteStockParId(mouvement.getEntiteStock());
	//
	// ArticleValue article =
	// articleDomaine.rechercheArticleParId(entiteStock.getArticle());
	// // get SousFamille puis getFamille puis getType
	//
	// Double qteLibre = entiteStock.getQteActuelle() -
	// entiteStock.getQteReservee();
	// Double reservationOF = calculerReservationOF(entiteStock.getId(),
	// bonMouvement.getOfId());
	// Double aReserver = mouvement.getBesoinOF() - reservationOF;
	// Boolean manque = false;
	//
	// Double besoinUnitaire = 0D;
	//
	// if (mouvement.getBesoinOF() != null && mouvement.getQteOF() != null) {
	// besoinUnitaire = mouvement.getBesoinOF() / mouvement.getQteOF();
	// }
	//
	// if (mouvement.getBesoinOF() != null && qteLibre != null) {
	// manque = ((mouvement.getBesoinOF() - qteLibre) < ZEROD ? true : false);
	// }
	//
	// MouvementOfVue mouvementOfVue = new MouvementOfVue(article.getRef(),
	// article.getDesignation(),
	// article.getFamilleArticleDesignation(), entiteStock.getQteActuelle(),
	// qteLibre,
	// mouvement.getBesoinOF(), aReserver, entiteStock.getEmplacement(),
	// mouvement.getType(), manque,
	// reservationOF, mouvement.getQteOF().longValue(), 0D,
	// mouvement.getEntiteStock(),
	// Long.parseLong(mouvement.getType()), entiteStock.getReferenceLot(),
	// entiteStock.getDateEntree(),
	// besoinUnitaire);
	//
	// listMouvements.add(mouvementOfVue);
	//
	// }
	//
	// return listMouvements;
	// }

	// private Double calculerReservationOF(Long entiteStockId, Long
	// ordreFabricationId) {
	//
	// Double reservationOF = ZEROD;
	//
	// RechercheMulticritereMouvementValue request = new
	// RechercheMulticritereMouvementValue();
	// request.setHistorique(TYPE_RESERVATION);
	// request.setOfId(ordreFabricationId);
	// request.setEntiteStockId(entiteStockId);
	//
	// ResultatRechecheMouvementValue result =
	// mouvementPersistance.rechercherMouvementMultiCritere(request);
	//
	// if (result != null) {
	//
	// for (MouvementStockValue mouvementStock : result.getMouvementStock()) {
	//
	// if (mouvementStock.getQuantiteReelle() != null) {
	//
	// reservationOF = reservationOF + mouvementStock.getQuantiteReelle();
	// }
	//
	// }
	// }
	//
	// return reservationOF;
	// }

	@Override
	public BonMouvementStockValue rechercheBonMouvementParNum(String pBonMouvementNum) {
		// TODO Auto-generated method stub
		return bonMouvementPersistance.rechercheBonMouvementParNum(pBonMouvementNum);
	}

	// enrichir soustraction Stock
	public BonMouvementStockValue enrichirCreationBonSortieSoustractionStock(
			BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;

		Set<MouvementStockValue> vListMouvementResultat = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {

			EntiteStockValue vEntiteStock = new EntiteStockValue();

			vEntiteStock = entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
					vBonMouvementStockValue.getIdMagasin());
//			System.out.println("----------vEntiteStock BEFORE soustraction--------" + vEntiteStock);

			if (vEntiteStock != null) {

				mouvement.setEntiteStock(vEntiteStock.getId());

				// soustractionStock
				if ((vEntiteStock.getQteActuelle() != null) && (mouvement.getQuantiteReelle() != null)) {
					
					Double qteStock = vEntiteStock.getQteActuelle() - mouvement.getQuantiteReelle();
					vEntiteStock.setQteActuelle(qteStock);
				}

				mouvement.setEntiteStockValue(vEntiteStock);
//				System.out.println("----------vEntiteStock AFTER soustraction--------" + vEntiteStock);
				
			}

//			FicheSuiveuseValue ficheSuiveuseValue = ficheSuiveusePersistance.rechercheFSByRefMise(vBonMouvementStockValue.getNumero());
//			ficheSuiveuseValue.setSoustractionEffectuee(true);
//			System.out.println("----------FicheSuiveuseValue AFTER soustractionEffectuee ** ** **--------" + ficheSuiveuseValue.toString());
//
//			ficheSuiveusePersistance.update(ficheSuiveuseValue);
//			

			vListMouvementResultat.add(mouvement);
		}
		vBonMouvementStockValue.setOrigineFSuiveuse(true);
//		System.out.println("----------vBonMouvementStockValue AFTER soustraction--------" + vBonMouvementStockValue);


		vBonMouvementStockValue.setMouvements(vListMouvementResultat);
		return vBonMouvementStockValue;

	}

	
	private String getNumeroBonMouvementEntre(final Calendar pDateBonMouvementEntre){

		Long vNumGuichetBonMouvementEntre = this.guichetAnnuelDomaine.getNextNumBonMouvementEntre();
		/** Année courante. */
		int vAnneeCourante = pDateBonMouvementEntre.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonMouvementEntre = new StringBuilder("");
		vNumBonMouvementEntre.append(vAnneeCourante);
		if(vNumGuichetBonMouvementEntre<100)
			vNumBonMouvementEntre.append("0");
	       	vNumBonMouvementEntre.append( vNumGuichetBonMouvementEntre);
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) +1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumBonMouvementEntre(new Long(
				vNumGuichetBonMouvementEntre + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetBonMouvementEntreAnnuel(vGuichetValeur);
		return vNumBonMouvementEntre.toString();
	}
	
	private String getNumeroBonMouvementSortie(final Calendar pDateBonMouvementSortie){

		Long vNumGuichetBonMouvementSortie = this.guichetAnnuelDomaine.getNextNumBonMouvementSortie();
		/** Année courante. */
		int vAnneeCourante = pDateBonMouvementSortie.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonMouvementSortie = new StringBuilder("");
		vNumBonMouvementSortie.append(vAnneeCourante);
		if(vNumGuichetBonMouvementSortie<100)
			vNumBonMouvementSortie.append("0");
		    vNumBonMouvementSortie.append( vNumGuichetBonMouvementSortie);
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2016) +1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumBonMouvementSortie(new Long(
				vNumGuichetBonMouvementSortie + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetBonMouvementSortieAnnuel(vGuichetValeur);
		return vNumBonMouvementSortie.toString();
	}
}
