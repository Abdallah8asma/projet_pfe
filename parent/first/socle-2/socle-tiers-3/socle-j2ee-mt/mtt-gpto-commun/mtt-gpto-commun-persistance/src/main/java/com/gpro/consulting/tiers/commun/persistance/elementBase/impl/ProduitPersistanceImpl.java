package com.gpro.consulting.tiers.commun.persistance.elementBase.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.elementBase.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class ProduitPersistanceImpl.
 * @author med
 */

@Component
public class ProduitPersistanceImpl extends AbstractPersistance implements IProduitPersistance {
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	private String familleProduit="familleProduit";
	private String sousFamille="sousFamille";
	private String id="id";
	private String reference="reference";
	private String designation="designation";
	private String partieIntersseId="partieIntersseId";
	private String siteId="siteId";
	private String etat="etat";
	private String prixUnitaire="prixUnitaire";
	private String unite="unite";
	
	private String retour="retour";
	
	private String stock="stock";
	
	private String boutiqueId="boutiqueId";
	
	private String numero="numero";
	
	private String referenceFournisseur="referenceFournisseur";
	private String pantone="pantone";
	private String format="format";
	
	
	// creer  produit
	@Override
	public String creerProduit(ProduitValue pProduitValue) {
		SousFamilleProduitEntity vSousFamille=new SousFamilleProduitEntity();
		if(log.isDebugEnabled()){
			//log.debug("creation de produit de designation =" + pProduitValue.getDesignation() + " est en cours.");
		}
		if(pProduitValue.getSousFamilleId()!=null){
			vSousFamille=this.rechercherParId(pProduitValue.getSousFamilleId(), SousFamilleProduitEntity.class);
		}		
		ProduitEntity vProduitEntity= (ProduitEntity) this.creer(PersistanceUtilities.toEntity(pProduitValue,vSousFamille));
		ProduitValue vProduitValueResult=PersistanceUtilities.toValue(vProduitEntity);
		return vProduitValueResult.getId().toString();
	}

	// supprimer produit
	@Override
	public void supprimerProduit(Long pProduitId) {
		if (log.isDebugEnabled()) {

			//log.debug("Suppression de  la produit  d'ID=" + pProduitId.toString() + " est en cours.");

           }
          this.supprimerEntite(ProduitEntity.class, pProduitId);	
	}

	//modifier produit
	@Override
	public String modifierProduit(ProduitValue pProduitValue) {
		SousFamilleProduitEntity vSousFamille=new SousFamilleProduitEntity();
		if(log.isDebugEnabled()){
			//log.debug("creation de produit   de designation =" + pProduitValue.getDesignation() + " est en cours.");
		}
		if(pProduitValue.getSousFamilleId()!=null){
			vSousFamille=this.rechercherParId(pProduitValue.getSousFamilleId(), SousFamilleProduitEntity.class);
		}
		ProduitEntity vProduitEntity= (ProduitEntity) this.modifier(PersistanceUtilities.toEntity(pProduitValue,vSousFamille));
		ProduitValue vProduitValueResult=PersistanceUtilities.toValue(vProduitEntity);
		return vProduitValueResult.getId().toString();
	}

