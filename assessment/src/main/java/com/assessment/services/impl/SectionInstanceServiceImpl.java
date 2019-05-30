package com.assessment.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.QuestionType;
import com.assessment.data.Section;
import com.assessment.data.SectionInstance;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.repositories.SectionInstanceRepository;
import com.assessment.repositories.SectionRepository;
import com.assessment.services.SectionInstanceService;
import com.assessment.services.SectionService;

@Service
@Transactional
public class SectionInstanceServiceImpl implements SectionInstanceService{
	
	@Autowired
	SectionInstanceRepository sectionInstanceRepository;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	QuestionMapperInstanceRepository questionMapperInstanceRepository;
	
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	private void validateMandatoryFields(SectionInstance sectionInstance) {
		Set<ConstraintViolation<SectionInstance>> violations = validator.validate(sectionInstance);
		if(violations.size() > 0){
			throw new AssessmentGenericException("NOT_SUFFICIENT_PARAMS");
		}
		
		
	}
	
	private void validateMandatoryFields(QuestionMapperInstance questionMapperInstance) {
		Set<ConstraintViolation<QuestionMapperInstance>> violations = validator.validate(questionMapperInstance);
		if(violations.size() > 0){
			throw new AssessmentGenericException("NOT_SUFFICIENT_PARAMS");
		}
		
		
	}

	@Override
	public void saveSection(SectionInstance sectionInstance, List<QuestionMapperInstance> questionMapperInstances) {
		// TODO Auto-generated method stub
		validateMandatoryFields(sectionInstance);
		List<SectionInstance> pastInstances = getSectionInstances(sectionInstance.getSectionName(), sectionInstance.getCompanyId(), sectionInstance.getUser());
		Section section = sectionRepository.findByPrimaryKey(sectionInstance.getTestName(), sectionInstance.getSectionName(), sectionInstance.getCompanyId());
		int sectionTime = section.getSectionTimeInMinutes() == null?30:section.getSectionTimeInMinutes();
		int timeYet = 0;
		for(SectionInstance ins : pastInstances) {
			Long startTime = ins.getStartTime();
			Long endTime = ins.getEndTime();
			int mins = (int) ((endTime - startTime)/(1000 * 60));
			timeYet += mins;
		}
		
		if(timeYet >= sectionTime) {
			//enable later
			//throw new AssessmentGenericException("SECTION_TIME_LIMIT_CROSSED");
		}
		
		for(QuestionMapperInstance questionMapperInstance : questionMapperInstances) {
			validateMandatoryFields(questionMapperInstance);
			QuestionMapperInstance questionMapperInstance2 = questionMapperInstanceRepository.findUniqueQuestionMapperInstanceForUser(questionMapperInstance.getQuestionText(), questionMapperInstance.getTestName(), questionMapperInstance.getSectionName(), questionMapperInstance.getUser(), questionMapperInstance.getCompanyId());
			if(questionMapperInstance2 != null) {
				//update answer
				questionMapperInstance2.setUserChoices(questionMapperInstance.getUserChoices());
				questionMapperInstance2.setUpdateDate(new Date());
				checkAnswer(questionMapperInstance2);
				questionMapperInstanceRepository.save(questionMapperInstance2);
			}
			else {
				//new answer
				questionMapperInstance.setCreateDate(new Date());
				checkAnswer(questionMapperInstance);
				questionMapperInstanceRepository.save(questionMapperInstance);
			}
			
		}
		
		sectionInstanceRepository.save(sectionInstance);
	}
	
	private void checkAnswer(QuestionMapperInstance instance) {
		/**Added if condition code for coding question type answer verification
		 * 
		 */
		if(instance.getQuestionMapper().getQuestion().getQuestionType() != null && instance.getQuestionMapper().getQuestion().getQuestionType().getType().equals(QuestionType.CODING.getType())){
			String op = instance.getCodingOuputBySystemTestCase();
			op = (op == null)?"":op;
			if(instance.getQuestionMapper().getQuestion().getHiddenOutputNegative().equalsIgnoreCase(op)){
				instance.setCorrect(true);
				
			}
			else{
				instance.setCorrect(false);
			}
			instance.setAnswered(true);
			return;
		}
		/**End Added if condition code for coding question type answer verification
		 * 
		 */
		
		/**
		 * Check if right
		 */
		if(instance.getAnswered()) {
			String rightChoices = instance.getQuestionMapper().getQuestion().getRightChoices();
			String rt[] = rightChoices.split("-");
			String userChoices[] = instance.getUserChoices().split("-");
			instance.setCorrect(Arrays.equals(rt, userChoices));
		}
	}

	@Override
	public List<SectionInstance> getSectionInstances(String sectionName, String companyId, String user) {
		// TODO Auto-generated method stub
		return sectionInstanceRepository.findSectionForUser(sectionName, user, companyId);
	}

}
