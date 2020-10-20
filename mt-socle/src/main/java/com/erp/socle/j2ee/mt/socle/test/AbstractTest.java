package com.erp.socle.j2ee.mt.socle.test;

import org.junit.ClassRule;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract de toutes les classes de test.
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 16 $
 */
public class AbstractTest {

  /** Logger. */
  protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

  /** Rule permettant de logger le nom de la classe de test excutée. */
  @ClassRule
  // CHECKSTYLE IGNORE VisibilityModifier FOR NEXT 1 LINES (les rules junit doivent être public)
  public static LoggingRule classRule = new LoggingRule(AbstractTest.class);

  /** Rule permettant de logger le nom de la methode de test excutée. */
  @Rule
  // CHECKSTYLE IGNORE VisibilityModifier FOR NEXT 1 LINES (les rules junit doivent être public)
  public LoggingRule loggingMethodRule = new LoggingRule(this.getClass());

  /**
   * Constructeur de la classe.
   */
  public AbstractTest() {
    super();
    // Constructeur vide
  }

}
