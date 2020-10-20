package com.erp.socle.j2ee.mt.socle.uploadFichier;

import java.util.Date;
import java.util.List;

import com.erp.socle.j2ee.mt.socle.uploadFichier.value.DocumentMetadata;
import com.erp.socle.j2ee.mt.socle.uploadFichier.value.DocumentPhysique;

/**
 * 
 * @author $Author: Ridha KHASKHOUSSY $
 * @version $Revision: 0 $
 */
public interface IGestionnaireDocument {

  /**
   * Méthode de sauvegarde d'un document dans un archive
   * 
   * @param document
   */
  public DocumentMetadata sauvegarderDocument(DocumentPhysique document);

  /**
   * Méthode de sauvegarde d'un document dans un archive
   * 
   * @param document
   */
  public String sauvegarderDocumentParCreation(DocumentPhysique document);

  /**
   * Méthode de recherche d'un document par PartieConcernee et la date
   * 
   * @param partieConcernee
   * @param date
   * @return
   */
  public List < DocumentMetadata > rechercherDocument(String partieConcernee, Date date);

  /**
   * Méthode permettant de charger un document par son id
   * 
   * @param uuid
   * @return
   */
  public DocumentPhysique chargerDocument(String uuid, String module);

  /**
   * Méthode permettant de charger un document par son id et le transformer en format binaire
   * 
   * @param uuid
   * @return
   */
  public byte[] chargerDocumentBinaire(String uuid, String module);

public DocumentMetadata sauvegarderDocument(DocumentPhysique document, String dIRECTORY);



}
