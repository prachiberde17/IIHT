package com.assessment.services.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;
import com.assessment.repositories.SkillRepository;
import com.assessment.services.SkillService;
@Service
@Transactional
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	SkillRepository skillRepository;

	@Override
	public void createSkill(Skill skill) throws AssessmentGenericException {
		// TODO Auto-generated method stub
		System.out.println("in create skill");
		Skill skill2 = findSkillByNameAndLevel(skill.getSkillName(), skill.getSkillLevel().getLevel(), skill.getCompanyId());
			if(skill2 != null) {
				throw new AssessmentGenericException("SKILL_ALREADY_EXISTS");//@todo - EXTERNALISE THE MAPPING BETWEEN error code and message
			}
			System.out.println("Creeating skill");
		skillRepository.save(skill);
		System.out.println("Creeating skill done");
	}

	@Override
	public Skill findSkillByNameAndLevel(String skillName, String skillLevel, String companyId) {
		// TODO Auto-generated method stub
		return skillRepository.findByPrimaryKey(skillName, SkillLevel.valueOf(skillLevel), companyId);
	}

	@Override
	public List<Skill> getSkillsByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return skillRepository.getSkillsByCompanyId(companyId);
	}

	@Override
	public void updateSkill(Skill skill) {
		// TODO Auto-generated method stub
		if(skill.getId() == null){
			throw new AssessmentGenericException("Skill_Id_Null");
		}
		
		Skill skill2 = skillRepository.findById(skill.getId()).get();
		Mapper mapper = new DozerBeanMapper();
		mapper.map(skill, skill2);
		skillRepository.save(skill2);
		
	}

}
