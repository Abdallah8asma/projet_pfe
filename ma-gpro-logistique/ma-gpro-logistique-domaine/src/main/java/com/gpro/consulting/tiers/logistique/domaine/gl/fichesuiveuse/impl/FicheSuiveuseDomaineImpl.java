package com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IArticlePersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.bonReception.value.TraitementValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementControleValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ElementRecetteMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.FicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.RechercheMulticritereFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElemCoutValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gl.fichesuiveuse.value.ResultatRechecheFicheSuiveuseValue;
import com.gpro.consulting.tiers.logistique.coordination.gs.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.bonReception.ITraitementDomaine;
import com.gpro.consulting.tiers.logistique.domaine.gl.fichesuiveuse.IFicheSuiveuseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.gl.fichesuiveuse.IFicheSuiveusePersistance;
import com.gpro.consulting.tiers.logistique.persistance.gs.IBonMouvementPersistance;

/**
 * implementation of {@link IFicheSuiveuseDomaine}
 * 
 * @author Wahid Gazzah
 * @since 28/03/2016
 *
 */

@Component
public class FicheSuiveuseDomaineImpl implements IFicheSuiveuseDomaine{

	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveuseDomaineImpl.class);
	
	private static final Double ZERO = 0D;
	
	private static final String METHODE_POIDS = "poids";
	private static final String METHODE_VOLUME = "volume";
	private static final String METHODE_DIRECT = "direct";
	
	private static final String METHODE_POIDS_PES = "poidsPES";
	private static final String METHODE_VOLUME_PES = "volumePES";
	
	private static final String METHODE_POIDS_COTON = "poidsCoton";
	private static final String METHODE_VOLUME_COTON = "volumeCoton";
	
	@Autowired
	private IFicheSuiveusePersistance ficheSuiveusePersistance;
	
	@Autowired
	private ITraitementDomaine traitementDomaine;
	
	@Autowired
	private IArticlePersistance articlePersistance;

	@Autowired
	private IBonMouvementPersistance bonMouvementPersistance;

	
	
	@Override
	public ResultatRechecheFicheSuiveuseValue rechercherMultiCritere(
			RechercheMulticritereFicheSuiveuseValue request) {
		
		//logger.info("rechercheMulticritere: Delegating request to Persistance layer.");
		
		return ficheSuiveusePersistance.rechercherMultiCritere(request);
	}

	@Override
	public String create(FicheSuiveuseValue ficheSuiveuseValue) {
		
		if(ficheSuiveuseValue != null){
			
			for(TraitementMiseValue traitementMise : ficheSuiveuseValue.getListeTraitementsMise()){
				
				if(traitementMise.getListElementRecetteMise() != null){
					
					for(ElementRecetteMiseValue elementRecetteMise : traitementMise.getListElementRecetteMise()){
						
						Double poids = ZERO;
						
						if(validString(elementRecetteMise.getMethode())) {
							
//	logger.info("elementRecetteMise.methode: "+elementRecetteMise.getMethode());

							switch(elementRecetteMise.getMethode()) {
							
						        case METHODE_POIDS_PES:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getPoidsPES())){
											
										poids = (ficheSuiveuseValue.getPoidsPES() * elementRecetteMise.getPourcentage()) / 100;
									}
						        	break;
						        	
								case METHODE_POIDS_COTON:

									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getPoidsCoton())){
											
										poids = (ficheSuiveuseValue.getPoidsCoton() * elementRecetteMise.getPourcentage()) / 100;
									}
									break;
									
								case METHODE_VOLUME_PES:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getVolumePES())){
										
										poids = ((ficheSuiveuseValue.getVolumePES()/1000) * (elementRecetteMise.getPourcentage()));
									}
									break;
									
								case METHODE_VOLUME_COTON:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getVolumeCoton())){
										
										poids = ((ficheSuiveuseValue.getVolumeCoton()/1000) * elementRecetteMise.getPourcentage());
									}
									break;
	
								case METHODE_VOLUME:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getVolume())){
										
										poids = ((ficheSuiveuseValue.getVolume()/1000) * elementRecetteMise.getPourcentage());
									}
									break;
									
								case METHODE_POIDS:

									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getPoids())){
											
										poids = (ficheSuiveuseValue.getPoids() * elementRecetteMise.getPourcentage()) / 100;
									}
									break;
									
								case METHODE_DIRECT:
									
									poids = elementRecetteMise.getPoids();
									
									break;
								
								default:
									break;
							
							}

						}
						
						elementRecetteMise.setPoids(poids);
					}
					
				}
				TraitementValue traitementValue;
				traitementValue= traitementDomaine.getTraitementParId(traitementMise.getTraitementId());
				if(traitementValue.getPU() != null){
					traitementMise.setPu(traitementValue.getPU());
				}
				
			}
		}
		
		
		
		//logger.info("createFicheSuiveuse: Delegating request to Persistance layer.");
		//System.out.println("----createFicheSuiveuse : before sous ficheSuiveuseValue----"+ficheSuiveuseValue);

		//soutraction Stock
		
