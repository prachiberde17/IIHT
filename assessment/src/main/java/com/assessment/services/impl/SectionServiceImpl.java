package com.assessment.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.Section;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.SectionRepository;
import com.assessment.services.SectionService;
import com.assessment.services.TestService;
@Service
@Transactional
public class SectionServiceImpl implements SectionService{
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TestService testService;
	
	
	
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	private void validateMandatoryFields(Section section) {
		Set<ConstraintViolation<Section>> violations = validator.validate(section);
		if(violations.size() > 0){
			throw new AssessmentGenericException("NOT_SUFFICIENT_PARAMS");
		}
		
		
	}

	@Override
	public QuestionMapper addQuestionToSection(Question question, Section section, Integer pointsToaward) {
		// TODO Auto-generated method stub
		
		validateMandatoryFields(section);
		if(!section.getCompanyId().equalsIgnoreCase(question.getCompanyId())) {
			throw new AssessmentGenericException("Question From different Company");//make it configurable
		}
		
		if(testService.findbyTest(section.getTestName(), section.getCompanyId()) == null) {
			throw new AssessmentGenericException("NO_TEST_EXISTS");
		}
		
		question = questionRepository.findByPrimaryKey(question.getQuestionText(), question.getCompanyId());
		QuestionMapper mapper = new QuestionMapper();
		mapper.setQuestion(question);
		mapper.setSectionName(section.getSectionName());
		mapper.setTestName(section.getTestName());
		mapper.setCompanyId(section.getCompanyId());
		mapper.setCompanyName(section.getCompanyName());
		mapper.setCreateDate(new Date());
		mapper.setPointsToAward(pointsToaward);
		questionMapperRepository.save(mapper);
		return mapper;
	}

	@Override
	public void createSection(Section section) {
		// TODO Auto-generated method stub
		validateMandatoryFields(section);
		/**
		 * Validate id test is existing
		 */
		if(testService.findbyTest(section.getTestName(), section.getCompanyId()) == null) {
			throw new AssessmentGenericException("NO_TEST_EXISTS");
		}
		sectionRepository.save(section);
	}

	@Override
	public void removeQuestionFromSection(Section section, QuestionMapper questionMapper) {
		// TODO Auto-generated method stub
		Optional<QuestionMapper> optional = questionMapperRepository.findById(questionMapper.getId());
		questionMapper = optional.get();
		questionMapperRepository.delete(questionMapper);
		
	}

	@Override
	public QuestionMapper updatePointsToAward(QuestionMapper questionMapper, Integer pointsToaward) {
		// TODO Auto-generated method stub
		Optional<QuestionMapper> optional = questionMapperRepository.findById(questionMapper.getId());
		questionMapper = optional.get();
		questionMapper.setPointsToAward(pointsToaward);
		questionMapperRepository.save(questionMapper);
		return questionMapper;
		
	}

	@Override
	public List<QuestionMapper> getQuestionsForSection(String testName, String sectionName, String companyId) {
		// TODO Auto-generated method stub
		return questionMapperRepository.getQuestionsForSection(testName, sectionName, companyId);
	}

	@Override
	public List<Section> getSectionsForTest(String testName, String companyId) {
		// TODO Auto-generated method stub
		return sectionRepository.getSectionsForTest(testName, companyId);
	}
	
	public Section getSectionById(Long id) {
		return sectionRepository.findById(id).get();
	}
	
	public void removeQuestionsFromSection(String sectionName, String testName, String companyId) {
		List<QuestionMapper> mappers = questionMapperRepository.getQuestionsForSection(testName, sectionName, companyId);
			for(QuestionMapper mapper : mappers) {
				/**
				 * Te method doesn't do much with section object
				 */
				removeQuestionFromSection(null, mapper);
			}
	}

	@Override
	public void removeSection(Section section) {
		// TODO Auto-generated method stub
		removeQuestionsFromSection(section.getSectionName(), section.getTestName(), section.getCompanyId());
		sectionRepository.deleteById(section.getId());
	}

	@Override
	public void changeSectionNameAndPercent(Section section, String newSectionName, Integer percent, Integer totalQuestions) {
		// TODO Auto-generated method stub
		section = getSectionById(section.getId());
		section.setSectionName(newSectionName);
		section.setPercentQuestionsAsked(percent);
		section.setNoOfQuestions(totalQuestions);
		sectionRepository.save(section);
	}
	
	@Override
	public Section findByPrimaryKey(String testName, String sectionName,  String companyId) {
		return sectionRepository.findByPrimaryKey(testName, sectionName, companyId);
	}

}
