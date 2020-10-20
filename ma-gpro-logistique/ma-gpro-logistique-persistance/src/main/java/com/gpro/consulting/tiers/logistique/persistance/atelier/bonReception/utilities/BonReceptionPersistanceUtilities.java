package com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.BonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ElementRechecheBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.GuichetBonReceptionValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.MachineValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.ReceptionTraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.RouleauEcruValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.BonReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.GuichetBonReceptionEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.MachineEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.ReceptionTraitementEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.RouleauEcruEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.bonReception.entity.TraitementEntity;


/**
 * Classe Utilitaire permettant de convertir des objets valeur en entité et des objets entité en
 * objets valeur
 * 
 * @author Ameni
 *
 */

@Component
public class BonReceptionPersistanceUtilities {

  /** Logger */
  private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(BonReceptionPersistanceUtilities.class);
  /***************************** To Entity ********************************/
  /**
   * Convertion BonReceptionValeur enBonReceptionEntité.
   * 
   * @param pBonReceptionValue
   * @return vBonReceptionEntity
   */
  public static BonReceptionEntity toEntity(BonReceptionValue pBonReceptionValue) {
    BonReceptionEntity vBonReceptionEntity = new BonReceptionEntity();
    /** Caractéristique du Bon de reception */
    if (pBonReceptionValue.getId() != null) {
      vBonReceptionEntity.setId(pBonReceptionValue.getId());
    }
    vBonReceptionEntity.setBcClient(pBonReceptionValue.getBcClient());
    vBonReceptionEntity.setDateIntroduction(pBonReceptionValue.getDateIntroduction());
    vBonReceptionEntity.setDateLivraison(pBonReceptionValue.getDateLivraison());
    vBonReceptionEntity.setEtat(pBonReceptionValue.getEtat());
    vBonReceptionEntity.setLaizeFini(pBonReceptionValue.getLaizeFini());
    vBonReceptionEntity.setMetrageAnnonce(pBonReceptionValue.getMetrageAnnonce());
    vBonReceptionEntity.setMetrageTrouve(pBonReceptionValue.getMetrageTrouve());
    vBonReceptionEntity.setNombreRouleau(pBonReceptionValue.getNombreRouleau());
    vBonReceptionEntity.setObservations(pBonReceptionValue.getObservations());
    vBonReceptionEntity.setPartieInteressee(pBonReceptionValue.getPartieInteressee());
    vBonReceptionEntity.setPoidsAnnonce(pBonReceptionValue.getPoidsAnnonce());
    vBonReceptionEntity.setPoidsTrouve(pBonReceptionValue.getPoidsTrouve());
    vBonReceptionEntity.setProduit(pBonReceptionValue.getProduit());
    vBonReceptionEntity.setReference(pBonReceptionValue.getReference());
    vBonReceptionEntity.setVersion(pBonReceptionValue.getVersion());
    vBonReceptionEntity.setFini(pBonReceptionValue.isFini());
    

    /** Liste des rouleaux */
    if(pBonReceptionValue.getListeRouleauxEcru() != null){
	     Set < RouleauEcruEntity > vListeRouleau = new HashSet < RouleauEcruEntity >();
	     for (RouleauEcruValue rouleauValue : pBonReceptionValue.getListeRouleauxEcru()) {
	      RouleauEcruEntity rEe = toEntity(rouleauValue, vBonReceptionEntity);
	      vListeRouleau.add(rEe);
	    }
	    vBonReceptionEntity.setListeRouleauxEcru(vListeRouleau);
	}
    /** Liste des traitements */
   if(pBonReceptionValue.getListeTraitements() != null){
	  Set < ReceptionTraitementEntity > vListeRcTraitement = new HashSet < ReceptionTraitementEntity >();
	   for (ReceptionTraitementValue rcTraitementValue : pBonReceptionValue.getListeTraitements()) {
	      ReceptionTraitementEntity vTe = toEntity(rcTraitementValue, vBonReceptionEntity);
	      vListeRcTraitement.add(vTe);
	    }
	    vBonReceptionEntity.setListeTraitements(vListeRcTraitement);
	}
    return vBonReceptionEntity;
  }

