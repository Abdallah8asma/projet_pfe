package com.erp.socle.j2ee.mt.socle.exception;


/**
 * Interface des exceptions.
 * @author $Author: Ridha KHASKHOUSSY
 * 
 */
public interface IException {

  /**
   * Retourne la cause de l'exception.
   * 
   * @return Throwable la cause de l'exception
   */
  Throwable getCause();

  /**
   * Retourne le message de l'exception.
   * 
   * @return String le message de l'exception
   */
  String getMessage();

  /**
   * Retourne le Uuid de l'exception.
   * 
   * @return String le Uuid de l'exception
   */
  byte[] getUuid();

  /**
   * Retourne le code de l'exception.
   * 
   * @return String le code de l'exception
   */
  String getCode();

  /**
   * Définit le code de l'exception.
   * 
   * @param code
   *          Le code de l'exception
   */
  void setCode(String code);

}
