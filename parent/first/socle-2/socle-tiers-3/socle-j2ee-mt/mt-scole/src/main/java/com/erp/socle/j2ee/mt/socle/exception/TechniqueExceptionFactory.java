package com.erp.socle.j2ee.mt.socle.exception;


import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erp.socle.j2ee.mt.socle.utilities.ResourcesBundleUtilities;


/**
 * Factory des exceptions techniques.<br/>
 * Utilisée seulement par les Composants Techniques via l'appel de la méthode
 * {@link #getInstance(Class)}.
 * 
 * $Author: Ridha KHASKHOUSSY
 * 
 */
public final class TechniqueExceptionFactory {

  /** Logger. */
  private Logger logger; // NOPMD

  /** Le ressource bundle contenant les messages d'erreurs. */
  private ResourceBundle resourceBundle;

  /**
   * Retourne une exception factory pour la classe passée an paramètre.
   * 
   * @param clz
   *          la classe pour laquelle on souhaite instancier une exception factory.
   * @return une exception factory pour la classe passée an paramètre
   */
  public static TechniqueExceptionFactory getInstance(final Class < ? > clz) {
    TechniqueExceptionFactory factory = new TechniqueExceptionFactory();
    factory.init(clz);
    return factory;
  }

  /**
   * Constructeur par défaut.
   */
  private TechniqueExceptionFactory() {
  }

  /**
   * Retourne le message de l'exception technique dont le code est passé en paramètre.
   * 
   * @param code
   *          le code de l'exception technique dont on souhaite obtenir le message
   * @param args
   *          les paramètres du message de l'exception technique
   * @return le message de l'exception technique dont le code est passé en paramètre
   */
  public String getMessage(final String code, final Object[] args) {
    if (resourceBundle.containsKey(code)) {
      String message = resourceBundle.getString(code);
      return MessageFormat.format(message == null ? "" : message.replaceAll("'", "''"), args);
    }
    return "Libelle erreur introuvable";
  }

  /**
   * Retourne une exception technique avec le code, le message et l'exception passés en paramètres.
   * 
   * @param code
   *          le code de l'exception technique
   * @param message
   *          le message de l'exception technique
   * @param ex
   *          l'exception d'origine
   * 
   * @return une exception technique avec le code, le message et l'exception passés en paramètres.
   */
  public TechniqueException getTechniqueException(final String code, final String message, final Throwable ex) {
    TechniqueException te = null;
    if (ex == null) {
      te = new TechniqueException(message);
    } else {
      te = new TechniqueException(message, ex);
    }
    te.setCode(code);
    return te;
  }

  /**
   * Charge les messages des exceptions techniques du CT auquel est rattaché la classe passé en
   * paramètre.<BR>
   * Initialise le logger de l'exception factory.
   * 
   * @param clz
   *          la classe qui instancie l'exception factory.
   */
  private void init(final Class < ? > clz) {
    resourceBundle = ResourcesBundleUtilities.getResourceBundle(clz);
    logger = LoggerFactory.getLogger(clz);
  }

  /**
   * Trace en niveau ERROR le message qui a le code <code>code</code>.
   * 
   * @param code
   *          le code du message à tracer
   * @param args
   *          les valeurs des arguments du message
   * @param cause
   *          l'exception d'origine
   * @return le message tracé
   */
  public String logMessage(final String code, final Object[] args, final Throwable cause) {
    String message = getMessage(code, args);
    if (cause == null) {
      logger.error("[" + code + "] " + message);
    } else {
      logger.error("[" + code + "] " + message, cause);
    }
    return message;
  }

  /**
   * Génère une exception technique avec le message identifié par le code <code>code</code>.
   * 
   * @param code
   *          le code de l'exception technique
   */
  public void throwTechniqueException(final String code) {
    throwTechniqueException(code, (String[]) null, (Throwable) null);
  }

  /**
   * Génère une exception technique avec le message identifié par le code <code>code</code>.
   * 
   * @param code
   *          le code de l'exception technique
   * @param arg
   *          la valeur de l'argument du message
   */
  public void throwTechniqueException(final String code, final String arg) {
    throwTechniqueException(code, new String[] {arg }, (Throwable) null);
  }

  /**
   * Génère une exception technique avec le message identifié par le code <code>code</code> et
   * l'exception d'origine <code>cause</code>.
   * 
   * @param code
   *          le code de l'exception technique
   * @param arg
   *          la valeur de l'argument du message
   * @param cause
   *          l'exception d'origine
   */
  public void throwTechniqueException(final String code, final String arg, final Throwable cause) {
    throwTechniqueException(code, new String[] {arg }, cause);
  }

  /**
   * Génère une exception technique avec le message identifié par le code <code>code</code>.
   * 
   * @param code
   *          le code de l'exception technique
   * @param arg
   *          les valeurs des arguments du message
   */
  public void throwTechniqueException(final String code, final String[] arg) {
    throwTechniqueException(code, arg, (Throwable) null);
  }

  /**
   * Génère une exception technique avec le message identifié par le code <code>code</code> et
   * l'exception d'origine <code>cause</code>.
   * 
   * @param code
   *          le code de l'exception technique
   * @param arg
   *          les valeurs des arguments du message
   * @param cause
   *          l'exception d'origine
   */
  public void throwTechniqueException(final String code, final String[] arg, final Throwable cause) {
    String msg = logMessage(code, arg, cause);
    TechniqueException e = getTechniqueException(code, msg, cause);
    logger.error("[" + code + "] Exception ID :" + e.getNamedUuid());
    throw e;
  }

  /**
   * Génère une exception technique avec le message identifié par le code <code>code</code> et
   * l'exception d'origine <code>cause</code>.
   * 
   * @param code
   *          le code de l'exception technique
   * @param cause
   *          l'exception d'origine
   */
  public void throwTechniqueException(final String code, final Throwable cause) {
    throwTechniqueException(code, (String[]) null, cause);
  }

  /**
   * Génère une exception technique avec l'exception d'origine <code>cause</code>.
   * 
   * @param cause
   *          l'exception d'origine
   */
  public void throwTechniqueException(final Throwable cause) {
    throw new TechniqueException("Erreur Interne : " + cause.getMessage(), cause);
  }
}