  /**
   * Convertion MachineValeur en MachineEntité.
   * 
   * @param pMachineValue
   * @return vMachineEntity
   */
  public static MachineEntity toEntity(MachineValue pMachineValue) {
    MachineEntity vMachineEntity = new MachineEntity();
    if (pMachineValue.getId() != null) {
      vMachineEntity.setId(pMachineValue.getId());
    }
    vMachineEntity.setDesignation(pMachineValue.getDesignation());
    vMachineEntity.setVersion(pMachineValue.getVersion());

    return vMachineEntity;
  }

  /**
   * Convertion RouleauEcruValeur en RouleauEcruEntité.
   * 
   * @param pRouleauEcruValue
   * @return vRouleauEcruEntity
   */
  public static RouleauEcruEntity toEntity(RouleauEcruValue pRouleauEcruValue, BonReceptionEntity pBonReceptionEntity) {
    RouleauEcruEntity vRouleauEcruEntity = new RouleauEcruEntity();
    if (pRouleauEcruValue.getId() != null) {
      vRouleauEcruEntity.setId(pRouleauEcruValue.getId());
    }
    if (pBonReceptionEntity != null /*&& pBonReceptionEntity.getId() != null*/) {
      vRouleauEcruEntity.setBonReception(pBonReceptionEntity);
    }
    vRouleauEcruEntity.setMetrageAnnonce(pRouleauEcruValue.getMetrageAnnonce());
    vRouleauEcruEntity.setMetrageTrouve(pRouleauEcruValue.getMetrageTrouve());
    vRouleauEcruEntity.setPoidsAnnonce(pRouleauEcruValue.getPoidsAnnonce());
    vRouleauEcruEntity.setPoidsTrouve(pRouleauEcruValue.getPoidsTrouve());
    vRouleauEcruEntity.setReference(pRouleauEcruValue.getReference());
    vRouleauEcruEntity.setVersion(pRouleauEcruValue.getVersion());

    return vRouleauEcruEntity;
  }

  /**
   * Convertion ReceptionTraitementValeur en ReceptionTraitementEntité.
   * 
   * @param pReceptionTraitementValue
   * @return vReceptionTraitementEntity
   */
  public static ReceptionTraitementEntity toEntity(ReceptionTraitementValue pReceptionTraitementValue,
    BonReceptionEntity pBonReceptionEntity) {
    ReceptionTraitementEntity vReceptionTraitementEntity = new ReceptionTraitementEntity();
    if (pReceptionTraitementValue.getId() != null) {
      vReceptionTraitementEntity.setId(pReceptionTraitementValue.getId());
    }

    if (pBonReceptionEntity != null){ /*&& pBonReceptionEntity.getId() != null) {*/
      vReceptionTraitementEntity.setBonReception(pBonReceptionEntity);
    }
    vReceptionTraitementEntity.setMachineId(pReceptionTraitementValue.getMachineId());
    vReceptionTraitementEntity.setObservations(pReceptionTraitementValue.getObservations());
    vReceptionTraitementEntity.setTraitementId(pReceptionTraitementValue.getTraitementId());
    vReceptionTraitementEntity.setVersion(pReceptionTraitementValue.getVersion());

    return vReceptionTraitementEntity;
  }

  /**
   * Convertion TraitementValeur en TraitementEntité.
   * 
   * @param pTraitementValue
   * @return vTraitementEntity
   */
  public static TraitementEntity toEntity(TraitementValue pTraitementValue) {
    TraitementEntity vTraitementEntity = new TraitementEntity();
    if (pTraitementValue.getId() != null) {
      vTraitementEntity.setId(pTraitementValue.getId());
    }
    vTraitementEntity.setDesignation(pTraitementValue.getDesignation());
    vTraitementEntity.setFacture(pTraitementValue.isFacture());
    vTraitementEntity.setObservations(pTraitementValue.getObservations());
    vTraitementEntity.setVersion(pTraitementValue.getVersion());
    vTraitementEntity.setPU(pTraitementValue.getPU());

    return vTraitementEntity;
  }

  /***************************** To Value ***********************************/

