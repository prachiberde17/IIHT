package com.assessment.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
/**
 * pk - sectionName & companyId
 * @author jsutaria
 *
 */
@Entity
public class Skill extends Base {

	String skillName;
	
	@Enumerated(EnumType.STRING)
	private SkillLevel skillLevel = SkillLevel.BASIC;
	
	@Transient
	private String level;
	
	@Transient
	private String label;
	
	public Skill() {
		
	}
	
	public Skill(String skillName, SkillLevel skillLevel) {
		this.skillName = skillName;
		this.skillLevel = skillLevel;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getLevel() {
		return getSkillLevel().getLevel();
	}

	public void setLevel(String level) {
		this.level = level;
		setSkillLevel(SkillLevel.valueOf(level));
	}
	
	public String toString(){
		return getSkillName()+"-"+getSkillLevel();
	}

	public String getLabel() {
		return getSkillName()+"-"+getSkillLevel();
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
