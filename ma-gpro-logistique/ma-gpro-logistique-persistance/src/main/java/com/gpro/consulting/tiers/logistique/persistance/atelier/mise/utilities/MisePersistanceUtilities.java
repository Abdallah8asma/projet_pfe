package com.gpro.consulting.tiers.logistique.persistance.atelier.mise.utilities;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ElementRechecheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.GuichetMiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.MiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.entity.TraitementMiseEntity;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.entity.FicheSuiveuseEntity;


/**
 * Classe Utilitaire permettant de convertir des objets valeur en entité et des objets entité en
 * objets valeur
 * 
 * @author Ghazi
 *
 */
@Component
public class MisePersistanceUtilities {

  /**
   * Instanciation du gestionnaire de persistance
   */
  private static MisePersistanceUtilities instance = new MisePersistanceUtilities();

  /** Logger */
  private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(MisePersistanceUtilities.class);
  /**
   * Convertion TraitementMiseValeur en TraitementMiseEntité.
   * 
   * @param pTraitementMiseValue
   * @return vTraitementMiseEntity
   */
  public static TraitementMiseEntity toEntity(TraitementMiseValue pTraitementMiseValue,
		  FicheSuiveuseEntity pficheSuiveuseEntity) {
    TraitementMiseEntity vTraitementMiseEntity = new TraitementMiseEntity();
    if (pTraitementMiseValue.getId() != null) {
    	vTraitementMiseEntity.setId(pTraitementMiseValue.getId());
    }

    if (vTraitementMiseEntity != null){ /*&& pMiseEntity.getId() != null) {*/
    	vTraitementMiseEntity.setFicheSuiveuse(pficheSuiveuseEntity);;
    }
    vTraitementMiseEntity.setMachineId(pTraitementMiseValue.getMachineId());
    vTraitementMiseEntity.setObservations(pTraitementMiseValue.getObservations());
    vTraitementMiseEntity.setTraitementId(pTraitementMiseValue.getTraitementId());
    vTraitementMiseEntity.setVersion(pTraitementMiseValue.getVersion());
    
    vTraitementMiseEntity.setBlSuppression(pTraitementMiseValue.getBlSuppression());
    vTraitementMiseEntity.setDateCreation(pTraitementMiseValue.getDateCreation());
    vTraitementMiseEntity.setDateModification(pTraitementMiseValue.getDateModification());
    vTraitementMiseEntity.setDateSuppression(pTraitementMiseValue.getDateSuppression());
    vTraitementMiseEntity.setVersion(pTraitementMiseValue.getVersion());
    
    vTraitementMiseEntity.setPu(pTraitementMiseValue.getPu());

    return vTraitementMiseEntity;
  }
  
  public static MiseEntity toEntity(MiseValue dto) {
	  
	  MiseEntity entity = new MiseEntity();
	  
	  if(dto.getId()!=null){
		  entity.setId(dto.getId());
	  }
	  
	  entity.setReference(dto.getReference());
	  entity.setRefBonreception(dto.getRefBonreception());
	  entity.setDateIntroduction(dto.getDateIntroduction());
	  entity.setCodeBarre(dto.getCodeBarre());
	  entity.setMetrage(dto.getMetrage());
	  entity.setPoids(dto.getPoids());
	  entity.setNombreRouleau(dto.getNombreRouleau());
	  entity.setObservations(dto.getObservations());
	  entity.setProduitId(dto.getProduitId());
	  entity.setPartieintId(dto.getPartieintId());
	  entity.setFini(dto.isFini());
	  
	  

	  //from thermo
		entity.setPoidFini(dto.getPoidFini());
		entity.setQuantite(dto.getQuantite());
		entity.setDateFin(dto.getDateFin());
		entity.setStatut(dto.getStatut());
		entity.setDestinationProduit(dto.getDestinationProduit());
        entity.setTypeEtiquette(dto.getTypeEtiquette());
		entity.setTypeOF(dto.getTypeOF());
		entity.setPackaging(dto.getPackaging());
		entity.setDestination(dto.getDestination());
		entity.setMachine(dto.getMachine());
		
		entity.setQteProduite(dto.getQteProduite());
		entity.setNbrColis(dto.getNbrColis());
		entity.setDateDebutProduction(dto.getDateDebutProduction());
		entity.setDateFinProduction(dto.getDateFinProduction());
		
		entity.setNbrColisExpedition(dto.getNbrColisExpedition());
		entity.setQteExpedition(dto.getQteExpedition());
	  entity.setRefCommande(dto.getRefCommande());
	  /*
	  if(dto.getListeTraitements()!=null){
		  
		  Set <TraitementMiseEntity> listTraitementEntity = new HashSet<TraitementMiseEntity>();
		  
		  for (TraitementMiseValue traitementMiseValue : dto.getListeTraitements()){
			  TraitementMiseEntity traitementMiseEntity = toEntity(traitementMiseValue, entity);
			  listTraitementEntity.add(traitementMiseEntity);
		  }
		  entity.setListeTraitements(listTraitementEntity);
	  }
	  */
	  return entity;
  }
  
