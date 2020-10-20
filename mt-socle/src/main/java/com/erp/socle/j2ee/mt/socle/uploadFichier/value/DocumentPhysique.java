package com.erp.socle.j2ee.mt.socle.uploadFichier.value;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

/**
 * Classe document sert à l'archivage dans le serveur
 * 
 * @author Ridha KHASKHOUSSY
 */
public class DocumentPhysique extends DocumentMetadata implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 2004955454853853315L;

  /**
   * Liste des fichiers
   */
  private byte[] fileData;

  /**
   * Constructeur de la classe.
   * 
   * @param fileData
   * @param nomFichier
   * @param dateDocument
   * @param partieConcernee
   */
  public DocumentPhysique(byte[] fileData, String nomFichier, Date dateDocument, String partieConcernee, String module) {
    super(nomFichier, dateDocument, partieConcernee, module);
    this.fileData = fileData;
  }

  /**
   * Constructeur de la classe.
   * 
   * @param properties
   */
  public DocumentPhysique(Properties properties) {
    super(properties);
  }

  /**
   * Constructeur de la classe.
   * 
   * @param metadata
   */
  public DocumentPhysique(DocumentMetadata metadata) {
    super(metadata.getUuid(), metadata.getNomFichier(), metadata.getDateDocument(), metadata.getPartieConcernee(), metadata.getModule());
  }

  /**
   * @return
   */
  public DocumentMetadata getMetadata() {
    return new DocumentMetadata(getUuid(), getNomFichier(), getDateDocument(), getPartieConcernee(), getModule());
  }

  /**
   * Accesseur en lecture de l'attribut <code>fileData</code>.
   * 
   * @return byte[] L'attribut fileData à lire.
   */
  public byte[] getFileData() {
    return fileData;
  }

  /**
   * Accesseur en écriture de l'attribut <code>fileData</code>.
   *
   * @param fileData
   *          L'attribut fileData à modifier.
   */
  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

}
