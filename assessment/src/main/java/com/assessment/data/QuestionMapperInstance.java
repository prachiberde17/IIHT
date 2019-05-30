package com.assessment.data;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
/**
 * Primary key - questionMapper & companyId
 * @author jsutaria
 *
 */
@Entity
public class QuestionMapperInstance extends Base{
	@ManyToOne
	QuestionMapper questionMapper;
	
	
	String userChoices;
	
	Boolean correct = false;
	
	Boolean answered = false;
	
	String questionText;
	
	@javax.validation.constraints.NotNull
	String testName;
	
	@javax.validation.constraints.NotNull
	String sectionName;
	
	@javax.validation.constraints.NotNull
	String user;
	
	String codingOuputBySystemTestCase;
	
	@Lob
	String codeByUser;
	
	@Lob
	String reviewerComments;
	
	@Column(length=400)
	String workspaceUrl;
	
	@Column(length=200)
	String workSpaceId;
	
	@Column(length=400)
	String usageDocumentUrl;
	
	@Column
	Boolean workspaceSubmitted;
	
	@Transient
	String uerFullName;
	//Long userTestSessionId;
	
	@Transient
	String workspaceDateOfSubmission;
	
	Boolean confidence;

	public QuestionMapper getQuestionMapper() {
		return questionMapper;
	}

	public void setQuestionMapper(QuestionMapper questionMapper) {
		this.questionMapper = questionMapper;
	}



	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Boolean getAnswered() {
		return answered;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public String getUserChoices() {
		return userChoices;
	}

	public void setUserChoices(String userChoices) {
		this.userChoices = userChoices;
			if(userChoices.length() > 0) {
				setAnswered(true);
				String choices = getQuestionMapper().getQuestion().getRightChoices();
				String correct[] = choices.split("-");
				String userC[] = userChoices.split("-");
				if(Arrays.equals(correct, userC)) {
					setCorrect(true);
				}
			}
			else {
				setAnswered(false);
			}
		
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

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

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getCodingOuputBySystemTestCase() {
		return codingOuputBySystemTestCase;
	}

	public void setCodingOuputBySystemTestCase(String codingOuputBySystemTestCase) {
		this.codingOuputBySystemTestCase = codingOuputBySystemTestCase;
		if(getQuestionMapper().getQuestion().getHiddenOutputNegative().equalsIgnoreCase(codingOuputBySystemTestCase)){
			setCorrect(true);
		}
		else{
			setCorrect(false);
		}
		setAnswered(true);
	}

	public String getCodeByUser() {
		return codeByUser;
	}

	public void setCodeByUser(String codeByUser) {
		this.codeByUser = codeByUser;
	}

	public String getReviewerComments() {
		return reviewerComments;
	}

	public void setReviewerComments(String reviewerComments) {
		this.reviewerComments = reviewerComments;
	}

	public String getWorkspaceUrl() {
		return workspaceUrl;
	}

	public void setWorkspaceUrl(String workspaceUrl) {
		this.workspaceUrl = workspaceUrl;
	}

	public String getWorkSpaceId() {
		return workSpaceId;
	}

	public void setWorkSpaceId(String workSpaceId) {
		this.workSpaceId = workSpaceId;
	}

	public String getUsageDocumentUrl() {
		return usageDocumentUrl;
	}

	public void setUsageDocumentUrl(String usageDocumentUrl) {
		this.usageDocumentUrl = usageDocumentUrl;
	}

	public Boolean getWorkspaceSubmitted() {
		return workspaceSubmitted;
	}

	public void setWorkspaceSubmitted(Boolean workspaceSubmitted) {
		this.workspaceSubmitted = workspaceSubmitted;
	}

	public String getUerFullName() {
		return uerFullName;
	}

	public void setUerFullName(String uerFullName) {
		this.uerFullName = uerFullName;
	}

	public String getWorkspaceDateOfSubmission() {
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if(getUpdateDate() == null) {
			if(getCreateDate() == null) {
				return "Not Available";
			}
			else {
				return simpleDateFormat.format(getCreateDate());
			}
			
		}
		else {
			return simpleDateFormat.format(getUpdateDate());
		}
	}

	public void setWorkspaceDateOfSubmission(String workspaceDateOfSubmission) {
		this.workspaceDateOfSubmission = workspaceDateOfSubmission;
	}

	public Boolean getConfidence() {
		return confidence;
	}

	public void setConfidence(Boolean confidence) {
		this.confidence = confidence;
	}
	
	
	
	
}
