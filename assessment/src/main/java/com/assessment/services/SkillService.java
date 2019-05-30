package com.assessment.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.data.Skill;

public interface SkillService {
	
	public void createSkill(Skill skill) throws AssessmentGenericException;
	
	public Skill findSkillByNameAndLevel(String skillName, String skillLevel, String companyId);
	
	public List<Skill> getSkillsByCompanyId(@Param("companyId") String companyId);
	
	public void updateSkill(Skill skill);
	
	

}