//		if (ficheSuiveuseValue.getReferenceMise()!=null) {
//			
//		
//		BonMouvementStockValue bonMouvementStockValue = bonMouvementPersistance.rechercheBonMouvementParNum(ficheSuiveuseValue.getReferenceMise());
//		
//		if (bonMouvementStockValue.isOrigineFSuiveuse()) {
//			
//			System.out.println("---createFicheSuiveuse: isOrigineFSuiveuse :isOrigineFSuiveuse"+bonMouvementStockValue.isOrigineFSuiveuse());
//
//			ficheSuiveuseValue.setSoustractionEffectuee(true);
//			System.out.println("createFicheSuiveuse: setSoustractionEffectuee"+ficheSuiveuseValue.isSoustractionEffectuee());
//
//		}
//		
//		}
		
		return ficheSuiveusePersistance.create(ficheSuiveuseValue);
	}

	@Override
	public String update(FicheSuiveuseValue ficheSuiveuseValue) {
		
		if(ficheSuiveuseValue != null){
			
			for(TraitementMiseValue traitementMise : ficheSuiveuseValue.getListeTraitementsMise()){
				
				if(traitementMise.getListElementRecetteMise() != null){
					
					for(ElementRecetteMiseValue elementRecetteMise : traitementMise.getListElementRecetteMise()){
						
						Double poids = ZERO;
						
						if(validString(elementRecetteMise.getMethode())) {
							
//							logger.info("elementRecetteMise.methode: "+elementRecetteMise.getMethode());

							switch(elementRecetteMise.getMethode()) {
							
						        case METHODE_POIDS_PES:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getPoidsPES())){
											
										poids = (ficheSuiveuseValue.getPoidsPES() * elementRecetteMise.getPourcentage()) / 100;
									}
						        	break;
						        	
								case METHODE_POIDS_COTON:

									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getPoidsCoton())){
											
										poids = (ficheSuiveuseValue.getPoidsCoton() * elementRecetteMise.getPourcentage()) / 100;
									}
									break;
									
								case METHODE_VOLUME_PES:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getVolumePES())){
										
										poids = ((ficheSuiveuseValue.getVolumePES()/1000) * elementRecetteMise.getPourcentage());
									}
									break;
									
								case METHODE_VOLUME_COTON:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getVolumeCoton())){
										
										poids = ((ficheSuiveuseValue.getVolumeCoton()/1000) * elementRecetteMise.getPourcentage());
									}
									break;
	
								case METHODE_VOLUME:
									
									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getVolume())){
										
										poids = ((ficheSuiveuseValue.getVolume()/1000) * elementRecetteMise.getPourcentage());
									}
									break;
									
								case METHODE_POIDS:

									if(validDoubles(elementRecetteMise.getPourcentage(), ficheSuiveuseValue.getPoids())){
											
										poids = (ficheSuiveuseValue.getPoids() * elementRecetteMise.getPourcentage()) / 100;
									}
									break;
									
								case METHODE_DIRECT:
									
									poids = elementRecetteMise.getPoids();
									
									break;
								
								default:
									break;
							
							}

						}
						
						elementRecetteMise.setPoids(poids);
					}					
				}
			
				TraitementValue traitementValue;
				traitementValue= traitementDomaine.getTraitementParId(traitementMise.getTraitementId());
				if(traitementValue.getPU() != null){
					traitementMise.setPu(traitementValue.getPU());
				}
			}
		}
		
		//logger.info("updateFicheSuiveuse: Delegating request to Persistance layer.");
		
		return ficheSuiveusePersistance.update(ficheSuiveuseValue);
	}
	
	@Override
	public FicheSuiveuseValue getById(Long id) {
		
		//logger.info("getFicheSuiveuseById: Delegating request id {} to Persistance layer.", id);
		
		FicheSuiveuseValue ficheSuiveuse = ficheSuiveusePersistance.getById(id);
		
		if(ficheSuiveuse != null){
			
			List<ElementControleValue> listElementsControle = new ArrayList<ElementControleValue>();
			List<TraitementMiseValue> listeTraitementsMise = new ArrayList<TraitementMiseValue>();
			
			for(ElementControleValue element : ficheSuiveuse.getListElementControle()){
				listElementsControle.add(element);
			}
			
			for(TraitementMiseValue traitementMise : ficheSuiveuse.getListeTraitementsMise()){
				
				List<ElementRecetteMiseValue> listElementsRecetteMise = new ArrayList<ElementRecetteMiseValue>();
				
				for(ElementRecetteMiseValue element : traitementMise.getListElementRecetteMise()){
					
					listElementsRecetteMise.add(element);
				}
				
				Collections.sort(listElementsRecetteMise);
				traitementMise.setListElementRecetteMise(new TreeSet<>(listElementsRecetteMise));
				listeTraitementsMise.add(traitementMise);
			}
			
			Collections.sort(listElementsControle);
			ficheSuiveuse.setListElementControle(new TreeSet<>(listElementsControle));
			ficheSuiveuse.setListeTraitementsMise(listeTraitementsMise);
			
		}
		
		return ficheSuiveuse;
	}

	@Override
	public void delete(Long id) {
		
		//logger.info("deleteFicheSuiveuse: Delegating request id {} to Persistance layer.", id);
		
		//Added on 01/02/2017 by Hajer AMRI
		FicheSuiveuseValue ficheSuiveuseValue = ficheSuiveusePersistance.getById(id);
		
		//System.out.println("---delete : ficheSuiveuseValue byId----"+ficheSuiveuseValue);
		
//		if (ficheSuiveuseValue.isSoustractionEffectuee()) {
			
			BonMouvementStockValue bonMouvementStockValue =  bonMouvementPersistance.rechercheBonMouvementParNum(ficheSuiveuseValue.getReferenceMise());
	   
			//System.out.println("---delete : ficheSuiveuseValue bonMouvementStockValueParNum----"+bonMouvementStockValue);
			
				if (bonMouvementStockValue!= null ) {
					
						bonMouvementPersistance.supprimerBonMouvement(bonMouvementStockValue.getId());
				}
//		}
		
		ficheSuiveusePersistance.delete(id);
	}

	@Override
	public List<FicheSuiveuseValue> getAll() {
		
		return ficheSuiveusePersistance.getAll();
	}
	
	
	private boolean validString(String val) {
		return val != null && !"".equals(val);
	}
	
	private boolean validDoubles(Double val1, Double val2) {
		return val1 != null && val2 != null;
	}

	@SuppressWarnings("null")
	@Override
	public ResultatRechecheFicheSuiveuseCoutValue rechercherMultiCritereAvecCout(
			RechercheMulticritereFicheSuiveuseValue request) {
		
		//logger.info("rechercheMulticritere avec cout : request \n "+ request.toString()+ "\n");
		
		//Récupérer le résultat de recherche 
		ResultatRechecheFicheSuiveuseValue result =  ficheSuiveusePersistance.rechercherMultiCritere(request);

		//logger.info("---- résultat rechercheMulticritere avec cout : size "+ result.getList().size());
		//logger.info("---- résultat rechercheMulticritere avec cout : list "+ result.getList().toString());
		
		//Remplir le valeur de resultatRecherche relative au cout à partir du premier résultat
		ResultatRechecheFicheSuiveuseCoutValue finalResult= new ResultatRechecheFicheSuiveuseCoutValue();
		
		//Remplir le nombre de résultats
		finalResult.setNombreResultaRechercher(result.getNombreResultaRechercher());
		
		
		Set<ResultatRechecheFicheSuiveuseElemCoutValue> resultList =  new TreeSet <ResultatRechecheFicheSuiveuseElemCoutValue>(); 
		
		Double coutRecette = 0.0;
		Double coutTotalTraitements = 0.0;
		
//		logger.info("\n ---- Domaine length list result primaire------- "+ result.getList().size());
		
		//Remplir les élements de la liste
		for (ResultatRechecheFicheSuiveuseElementValue element : result.getList()) {
			
			ResultatRechecheFicheSuiveuseElemCoutValue elementCout = new ResultatRechecheFicheSuiveuseElemCoutValue();
			
			//Affectation du résultat courant
			
		//if(element.getId()!= null){
			elementCout.setId(element.getId());			
			elementCout.setReferenceMise(element.getReferenceMise());
			elementCout.setProduitId(element.getProduitId());
			elementCout.setPartieIntId(element.getPartieIntId());
			elementCout.setDateLivraison(element.getDateLivraison());
			elementCout.setDateLancement(element.getDateLancement());
			elementCout.setTypeLivraison(element.getTypeLivraison());
			elementCout.setPoidSortie(element.getPoidSortie());
			elementCout.setLaizeFini(element.getLaizeFini());
			elementCout.setProduitReference(element.getProduitReference());
			elementCout.setProduitDesignation(element.getProduitDesignation());
			elementCout.setClientReference(element.getClientReference());
			elementCout.setClientAbreviation(element.getClientAbreviation());
		
			
			//logger.info("---- elementCout resultat recherche---- \n"+ elementCout.toString());
			
			// Enrichissement avec calcul des couts
			
			
			//Calcul coutRecette et coutTraitement
			List<Double> resultatCalcul = this.calculCouts(elementCout);
			
			if(resultatCalcul.size()>0){
				
				//Affectation cout de la recette
				coutRecette = resultatCalcul.get(0);
				
				//Affectation cout du traitement
				coutTotalTraitements = resultatCalcul.get(1);	
			}
			
				
					
			
			//Affectation des couts à l'element ficheSuiveuse
			elementCout.setCoutRecette(coutRecette);
			elementCout.setCoutTraitement(coutTotalTraitements);
			elementCout.setCoutTotal(coutTotalTraitements+coutRecette);
			
			//Affecter l'element ficheSuiveuse à la liste des fiches suiveuses (Résultat de recherche)
			//logger.info("\n------- DOMAINE : elementCout apres calcul------- \n "+ elementCout.toString()+ "\n");
			resultList.add(elementCout);
		//}
	   }
		
		//logger.info("\n------- DOMAINE : resultList------- \n "+ resultList.toString()+ "\n");
		
//		logger.info("\n------- DOMAINE : Length liste resultat couts------- \n "+ resultList.size()+ "\n");
		
		finalResult.setList(resultList);
		
		return finalResult;
	}


	// Calcul coutRecette + coutTraitement
	
	private List<Double> calculCouts(ResultatRechecheFicheSuiveuseElemCoutValue elementFicheSuiveuseAvecCout){
		Double coutTotalTraitements= 0.0;
		Double coutRecette = 0.0;
		List<Double> resultat = new ArrayList<Double>();
		
		FicheSuiveuseValue ficheSuiveuse = ficheSuiveusePersistance.getById(elementFicheSuiveuseAvecCout.getId());
		List<TraitementMiseValue> traitementMiseList = ficheSuiveuse.getListeTraitementsMise();
		
		resultat.add(coutRecette); // Premier element : coutRecette = 0.0
		resultat.add(coutTotalTraitements); // Deuxieme element : coutTraitement = 0.0
		
		if(traitementMiseList.size()>0){
			for (TraitementMiseValue traitementMiseValue : traitementMiseList) {
				
				//Calcul coutRecette
				for (ElementRecetteMiseValue elementRecetteMiseValue : traitementMiseValue.getListElementRecetteMise()) {
					if(elementRecetteMiseValue.getArticleId() != null){
						ArticleValue articleValue= articlePersistance.getArticleParId(elementRecetteMiseValue.getArticleId());
						if(articleValue.getPu()!= null && elementRecetteMiseValue.getPoids()!= null){
							coutRecette+=articleValue.getPu()* elementRecetteMiseValue.getPoids();
						}					
					}
					
				}
				
				//Calcul coutTraitement
				
				if (traitementMiseValue.getPu() != null){
					coutTotalTraitements+= traitementMiseValue.getPu() * elementFicheSuiveuseAvecCout.getPoidSortie();
				}
					
				//Affectation du resultat
				resultat.set(0, coutRecette); // Premier element : coutRecette
				resultat.set(1, coutTotalTraitements); // Deuxieme element : coutTraitement
			}
		}
		
		return resultat;
	}
	
	
	
}
