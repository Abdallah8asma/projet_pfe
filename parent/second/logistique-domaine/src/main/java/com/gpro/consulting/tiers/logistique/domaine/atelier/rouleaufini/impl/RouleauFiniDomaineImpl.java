package com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gpro.consulting.tiers.logistique.coordination.atelier.IConstanteLogistique;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.CritereRechercheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ElementResultatRechecheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.GuichetRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RechercheMulticritereRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauFiniValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ResultatRechecheRouleauStandardValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.RouleauFiniValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IGuichetRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.rouleaufini.IRouleauFiniDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IMisePersistance;
import com.gpro.consulting.tiers.logistique.persistance.atelier.rouleaufini.IRouleauFiniPersistance;

/**
 * Implementation of {@link IRouleauFiniDomaine}
 * 
 * @author Wahid Gazzah
 * @since 11/12/2015
 *
 */

@Component
public class RouleauFiniDomaineImpl implements IRouleauFiniDomaine{
	
	private static final Double SOMME_ZERO=0D;
	private static final Long ZERO = 0L;

	@Autowired
	private IRouleauFiniPersistance rouleauFiniPersitance;

	/** Service Guichet */
	@Autowired
	private IGuichetRouleauFiniDomaine guichetRouleauFiniDomaine;
	
	
	@Autowired
	private IMisePersistance misePersitance;
	
	@Override
	public ResultatRechecheRouleauFiniValue rechercherMultiCritere(RechercheMulticritereRouleauFiniValue request) {
		
		return rouleauFiniPersitance.rechercherMultiCritere(request);
	}
	
	@Override
	public ResultatRechecheRouleauFiniValue rechercherMultiCritereStandard(RechercheMulticritereRouleauFiniStandardValue request) {
		
		return rouleauFiniPersitance.rechercherMultiCritereStandard(request);
	}

	@Override
	public RouleauFiniValue getRouleauFiniById(Long id) {
		
		return rouleauFiniPersitance.getRouleauFiniById(id);
	}

	@Override
	public String updateRouleauFini(RouleauFiniValue request) {
		
		//Recherche de Mise et mise à jour poids fini
				List<MiseValue> listMise= misePersitance.getMiseByReference(request.getReferenceMise());
				
				MiseValue miseValue= listMise.get(0);
				
								
				miseValue.setQteProduite(miseValue.getQteProduite() - request.getMetrageAncien().longValue() + request.getMetrage().longValue());
				
				misePersitance.modifierMise(miseValue);
		
		
		
		return rouleauFiniPersitance.updateRouleauFini(request);
	}

	@Override
	public void deleteRouleauFini(Long id) {
		
		RouleauFiniValue rouleau = rouleauFiniPersitance.getRouleauFiniById(id);
		
		
		if(rouleau.getBonSortie() == null) {
			
			
			List<MiseValue> listMise= misePersitance.getMiseByReference(rouleau.getReferenceMise());
			
			if(listMise.size() > 0) {
				
				MiseValue miseValue= listMise.get(0);
				
				miseValue.setQteProduite(miseValue.getQteProduite() - rouleau.getMetrageAncien().longValue());
				
				miseValue.setNbrColis(miseValue.getNbrColis()  -1 );
				
				misePersitance.modifierMise(miseValue);
				
			}
			
			
			
			rouleauFiniPersitance.deleteRouleauFini(id);
			
		}
		
	
	}

