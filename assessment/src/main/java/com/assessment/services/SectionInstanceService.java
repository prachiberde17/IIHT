package com.assessment.services;

import java.util.List;

import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.SectionInstance;

public interface SectionInstanceService {

	public void saveSection(SectionInstance section, List<QuestionMapperInstance> questionMapperInstances);
	
	public List<SectionInstance> getSectionInstances(String sectionName, String companyId, String user);
	
}
