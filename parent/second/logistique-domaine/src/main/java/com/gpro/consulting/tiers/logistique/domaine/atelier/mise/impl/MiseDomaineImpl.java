package com.gpro.consulting.tiers.logistique.domaine.atelier.mise.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.elementBase.IProduitPersistance;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.GuichetMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.MiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.RechercheMulticritereMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.ResultatRechercheMiseValue;
import com.gpro.consulting.tiers.logistique.coordination.atelier.mise.value.TraitementMiseValue;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IGuichetMiseDomaine;
import com.gpro.consulting.tiers.logistique.domaine.atelier.mise.IMiseDomaine;
import com.gpro.consulting.tiers.logistique.persistance.atelier.mise.IMisePersistance;

/**
 * Implémentation des méthodes CRUD du Bon de reception
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */

@Component
public class MiseDomaineImpl implements IMiseDomaine {
	
	@Autowired
	private IMisePersistance vMisePersitance;
	
	@Autowired
	private IGuichetMiseDomaine guichetMiseDomaine;
	
	@Autowired
	private IProduitPersistance produitPersistence;

	private static final Logger LOGGER = LoggerFactory.getLogger(MiseDomaineImpl.class);

	@Override
	public String creerMise(MiseValue dto) {

		// Set fini attribute to TRUE if the Client is STIT, the STIT id is
		// fixed to 1
		/*
		if (dto.getPartieintId().equals(IConstanteLogistique.STIT_CLIENT_ID)) {
			dto.setFini(true);
		} else {
			dto.setFini(false);
		}
		 */
		
		
		dto.setNbrColis(0l);
		dto.setQteProduite(0l);
		
		dto.setQteExpedition(0d);
		dto.setNbrColisExpedition(0l);
		
		
		if (dto.getReference() == null || dto.getReference().equals("")) {
			dto.setReference(getNumeroMise(Calendar.getInstance()));

		}
		
		
//		if (dto.isFini() != null && dto.isFini().equals(true)){
//			/* ref = S+####+/+Annee */
//			if(dto.getReference()==null || dto.getReference().equals("")){
//				dto.setReference(getNumeroMise(Calendar.getInstance()));
//			      
//			}
//		}
		dto.setDateIntroduction(Calendar.getInstance());
		
		
		 if (dto.getStatut() != null && dto.getStatut().equals("En cours"))
        	 dto.setDateDebutProduction(Calendar.getInstance());

		return vMisePersitance.creerMise(dto);
	}

	@Override
	public void supprimerMise(Long pId) {
		vMisePersitance.supprimerMise(pId);
	}

	@Override
	public String modifierMise(MiseValue dto) {

		// Set fini attribute to TRUE if the Client is STIT, the STIT id is
		// fixed to 1
		/*
		if (dto.getPartieintId().equals(IConstanteLogistique.STIT_CLIENT_ID)) {
			dto.setFini(true);
		} else {
			dto.setFini(false);
		}
		 */
		
		   if (dto.getStatut() != null && dto.getStatut().equals("En cours") && dto.getDateDebutProduction() == null)
	        	 dto.setDateDebutProduction(Calendar.getInstance());
		   
	       if (dto.getStatut() != null && dto.getStatut().equals("Produit") && dto.getDateFinProduction() == null)
	        	 dto.setDateFinProduction(Calendar.getInstance());
		
		return vMisePersitance.modifierMise(dto);
	}


	@Override
	public MiseValue rechercheMiseParId(Long id) {

		return vMisePersitance.rechercheMiseParId(id);
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
	 * @param pDateMise
	 *            la date de la Bon Reception
	 * @return le numéro du prochaine Bon Reception à insérer
	 */
	private String getNumeroMise(final Calendar pDateMise) {

		Long vNumGuichetMise = this.guichetMiseDomaine.getNextNumReference();
		/** Année courante. */
		int vAnneeCourante = pDateMise.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NNNNNN. */
		StringBuilder vNumMise = new StringBuilder("");
		vNumMise.append("S");
		vNumMise.append(String.format("%04d", vNumGuichetMise));
		vNumMise.append("/");
		vNumMise.append(vAnneeCourante);
		
		/** Inserer une nouvelle valeur dans Guichet Mise. */
		GuichetMiseValue vGuichetValeur = new GuichetMiseValue();
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceCourant(new Long(vNumGuichetMise + 1L));

		/** Modification de la valeur en base du numéro. */
		this.guichetMiseDomaine.modifierGuichetMise(vGuichetValeur);

		return vNumMise.toString();

	}

	@Override
	public ResultatRechercheMiseValue rechercherMiseMultiCritere(
			RechercheMulticritereMiseValue request) {
		
		
		if(request.getEtatProduced() != null && request.getEtatProduced().equals("PLUS")) request.setEtatProduced("+");
		if(request.getEtatShipped() != null && request.getEtatShipped().equals("PLUS")) request.setEtatShipped("+");
		
		
		
		return vMisePersitance.rechercherMiseMultiCritere(request);
	}

	@Override
	public List<MiseValue> listerMise() {
		
		return vMisePersitance.listerMise();
	}

	@Override
	public TraitementMiseValue getTraitementMiseById(Long id) {
		// TODO Auto-generated method stub
		return vMisePersitance.getTraitementMiseById(id);
	}

	@Override
	public List<MiseValue> getMiseByReference(String referenceMise) {
		return vMisePersitance.getMiseByReference(referenceMise);
	}

	@Override
	public List<String> getListRefMiseParRefBR(String referenceBR) {
		return vMisePersitance.getListRefMiseParRefBR(referenceBR);
	}

	@Override
	public String listRefMiseParRefBR(String referenceBR) {
		List<String> listRefMise = this.getListRefMiseParRefBR(referenceBR);
		//System.out.println("------listRefMise----------"+listRefMise);

		String concatinatedList="";
		
		for (int i = 0; i < listRefMise.size(); i++) {
			String refMise = listRefMise.get(i);
			if(i != listRefMise.size()-1 ){
				concatinatedList+= refMise + ",";
			}else{
				concatinatedList+= refMise;	
			}
		}
		
		//System.out.println("------concatinatedList----------"+concatinatedList);
		return concatinatedList;
	}
	
	@Override
	public List<MiseValue> getReferenceMise() {
		// TODO Auto-generated method stub
		return vMisePersitance.getReferenceMise();
	}

	@Override
	public MiseValue rechercheMiseParReference(String pId) {
		
		MiseValue mv = vMisePersitance.rechercheMiseParReference(pId);
		ProduitValue produit = null ;
		
		if (mv != null && mv.getProduitId() != null)
		  produit=produitPersistence.rechercheProduitById(mv.getProduitId());
		
		if (produit != null )
		mv.setReferenceProduit(produit.getReference());
		
		return mv;
	}
}