	@Override
	public String createRouleauFini(RouleauFiniValue request) {
		
		List<String> ids = new ArrayList<String>();
	
		
		if(request.getChoix() == null)
			request.setChoix(IConstanteLogistique.CHOIX_ROULEAU_FINI_1);
		
		request.setDateIntroduction(Calendar.getInstance());
		
		if(request.getNumberOfBox() == null)
			request.setNumberOfBox(1l);
		
		
		
		
		for(int i = 0 ; i< request.getNumberOfBox().intValue() ; i++) {
			
			request.setReference(getNumero(Calendar.getInstance()));
			 // LOGGER.info("A--RefBR "+  request.getReference());
			
			
		
			
			//request.setPremierMetrage(request.getMetrage());
			
			//Recherche de Mise et mise à jour poids fini
			List<MiseValue> listMise= misePersitance.getMiseByReference(request.getReferenceMise());
			
			MiseValue miseValue= listMise.get(0);
			
			
			if(miseValue.getDateDebutProduction() == null) {
				miseValue.setDateDebutProduction(Calendar.getInstance());
				miseValue.setStatut("En cours");
				
				
			}
				
			
			
			Long qteProduite=miseValue.getQteProduite();
			Long nbrColis=miseValue.getNbrColis();
			nbrColis ++;
			
			if(request.getMetrage()!=null && qteProduite!=null )
				qteProduite+=request.getMetrage().longValue();
			
			//TODO modifier par samer le 28.06.19 afin d'utiliser le poidsFini comme étant quantite par box
			//miseValue.setPoidFini(poidsFini);
			
			miseValue.setQteProduite(qteProduite);
			miseValue.setNbrColis(nbrColis);
			
			
			if(miseValue.getQteProduite() >=  miseValue.getQuantite().longValue())
			{
				if(miseValue.getDateFinProduction() == null)
				miseValue.setDateFinProduction(Calendar.getInstance());
				
				
				
				miseValue.setStatut("Produit");
			}
			
			
			misePersitance.modifierMise(miseValue);
			
			
		
			
			     String idRouleau =  rouleauFiniPersitance.createRouleauFini(request);
			
			         ids.add(idRouleau);
			
			
		}
		
		
		
		
		return org.apache.commons.lang3.StringUtils.join(ids,",") ;
	
		
		
		
	
		//LOGGER.info("B--RefBR "+  request.getReference());
		
	}
	/**
	   * Récuperer le numéro du Bon de receptionà partir du Guichet Bon Reception
	   * 
	   * Le Numéro Bon Reception est unique pour une Bon Reception OSIRIS et doit respect le format:
	   * 
	   * - AAAA : année du Bon Reception. - NNNNNN : un numéro incrémental au sein de l'année civile.
	   * 
	   * @param pDateMise
	   *          la date de la Bon Reception
	   * @return le numéro du prochaine Bon Reception à insérer
	   */
	private String getNumero(final Calendar pDateMise) {

		Long vNumGuichetMise = this.guichetRouleauFiniDomaine.getNextNumReference();
		
		Integer vPrefixe= this.guichetRouleauFiniDomaine.getPrefixe();
		
		
	    /** Année courante. */
	    int vAnneeCourante = pDateMise.get(Calendar.YEAR);
	    /** Format du numero de la Bon Reception= AAAA-NNNNNN. */
	    StringBuilder vNumMise = new StringBuilder("");
        //TODO A changer 
	    vNumMise.append(vPrefixe);
	   // vNumMise.append("-");
	    vNumMise.append(String.format("%06d", vNumGuichetMise));
	    /** Inserer une nouvelle valeur dans Guichet Mise. */
	    GuichetRouleauFiniValue vGuichetValeur = new GuichetRouleauFiniValue();
	    vGuichetValeur.setAnnee(new Long(vAnneeCourante));
	    vGuichetValeur.setNumReferenceCourant(new Long(vNumGuichetMise + 1L));

	    /** Modification de la valeur en base du numéro. */
	    this.guichetRouleauFiniDomaine.modifierGuichetBonReception(vGuichetValeur);

	    return vNumMise.toString();

	  }
	  
