package com.assessment.reports.manager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.assessment.data.Section;
import com.assessment.data.User;
import com.assessment.repositories.UserTestSessionRepository;
import com.assessment.services.SectionService;
import com.assessment.services.TestService;
import com.assessment.services.UserService;

public class AssessmentReportDataManager {
	/**
	 * data fetched by query
	 */
	private List<AssessmentTestData> data;
	
	/**
	 * between test name and AssessmentTestPerspectiveData
	 */
	Map<String,AssessmentTestPerspectiveData> testMap = new LinkedHashMap<>();
	
	/**
	 * between test name and AssessmentTestData
	 */
	Map<String, List<AssessmentTestData>> databaseDataMap = new LinkedHashMap<>();
	
	/**
	 * between user(email) and AssessmentTestData
	 */
	Map<String, List<AssessmentTestData>> userMap = new LinkedHashMap<>();
	
	/**
	 *  AssessmentUserPerspectiveData
	 */
	List<AssessmentUserPerspectiveData> userPerspectiveData = new ArrayList();
	
	UserTestSessionRepository userTestSessionRepository;
	
	TestService testService;
	
	SectionService sectionService;
	
	UserService userService;
	
	String companyId;
	
	String createdBy;
	
	
	public AssessmentReportDataManager(UserTestSessionRepository repository, SectionService sectionService, UserService userService, String companyId, String createBy) {
		this.userTestSessionRepository = repository;
		this.sectionService = sectionService;
		this.userService = userService;
		this.companyId = companyId;
		this.createdBy = createBy;
		build(companyId, createdBy);
		buildUserPerspective(companyId, createBy);
	}
	
