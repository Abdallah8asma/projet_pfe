package com.erp.socle.j2ee.mt.socle.test;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rule junit permettant de logger le nom de la methode de test executée.
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 7 $
 */
public class LoggingRule extends TestWatcher {

  /** Le logger. */
  private Logger logger; // NOPMD

  /**
   * Constructeur de la classe.
   * 
   * @param clazz
   *          la classe de test executée
   */
  public LoggingRule(final Class < ? > clazz) {
    super();
    logger = LoggerFactory.getLogger(clazz);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void starting(final Description description) {
    logger.info("Execution du test [{}]", description.getDisplayName());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void finished(final Description description) {
    logger.info("Fin d'execution du test [{}]", description.getDisplayName());
  }

}