  /**
   * Converstion BonReceptionEntite en BonReceptionValue
   * 
   * @param pBonReceptionEntity
   * @return vBonReceptionValue
   */
  public static BonReceptionValue toValue(BonReceptionEntity pBonReceptionEntity) {

    /** Caractéristiques */
    BonReceptionValue vBonReceptionValue = new BonReceptionValue();
    vBonReceptionValue.setId(pBonReceptionEntity.getId());
    vBonReceptionValue.setBcClient(pBonReceptionEntity.getBcClient());
    vBonReceptionValue.setDateIntroduction(pBonReceptionEntity.getDateIntroduction());
    vBonReceptionValue.setDateLivraison(pBonReceptionEntity.getDateLivraison());
    vBonReceptionValue.setEtat(pBonReceptionEntity.getEtat());
    vBonReceptionValue.setLaizeFini(pBonReceptionEntity.getLaizeFini());
    vBonReceptionValue.setMetrageAnnonce(pBonReceptionEntity.getMetrageAnnonce());
    vBonReceptionValue.setMetrageTrouve(pBonReceptionEntity.getMetrageTrouve());
    vBonReceptionValue.setNombreRouleau(pBonReceptionEntity.getNombreRouleau());
    vBonReceptionValue.setObservations(pBonReceptionEntity.getObservations());
    vBonReceptionValue.setPartieInteressee(pBonReceptionEntity.getPartieInteressee());
    vBonReceptionValue.setPoidsAnnonce(pBonReceptionEntity.getPoidsAnnonce());
    vBonReceptionValue.setPoidsTrouve(pBonReceptionEntity.getPoidsTrouve());
    vBonReceptionValue.setProduit(pBonReceptionEntity.getProduit());
    vBonReceptionValue.setReference(pBonReceptionEntity.getReference());
    vBonReceptionValue.setVersion(pBonReceptionEntity.getVersion());
    vBonReceptionValue.setFini(pBonReceptionEntity.isFini());

    /** Liste des rouleaux */
    List<RouleauEcruValue> vListeRouleau = new ArrayList <RouleauEcruValue>();

    for (RouleauEcruEntity rouleauEntity : pBonReceptionEntity.getListeRouleauxEcru()) {
      RouleauEcruValue vRv = toValue(rouleauEntity);
      vListeRouleau.add(vRv);
    }

    vBonReceptionValue.setListeRouleauxEcru(vListeRouleau);
    
    /** Liste des traitements */
    List <ReceptionTraitementValue> vListeTraitement = new ArrayList <ReceptionTraitementValue>();

    for (ReceptionTraitementEntity traitementEntity : pBonReceptionEntity.getListeTraitements()) {
      ReceptionTraitementValue vRt = toValue(traitementEntity);
      vListeTraitement.add(vRt);
    }

    vBonReceptionValue.setListeTraitements(vListeTraitement);

    return vBonReceptionValue;
  }
  
  /**
   * Converstion MachineEntite en MachineValue
   * 
   * @param pMachineEntity
   * @return vMachineValue
   */
  public static MachineValue toValue(MachineEntity pMachineEntity) {
    MachineValue vMachineValue = new MachineValue();
    vMachineValue.setDesignation(pMachineEntity.getDesignation());
    vMachineValue.setVersion(pMachineEntity.getVersion());
    return vMachineValue;
  }

  /**
   * Converstion ReceptionTraitementEntite en ReceptionTraitementValue
   * 
   * @param pReceptionTraitementEntity
   * @return vReceptionTraitementValue
   */
  public static ReceptionTraitementValue toValue(ReceptionTraitementEntity pReceptionTraitementEntity) {
    ReceptionTraitementValue vReceptionTraitementValue = new ReceptionTraitementValue();
    if (pReceptionTraitementEntity.getBonReception() != null) {
      vReceptionTraitementValue.setBonReceptionId(pReceptionTraitementEntity.getBonReception().getId());
    }
    if(pReceptionTraitementEntity.getId() != null){
    	vReceptionTraitementValue.setId(pReceptionTraitementEntity.getId());
    }
    vReceptionTraitementValue.setMachineId(pReceptionTraitementEntity.getMachineId());
    vReceptionTraitementValue.setObservations(pReceptionTraitementEntity.getObservations());
    vReceptionTraitementValue.setTraitementId(pReceptionTraitementEntity.getTraitementId());
    vReceptionTraitementValue.setVersion(pReceptionTraitementEntity.getVersion());

    return vReceptionTraitementValue;
  }

