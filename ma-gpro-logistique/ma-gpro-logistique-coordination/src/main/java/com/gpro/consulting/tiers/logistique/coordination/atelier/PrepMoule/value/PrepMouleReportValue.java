package com.gpro.consulting.tiers.logistique.coordination.atelier.PrepMoule.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * The Class MiseReportValue.
 *
 * @author Hamdi etteieb
 * @since 07/01/2019
 */
public class PrepMouleReportValue {
	

	/** The id. */
	private Long id;
	
	private Long idMoule;

	/** The reference. */
	private String reference;

	private String designation;
	
	private String machine;
	
	private String emplacement;
	 
	private Calendar datePrep;
	

	/** The report stream. */
	private InputStream reportStream;

	/** The params. */
	private HashMap<String, Object> params;

	/** The j R bean collection data source. */
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;

	/** The file name. */
	private String fileName;



	/**
	 * Gets the report stream.
	 *
	 * @return the report stream
	 */
	public InputStream getReportStream() {
		return reportStream;
	}

	/**
	 * Sets the report stream.
	 *
	 * @param reportStream
	 *            the new report stream
	 */
	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public HashMap<String, Object> getParams() {
		return params;
	}

	/**
	 * Sets the params.
	 *
	 * @param params
	 *            the params
	 */
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	/**
	 * Gets the JR bean collection data source.
	 *
	 * @return the JR bean collection data source
	 */

	
	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName
	 *            the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMoule() {
		return idMoule;
	}

	public void setIdMoule(Long idMoule) {
		this.idMoule = idMoule;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Calendar getDatePrep() {
		return datePrep;
	}

	public void setDatePrep(Calendar datePrep) {
		this.datePrep = datePrep;
	}

	@Override
	public String toString() {
		return "PrepMouleReportValue [id=" + id + ", idMoule=" + idMoule + ", reference=" + reference + ", designation="
				+ designation + ", machine=" + machine + ", emplacement=" + emplacement + ", datePrep=" + datePrep
				+ ", reportStream=" + reportStream + ", params=" + params + ", jRBeanCollectionDataSource="
				+ jRBeanCollectionDataSource + ", fileName=" + fileName + "]";
	}




}
