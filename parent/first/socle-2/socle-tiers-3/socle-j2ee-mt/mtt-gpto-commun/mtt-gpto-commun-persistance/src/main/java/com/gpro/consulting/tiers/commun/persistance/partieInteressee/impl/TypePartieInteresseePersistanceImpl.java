package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ITypePartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.TypeEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class TypePartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class TypePartieInteresseePersistanceImpl extends AbstractPersistance
		implements ITypePartieInteresseePersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

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

	/************************* create type Partie intersee *************************************/
	@Override
	public String creerTypePartieInteressee(TypeValue pTypeValue) {
		TypeEntite typeEntite = (TypeEntite) this.creer(PersistanceUtilities
				.toTypePArtieInteresseEntity(pTypeValue));
		TypeValue pTypePartieIntersseValueResult = PersistanceUtilities
				.toTypePArtieInteresseValue(typeEntite);
		return pTypePartieIntersseValueResult.getId().toString();
	}

	/****************** supprimer type PI *****************************/
	@Override
	public void supprimerTypePartieInteressee(TypeValue pTypeValue) {
		this.supprimerEntite(TypeEntite.class, pTypeValue.getId().longValue());
	}

	/****************** modifier type *****************/
	@Override
	public String modifierTypePartieInteressee(TypeValue pTypeValue) {
		TypeEntite typeEntite = (TypeEntite) this.modifier(PersistanceUtilities
				.toTypePArtieInteresseEntity(pTypeValue));
		TypeValue pTypePartieIntersseValueResult = PersistanceUtilities
				.toTypePArtieInteresseValue(typeEntite);
		return pTypePartieIntersseValueResult.getId().toString();
	}

	/****************** recherche par id **********************/
	@Override
	public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue) {
		TypeEntite pTypeEntite = this.rechercherParId(pTypeValue.getId()
				.longValue(), TypeEntite.class);
		TypeValue pTypeValueResult = PersistanceUtilities
				.toTypePArtieInteresseValue(pTypeEntite);
		return pTypeValueResult;
	}

	@Override
	public List<TypeValue> listeTypePartieIntPartieInteressee() {
		 List <TypeEntite > vListeTypeEntite = this.lister(TypeEntite.class);
		    List < TypeValue > vListeTypeValues = new ArrayList < TypeValue >();
		    for (TypeEntite vTypeEntite : vListeTypeEntite) {
		    	vListeTypeValues.add(PersistanceUtilities.toTypePArtieInteresseValue(vTypeEntite));
		    }
		    return vListeTypeValues;
	}

}