	  /**
	   * Recherche multicritere pour l'inventaire
	   *  (non-Javadoc)
	   * @see com.gpro.consulting.tiers.logistique.domaine.rouleaufini.IRouleauFiniDomaine#rechercherRouleauInventaire(com.gpro.consulting.tiers.logistique.coordination.rouleaufini.value.CritereRechercheRouleauStandardValue)
	   */
	@Override
	public ResultatRechecheRouleauStandardValue rechercherRouleauInventaire(CritereRechercheRouleauStandardValue pCritereRechecheRouleau) {
		
		
	    /** Récupéeration de la liste des rouleaux */
	    List < RouleauFiniValue > listeRouleauFini=rouleauFiniPersitance.rechercherMultiCritereRouleauFiniStandard(pCritereRechecheRouleau);
	    
	    
	    /** Grouper par produit id */
	    Map<Long, List<RouleauFiniValue>> mapRouleau = new HashMap<Long, List<RouleauFiniValue>>();
	    
	    Map<Long, List<String>> mapRefMise = new HashMap<Long, List<String>>();
	    
	    for (RouleauFiniValue rouleau : listeRouleauFini){
	    	Long key = rouleau.getProduitId();
	    	if (mapRouleau.get(key) == null) {
	    		mapRouleau.put(key, new ArrayList<RouleauFiniValue>());
	    		mapRefMise.put(key, new ArrayList<String>());
	    	}
	    	mapRouleau.get(key).add(rouleau);
	    	mapRefMise.get(key).add(rouleau.getReferenceMise());
	    }
	    
	    /** Construction et alimentation du résultat de recherche Par les critères de recheche 
	     * Nombre Colie Entre et  Nombre Colis a
	     * 
	     * Metrage entre et Metrage a 
	     * */
	    Iterator it = mapRouleau.entrySet().iterator();
	    Iterator itRefMise = mapRefMise.entrySet().iterator();
	    List < ElementResultatRechecheRouleauStandardValue > listRapport=new ArrayList<>();
	    while (it.hasNext() && itRefMise.hasNext()) {
	    	
			// Création d'un nouveau Elément
			ElementResultatRechecheRouleauStandardValue vElement = new ElementResultatRechecheRouleauStandardValue();

			Map.Entry<Long, List<RouleauFiniValue>> pair = (Map.Entry<Long, List<RouleauFiniValue>>) it.next();

			Map.Entry<Long, List<String>> pairRefMise = (Map.Entry<Long, List<String>>) itRefMise.next();

			/** Enrichissement d'un element */
			// Produit
			vElement.setIdProduit(pair.getKey());
			Double sommeMetrage = SOMME_ZERO;
			Double sommePoid = SOMME_ZERO;
			Long nombreMise = ZERO;
			for(RouleauFiniValue rouleau: pair.getValue()){
				//Metrage
				if(rouleau.getMetrage()!=null){
					sommeMetrage=rouleau.getMetrage().doubleValue()+sommeMetrage;
				}
				//Somme des poids
				if(rouleau.getPoids()!=null){
					sommePoid=rouleau.getPoids().doubleValue()+ sommePoid;
				}
	      }
	      vElement.setMetrage(sommeMetrage);
	      vElement.setPoids(sommePoid);
	      //Nombre de colis
	      vElement.setNombreColis(Long.valueOf(pair.getValue().size()));
	      	   
	      //Nombre des mises
	      Set<String> refMiseSet = new HashSet<String>(pairRefMise.getValue());
	      nombreMise = Long.valueOf(refMiseSet.size());
	      vElement.setNombreMise(nombreMise);
	      
	      listRapport.add(vElement);
	      it.remove(); 
	      itRefMise.remove(); 
	  }

	    /** Controle sur les critères de recherche */

	    ResultatRechecheRouleauStandardValue vResultat=new ResultatRechecheRouleauStandardValue();
	    
	    vResultat.setListeElementResultatRechecheRouleauStandardValue(listRapport);
	    
	    return vResultat;
	  }

	@Override
	public ResultatRechecheRouleauFiniValue getRouleauFiniByInfoModif(String infoModif) {
		
		return rouleauFiniPersitance.getRouleauFiniByInfoModif(infoModif);
	}

	@Override
	public List<String> getListRefMiseRouleauNonSortie() {
		return rouleauFiniPersitance.getListRefMiseRouleauNonSortie();
	}

	@Override
	public List<String> getListCodeBarreByRefMiseAndIdBSisNull(String refMise) {
		return rouleauFiniPersitance.getListCodeBarreByRefMiseAndIdBSisNull(refMise);
	}

	@Override
	public Double getQteExpedierByMiseRef(String refMise) {
		// TODO Auto-generated method stub
		return rouleauFiniPersitance.getQteExpedierByMiseRef(refMise);
	}

