package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * @author Samer Hassen
 * @since 04/01/2019
 *
 */
public class ProduitReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	
	private String codeBarre;
	
	private String designation;
	
	


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public InputStream getReportStream() {
		return reportStream;
	}


	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
		
	}


	public HashMap<String, Object> getParams() {
		return params;
	}


	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}


	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}


	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getCodeBarre() {
		return codeBarre;
	}


	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}
	
	
	
}
