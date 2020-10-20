package com.erp.socle.j2ee.mt.socle.report;

import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public interface GestionnaireDownload {
	
	public void download(String type, InputStream reportStream, HashMap < String, Object > params, 
			String fileName, JRBeanCollectionDataSource jRBeanCollectionDataSource, 
			HttpServletResponse response);

}
