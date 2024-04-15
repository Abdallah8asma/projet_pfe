package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.GroupeClientValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.IGroupeClientPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.GroupeClientEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class GroupeClientImpl.
 * 
 * @author $mohamed
 */

@Component
public class GroupeClientPersistanceImpl extends AbstractPersistance
		implements IGroupeClientPersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerGroupeClient(GroupeClientValue pGroupeClientValue) {
		GroupeClientEntite pGroupeClientEntite = (GroupeClientEntite) this.creer(PersistanceUtilities.toEntity(pGroupeClientValue));
		GroupeClientValue pGroupeClientValueResult = PersistanceUtilities.toValue(pGroupeClientEntite);
		return pGroupeClientValueResult.getId().toString();
	}

	/**************** supprimer categorie partie interessee *******************/
	@Override
	public void supprimerGroupeClient(Long pGroupeClientValue) {
		this.supprimerEntite(GroupeClientEntite.class, pGroupeClientValue);
	}

	/**************** modifier categorie partie interessee *******************/
	@Override
	public String modifierGroupeClient(GroupeClientValue pGroupeClientValue) {
		GroupeClientEntite pGroupeClientEntite = (GroupeClientEntite) this
				.modifier(PersistanceUtilities.toEntity(pGroupeClientValue));
		GroupeClientValue pGroupeClientValueResult = PersistanceUtilities.toValue(pGroupeClientEntite);
		return pGroupeClientValueResult.getId().toString();
	}

	/****************
	 * recherche categorie partie interessee Par Id
	 *******************/

	@Override
	public GroupeClientValue rechercheGroupeClientParId(GroupeClientValue pGroupeClientValue) {
		GroupeClientEntite pGroupeClientEntite = this.rechercherParId(pGroupeClientValue.getId().longValue(),
				GroupeClientEntite.class);
		GroupeClientValue pGroupeClientValueResult = PersistanceUtilities.toValue(pGroupeClientEntite);
		return pGroupeClientValueResult;
	}

	/**************** liste categorie partie interessee *******************/

	@Override
	public java.util.List<GroupeClientValue> listeGroupeClient() {
		List<GroupeClientEntite> vGroupeClientsEntity = this.lister(GroupeClientEntite.class);
		List<GroupeClientValue> vListGroupeClientsValues = new ArrayList<GroupeClientValue>();
		for (GroupeClientEntite vGroupeClientEntite : vGroupeClientsEntity) {
			vListGroupeClientsValues.add(PersistanceUtilities.toValue(vGroupeClientEntite));
		}
		return vListGroupeClientsValues;
	}

	/*
	 * get entity manager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
