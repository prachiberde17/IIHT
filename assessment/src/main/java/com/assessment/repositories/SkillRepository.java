package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;

public interface SkillRepository extends JpaRepository<Skill,Long>{
	
	@Query("SELECT s FROM Skill s WHERE s.skillName=:skillName and s.skillLevel=:skillLevel and s.companyId=:companyId")
	Skill findByPrimaryKey(@Param("skillName") String skillName, @Param("skillLevel") SkillLevel skillLevel, @Param("companyId") String companyId);

	@Query("SELECT s FROM Skill s WHERE s.companyId=:companyId")
	List<Skill> getSkillsByCompanyId(@Param("companyId") String companyId);
}
