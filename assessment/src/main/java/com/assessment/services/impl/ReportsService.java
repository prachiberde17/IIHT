package com.assessment.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.data.CandidateProfileParams;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.User;
import com.assessment.reports.manager.UserSkillArea;
import com.assessment.reports.manager.UserTrait;
import com.assessment.reports.manager.detailedreports.ReportManagerTrait;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.services.QuestionMapperInstanceService;
import com.assessment.services.UserService;
@Service
public class ReportsService {
	@Autowired
	QuestionMapperInstanceService questionMapperInstanceService;
	
	@Autowired
	CandidateProfileParamsService candidateProfileParamsService;
	
	@Autowired
	UserService userService;
	
	public String generatedetailedReportForCompositeTest(String companyId, String testName, String user) throws Exception{
		List<CandidateProfileParams> candidateProfileParams = candidateProfileParamsService.findCandidateProfileParamsByCompanyId(companyId);
		List<QuestionMapperInstance> answers = questionMapperInstanceService.findQuestionMapperInstancesForUserForTest(testName, user, companyId);
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
		User usr = 	userService.findByPrimaryKey(user, companyId);
		ReportManagerTrait managerTrait = new ReportManagerTrait();
		String fileName = managerTrait.buildComprehensiveReport(traits, skillAreas, testName, usr.getFirstName()+" "+usr.getLastName());//this should not be hard coded
		return fileName;
	}
}
