package com.gpro.consulting.tiers.logistique.persistance.gs.charts.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.logistique.coordination.gc.chart.value.ResultBestElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.MouvementStockChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.IMouvementStockChartPersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.charts.utilities.MouvementStockChartPersistanceUtilities;
import com.gpro.consulting.tiers.logistique.persistance.gs.entite.MouvementEntite;

/**
 * Implementation of {@link IMouvementStockChartPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */

@Component
public class MouvementStockChartPersistanceImpl extends AbstractPersistance implements IMouvementStockChartPersistance{

	private String PREDICATE_TYPE = "type";
	private String PREDICATE_DATE = "date";
	private String TABLE_BON_MOUVEMENT = "bonMouvement";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	MouvementStockChartPersistanceUtilities mouvementStockChartPersistanceUtilities;

	@Override
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(RechercheMulticritereMouvementChartValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < MouvementEntite > criteriaQuery = criteriaBuilder.createQuery(MouvementEntite.class);
	    List < Predicate > whereClause = new ArrayList < Predicate >();
	    Root < MouvementEntite > rootMouvement = criteriaQuery.from(MouvementEntite.class);
//	    
//	    if(request.getDateFrom() != null){
//	    	Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = rootMouvement.join(TABLE_BON_MOUVEMENT);
//	    	Expression<Calendar> dateMouvement =jointureMvtBmvt.get(PREDICATE_DATE);
//	    	Calendar dateEntre = request.getDateFrom(); 
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(dateMouvement,dateEntre));
//	    }
//
//		if (request.getDateTo() != null) {
//			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = rootMouvement.join(TABLE_BON_MOUVEMENT);
//			Expression<Calendar> dateMouvement = jointureMvtBmvt.get(PREDICATE_DATE);
//			Calendar dateSortie = request.getDateTo();
//			whereClause.add(criteriaBuilder.lessThanOrEqualTo(dateMouvement, dateSortie));
//		}
	    
		if(estNonVide(request.getType())){
			whereClause.add(criteriaBuilder.equal(rootMouvement.get(PREDICATE_TYPE), request.getType()));
		}

	    criteriaQuery.select(rootMouvement).where(whereClause.toArray(new Predicate[] {}));
	    List < MouvementEntite > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<MouvementStockChartValue> listMouvementStockChart = new ArrayList < MouvementStockChartValue >();

	    for (MouvementEntite entity : resultatEntite) {
	    	MouvementStockChartValue dto = mouvementStockChartPersistanceUtilities.toValue(entity);
	    	listMouvementStockChart.add(dto);
	    }

	    ResultatRechecheMouvementChartValue result = new ResultatRechecheMouvementChartValue();

	    result.setNombreResultaRechercher(Long.valueOf(listMouvementStockChart.size()));

