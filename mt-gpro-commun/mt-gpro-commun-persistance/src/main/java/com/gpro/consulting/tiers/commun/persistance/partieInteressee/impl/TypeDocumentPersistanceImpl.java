package com.gpro.consulting.tiers.commun.persistance.partieInteressee.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.partieInteressee.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.ITypeDocumentPersistance;
import com.gpro.consulting.tiers.commun.persistance.partieInteressee.entity.TypeDocumentEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class TypeDocumentPartieInteresseeImpl.
 * 
 * @author $mohamed
 */

@Component
public class TypeDocumentPersistanceImpl extends AbstractPersistance implements
		ITypeDocumentPersistance {

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

	@Override
	public String creerTypeDocumentPartieInteressee(TypeDocumentValue pTypeDocumentValue) {
		  TypeDocumentEntite typeDocumentEntite= (TypeDocumentEntite)this.creer(PersistanceUtilities.toEntity(pTypeDocumentValue));
		  TypeDocumentValue  typeDocumentValueResult =PersistanceUtilities.toValue(typeDocumentEntite);
		  return typeDocumentValueResult.getId().toString();
	}

	@Override
	public void supprimerTypeDocumentPartieInteressee(TypeDocumentValue pTypeDocumentValue) {
		this.supprimerEntite(TypeDocumentEntite.class, pTypeDocumentValue.getId().longValue());
		
	}

	@Override
	public String modifierTypeDocumentPartieInteressee(TypeDocumentValue pTypeDocumentValue) {
		  TypeDocumentEntite typeDocumentEntite= (TypeDocumentEntite)this.modifier(PersistanceUtilities.toEntity(pTypeDocumentValue));
		  TypeDocumentValue  typeDocumentValueResult =PersistanceUtilities.toValue(typeDocumentEntite);
		  return typeDocumentValueResult.getId().toString();
	}

	@Override
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue) {
		TypeDocumentEntite vTypetity= (TypeDocumentEntite) this.rechercherParId(pTypeDocumentValue, TypeDocumentEntite.class);
		TypeDocumentValue vTypeResult=PersistanceUtilities.toValue(vTypetity);
		return vTypeResult;
	}

	@Override
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee() {
		 List <TypeDocumentEntite > vListeTypeDocumentEntite = this.lister(TypeDocumentEntite.class);
		    List < TypeDocumentValue > vListeTypeDocumentValues = new ArrayList < TypeDocumentValue >();
		    for (TypeDocumentEntite vTypeDocumentEntite : vListeTypeDocumentEntite) {
		    	vListeTypeDocumentValues.add(PersistanceUtilities.toValue(vTypeDocumentEntite));
		    }
		    return vListeTypeDocumentValues;
	}

}
