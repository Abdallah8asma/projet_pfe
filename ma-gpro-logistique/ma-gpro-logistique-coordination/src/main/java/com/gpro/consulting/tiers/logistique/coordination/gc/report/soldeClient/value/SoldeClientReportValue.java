package com.gpro.consulting.tiers.logistique.coordination.gc.report.soldeClient.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.gpro.consulting.tiers.logistique.coordination.gc.report.ficheclient.value.FicheClientElementValue;
import com.gpro.consulting.tiers.logistique.coordination.gc.soldeClient.value.SoldeClientValue;

/**
 * @author Ameni Berrich
 *
 */
public class SoldeClientReportValue {
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long partieIntId;
	private Boolean bloque;
	
	private String clientReference;
	private String clientAbreviation;
	
	private List<SoldeClientValue> listSoldeClient = new ArrayList<SoldeClientValue>();

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

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Boolean getBloque() {
		return bloque;
	}

	public void setBloque(Boolean bloque) {
		this.bloque = bloque;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public List<SoldeClientValue> getListSoldeClient() {
		return listSoldeClient;
	}

	public void setListSoldeClient(List<SoldeClientValue> listSoldeClient) {
		this.listSoldeClient = listSoldeClient;
	}

}