	@Override
	public Long getNbrColisExpedierByMiseRef(String refMise) {
		// TODO Auto-generated method stub
		return rouleauFiniPersitance.getNbrColisExpedierByMiseRef(refMise);
	}

	@Override
	public ResultatRechecheRouleauStandardValue rechercherRouleauInventaireByOF(
			CritereRechercheRouleauStandardValue pCritereRechecheRouleau) {

		
	    /** Récupéeration de la liste des rouleaux */
	    List < RouleauFiniValue > listeRouleauFini=rouleauFiniPersitance.rechercherMultiCritereRouleauFiniStandard(pCritereRechecheRouleau);
	    
	    
	    /** Grouper par produit id */
	    Map<String, List<RouleauFiniValue>> mapRouleau = new HashMap<String, List<RouleauFiniValue>>();
	    
	  //  Map<Long, List<String>> mapRefMise = new HashMap<Long, List<String>>();
	    
	    for (RouleauFiniValue rouleau : listeRouleauFini){
	    	//Long key = rouleau.getProduitId();
	    	
	    	String key = rouleau.getReferenceMise();
	    	
	    	if (mapRouleau.get(key) == null) {
	    		mapRouleau.put(key, new ArrayList<RouleauFiniValue>());
	    	//	mapRefMise.put(key, new ArrayList<String>());
	    	}
	    	mapRouleau.get(key).add(rouleau);
	    
	    	//mapRefMise.get(key).add(rouleau.getReferenceMise());
	    }
	    
	    /** Construction et alimentation du résultat de recherche Par les critères de recheche 
	     * Nombre Colie Entre et  Nombre Colis a
	     * 
	     * Metrage entre et Metrage a 
	     * */
	    Iterator it = mapRouleau.entrySet().iterator();
	  
	    
	    
	    //Iterator itRefMise = mapRefMise.entrySet().iterator();
	    List < ElementResultatRechecheRouleauStandardValue > listRapport=new ArrayList<>();
	   // while (it.hasNext() && itRefMise.hasNext()) {
	    	
		    while (it.hasNext() ) {
	    	
			// Création d'un nouveau Elément
			ElementResultatRechecheRouleauStandardValue vElement = new ElementResultatRechecheRouleauStandardValue();

			Map.Entry<String, List<RouleauFiniValue>> pair = (Map.Entry<String, List<RouleauFiniValue>>) it.next();

		//	Map.Entry<Long, List<String>> pairRefMise = (Map.Entry<Long, List<String>>) itRefMise.next();

			/** Enrichissement d'un element */
			// Produit
		//	vElement.setIdProduit(pair.getKey());
			
			
			
			vElement.setReferenceMise(pair.getKey());
			
			Double sommeMetrage = SOMME_ZERO;
			Double sommePoid = SOMME_ZERO;
			Long nombreMise = ZERO;
			for(RouleauFiniValue rouleau: pair.getValue()){
				//Metrage
				if(rouleau.getMetrage()!=null){
					sommeMetrage=rouleau.getMetrage().doubleValue()+sommeMetrage;
				}
				//Somme des poids
				if(rouleau.getPoids()!=null){
					sommePoid=rouleau.getPoids().doubleValue()+ sommePoid;
				}
				
				vElement.setIdProduit(rouleau.getProduitId());
	      }
	      vElement.setMetrage(sommeMetrage);
	      vElement.setPoids(sommePoid);
	      //Nombre de colis
	      vElement.setNombreColis(Long.valueOf(pair.getValue().size()));
	      	   
	      //Nombre des mises
	 //     Set<String> refMiseSet = new HashSet<String>(pairRefMise.getValue());
	 //     nombreMise = Long.valueOf(refMiseSet.size());
	      vElement.setNombreMise(nombreMise);
	      
	      listRapport.add(vElement);
	      it.remove(); 
	//      itRefMise.remove(); 
	  }

	    /** Controle sur les critères de recherche */

	    ResultatRechecheRouleauStandardValue vResultat=new ResultatRechecheRouleauStandardValue();
	    
	    vResultat.setListeElementResultatRechecheRouleauStandardValue(listRapport);
	    
	    return vResultat;
	}
	  
}
