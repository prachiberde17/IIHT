package com.assessment.common;

import java.util.ArrayList;
import java.util.List;

import com.assessment.web.dto.QuestionInstanceDto;
import com.assessment.web.dto.SectionInstanceDto;

public class QuestionSequence {

	
	List<QuestionInstanceDto> questionInstanceDtos = new ArrayList<>();
	
	public QuestionSequence(List<QuestionInstanceDto> questionInstanceDtos) {
		this.questionInstanceDtos = questionInstanceDtos;
	}
	
	public QuestionInstanceDto nextQuestion(Long questionMapperId) {
		for(int i=0 ;i<questionInstanceDtos.size() ; i++) {
			QuestionInstanceDto dto = 	questionInstanceDtos.get(i);
			if(dto.getQuestionMapperInstance().getQuestionMapper().getId().equals(questionMapperId)) {
				if(i < (questionInstanceDtos.size() -1)) {
					return questionInstanceDtos.get(i+1);
				}
				else {
					return null;
				}
			}
		}
		
		return null;
	}
	
	public QuestionInstanceDto previousQuestion(Long questionMapperId) {
		for(int i=questionInstanceDtos.size()-1 ;i>=0 ; i--) {
			QuestionInstanceDto dto = 	questionInstanceDtos.get(i);
			if(dto.getQuestionMapperInstance().getQuestionMapper().getId().equals(questionMapperId)) {
				if(i == 0) {
					return null;
				}
				else {
					return questionInstanceDtos.get(i-1);
				}
			}
		}
		
		return null;
	}
}
