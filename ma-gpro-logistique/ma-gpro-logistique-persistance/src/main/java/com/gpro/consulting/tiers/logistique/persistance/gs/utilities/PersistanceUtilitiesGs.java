package com.gpro.consulting.tiers.logistique.persistance.gs.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.SiteEntite;
import com.gpro.consulting.tiers.logistique.coordination.gc.bonlivraison.value.TaxeLivraisonValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonInventaireValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.DetBonStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EmplacementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockMouvementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.EntiteStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MagasinValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.MouvementStockValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.TaxeLivraisonEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BLAchatEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonInventaireEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonMouvementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.BonStockEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.DetBonInventaireEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.DetBonStockEntity;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EmplacementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MagasinEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MouvementEntite;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.RaisonMouvementEntite;

/**
 * The Class PersistanceUtilities.
 * 
 * @author mohamed
 */

@Component
public class PersistanceUtilitiesGs {

	public static EntiteStockMouvementValue toEntiteStockMouvementValue(EntiteStockEntite pEntiteStockEntite) {
		EntiteStockMouvementValue vEntiteStockMouvementValue = new EntiteStockMouvementValue();
		vEntiteStockMouvementValue.setEmpl(pEntiteStockEntite.getEmplacement());
		vEntiteStockMouvementValue.setEntiteStockId(pEntiteStockEntite.getId());
		vEntiteStockMouvementValue.setFinCone(pEntiteStockEntite.getFinconeActuel());
		vEntiteStockMouvementValue.setPmp(pEntiteStockEntite.getPmp());
		vEntiteStockMouvementValue.setPoid(pEntiteStockEntite.getPoidsActuel());
		vEntiteStockMouvementValue.setPu(pEntiteStockEntite.getPrixUnitaire());
		vEntiteStockMouvementValue.setQteActuel(pEntiteStockEntite.getQteActuelle());

		vEntiteStockMouvementValue.setCone(pEntiteStockEntite.getConesActuel());
		
		vEntiteStockMouvementValue.setNumeroBonEntree(pEntiteStockEntite.getNumeroBonEntree());
		
		vEntiteStockMouvementValue.setQteEntree(pEntiteStockEntite.getQteEntree());
		
		if (pEntiteStockEntite.getArticle() != null) {

			// vEntiteStockMouvementValue.se
			vEntiteStockMouvementValue.setDesignation(pEntiteStockEntite.getArticle().getDesignation());
			vEntiteStockMouvementValue.setReference(pEntiteStockEntite.getArticle().getRef());

			if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite() != null) {
				vEntiteStockMouvementValue
						.setSousFamille(pEntiteStockEntite.getArticle().getSousFamilleArtEntite().getDesignation());// sous
																													// famille
																													// article
			}
			if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite().getFamilleArticle() != null) {
				vEntiteStockMouvementValue.setFamille(pEntiteStockEntite.getArticle().getSousFamilleArtEntite()
						.getFamilleArticle().getDesignation().toString());// famille
																			// article
			}
			if (pEntiteStockEntite.getArticle().getUniteEntite() != null) {
				vEntiteStockMouvementValue.setUnite(pEntiteStockEntite.getArticle().getUniteEntite().toString());// unitearticle
			}
			
		}
		// vEntiteStockMouvementValue.setQteOf(pEntiteStockEntite.getQteActuelle());
		// qte OF
		// vEntiteStockMouvementValue.setQteR(qteR); qte R
		// etat
		return vEntiteStockMouvementValue;
	}
	
	
	
	/*****************Bon Inventaire ************************/
	public static BonInventaireValue toValue(BonInventaireEntity pEntiteInventaireEntite) {
		BonInventaireValue entiteInventaire = new BonInventaireValue();
		entiteInventaire.setId(pEntiteInventaireEntite.getId());

		 
		entiteInventaire.setReference(pEntiteInventaireEntite.getReference());
		entiteInventaire.setDate(pEntiteInventaireEntite.getDate());
		entiteInventaire.setObservations(pEntiteInventaireEntite.getObservations());
		entiteInventaire.setMetrageTotal(pEntiteInventaireEntite.getMetrageTotal());
		entiteInventaire.setVersion(pEntiteInventaireEntite.getVersion());
		entiteInventaire.setIdDepot(pEntiteInventaireEntite.getIdDepot());
		entiteInventaire.setType(pEntiteInventaireEntite.getType());
		entiteInventaire.setBoutiqueId(pEntiteInventaireEntite.getBoutiqueId());
		 
		if(pEntiteInventaireEntite.getListDetBonInventaire()!= null){
	    	List<DetBonInventaireValue> list = new ArrayList <DetBonInventaireValue>();
		     for (DetBonInventaireEntity detBonInventaireEntity : pEntiteInventaireEntite.getListDetBonInventaire()) {
		    	 DetBonInventaireValue detBonInventaireValue = toValue(detBonInventaireEntity);
		    	  
		    	 list.add(detBonInventaireValue);
		    }
		     entiteInventaire.setListDetBonInventaire(list);
		}
		
		
		return entiteInventaire;
	}
	
	public static BonInventaireEntity toEntity(BonInventaireValue pInventaireValue) {
		BonInventaireEntity pInventaireEntity = new BonInventaireEntity();
		pInventaireEntity.setId(pInventaireValue.getId());

		 
		pInventaireEntity.setReference(pInventaireValue.getReference());
		pInventaireEntity.setDate(pInventaireValue.getDate());
		pInventaireEntity.setObservations(pInventaireValue.getObservations());
		pInventaireEntity.setMetrageTotal(pInventaireValue.getMetrageTotal());
		pInventaireEntity.setVersion(pInventaireValue.getVersion());
		pInventaireEntity.setIdDepot(pInventaireValue.getIdDepot());
		pInventaireEntity.setType(pInventaireValue.getType());
		pInventaireEntity.setBoutiqueId(pInventaireValue.getBoutiqueId());
		
		if(pInventaireValue.getListDetBonInventaire()!= null){
	    	Set<DetBonInventaireEntity> list = new HashSet<DetBonInventaireEntity>();
		     for (DetBonInventaireValue detBonInventaireValue : pInventaireValue.getListDetBonInventaire()) {
		    	 DetBonInventaireEntity detBonInventaireEntity = toEntity(detBonInventaireValue);
		    	 detBonInventaireEntity.setBonInventaire(pInventaireEntity);
		    	 list.add(detBonInventaireEntity);
		    }
		     pInventaireEntity.setListDetBonInventaire(list);
		}

		
		return pInventaireEntity;
	}
	
	
	public static DetBonInventaireValue toValue(DetBonInventaireEntity entity) {
		
		DetBonInventaireValue dto = new DetBonInventaireValue();
		
		dto.setId(entity.getId());
		dto.setProduitId(entity.getProduitId());
		dto.setQuantite(entity.getQuantite());
		dto.setSerialisable(entity.isSerialisable());
		dto.setNumeroSeries(entity.getNumeroSeries());
		dto.setDescription(entity.getDescription());
		
		
		
		if(entity.getBonInventaire() != null){
			
			BonInventaireValue bonInventaireValue = new BonInventaireValue();
			bonInventaireValue.setId(entity.getBonInventaire().getId());
			 
			dto.setBonInventaire(bonInventaireValue);
			
			
		}
		
		
		
		
		
		return dto;
	}
	
	public static DetBonInventaireEntity toEntity(DetBonInventaireValue value) {
		
		DetBonInventaireEntity entity = new DetBonInventaireEntity();
		
		entity.setId(value.getId());
		entity.setProduitId(value.getProduitId());
		entity.setQuantite(value.getQuantite());
		entity.setSerialisable(value.isSerialisable());
		entity.setNumeroSeries(value.getNumeroSeries());
		entity.setDescription(value.getDescription());
		
		
		
		if(value.getBonInventaire() != null){
			
			BonInventaireEntity bonInventaireEntity = new BonInventaireEntity();
			bonInventaireEntity.setId(value.getBonInventaire().getId());
			 
			entity.setBonInventaire(bonInventaireEntity);
		}
		
		
		
		
		
		return entity;
	}

	
	
	
	
	
	
	
	public static EntiteStockValue toValue(EntiteStockEntite pEntiteStockEntite) {
		EntiteStockValue entiteStock = new EntiteStockValue();
		entiteStock.setId(pEntiteStockEntite.getId());

		if (pEntiteStockEntite.getArticle() != null) {
			entiteStock.setArticle(pEntiteStockEntite.getArticle().getId());
			entiteStock.setReferenceArticle(pEntiteStockEntite.getArticle().getRef());
			entiteStock.setLibelleArticle(pEntiteStockEntite.getArticle().getDesignation());

			if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite() != null) {
				if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite().getFamilleArticle() != null) {
					entiteStock.setFamilleArticle(pEntiteStockEntite.getArticle().getSousFamilleArtEntite()
							.getFamilleArticle().getDesignation());
				}
			}
			///////////////////////////
			if (pEntiteStockEntite.getArticle().getGrammage()!= null) {
		
					entiteStock.setGrammageArticle(pEntiteStockEntite.getArticle().getGrammage());
				
			}
			if (pEntiteStockEntite.getArticle().getDimension()!= null) {
				
				entiteStock.setDimensionArticle(pEntiteStockEntite.getArticle().getDimension());
			
		}
			
			
	    if (pEntiteStockEntite.getArticle().getProducteur()!= null) {
				
				entiteStock.setProducteurArticle(pEntiteStockEntite.getArticle().getProducteur());
			
		}
	
	
	

		}
		entiteStock.setConeReserve(pEntiteStockEntite.getConeReserve());
		entiteStock.setConesActuel(pEntiteStockEntite.getConesActuel());
		entiteStock.setDateEntree(pEntiteStockEntite.getDateEntree());
		entiteStock.setDateLot(pEntiteStockEntite.getDateLot());
		entiteStock.setEmplacement(pEntiteStockEntite.getEmplacement());
		entiteStock.setEquivalenceCone(pEntiteStockEntite.getEquivalenceCone());
		entiteStock.setFinconeActuel(pEntiteStockEntite.getFinconeActuel());
		entiteStock.setFinconeReserve(pEntiteStockEntite.getFinconeReserve());
		if (pEntiteStockEntite.getMagasin() != null) {
			entiteStock.setMagasin(pEntiteStockEntite.getMagasin().getId());
			entiteStock.setDesignationMagasin(pEntiteStockEntite.getMagasin().getDesignation());
		}

		entiteStock.setPmp(pEntiteStockEntite.getPmp());
		entiteStock.setPoidsActuel(pEntiteStockEntite.getPoidsActuel());
		entiteStock.setPoidsReserve(pEntiteStockEntite.getPoidsReserve());
		entiteStock.setPrixUnitaire(pEntiteStockEntite.getPrixUnitaire());
		entiteStock.setQteActuelle(pEntiteStockEntite.getQteActuelle());
		entiteStock.setQteReservee(pEntiteStockEntite.getQteResrvee());

		entiteStock.setReferenceContenaire(pEntiteStockEntite.getReferenceContenaire());
		entiteStock.setReferenceLot(pEntiteStockEntite.getReferenceLot());
		entiteStock.setRouleauxActuel(pEntiteStockEntite.getRouleauxActuel());
		entiteStock.setRouleauxReserve(pEntiteStockEntite.getRouleauxReserve());
		
		entiteStock.setNumeroBonEntree(pEntiteStockEntite.getNumeroBonEntree());
		
		entiteStock.setQteEntree(pEntiteStockEntite.getQteEntree());
		
		
		return entiteStock;
	}

	public static EntiteStockEntite toEntity(EntiteStockValue pEntiteStockValue, MagasinEntite pMagasinEntite,
			ArticleEntite pArticleEntite) {
		EntiteStockEntite entiteStock = new EntiteStockEntite();
		if (pEntiteStockValue.getId() != null) {
			entiteStock.setId(pEntiteStockValue.getId());
		}
		entiteStock.setArticle(pArticleEntite);
		entiteStock.setConeReserve(pEntiteStockValue.getConeReserve());
		entiteStock.setConesActuel(pEntiteStockValue.getConesActuel());
		entiteStock.setDateEntree(pEntiteStockValue.getDateEntree());
		entiteStock.setDateLot(pEntiteStockValue.getDateLot());
		entiteStock.setEmplacement(pEntiteStockValue.getEmplacement());
		entiteStock.setEquivalenceCone(pEntiteStockValue.getEquivalenceCone());
		entiteStock.setFinconeActuel(pEntiteStockValue.getFinconeActuel());
		entiteStock.setFinconeReserve(pEntiteStockValue.getFinconeReserve());
		entiteStock.setMagasin(pMagasinEntite);
		entiteStock.setLibelleArticle(pEntiteStockValue.getLibelleArticle());
		entiteStock.setPmp(pEntiteStockValue.getPmp());
		entiteStock.setPoidsActuel(pEntiteStockValue.getPoidsActuel());
		entiteStock.setPoidsReserve(pEntiteStockValue.getPoidsReserve());
		entiteStock.setPrixUnitaire(pEntiteStockValue.getPrixUnitaire());
		entiteStock.setQteActuelle(pEntiteStockValue.getQteActuelle());
		entiteStock.setQteResrvee(pEntiteStockValue.getQteReservee());
		entiteStock.setReferenceArticle(pEntiteStockValue.getReferenceArticle());
		entiteStock.setReferenceContenaire(pEntiteStockValue.getReferenceContenaire());
		entiteStock.setReferenceLot(pEntiteStockValue.getReferenceLot());
		entiteStock.setRouleauxActuel(pEntiteStockValue.getRouleauxActuel());
		entiteStock.setRouleauxReserve(pEntiteStockValue.getRouleauxReserve());
		
		entiteStock.setNumeroBonEntree(pEntiteStockValue.getNumeroBonEntree());
		
		entiteStock.setQteEntree(pEntiteStockValue.getQteEntree());
		
		
		return entiteStock;
	}

	/**
	 * To value.
	 *
	 * @param pMouvementEntite
	 *            the mouvement entite
	 * @return
	 * @return
	 * @return the mouvement stock value
	 */
	public static MouvementStockValue toValue(MouvementEntite pMouvementEntite) {
		MouvementStockValue mouvementStockValue = new MouvementStockValue();
		mouvementStockValue.setId(pMouvementEntite.getId());
		mouvementStockValue.setType(pMouvementEntite.getType());
		mouvementStockValue.setCones(pMouvementEntite.getCones());
		mouvementStockValue.setConesReel(pMouvementEntite.getConesReel());
		mouvementStockValue.setDetailsMouvement(pMouvementEntite.getDetailsMouvement());
		mouvementStockValue.setEmplacement(pMouvementEntite.getEmplacement());
		// added on 13/04/2016, by Ameni Berrich
		mouvementStockValue.setLot(pMouvementEntite.getLot());

		if (pMouvementEntite.getEntiteStock() != null) {
			mouvementStockValue.setEntiteStock(pMouvementEntite.getEntiteStock().getId());
			mouvementStockValue.setEntiteStockValue(toValue(pMouvementEntite.getEntiteStock()));

			if (pMouvementEntite.getEntiteStock().getArticle() != null) {
				mouvementStockValue.setIdArticle(pMouvementEntite.getEntiteStock().getArticle().getId());
				mouvementStockValue.setReferenceArticle(pMouvementEntite.getEntiteStock().getArticle().getRef());
				mouvementStockValue
						.setDesignationArticle(pMouvementEntite.getEntiteStock().getArticle().getDesignation());

				if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite() != null) {

					mouvementStockValue.setSousFamilleArticle(
							pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite().getDesignation());

					if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite()
							.getFamilleArticle() != null) {

						mouvementStockValue.setFamilleArticle(pMouvementEntite.getEntiteStock().getArticle()
								.getSousFamilleArtEntite().getFamilleArticle().getDesignation());
						if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite().getFamilleArticle()
								.getTypeArticle() != null) {

							mouvementStockValue.setTypeArticle(pMouvementEntite.getEntiteStock().getArticle()
									.getSousFamilleArtEntite().getFamilleArticle().getTypeArticle().getId());
						}
					}
				}
			}

			if (pMouvementEntite.getEntiteStock().getMagasin() != null) {
				mouvementStockValue.setIdMagasin(pMouvementEntite.getEntiteStock().getMagasin().getId());
			}
		}

		mouvementStockValue.setFincones(pMouvementEntite.getFinCones());
		mouvementStockValue.setNbRouleaux(pMouvementEntite.getNbRouleaux());
		mouvementStockValue.setNbRouleauxReel(pMouvementEntite.getNbRouleauxReel());
		mouvementStockValue.setPoids(pMouvementEntite.getPoids());
		mouvementStockValue.setPrixUnitaire(pMouvementEntite.getPrixUnitaire());
		mouvementStockValue.setQuantite(pMouvementEntite.getQuantite());
		mouvementStockValue.setQuantiteReelle(pMouvementEntite.getQuantiteReelle());
		mouvementStockValue.setFinconesReel(pMouvementEntite.getFinConesReel());
		mouvementStockValue.setDescription(pMouvementEntite.getDescription());
		mouvementStockValue.setPoidsReel(pMouvementEntite.getPoidsReel());
		mouvementStockValue.setNouveau(false);
		mouvementStockValue.setQteOF(pMouvementEntite.getQteOF());
		mouvementStockValue.setBesoinOF(pMouvementEntite.getBesoinOF());
		return mouvementStockValue;
	}

	public static MouvementStockValue toValueAffichage(MouvementEntite pMouvementEntite) {
		MouvementStockValue mouvementStockValue = new MouvementStockValue();
		mouvementStockValue.setId(pMouvementEntite.getId());
		mouvementStockValue.setType(pMouvementEntite.getType());
		mouvementStockValue.setCones(pMouvementEntite.getCones());
		mouvementStockValue.setConesReel(pMouvementEntite.getConesReel());
		mouvementStockValue.setDetailsMouvement(pMouvementEntite.getDetailsMouvement());
		mouvementStockValue.setEmplacement(pMouvementEntite.getEmplacement());
		mouvementStockValue.setEntiteStock(pMouvementEntite.getEntiteStock().getId());
		mouvementStockValue.setFincones(pMouvementEntite.getFinCones());
		mouvementStockValue.setNbRouleaux(pMouvementEntite.getNbRouleaux());
		mouvementStockValue.setNbRouleauxReel(pMouvementEntite.getNbRouleauxReel());
		mouvementStockValue.setPoids(pMouvementEntite.getPoids());
		mouvementStockValue.setPrixUnitaire(pMouvementEntite.getPrixUnitaire());
		mouvementStockValue.setQuantite(pMouvementEntite.getQuantite());
		mouvementStockValue.setQuantiteReelle(pMouvementEntite.getQuantiteReelle());
		mouvementStockValue.setFinconesReel(pMouvementEntite.getFinConesReel());
		mouvementStockValue.setDescription(pMouvementEntite.getDescription());
		mouvementStockValue.setPoidsReel(pMouvementEntite.getPoidsReel());

		if (pMouvementEntite.getEntiteStock() != null) {
			mouvementStockValue.setEntiteStock(pMouvementEntite.getEntiteStock().getId());

			if (pMouvementEntite.getEntiteStock().getMagasin() != null) {
				mouvementStockValue.setIdMagasin(pMouvementEntite.getEntiteStock().getMagasin().getId());
				mouvementStockValue
						.setDesignationMagasin(pMouvementEntite.getEntiteStock().getMagasin().getDesignation());
			}

			if (pMouvementEntite.getEntiteStock().getArticle() != null) {
				mouvementStockValue.setReferenceArticle(pMouvementEntite.getEntiteStock().getArticle().getRef());
				mouvementStockValue
						.setDesignationArticle(pMouvementEntite.getEntiteStock().getArticle().getDesignation());

				if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite() != null) {

					mouvementStockValue.setSousFamilleArticle(
							pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite().getDesignation());

					if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite()
							.getFamilleArticle() != null) {

						mouvementStockValue.setFamilleArticle(pMouvementEntite.getEntiteStock().getArticle()
								.getSousFamilleArtEntite().getFamilleArticle().getDesignation());
					}
				}
			}
		}

		if (pMouvementEntite.getBonMouvement() != null) {
			BonMouvementStockValue vBm = toValueAffichage(pMouvementEntite.getBonMouvement());
			mouvementStockValue.setBonMouvement(vBm);
		}
		return mouvementStockValue;
	}

	/**
	 * To entity.
	 *
	 * @param pMouvementStockValue
	 *            the mouvement stock value
	 * @return the mouvement entite
	 */
	public static MouvementEntite toEntity(MouvementStockValue pMouvementStockValue,
			EntiteStockEntite pEntiteStockEntite) {
		MouvementEntite mouvementEntite = new MouvementEntite();
		if (pMouvementStockValue.getId() != null) {
			mouvementEntite.setId(pMouvementStockValue.getId());
		}
		mouvementEntite.setType(pMouvementStockValue.getType());
		mouvementEntite.setCones(pMouvementStockValue.getCones());
		mouvementEntite.setConesReel(pMouvementStockValue.getConesReel());
		mouvementEntite.setDetailsMouvement(pMouvementStockValue.getDetailsMouvement());
		mouvementEntite.setEmplacement(pMouvementStockValue.getEmplacement());
		mouvementEntite.setEntiteStock(pEntiteStockEntite);
		mouvementEntite.setFinCones(pMouvementStockValue.getFincones());
		mouvementEntite.setNbRouleaux(pMouvementStockValue.getNbRouleaux());
		mouvementEntite.setNbRouleauxReel(pMouvementStockValue.getNbRouleauxReel());
		mouvementEntite.setPoids(pMouvementStockValue.getPoids());
		mouvementEntite.setPrixUnitaire(pMouvementStockValue.getPrixUnitaire());
		mouvementEntite.setQuantite(pMouvementStockValue.getQuantite());
		mouvementEntite.setQuantiteReelle(pMouvementStockValue.getQuantiteReelle());
		mouvementEntite.setDescription(pMouvementStockValue.getDescription());
		mouvementEntite.setPoidsReel(pMouvementStockValue.getPoidsReel());
		mouvementEntite.setFinConesReel(pMouvementStockValue.getFinconesReel());
		mouvementEntite.setBesoinOF(pMouvementStockValue.getBesoinOF());
		mouvementEntite.setQteOF(pMouvementStockValue.getQteOF());
		return mouvementEntite;
	}

	/**
	 * To value.
	 *
	 * @param pBonMouvementEntite
	 *            the bon mouvement entite
	 * @return the bon mouvement stock value
	 */
	public static BonMouvementStockValue toValue(BonMouvementEntite pBonMouvementEntite) {
		BonMouvementStockValue bonMouvementStockValue = new BonMouvementStockValue();
		
		//System.out.println("---Persistance supprimerBonMouvementStock--bonMouvement:"+bonMouvementStockValue);

		bonMouvementStockValue.setId(pBonMouvementEntite.getId());
		bonMouvementStockValue.setDate(pBonMouvementEntite.getDate());
		bonMouvementStockValue.setDescription(pBonMouvementEntite.getDescription());

		if (pBonMouvementEntite.getBlachat() != null)
			bonMouvementStockValue.setBlachatId(pBonMouvementEntite.getBlachat().getId());

		if (pBonMouvementEntite.getMouvements() != null) {
			Set<MouvementStockValue> vListMouvementValue = new HashSet<MouvementStockValue>();

			for (MouvementEntite vMouvementEntite : pBonMouvementEntite.getMouvements()) {
				MouvementStockValue vMs = toValue(vMouvementEntite);
				vListMouvementValue.add(vMs);
			}

			bonMouvementStockValue.setMouvements(vListMouvementValue);
		}
		bonMouvementStockValue.setNumero(pBonMouvementEntite.getNumero());
		bonMouvementStockValue.setRaisonMouvementId(pBonMouvementEntite.getRaisonMouvementId());
		bonMouvementStockValue.setResponsable(pBonMouvementEntite.getResponsable());
		bonMouvementStockValue.setType(pBonMouvementEntite.getType());
		bonMouvementStockValue.setValeur(pBonMouvementEntite.getValeur());
		bonMouvementStockValue.setEtat(pBonMouvementEntite.getEtat());
		bonMouvementStockValue.setPartieintId(pBonMouvementEntite.getPartieInteresseeId());
		bonMouvementStockValue.setOfId(pBonMouvementEntite.getOfId());
		bonMouvementStockValue.setNumBRSortie(pBonMouvementEntite.getNumBRSortie());
		bonMouvementStockValue.setOrigineFSuiveuse(pBonMouvementEntite.isOrigineFSuiveuse());
		bonMouvementStockValue.setReferenceBonReception(pBonMouvementEntite.getReferenceBonReception());

		
		return bonMouvementStockValue;
	}

	/**
	 * To value.
	 *
	 * @param pBonMouvementEntite
	 *            the bon mouvement entite
	 * @return the bon mouvement stock value
	 */
	public static BonMouvementStockValue toValueAffichage(BonMouvementEntite pBonMouvementEntite) {
		BonMouvementStockValue bonMouvementStockValue = new BonMouvementStockValue();
		bonMouvementStockValue.setId(pBonMouvementEntite.getId());
		bonMouvementStockValue.setDate(pBonMouvementEntite.getDate());
		bonMouvementStockValue.setDescription(pBonMouvementEntite.getDescription());
		if (pBonMouvementEntite.getBlachat() != null) {
			bonMouvementStockValue.setBlachatId(pBonMouvementEntite.getBlachat().getId());

		}

		bonMouvementStockValue.setNumero(pBonMouvementEntite.getNumero());
		bonMouvementStockValue.setRaisonMouvementId(pBonMouvementEntite.getRaisonMouvementId());
		bonMouvementStockValue.setResponsable(pBonMouvementEntite.getResponsable());
		bonMouvementStockValue.setType(pBonMouvementEntite.getType());
		bonMouvementStockValue.setValeur(pBonMouvementEntite.getValeur());
		bonMouvementStockValue.setEtat(pBonMouvementEntite.getEtat());
		bonMouvementStockValue.setPartieintId(pBonMouvementEntite.getPartieInteresseeId());
		bonMouvementStockValue.setOfId(pBonMouvementEntite.getOfId());
		bonMouvementStockValue.setNumBRSortie(pBonMouvementEntite.getNumBRSortie());
		bonMouvementStockValue.setOrigineFSuiveuse(pBonMouvementEntite.isOrigineFSuiveuse());

		return bonMouvementStockValue;
	}

	/**
	 * To entity.
	 *
	 * @param pBonMouvementStockValue
	 *            the bon mouvement stock value
	 * @return the bon mouvement entite
	 */
	public static BonMouvementEntite toEntity(BonMouvementStockValue pBonMouvementStockValue,
			BLAchatEntite pBlAchatEntite, Set<EntiteStockEntite> pListEntite) {
		BonMouvementEntite bonMouvementEntite = new BonMouvementEntite();
		if (pBonMouvementStockValue.getId() != null) {
			bonMouvementEntite.setId(pBonMouvementStockValue.getId());
		}
		bonMouvementEntite.setDate(pBonMouvementStockValue.getDate());
		bonMouvementEntite.setDescription(pBonMouvementStockValue.getDescription());
		if (pBonMouvementStockValue.getBlachatId() != null)
			bonMouvementEntite.setBlachat(pBlAchatEntite);

		if (pBonMouvementStockValue.getMouvements() != null) {
			Set<MouvementEntite> vListMouvementEntite = new HashSet<MouvementEntite>();
			for (MouvementStockValue vMouvementValue : pBonMouvementStockValue.getMouvements()) {
				EntiteStockEntite vEntiteStockMvt = rechercheEntiteStockFromList(pListEntite,
						vMouvementValue.getEntiteStock());
				MouvementEntite vMs = toEntity(vMouvementValue, vEntiteStockMvt);
				vMs.setBonMouvement(bonMouvementEntite);
				vListMouvementEntite.add(vMs);
			}
			bonMouvementEntite.setMouvements(vListMouvementEntite);
		}
		bonMouvementEntite.setNumero(pBonMouvementStockValue.getNumero());
		bonMouvementEntite.setRaisonMouvementId(pBonMouvementStockValue.getRaisonMouvementId());
		bonMouvementEntite.setResponsable(pBonMouvementStockValue.getResponsable());
		bonMouvementEntite.setType(pBonMouvementStockValue.getType());
		bonMouvementEntite.setValeur(pBonMouvementStockValue.getValeur());
		bonMouvementEntite.setEtat(pBonMouvementStockValue.getEtat());
		bonMouvementEntite.setPartieInteresseeId(pBonMouvementStockValue.getPartieintId());
		bonMouvementEntite.setOfId(pBonMouvementStockValue.getOfId());
		bonMouvementEntite.setNumBRSortie(pBonMouvementStockValue.getNumBRSortie());
		bonMouvementEntite.setOrigineFSuiveuse(pBonMouvementStockValue.isOrigineFSuiveuse());
		return bonMouvementEntite;
	}

	/**
	 * To value.
	 *
	 * @param pMagasinEntite
	 *            the magasin entite
	 * @return the magasin value
	 */
	public static MagasinValue toValue(MagasinEntite pMagasinEntite) {
		MagasinValue magasinValue = new MagasinValue();
		magasinValue.setId(pMagasinEntite.getId());
		magasinValue.setDesignation(pMagasinEntite.getDesignation());
	    magasinValue.setDepot(pMagasinEntite.getDepot());
	    magasinValue.setPointVente(pMagasinEntite.getPointVente());
	    magasinValue.setBoutiqueId(pMagasinEntite.getBoutiqueId());
	    
	    magasinValue.setSocieteId(pMagasinEntite.getSocieteId());
	    
		return magasinValue;
	}

	/**
	 * To entity.
	 *
	 * @param pMagasinValue
	 *            the magasin value
	 * @return the magasin entite
	 */
	public static MagasinEntite toEntity(MagasinValue pMagasinValue, SiteEntite pSiteEntite) {
		MagasinEntite magasinEntite = new MagasinEntite();
		if (pMagasinValue.getId() != null) {
			magasinEntite.setId(pMagasinValue.getId());
		}
		magasinEntite.setDesignation(pMagasinValue.getDesignation());
		magasinEntite.setDepot(pMagasinValue.getDepot());
		magasinEntite.setPointVente(pMagasinValue.getPointVente());
		
		magasinEntite.setBoutiqueId(pMagasinValue.getBoutiqueId());
		magasinEntite.setSocieteId(pMagasinValue.getSocieteId());
		
		return magasinEntite;
	}

	/**
	 * To value.
	 *
	 * @param pEmplacementEntite
	 *            the emplacement entite
	 * @return the emplacement value
	 */
	public static EmplacementValue toValue(EmplacementEntite pEmplacementEntite) {
		EmplacementValue emplacementValue = new EmplacementValue();
		emplacementValue.setId(pEmplacementEntite.getId());
		emplacementValue.setDesignation(pEmplacementEntite.getDesignation());
		emplacementValue.setMagasin(pEmplacementEntite.getMagasin());
		return emplacementValue;
	}

	/**
	 * To entity.
	 *
	 * @param pEmplacementValue
	 *            the emplacement value
	 * @return the emplacement entite
	 */
	public static EmplacementEntite toEntity(EmplacementValue pEmplacementValue) {
		EmplacementEntite emplacementEntite = new EmplacementEntite();
		if (pEmplacementValue.getId() != null) {
			emplacementEntite.setId(pEmplacementValue.getId());
		}
		emplacementEntite.setDesignation(pEmplacementValue.getDesignation());
		emplacementEntite.setMagasin(pEmplacementValue.getMagasin());
		return emplacementEntite;
	}

	/**
	 * To value.
	 *
	 * @param pRaisonMouvementEntite
	 *            the raison mouvement entite
	 * @return the raison mouvement stock value
	 */
	public static RaisonMouvementStockValue toValue(RaisonMouvementEntite pRaisonMouvementEntite) {
		RaisonMouvementStockValue raisonMouvementStockValue = new RaisonMouvementStockValue();
		raisonMouvementStockValue.setId(pRaisonMouvementEntite.getId());
		raisonMouvementStockValue.setDesignation(pRaisonMouvementEntite.getDesignation());
		return raisonMouvementStockValue;
	}

	/**
	 * To entity.
	 *
	 * @param pRaisonMouvementStockValue
	 *            the raison mouvement stock value
	 * @return the raison mouvement entite
	 */
	public static RaisonMouvementEntite toEntity(RaisonMouvementStockValue pRaisonMouvementStockValue) {
		RaisonMouvementEntite raisonMouvementEntite = new RaisonMouvementEntite();
		if (pRaisonMouvementStockValue.getId() != null) {
			raisonMouvementEntite.setId(pRaisonMouvementStockValue.getId());
		}
		raisonMouvementEntite.setDesignation(pRaisonMouvementStockValue.getDesignation());
		return raisonMouvementEntite;
	}

	/**
	 * Recherche EntiteStockEntite fromm List.
	 *
	 * @param pRaisonMouvementStockValue
	 *            the raison mouvement stock value
	 * @return the raison mouvement entite
	 */
	public static EntiteStockEntite rechercheEntiteStockFromList(Set<EntiteStockEntite> pList, Long pId) {
		EntiteStockEntite vEntite = new EntiteStockEntite();
		for (EntiteStockEntite entiteStock : pList) {
			if (entiteStock.getId().equals(pId))
				vEntite = entiteStock;
			break;
		}

		return vEntite;

	}

	/**
	 * To toBonMouvementEntity.
	 *
	 * @param pBonMouvementStockValue
	 *            the bon mouvement stock value
	 * @return the bon mouvement entite
	 */
	public static BonMouvementEntite toBonMouvementEntity(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementEntite bonMouvementEntite = new BonMouvementEntite();

		if (pBonMouvementStockValue.getId() != null) {
			bonMouvementEntite.setId(pBonMouvementStockValue.getId());
		}

		bonMouvementEntite.setDate(pBonMouvementStockValue.getDate());
		bonMouvementEntite.setDescription(pBonMouvementStockValue.getDescription());

		if (pBonMouvementStockValue.getMouvements() != null) {
			Set<MouvementEntite> vListMouvementEntite = new HashSet<MouvementEntite>();

			for (MouvementStockValue vMouvementValue : pBonMouvementStockValue.getMouvements()) {

				EntiteStockEntite vEntiteStockMvt = toEntityStock(vMouvementValue.getEntiteStockValue());

				MouvementEntite vMs = toMouvementEntity(vMouvementValue, vEntiteStockMvt);

				vMs.setBonMouvement(bonMouvementEntite);
				vListMouvementEntite.add(vMs);
			}
			bonMouvementEntite.setMouvements(vListMouvementEntite);
		}
		bonMouvementEntite.setNumero(pBonMouvementStockValue.getNumero());
		bonMouvementEntite.setRaisonMouvementId(pBonMouvementStockValue.getRaisonMouvementId());
		bonMouvementEntite.setResponsable(pBonMouvementStockValue.getResponsable());
		bonMouvementEntite.setType(pBonMouvementStockValue.getType());
		bonMouvementEntite.setValeur(pBonMouvementStockValue.getValeur());
		bonMouvementEntite.setEtat(pBonMouvementStockValue.getEtat());
		bonMouvementEntite.setPartieInteresseeId(pBonMouvementStockValue.getPartieintId());
		bonMouvementEntite.setOfId(pBonMouvementStockValue.getOfId());
		bonMouvementEntite.setNumBRSortie(pBonMouvementStockValue.getNumBRSortie());
		bonMouvementEntite.setOrigineFSuiveuse(pBonMouvementStockValue.isOrigineFSuiveuse());

		return bonMouvementEntite;
	}

	/**
	 * To entity.Mouveement
	 *
	 * @param pMouvementStockValue
	 *            the mouvement stock value
	 * @return the mouvement entite
	 */
	public static MouvementEntite toMouvementEntity(MouvementStockValue pMouvementStockValue,
			EntiteStockEntite pEntiteStockEntite) {
		MouvementEntite mouvementEntite = new MouvementEntite();
		if (pMouvementStockValue.getId() != null) {
			mouvementEntite.setId(pMouvementStockValue.getId());
		}
		mouvementEntite.setType(pMouvementStockValue.getType());
		mouvementEntite.setCones(pMouvementStockValue.getCones());
		mouvementEntite.setConesReel(pMouvementStockValue.getConesReel());
		mouvementEntite.setDetailsMouvement(pMouvementStockValue.getDetailsMouvement());
		mouvementEntite.setEmplacement(pMouvementStockValue.getEmplacement());
		mouvementEntite.setEntiteStock(pEntiteStockEntite);
		mouvementEntite.setFinCones(pMouvementStockValue.getFincones());
		mouvementEntite.setNbRouleaux(pMouvementStockValue.getNbRouleaux());
		mouvementEntite.setNbRouleauxReel(pMouvementStockValue.getNbRouleauxReel());
		mouvementEntite.setPoids(pMouvementStockValue.getPoids());
		mouvementEntite.setPrixUnitaire(pMouvementStockValue.getPrixUnitaire());
		mouvementEntite.setQuantite(pMouvementStockValue.getQuantite());
		mouvementEntite.setQuantiteReelle(pMouvementStockValue.getQuantiteReelle());
		mouvementEntite.setDescription(pMouvementStockValue.getDescription());
		mouvementEntite.setPoidsReel(pMouvementStockValue.getPoidsReel());
		mouvementEntite.setFinConesReel(pMouvementStockValue.getFinconesReel());
		return mouvementEntite;
	}

	/**
	 * to Entity EntityStock
	 * 
	 * @param pEntiteStockValue
	 * @param pMagasinEntite
	 * @param pArticleEntite
	 * @return
	 */
	public static EntiteStockEntite toEntityStock(EntiteStockValue pEntiteStockValue) {
		EntiteStockEntite entiteStock = new EntiteStockEntite();
		if (pEntiteStockValue.getId() != null) {
			entiteStock.setId(pEntiteStockValue.getId());
		}

		entiteStock.setConeReserve(pEntiteStockValue.getConeReserve());
		entiteStock.setConesActuel(pEntiteStockValue.getConesActuel());
		entiteStock.setDateEntree(pEntiteStockValue.getDateEntree());
		entiteStock.setDateLot(pEntiteStockValue.getDateLot());
		entiteStock.setEmplacement(pEntiteStockValue.getEmplacement());
		entiteStock.setEquivalenceCone(pEntiteStockValue.getEquivalenceCone());
		entiteStock.setFinconeActuel(pEntiteStockValue.getFinconeActuel());
		entiteStock.setFinconeReserve(pEntiteStockValue.getFinconeReserve());
		entiteStock.setLibelleArticle(pEntiteStockValue.getLibelleArticle());
		entiteStock.setPmp(pEntiteStockValue.getPmp());
		entiteStock.setPoidsActuel(pEntiteStockValue.getPoidsActuel());
		entiteStock.setPoidsReserve(pEntiteStockValue.getPoidsReserve());
		entiteStock.setPrixUnitaire(pEntiteStockValue.getPrixUnitaire());
		entiteStock.setQteActuelle(pEntiteStockValue.getQteActuelle());
		entiteStock.setQteResrvee(pEntiteStockValue.getQteReservee());
		entiteStock.setReferenceArticle(pEntiteStockValue.getReferenceArticle());
		entiteStock.setReferenceContenaire(pEntiteStockValue.getReferenceContenaire());
		entiteStock.setReferenceLot(pEntiteStockValue.getReferenceLot());
		entiteStock.setRouleauxActuel(pEntiteStockValue.getRouleauxActuel());
		entiteStock.setRouleauxReserve(pEntiteStockValue.getRouleauxReserve());
		return entiteStock;
	}
	
	
	
	              /*********************************** BON SOCK  **************************************/
	
	public BonStockValue toValue(BonStockEntity entity) {
		
		BonStockValue dto = new BonStockValue();
		
		if(entity.getId()!=null){
			dto.setId(entity.getId());
		}
		
		dto.setReference(entity.getReference());
		dto.setRefexterne(entity.getRefexterne());
		dto.setDate(entity.getDate());
		dto.setMontantHTaxe(entity.getMontantHTaxe());
		dto.setMontantTTC(entity.getMontantTTC());
		dto.setMontantTaxe(entity.getMontantTaxe());
		dto.setMontantRemise(entity.getMontantRemise());
		dto.setObservations(entity.getObservations());
		dto.setInfoSortie(entity.getInfoSortie());
		dto.setPartieIntId(entity.getPartieIntId());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setEtat(entity.getEtat());
		dto.setTransporteur(entity.getTransporteur());
		dto.setMetrageTotal(entity.getMetrageTotal());
		
		if(entity.getNatureLivraison() != null)
		dto.setNatureLivraison(entity.getNatureLivraison().trim());
		
		dto.setTotalPourcentageRemise(entity.getTotalPourcentageRemise());
		dto.setStock(entity.getStock());
		dto.setIdDepot(entity.getIdDepot());
		dto.setRefCommande(entity.getRefCommande());
		dto.setIdCamion(entity.getIdCamion());
		dto.setIdChauffeur(entity.getIdChauffeur());
	    dto.setIdRemorque(entity.getIdRemorque());
	    
	    dto.setDescription(entity.getDescription());
	    dto.setTypePartieInteressee(entity.getTypePartieInteressee());
	    
	    dto.setDeclare(entity.getDeclare());
	    
	    dto.setGroupeClientId(entity.getGroupeClientId());
	    
	    dto.setType(entity.getType());
	    dto.setRefFacture(entity.getRefFacture());
	    dto.setRefAvoirRetour(entity.getRefAvoirRetour());
	    dto.setRefBR(entity.getRefBR());
	    
	    dto.setDateConcernee(entity.getDateConcernee());
	    
		if(entity.getListDetBonStock()!= null){
	    	List<DetBonStockValue> list = new ArrayList <DetBonStockValue>();
		     for (DetBonStockEntity detBonStockEntity : entity.getListDetBonStock()) {
		    	 DetBonStockValue detBonStockValue = toValue(detBonStockEntity);
		    	 list.add(detBonStockValue);
		    }
		     dto.setListDetBonStock(list);
		}
	    
	 
	    
	    dto.setModifier(entity.isModifier());
	    dto.setModepaiementId(entity.getModepaiementId());
	    dto.setMarcheId(entity.getMarcheId());
	    dto.setCamion(entity.getCamion());
	    dto.setChauffeur(entity.getChauffeur());
	    dto.setRemorque(entity.getRemorque());
	   
	  
		
		return dto;
	}

	public BonStockEntity toEntity(BonStockValue dto) {
		
		BonStockEntity entity = new BonStockEntity();
		
		entity.setId(dto.getId());
		entity.setReference(dto.getReference());
		entity.setRefexterne(dto.getRefexterne());
		entity.setDate(dto.getDate());
		entity.setMontantHTaxe(dto.getMontantHTaxe());
		entity.setMontantTTC(dto.getMontantTTC());
		entity.setMontantTaxe(dto.getMontantTaxe());
		entity.setMontantRemise(dto.getMontantRemise());
		entity.setObservations(dto.getObservations());
		entity.setInfoSortie(dto.getInfoSortie());
		entity.setPartieIntId(dto.getPartieIntId());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setEtat(dto.getEtat());
		entity.setTransporteur(dto.getTransporteur());
		entity.setMetrageTotal(dto.getMetrageTotal());
		
		if(dto.getNatureLivraison() != null)
		entity.setNatureLivraison(dto.getNatureLivraison().trim());
		
		entity.setTotalPourcentageRemise(dto.getTotalPourcentageRemise());
		entity.setStock(dto.getStock());
		entity.setIdDepot(dto.getIdDepot());
		entity.setRefCommande(dto.getRefCommande());
		entity.setIdCamion(dto.getIdCamion());
		entity.setIdChauffeur(dto.getIdChauffeur());
		entity.setIdRemorque(dto.getIdRemorque());
		entity.setCamion(dto.getCamion());
		entity.setRemorque(dto.getRemorque());
		entity.setChauffeur(dto.getChauffeur());
		
		entity.setDescription(dto.getDescription());
		entity.setTypePartieInteressee(dto.getTypePartieInteressee());
		
		entity.setDeclare(dto.getDeclare());
		
		entity.setGroupeClientId(dto.getGroupeClientId());
		
		entity.setType(dto.getType());
		entity.setRefFacture(dto.getRefFacture());
		entity.setRefAvoirRetour(dto.getRefAvoirRetour());
		entity.setRefBR(dto.getRefBR());
		
		entity.setDateConcernee(dto.getDateConcernee());
		
	    if(dto.getListDetBonStock() != null){
		     Set<DetBonStockEntity> list = new HashSet <DetBonStockEntity>();
		     for (DetBonStockValue detBonStockValue : dto.getListDetBonStock()) {
		    	 DetBonStockEntity detBonStockEntity = toEntity(detBonStockValue);
		    	 detBonStockEntity.setBonStock(entity);
		    	 list.add(detBonStockEntity);
		    }
		     entity.setListDetBonStock(list);
		}
	    

	    
	    entity.setModifier(dto.isModifier());
	    entity.setModepaiementId(dto.getModepaiementId());
	    entity.setMarcheId(dto.getMarcheId());
		
		return entity;
	}

	public DetBonStockEntity toEntity(DetBonStockValue dto) {
		
		DetBonStockEntity entity = new DetBonStockEntity();
		
		entity.setId(dto.getId());
		entity.setProduitId(dto.getProduitId());
		entity.setQuantite(dto.getQuantite());
		entity.setUnite(dto.getUnite());
		entity.setNombreColis(dto.getNombreColis());
		entity.setRemise(dto.getRemise());
		entity.setChoix(dto.getChoix());
		entity.setTraitementFaconId(dto.getTraitementFaconId());
		entity.setPrixUnitaireHT(dto.getPrixUnitaireHT());
		entity.setPrixTotalHT(dto.getPrixTotalHT());
		entity.setFicheId(dto.getFicheId());
		entity.setUniteSupplementaire(dto.getUniteSupplementaire());
		entity.setQuantiteConversion(dto.getQuantiteConversion());
		entity.setConversion(dto.getConversion());
		entity.setPrixUnitaireHTConversion(dto.getPrixUnitaireHTConversion());
		entity.setTauxTva(dto.getTauxTVA());
		entity.setPrixTTC(dto.getPrixTTC());
		entity.setSerialisable(dto.isSerialisable());
		entity.setNumeroSeries(dto.getNumeroSeries());
		entity.setDescription(dto.getDescription());
		
		
		
		if(dto.getBonStockId()!= null){
			BonStockEntity bonStocEntity = new BonStockEntity();
			bonStocEntity.setId(dto.getBonStockId());
			entity.setBonStock(bonStocEntity);
		}
		
		return entity;
	}

	public DetBonStockValue toValue(DetBonStockEntity entity) {
		
		DetBonStockValue dto = new DetBonStockValue();
		
		dto.setId(entity.getId());
		dto.setProduitId(entity.getProduitId());
		dto.setQuantite(entity.getQuantite());
		dto.setUnite(entity.getUnite());
		dto.setNombreColis(entity.getNombreColis());
		dto.setRemise(entity.getRemise());
		dto.setChoix(entity.getChoix());
		dto.setTraitementFaconId(entity.getTraitementFaconId());
		dto.setPrixUnitaireHT(entity.getPrixUnitaireHT());
		dto.setPrixTotalHT(entity.getPrixTotalHT());
		dto.setFicheId(entity.getFicheId());
		dto.setUniteSupplementaire(entity.getUniteSupplementaire());
		dto.setQuantiteConversion(entity.getQuantiteConversion());
		dto.setPrixUnitaireHTConversion(entity.getPrixUnitaireHTConversion());
		dto.setConversion(entity.getConversion());
		dto.setTauxTVA(entity.getTauxTva());
		dto.setPrixTTC(entity.getPrixTTC());
		dto.setSerialisable(entity.isSerialisable());
		dto.setNumeroSeries(entity.getNumeroSeries());
		dto.setDescription(entity.getDescription());
		
		/*
		if (entity.getProduitId()!=null) {
			ProduitValue produitValue = produitPersistance.rechercheProduitById(entity.getProduitId());
			dto.setProduitDesignation(produitValue.getDesignation());
			dto.setProduitReference(produitValue.getReference());
		}*/
		
		if(entity.getBonStock() != null){
			dto.setBonStockId(entity.getBonStock().getId());
			dto.setDate(entity.getBonStock().getDate());
			dto.setReferenceBL(entity.getBonStock().getReference());
		}
		
		return dto;
	}

}
