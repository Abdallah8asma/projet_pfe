package com.gpro.consulting.tiers.logistique.rest.report.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


@Component
public class ExcelUtilsXlsx {
	  public static CellStyle arial10format = null;
	    public static CellStyle arial12format = null;
		public static CellStyle arial14format = null;
		public static CellStyle arial_bold_s9_white_black = null;
		public static CellStyle arial_bold_s10_black_AQUA = null;
		public static CellStyle arial_bold_s10_white_AUTO = null;
		
		public static CellStyle boldRed2 = null ;
		public static CellStyle boldRed3 = null;
		public static CellStyle boldRed5 = null;
		public static CellStyle boldRed = null ;
		public static CellStyle boldTitre  = null ;
		
		public static CellStyle TAHOMA_12_BOLD_CENTER = null;
		public static CellStyle ARIAL_10_NO_BOLD_CENTER = null;
		
		public static CellStyle ARIAL_10_NO_BOLD_LEFT = null;
		
		public static CellStyle ARIAL_10_BOLD_CENTER_BORDER = null;
	
		
		
		
		
		//Font
		public static Font arial10font = null;
		public static Font arial12font = null;
	    public static Font arial14font = null;

	    
		public static void init(Workbook workbook) 
		{

			try {
				
				

				Font arial_bold_s9 =workbook.createFont();
				arial_bold_s9.setFontName("Arial");
				arial_bold_s9.setFontHeightInPoints((short) 9);
				arial_bold_s9.setBold(true);
				arial_bold_s9_white_black = workbook.createCellStyle() ; 
				arial_bold_s9_white_black.setFont(arial_bold_s9);
				arial_bold_s9_white_black.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
				
				arial_bold_s9_white_black.setAlignment(CellStyle.ALIGN_LEFT);
				arial_bold_s9_white_black.setWrapText(true);
				arial_bold_s9_white_black.setBorderBottom(CellStyle.BORDER_DOTTED);
				arial_bold_s9_white_black.setBorderRight( CellStyle.BORDER_THIN);
				arial_bold_s9_white_black.setBorderLeft( CellStyle.BORDER_THIN);
				arial_bold_s9_white_black.setAlignment(CellStyle.ALIGN_LEFT);
				
				
				//arial_bold_s10_black_AQUA
				Font font2 = workbook.createFont();
				font2.setFontName("Arial");
				font2.setFontHeightInPoints((short) 10);
				font2.setBold(true);
				arial_bold_s10_black_AQUA = workbook.createCellStyle();
				arial_bold_s9_white_black.setFont(font2);
				arial_bold_s10_black_AQUA.setBorderBottom(CellStyle.BORDER_THIN);
				arial_bold_s10_black_AQUA.setBorderLeft(CellStyle.BORDER_THIN);
				arial_bold_s10_black_AQUA.setBorderRight(CellStyle.BORDER_THIN);
				arial_bold_s10_black_AQUA.setBorderTop(CellStyle.BORDER_THIN);
				arial_bold_s10_black_AQUA.setWrapText(true);
				arial_bold_s10_black_AQUA.setAlignment(CellStyle.ALIGN_CENTER);
				arial_bold_s10_black_AQUA.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
				arial_bold_s10_black_AQUA.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
				
				
				//arial_bold_s10_white_AUTO
				Font font3 = workbook.createFont();
				font3.setFontName("Arial");
				font3.setFontHeightInPoints((short) 10);
				font3.setBold(true);
				font3.setColor(IndexedColors.WHITE.getIndex());
				arial_bold_s10_white_AUTO = workbook.createCellStyle();
				arial_bold_s10_white_AUTO.setFont(font3);
				arial_bold_s10_white_AUTO.setBorderBottom(CellStyle.BORDER_THIN);
				arial_bold_s10_white_AUTO.setBorderLeft(CellStyle.BORDER_THIN);
				arial_bold_s10_white_AUTO.setBorderRight(CellStyle.BORDER_THIN);
				arial_bold_s10_white_AUTO.setBorderTop(CellStyle.BORDER_THIN);
				arial_bold_s10_white_AUTO.setWrapText(true);
				arial_bold_s10_white_AUTO.setAlignment(CellStyle.ALIGN_CENTER);
				arial_bold_s10_white_AUTO.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
				arial_bold_s10_white_AUTO.setFillBackgroundColor(IndexedColors.AUTOMATIC.getIndex());
				
				
				
				
				
				//others
				arial14font = workbook.createFont();
				arial14font.setFontName("Arial");
				arial14font.setFontHeightInPoints((short) 14);
				arial14font.setBold(true);
				arial14font.setColor(IndexedColors.LIGHT_BLUE.getIndex());
				arial14format = workbook.createCellStyle();
				arial14format.setFont(arial_bold_s9);
				arial14format.setAlignment(CellStyle.ALIGN_CENTER);
				arial14format.setBorderBottom(CellStyle.BORDER_THIN);
				arial14format.setBorderLeft(CellStyle.BORDER_THIN);
				arial14format.setBorderRight(CellStyle.BORDER_THIN);
				arial14format.setBorderTop(CellStyle.BORDER_THIN);
				arial14format.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());

				arial10font = workbook.createFont();
				arial10font.setFontName("Arial");
				arial10font.setFontHeightInPoints((short) 10);
				arial10font.setBold(true);
				arial10format = workbook.createCellStyle();
				arial10format.setFont(arial10font);
				arial10format.setAlignment(CellStyle.ALIGN_CENTER);
				arial10format.setBorderBottom(CellStyle.BORDER_THIN);
				arial10format.setBorderLeft(CellStyle.BORDER_THIN);
				arial10format.setBorderRight(CellStyle.BORDER_THIN);
				arial10format.setBorderTop(CellStyle.BORDER_THIN);
				arial10format.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

				arial12font = workbook.createFont();
				arial14font.setFontName("Arial");
				arial14font.setFontHeightInPoints((short) 12);
				arial14font.setBold(true);
				arial12format = workbook.createCellStyle();
				arial12format.setFont(arial14font);
				arial12format.setAlignment(CellStyle.ALIGN_CENTER);
				arial12format.setBorderBottom(CellStyle.BORDER_THIN);
				arial12format.setBorderLeft(CellStyle.BORDER_THIN);
				arial12format.setBorderRight(CellStyle.BORDER_THIN);
				arial12format.setBorderTop(CellStyle.BORDER_THIN);

				
				//boldTitre
				
				Font fontTitre = workbook.createFont();
				fontTitre.setFontName("Arial");
				fontTitre.setFontHeightInPoints((short) 16);
				fontTitre.setBold(true);
				fontTitre.setUnderline((byte) 5);
				fontTitre.setItalic(false);
				
				boldTitre = workbook.createCellStyle();
				boldTitre.setFont(fontTitre);
				boldTitre.setWrapText(true);
				boldTitre.setAlignment(CellStyle.ALIGN_CENTER);
				
				//boldRed
				
				Font font = workbook.createFont();
				font.setFontName("Arial");
				font.setFontHeightInPoints((short) 10);
				font.setBold(true);
				
				boldRed = workbook.createCellStyle();
				boldRed.setFont(font);
				// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
				boldRed.setAlignment(CellStyle.ALIGN_LEFT);
				boldRed.setWrapText(true);
				boldRed.setBorderBottom( CellStyle.BORDER_DOTTED);
				boldRed.setBorderRight( CellStyle.BORDER_THIN);
				boldRed.setBorderLeft(CellStyle.BORDER_THIN);
				boldRed.setAlignment(CellStyle.ALIGN_LEFT);
				
				
				//boldRed2
				
				Font font6 = workbook.createFont();
				font6.setFontName("Arial");
				font6.setFontHeightInPoints((short) 10);
				font6.setBold(true);
				boldRed2 = workbook.createCellStyle();
				boldRed2.setFont(font6);
				boldRed2.setBorderBottom(CellStyle.BORDER_THIN);
				boldRed2.setBorderLeft(CellStyle.BORDER_THIN);
				boldRed2.setBorderRight(CellStyle.BORDER_THIN);
				boldRed2.setBorderTop(CellStyle.BORDER_THIN);
				boldRed2.setWrapText(true);
				boldRed2.setAlignment(CellStyle.ALIGN_CENTER);
				
				//boldRed3
				
				Font font4 = workbook.createFont();
				font4.setFontName("Arial");
				font4.setFontHeightInPoints((short) 10);
				font4.setBold(false);	
				boldRed3 = workbook.createCellStyle();
				boldRed3.setFont(font4);
				boldRed3.setWrapText(true);
				boldRed3.setAlignment(CellStyle.ALIGN_LEFT);
				
				
				//boldRed5
				Font font5 = workbook.createFont();
				font5.setFontName("Arial");
				font5.setFontHeightInPoints((short) 10);
				font5.setBold(true);		
				 boldRed5 =workbook.createCellStyle();
				 boldRed5.setFont(font5);
				// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
				boldRed5.setWrapText(true);
				boldRed5.setAlignment(CellStyle.ALIGN_LEFT);
				
				
				
				// new Font(Font.TAHOMA, 12, Font.BOLD);
				//TAHOMA_12_BOLD_CENTER
				
				Font wfn6 = workbook.createFont();
				wfn6.setFontName("TAHOMA");
				wfn6.setFontHeightInPoints((short) 12);
				wfn6.setBold(true);
				TAHOMA_12_BOLD_CENTER =workbook.createCellStyle();
				TAHOMA_12_BOLD_CENTER.setFont(wfn6);
				TAHOMA_12_BOLD_CENTER.setWrapText(true);
				TAHOMA_12_BOLD_CENTER.setAlignment(CellStyle.ALIGN_CENTER);
				
				
				
				//ARIAL_10_NO_BOLD_CENTER
				
				Font WF_ARIAL_10_NO_BOLD_CENTER = workbook.createFont();
				WF_ARIAL_10_NO_BOLD_CENTER.setFontName("Arial");
				WF_ARIAL_10_NO_BOLD_CENTER.setFontHeightInPoints((short) 10);
				WF_ARIAL_10_NO_BOLD_CENTER.setBold(false);
				ARIAL_10_NO_BOLD_CENTER = workbook.createCellStyle();
				ARIAL_10_NO_BOLD_CENTER.setFont(WF_ARIAL_10_NO_BOLD_CENTER);
				ARIAL_10_NO_BOLD_CENTER.setWrapText(true);
				ARIAL_10_NO_BOLD_CENTER.setAlignment(CellStyle.ALIGN_CENTER);
				
				//ARIAL_10_NO_BOLD_LEFT
				
				Font WF_ARIAL_10_NO_BOLD_LEFT = workbook.createFont();
				WF_ARIAL_10_NO_BOLD_LEFT.setFontName("Arial");
				WF_ARIAL_10_NO_BOLD_LEFT.setFontHeightInPoints((short) 10);
				WF_ARIAL_10_NO_BOLD_LEFT.setBold(false);
				ARIAL_10_NO_BOLD_LEFT =workbook.createCellStyle();
				ARIAL_10_NO_BOLD_LEFT.setFont(WF_ARIAL_10_NO_BOLD_LEFT);
				ARIAL_10_NO_BOLD_LEFT.setWrapText(true);
				ARIAL_10_NO_BOLD_LEFT.setAlignment(CellStyle.ALIGN_LEFT);
				
				
				
				
				//ARIAL_14_BOLD_CENTER_BORDER
	         
				Font WF_ARIAL_10_BOLD_CENTER_BORDER = workbook.createFont();
				WF_ARIAL_10_BOLD_CENTER_BORDER.setFontName("Arial");
				WF_ARIAL_10_BOLD_CENTER_BORDER.setFontHeightInPoints((short) 10);
				WF_ARIAL_10_BOLD_CENTER_BORDER.setBold(true);
				WF_ARIAL_10_BOLD_CENTER_BORDER.setUnderline((byte) 5);
				ARIAL_10_BOLD_CENTER_BORDER = workbook.createCellStyle();
				ARIAL_10_BOLD_CENTER_BORDER.setFont(WF_ARIAL_10_BOLD_CENTER_BORDER);
				ARIAL_10_BOLD_CENTER_BORDER.setWrapText(true);
				ARIAL_10_BOLD_CENTER_BORDER.setAlignment(CellStyle.ALIGN_CENTER);
				
				
				ARIAL_10_BOLD_CENTER_BORDER.setBorderTop(CellStyle.BORDER_DOTTED);
				ARIAL_10_BOLD_CENTER_BORDER.setBorderBottom(CellStyle.BORDER_DOTTED);
				ARIAL_10_BOLD_CENTER_BORDER.setBorderRight(CellStyle.BORDER_THIN);
				ARIAL_10_BOLD_CENTER_BORDER.setBorderLeft(CellStyle.BORDER_THIN);
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		public ExcelUtilsXlsx( )
		{
			
		}
		
		
	}
 