	private void buildUserPerspective(String companyId, String createdBy) {
		for(AssessmentTestData assessmentTestData : data) {
			if(userMap.get(assessmentTestData.getUser()) == null) {
				List<AssessmentTestData> userData = new ArrayList<>();
				userData.add(assessmentTestData);
				userMap.put(assessmentTestData.getUser(), userData);
			}
			else {
				userMap.get(assessmentTestData.getUser()).add(assessmentTestData);
			}
		}
		String pattern = "dd-MM-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		for(String user : userMap.keySet()) {
			List<AssessmentTestData> userData = userMap.get(user);
			User usr = userService.findByPrimaryKey(user, companyId);
			List<AssessmentUserPerspectiveData> coll = new ArrayList<>();
			for(AssessmentTestData data : userData) {
				AssessmentUserPerspectiveData assessmentUserPerspectiveData = new AssessmentUserPerspectiveData();
				assessmentUserPerspectiveData.setEmail(user);
				assessmentUserPerspectiveData.setFirstName(usr.getFirstName());
				assessmentUserPerspectiveData.setLastName(usr.getLastName());
				assessmentUserPerspectiveData.setNoOfAttempts(data.getNoOfAttempts());
				assessmentUserPerspectiveData.setOverAllScore(data.getPercentageMarksRecieved());
				assessmentUserPerspectiveData.setPass(data.getPass());
				assessmentUserPerspectiveData.setReportCreatedBy(createdBy);
				assessmentUserPerspectiveData.setReportCreationDate(formatter.format(new Date()));
				assessmentUserPerspectiveData.setSectionWiseScore(data.getSectionResults());
				assessmentUserPerspectiveData.setTestName(data.getTestName());
				assessmentUserPerspectiveData.setNoOfQuestionsNotAnswered(data.getNoOfQuestionsNotAnswered());
				assessmentUserPerspectiveData.setSharedDirect(data.getSharedDirect());
				assessmentUserPerspectiveData.setTestInviteSent(data.getTestInviteSent());
				Date sdate = data.getTestStartDate();
					if(sdate != null){
						String s1 = simpleDateFormat.format(sdate);
						assessmentUserPerspectiveData.setTestStartDate(s1);
					}
					else{
						assessmentUserPerspectiveData.setTestStartDate("NA");
					}
				Date edate = data.getTestEndDate();
					if(edate != null){
						String s1 = simpleDateFormat.format(edate);
						assessmentUserPerspectiveData.setTestEndDate(s1);
					}
					else{
						assessmentUserPerspectiveData.setTestEndDate("NA");
					}
				
				coll.add(assessmentUserPerspectiveData);
			}
			Collections.sort(coll, new Comparator<AssessmentUserPerspectiveData>() {

				@Override
				public int compare(AssessmentUserPerspectiveData o1, AssessmentUserPerspectiveData o2) {
					// TODO Auto-generated method stub
					return Float.compare(o2.getOverAllScore(), o1.getOverAllScore());
				}
			});
			userPerspectiveData.addAll(coll);
		}
	}
	
	public List<AssessmentUserPerspectiveData> getUserPerspectiveData(){
		return userPerspectiveData;
	}
	
	private void build(String companyId, String createdBy) {
		data = userTestSessionRepository.getAllResultsData(companyId);
		testMap.clear();
		Map<String, String> testSectionsMap = new LinkedHashMap<>();
		for(AssessmentTestData assessmentTestData: data) {
			if(databaseDataMap.get(assessmentTestData.getTestName()) == null) {
				List<AssessmentTestData> list = new ArrayList();
				list.add(assessmentTestData);
				databaseDataMap.put(assessmentTestData.getTestName(), list);
			}
			else {
				databaseDataMap.get(assessmentTestData.getTestName()).add(assessmentTestData);
			}
//			AssessmentTestPerspectiveData assessmentTestPerspectiveData = new AssessmentTestPerspectiveData();
//			assessmentTestPerspectiveData.setTestName(assessmentTestData.getTestName());
//			String sectionsInfo = "";

			//testMap.put(assessmentTestData.getTestName(), assessmentTestData);
		}
		
		for(String testName: databaseDataMap.keySet()) {
			List<AssessmentTestData> testSpecificData = databaseDataMap.get(testName);
			AssessmentTestPerspectiveData assessmentTestPerspectiveData = new AssessmentTestPerspectiveData();
			assessmentTestPerspectiveData.setTestName(testName);
			String sectionsInfo = "";
				if(testSectionsMap.get(testName) == null) {
				List<Section> sections = sectionService.getSectionsForTest(testName, companyId);
					for(Section section : sections) {
						sectionsInfo += section.getSectionName()+", ";
				}
					sectionsInfo = sectionsInfo.trim();
					sectionsInfo = sectionsInfo.substring(0, sectionsInfo.length() - 1);
					testSectionsMap.put(testName, sectionsInfo);
			}
			else {
				sectionsInfo = testSectionsMap.get(testName);
			}
			
		
			assessmentTestPerspectiveData.setSectionsInfo(sectionsInfo);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			assessmentTestPerspectiveData.setReportCreationDate(formatter.format(new Date()));
			assessmentTestPerspectiveData.setCreatedBy(createdBy);
			assessmentTestPerspectiveData.setNoOfSessions(testSpecificData.size());
			Integer noOfPAss = 0;
			Float overAllTestScore = 0.0f;
			DecimalFormat df = new DecimalFormat("##.##");
				for(AssessmentTestData assessmentTestData : testSpecificData) {
					if(assessmentTestData.getPass()) {
						noOfPAss ++;
					}
					overAllTestScore += assessmentTestData.getPercentageMarksRecieved();
				}
			assessmentTestPerspectiveData.setNoOfPassResults(noOfPAss);	
			Float averageScore = Float.parseFloat(df.format(overAllTestScore/testSpecificData.size()));	
			assessmentTestPerspectiveData.setAverageScore(averageScore);
			Collections.sort(testSpecificData, new Comparator<AssessmentTestData>() {
	
				@Override
				public int compare(AssessmentTestData o1, AssessmentTestData o2) {
					// TODO Auto-generated method stub
					return Float.compare(o2.getPercentageMarksRecieved(), o1.getPercentageMarksRecieved());
				}
			});
		
			if(testSpecificData.size() > 0) {
				assessmentTestPerspectiveData.setHighestScore(testSpecificData.get(0).getPercentageMarksRecieved());
				if(testSpecificData.size() > 2) {
					User one = userService.findByPrimaryKey(testSpecificData.get(0).getUser(), companyId);
					User two = userService.findByPrimaryKey(testSpecificData.get(1).getUser(), companyId);
					User three = userService.findByPrimaryKey(testSpecificData.get(2).getUser(), companyId);
					String topCandidates = one.getFirstName()+" "+one.getLastName()+"-"+testSpecificData.get(0).getPercentageMarksRecieved()+", "
							+two.getFirstName()+" "+two.getLastName()+"-"+testSpecificData.get(1).getPercentageMarksRecieved()+", "
							+three.getFirstName()+" "+three.getLastName()+"-"+testSpecificData.get(2).getPercentageMarksRecieved();
					assessmentTestPerspectiveData.setTopCandidates(topCandidates);
					
					String topCandidatesEmail = one.getEmail()+", "+two.getEmail()+", "+three.getEmail();
					assessmentTestPerspectiveData.setTopCandidatesEmail(topCandidatesEmail);
				}
				else if(testSpecificData.size() == 2) {
					User one = userService.findByPrimaryKey(testSpecificData.get(0).getUser(), companyId);
					User two = userService.findByPrimaryKey(testSpecificData.get(1).getUser(), companyId);
					String topCandidates = one.getFirstName()+" "+one.getLastName()+"-"+testSpecificData.get(0).getPercentageMarksRecieved()+", "
							+two.getFirstName()+" "+two.getLastName()+"-"+testSpecificData.get(1).getPercentageMarksRecieved();
					assessmentTestPerspectiveData.setTopCandidates(topCandidates);
					
					String topCandidatesEmail = one.getEmail()+", "+two.getEmail();
					assessmentTestPerspectiveData.setTopCandidatesEmail(topCandidatesEmail);
				}
				else if(testSpecificData.size() == 1) {
					User one = userService.findByPrimaryKey(testSpecificData.get(0).getUser(), companyId);
					String topCandidates = one.getFirstName()+" "+one.getLastName()+"-"+testSpecificData.get(0).getPercentageMarksRecieved();
					assessmentTestPerspectiveData.setTopCandidates(topCandidates);
					String topCandidatesEmail = one.getEmail();
					assessmentTestPerspectiveData.setTopCandidatesEmail(topCandidatesEmail);
				}
			}
			else {
				assessmentTestPerspectiveData.setTopCandidates("NA");
				assessmentTestPerspectiveData.setTopCandidatesEmail("NA");
			}
			testMap.put(testName, assessmentTestPerspectiveData);
		}
	}
	
	public Set<String> getTestNames(){
		return testMap.keySet();
	}
	
	public AssessmentTestPerspectiveData fetchForTest(String testName){
		return testMap.get(testName);
	}

	public Collection<AssessmentTestPerspectiveData> getTestPerspectiveData(){
		return testMap.values();
	}
}
