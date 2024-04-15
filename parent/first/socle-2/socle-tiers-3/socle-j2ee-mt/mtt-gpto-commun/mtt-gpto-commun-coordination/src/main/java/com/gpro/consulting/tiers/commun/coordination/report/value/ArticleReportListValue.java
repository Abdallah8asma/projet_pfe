package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.elementBase.ArticleValue;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public class ArticleReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
  //  private List<ArticleValue>  bareCodeList=new ArrayList<ArticleValue>();
	
	
	
	private List<ArticleReportElementValue> productList = new ArrayList <ArticleReportElementValue>();

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

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public List<ArticleReportElementValue> getProductList() {
		return productList;
	}

	public void setProductList(List<ArticleReportElementValue> productList) {
		this.productList = productList;
	}

	
	
		
	
}
