package com.assessment.data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
/**
 * Primary key - question & companyId
 * @author jsutaria
 *
 */
@Entity
public class QuestionMapper extends Base {

	@ManyToOne
	Question question;
	
	String sectionName;
	
	String testName;

	Integer pointsToAward = 1;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getPointsToAward() {
		return pointsToAward;
	}

	public void setPointsToAward(Integer pointsToAward) {
		this.pointsToAward = pointsToAward;
	}

	
	
	
}
