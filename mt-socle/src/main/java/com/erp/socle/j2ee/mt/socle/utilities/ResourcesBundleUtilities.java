package com.erp.socle.j2ee.mt.socle.utilities;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.erp.socle.j2ee.mt.socle.exception.TechniqueExceptionFactory;


/**
 * Classe utilitaire pour la gestion des resources bundle des composants techniques.
 * 
 * @author $Author: Ridha KHASKHOUSSY
 * 
 */
public final class ResourcesBundleUtilities {

  /** Factory d'exceptions techniques. */
  private static final TechniqueExceptionFactory TECHNIQUE_EXCEPTION_FACTORY = TechniqueExceptionFactory
    .getInstance(ResourcesBundleUtilities.class);

  /**
   * Constructeur de la classe.
   */
  private ResourcesBundleUtilities() {
    // Constructeur vide
  }

  /**
   * Charge le <code>ResourceBundle</code> du composant technique auquel est rattaché la classe
   * passée en paramètre.
   * 
   * @param clz
   *          la classe depuis laquelle est demandé le chargement du <code>ResourceBundle</code>
   * @return le <code>ResourceBundle</code> du composant technique auquel est rattaché la classe
   *         passée en paramètre
   */
  public static ResourceBundle getResourceBundle(final Class < ? > clz) {

    ResourceBundle resourceBundle = null;
    String nomComposantTechnique = clz.getPackage().getName();

    if (nomComposantTechnique.startsWith(IConstantesUtilities.PREFIX_PACKAGE_MT)) {
      nomComposantTechnique = nomComposantTechnique.replaceFirst(IConstantesUtilities.PREFIX_PACKAGE_MT + ".", "");
      nomComposantTechnique = nomComposantTechnique.split("\\.")[0];
    } else {
      TECHNIQUE_EXCEPTION_FACTORY.throwTechniqueException(IConstantesUtilities.ERR_CHARGEMENT_RESOURCE_BUNDLE, clz.getName());
    }

    String baseName = MessageFormat.format(IConstantesUtilities.PATH_RESOURCE_BUNDLE, nomComposantTechnique);

    try {
      resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault(), clz.getClassLoader());
    } catch (MissingResourceException mre) {
      resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault(), Thread.currentThread().getContextClassLoader());
    }

    return resourceBundle;
  }
}
