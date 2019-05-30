package com.test;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.Section;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.repositories.SectionRepository;
import com.assessment.services.SectionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext_Remote.xml"})
@Transactional
public class TestSection {
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;
	
//	@Test
//	@Rollback(value=false)
//	public void testCreateQuestion() {
//		Question q = new Question();
//		q.setCompanyId("V2");
//		q.setCompanyName("V2 Technologies");
//		q.setQuestionText("Qhat is your name");
//		questionService.createQuestion(q);
//	}
	

	@Test
	@Rollback(value=false)
	public void createSection() {
		Section section = new Section();
		section.setCompanyId("V2");
		section.setCompanyName("V2 Technologies");
		section.setSectionName("Core Java");
		section.setTestName("Java Test");
		section.setSectionTimeInMinutes(30);
		sectionService.createSection(section);
		//section.set
	}
	
	@Test
	@Rollback(value=false)
	public void testAddQuestionToSection() {
		Question question = questionRepository.findByPrimaryKey("Qhat is your name", "V2");
		Section section = sectionRepository.findByPrimaryKey("Java Test", "Core Java", "V2");
		sectionService.addQuestionToSection(question, section, 30);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testUpdatePointsToAward() {
		Optional<QuestionMapper> optonal = questionMapperRepository.findById(new Long(4));
		QuestionMapper mapper = optonal.get();
		sectionService.updatePointsToAward(mapper, 50);
	}
	
	@Test
	@Rollback(value=false)
	public void testRemoveQuestionFromSection() {
		Optional<QuestionMapper> optonal = questionMapperRepository.findById(new Long(3));
		QuestionMapper mapper = optonal.get();
		Section section = sectionRepository.findByPrimaryKey("Java Test", "Core Java", "V2");
		sectionService.removeQuestionFromSection(section, mapper);
	}
	
	@Test
	@Rollback(value=false)
	public void testSetSectionNoOfQuestions() {
		List<Section> sections = sectionRepository.findAll();
		for(Section section : sections) {
			Integer percent = (section.getPercentQuestionsAsked() == null || section.getPercentQuestionsAsked() == 0) ? 100:section.getPercentQuestionsAsked();
			Integer noOfQuestions = questionMapperRepository.findCountQuestionMapperForTestAndSection(section.getSectionName().trim(), section.getTestName(), section.getCompanyId());
			section.setNoOfQuestions(noOfQuestions);
			section.setPercentQuestionsAsked(percent);
			sectionService.changeSectionNameAndPercent(section, section.getSectionName(), percent, noOfQuestions);
		}
	}

}
