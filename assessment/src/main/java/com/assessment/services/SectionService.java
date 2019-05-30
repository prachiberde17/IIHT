package com.assessment.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.data.Section;

public interface SectionService {
	
	public QuestionMapper addQuestionToSection(Question question, Section section, Integer pointsToaward);
	
	public QuestionMapper updatePointsToAward(QuestionMapper questionMapper, Integer pointsToaward);
	
	public void createSection(Section section);
	
	public void removeQuestionFromSection(Section section, QuestionMapper questionMapper);
	
	public List<QuestionMapper> getQuestionsForSection(String testName, String sectionName, String companyId);
	
	
	public List<Section> getSectionsForTest( String testName,   String companyId);
	
	public Section getSectionById(Long id);
	
	public void removeQuestionsFromSection(String sectionName, String testName, String companyId);
	
	public void removeSection(Section section);
	
	public void changeSectionNameAndPercent(Section section, String newSectionName, Integer percent, Integer totalQuestions);
	
	Section findByPrimaryKey(String testName, String sectionName,  String companyId);

}
