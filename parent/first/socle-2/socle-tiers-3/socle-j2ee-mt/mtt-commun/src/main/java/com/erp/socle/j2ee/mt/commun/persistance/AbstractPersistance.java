package com.erp.socle.j2ee.mt.commun.persistance;


import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erp.socle.j2ee.mt.commun.utilities.IConstanteUtilities;
import com.erp.socle.j2ee.mt.socle.exception.TechniqueExceptionFactory;


/**
 * Helper pour la couche persistance.
 * 
 * @author Ridha KHASKHOUSSY
 * 
 */
public abstract class AbstractPersistance {

  /** Logger. */
  protected Logger log = LoggerFactory.getLogger(AbstractPersistance.class); // NOPMD

  /** Factory des exceptions techniques. */
  private static final TechniqueExceptionFactory TECHNIQUE_EXCEPTION_FACTORY = TechniqueExceptionFactory
    .getInstance(AbstractPersistance.class);

  /** Chaine unchecked. */
  private static final String UNCHECKED = "unchecked";

  /** Message indiquant que la classe de l'entité est nulle. */
  private static final String CLAZZ_ENTITE_NULL = "La classe de l'entité est nulle";

  /** SELECT FROM model. */
  private static final String SELECT_FROM_MODEL = "select model from ";

  /** Message instance. */
  private static final String INSTANCE = " instance";

  /**
   * Retourne l'Entity Manager de l'application Doit etre defini dans chaque classe fille.
   * 
   * @return {@link EntityManager}
   */
  public abstract EntityManager getEntityManager();

  /**
   * Créer l'objet en base.
   * 
   * @param entity
   *          l'entité à créer
   * @return l'entité créé
   */
  protected final Object creer(final Object entity) {
    if (log.isDebugEnabled()) {
      log.debug("saving " + entity.getClass().getSimpleName() + INSTANCE);
    }

    getEntityManager().persist(entity);

    if (log.isDebugEnabled()) {
      log.debug("save successful");
    }

    return entity;
  }

  /**
   * Modifie l'entité (JPA merge).
   * 
   * @param entity
   *          à modifier
   * @return l'entité modifié
   */
  protected final Object modifier(final Object entity) {
    if (log.isDebugEnabled()) {
      log.debug("update " + entity.getClass().getSimpleName() + INSTANCE);
    }
    Object result = null;
    result = getEntityManager().merge(entity);
    log.debug("update successful");
    return result;
  }

  /**
   * Recherche l'entité par id.
   * 
   * @param id
   *          Id de l'objet à rechercher
   * @param clazz
   *          Le type de donnée
   * @param <T>
   *          la class de l'entite recherchée
   * @return T l'entité recherchée
   */
  protected final <T> T rechercherParId(final Object id, final Class < T > clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException(CLAZZ_ENTITE_NULL);
    }
    T instance = null;
    if (log.isDebugEnabled()) {
      log.debug("finding instance {} Id : {}", clazz.getSimpleName(), id);
    }

