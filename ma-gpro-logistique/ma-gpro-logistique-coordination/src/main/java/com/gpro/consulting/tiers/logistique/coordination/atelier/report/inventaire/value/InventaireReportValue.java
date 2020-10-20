package com.gpro.consulting.tiers.logistique.coordination.atelier.report.inventaire.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.gpro.consulting.tiers.logistique.coordination.atelier.rouleaufini.value.ElementResultatRechecheRouleauStandardValue;

/**
 * 
 * Classe valeur de l'inventaire
 * @author rkhaskho
 *
 */
public class InventaireReportValue {
	
	
	/** Paramètre du report */
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	
	List<ElementResultatRechecheRouleauStandardValue> elementsList;
	
	/** Critère de recherche */

	/** Client */
	private String client ;
	
	/** nombre Colis du */
	private String nombreColieDu;
	
	/** nombre Colis a  */
	private String nombreColieA;
	
	/** Entrepot */
	private String entrepot;

	/** Emplacement */
	private String emplacement ;
	
	/** Metrage du */
	private String metrageDu;
	
	/** Metrage a */
	private String metrageA;
	
	/** Liste de retour */
	/** Référence du produit */
	private String referenceProduit;
	
	/** Designation */
	private String designation;
	
	/** Metrage */
	private Double metrage;
	
	/** Poids */
	private Double poids;
	
	/** Nombre de colis */
	private Long nombreColis;
	
	/** Prix unitaire */
	private Double prixUnitaire;
	
	/** Prix Total   */
	private Double prixTotal;
	
	// added on 22/01/2016
	private Calendar dateEtat;
	private String orderBy;
	private String designationQuiContient;

	/**
	 * @return the reportStream
	 */
	public InputStream getReportStream() {
		return reportStream;
	}

	/**
	 * @param reportStream the reportStream to set
	 */
	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}

	/**
	 * @return the params
	 */
	public HashMap<String, Object> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	/**
	 * @return the jRBeanCollectionDataSource
	 */
	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	/**
	 * @param jRBeanCollectionDataSource the jRBeanCollectionDataSource to set
	 */
	public void setJRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * @return the nombreColieDu
	 */
	public String getNombreColieDu() {
		return nombreColieDu;
	}

	/**
	 * @param nombreColieDu the nombreColieDu to set
	 */
	public void setNombreColieDu(String nombreColieDu) {
		this.nombreColieDu = nombreColieDu;
	}

	/**
	 * @return the nombreColieA
	 */
	public String getNombreColieA() {
		return nombreColieA;
	}

	/**
	 * @param nombreColieA the nombreColieA to set
	 */
	public void setNombreColieA(String nombreColieA) {
		this.nombreColieA = nombreColieA;
	}

	/**
	 * @return the entrepot
	 */
	public String getEntrepot() {
		return entrepot;
	}

	/**
	 * @param entrepot the entrepot to set
	 */
	public void setEntrepot(String entrepot) {
		this.entrepot = entrepot;
	}

	/**
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}

	/**
	 * @param emplacement the emplacement to set
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * @return the metrageDu
	 */
	public String getMetrageDu() {
		return metrageDu;
	}

	/**
	 * @param metrageDu the metrageDu to set
	 */
	public void setMetrageDu(String metrageDu) {
		this.metrageDu = metrageDu;
	}

	/**
	 * @return the metrageA
	 */
	public String getMetrageA() {
		return metrageA;
	}

	/**
	 * @param metrageA the metrageA to set
	 */
	public void setMetrageA(String metrageA) {
		this.metrageA = metrageA;
	}

	/**
	 * @return the referenceProduit
	 */
	public String getReferenceProduit() {
		return referenceProduit;
	}

	/**
	 * @param referenceProduit the referenceProduit to set
	 */
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the metrage
	 */
	public Double getMetrage() {
		return metrage;
	}

	/**
	 * @param metrage the metrage to set
	 */
	public void setMetrage(Double metrage) {
		this.metrage = metrage;
	}

	/**
	 * @return the poids
	 */
	public Double getPoids() {
		return poids;
	}

	/**
	 * @param poids the poids to set
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}

	/**
	 * @return the nombreColis
	 */
	public Long getNombreColis() {
		return nombreColis;
	}

	/**
	 * @param nombreColis the nombreColis to set
	 */
	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	/**
	 * @return the prixUnitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal the prixTotal to set
	 */
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	/**
	 * @return the elementsList
	 */
	public List<ElementResultatRechecheRouleauStandardValue> getElementsList() {
		return elementsList;
	}

	/**
	 * @param elementsList the elementsList to set
	 */
	public void setElementsList(
			List<ElementResultatRechecheRouleauStandardValue> elementsList) {
		this.elementsList = elementsList;
	}

	public Calendar getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Calendar dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDesignationQuiContient() {
		return designationQuiContient;
	}

	public void setDesignationQuiContient(String designationQuiContient) {
		this.designationQuiContient = designationQuiContient;
	}

	
}
