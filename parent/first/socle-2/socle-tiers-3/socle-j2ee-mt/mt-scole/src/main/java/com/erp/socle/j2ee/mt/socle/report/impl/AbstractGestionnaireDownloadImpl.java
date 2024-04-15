package com.erp.socle.j2ee.mt.socle.report.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author rkhaskho
 *
 */
public abstract class  AbstractGestionnaireDownloadImpl 
//implements GestionnaireDownload
{

  /**
   * Méthode de téléchargement du report
   * 
   * (non-Javadoc)
   * @see com.erp.socle.j2ee.mt.socle.report.IGestionnaireReport#download(java.lang.String, java.io.InputStream, javax.servlet.http.HttpServletResponse)
   */
  
  public static void download(String pType, InputStream reportStream, HashMap < String, Object > params, String fileName, JRBeanCollectionDataSource bRbeanColDataSource, HttpServletResponse pResponse) {
    try {
      
      //  Convert template to JasperDesign
      JasperDesign jd = JRXmlLoader.load(reportStream);
       
      //  Compile design to JasperReport
      JasperReport jr = JasperCompileManager.compileReport(jd);
       
      //  Create the JasperPrint object
      // Make sure to pass the JasperReport, report parameters, and data source
      JasperPrint jp = JasperFillManager.fillReport(jr, params, bRbeanColDataSource);
       
      //  Create an output byte stream where data will be written
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
       
      //  Export report
      GestionnaireExportImpl.export(pType, jp, pResponse, baos,generateDocName(fileName));
       
      //  Write to reponse stream
      write( pResponse, baos);
    
    } catch (JRException jre) {

      throw new RuntimeException(jre);
    }
    
  }
  
  
  /**
   * Writes the report to the output stream
   */
   private static void write(HttpServletResponse response,
       ByteArrayOutputStream baos) {
      
     try {

       // Retrieve output stream
       ServletOutputStream outputStream = response.getOutputStream();
       // Write to output stream
       baos.writeTo(outputStream);
       // Flush the stream
       outputStream.flush();
       

       
     } catch (Exception e) {

       throw new RuntimeException(e);
     }
   }
   
   
   /**
    * This function is used to genearate name each time we download PDF file.
    * 
    * @return
    */
   public static String generateDocName(String fileName) {
     
     FastDateFormat format = FastDateFormat.getInstance("_dd_MM_yyyy_hh_mm_ss");
     String currentDateAndTime = format.format(new Date());

     StringBuffer fileNameGenerated=new StringBuffer(fileName);
     
     fileNameGenerated.append(currentDateAndTime);
    
     return fileNameGenerated.toString();
   }

}