	//recherche par id
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId) {
		if(log.isDebugEnabled()){
			//log.debug("recherche  de produit   d'id =" +pProduitId + " est en cours.");
		}
		ProduitEntity vProduitEntity=  this.rechercherParId(pProduitId,ProduitEntity.class);
		ProduitValue vProduitValueResult=PersistanceUtilities.toValue(vProduitEntity);
		return vProduitValueResult;
	}
	
	//recherche par id
		@Override
		public String rechercheDesignationProduitById(Long pProduitId) {
			
			ProduitEntity vProduitEntity=  this.rechercherParId(pProduitId,ProduitEntity.class);
			
			return vProduitEntity.getDesignation();
		}
	
	//liste produit
	@Override
	public List<ProduitValue> listeProduit() {
		 List<ProduitEntity> vListeProduitsEntity = this.lister(ProduitEntity.class);
		 List < ProduitValue > vListeProduitsValues = new ArrayList < ProduitValue >();
		 for (ProduitEntity vProduitEntite : vListeProduitsEntity) {
			 vListeProduitsValues.add(PersistanceUtilities.toValue(vProduitEntite));
	 	    }
		return vListeProduitsValues;
	}
	//entity manager getter
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public ProduitValue rechercheProduitParReference(String ref) {
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < ProduitEntity > vCriteriaQuery = vBuilder.createQuery(ProduitEntity.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resultat ***/

		    /************ entity jointure *****************/
		    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

		    /***************** Predicate *************/
		    
		    
		  
		    
		    
		    
		   
		    
		    
		    
		    if (ref!=null) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(reference),
		    		 ref));
		    }
  
		  
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
		    List < ProduitEntity > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < ProduitValue > vListeResultat = new ArrayList < ProduitValue >();

		    for (ProduitEntity vProduitEntite : resultatEntite) {
		    	ProduitValue vPv = PersistanceUtilities.toValue(vProduitEntite);
		      vListeResultat.add(vPv);
		    }

		   

		    if(vListeResultat!=null && vListeResultat.size()>0)
		    	return vListeResultat.get(0);
		  
				return null;

	}

	/**rechereche multi critere */
	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritere(RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < ProduitEntity > vCriteriaQuery = vBuilder.createQuery(ProduitEntity.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resultat ***/

		    /************ entity jointure *****************/
		    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

		    /***************** Predicate *************/
		    
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getNumero())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(numero),
			    		  pRechercheProduitMulitCritere.getNumero()));
			    }
		    
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getReferenceFournisseur())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(referenceFournisseur),
			    		  pRechercheProduitMulitCritere.getReferenceFournisseur()));
			    }
		    
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getStock())) {
		    	 
		    	Expression<Boolean> expression = vRootProduit.get(stock);
				switch (pRechercheProduitMulitCritere.getStock()) {
					case IConstante.OUI:
						vWhereClause.add(vBuilder.isTrue(expression));
						break;
					case IConstante.NON:
						vWhereClause.add(vBuilder.isFalse(expression));
						break;
					case IConstante.ALL:
						break;
					default:
						break;
				}
				
				
				
			    }
		    
		    
		    if (pRechercheProduitMulitCritere.getBoutiqueId() != null ) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(boutiqueId),
			    		  pRechercheProduitMulitCritere.getBoutiqueId()));
			    }
		    
		    
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getRetour())) {
		    	 
		    	Expression<Boolean> expression = vRootProduit.get(retour);
				switch (pRechercheProduitMulitCritere.getRetour()) {
					case IConstante.OUI:
						vWhereClause.add(vBuilder.isTrue(expression));
						break;
					case IConstante.NON:
						vWhereClause.add(vBuilder.isFalse(expression));
						break;
					case IConstante.ALL:
						break;
					default:
						break;
				}
				
				
				
			    }
		    
		    
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getReference())) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(reference),
		    		  pRechercheProduitMulitCritere.getReference()));
		    }

		    if (estNonVide(pRechercheProduitMulitCritere.getDesignation())) {
		      vWhereClause.add(vBuilder.equal(vRootProduit.get(designation),
		    		  pRechercheProduitMulitCritere.getDesignation()));
		    }
              
		    if (estNonVide(pRechercheProduitMulitCritere.getFamille())) {
		    	  Join<ProduitEntity, SousFamilleProduitEntity> jointurePrdSousFm = vRootProduit.join(sousFamille);
			  	  Join<SousFamilleProduitEntity, FamilleProduitEntity> jointureSFmFamille = jointurePrdSousFm.join(familleProduit);
			      vWhereClause.add(vBuilder.equal(jointureSFmFamille.get(id),
			    		  pRechercheProduitMulitCritere.getFamille()));
			    }
		    
		 /*   if (estNonVide(pRechercheProduitMulitCritere.getPartieInteressee())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(partieIntersseId),
		    		  pRechercheProduitMulitCritere.getPartieInteressee()));
		    }
		   */ 
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getSite())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(siteId),
			    		  pRechercheProduitMulitCritere.getSite()));
			    }
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getSousfamille())) {
		    	  Join<ProduitEntity, SousFamilleProduitEntity> jointurePrdSousFm = vRootProduit.join(sousFamille);
			      vWhereClause.add(vBuilder.equal(jointurePrdSousFm.get(id),
			    		  pRechercheProduitMulitCritere.getSousfamille()));
			    }

		    if (estNonVide(pRechercheProduitMulitCritere.getEtat())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(etat),
			    		  pRechercheProduitMulitCritere.getEtat()));
			    }
		    
		    
		    if( pRechercheProduitMulitCritere.getPrix_inf()!=null ){
			    Expression<Double> rootValeur =vRootProduit.get(prixUnitaire);
		        vWhereClause.add(vBuilder.ge(rootValeur, pRechercheProduitMulitCritere.getPrix_inf()));
		      }
		      if( pRechercheProduitMulitCritere.getPrix_sup()!=null ){
				    Expression<Double> rootValeur =vRootProduit.get(prixUnitaire);
			        vWhereClause.add(vBuilder.le(rootValeur, pRechercheProduitMulitCritere.getPrix_sup()));
			      }
		      
		  
		      
			    if (estNonVide(pRechercheProduitMulitCritere.getPantone())) {
				      vWhereClause.add(vBuilder.equal(vRootProduit.get(pantone),
				    		  pRechercheProduitMulitCritere.getPantone()));
				    }
			    if (estNonVide(pRechercheProduitMulitCritere.getFormat())) {
				      vWhereClause.add(vBuilder.equal(vRootProduit.get(format),
				    		  pRechercheProduitMulitCritere.getFormat()));
				    }
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
		    List < ProduitEntity > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < ProduitValue > vListeResultat = new ArrayList < ProduitValue >();

		    for (ProduitEntity vProduitEntite : resultatEntite) {
		    	ProduitValue vPv = PersistanceUtilities.toValue(vProduitEntite);
		      vListeResultat.add(vPv);
		    }

		    /** retour de list de recherche et le nombre de resultat **/
		    ResultatRechecheProduitValue vResultatRechecheProduitValue = new ResultatRechecheProduitValue();

		    vResultatRechecheProduitValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));
		    
		    Collections.sort(vListeResultat);
		    
		    vResultatRechecheProduitValue.setProduitValues(new TreeSet<>(vListeResultat));

		    return vResultatRechecheProduitValue;

	}
	
	 @SuppressWarnings("unused")
	  private boolean estNonVide(String val) {
	    return val != null && !"".equals(val);

	  }

	
	 
		
		@Override
		public List<ProduitValue> rechercheProduitFinance() {
			  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
			    CriteriaQuery < ProduitEntity > vCriteriaQuery = vBuilder.createQuery(ProduitEntity.class);
			    List < Predicate > vWhereClause = new ArrayList < Predicate >();

			    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

			
			      vWhereClause.add(vBuilder.equal(vRootProduit.get("retour"),
			    		true));
			 
			    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
			    List < ProduitEntity > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

			    List < ProduitValue > vListeResultat = new ArrayList < ProduitValue >();

			    for (ProduitEntity vProduitEntite : resultatEntite) {
			    	ProduitValue vPv = PersistanceUtilities.toValue(vProduitEntite);
			      vListeResultat.add(vPv);
			    }

			   

				return vListeResultat;
			  
			

		}

		@Override
		public List<ProduitValue> rechercheProduitNonFinance() {
			  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
			    CriteriaQuery < ProduitEntity > vCriteriaQuery = vBuilder.createQuery(ProduitEntity.class);
			    List < Predicate > vWhereClause = new ArrayList < Predicate >();

			    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

			
			      vWhereClause.add(vBuilder.equal(vRootProduit.get("retour"),
			    		false));
			 
			    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
			    List < ProduitEntity > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

			    List < ProduitValue > vListeResultat = new ArrayList < ProduitValue >();

			    for (ProduitEntity vProduitEntite : resultatEntite) {
			    	ProduitValue vPv = PersistanceUtilities.toValue(vProduitEntite);
			      vListeResultat.add(vPv);
			    }

			   

				return vListeResultat;
			  
			
		}


	


}
