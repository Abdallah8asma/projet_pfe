package com.erp.socle.j2ee.mt.socle.uploadFichier.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erp.socle.j2ee.mt.socle.uploadFichier.IGestionnaireDocument;
import com.erp.socle.j2ee.mt.socle.uploadFichier.value.DocumentMetadata;
import com.erp.socle.j2ee.mt.socle.uploadFichier.value.DocumentPhysique;

/**
 * 
 * @author $Author: Ridha khaskhoussy $
 * @version $Revision: 0 $
 */
public class GestionnaireDocumentImpl implements IGestionnaireDocument {

  private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireDocumentImpl.class);

  /** Répertoire par défaut */
 // public static final String DIRECTORY = "c:/ERP/archive";
  
  public  String DIRECTORY = "";
  /** Nom du fichier metadata */
  public static final String META_DATA_FILE_NAME = "metadata.properties";

  /** Message Erreur : Fichier non trouve */
  public static final String ERREUR_FICHIER_NON_TROUVE = "Erreur trouvée suit à la recherche du document.";
  /** Message Erreur: Chargement non effectué */
  public static final String ERREUR_FICHIER_CHARGEMENT_DOCUMENT = "Erreur lors de chargement du document par uuid.";


  /**
   * Constructeur de la classe.
   */
  public GestionnaireDocumentImpl() {
    // Constructeur vide
	  
  }

  
  




public String getDIRECTORY() {
	return DIRECTORY;
}







public void setDIRECTORY(String dIRECTORY) {
	DIRECTORY = dIRECTORY;
}







/**
   * Création du répertoire d'archivage
   */
  @PostConstruct
  public void init() {
    creerRepertoire(DIRECTORY);
  }

  /**
   * Méthode d'insertion du document dans l'archive
   * 
   * {@inheritDoc}
   * 
   * @return
   */
  @Override
  public DocumentMetadata sauvegarderDocument(DocumentPhysique document) {

    try {
      creerRepertoire(document);
      sauvegarderDonneesFichier(document);
      savegarderMetaData(document);
      return document.getMetadata();

    } catch (IOException e) {
      String message = "Erreur d'insertion du document";
      LOGGER.error(message, e);
      throw new RuntimeException(message, e);
    }

  }

  /**
   * Méthode d'insertion du document dans l'archive
   * 
   * {@inheritDoc}
   * 
   * @return
   */
  @Override
  public String sauvegarderDocumentParCreation(DocumentPhysique document) {

    try {
      creerRepertoire(document);
      sauvegarderDonneesFichier(document);
      savegarderMetaData(document);
      return document.getMetadata().getUuid();

    } catch (IOException e) {
      String message = "Erreur d'insertion du document";
      LOGGER.error(message, e);
      throw new RuntimeException(message, e);
    }

  }

  /**
   * Méthode de recherche d'une liste document par critére de recherche : PartieConcernee et date
   * 
   * {@inheritDoc}
   */
  