  /**
   * Converstion RouleauEcruEntite en RouleauEcruValue
   * 
   * @param pRouleauEcruEntity
   * @return vRouleauEcruValue
   */
  public static RouleauEcruValue toValue(RouleauEcruEntity pRouleauEcruEntity) {
    RouleauEcruValue vRouleauEcruValue = new RouleauEcruValue();
   if(pRouleauEcruEntity.getId() != null){
	   vRouleauEcruValue.setId(pRouleauEcruEntity.getId());
   }
    if (pRouleauEcruEntity.getBonReception() != null) {
      vRouleauEcruValue.setBonReceptionId(pRouleauEcruEntity.getBonReception().getId());
    }
    vRouleauEcruValue.setMetrageAnnonce(pRouleauEcruEntity.getMetrageAnnonce());
    vRouleauEcruValue.setMetrageTrouve(pRouleauEcruEntity.getMetrageTrouve());
    vRouleauEcruValue.setPoidsAnnonce(pRouleauEcruEntity.getPoidsAnnonce());
    vRouleauEcruValue.setPoidsTrouve(pRouleauEcruEntity.getPoidsTrouve());
    vRouleauEcruValue.setReference(pRouleauEcruEntity.getReference());
    vRouleauEcruValue.setVersion(pRouleauEcruEntity.getVersion());
    return vRouleauEcruValue;
  }

  /**
   * Converstion TraitementEntite en TraitementValue
   * 
   * @param pTraitementEntity
   * @return vTraitementValue
   */
  public static TraitementValue toValue(TraitementEntity pTraitementEntity) {
    TraitementValue vTraitementValue = new TraitementValue();
    if(pTraitementEntity.getId() != null){
    	vTraitementValue.setId(pTraitementEntity.getId());
    }
    vTraitementValue.setDesignation(pTraitementEntity.getDesignation());
    vTraitementValue.setFacture(pTraitementEntity.isFacture());
    vTraitementValue.setObservations(pTraitementEntity.getObservations());
    vTraitementValue.setVersion(pTraitementEntity.getVersion());
    vTraitementValue.setPU(pTraitementEntity.getPU());
    return vTraitementValue;
  }


  /**
   * 
   * Méthode permettant la conversion d'une entité en valeur : BonReceptionValue
   * 
   * @param vBonReceptionEntite
   * @return
   */ 
  public ElementRechecheBonReceptionValue ResultatRechecheBonReceptionValue(BonReceptionEntity vBonReceptionEntite) {
    ElementRechecheBonReceptionValue vElementRechecheBonReceptionValue = new ElementRechecheBonReceptionValue();
    vElementRechecheBonReceptionValue.setReferenceBR(vBonReceptionEntite.getReference());
    vElementRechecheBonReceptionValue.setIdBonReception(vBonReceptionEntite.getId());
    vElementRechecheBonReceptionValue.setAbreviationClient(vBonReceptionEntite.getPartieInteressee());
    vElementRechecheBonReceptionValue.setDateIntroduction(vBonReceptionEntite.getDateIntroduction());
    vElementRechecheBonReceptionValue.setDateLivraison(vBonReceptionEntite.getDateLivraison());
    vElementRechecheBonReceptionValue.setDesignationProduit(vBonReceptionEntite.getProduit());
    vElementRechecheBonReceptionValue.setMetrageAnnonce(vBonReceptionEntite.getMetrageAnnonce());
    vElementRechecheBonReceptionValue.setNombreRouleau(vBonReceptionEntite.getNombreRouleau());

    return vElementRechecheBonReceptionValue;
  }

  /**
   * Permet de convertir Guichet Bon Reception Valeur en entité
   * 
   * @param pGuichetValeur
   *          la valeur à convertir
   * @return GuichetBonReceptionEntity l'entité convertie
   */
  public static GuichetBonReceptionEntity toEntite(final GuichetBonReceptionValue pGuichetValeur) {

    // Création d'une entité Guichet
	  GuichetBonReceptionEntity vGuichetEntite = new GuichetBonReceptionEntity();
    if (pGuichetValeur != null) {
      if (pGuichetValeur.getAnnee() != null) {
        vGuichetEntite.setAnnee(pGuichetValeur.getAnnee());
      }

      if (pGuichetValeur.getNumReferenceCourant() != null) {
        vGuichetEntite.setNumReferenceCourante(pGuichetValeur.getNumReferenceCourant());
      }
    }
    return vGuichetEntite;
  }
  
  
}
