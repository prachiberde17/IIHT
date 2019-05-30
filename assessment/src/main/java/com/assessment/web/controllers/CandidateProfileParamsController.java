package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.Qualifiers;
import com.assessment.data.CandidateProfileParams;
import com.assessment.data.Question;
import com.assessment.data.User;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.CandidateProfileParamsService;
import com.assessment.web.dto.SectionDto;

@Controller
public class CandidateProfileParamsController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	CandidateProfileParamsService profileService;

	
//	@RequestMapping(value = "/showProfileParams", method = RequestMethod.GET)
//	public ModelAndView showAll(@RequestParam(name= "page", required = false) Integer pageNumber, @RequestParam(name= "qid", required = false) Long qid, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView("reports_Coding");
//		User user = (User) request.getSession().getAttribute("user");
//		List<CandidateProfileParams> params = profileService.findCandidateProfileParamsByCompanyId(user.getCompanyId());
//				
//		Set<Qualifiers> qualifiers = questionRepository.getAllUniqueQualifiers(user.getCompanyId());
//		List<CandidateProfileParams> list = new ArrayList<>();
//		for(Qualifiers q : qualifiers){
//			CandidateProfileParams candidateProfileParams = new CandidateProfileParams(q.getQualifier1(), q.getQualifier2(), q.getQualifier3(), q.getQualifier4(), q.getQualifier5());
//			if(!params.contains(candidateProfileParams)){
//				list.add(candidateProfileParams);
//			}
//		}
//		params.addAll(list);
//		mav.addObject("params", params);
//		
//		return mav;
//	} 
	
	@RequestMapping(value = "/showProfileParams", method = RequestMethod.GET)
	public ModelAndView showAll(@RequestParam(name= "qual", required = false) String qual, @RequestParam(name= "qid", required = false) Long qid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("recomm");
		User user = (User) request.getSession().getAttribute("user");
		Set<Qualifiers> qualifiers = questionRepository.getAllUniqueQualifiers(user.getCompanyId());
		if(qual != null){
			String qls[] = qual.split("-");
			CandidateProfileParams params = profileService.findUniqueCandidateProfileParams(user.getCompanyId(), qls[0], qls[1], qls[2], qls[3], qls[4]);
			if(params == null){
				params = new CandidateProfileParams(qls[0], qls[1], qls[2], qls[3], qls[4]);
			}
			params.setContext(qual);
			mav.addObject("params", params);
		}
		else{
			Qualifiers qualifier;
			if(qualifiers.size() > 0){
				qualifier = qualifiers.iterator().next();
				CandidateProfileParams params = profileService.findUniqueCandidateProfileParams(user.getCompanyId(), qualifier.getQualifier1(), qualifier.getQualifier2()==null?"NA":qualifier.getQualifier2(), qualifier.getQualifier3()==null?"NA":qualifier.getQualifier3(), qualifier.getQualifier4()==null?"NA":qualifier.getQualifier4(), qualifier.getQualifier5()==null?"NA":qualifier.getQualifier5());
				if(params == null){
					params = new CandidateProfileParams(qualifier.getQualifier1(), qualifier.getQualifier2(), qualifier.getQualifier3(), qualifier.getQualifier4(), qualifier.getQualifier5());
				}
				mav.addObject("params", params);
				//mav.addObject("qualifiers", qualifiers);
			}
			else{
				mav.addObject("params", new CandidateProfileParams());
			}
		}
	
				
		
		
		mav.addObject("qualifiers", qualifiers);
		
		return mav;
	} 
	
	@RequestMapping(value = "/saveProfileParams", method = RequestMethod.POST)
    public ModelAndView  saveProfileParams(@ModelAttribute("params") CandidateProfileParams params, HttpServletRequest request, HttpServletResponse response) {
	ModelAndView mav = new ModelAndView("recomm");
	 User user = (User) request.getSession().getAttribute("user");
	 params.setCompanyId(user.getCompanyId());
	 params.setCompanyName(user.getCompanyName());
	 profileService.saveOrUpdate(params);
	 mav.addObject("message", "Save Success ");// later put it as label
		mav.addObject("msgtype", "Information");
		Set<Qualifiers> qualifiers = questionRepository.getAllUniqueQualifiers(user.getCompanyId());
		mav.addObject("params", params);
		mav.addObject("qualifiers", qualifiers);
 	 return mav;
    }
}