//TODO : A corriger avec l'ajout du paramètre module dans le path
  @Override
  public List < DocumentMetadata > rechercherDocument(String partieConcernee, Date date) {
    
	  /*try {
      return rechercherDansFichierSysteme(partieConcernee, date);
    } catch (IOException e) {

      LOGGER.error(ERREUR_FICHIER_NON_TROUVE, e);
      throw new RuntimeException(ERREUR_FICHIER_NON_TROUVE, e);
    }*/
	  
	  return null;
  }

  /**
   * Chargement d'un document
   * 
   * {@inheritDoc}
   */
  @Override
  public DocumentPhysique chargerDocument(String uuid, String module) {
    try {
      return chargerDocumentFichierSysteme(uuid, module);
    } catch (IOException e) {

      LOGGER.error(ERREUR_FICHIER_CHARGEMENT_DOCUMENT, e);
      throw new RuntimeException(ERREUR_FICHIER_CHARGEMENT_DOCUMENT, e);
    }
  }

  /**
   * Chargement d'un document sous format binaire
   * 
   * {@inheritDoc}
   */
  @Override
  public byte[] chargerDocumentBinaire(String uuid, String module) {
    try {

      DocumentPhysique document = chargerDocumentFichierSysteme(uuid, module);

      if (document != null) {
        return document.getFileData();
      } else {
        return null;
      }
    } catch (IOException e) {

      LOGGER.error(ERREUR_FICHIER_CHARGEMENT_DOCUMENT, e);
      throw new RuntimeException(ERREUR_FICHIER_CHARGEMENT_DOCUMENT, e);
    }
  }

  /**
   * Méthode permettant de recupérer une liste de document
   * 
   * @param partieConcernee
   * @param date
   * @return
   * @throws IOException
   */
  
  // TODO : A corriger avec l'ajout du paramètre module dans le path
  /*
  private List < DocumentMetadata > rechercherDansFichierSysteme(String partieConcernee, Date date) throws IOException {
    List < String > listUuid = getUuidList();
    List < DocumentMetadata > listMetadata = new ArrayList < DocumentMetadata >(listUuid.size());
    for (String uuid : listUuid) {    	
      DocumentMetadata metadata = chargerMetadataFichierSystem(uuid);
      if (isMatched(metadata, partieConcernee, date)) {
        listMetadata.add(metadata);
      }
    }
    return listMetadata;
  }
  
  */

  /**
   * Comparer les critères de recherche et les données du metadata
   * 
   * @param metadata
   * @param personName
   * @param date
   * @return
   */
  private boolean isMatched(DocumentMetadata metadata, String partieConcernee, Date date) {
    if (metadata == null) {
      return false;
    }
    boolean match = true;
    if (partieConcernee != null) {
      match = (partieConcernee.equals(metadata.getPartieConcernee()));
    }
    if (match && date != null) {
      match = (date.equals(metadata.getDateDocument()));
    }
    return match;
  }

  /**
   * Chargement du document du systeme
   * 
   * @param uuid
   * @return
   * @throws IOException
   */
  private DocumentPhysique chargerDocumentFichierSysteme(String uuid, String module) throws IOException {
    DocumentMetadata metadata = chargerMetadataFichierSystem(uuid, module);
    if (metadata == null) {
      return null;
    }
    
    //////System.out.println("**** metadata : chargerDocumentFichierSysteme ****"+ metadata.toString() );
    Path path = Paths.get(getPathFichier(metadata));
    DocumentPhysique document = new DocumentPhysique(metadata);
    document.setFileData(Files.readAllBytes(path));
    return document;
  }

  /**
   * Récupération du path du fichier
   * 
   * @param metadata
   * @return
   */
  private String getPathFichier(DocumentMetadata metadata) {
    String pathRepertoire = getPathRepertoire(metadata.getUuid(), metadata.getModule());
    StringBuilder sb = new StringBuilder();
    //////System.out.println("**** metadata : getPathFichier ****"+ metadata.toString() );
    //////System.out.println("**** metadata.getNomFichier()****"+ metadata.getNomFichier() );
    sb.append(pathRepertoire).append(File.separator).append(metadata.getNomFichier());
    //////System.out.println("************** path ************** "+ sb.toString());
    return sb.toString();
  }

  /**
   * Chargement des metadata à partir du systeme
   * 
   * @param uuid
   * @return
   * @throws IOException
   */
  private DocumentMetadata chargerMetadataFichierSystem(String uuid, String module) throws IOException {
    DocumentMetadata document = null;
    String dirPath = getPathRepertoire(uuid, module);
    File file = new File(dirPath);
    if (file.exists()) {
      Properties properties = getProperties(uuid, module);
      document = new DocumentMetadata(properties);

    }
    return document;
  }

  /**
   * Lecture des fichiers properties
   * 
   * @param uuid
   * @return
   * @throws IOException
   */
  private Properties getProperties(String uuid, String module) throws IOException {
    Properties propertie = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream(new File(getPathRepertoire(uuid, module), META_DATA_FILE_NAME));
      propertie.load(input);
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return propertie;
  }

  /**
   * Récupération du chemin du répertoire par UUID
   * 
   * @param uuid
   * @return
   */
  private String getPathRepertoire(String uuid, String module) {
    StringBuilder sb = new StringBuilder();
    sb.append(DIRECTORY).append(File.separator).append(module).append(File.separator).append(uuid);
    String path = sb.toString();
    return path;
  }

  /**
   * Récupération de la liste des UUID
   * 
   * @return
   */
  private List < String > getUuidList() {
    File tempFile = new File(DIRECTORY);
    String[] listeModules = tempFile.list(new FilenameFilter() {
      @Override
      public boolean accept(File courantFichier, String nomFichier) {
        return new File(courantFichier, nomFichier).isDirectory();
      }
    });
    
    
    String[] listeRepertoire =null ;
    
	 for (String module : listeModules) {
		 
		StringBuilder sb = new StringBuilder();
		sb.append(DIRECTORY).append(File.separator).append(module);
	    File file = new File(sb.toString());
	    listeRepertoire = file.list(new FilenameFilter() {
		      @Override
		      public boolean accept(File courantFichier, String nomFichier) {
		        return new File(courantFichier, nomFichier).isDirectory();
		      }
		});	
	  }       
    
    return Arrays.asList(listeRepertoire);
  }

  /**
   * Création du répertoire associé au document contenant le fichier et son metadata
   * 
   * @param document
   */
  private String creerRepertoire(DocumentPhysique document) {
    String path = getPathRepertoire(document);
    creerRepertoire(path);
    return path;
  }

  /**
   * Récupération du Path du répertoire
   * 
   * @param document
   * @return
   */
  private String getPathRepertoire(DocumentPhysique document) {
    return getRepertoirePath(document.getUuid(), document.getModule());
  }

  /**
   * Récuperer le path physique du répertoire
   * 
   * @param uuid
   * @return
   */
  private String getRepertoirePath(String uuid, String module) {
    StringBuilder sb = new StringBuilder();
    sb.append(DIRECTORY).append(File.separator).append(module).append(File.separator).append(uuid);
    String path = sb.toString();
    return path;
  }

  /**
   * Création du répertoire
   * 
   * @param path
   */
  private void creerRepertoire(String path) {
    File file = new File(path);
    file.mkdirs();
  }

  /**
   * Sauvgarder les données concernant le fichier
   * 
   * @param document
   * @throws IOException
   */
  private void sauvegarderDonneesFichier(DocumentPhysique document) throws IOException {
    String path = getPathRepertoire(document);
    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
      new File(new File(path), document.getNomFichier())));
    stream.write(document.getFileData());
    stream.close();
  }

  /**
   * Méthode de sauvegarde des fichiers properties
   * 
   * @param document
   * @throws IOException
   */
  private void savegarderMetaData(DocumentPhysique document) throws IOException {
    String path = getPathRepertoire(document);
    Properties props = document.createProperties();
    File f = new File(new File(path), META_DATA_FILE_NAME);
    OutputStream out = new FileOutputStream(f);
    props.store(out, "Document meta data");
  }







@Override
public DocumentMetadata sauvegarderDocument(DocumentPhysique document, String dIRECTORY) {

	this.DIRECTORY = dIRECTORY;
    try {
      creerRepertoire(document);
      sauvegarderDonneesFichier(document);
      savegarderMetaData(document);
      return document.getMetadata();

    } catch (IOException e) {
      String message = "Erreur d'insertion du document";
      LOGGER.error(message, e);
      throw new RuntimeException(message, e);
    }

	
}



}
