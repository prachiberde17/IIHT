package com.assessment.reports.manager.detailedreports;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.assessment.reports.manager.NoTableLayoutManager;
import com.assessment.reports.manager.UserSkillArea;
import com.assessment.reports.manager.UserTrait;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.builder.DJBar3DChartBuilder;
import ar.com.fdvs.dj.domain.chart.builder.DJPie3DChartBuilder;
import ar.com.fdvs.dj.domain.chart.plot.DJAxisFormat;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportManagerTrait {
	
	 protected AbstractColumn skill;
	   protected AbstractColumn percentage;
	//   protected AbstractColumn qtyCol;
	   private DynamicReportBuilder builder;
	   
	   private DynamicReportBuilder builder2;
	   public ReportManagerTrait(){
		   skill = ColumnBuilder.getNew()
				      .setColumnProperty("skillarea", String.class.getName())
				      .setTitle("Skill").setWidth(120).build();
		   percentage = ColumnBuilder.getNew()
				      .setColumnProperty("percentage", Float.class.getName())
				      .setTitle("Percentage").setWidth(400).build();
		   builder = new DynamicReportBuilder();
		   builder.addColumn(skill);
		      builder.addColumn(percentage);
		      
		      builder2 = new DynamicReportBuilder();
			  builder2.addColumn(skill);
			  builder2.addColumn(percentage);
	   }
	   
	   public Style getHeaderStyle() {
		      Style headerStyle = new Style();
		      headerStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
		      headerStyle.setBorderBottom(Border.PEN_2_POINT());
		      headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		      headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		      headerStyle.setBackgroundColor(Color.DARK_GRAY);
		      headerStyle.setTextColor(Color.WHITE);
		      headerStyle.setTransparency(Transparency.OPAQUE);
		      return headerStyle;
		   }

	   private DJChart createPieChart() {
		      DJChart chart = new DJPie3DChartBuilder()
		     .setX(20).setY(10).setWidth(500)
		    	//.set	  
		      .setHeight(500).setCentered(false)
		      .setBackColor(Color.LIGHT_GRAY).setShowLegend(true)
		      .setPosition(DJChartOptions.POSITION_FOOTER)
		      .setTitle("Skill Profile").setTitleColor(Color.DARK_GRAY)
		      .setTitleFont(Font.ARIAL_BIG_BOLD)
		      .setSubtitle("Percentage vis-a-vis Skill Areas")
		      .setSubtitleColor(Color.DARK_GRAY)
		      .setSubtitleFont(Font.GEORGIA_SMALL_BOLD)
		      .setLegendColor(Color.DARK_GRAY)
		      .setLegendFont(Font.ARIAL_SMALL_BOLD)
		      .setLegendBackgroundColor(Color.WHITE)
		      .setLegendPosition(DJChartOptions.EDGE_BOTTOM)
		      .setTitlePosition(DJChartOptions.EDGE_TOP)
		      .setLineStyle(DJChartOptions.LINE_STYLE_SOLID)
		      .setLineWidth(2)
		      .setLineColor(Color.black).setPadding(5)
		      .setKey((PropertyColumn) skill).addSerie(percentage)
		      .setCircular(true).build();
		      return chart;
		   }
	   
	public DynamicReport buildPieChart(String candidateName, String testName){
		 StyleBuilder titleStyle=new StyleBuilder(true);
         titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
       //  titleStyle.setf
         Font font = new Font();
         font.setFontSize(16f);
       titleStyle.setFont(font);
         
         StyleBuilder subTitleStyle=new StyleBuilder(true);
         subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        // subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_TIMES_NEW_ROMAN, true));
         builder.setTitle(candidateName);
         builder.setTitleStyle(titleStyle.build());
         builder.setSubtitle(testName);
         builder.setSubtitleStyle(subTitleStyle.build());
         builder.setUseFullPageWidth(true);
		
		//builder.
		builder.setAllowDetailSplit(true);
		 builder.addChart(createPieChart());
	     return builder.build();
	}
	
	 public DynamicReport buildBarReport(String candidateName, String testName) throws Exception {
		 StyleBuilder titleStyle=new StyleBuilder(true);
         titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
       //  titleStyle.setf
         Font font = new Font();
         font.setFontSize(16f);
       titleStyle.setFont(font);
         
         StyleBuilder subTitleStyle=new StyleBuilder(true);
         subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        // subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_TIMES_NEW_ROMAN, true));
         builder2.setTitle(testName+" - "+candidateName);
         builder2.setTitleStyle(titleStyle.build());
         //builder2.setSubtitle("Jatin Sutaria - General_Technology_Screening ");
         builder2.setSubtitleStyle(subTitleStyle.build());
         builder2.setUseFullPageWidth(true);
		
		//builder.
		builder2.setAllowDetailSplit(true);
		//builder2.s
		
	      builder2.addChart(createBarChart());
	      return builder2.build();
	   }
	
	 private DJChart createBarChart() {

	      DJAxisFormat categoryAxisFormat = new DJAxisFormat("");
	      categoryAxisFormat.setLabelFont(Font.ARIAL_SMALL);
	    //  categoryAxisFormat.setLabelColor(Color.pink);
	      categoryAxisFormat.setTickLabelFont(Font.TIMES_NEW_ROMAN_MEDIUM_BOLD);
	      categoryAxisFormat.setTickLabelColor(new Color(18,102,237));
	      categoryAxisFormat.setTickLabelMask("");
	      categoryAxisFormat.setLineColor(Color.DARK_GRAY);
	     // categoryAxisFormat.set

	      DJAxisFormat valueAxisFormat = new DJAxisFormat("");//skill
	      valueAxisFormat.setLabelFont(Font.ARIAL_SMALL);
	     // valueAxisFormat.setLabelColor(Color.pink);
	      valueAxisFormat.setTickLabelFont(Font.TIMES_NEW_ROMAN_MEDIUM_BOLD);
	      valueAxisFormat.setTickLabelColor(new Color(18,102,237));
	      valueAxisFormat.setTickLabelMask("#,##0.0");
	      valueAxisFormat.setLineColor(Color.DARK_GRAY);
	     
	    //  valueAxisFormat.set
	      
	    

	      DJChart chart = new DJBar3DChartBuilder()
	      .setX(10).setY(10).setWidth(550)
	      .setHeight(420).setCentered(false)
	      .setPosition(DJChartOptions.EDGE_TOP)
	      .setBackColor(Color.LIGHT_GRAY).setShowLegend(false)
	     // .setPosition(DJChartOptions.LINE_STYLE_SOLID)
	    //  .setTitle("Percentage - Area Report").setTitleColor(Color.DARK_GRAY)
	      .setTitleFont(Font.ARIAL_BIG_BOLD)
	      .setCustomizerClass("com.assessment.reports.manager.detailedreports.ColorCustomizer")
	    //  .setLabelRotation(360)
	      //.setSubtitle("Models sold in year(s) 2009-2015")
	    //  .setSubtitleColor(Color.DARK_GRAY)
	      .setSubtitleFont(Font.GEORGIA_SMALL_BOLD)
	     // .setLegendColor(Color.PINK)
	      .setLegendFont(Font.COMIC_SANS_BIG_BOLD)
	      .setLegendBackgroundColor(Color.WHITE)
	      .setLegendPosition(DJChartOptions.EDGE_TOP)
	     // .setShowTickLabels(true)
	     //.set
	      .setTitlePosition(DJChartOptions.EDGE_TOP)
	      .setLineStyle(DJChartOptions.LINE_STYLE_SOLID)
	      .setLineWidth(2)
	      .setLineColor(Color.black).setPadding(0)
	    //  StringExpression expression = StringExpression
	      .setCategory((PropertyColumn) skill).addSerie(percentage)
	      .setShowLabels(false).setCategoryAxisFormat(categoryAxisFormat).setLabelRotation(90.0)
	    //  .setShowLegend(true)
	      .setValueAxisFormat(valueAxisFormat).build();
	      
	      return chart;
	   }
	   
	 
	 
	public String buildComprehensiveReport(List<UserTrait> traits, List<UserSkillArea> areas, String testName, String canddateName) throws Exception{
		try {
			JasperReportBuilder report = report();
			report.addColumn(col.column("Profiling Param", "trait", (DRIDataType) type.stringType()).setWidth(100));
			report.addColumn(col.column("Detail", "description", (DRIDataType) type.stringType()).setWidth(450));
			
			
			JasperReportBuilder builder = report
					  .setTemplate(com.assessment.reports.manager.Templates2.reportTemplate);
//	 .title(com.assessment.reports.manager.Templates.createTitleComponent("Comprehensive Report - Jatin sutaria"));
			
			//JasperReportBuilder builder = report.ignorePageWidth();
			Properties props = new Properties();
			props.put("net.sf.jasperreports.awt.ignore.missing.font", "true");
			//builder = builder.pageFooter(com.assessment.reports.manager.Templates.footerComponent, cmp.line())
			builder = builder.setDataSource(traits)
					//.highlightDetailOddRows()
					//.setBackgroundStyle(DynamicReports.stl.style(DynamicReports.stl.pen1Point()))
					 .setIgnorePagination(true)
					 
					// .highlightDetailOddRows()
					 .setParameter("net.sf.jasperreports.awt.ignore.missing.font", "true")
					 .setProperties(props);
					 
			
			JasperPrint jasperPrint  = builder.toJasperPrint();
			jasperPrint.setPageHeight(600);
			DynamicReport dynamicReport = buildBarReport(canddateName, testName);
			Map<String, String> properties = new HashMap<>();
			properties.put("net.sf.jasperreports.awt.ignore.missing.font", "true");
			dynamicReport.setProperties(properties);
			
			JasperPrint jp1 = DynamicJasperHelper.generateJasperPrint(	dynamicReport, new NoTableLayoutManager(),areas);
			DynamicReport dynamicReport2 = buildPieChart(canddateName, testName);
			jp1.setPageHeight(600);
			dynamicReport2.setProperties(properties);
			
			 List<JRPrintPage> pages = jasperPrint.getPages();
			 JasperPrint jp2 = DynamicJasperHelper.generateJasperPrint(	dynamicReport2, new NoTableLayoutManager(),areas);
			 jp2.setPageHeight(500);
			 for(int i=0;i<jp2.getPages().size();i++){
				 JRPrintPage object = (JRPrintPage) jp2.getPages().get(i);
				 jp1.addPage(object);
			 }
			  for (int j = 0; j < pages.size(); j++) {
			     JRPrintPage object = (JRPrintPage) pages.get(j);
			     jp1.addPage(object);

			  }
			 
			  String fileName = canddateName+"-"+testName+".pdf";
			  System.out.println("Saving report by following name "+fileName);
			  OutputStream output = new FileOutputStream(new File(fileName)); 
			  JasperExportManager.exportReportToPdfStream(jp1, output); 
			  System.out.println("Report saved by following name "+fileName);
			  return fileName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
			throw e;
		}
	}
	
	

}
