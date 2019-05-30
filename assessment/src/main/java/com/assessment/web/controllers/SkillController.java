package com.assessment.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;
import com.assessment.data.User;
import com.assessment.repositories.SkillRepository;
import com.assessment.services.SkillService;

@Controller
public class SkillController {
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	SkillService skillService;
	
	@RequestMapping(value = "/skills", method = RequestMethod.GET)
	  public ModelAndView getSkills(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView("skills");
		java.util.List<Skill> skills = skillRepository.getSkillsByCompanyId(user.getCompanyId());
		mav.addObject("skills", skills);
		return mav;
	}
	
	@RequestMapping(value = "/addSkill", method = RequestMethod.GET)
	  public ModelAndView addSkill(@RequestParam(name= "skillId", required = false) Long skillId,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		 ModelAndView mav = new ModelAndView("addSkill");
		 Skill skill = null;
		 	if(skillId == null){
		 		skill = new Skill();
				skill.setCompanyId(user.getCompanyId());
				skill.setCompanyName(user.getCompanyName());
				mav.addObject("label", "Create");
		 	}
		 	else{
		 		skill = skillRepository.findById(skillId).get();
		 		mav.addObject("label", "Update");
		 	}
		 mav.addObject("levels", SkillLevel.values());
		 mav.addObject("skill", skill);
		 return mav;
	}
	
	@RequestMapping(value = "/saveSkill", method = RequestMethod.POST)
	  public ModelAndView saveSkill(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("skill") Skill skill) {
		User user = (User) request.getSession().getAttribute("user");
		if(skill.getId() != null){
			skill.setCompanyId(user.getCompanyId());
			skill.setCompanyName(user.getCompanyName());
			skillService.updateSkill(skill);
		}
		else{
			try {
				skill.setCompanyId(user.getCompanyId());
				skill.setCompanyName(user.getCompanyName());
				skillService.createSkill(skill);
			} catch (AssessmentGenericException e) {
				// TODO Auto-generated catch block
				ModelAndView mav = new ModelAndView("skills");
				java.util.List<Skill> skills = skillRepository.getSkillsByCompanyId(user.getCompanyId());
				mav.addObject("skills", skills);
				mav.addObject("message", "Skill object of same name and level exists! You can not create a duplicate skill" );// later put it as label
				mav.addObject("msgtype", "Information");
				return mav;
			}
		}
		 return getSkills(request, response);
	}
	
	
}
