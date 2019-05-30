package com.assessment.web.controllers;

import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.ExcelReader;
import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.Company;
import com.assessment.data.Skill;
import com.assessment.data.SkillLevel;
import com.assessment.data.Tenant;
import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.data.UserType;
import com.assessment.services.CompanyService;
import com.assessment.services.SkillService;
import com.assessment.services.TenantService;
import com.assessment.services.TestService;
import com.assessment.services.UserNonComplianceService;
import com.assessment.services.UserService;
import com.assessment.services.UserTestSessionService;
@Controller
public class UserController {
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	@Autowired
	TestService testService;
	
	@Autowired
	UserNonComplianceService userNonComplianceService;
	
	@Autowired
	UserTestSessionService userTestSessionService;
	
	@Autowired
	DataSource dataSourceRoot;
	
	@Autowired
	TenantService tenantService;
	
	@Autowired
	SkillService skillService;
	
private static String LOCAL_BASE_URL="http://localhost/";

Logger logger =LoggerFactory.getLogger(UserController.class);
	
	//private static String REMOTE_BASE_URL="http://13.59.126.83/";
	

	@RequestMapping(value = "/uploadUsers", method = RequestMethod.POST)
	  public void uploadUsers(HttpServletResponse response, MultipartHttpServletRequest request ) throws Exception {
		  try {
			  System.out.println("in uploadUsers entering");
			MultipartFile multipartFile = request.getFile("fileFromUserForm");
			    Long size = multipartFile.getSize();
			    String contentType = multipartFile.getContentType();
			    InputStream stream = multipartFile.getInputStream();
			    File file = new File("users.xml");
			    List<User> users = ExcelReader.parseExcelFileToBeans(stream, file);
			    logger.info("in upload method users size "+users.size());
				if(users.size() == 0) {
					throw new AssessmentGenericException("NO_DATA_IN_EXCEL");
				}
				String compId = users.get(0).getCompanyId();
				Company company = companyService.findByCompanyId(compId);
				System.out.println("Company got in uploadUsers "+company.getId() +" "+company.getCompanyName());
				logger.info("Company got in uploadQuestions "+company.getId() +" "+company.getCompanyName());
				for(User u : users) {
					u.setCompanyId(company.getCompanyId());
					u.setCompanyName(company.getCompanyName());
					userService.saveOrUpdate(u);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("problems in uploading users", e);
		}
		}
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	  public ModelAndView init(@RequestParam String tenantEmailId, @RequestParam String companyId, @RequestParam String companyName, HttpServletResponse response, HttpServletRequest request ) throws Exception {
		try {
			ModelAndView mav = new ModelAndView("index");
			if(companyService.findByCompanyId(companyId) != null) {
				 User user = new User();
				 user.setCompanyId(companyId);
				 user.setCompanyName(companyName);
				 user.setInternalUser(true);
				 user.setEmail("system.admin@"+companyId+".com");
				 user.setPassword("12345");
				 return mav;
			}
			
			 Company company = new Company();
			 company.setCompanyId(companyId);
			 company.setCompanyName(companyName);
			 companyService.saveOrUpdate(company);
			 logger.info("Company crated");
			 System.out.println("Company crated");
				Skill skill = new Skill("Java", SkillLevel.BASIC);
				skill.setCompanyId(companyId);
				skill.setCompanyName(companyName);
				skillService.createSkill(skill);
				 logger.info("Skill crated");
				 System.out.println("Skill crated");
			 User user = new User();
			 user.setCompanyId(companyId);
			 user.setCompanyName(companyName);
			 user.setInternalUser(true);
			 	if(tenantEmailId == null) {
			 		 user.setEmail("system.admin@"+companyId+".com");
			 	}
			 	else {
			 		user.setEmail(tenantEmailId);
			 	}
			 user.setUserType(UserType.ADMIN);
			 user.setPassword("12345");
			 userService.addUser(user);
			 
			 mav.addObject("user", user);
			 return mav;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("index");
			 User user = new User();
			 user.setCompanyId(companyId);
			 user.setCompanyName(companyName);
			 user.setInternalUser(true);
			 user.setEmail("system.admin@"+companyId+".com");
			 user.setPassword("12345");
			 logger.error("problem ", e);
			 throw new AssessmentGenericException("problem in init", e);
			
			 
		}
		}
	
	@RequestMapping(value = "/setUpTenant", method = RequestMethod.GET)
	  public void setUpTenant(@RequestParam String tenantEmailId, @RequestParam String tenantId, @RequestParam String companyName, HttpServletResponse response, HttpServletRequest request ) throws Exception {
		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
		List<String> list = bean.getInputArguments();
		boolean local = false;
			for(String str : list) {
				System.out.println(str);
					if(str.contains("localDeploy=true")) {
						local = true;
						break;
					}
			}
		
		
		String exampleConfig = propertyConfig.getDefaultReferenceConfigFileLocation()+File.separator+"config.properties";
		String tenatsFolder = propertyConfig.getTenantsConfigLocation();
		
		File file = new File(exampleConfig);
		
			/**
			 * Step0 - Create a schema for new tenant in database
			 */
		String password = "Welcome@123";
		dataSourceRoot.getConnection().createStatement().execute("create schema "+tenantId);
		dataSourceRoot.getConnection().createStatement().execute("create user 'User_"+tenantId+"'@'%' IDENTIFIED BY '"+password+"'");
		dataSourceRoot.getConnection().createStatement().execute("grant all privileges on "+tenantId+".* to 'User_"+tenantId+"'@'%'");
			
			/**
			 * Step 1 update config.properties with right values
			 */
			String op = propertyConfig.getTenantsConfigLocation()+File.separator+tenantId;
			File output = new File(op);
			FileUtils.copyDirectory(new File(propertyConfig.getDefaultReferenceConfigFileLocation()), new File(op));
			FileUtils.forceMkdir(output);
			
			//FileInputStream in = new FileInputStream(exampleConfig);
			String configData = FileUtils.readFileToString(new File(exampleConfig));
				if(local) {
					configData = configData.replace("${BASE_URL}", LOCAL_BASE_URL);
				}
				else {
					configData = configData.replace("${BASE_URL}", propertyConfig.getRemoteBaseUrl());
				}
			configData = configData.replace("${TENANT}", tenantId);
			configData = configData.replace("${TENANT_SCREENSHOT_LOCATION}", op+File.separator+"ScreenShots");
			/**
			 * Step 2 - Put the updated config.properties file in the right tenant folder
			 */


			FileUtils.write(new File(op+File.separator+"config.properties"), configData, false);
			
			
			/**
			 * Step 3 - Copy the base war file folder (assessment) into Tomcat's webapps folder
			 */
			FileUtils.copyDirectory(new File(propertyConfig.getDefaultReferenceConfigFileLocation()+File.separator+"assessment"), new File(propertyConfig.getTomcatDeployLocation()+File.separator+tenantId));
		
			/**
			 * Step 4 - Change the appContext file of copied war folder to correspond to tenant specific values
			 */
			String appContextFileLoc = propertyConfig.getTomcatDeployLocation()+File.separator+tenantId+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"appContext.xml";
			String contents = FileUtils.readFileToString(new File(appContextFileLoc));
			contents = contents.replace("${CONFIG_LOCATION}", op+File.separator+"config.properties");
			String jdbcUrl ="";
			if(local) {
				 jdbcUrl = "jdbc:mysql://localhost:3306/"+tenantId;
			}
			else {
				jdbcUrl = "jdbc:mysql://127.0.0.1:3306/"+tenantId;
			}
			String user = "User_"+tenantId;
			//String password = "password";
			contents = contents.replace("${TEST_LINK_LOCATION}", op +File.separator+"sendTestLink.html");
			contents = contents.replace("${RESULT_LINK_LOCATION}", op +File.separator+"sendTestResultInfo.html");
			contents = contents.replace("${JDBC_URL}", jdbcUrl);
			contents = contents.replace("${USER}", user);
			contents = contents.replace("${PASSWORD}", password);
			FileUtils.write(new File(appContextFileLoc), contents, false);
			String logFile = propertyConfig.getTomcatDeployLocation()+File.separator+tenantId+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"log4j.properties";
			String logContents = FileUtils.readFileToString(new File(logFile));
			logContents = logContents.replace("${LOG_FILE}", tenantId);
			FileUtils.write(new File(logFile), logContents, false);
			String message = "";
				if(local) {
					message = "Hello \n\n Please go to http://localhost/"+tenantId+"/init?companyId="+tenantId+"&companyName="+companyName+"&tenantEmailId="+tenantEmailId+"\n"+
							"Thanks and Regards\n"
							+ "System Admin - Assessment Platform\n"
							+"IIHT";
				}
				else {
					message = "Hello \n\n Please go to "+(propertyConfig.getRemoteBaseUrl()+tenantId)+"/init?companyId="+tenantId+"&companyName="+companyName+"&tenantEmailId="+tenantEmailId+"\n"+
							"Thanks and Regards\n"
							+ "System Admin - Assessment Platform\n"
							+"IIHT";
				}
			
			
			
			EmailGenericMessageThread runnable = new EmailGenericMessageThread(tenantEmailId, "Asessment Platform Setup Details", message, propertyConfig);
			Thread th = new Thread(runnable);
			th.start(); 
		}
	
	@RequestMapping(value = "/goscreen", method = RequestMethod.GET)
	  public ModelAndView goscreen(HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 return new ModelAndView("screen_capture_demo");
		}
	
	@RequestMapping(value = "/uploadScreenSnapShot", method = RequestMethod.POST)
	  public void uploadScreenSnapShot(@RequestParam String testName, @RequestBody String imageValue,HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String folder = propertyConfig.getScreenShotFolder();
		System.out.println("folder is "+folder);
		System.out.println("testName is "+testName);
		com.assessment.data.Test test = testService.findbyTest(testName, user.getCompanyId());
		System.out.println("user is "+user);
		System.out.println("comp id is "+user.getCompanyId());
		System.out.println("test is "+test.getTestName());
		System.out.println("comp is "+user.getCompanyName());
		folder = folder + File.separator + user.getCompanyName()+File.separator+user.getFirstName()+"-"+user.getLastName()+File.separator+test.getTestName()+"-"+test.getId();
		FileUtils.forceMkdir(new File(folder));
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date = new Date();
		String fileName = dateFormat.format(date)+".png";
		fileName = fileName.replace(":", "_");
		File dest = new File(folder+File.separator+fileName);
		imageValue = URLDecoder.decode(imageValue);
		imageValue = imageValue.substring(("testName="+testName+"&data=data:image/png;base64,").length(), imageValue.length());
		byte bat[] = Base64.getDecoder().decode(imageValue);
	        FileUtils.writeByteArrayToFile(dest, bat);
		}
	
	@RequestMapping(value = "/registerNonCompliance", method = RequestMethod.POST)
	  public void registerNonCompliance(@RequestBody String userNonCompliance,HttpServletRequest request) throws Exception {
		userNonCompliance = userNonCompliance.substring("data=".length(), userNonCompliance.length());
			User user = (User) request.getSession().getAttribute("user");
			//userNonCompliance = userNonCompliance.replaceAll("$$$", "\n");
			userNonCompliance = URLDecoder.decode(userNonCompliance);
			Properties properties = new Properties();
		    properties.load(new StringReader(userNonCompliance));
		    String email = properties.getProperty("user");
		    String testName=properties.getProperty("testName");
		    String companyId = properties.getProperty("companyId");
		    UserTestSession session = userTestSessionService.findUserTestSession(email, testName, companyId) ;
		    userNonComplianceService.increment(email, testName, companyId, null);
//		    	if(session == null) {
//		    		userNonComplianceService.increment(email, testName, companyId, null);
//		    	}
//		    	else {
//		    		userNonComplianceService.increment(email, testName, companyId, session.getId());
//		    	}
		    
		}
	
	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	  public ModelAndView listUsers(HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 List<User> users = userService.findByCompany(user.getCompanyId());
		 ModelAndView mav = new ModelAndView("add_user");
		 mav.addObject("users", users);
		 User usr = new User();
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 mav.addObject("usr", usr);
		 return mav;
		}
	
	@RequestMapping(value = "/searchUsrs", method = RequestMethod.GET)
	  public ModelAndView searchUsers(@RequestParam String searchText, HttpServletResponse response, HttpServletRequest request ) throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 List<User> users = userService.searchUsers(user.getCompanyId(), searchText);
		 ModelAndView mav = new ModelAndView("add_user");
		 mav.addObject("users", users);
		 User usr = new User();
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 mav.addObject("usr", usr);
		 return mav;
		}
	
	 @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	  public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("usr") User usr) {
		 User user = (User) request.getSession().getAttribute("user");
		 ModelAndView mav= new ModelAndView("add_user");
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 userService.saveOrUpdate(usr);
		 usr = new User();
		 usr.setCompanyId(user.getCompanyId());
		 usr.setCompanyName(user.getCompanyName());
		 mav.addObject("usr", usr);
		 List<User> users = userService.findByCompany(user.getCompanyId());
		 mav.addObject("users", users);
		 return mav;
	 }
}
