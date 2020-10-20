package com.erp.socle.j2ee.mt.socle.report;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

public interface GestionnaireExport {
	
	public HttpServletResponse export(String type, 
			JasperPrint jp, 
			HttpServletResponse response,
			ByteArrayOutputStream baos,String fileName);
	
	public void exportXls(JasperPrint jp, ByteArrayOutputStream baos);

}