    instance = getEntityManager().find(clazz, id);
    if (log.isDebugEnabled()) {
      log.debug("after " + clazz.getSimpleName() + " instance with id: " + id);
    }
    return instance;
  }

  /**
   * Liste l'ensemble des entités.
   * 
   * @param clazz
   *          l'entité à retourner
   * @param <T>
   *          la class de l'entite recherchée
   * @return Liste des entités recherchées
   */
  @SuppressWarnings(UNCHECKED)
  protected final <T> List < T > lister(final Class < T > clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException(CLAZZ_ENTITE_NULL);
    }
    if (log.isDebugEnabled()) {
      log.debug("finding all instance {} : ", clazz.getSimpleName());
    }

    String queryString = SELECT_FROM_MODEL + clazz.getSimpleName() + " model";
    Query query = getEntityManager().createQuery(queryString);

    return query.getResultList();
  }

  /**
   * Liste l'ensemble des entités.
   * 
   * @param clazz
   *          l'entité à retourner
   * @param debut
   *          de pagination
   * @param quantite
   *          d'objet retourné
   * @param <T>
   *          la class de l'entite recherchée
   * @return Liste des entités recherchées
   */
  @SuppressWarnings(UNCHECKED)
  protected final <T> List < T > lister(final int debut, final int quantite, final Class < T > clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException(CLAZZ_ENTITE_NULL);
    }

    log.debug("finding all instance {} : ", clazz.getSimpleName());

    String queryString = SELECT_FROM_MODEL + clazz.getSimpleName() + " model";
    Query query = getEntityManager().createQuery(queryString);

    query.setFirstResult(debut);
    query.setMaxResults(quantite);

    return query.getResultList();
  }

  /**
   * Liste l'ensemble des entité par le nom d'une propriete en parametre.
   * 
   * @param propertyName
   *          propriete
   * @param value
   *          valeur
   * @param clazz
   *          l'entité à retourner
   * @param <T>
   *          la class de l'entite recherchée
   * @return Liste des entités recherchées
   */
  @SuppressWarnings(UNCHECKED)
  protected final <T> List < T > rechercherParAttribut(final String propertyName, final Object value, final Class < T > clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException(CLAZZ_ENTITE_NULL);
    }

    if (log.isDebugEnabled()) {
      log.debug("finding {} instance with property: {}, value: {}", clazz.getSimpleName());
    }

    String queryString = SELECT_FROM_MODEL + clazz.getSimpleName() + " model where model." + propertyName + "= :propertyValue";

    Query query = getEntityManager().createQuery(queryString);
    query.setParameter("propertyValue", value);

    return query.getResultList();
  }

  /**
   * Liste l'ensemble des entité par le nom d'une propriete en parametre en mode LIKE sql.
   * 
   * @param propertyName
   *          propriete
   * @param value
   *          valeur
   * @param clazz
   *          l'entité à retourner
   * @return Liste des entités recherchées
   */
  @SuppressWarnings(UNCHECKED)
  protected final List < Object > rechercherCorrespondantAttribut(final String propertyName, final String value,
    final Class < ? > clazz) {
    if (clazz == null) {
      throw new IllegalArgumentException(CLAZZ_ENTITE_NULL);
    }

    if (log.isDebugEnabled()) {
      log.debug("finding {} instance with property: {}, value: {}", clazz.getSimpleName());
    }

    String queryString = SELECT_FROM_MODEL + clazz.getSimpleName() + " model where model." + propertyName
      + " like :propertyValue";

    Query query = getEntityManager().createQuery(queryString);
    query.setParameter("propertyValue", "%" + value + "%");

    return query.getResultList();
  }

  /**
   * Liste l'ensemble des entité par le nom de l'identifiant en parametre (Pagination Compliant).
   * 
   * @param idsValues
   *          liste des valeurs possibles de l'identifiant de l'objet
   * @param debut
   *          de pagination
   * @param quantite
   *          d'objet retourné
   * @param clazz
   *          l'entité à retourner
   * @param clazzIdFieldName
   *          nom de l'attribut de l'entité
   * @param <T>
   *          la class de l'entite recherchée
   * @return Liste des entités recherchées
   */
  @SuppressWarnings(UNCHECKED)
  public final <T> List < T > rechercherParIds(final List < Object > idsValues, final int debut, final int quantite,
    final Class < T > clazz, final String clazzIdFieldName) {

    if (log.isDebugEnabled()) {
      log.debug("finding " + clazz.getSimpleName() + " instance with ID IN : " + StringUtils.join(idsValues, ","));
    }

    if (clazzIdFieldName == null) {
      log.warn("Aucun attribut n'est défini en tant que ID pour l'entité {} ", clazz.getSimpleName());
    }

    String queryString = SELECT_FROM_MODEL + clazz.getSimpleName() + " model where model." + clazzIdFieldName
      + " in (:idsValues)";

    if (log.isDebugEnabled()) {
      log.debug(queryString);
    }

    Query query = getEntityManager().createQuery(queryString);
    query.setParameter("idsValues", idsValues);
    query.setFirstResult(debut);
    query.setMaxResults(quantite);

    return query.getResultList();
  }

  /**
   * Vérifie que le timestamp et base et indique à celui en mémoire, si ce n'est pas le cas on
   * revoie une exception tech nique de type concurrence d'accès. Si L'objet passé en paramètre
   * n'est pas une entité, une exception technique est renvoyée.
   * 
   * @param objetEntite
   *          : Entité sur laquelle on veut vérifier le timestamp
   * @param dateEnBase
   *          : Timestamp récupéré de la base de données
   * @param dateMAJ
   *          : Timestamp en mémoire
   */
  public final void verifierConcurrence(final Object objetEntite, final Calendar dateEnBase, final Calendar dateMAJ) {

    if (objetEntite.getClass().getAnnotation(Entity.class) == null) {
      TECHNIQUE_EXCEPTION_FACTORY.throwTechniqueException(IConstanteUtilities.ERR_NOT_ENTITY);
    }

    if (!dateEnBase.getTime().equals(dateMAJ.getTime())) {
      TECHNIQUE_EXCEPTION_FACTORY.throwTechniqueException(IConstanteUtilities.ERR_CONCURRENCE_ACCES);
    }

    getEntityManager().lock(objetEntite, LockModeType.READ);
  }

  /**
   * Supprimer l'entité par ID (attention on recherche l'objet en base puis on le supprime).
   * 
   * @param clazz
   *          l'entité à supprimer
   * @param idAgent
   *          l'identifiant de l'agent à supprimer
   */
  public final void supprimerEntite(final Class < ? > clazz, final long idAgent) {
    if (clazz == null) {
      throw new IllegalArgumentException(CLAZZ_ENTITE_NULL);
    }

    if (clazz.getAnnotation(Entity.class) == null) {
      TECHNIQUE_EXCEPTION_FACTORY.throwTechniqueException(IConstanteUtilities.ERR_NOT_ENTITY);
    }

    Session session = (Session) getEntityManager().getDelegate();
    Object entite = session.load(clazz, idAgent);
    session.delete(entite);
  }

  /**
   * Wrapper pour flush(). Synchronise la base de données avec les valeurs des entités.
   * 
   */
  protected final void synchroBaseDeDonnee() {
    getEntityManager().flush();
  }


}
