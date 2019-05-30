package com.assessment.reports.manager;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.UserService;
import com.assessment.web.dto.TestAnswerData;
import com.assessment.web.dto.UserBySkillDTO;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ListLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HyperLinkComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
@Service
public class AssessmentReportsManager {
	static org.slf4j.Logger logger = LoggerFactory.getLogger(AssessmentReportsManager.class);

	
	
	static {
		JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
	}
	
	@Autowired
	UserTestSessionRepository userTestSessionRepository;
	@Autowired
	UserService userService;
	
	public String generateUserSessionReport(List<TestAnswerData> collection, String fullName, String testName) throws MalformedURLException {
		JasperReportBuilder report = report();
		//report.addColumn(col.column("First Name", "firstName", (DRIDataType) type.stringType()).setWidth(20));
		//report.addColumn(col.column("Last Name", "lastName", (DRIDataType) type.stringType()).setWidth(20));
		
		//report.addColumn(col.column("Email", "email", (DRIDataType) type.stringType()).setWidth(30));
		//report.addColumn(col.column("Test Name", "testName", (DRIDataType) type.stringType()).setWidth(30));
		
		report.addColumn(col.column("Problem", "problem", (DRIDataType) type.stringType()).setWidth(124));
		report.addColumn(col.column("Question Category", "questionCategory", (DRIDataType) type.stringType()).setWidth(30));
		
		report.addColumn(col.column("Choice 1", "choice1", (DRIDataType) type.stringType()).setWidth(40));
		report.addColumn(col.column("Choice 2", "choice2", (DRIDataType) type.stringType()).setWidth(40));
		report.addColumn(col.column("Choice 3", "choice3", (DRIDataType) type.stringType()).setWidth(40));
		report.addColumn(col.column("Choice 4", "choice4", (DRIDataType) type.stringType()).setWidth(30));
		report.addColumn(col.column("Choice 5", "choice5", (DRIDataType) type.stringType()).setWidth(30));
		report.addColumn(col.column("Choice 6", "choice6", (DRIDataType) type.stringType()).setWidth(20));
		report.addColumn(col.column("Correct Choice", "correctChoice", (DRIDataType) type.stringType()).setWidth(40));
		
		
		
		report.addColumn(col.column("User Choice", "userChoice", (DRIDataType) type.stringType()).setWidth(40));
		report.addColumn(col.column("User Code(In case of Coding Q)", "userProgram", (DRIDataType) type.stringType()).setWidth(70));
		report.addColumn(col.column("Is Correct?", "correct", (DRIDataType) type.stringType()).setWidth(30));
		report.addColumn(col.column("Confident about Answer?", "confidentAboutAnswer", (DRIDataType) type.stringType()).setWidth(30));
		
		JasperReportBuilder builder = report
				  .setTemplate(com.assessment.reports.manager.Templates.reportTemplate)
		 .title(com.assessment.reports.manager.Templates.createTitleComponent("Test Session Details for "+fullName+" on test - "+testName));
		
		//JasperReportBuilder builder = report.ignorePageWidth();
		Properties props = new Properties();
		props.put("net.sf.jasperreports.awt.ignore.missing.font", "true");
		//builder = builder.pageFooter(com.assessment.reports.manager.Templates.footerComponent, cmp.line())
		builder = builder.setDataSource(collection)
				.highlightDetailOddRows()
				.setBackgroundStyle(DynamicReports.stl.style(DynamicReports.stl.pen1Point()))
				 .setIgnorePagination(true)
				// .highlightDetailOddRows()
				 .setParameter("net.sf.jasperreports.awt.ignore.missing.font", "true")
				 .setProperties(props);
		
	//	builder.
		 
		 try {
			 synchronized (this) {
				 FileOutputStream fos = new FileOutputStream(fullName+"-"+testName+"-Session.xls");
					builder.toXls(fos);
					fos.close();
					return fullName+"-"+testName+"-Session.xls";
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	return null;	 

	}
	
	public void generateReport(List<TestReportData> collection) throws MalformedURLException {
		JasperReportBuilder report = report();
		report.addColumn(col.column("Test Name", "testName", (DRIDataType) type.stringType()));
		report.addColumn(col.column("No of Sessions", "noOfSessions", (DRIDataType) type.integerType()));
		
		report.addColumn(col.column("Created By", "createdBy", (DRIDataType) type.stringType()));
		report.addColumn(col.column("Overall Average Score", "overallAverageScore", (DRIDataType) type.floatType()));
		
		report.addColumn(col.column("Skills", "skills", (DRIDataType) type.stringType()));
		
		report.addColumn(col.column("Sections", "sectionsInfo", (DRIDataType) type.stringType()));
		report.addColumn(col.column("Section Wise Average", "averageScoreSummary", (DRIDataType) type.stringType()));
		report.addColumn(col.column("Top Candidates", "topCandidates", (DRIDataType) type.stringType()));
		report.addColumn(col.column("Top Candidates Email", "topCandidatesEmail", (DRIDataType) type.stringType()));
		
		JasperReportBuilder builder = report
				  .setTemplate(com.assessment.reports.manager.Templates.reportTemplate)
		 .title(com.assessment.reports.manager.Templates.createTitleComponent("All Tests Report - IIHT"));
		
		 builder = builder.pageFooter(com.assessment.reports.manager.Templates.footerComponent, cmp.line())
				 .setDataSource(collection)
				 .highlightDetailOddRows();
		 
		 try {
			FileOutputStream fos = new FileOutputStream("tests.pdf");
				builder.toPdf(fos);
				fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 

	}
	
	public String generateTestPerspectiveReport(Collection<AssessmentTestPerspectiveData> collection, String createdBy, String createDate) throws MalformedURLException {
		JasperReportBuilder report = report();
		report.addColumn(col.column("Test Name", "testName", (DRIDataType) type.stringType()));
		report.addColumn(col.column("Sections", "sectionsInfo", (DRIDataType) type.stringType()));
		
		report.addColumn(col.column("No Of Sessions", "noOfSessions", (DRIDataType) type.integerType()));
		report.addColumn(col.column("No of Pass", "noOfPassResults", (DRIDataType) type.integerType()));
		
		report.addColumn(col.column("Average Score", "averageScore", (DRIDataType) type.floatType()));
		
		report.addColumn(col.column("Highest Score", "highestScore", (DRIDataType) type.floatType()));
		
		report.addColumn(col.column("Top Candidates", "topCandidates", (DRIDataType) type.stringType()));
		report.addColumn(col.column("Top Candidates Email", "topCandidatesEmail", (DRIDataType) type.stringType()));
		
		Properties props = new Properties();
		props.put("net.sf.jasperreports.awt.ignore.missing.font", "true");
		
		JasperReportBuilder builder = report
				  .setTemplate(com.assessment.reports.manager.Templates.reportTemplate)
		 .title(com.assessment.reports.manager.Templates.createTitleComponent("All Tests Data", createdBy, createDate ))
		 .setProperties(props);
		
		 builder = builder.pageFooter(com.assessment.reports.manager.Templates.footerComponent, cmp.line())
				 .setDataSource(collection)
				 .highlightDetailOddRows()
				 .setProperties(props)
				 .setParameter("net.sf.jasperreports.awt.ignore.missing.font", "true");
		 
		 try {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			 String fileName = formatter.format(new Date())+"_allTestsData.pdf";
			 fileName = fileName.replace(" ", "_");
			 fileName = fileName.replaceAll(":", ".");
			FileOutputStream fos = new FileOutputStream(fileName);
				builder.toPdf(fos);
				fos.close();
				return fileName;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null; 

	}
	
	
	public String generateUserPerspectiveReport(Collection<AssessmentUserPerspectiveData> collection, String createdBy, String createDate) throws MalformedURLException {
		JasperReportBuilder report = report();
		report.addColumn(col.column("First Name", "firstName", (DRIDataType) type.stringType()).setFixedWidth(35));
		report.addColumn(col.column("Last Name", "lastName", (DRIDataType) type.stringType()).setFixedWidth(35));
		
		report.addColumn(col.column("Contact", "email", (DRIDataType) type.stringType()).setFixedWidth(50));
		report.addColumn(col.column("Test Name", "testName", (DRIDataType) type.stringType()).setFixedWidth(50));
		
		report.addColumn(col.column("Test Start", "testStartDate", (DRIDataType) type.stringType()).setFixedWidth(50));
		report.addColumn(col.column("Test End", "testEndDate", (DRIDataType) type.stringType()).setFixedWidth(50));
		
		report.addColumn(col.column("Score", "overAllScore", (DRIDataType) type.floatType()).setFixedWidth(30));
		
		report.addColumn(col.column("Section Wise", "sectionWiseScore", (DRIDataType) type.stringType()).setFixedWidth(50));
		
		report.addColumn(col.column("Result", "result", (DRIDataType) type.stringType()).setFixedWidth(30));
		report.addColumn(col.column("No Of Attempts", "noOfAttempts", (DRIDataType) type.integerType()).setFixedWidth(30));
		TextColumnBuilder column = col.column("Call this url to fetch session details", "urlForUserSession", type.stringType());
		column.setFixedWidth(164);
		HyperLinkBuilder hyperLinkBuilder = DynamicReports.hyperLink();
		column.setHyperLink(hyperLinkBuilder);
		
		report.addColumn(column);
	//	report.
		
		Properties props = new Properties();
		props.put("net.sf.jasperreports.awt.ignore.missing.font", "true");
		
		JasperReportBuilder builder = report
				  .setTemplate(com.assessment.reports.manager.Templates.reportTemplate)
		 .title(com.assessment.reports.manager.Templates.createTitleComponent("Candidates Test Summary", createdBy, createDate ))
		 .setProperties(props);
		
		 builder = builder.pageFooter(com.assessment.reports.manager.Templates.footerComponent, cmp.line())
				 .setDataSource(collection)
				 .highlightDetailOddRows()
				 .setParameter("net.sf.jasperreports.awt.ignore.missing.font", "true")
				 .setProperties(props);
		 
		 try {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			 String fileName = formatter.format(new Date())+"_allUserTestsData.xls";
			 fileName = fileName.replaceAll(" ", "_");
			 fileName = fileName.replaceAll(":", ".");
			FileOutputStream fos = new FileOutputStream(fileName);
				builder.toXls(fos);
				fos.close();
				return fileName;
				//fos.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 return null;

	}

	public String generateUserSessionsReportForTest(String testName, String companyId) throws Exception {
		List<UserTestSession> sessions = userTestSessionRepository.findUserSessionsForTest(testName, companyId);
		 Map<String, Class<?>> columns_type = new HashMap<>();
		 FastReportBuilder reportBuilder = new FastReportBuilder();
		 String pattern = "dd-MM-yyyy HH:mm:ss";
		 String testname = testName.replace("\\", "").replace("/", "").replace("*", "-").replace(":", "-").replace("?", "").replace("[", "").replace("]", "");
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Page page = Page.Page_Letter_Landscape();
			reportBuilder.setPageSizeAndOrientation(page)
			                          .setUseFullPageWidth(true)
			                          .setReportName(testname+"-User_Sessions_Report")
			                          .setReportName("User_Sessions_Report")
			                          .setPrintColumnNames(true)
			                          .setAllowDetailSplit(true)
			                          .setIgnorePagination(true)
			                          .setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			
			reportBuilder.setDefaultStyles(null, null, getColumnHeaderStyle(), getColumnDetailsStyle());
			reportBuilder.setMargins(0, 0, 0, 0);
			int key = 12;
			for(UserTestSession session : sessions) {
				if(columns_type.get("First Name") == null){
					columns_type.put("Company Id", String.class);
		 			columns_type.put("First Name", String.class);
			 		columns_type.put("Last Name", String.class);
			 		columns_type.put("Email", String.class);
			 		columns_type.put("Test Invite Sent", String.class);
			 		columns_type.put("Overall Score", String.class);
			 		columns_type.put("Pass", String.class);
			 		columns_type.put("Sectionwise Score", String.class);
			 		//columns_type.put("Result", String.class);
			 		columns_type.put("Session Time", String.class);
			 		columns_type.put("Report Created By", String.class);
			 		columns_type.put("Test Shared Direct?", String.class);
			 		reportBuilder.addColumn("Company ID","key0", String.class.getName(),500);
			 		reportBuilder.addColumn("First Name","key1", String.class.getName(),500);
			 		reportBuilder.addColumn("Last Name","key2", String.class.getName(),500);
			 		reportBuilder.addColumn("Email","key3", String.class.getName(),500);
			 		reportBuilder.addColumn("Test Invite Sent","key4", String.class.getName(),500);
			 		reportBuilder.addColumn("Overall Score%","key5", String.class.getName(),500);
			 		reportBuilder.addColumn("Pass","key6", String.class.getName(),500);
			 		reportBuilder.addColumn("Sectionwise Score%","key7", String.class.getName(),500);
			 		reportBuilder.addColumn("Session Time","key9", String.class.getName(),500);
			 		reportBuilder.addColumn("Report Created By","key10", String.class.getName(),500);
			 		reportBuilder.addColumn("Test Shared Direct?","key11", String.class.getName(),500);
			 		/**
		 			 * add now for start and end dates in reports
		 			 */
			 		reportBuilder.addColumn("Test Start","key-testStart", String.class.getName(),300);
			 		reportBuilder.addColumn("Test End","key-testEnd", String.class.getName(),300);
			 		/**
			 		 * End
			 		 */
				}
				
		 		
		 		String sectionResults = session.getSectionResults();
		 		if(sectionResults == null) {
		 			sectionResults = "";
		 		}
		 		else {
		 			String secs[] = sectionResults.split(",");
			 		for(String s : secs) {
			 			String sectionName = s.split("-")[0];
			 			if(columns_type.get("Score(%) - "+sectionName) == null){
			 				columns_type.put("Score(%) - "+sectionName, String.class);
			 				reportBuilder.addColumn("Score(%) - "+sectionName,"key"+key, String.class.getName(),500);
				 			key++;
			 			}
			 			
			 			
			 		}
		 		}
		 		
		 		
		 		String secsnoQ = session.getSectionsNoOfQuestionsNotAnswered();
		 		if(secsnoQ == null) {
		 			secsnoQ = "";
		 		}
		 		else {
		 			String noqs[] = secsnoQ.split(",");
			 		for(String s : noqs) {
			 			String sectionName = s.split("-")[0];
			 			if(columns_type.get("No of Unanswered Qs in "+sectionName) == null){
			 				columns_type.put("No of Unanswered Qs in "+sectionName, String.class);
			 				reportBuilder.addColumn("No of Unanswered Qs in "+sectionName,"key"+key, String.class.getName(),500);
				 			key++;
			 			}
			 			
			 			
			 		}
		 		}
		 		
			}
			
			List rowsDataList = new ArrayList();
			for (int row = 0; row < sessions.size(); row++) {
		        HashMap<String, Object> rowHashMap = new HashMap<>();
		        UserTestSession session = sessions.get(row);
		        //String companyId = session.getCompanyId();
		        String email = session.getUser();
		        User usr = userService.findByPrimaryKey(email, companyId);
		        String firstName = usr.getFirstName();
		        String lastName = usr.getLastName();
		        
		        String testInviteSent = null;
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		        if(session.getTestInviteSent() != null) {
		        	
		        	testInviteSent = dateFormat.format(session.getTestInviteSent());
		        }
		        
		        String overAllScore = session.getPercentageMarksRecieved()+"";
		        String pass = session.getPass() == null?"No":(session.getPass()?"Yes":"No");
		        String sectionWiseScore = session.getSectionResults();
		        String reportCreationDate = null;
		        	if(session.getCreateDate() != null) {
		        		reportCreationDate = dateFormat.format(session.getCreateDate());
		        	}
		        String reportCreatedBy = session.getCreatedBy();
		        String sharedDirect = session.getSharedDirect()==null?"NA":(session.getSharedDirect()?"Yes":"No");
		        rowHashMap.put("key0",companyId);
		        rowHashMap.put("key1",firstName);
		        rowHashMap.put("key2",lastName);
		        rowHashMap.put("key3",email);
		        rowHashMap.put("key4",testInviteSent == null?"Direct":testInviteSent);
		        rowHashMap.put("key5",overAllScore);
		        rowHashMap.put("key6",pass);
		        rowHashMap.put("key7",sectionWiseScore);
		        rowHashMap.put("key9",reportCreationDate);
		        rowHashMap.put("key10",reportCreatedBy==null?"Admin":"reportCreatedBy");
		        rowHashMap.put("key11",sharedDirect);
		        
		        int count = 12;
		        String sectionResults = session.getSectionResults();
		        	if(sectionResults == null) {
		        		sectionResults = "";
		        	}
		        	else {
		        		String secs[] = sectionResults.split(",");
				 		for(String s : secs) {
				 			String score = s.split("-")[1];
				 			 rowHashMap.put("key"+count,score);
				 			count++;
				 		}
		        	}
		 		
		 		
		 		String secsnoQ = session.getSectionsNoOfQuestionsNotAnswered();
		 			if(secsnoQ == null) {
		 				secsnoQ = "";
		 			}
		 			else {
		 				String noqs[] = secsnoQ.split(",");
				 		for(String s : noqs) {
				 			String noQsec = s.split("-")[1];
				 			rowHashMap.put("key"+count,noQsec);
				 			count++;
				 		}
		 			}
		 			/**
		 			 * add now for start and end dates in reports
		 			 */
		 		Date sdate = session.getCreateDate();
		        Date edate = session.getUpdateDate();
		        if(sdate != null){
		        	String s1 = dateFormat.format(sdate);
		        	rowHashMap.put("key-testStart",s1);
		        }
		        else{
		        	rowHashMap.put("key-testStart","NA");
		        }
		        if(edate != null){
		        	String s1 = dateFormat.format(edate);
		        	rowHashMap.put("key-testEnd",s1);
		        }
		        else{
		        	rowHashMap.put("key-testEnd","NA");
		        }
		        /**
		         * end for start and end dates in reports
		         */
		        
		 		 rowsDataList.add(rowHashMap); 
			}
			
			//reportBuilder.setf
			DynamicReport dynamicReport  = reportBuilder.build();
			dynamicReport.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			
			JasperPrint finalReport = DynamicJasperHelper.generateJasperPrint(dynamicReport,
	                new ListLayoutManager(),
	                      rowsDataList);
			
			
			JRXlsExporter exporter = new JRXlsExporter();
			
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		//	 String fileName = formatter.format(new Date())+"_allUserTestsData.xls";
			 String fileName = "userSessionsForTest.xls";
			 File outputFile = new File(fileName);
		        FileOutputStream fos = new FileOutputStream(outputFile);

		        
		    	JRXlsExporter xlsExporter = new JRXlsExporter();
				ExporterInput exporterInput = new SimpleExporterInput(finalReport);
				OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fos);
				xlsExporter.setExporterOutput(exporterOutput);
				xlsExporter.setExporterInput(exporterInput);


				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setWhitePageBackground(true);
				configuration.setRemoveEmptySpaceBetweenColumns(false);
				configuration.setColumnWidthRatio(1.4f);
				configuration.setIgnoreCellBorder(false);
				configuration.setWhitePageBackground(false);
				
				xlsExporter.setConfiguration(configuration);


				xlsExporter.exportReport();
				return fileName;
	}

	public String generateUserPerspectiveReportWithExtraAttrs(Collection<AssessmentUserPerspectiveData> collection, String createdBy, String createDate) throws MalformedURLException, ColumnBuilderException, ClassNotFoundException {
		 Map<String, Class<?>> columns_type = new HashMap<>();
		 FastReportBuilder reportBuilder = new FastReportBuilder();
			Page page = Page.Page_Letter_Landscape();
			reportBuilder.setPageSizeAndOrientation(page)
			                          .setUseFullPageWidth(true)
			                          .setReportName("IIHT User Report")
			                          .setPrintColumnNames(true)
			                          .setAllowDetailSplit(true)
			                          .setIgnorePagination(true);
			
			reportBuilder.setDefaultStyles(null, null, getColumnHeaderStyle(), getColumnDetailsStyle());
			reportBuilder.setMargins(0, 0, 0, 0);
			int column = 12;
		 	for(AssessmentUserPerspectiveData data : collection) {
		 		
		 		
		 		if(columns_type.get("First Name") == null){
		 			columns_type.put("First Name", String.class);
			 		columns_type.put("Last Name", String.class);
			 		columns_type.put("Email", String.class);
			 		columns_type.put("Test Invite Sent", String.class);
			 		columns_type.put("Overall Score", String.class);
			 		columns_type.put("Pass", String.class);
			 		columns_type.put("Sectionwise Score", String.class);
			 		//columns_type.put("Result", String.class);
			 		columns_type.put("Report Creation Date", String.class);
			 		columns_type.put("Report Created By", String.class);
			 		columns_type.put("Test Shared Direct?", String.class);
		 			 reportBuilder.addColumn("First Name","key1", String.class.getName(),500);
				 		reportBuilder.addColumn("Last Name","key2", String.class.getName(),500);
				 		reportBuilder.addColumn("Email","key3", String.class.getName(),500);
				 		reportBuilder.addColumn("Test Invite Sent","key4", String.class.getName(),500);
				 		reportBuilder.addColumn("Overall Score","key5", String.class.getName(),500);
				 		reportBuilder.addColumn("Pass","key6", String.class.getName(),500);
				 		reportBuilder.addColumn("Sectionwise Score","key7", String.class.getName(),500);
				 		//reportBuilder.addColumn("Result","key8", String.class.getName(),500);
				 		reportBuilder.addColumn("Report Creation Date","key9", String.class.getName(),500);
				 		reportBuilder.addColumn("Report Created By","key10", String.class.getName(),500);
				 		reportBuilder.addColumn("Test Shared Direct?","key11", String.class.getName(),500);
		 		}
		 		
		 		
				 
		 		
		 		for(String key : data.getSections_score().keySet()) {
		 			if(columns_type.get("Section Score- "+key) == null){
		 				columns_type.put("Section Score- "+key, String.class);
			 			reportBuilder.addColumn("Section Score- "+key,"key"+column, String.class.getName(),600);
			 			column++;
		 			}
		 			
		 		}
		 		for(String key:data.getSections_noOfQuestionsNotAnswered().keySet()) {
		 			if(columns_type.get("(No Of Qs not Answered) for "+key) == null){
		 				columns_type.put("(No Of Qs not Answered) for "+key, String.class);
			 			reportBuilder.addColumn("(No Of Qs not Answered) for "+key,"key"+column, String.class.getName(),600);
			 			column++;
		 			}
		 			
		 			
		 		}
		 		
		 	}
		 	
		 	
			
		
			List rowsDataList = new ArrayList();
			for (int row = 0; row < collection.size(); row++) {
		        HashMap<String, Object> rowHashMap = new HashMap<>();
		       AssessmentUserPerspectiveData data =  ((List<AssessmentUserPerspectiveData>) collection).get(0);
		        rowHashMap.put("key1",data.getFirstName());
		        rowHashMap.put("key2",data.getLastName());
		        rowHashMap.put("key3",data.getEmail());
		        rowHashMap.put("key4",data.getTestName());
		        rowHashMap.put("key5",""+data.getOverAllScore());
		        rowHashMap.put("key6",data.getPass()?"Yes":"No");
		        rowHashMap.put("key7",data.getSectionWiseScore());
		       // rowHashMap.put("key8",data.getResult());
		        rowHashMap.put("key9",data.getReportCreationDate());
		        rowHashMap.put("key10",data.getReportCreatedBy());
		        rowHashMap.put("key11",data.getSharedDirect());
		        int ind = 12;
		        for(String key : data.getSections_score().keySet()) {
		        	rowHashMap.put("key"+ind,data.getSections_score().get(key));
		        	ind++;
		 		}
		        
		        for(String key:data.getSections_noOfQuestionsNotAnswered().keySet()) {
		        	rowHashMap.put("key"+ind,data.getSections_noOfQuestionsNotAnswered().get(key));
		        	ind++;
		 		}
		        rowsDataList.add(rowHashMap);
		}
		 
		 try {
			 DynamicReport dynamicReport  = reportBuilder.build();
				
				JasperPrint finalReport = DynamicJasperHelper.generateJasperPrint(dynamicReport,
		                new ListLayoutManager(),
		                      rowsDataList);
				
				
				JRXlsExporter exporter = new JRXlsExporter();
				
				 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			//	 String fileName = formatter.format(new Date())+"_allUserTestsData.xls";
				 String fileName = "allTestUseData.xls";
				 File outputFile = new File(fileName);
			        FileOutputStream fos = new FileOutputStream(outputFile);

			        
			    	JRXlsExporter xlsExporter = new JRXlsExporter();
					ExporterInput exporterInput = new SimpleExporterInput(finalReport);
					OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fos);
					xlsExporter.setExporterOutput(exporterOutput);
					xlsExporter.setExporterInput(exporterInput);


					SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
					configuration.setOnePagePerSheet(false);
					configuration.setWhitePageBackground(true);
					configuration.setRemoveEmptySpaceBetweenColumns(false);
					configuration.setColumnWidthRatio(1.4f);
					configuration.setIgnoreCellBorder(false);
					configuration.setWhitePageBackground(false);
					
					xlsExporter.setConfiguration(configuration);


					xlsExporter.exportReport();
					return fileName;
				//fos.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 return null;

	}

	private Style getColumnHeaderStyle() {
        Style hStyle = new Style();
        hStyle.setBorder(Border.THIN());
        hStyle.setTransparent(false);
        hStyle.setBackgroundColor(new Color(0, 142, 175));
        hStyle.setTextColor(Color.WHITE);
        hStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        hStyle.setVerticalAlign(VerticalAlign.MIDDLE);
       // hStyle.setFont(new Font(10, , false));
        return hStyle;
    }

    private Style getColumnDetailsStyle() {
        Style cStyle = new Style();
        cStyle.setBorder(Border.THIN());
      //  cStyle.setFont(new Font(10, MY_FONT, false));
        cStyle.setVerticalAlign(VerticalAlign.TOP);
        return cStyle;
    }
    
    public String generateUsersBySkillReport(List<UserBySkillDTO> collection, String skill) throws MalformedURLException {
		JasperReportBuilder report = report();
		//report.addColumn(col.column("First Name", "firstName", (DRIDataType) type.stringType()).setWidth(20));
		//report.addColumn(col.column("Last Name", "lastName", (DRIDataType) type.stringType()).setWidth(20));
		
		//report.addColumn(col.column("Email", "email", (DRIDataType) type.stringType()).setWidth(30));
		//report.addColumn(col.column("Test Name", "testName", (DRIDataType) type.stringType()).setWidth(30));
		
		report.addColumn(col.column("First Name", "firstName", (DRIDataType) type.stringType()).setWidth(60));
		report.addColumn(col.column("Last Name", "lastName", (DRIDataType) type.stringType()).setWidth(60));
		
		report.addColumn(col.column("Email", "email", (DRIDataType) type.stringType()).setWidth(80));
		report.addColumn(col.column("Skill", "skill", (DRIDataType) type.stringType()).setWidth(60));
		report.addColumn(col.column("Score in %", "scoreInPercentage", (DRIDataType) type.stringType()).setWidth(40));
		report.addColumn(col.column("No of Questions Attempted across tests", "noOfQuestionsAttempted", (DRIDataType) type.integerType()).setWidth(100));
		//report.addColumn(col.column("Company I", "choice5", (DRIDataType) type.stringType()).setWidth(40));
		report.addColumn(col.column("Tests Appeared for the Skill", "tests", (DRIDataType) type.stringType()).setWidth(200));
		
		
		JasperReportBuilder builder = report
				  .setTemplate(com.assessment.reports.manager.Templates.reportTemplate)
		 .title(com.assessment.reports.manager.Templates.createTitleComponent("Users for Skill - "+skill));
		
		//JasperReportBuilder builder = report.ignorePageWidth();
		Properties props = new Properties();
		props.put("net.sf.jasperreports.awt.ignore.missing.font", "true");
		//builder = builder.pageFooter(com.assessment.reports.manager.Templates.footerComponent, cmp.line())
		builder = builder.setDataSource(collection)
				.highlightDetailOddRows()
				.setBackgroundStyle(DynamicReports.stl.style(DynamicReports.stl.pen1Point()))
				 .setIgnorePagination(true)
				// .highlightDetailOddRows()
				 .setParameter("net.sf.jasperreports.awt.ignore.missing.font", "true")
				 .setProperties(props);
		 
		 try {
			 synchronized (this) {
				 FileOutputStream fos = new FileOutputStream(skill+".pdf");
					builder.toPdf(fos);
					fos.close();
					return skill+".pdf";
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	return null;	 

	}
	
}
