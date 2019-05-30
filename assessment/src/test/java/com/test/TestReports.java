package com.test;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CandidateProfileParams;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.reports.manager.AssessmentReportDataManager;
import com.assessment.reports.manager.AssessmentReportsManager;
import com.assessment.reports.manager.AssessmentTestData;
import com.assessment.reports.manager.AssessmentTestPerspectiveData;
import com.assessment.reports.manager.AssessmentUserPerspectiveData;
import com.assessment.reports.manager.TestReportData;
import com.assessment.reports.manager.UserSkillArea;
import com.assessment.reports.manager.UserTrait;
import com.assessment.reports.manager.detailedreports.ReportManagerTrait;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.SectionService;
import com.assessment.services.UserService;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.ListLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestReports {
	
	@Autowired
	AssessmentReportsManager reportsManager;
	
	@Autowired
	UserTestSessionRepository userTestSessionRepository;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuestionMapperInstanceService questionMapperInstanceService;
	
	@Autowired
	CandidateProfileParamsService candidateProfileParamsService;
	
	@Test
	public void testGranulaReport() throws Exception{
		List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId("IH");
		List<QuestionMapperInstance> answers = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest("General_Technology_Comprehensive", "jatin.sutaria@thev2technologies.com", "IH");
		Map<CandidateProfileParams, List<QuestionMapperInstance>> map = new HashMap<>();
		for(QuestionMapperInstance ans : answers){
			CandidateProfileParams param = new CandidateProfileParams(ans.getQuestionMapper().getQuestion().getQualifier1(), ans.getQuestionMapper().getQuestion().getQualifier2(), ans.getQuestionMapper().getQuestion().getQualifier3(), ans.getQuestionMapper().getQuestion().getQualifier4(), ans.getQuestionMapper().getQuestion().getQualifier5()); 
			if(map.get(param) == null){
				List<QuestionMapperInstance> ins = new ArrayList<>();
				ins.add(ans);
				map.put(param, ins);
			}
			else{
				map.get(param).add(ans);
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");
		Map<CandidateProfileParams,Float> mapPer = new HashMap<>();
		Map<CandidateProfileParams, String> mapTrait = new HashMap<>();
		for(CandidateProfileParams param : map.keySet()){
			List<QuestionMapperInstance> answersForQualifier = map.get(param);
			int noOfCorrect = 0;
			for(QuestionMapperInstance ans :answersForQualifier ){
				if(ans.getCorrect()){
					noOfCorrect++;
				}
			}
			
			Float percent = Float.parseFloat(df.format(noOfCorrect * 100 / answersForQualifier.size()));
			mapPer.put(param, percent);
			int index = candidateProfileParams.indexOf(param);
				if(index != -1){
					CandidateProfileParams paramWithData = candidateProfileParams.get(index);
					String trait = "";
					if(percent < 20){
						trait = paramWithData.getLESS_THAN_TWENTY_PERCENT();
					}
					else if(percent >= 20 && percent < 50){
						trait = paramWithData.getBETWEEN_TWENTY_AND_FIFTY();
					}
					else if(percent >= 50 && percent < 75){
						trait = paramWithData.getBETWEEN_FIFTY_AND_SEVENTYFIVE();
					}
					else if(percent >= 75 && percent < 90){
						trait = paramWithData.getBETWEEN_SEVENTYFIVE_AND_NINETY();
					}
					else if(percent > 90){
						trait = paramWithData.getMORE_THAN_NINETY();
					}
					mapTrait.put(param, trait);
				}
			
		}
		List<UserTrait> traits = new ArrayList<>();
		for(CandidateProfileParams param : mapTrait.keySet()){
			UserTrait trait = new UserTrait();
			String qual = param.getQualifier1();
			if(param.getQualifier2()!= null && !param.getQualifier2().equals("NA")){
				qual += "-"+param.getQualifier2();
			}
			if(param.getQualifier3()!= null && !param.getQualifier3().equals("NA")){
				qual += "-"+param.getQualifier3();
			}
			if(param.getQualifier4()!= null && !param.getQualifier4().equals("NA")){
				qual += "-"+param.getQualifier4();
			}
			if(param.getQualifier5()!= null && !param.getQualifier5().equals("NA")){
				qual += "-"+param.getQualifier5();
			}
			
			trait.setTrait(qual);
			trait.setDescription(mapTrait.get(param));
			traits.add(trait);
		}
		
		List<UserSkillArea> skillAreas = new ArrayList<>();
			for(CandidateProfileParams param : mapPer.keySet()){
				UserSkillArea area = new UserSkillArea();
				String qual = param.getQualifier1();
				if(param.getQualifier2()!= null && !param.getQualifier2().equals("NA")){
					qual += "-"+param.getQualifier2();
				}
				if(param.getQualifier3()!= null && !param.getQualifier3().equals("NA")){
					qual += "-"+param.getQualifier3();
				}
				if(param.getQualifier4()!= null && !param.getQualifier4().equals("NA")){
					qual += "-"+param.getQualifier4();
				}
				if(param.getQualifier5()!= null && !param.getQualifier5().equals("NA")){
					qual += "-"+param.getQualifier5();
				}
				area.setSkillarea(qual);
				Float percent = mapPer.get(param);
				area.setPercentage(percent);
				skillAreas.add(area);
			}
		
		ReportManagerTrait managerTrait = new ReportManagerTrait();
		managerTrait.buildComprehensiveReport(traits, skillAreas, "General Composite Test", "Jatin Sutaria");
	}
	
	@Test
	public void testGenerateSampleReport() throws MalformedURLException {
		TestReportData data1 = new TestReportData("Java Comprehensive Test", "Jatin Sutaria", 45.0f, 10);
		data1.setSkills("Java");
		data1.setSectionsInfo("Main Section, Advanced Section");
		data1.setAverageScoreSummary("Main Section - 67.0, Advanced Section - 70.0");
		data1.setTopCandidates("Akash Singh-92%, Abdullah Khan-89%, Sarita Iyer-77.5%");
		data1.setTopCandidatesEmail("a.singh@rrr.com, a.khan@eee.com, s.iyer@erty.com");
		
		TestReportData data2 = new TestReportData("Java Interview Test", "Jatin Sutaria", 55.0f, 20);
		data2.setSkills("Dot Net");
		data2.setSectionsInfo("Main Section, Advanced Section");
		data2.setAverageScoreSummary("Main Section - 73.0, Advanced Section - 75.0");
		data2.setTopCandidates("Prabhjot Singh-88%, Sameer Khan-79%, Ram Iyer-78%");
		
		data2.setTopCandidatesEmail("p.singh@xyz.com, s.khan@def.com, r.iyer@hhh.com");
		
		List<TestReportData> collection = new ArrayList<>();
		collection.add(data1);
		collection.add(data2);
		
		reportsManager.generateReport(collection);
		
	}
	
	@Test
	public void testGetData() {
		 List<AssessmentTestData> data = userTestSessionRepository.getAllResultsData("IH");
		 System.out.println(data.size());
	}
	
	@Test
	public void testGetTestData() throws MalformedURLException {
		AssessmentReportDataManager assessmentReportDataManager = new AssessmentReportDataManager(userTestSessionRepository, sectionService, userService, "IH", "Jatin Sutaria");
//			for(String testName: assessmentReportDataManager.getTestNames()) {
//				AssessmentTestPerspectiveData assessmentTestPerspectiveData = assessmentReportDataManager.fetchForTest(testName);
//				System.out.println(assessmentTestPerspectiveData.getHighestScore());
//			}
		Collection<AssessmentTestPerspectiveData> data = assessmentReportDataManager.getTestPerspectiveData();
		String user = "Jatin Sutaria";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(new Date());
		reportsManager.generateTestPerspectiveReport(data, user, date);
	}
	
	@Test
	public void testGetUserPerspectiveData() throws MalformedURLException, ColumnBuilderException, ClassNotFoundException {
		AssessmentReportDataManager assessmentReportDataManager = new AssessmentReportDataManager(userTestSessionRepository, sectionService, userService, "IH", "Jatin Sutaria");
		List<AssessmentUserPerspectiveData> collection = assessmentReportDataManager.getUserPerspectiveData();
		String user = "Jatin Sutaria";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(new Date());
		//reportsManager.generateUserPerspectiveReport(collection, user, date);
		reportsManager.generateUserPerspectiveReportWithExtraAttrs(collection, user, date);
			
	}
	
	@Test
	public void testGetUsersDataForTest() throws Exception, ClassNotFoundException {
		List<UserTestSession> sessions = userTestSessionRepository.findUserSessionsForTest("[LTI] BA & Scrum Agile for Business Analysts", "IH");
		 Map<String, Class<?>> columns_type = new HashMap<>();
		 FastReportBuilder reportBuilder = new FastReportBuilder();
			Page page = Page.Page_Letter_Landscape();
			reportBuilder.setPageSizeAndOrientation(page)
			                          .setUseFullPageWidth(true)
			                          .setReportName("NewTest_23Nov-Users_Report")
			                          .setPrintColumnNames(true)
			                          .setAllowDetailSplit(true)
			                          .setIgnorePagination(true);
			
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
			 		columns_type.put("Report Creation Date", String.class);
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
			 		reportBuilder.addColumn("Report Creation Date","key9", String.class.getName(),500);
			 		reportBuilder.addColumn("Report Created By","key10", String.class.getName(),500);
			 		reportBuilder.addColumn("Test Shared Direct?","key11", String.class.getName(),500);
				}
				
		 		
		 		String sectionResults = session.getSectionResults();
		 		String secs[] = sectionResults.split(",");
		 		for(String s : secs) {
		 			String sectionName = s.split("-")[0];
		 			if(columns_type.get("Section - "+sectionName) == null){
		 				columns_type.put("Section - "+sectionName, String.class);
		 				reportBuilder.addColumn("Section - "+sectionName,"key"+key, String.class.getName(),500);
			 			key++;
		 			}
		 			
		 			
		 		}
		 		
		 		String secsnoQ = session.getSectionsNoOfQuestionsNotAnswered();
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
			
			List rowsDataList = new ArrayList();
			for (int row = 0; row < sessions.size(); row++) {
		        HashMap<String, Object> rowHashMap = new HashMap<>();
		        UserTestSession session = sessions.get(row);
		        String companyId = session.getCompanyId();
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
		        rowHashMap.put("key4",testInviteSent);
		        rowHashMap.put("key5",overAllScore);
		        rowHashMap.put("key6",pass);
		        rowHashMap.put("key7",sectionWiseScore);
		        rowHashMap.put("key9",reportCreationDate);
		        rowHashMap.put("key10",reportCreatedBy);
		        rowHashMap.put("key11",sharedDirect);
		        
		        int count = 12;
		        String sectionResults = session.getSectionResults();
		 		String secs[] = sectionResults.split(",");
		 		for(String s : secs) {
		 			String score = s.split("-")[1];
		 			 rowHashMap.put("key"+count,score);
		 			count++;
		 		}
		 		
		 		String secsnoQ = session.getSectionsNoOfQuestionsNotAnswered();
		 		String noqs[] = secsnoQ.split(",");
		 		for(String s : noqs) {
		 			String noQsec = s.split("-")[1];
		 			rowHashMap.put("key"+count,noQsec);
		 			count++;
		 		}
		        
		 		 rowsDataList.add(rowHashMap); 
			}
			
			DynamicReport dynamicReport  = reportBuilder.build();
			
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
	
	@Test
	public void testDynamicJasperReports() throws ColumnBuilderException, ClassNotFoundException, FileNotFoundException, JRException {
		FastReportBuilder reportBuilder = new FastReportBuilder();
		Page page = Page.Page_A4_Landscape();
		reportBuilder.setTitle("Table Name")
		                          .setPageSizeAndOrientation(page)
		                          .setUseFullPageWidth(true)
		                          .setReportName("Report Name")
		                          .setPrintColumnNames(true);
		
		int numberOfColumns = 10;
		
		for (int column = 1; column <= numberOfColumns; column++) {
		       reportBuilder.addColumn("Column"+ column,
		       "key" + column,
		        String.class.getName(),
		        30);
		}
		
		List rowsDataList = new ArrayList();
		int numberOfRows = 7;
		for (int row = 1; row <= numberOfRows; row++) {
		        HashMap<String, String> rowHashMap = new HashMap<>();
		        for (int column = 1; column <= numberOfColumns; column++) {
		                rowHashMap.put("key" + column,
		                                             "Row" + row + " Column " + column);
		        }
		        rowsDataList.add(rowHashMap);
		}
			
		
		
		DynamicReport dynamicReport  = reportBuilder.build();
		
		JasperPrint finalReport = DynamicJasperHelper.generateJasperPrint(dynamicReport,
                new ClassicLayoutManager(),
                      rowsDataList);
		
		
		JRXlsExporter exporter = new JRXlsExporter();
		
		
		 File outputFile = new File("dynamic.xls");
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
			xlsExporter.setConfiguration(configuration);


			xlsExporter.exportReport();
	        
        
	}

}
