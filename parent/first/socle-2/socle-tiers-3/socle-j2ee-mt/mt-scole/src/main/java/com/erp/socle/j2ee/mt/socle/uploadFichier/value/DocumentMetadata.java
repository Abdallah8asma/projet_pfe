package com.erp.socle.j2ee.mt.socle.uploadFichier.value;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Meta data créé pour un document à archiver
 * 
 * @author Ridha KHASKHOUSSY
 */
public class DocumentMetadata implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -761710902223007502L;

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentMetadata.class);

  /** Information sur le document */
  public static final String PROP_UUID = "uuid";
  public static final String PROP_PARTIE_CONCERNEE = "partie-concernee";
  public static final String PROP_NOM_FICHIER = "nom-fichier";
  public static final String PROP_DATE_DOCUMENT = "date-document";
  public static final String PROP_MODULE_DOCUMENT = "module-document";
  
  public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);

  /** Uuid */
  protected String uuid;

  /** Nom Fichier */
  protected String nomFichier;

  /** Date du Document */
  protected Date dateDocument;

  /** Partie Concernee */
  protected String partieConcernee;
  
  /** Module */
  protected String module;

  
  /**
   * Constructeur de la classe.
   */
  public DocumentMetadata() {
    super();
  }

  /**
   * Constructeur de la classe.
   * 
   * @param nomFichier
   * @param dateDocument
   * @param partieConcernee
   */
  public DocumentMetadata(String nomFichier, Date dateDocument, String partieConcernee, String module) {
    this(UUID.randomUUID().toString(), nomFichier, dateDocument, partieConcernee, module);
  }

  /**
   * Constructeur de la classe.
   * 
   * @param uuid
   * @param nomFichier
   * @param dateDocument
   * @param partieConcernee
   */
  public DocumentMetadata(String uuid, String nomFichier, Date dateDocument, String partieConcernee, String module) {
    super();
    this.uuid = uuid;
    this.nomFichier = nomFichier;
    this.dateDocument = dateDocument;
    this.partieConcernee = partieConcernee;
    this.module = module;
  }

  /**
   * Constructeur de la classe.
   * 
   * @param properties
   */
  public DocumentMetadata(Properties properties) {
    this(properties.getProperty(PROP_UUID), properties.getProperty(PROP_NOM_FICHIER), null, properties
      .getProperty(PROP_PARTIE_CONCERNEE), properties.getProperty(PROP_MODULE_DOCUMENT));
    String dateString = properties.getProperty(PROP_DATE_DOCUMENT);
    if (dateString != null) {
      try {
        this.dateDocument = DATE_FORMAT.parse(dateString);
      } catch (ParseException e) {
        LOGGER.error("Error while parsing date string: " + dateString + ", format is: yyyy-MM-dd", e);
      }
    }
  }

  /**
   * Création du fichier propertie
   * 
   * @return
   */
  public Properties createProperties() {
    Properties props = new Properties();
    props.setProperty(PROP_UUID, getUuid());
    props.setProperty(PROP_NOM_FICHIER, getNomFichier());
    props.setProperty(PROP_PARTIE_CONCERNEE, getPartieConcernee());
    props.setProperty(PROP_DATE_DOCUMENT, DATE_FORMAT.format(getDateDocument()));
    props.setProperty(PROP_MODULE_DOCUMENT, getModule());
    return props;
  }

  /**
   * Accesseur en lecture de l'attribut <code>uuid</code>.
   * 
   * @return String L'attribut uuid à lire.
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * Accesseur en écriture de l'attribut <code>uuid</code>.
   *
   * @param uuid
   *          L'attribut uuid à modifier.
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   * Accesseur en lecture de l'attribut <code>nomFichier</code>.
   * 
   * @return String L'attribut nomFichier à lire.
   */
  public String getNomFichier() {
    return nomFichier;
  }

  /**
   * Accesseur en écriture de l'attribut <code>nomFichier</code>.
   *
   * @param nomFichier
   *          L'attribut nomFichier à modifier.
   */
  public void setNomFichier(String nomFichier) {
    this.nomFichier = nomFichier;
  }

  /**
   * Accesseur en lecture de l'attribut <code>dateDocument</code>.
   * 
   * @return Date L'attribut dateDocument à lire.
   */
  public Date getDateDocument() {
    return dateDocument;
  }

  /**
   * Accesseur en écriture de l'attribut <code>dateDocument</code>.
   *
   * @param dateDocument
   *          L'attribut dateDocument à modifier.
   */
  public void setDateDocument(Date dateDocument) {
    this.dateDocument = dateDocument;
  }

  /**
   * Accesseur en lecture de l'attribut <code>partieConcernee</code>.
   * 
   * @return String L'attribut partieConcernee à lire.
   */
  public String getPartieConcernee() {
    return partieConcernee;
  }

  /**
   * Accesseur en écriture de l'attribut <code>partieConcernee</code>.
   *
   * @param partieConcernee
   *          L'attribut partieConcernee à modifier.
   */
  public void setPartieConcernee(String partieConcernee) {
    this.partieConcernee = partieConcernee;
  }

	public String getModule() {
		return module;
	}
	
	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "DocumentMetadata [uuid=" + uuid + ", nomFichier=" + nomFichier + ", dateDocument=" + dateDocument
				+ ", partieConcernee=" + partieConcernee + ", module=" + module + "]";
	}

}
