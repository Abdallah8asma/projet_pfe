package com.gpro.consulting.tiers.commun.service.partieInteressee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.DeviseValue;
import com.gpro.consulting.tiers.commun.domaine.partieInteressee.IDeviseDomaine;
import com.gpro.consulting.tiers.commun.service.partieInteressee.IDeviseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DevisePartieInteresseeServiceImpl implements IDeviseService{

		@Autowired
		IDeviseDomaine devisePartieInteresseeDomaine;
		
		/************************ Creation Matiere *****************************/
		@Override
		public String creerDevise(DeviseValue pDeviseValue) {
			return devisePartieInteresseeDomaine.creerDevise(pDeviseValue);
		}

		/************************ suppression Matiere ***************************/
		@Override
		public void supprimerDevise(Long pId) {
			devisePartieInteresseeDomaine.supprimerDevise(pId);
		}

		/************************ Modification Matiere ***************************/
		@Override
		public String modifierDevise(DeviseValue pDeviseValue) {
			return devisePartieInteresseeDomaine.modifierDevise(pDeviseValue);
		}

		/************************** Recherche Matiere *****************************/
		@Override
		public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue) {
			return devisePartieInteresseeDomaine.rechercheDeviseParId(pDeviseValue);
		}
		
		/************************** Liste des Matieres *****************************/
		@Override
		public List<DeviseValue> listeDevise() {
			return devisePartieInteresseeDomaine.listeDevise();
		}
		
}
		