	    result.setListMouvementStock(listMouvementStockChart);
	    return result;
	}
	
	private boolean estNonVide(String val) {
		
		return val != null && !"".equals(val);
	}
	  
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ResultBestElementValue> getBySousFamille(RechercheMulticritereMouvementChartValue request) {

		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		String whereFamille = "";
	
		if(request.getFamilleArticle() != null)
		    whereFamille=" and mv.entiteStock.article.sousFamilleArtEntite.familleArticle.id=:idFamilleArticle " ;
		

		 
		String requete =    "select sum(mv.quantiteReelle),mv.entiteStock.article.sousFamilleArtEntite.designation from MouvementEntite mv "
			      + " where mv.bonMouvement.date>=:dateDe and  mv.bonMouvement.date<=:dateA  and mv.bonMouvement.type =:typeBM and "
			     +" mv.entiteStock.article.sousFamilleArtEntite.familleArticle.typeArticle.id=:typeArticleId  " +whereFamille
			      + "group by mv.entiteStock.article.sousFamilleArtEntite.designation order by sum(mv.quantiteReelle) desc" ;
			
		 Query vQuery = this.entityManager.createQuery(requete)
				 .setParameter("dateDe", request.getDateDe())
				 .setParameter("dateA", request.getDateA())
				 .setParameter("typeBM", request.getType()) 
		         .setParameter("typeArticleId", request.getTypeArticle()) ;
		 
			if(request.getFamilleArticle() != null)
	              vQuery.setParameter("idFamilleArticle", request.getFamilleArticle()) ;
			     	 

			      
			      

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();

			    	sousFamille.setMontant(vResult.get(i)[0]);
			    	sousFamille.setAbreviation(((String)vResult.get(i)[1]).toString());
			    	
		
			    	
			    	//Object ttc= vResult.get(i)[1];
			    	//Object htaxe= vResult.get(i)[2];
			    	
			    	//client.setMontantTTC(((Double) vResult.get(i)[1]));
			    	//client.setMontantHTaxe(((Double)vResult.get(i)[2]));
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;

	}

	@Override
	public List<ResultBestElementValue> getByArticle(RechercheMulticritereMouvementChartValue request) {
		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		String whereFamille = new String("");
		
		String whereSousFamille = new String("");
		
		if(request.getFamilleArticle() != null)
		    whereFamille=" and mv.entiteStock.article.sousFamilleArtEntite.familleArticle.id=:idFamilleArticle " ;
		
		
		
		if(request.getSousFamilleArticle() != null)
			whereSousFamille=" and mv.entiteStock.article.sousFamilleArtEntite.id=:idSousFamilleArticle " ;
		
		 
		String requete =    "select sum(mv.quantiteReelle),mv.entiteStock.article.designation from MouvementEntite mv "
			      + " where mv.bonMouvement.date>=:dateDe and  mv.bonMouvement.date<=:dateA  and mv.bonMouvement.type =:typeBM and "
			     +" mv.entiteStock.article.sousFamilleArtEntite.familleArticle.typeArticle.id=:typeArticleId  "+whereFamille  +whereSousFamille
			      + "group by mv.entiteStock.article.designation order by sum(mv.quantiteReelle) desc" ;
			
		 Query vQuery = this.entityManager.createQuery(requete)
				 .setParameter("dateDe", request.getDateDe())
				 .setParameter("dateA", request.getDateA())
				 .setParameter("typeBM", request.getType()) 
				  .setParameter("typeArticleId", request.getTypeArticle())  ;
		 
			if(request.getFamilleArticle() != null)
				vQuery.setParameter("idFamilleArticle", request.getFamilleArticle())  ;
				 
					if(request.getSousFamilleArticle() != null)
						vQuery.setParameter("idSousFamilleArticle", request.getSousFamilleArticle()) ;
			     	 

			      
			      

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();

			    	sousFamille.setMontant(vResult.get(i)[0]);
			    	sousFamille.setAbreviation(((String)vResult.get(i)[1]).toString());
			    	
		
			    	
			    	//Object ttc= vResult.get(i)[1];
			    	//Object htaxe= vResult.get(i)[2];
			    	
			    	//client.setMontantTTC(((Double) vResult.get(i)[1]));
			    	//client.setMontantHTaxe(((Double)vResult.get(i)[2]));
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;
	}



	@Override
	public List<ResultBestElementValue> getActuelleByArticle(RechercheMulticritereMouvementChartValue request) {
	


		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		//Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		//Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		String whereFamille = "";
		
		
		String whereSousFamille = new String("");
	
		if(request.getFamilleArticle() != null)
		    whereFamille=" and es.article.sousFamilleArtEntite.familleArticle.id=:idFamilleArticle " ;
		


		if(request.getSousFamilleArticle() != null)
			whereSousFamille=" and es.article.sousFamilleArtEntite.id=:idSousFamilleArticle " ;
		
		
		 
		String requete =  "select sum(es.qteActuelle),es.article.designation from EntiteStockEntite es "

			     +" where es.article.sousFamilleArtEntite.familleArticle.typeArticle.id=:typeArticleId  " +whereFamille + whereSousFamille
			      + "group by es.article.designation order by sum(es.qteActuelle) desc" ;
			
		 Query vQuery = this.entityManager.createQuery(requete)
				 
				 .setParameter("typeArticleId", request.getTypeArticle()) 
		         .setParameter("idFamilleArticle", request.getFamilleArticle()) ;
		         
		         
		         if(request.getSousFamilleArticle() != null)
		        	 vQuery.setParameter("idSousFamilleArticle", request.getSousFamilleArticle()) ;

			      
			      

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();

			    	sousFamille.setMontant(vResult.get(i)[0]);
			    	sousFamille.setAbreviation(((String)vResult.get(i)[1]).toString());
			    	
		
			    	
			    	//Object ttc= vResult.get(i)[1];
			    	//Object htaxe= vResult.get(i)[2];
			    	
			    	//client.setMontantTTC(((Double) vResult.get(i)[1]));
			    	//client.setMontantHTaxe(((Double)vResult.get(i)[2]));
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;
		
		
		
	}

	@Override
	public List<ResultBestElementValue> getActuelleBySousFamille(RechercheMulticritereMouvementChartValue request) {


		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		//Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		//Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		String whereFamille = "";
	
		if(request.getFamilleArticle() != null)
		    whereFamille=" and es.article.sousFamilleArtEntite.familleArticle.id=:idFamilleArticle " ;
		

		 
		String requete =    "select sum(es.qteActuelle),es.article.sousFamilleArtEntite.designation from EntiteStockEntite es "

			     +" where es.article.sousFamilleArtEntite.familleArticle.typeArticle.id=:typeArticleId  " +whereFamille
			      + "group by es.article.sousFamilleArtEntite.designation order by sum(es.qteActuelle) desc" ;
			
		 Query vQuery = this.entityManager.createQuery(requete)
			
				
		         .setParameter("typeArticleId", request.getTypeArticle()) ;
		         
		 		if(request.getFamilleArticle() != null)
		 			vQuery.setParameter("idFamilleArticle", request.getFamilleArticle()) ;
			     	 

			      
			      

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();

			    	sousFamille.setMontant(vResult.get(i)[0]);
			    	sousFamille.setAbreviation(((String)vResult.get(i)[1]).toString());
			    	
		
			    	
			    	//Object ttc= vResult.get(i)[1];
			    	//Object htaxe= vResult.get(i)[2];
			    	
			    	//client.setMontantTTC(((Double) vResult.get(i)[1]));
			    	//client.setMontantHTaxe(((Double)vResult.get(i)[2]));
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;

	}

	@Override
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeBySousFamille(
			RechercheMulticritereMouvementChartValue request) {

		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		//Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		//Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		String whereFamille = "";
	
		if(request.getFamilleArticle() != null)
		    whereFamille=" and es.article.sousFamilleArtEntite.familleArticle.id=:idFamilleArticle " ;
		

		 
		String requete =    "select sum(es.qteActuelle),es.article.sousFamilleArtEntite.id from EntiteStockEntite es "

			     +" where es.article.sousFamilleArtEntite.familleArticle.typeArticle.id=:typeArticleId  " +whereFamille
			      + "group by es.article.sousFamilleArtEntite.id order by sum(es.qteActuelle) desc" ;
			
		 Query vQuery = this.entityManager.createQuery(requete)
			
				
		         .setParameter("typeArticleId", request.getTypeArticle()) ;
		         
		 		if(request.getFamilleArticle() != null)
		 			vQuery.setParameter("idFamilleArticle", request.getFamilleArticle()) ;
			     	 

			      
			      

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();

			    	sousFamille.setQuantiteActuelle((Double)vResult.get(i)[0]);
			    	
			    	//sousFamilleArticleId
			    	sousFamille.setId(((Long)vResult.get(i)[1]));
			    	
			    	
			    	Long sousFamilleArtilceId = ((Long)vResult.get(i)[1]) ;
			    	
			    	
			    	//calcule Qte Sortie par sous famill
			    		    	
			    	
			    	if(sousFamilleArtilceId != null) {
			    		
			    		//calcule Entree
			    		
			    		String requeteMvtEntree =    "select sum(mv.quantiteReelle)  from MouvementEntite mv "
							      + " where mv.bonMouvement.date>=:dateDe and  mv.bonMouvement.date<=:dateA  and mv.bonMouvement.type =:typeBM and "
							     +" mv.entiteStock.article.sousFamilleArtEntite.id=:idSousFamilleArticle  " ;
							 
							
						 Query vQueryMvtEntree = this.entityManager.createQuery(requeteMvtEntree)
								 .setParameter("dateDe", request.getDateDe())
								 .setParameter("dateA", request.getDateA())
								 .setParameter("typeBM", "ENTREE")
								 .setParameter("idSousFamilleArticle", sousFamilleArtilceId) ;
								
			

					       Object vResultMvtEntree = vQueryMvtEntree.getSingleResult();
					       
					       if(vResultMvtEntree != null)
					    	   sousFamille.setQuantiteEntree((Double)vResultMvtEntree);
					       else 
					    	   sousFamille.setQuantiteEntree(new Double(0));

					      
			    		
					    //calcul Sortie
					       
					   	String requeteMvtSortie =    "select sum(mv.quantiteReelle)  from MouvementEntite mv "
							      + " where mv.bonMouvement.date>=:dateDe and  mv.bonMouvement.date<=:dateA  and mv.bonMouvement.type =:typeBM and "
							     +" mv.entiteStock.article.sousFamilleArtEntite.id=:idSousFamilleArticle  " ;
							 
							
						 Query vQueryMvtSortie = this.entityManager.createQuery(requeteMvtSortie)
								 .setParameter("dateDe", request.getDateDe())
								 .setParameter("dateA", request.getDateA())
								 .setParameter("typeBM", "SORTIE")
								 .setParameter("idSousFamilleArticle", sousFamilleArtilceId) ;
								
			

					       Object vResultMvtSortie = vQueryMvtSortie.getSingleResult();
					       
					       if(vResultMvtSortie != null)
					    	   sousFamille.setQuantiteSortie((Double)vResultMvtSortie);
					       else 
					    	   sousFamille.setQuantiteSortie(new Double(0));
			    		
			    	}
			    	
				
			    	
			    
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;
	}

	@Override
	public List<ResultBestElementValue> getActuelleParRapportSortieAndEntreeByArticle(
			RechercheMulticritereMouvementChartValue request) {

		List<ResultBestElementValue> resultList=new ArrayList<ResultBestElementValue>();
		
		//Date dateDeb=new Date(request.getDateDe().getTime().getYear(), request.getDateDe().getTime().getMonth(), request.getDateDe().getTime().getDate());
	
		
		//Date dateA=new Date(request.getDateA().getTime().getYear(), request.getDateA().getTime().getMonth(), request.getDateA().getTime().getDate());
		
		String whereFamille = "";
		
		String whereSousFamille = new String("");
	
		if(request.getFamilleArticle() != null)
		    whereFamille=" and es.article.sousFamilleArtEntite.familleArticle.id=:idFamilleArticle " ;
		

		if(request.getSousFamilleArticle() != null)
			whereSousFamille=" and es.article.sousFamilleArtEntite.id=:idSousFamilleArticle " ;

		 
		String requete =    "select sum(es.qteActuelle),es.article.id from EntiteStockEntite es "

			     +" where es.article.sousFamilleArtEntite.familleArticle.typeArticle.id=:typeArticleId  " +whereFamille + whereSousFamille
			      + "group by es.article.id order by sum(es.qteActuelle) desc" ;
			
		 Query vQuery = this.entityManager.createQuery(requete)
			
				
		         .setParameter("typeArticleId", request.getTypeArticle()) ;
		         
		 		if(request.getFamilleArticle() != null)
		 			vQuery.setParameter("idFamilleArticle", request.getFamilleArticle()) ;
			     	 
		 		if(request.getSousFamilleArticle() != null)
		 			vQuery.setParameter("idSousFamilleArticle", request.getSousFamilleArticle()) ;
			     	 

			      
			      

			    List<Object[]> vResult = vQuery.getResultList();
			    
			   
			    for (int i=0;i<vResult.size();i++) {
			    	
			    	
			    	ResultBestElementValue sousFamille=new ResultBestElementValue();

			    	sousFamille.setQuantiteActuelle((Double)vResult.get(i)[0]);
			    	
			    	//sousFamilleArticleId
			    	sousFamille.setId(((Long)vResult.get(i)[1]));
			    	
			    	
			    	Long artilceId = ((Long)vResult.get(i)[1]) ;
			    	
			    	
			    	//calcule Qte Sortie par sous famill
			    		    	
			    	
			    	if(artilceId != null) {
			    		
			    		//calcule Entree
			    		
			    		String requeteMvtEntree =    "select sum(mv.quantiteReelle)  from MouvementEntite mv "
							      + " where mv.bonMouvement.date>=:dateDe and  mv.bonMouvement.date<=:dateA  and mv.bonMouvement.type =:typeBM and "
							     +" mv.entiteStock.article.id=:idArticle  " ;
							 
							
						 Query vQueryMvtEntree = this.entityManager.createQuery(requeteMvtEntree)
								 .setParameter("dateDe", request.getDateDe())
								 .setParameter("dateA", request.getDateA())
								 .setParameter("typeBM", "ENTREE")
								 .setParameter("idArticle", artilceId) ;
								
			

					       Object vResultMvtEntree = vQueryMvtEntree.getSingleResult();
					       
					       if(vResultMvtEntree != null)
					    	   sousFamille.setQuantiteEntree((Double)vResultMvtEntree);
					       else 
					    	   sousFamille.setQuantiteEntree(new Double(0));

					      
			    		
					    //calcul Sortie
					       
					   	String requeteMvtSortie =    "select sum(mv.quantiteReelle)  from MouvementEntite mv "
							      + " where mv.bonMouvement.date>=:dateDe and  mv.bonMouvement.date<=:dateA  and mv.bonMouvement.type =:typeBM and "
							     +" mv.entiteStock.article.id=:idArticle  " ;
							 
							
						 Query vQueryMvtSortie = this.entityManager.createQuery(requeteMvtSortie)
								 .setParameter("dateDe", request.getDateDe())
								 .setParameter("dateA", request.getDateA())
								 .setParameter("typeBM", "SORTIE")
								 .setParameter("idArticle", artilceId) ;
								
			

					       Object vResultMvtSortie = vQueryMvtSortie.getSingleResult();
					       
					       if(vResultMvtSortie != null)
					    	   sousFamille.setQuantiteSortie((Double)vResultMvtSortie);
					       else 
					    	   sousFamille.setQuantiteSortie(new Double(0));
			    		
			    	}
			    	
				
			    	
			    
			    	
			    	resultList.add(sousFamille);

			    }
		return resultList;
		
		
	}
	
}
