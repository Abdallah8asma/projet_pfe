package com.gpro.consulting.tiers.commun.rest.report.utilities;

import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;

import org.springframework.stereotype.Component;


@Component
public class ExcelUtils {
	

	
	
	
    public static WritableCellFormat arial10format = null;
    public static WritableCellFormat arial12format = null;
	public static WritableCellFormat arial14format = null;
	public static WritableCellFormat arial_bold_s9_white_black = null;
	public static WritableCellFormat arial_bold_s10_black_AQUA = null;
	public static WritableCellFormat arial_bold_s10_white_AUTO = null;
	
	public static WritableCellFormat boldRed2 = null ;
	public static WritableCellFormat boldRed3 = null;
	public static WritableCellFormat boldRed5 = null;
	public static WritableCellFormat boldRed = null ;
	public static WritableCellFormat boldTitre  = null ;
	
	public static WritableCellFormat TAHOMA_12_BOLD_CENTER = null;
	public static WritableCellFormat ARIAL_10_NO_BOLD_CENTER = null;
	
	public static WritableCellFormat ARIAL_10_NO_BOLD_LEFT = null;
	
	public static WritableCellFormat ARIAL_10_BOLD_CENTER_BORDER = null;
	
	
	
	
	
	//WritableFont
	public static WritableFont arial10font = null;
	public static WritableFont arial12font = null;
    public static WritableFont arial14font = null;
    
	public static void init() 
	{
	
		try {

			
			//arial_bold_s9_white_black
			
			WritableFont arial_bold_s9 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
			arial_bold_s9_white_black = new WritableCellFormat(arial_bold_s9);
			arial_bold_s9_white_black.setBackground(jxl.format.Colour.BLACK);
			
			arial_bold_s9_white_black.setAlignment(Alignment.LEFT);
			arial_bold_s9_white_black.setWrap(true);
			arial_bold_s9_white_black.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
			arial_bold_s9_white_black.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
			arial_bold_s9_white_black.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
			arial_bold_s9_white_black.setAlignment(Alignment.LEFT);
			
			
			//arial_bold_s10_black_AQUA
			WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			arial_bold_s10_black_AQUA = new WritableCellFormat(font2);
			arial_bold_s10_black_AQUA.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
			arial_bold_s10_black_AQUA.setWrap(true);
			arial_bold_s10_black_AQUA.setAlignment(Alignment.CENTRE);
			arial_bold_s10_black_AQUA.setBackground(jxl.format.Colour.BLACK);
			arial_bold_s10_black_AQUA.setBackground(jxl.format.Colour.AQUA);
			
			
			//arial_bold_s10_white_AUTO
			WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			font3.setColour(Colour.WHITE);
			arial_bold_s10_white_AUTO = new WritableCellFormat(font3);
			arial_bold_s10_white_AUTO.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
			arial_bold_s10_white_AUTO.setWrap(true);
			arial_bold_s10_white_AUTO.setAlignment(Alignment.CENTRE);
			arial_bold_s10_white_AUTO.setBackground(jxl.format.Colour.BLACK);
			arial_bold_s10_white_AUTO.setBackground(jxl.format.Colour.AUTOMATIC);
			
			
			
			
			
			//others
		    arial14font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
            arial14font.setColour(jxl.format.Colour.LIGHT_BLUE);
            arial14format = new WritableCellFormat(arial14font);
            arial14format.setAlignment(jxl.format.Alignment.CENTRE);
            arial14format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
            arial14format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);

            arial10font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            arial10format = new WritableCellFormat(arial10font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);
            arial10format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
            arial10format.setBackground(Colour.GRAY_25);

            arial12font = new WritableFont(WritableFont.ARIAL, 12);
            arial12format = new WritableCellFormat(arial12font);
            arial12format.setAlignment(jxl.format.Alignment.CENTRE);
            arial12format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	
            
            //boldTitre
            
            WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
    				UnderlineStyle.NO_UNDERLINE);
    	    boldTitre = new WritableCellFormat(fontTitre);
    		boldTitre.setWrap(true);
    		boldTitre.setAlignment(Alignment.CENTRE);
            
            //boldRed
            
            WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
    	    boldRed = new WritableCellFormat(font);
    		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
    		boldRed.setAlignment(Alignment.LEFT);
    		boldRed.setWrap(true);
    		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
    		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
    		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
    		boldRed.setAlignment(Alignment.LEFT);
            
    		
    		//boldRed2
    		
    		WritableFont font6 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
    	    boldRed2 = new WritableCellFormat(font6);
    		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
    		boldRed2.setWrap(true);
    		boldRed2.setAlignment(Alignment.CENTRE);
    		
            //boldRed3
            
    		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
    	    boldRed3 = new WritableCellFormat(font4);
    		boldRed3.setWrap(true);
    		boldRed3.setAlignment(Alignment.LEFT);
    		
    		
    		//boldRed5
    		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
    		 boldRed5 = new WritableCellFormat(font5);
    		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
    		boldRed5.setWrap(true);
    		boldRed5.setAlignment(Alignment.LEFT);
    		
    		
    		
    		
    		//TAHOMA_12_BOLD_CENTER
    		
    		WritableFont wfn6 = new WritableFont(WritableFont.TAHOMA, 12, WritableFont.BOLD);
    		TAHOMA_12_BOLD_CENTER = new WritableCellFormat(wfn6);
    		TAHOMA_12_BOLD_CENTER.setWrap(true);
    		TAHOMA_12_BOLD_CENTER.setAlignment(Alignment.CENTRE);
    		
    		
    		
    		//ARIAL_10_NO_BOLD_CENTER
    		
    		WritableFont WF_ARIAL_10_NO_BOLD_CENTER = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
    		ARIAL_10_NO_BOLD_CENTER = new WritableCellFormat(WF_ARIAL_10_NO_BOLD_CENTER);
    		ARIAL_10_NO_BOLD_CENTER.setWrap(true);
    		ARIAL_10_NO_BOLD_CENTER.setAlignment(Alignment.CENTRE);
    		
    	    //ARIAL_10_NO_BOLD_LEFT
    		
    		WritableFont WF_ARIAL_10_NO_BOLD_LEFT = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
    		ARIAL_10_NO_BOLD_LEFT = new WritableCellFormat(WF_ARIAL_10_NO_BOLD_LEFT);
    		ARIAL_10_NO_BOLD_LEFT.setWrap(true);
    		ARIAL_10_NO_BOLD_LEFT.setAlignment(Alignment.LEFT);
    		
    		
    		
    	    
            //ARIAL_14_BOLD_CENTER_BORDER
            
            WritableFont WF_ARIAL_10_BOLD_CENTER_BORDER = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
    				UnderlineStyle.NO_UNDERLINE);
            ARIAL_10_BOLD_CENTER_BORDER = new WritableCellFormat(WF_ARIAL_10_BOLD_CENTER_BORDER);
            ARIAL_10_BOLD_CENTER_BORDER.setWrap(true);
            ARIAL_10_BOLD_CENTER_BORDER.setAlignment(Alignment.CENTRE);
    		
            
            ARIAL_10_BOLD_CENTER_BORDER.setBorder(jxl.format.Border.TOP, BorderLineStyle.DOTTED);
            ARIAL_10_BOLD_CENTER_BORDER.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
            ARIAL_10_BOLD_CENTER_BORDER.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
            ARIAL_10_BOLD_CENTER_BORDER.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
            
      
    		
            
		
		} catch (WriteException e) {
			
			e.printStackTrace();
		}
	
		
	}
	
	public ExcelUtils()
	{
		init();
	}
	
	
}
