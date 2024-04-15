package com.erp.socle.j2ee.mt.socle.exception;


/**
 * Provider static du service de configuration
 * 
 * @author $Author: Ridha KHASKHOUSSY
 * 
 */
public final class GestionnaireExceptionsProvider {

  /**
   * Service de gestion des exceptions du système.
   */
  private IExceptionFactory exceptionFactory;

  /**
   * Création d'une seule instance de GestionnaireExceptionsProvider
   */
  private static GestionnaireExceptionsProvider instance = new GestionnaireExceptionsProvider();

  /**
   * Constructeur de la classe
   */
  private GestionnaireExceptionsProvider() {
    super();
  }

  /**
   * Accesseur sur l'attribut gestionnaireExceptions
   * 
   * @return IGestionnaireExceptions gestionnaireExceptions
   */
  public static IExceptionFactory getExceptionFactory() {
    return getInstance().exceptionFactory;
  }

  /**
   * Mutateur sur l'attribut gestionnaireExceptions
   * 
   * @param gestionnaireExceptions
   *          l'attribut gestionnaireExceptions à modifier
   */
  public void setGestionnaireExceptions(final IExceptionFactory exceptionFactory) {
    this.exceptionFactory = exceptionFactory;
  }

  /**
   * Accesseur sur l'attribut instance
   * 
   * @return GestionnaireExceptionsProvider instance
   */
  public static GestionnaireExceptionsProvider getInstance() {
    return instance;
  }

}
