package com.erp.socle.j2ee.mt.socle.test;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Permet l'insertion et la suppression de données en base.
 * 
 * @org.apache.xbean.XBean element="dataSourceInitializer"
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 25 $
 */
public class DataSourceInitializer implements InitializingBean, DisposableBean {
  /** Le loggeur. */
  private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceInitializer.class);

  /** La source de données. */
  private DataSource dataSource;

  /** Les scripts de suppression des données. */
  private Resource[] destroyScripts;

  /** Permet d'ignorer les erreurs au moment du drop. */
  private boolean ignoreFailedDrop = true;

  /** Les scripts d'initialisation. */
  private Resource[] initScripts;

  /**
   * {@inheritDoc}
   */
  @Override
  public final void afterPropertiesSet() throws Exception { // NOPMD
    LOGGER.debug("Initialisation du DataSourceInitializer...");
    Assert.notNull(dataSource, "Impossible d'initialiser la base de données car la source de données est nulle");
    initialize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void destroy() throws Exception { // NOPMD
    LOGGER.debug("Exécution du script de nettoyage");
    if (ArrayUtils.isNotEmpty(destroyScripts)) {
      for (Resource destroyScript : destroyScripts) {
        try {
          doExecuteScript(destroyScript);
        } catch (Exception e) { // NOPMD
          LOGGER.error("Impossible d'exécuter le script de nettoyage " + destroyScript.getFilename(), e);
        }
      }
    }
  }

  /**
   * Execute le script référencé par <code>scriptResource</code>.
   * 
   * @param scriptResource
   *          la ressource qui contient les commandes sql.
   */
  private void doExecuteScript(final Resource scriptResource) {
    if (scriptResource == null) {
      return;
    }

    TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    transactionTemplate.execute(new TransactionCallback < Object >() {

      public Object doInTransaction(final TransactionStatus status) {
        String[] scripts;
        try {
          scripts = StringUtils.delimitedListToStringArray(stripComments(IOUtils.readLines(scriptResource.getInputStream())), ";"
            + SystemUtils.LINE_SEPARATOR);
        } catch (IOException e) {
          throw new BeanInitializationException("Impossible de charger le script sql " + scriptResource.getFilename(), e);
        }
        for (String script : scripts) {
          script = script.trim();

          if (StringUtils.hasText(script)) {
            try {
              new JdbcTemplate(dataSource).execute(script);
            } catch (DataAccessException e) {
              if (!ignoreFailedDrop || !script.toUpperCase().contains("DROP ")) {
                throw e;
              }
            }

          }
        }
        return null;
      }
    });
  }

  /**
   * Finalisation du DataSourceInitializer.
   * 
   * @throws Throwable
   *           exception
   * @see java.lang.Object#finalize()
   */
  @Override
  protected final void finalize() throws Throwable {
    LOGGER.debug("Finalisation du DataSourceInitializer");
    super.finalize();
  }

  /**
   * Phase d'initialisation.
   * 
   */
  public final void initialize() {
    LOGGER.debug("Exécution du script d'initialisation");
    if (ArrayUtils.isNotEmpty(initScripts)) {
      for (Resource initScript : initScripts) {
        try {
          doExecuteScript(initScript);
        } catch (Exception e) { // NOPMD
          LOGGER.error("Impossible d'exécuter le script d'initialisation " + initScript.getFilename(), e);
        }
      }
    }
  }

  /**
   * Retourne la liste <code>list</code> en ayant supprimé les lignes commentées.
   * 
   * @param list
   *          liste de commandes sql
   * @return les commandes sql sans commentaires
   */
  private String stripComments(final List < String > list) {
    StringBuilder buffer = new StringBuilder();
    for (String line : list) {
      if (!line.startsWith("//") && !line.startsWith("--")) {
        buffer.append(line + SystemUtils.LINE_SEPARATOR);
      }
    }
    return buffer.toString();
  }

  /**
   * Retourne l'attribut <code>ignoreFailedDrop</code>.
   * 
   * @return l'attribut <code>ignoreFailedDrop</code>
   * @org.apache.xbean.Property
   */
  public final boolean isIgnoreFailedDrop() {
    return ignoreFailedDrop;
  }

  /**
   * Initialise la source de données.
   * 
   * @param dataSource
   *          la source de données
   * @org.apache.xbean.Property
   */
  public final void setDataSource(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * Initialise les scripts de suppression des données.
   * 
   * @param destroyScripts
   *          les scripts de suppression des données
   * @org.apache.xbean.Property
   */
  public final void setDestroyScripts(final Resource[] destroyScripts) { // NOPMD
    this.destroyScripts = destroyScripts;
  }

  /**
   * Initialise les scripts d'insertion.
   * 
   * @param initScripts
   *          les scripts d'insertion
   * @org.apache.xbean.Property
   */
  public final void setInitScripts(final Resource[] initScripts) { // NOPMD
    this.initScripts = initScripts;
  }

  /**
   * Initialise l'attribut <code>ignoreFailedDrop</code>.
   * 
   * @param ignoreFailedDrop
   *          l'attribut <code>ignoreFailedDrop</code>
   * @org.apache.xbean.Property
   */
  public final void setIgnoreFailedDrop(final boolean ignoreFailedDrop) {
    this.ignoreFailedDrop = ignoreFailedDrop;
  }

}