  /**
   * Converstion ReceptionTraitementEntite en ReceptionTraitementValue
   * 
   * @param pReceptionTraitementEntity
   * @return vReceptionTraitementValue
   */
  public static TraitementMiseValue toValue(TraitementMiseEntity entity) {
	  
	TraitementMiseValue dto = new TraitementMiseValue();
	if (entity.getFicheSuiveuse() != null) {
		dto.setFicheSuiveuseId(entity.getFicheSuiveuse().getId());
	}
	
	if(entity.getId() != null){
		dto.setId(entity.getId());
	}
	
	dto.setMachineId(entity.getMachineId());
	dto.setObservations(entity.getObservations());
	dto.setTraitementId(entity.getTraitementId());
	
	dto.setBlSuppression(entity.isBlSuppression());
	dto.setDateCreation(entity.getDateCreation());
	dto.setDateModification(entity.getDateModification());
	dto.setDateSuppression(entity.getDateSuppression());
	dto.setVersion(entity.getVersion());
	dto.setPu(entity.getPu());

	return dto;
  }
  
  
  public static MiseValue toValue(MiseEntity entity) {
	  
	MiseValue dto = new MiseValue();
	
	dto.setId(entity.getId());
	dto.setReference(entity.getReference());
	dto.setRefBonreception(entity.getRefBonreception());
	dto.setDateIntroduction(entity.getDateIntroduction());
	dto.setMetrage(entity.getMetrage());
	dto.setPoids(entity.getPoids());
	dto.setObservations(entity.getObservations());
	dto.setPartieintId(entity.getPartieintId());
	dto.setProduitId(entity.getProduitId());
	dto.setFini(entity.isFini());
	

	//from thermo
	dto.setPoidFini(entity.getPoidFini());
	dto.setQuantite(entity.getQuantite());
	dto.setStatut(entity.getStatut());
	dto.setDestinationProduit(entity.getDestinationProduit());
	dto.setDateFin(entity.getDateFin());

	dto.setTypeEtiquette(entity.getTypeEtiquette());
	dto.setTypeOF(entity.getTypeOF());
	dto.setPackaging(entity.getPackaging());
	dto.setDestination(entity.getDestination());	
	
	dto.setMachine(entity.getMachine());
		
	dto.setQteProduite(entity.getQteProduite());
	dto.setNbrColis(entity.getNbrColis());
	dto.setDateDebutProduction(entity.getDateDebutProduction());
	dto.setDateFinProduction(entity.getDateFinProduction());
	
	dto.setNbrColisExpedition(entity.getNbrColisExpedition());
	dto.setQteExpedition(entity.getQteExpedition());
	
	dto.setRefCommande(entity.getRefCommande());
	 
	 /* 
	 if(pMiseEntity.getListeTraitements()!=null) { 
	 Set <TraitementMiseValue> vListeTraitement = new HashSet<TraitementMiseValue>();

	    for (TraitementMiseEntity traitementEntity : pMiseEntity.getListeTraitements()) {
	      TraitementMiseValue vRt = toValue(traitementEntity);
	      vListeTraitement.add(vRt);
	    }
	 vMiseValue.setListeTraitements(vListeTraitement);
	 }
	 */
	return dto;
	
  }
  
	
	public boolean checkForOptimization(
			RechercheMulticritereMiseValue request) {

		return isNullOrEmpty(request.getDateIntroduction())
				&& isNullOrEmpty(request.getDateIntroductionDE())
				&& isNullOrEmpty(request.getDateIntroductionA())
				&& isNullOrEmpty(request.getProduitId())
				&& isNullOrEmpty(request.getClient())
				&& isNullOrEmpty(request.getDateLivraisonDE())
				&& isNullOrEmpty(request.getDateLivraisonA())
				&& isNullOrEmpty(request.getDateDebutProductionDe())
				&& isNullOrEmpty(request.getDateDebutProductionA())
				
				&& isNullOrEmpty(request.getReferenceMise())
				&& isNullOrEmpty(request.getReferenceProduit())
		
			;

	}
	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}
  /**
   * 
   * Méthode permettant la conversion d'une entité en valeur : MiseValue
   * 
   * @param vMiseEntite
   * @return
   */
  public ElementRechecheMiseValue ResultatRechecheMiseValue(MiseEntity vMiseEntite) {
	  ElementRechecheMiseValue vElementRechecheMiseValue = new ElementRechecheMiseValue();
	  vElementRechecheMiseValue.setIdMise(vMiseEntite.getId());
      vElementRechecheMiseValue.setReference(vMiseEntite.getReference());
      vElementRechecheMiseValue.setAbreviationClient(vMiseEntite.getPartieintId());
      vElementRechecheMiseValue.setPoids(vMiseEntite.getPoids());
      vElementRechecheMiseValue.setMetrage(vMiseEntite.getMetrage());
      vElementRechecheMiseValue.setDateIntroduction(vMiseEntite.getDateIntroduction());
      vElementRechecheMiseValue.setDesignationProduit(vMiseEntite.getProduitId());
      
      
      vElementRechecheMiseValue.setReferenceBR(vMiseEntite.getRefBonreception());
		vElementRechecheMiseValue.setPoidFini(vMiseEntite.getPoidFini());
		vElementRechecheMiseValue.setDateFin(vMiseEntite.getDateFin());
		vElementRechecheMiseValue.setQuantite(vMiseEntite.getQuantite());
		vElementRechecheMiseValue.setDestinationProduit(vMiseEntite.getDestinationProduit());
      vElementRechecheMiseValue.setTypeOF(vMiseEntite.getTypeOF());
      
      vElementRechecheMiseValue.setStatut(vMiseEntite.getStatut());
      
      vElementRechecheMiseValue.setDateDebutProduction(vMiseEntite.getDateDebutProduction());
      vElementRechecheMiseValue.setDateFinProduction(vMiseEntite.getDateFinProduction());
      vElementRechecheMiseValue.setQteProduite(vMiseEntite.getQteProduite());
      vElementRechecheMiseValue.setNbrColis(vMiseEntite.getNbrColis());
      
      vElementRechecheMiseValue.setDesignationProduitDesignation(vMiseEntite.getDestinationProduit());
      
      vElementRechecheMiseValue.setNbrColisExpedition(vMiseEntite.getNbrColisExpedition());
      vElementRechecheMiseValue.setQteExpedition(vMiseEntite.getQteExpedition());
      
      vElementRechecheMiseValue.setMachine(vMiseEntite.getMachine());
      return vElementRechecheMiseValue;
  }

/**
 * @return the instance
 */
public static MisePersistanceUtilities getInstance() {
	return instance;
}

/**
 * @param instance the instance to set
 */
public static void setInstance(MisePersistanceUtilities instance) {
	MisePersistanceUtilities.instance = instance;
}
/**
 * Permet de convertir Guichet Bon Reception Valeur en entité
 * 
 * @param pGuichetValeur
 *          la valeur à convertir
 * @return GuichetMiseEntity l'entité convertie
 */
public static GuichetMiseEntity toEntite(final GuichetMiseValue pGuichetValeur) {

  // Création d'une entité Guichet
	  GuichetMiseEntity vGuichetEntite = new GuichetMiseEntity();
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
