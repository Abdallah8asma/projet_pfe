package com.erp.socle.j2ee.mt.socle.exception;

import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

/**
 * Factory des exceptions.
 * @author $Author: Ridha KHASKHOUSSY
 * 
 * @org.apache.xbean.XBean element="exceptionFactory" description="Factory des exceptions"
 */
public final class ExceptionFactory implements IExceptionFactory {

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionFactory.class);

  /** Source des messages de l'application. */
  private MessageSource messageSource;

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final String code) {
    throwTechniqueException(code, (String[]) null, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final Throwable ex) {
    throw new TechniqueException("Erreur Interne : " + ex.getMessage(), ex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final String code, final String[] args) {
    throwTechniqueException(code, args, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final String code, final String arg) {
    throwTechniqueException(code, new String[] {arg }, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final String code, final Throwable cause) {
    throwTechniqueException(code, (String[]) null, cause);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final String code, final String arg, final Throwable ex) {
    throwTechniqueException(code, new String[] {arg }, ex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwTechniqueException(final String code, final String[] args, final Throwable cause) {
    String message = logMessage(code, args);
    throw getTechniqueException(code, message, cause);
  }

  /**
   * Retourne une exception technique depuis l'exception <code>e</code>avec le code
   * <code>code</code> et le message <code>message</code>.
   * 
   * @param code
   *          le code de l'exception
   * @param message
   *          le message de l'exception
   * @param ex
   *          l'exception d'origine (nullable)
   * @return une exception technique depuis l'exception <code>e</code>avec le code <code>code</code>
   *         et le message <code>message</code>
   */
  private TechniqueException getTechniqueException(final String code, final String message, final Throwable ex) {
    TechniqueException ret = null;
    if (ex == null) {
      ret = new TechniqueException(message);
    } else {
      ret = new TechniqueException(message, ex);
    }
    ret.setCode(code);
    return ret;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwFonctionnelleException(final String code) throws FonctionnelleException {
    throwFonctionnelleException(code, (String[]) null, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwFonctionnelleException(final String code, final String[] args) throws FonctionnelleException {
    throwFonctionnelleException(code, args, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwFonctionnelleException(final String code, final String arg) throws FonctionnelleException {
    throwFonctionnelleException(code, new String[] {arg }, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwFonctionnelleException(final String code, final Throwable cause) throws FonctionnelleException {
    throwFonctionnelleException(code, (String[]) null, cause);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwFonctionnelleException(final String code, final String arg, final Throwable ex) throws FonctionnelleException {
    throwFonctionnelleException(code, new String[] {arg }, ex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void throwFonctionnelleException(final String code, final String[] args, final Throwable cause)
    throws FonctionnelleException {
    String message = logMessage(code, args);
    throw getFonctionnelleException(code, message, cause);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reThrowFonctionnelleException(final String code, final FonctionnelleException ex, final String... codes)
    throws FonctionnelleException {
    if (ArrayUtils.isNotEmpty(codes)) {
      for (String codeifs : codes) {
        if (codeifs.equals(code)) {
          throwFonctionnelleException(code, ex);
        }
      }
    } else {
      throwFonctionnelleException(code, ex);
    }
  }

  /**
   * Retourne une exception fonctionnelle depuis l'exception <code>ex</code>avec le code
   * <code>code</code> et le message <code>message</code>.
   * 
   * @param code
   *          le code de l'exception
   * @param message
   *          le message de l'exception
   * @param ex
   *          l'exception d'origine (nullable)
   * @return une exception fonctionnelle depuis l'exception <code>e</code>avec le code
   *         <code>code</code> et le message <code>message</code>
   */
  public FonctionnelleException getFonctionnelleException(final String code, final String message, final Throwable ex) {
    FonctionnelleException ret = null;
    if (ex == null) {
      ret = new FonctionnelleException(message);
    } else {
      ret = new FonctionnelleException(message, ex);
    }
    ret.setCode(code);
    return ret;
  }

  /**
   * Trace en niveau ERROR le message qui a le code <code>code</code>.
   * 
   * @param code
   *          le code du message à tracer
   * @param args
   *          les valeurs des arguments du message
   * @return le message tracé
   */
  public String logMessage(final String code, final String[] args) {
    String message = getMessage(code, args);
    LOGGER.error("[" + code + "] " + message);
    return message;
  }

  /**
   * Retourne le message qui a le code <code>code</code> en remplaçant les variables du messages par
   * les valeurs du paramètre <code>args</code>.
   * 
   * @param code
   *          le code du message à retourner
   * @param args
   *          les valeurs des arguments du message
   * @return le message qui a le code <code>code</code> en remplaçant les variables du messages par
   *         les valeurs du paramètre <code>args</code>
   */
  public String getMessage(final String code, final String[] args) {
    return messageSource.getMessage(code, args, "Libelle erreur introuvable", new Locale(""));
  }

  /**
   * Accesseur en lecture de l'attribut <code>messageSource</code>.
   * 
   * @return MessageSource L'attribut messageSource à lire.
   */
  public MessageSource getMessageSource() {
    return messageSource;
  }

  /**
   * Accesseur en écriture de l'attribut <code>messageSource</code>.
   * 
   * @param messageSource
   *          L'attribut messageSource à modifier.
   * @org.apache.xbean.Property alias="messageSource"
   *                            description="Source des messages de l'application"
   */
  public void setMessageSource(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

}
