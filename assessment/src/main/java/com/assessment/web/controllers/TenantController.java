package com.assessment.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.CommonUtil;
import com.assessment.data.DifficultyLevel;
import com.assessment.data.Question;
import com.assessment.data.Tenant;
import com.assessment.data.User;
import com.assessment.services.CompanyService;
import com.assessment.services.QuestionService;
import com.assessment.services.TenantService;
import com.assessment.services.UserService;

@Controller
public class TenantController {
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	TenantService tenantService;
	
	@Autowired
	UserController userController;
	
	@RequestMapping(value = "/listTenants", method = RequestMethod.GET)
	public ModelAndView listTenants(@RequestParam(name= "page", required = false) Integer pageNumber, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("list_tenant");
		if(pageNumber == null) {
			pageNumber = 0;
		}
		Page<Tenant> tenants = tenantService.findAllTenants(pageNumber);
		mav.addObject("tenants", tenants.getContent());
		mav.addObject("tenant", new Tenant());
		CommonUtil.setCommonAttributesOfPagination(tenants, mav.getModelMap(), pageNumber, "listTenants", null);
		return mav;
	}
	
	@RequestMapping(value = "/addTenant", method = RequestMethod.GET)
	public ModelAndView addTenant( HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("add_tenant");
		
		mav.addObject("tenant", new Tenant());
		return mav;
	}

	@RequestMapping(value = "/saveTenant", method = RequestMethod.POST)
	public ModelAndView saveTenant(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("tenant") Tenant tenant) throws Exception {
		ModelAndView mav = null;
		User user = (User) request.getSession().getAttribute("user");
		try {
			userController.setUpTenant(tenant.getSpoc(), tenant.getCompanyId(), tenant.getCompanyName(), response, request);
		} catch (Exception e) {
			mav = new ModelAndView("add_tenant");
			Page<Tenant> tenants = tenantService.findAllTenants(0);
			mav.addObject("tenants", tenants.getContent());
			mav.addObject("tenant", tenant);
			mav.addObject("message", "Looks like underlying schema or database user for this tenant - "+tenant.getCompanyId()+" has already been created. Contact Admin for further support.");// later put it as label
			mav.addObject("msgtype", "failure");
			CommonUtil.setCommonAttributesOfPagination(tenants, mav.getModelMap(), 0, "listTenants", null);
			return mav;
		}
		//Tenant tenant = new Tenant();
		tenant.setCompanyId(tenant.getCompanyId());
		tenant.setCompanyName(tenant.getCompanyName());
		tenant.setSpoc(tenant.getSpoc());
		tenant.setDatabaseSchema(tenant.getCompanyId());
		tenant.setSchemaUser("'User_"+tenant.getCompanyId());
		tenantService.saveOrUpdate(tenant);
		return listTenants(0, request, response);
	}
	
}
