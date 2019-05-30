package com.assessment.web.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.data.FullStackOptions;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapperInstance;
import com.assessment.data.Test;
import com.assessment.data.User;
import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.assessment.eclipseche.services.EclipseCheService;
import com.assessment.repositories.QuestionMapperInstanceRepository;
@Controller
public class FullStackConttroller {
	
	Logger logger = LoggerFactory.getLogger(FullStackConttroller.class);
	
@Autowired	
PropertyConfig propertyConfig;

@Autowired
QuestionMapperInstanceRepository  questionMapperInstanceRep;
	
	@RequestMapping(value = "/createWorkSpaceForUser", method = RequestMethod.GET)
    public @ResponseBody String  addQuestionsToSectionAjax(@RequestParam String userName, @RequestParam String stackName, @RequestParam String questionId,@RequestParam String testId, HttpServletRequest request, HttpServletResponse response) throws Exception {
 
	 User user = (User) request.getSession().getAttribute("user");
	 	if(user == null){
	 		return "Log in Again";
	 	}
 	 userName = userName.replace(" ", "");
 	 if(stackName.equals("Java")){
 		 String json = FileUtils.readFileToString(new File("eclipseChe/Java_FullStack.json"));
 		json = json.replace("${APP_USER}", userName+"="+testId+"-"+questionId+"-"+System.currentTimeMillis());
 		//json = json.replace("${APP_USER}", "a01");
 		json = json.replace("${APP_DESC}", "Skeleton Code............................Project\n\n\n.........");
 		EclipseCheService eclipseCheService = new EclipseCheService();
 		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
 		return workspaceResponse.getLinks().getIde();
 	 }
	
		
        return "";
    }
	
	 @RequestMapping(value = "/submitFullStackCode", method = RequestMethod.GET)
	  public @ResponseBody String submitFullStackCode(HttpServletRequest request, HttpServletResponse response,@RequestParam String qMapperInstanceId) throws Exception {
		 ModelAndView model= new ModelAndView("test");
		 User user = (User) request.getSession().getAttribute("user");
		 Test test = (Test) request.getSession().getAttribute("test");
		 QuestionMapperInstance questionMapperInstance = questionMapperInstanceRep.findById(Long.parseLong(qMapperInstanceId)).get();
		 questionMapperInstance.setWorkspaceSubmitted(true);
		 questionMapperInstanceRep.save(questionMapperInstance);
		 String workSpaceId = questionMapperInstance.getWorkSpaceId();
		 //String projname = 
		 String workSpaceFolder = questionMapperInstance.getWorkspaceUrl().substring(questionMapperInstance.getWorkspaceUrl().indexOf("che/")+4, questionMapperInstance.getWorkspaceUrl().length());
		 String codebasePath = propertyConfig.getFullStackCodeLocation()+File.separator+workSpaceId+File.separator+workSpaceFolder;
		 System.out.println(" workSpaceFolder is "+workSpaceFolder);
		 System.out.println(" codebasePath is "+codebasePath);
		 /**
		  * step 1 create a file called sonar-project.properties in workspace foldewr
		  * 
		  * */
		 String analysisFile = "";
		 if(questionMapperInstance.getQuestionMapper().getQuestion().getFullstack().getStack().equalsIgnoreCase(FullStackOptions.JAVA_FULLSTACK.getStack())){
			System.out.println("doing code quality on java stack");
			 analysisFile = FileUtils.readFileToString(new File(propertyConfig.getSonarAnalysisFileLocation()));
		 }
		 else if(questionMapperInstance.getQuestionMapper().getQuestion().getFullstack().getStack().equalsIgnoreCase(FullStackOptions.PHP_FULLSTACK.getStack())) {
			 System.out.println("doing code quality on php stack");
			 analysisFile = FileUtils.readFileToString(new File(propertyConfig.getSonalAnalysisFilePHPLocation()));
		 }
		 else if(questionMapperInstance.getQuestionMapper().getQuestion().getFullstack().getStack().equalsIgnoreCase(FullStackOptions.ANGULARJS_FULLSTACK.getStack()) ) {
			 System.out.println("doing code quality on php stack");
			 analysisFile = FileUtils.readFileToString(new File(propertyConfig.getSonarAnalysisFileAngularLocation()));
		 }
		 else if(questionMapperInstance.getQuestionMapper().getQuestion().getFullstack().getStack().equalsIgnoreCase(FullStackOptions.JAVASCRIPT_FULLSTACK.getStack()) ) {
			 System.out.println("doing code quality on php stack");
			 analysisFile = FileUtils.readFileToString(new File(propertyConfig.getSonarAnalysisFileAngularLocation()));
		 }
		 else{
			 System.out.println("doing code quality on others stack");
			 analysisFile = FileUtils.readFileToString(new File(propertyConfig.getSonarAnalysisFileLocation()));
		 }
	
		analysisFile = analysisFile.replace("${key}", workSpaceFolder);
		String writeLoc = codebasePath+File.separator+"sonar-project.properties";
		System.out.println(" writeLoc is "+writeLoc);
		try {
			System.out.println(" writting");
			FileUtils.write(new File(writeLoc), analysisFile);
			System.out.println(" written");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" analysis file written is ");
		 /**
		  * step 2 create a file called analyze.sh in workspace foldewr
		  * 
		  */
		 //not needed
		 
		 /**
		  * Step 3 Analyze
		  */
		 Process process;
		try {
			System.out.println("1 ");
			ProcessBuilder builder = new ProcessBuilder();
			System.out.println("2 ");
			 String[] command = {"/opt/softwares/sonar-scanner-3.3.0.1492/bin/sonar-scanner"};
			 System.out.println("3 ");
			 builder.redirectErrorStream(true); // This is the important part
			 System.out.println("4 ");
			 builder.command(command);
			 System.out.println("5 "+codebasePath);
			 builder.directory(new File(codebasePath));
			 System.out.println("6 ");
			 process = builder.start();
			 System.out.println(" command given");
			 LogStreamReader lsr = new LogStreamReader(process.getInputStream());
			 System.out.println("7 ");
			 Thread thread = new Thread(lsr, "LogStreamReader");
			 thread.start();
			 System.out.println("8 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// InputStream is = process.getInputStream();
		 
		 String html = FileUtils.readFileToString(new File(propertyConfig.getCodeQualityEmailTemplateLocation()));
		 String url = propertyConfig.getCodeQualityServerLink()+URLEncoder.encode(workSpaceFolder);
		 html = html.replace("{FULL_NAME}", user.getFirstName()+" "+user.getLastName());
		 html = html.replace("{TEST_NAME}", test.getTestName());
		 html = html.replace("{CODE_QUALITY_URL}", url);
		 
		 String reviewer = questionMapperInstance.getQuestionMapper().getQuestion().getReviewerEmail();
		 html = html.replace("{PROJECT_URL}", questionMapperInstance.getUsageDocumentUrl()==null?"":questionMapperInstance.getUsageDocumentUrl());
		 html = html.replace("{REVIEWER_EMAIL}", reviewer);
		 html = html.replace("{PASSWORD}", ""+reviewer.hashCode());
		 html = html.replace("{Company}", user.getCompanyName());
		 html = html.replace("{BASE_URL}", propertyConfig.getBaseUrl()+"login");
		// String message = "Results can not be sent for "+user.getEmail()+" for test "+test.getTestName();
		 System.out.println(" sending mail with foll link "+url);
		 	EmailGenericMessageThread client = new EmailGenericMessageThread("jatin.sutaria@thev2technologies.com", "Code quality Report Link for "+user.getFirstName(), html, propertyConfig);
		 	String cc[] = {reviewer};
		 	client.setCcArray(cc);
		 	Thread th = new Thread(client);
			th.start();
		 return "Your code has been submitted for verification. This is a 2 step process - Code quality will be measured through automation and functional compilance will be judged by reviewer.";
	 }
	 
	 
	 @RequestMapping(value = "/uploadProjectDocs", method = RequestMethod.POST)
	 public @ResponseBody String doUpload(@RequestParam("addimage") MultipartFile addimage,HttpServletRequest request, HttpServletResponse response, @RequestParam String qMapperInstanceId) throws Exception {     
		 String docUrl = "";
		 ModelAndView mav = null;
			User user = (User) request.getSession().getAttribute("user");
			List<Question> questions = new ArrayList<Question>();
			if(addimage != null){
				String fileName = qMapperInstanceId+(user.getFirstName()+user.getLastName()+System.currentTimeMillis())+addimage.getOriginalFilename();
				 String destination = propertyConfig.getFileServerLocation()+File.separator+"docs"+File.separator+fileName;
				 File file = new File(destination);
				 	if( file.exists()){
				 		if(addimage.getOriginalFilename() != null && addimage.getOriginalFilename().trim().length() > 0){
				 			FileUtils.forceDelete(file);
				 		}
				 		
				 	}
				 	if(addimage.getOriginalFilename() != null && addimage.getOriginalFilename().trim().length() > 0){
				 		 docUrl = propertyConfig.getFileServerWebUrl()+"docs/"+fileName;
						
				 		 addimage.transferTo(file);
				 	}
				
				
			}
			
			QuestionMapperInstance questionMapperInstance = questionMapperInstanceRep.findById(Long.parseLong(qMapperInstanceId)).get();
			questionMapperInstance.setUsageDocumentUrl(docUrl);
			//questionMapperInstance.setWorkspaceSubmitted(true);
			questionMapperInstanceRep.save(questionMapperInstance);
	     return docUrl;
	 }
	
	
}

class LogStreamReader implements Runnable {
	Logger logger = LoggerFactory.getLogger(LogStreamReader.class);
    private BufferedReader reader;

    public LogStreamReader(InputStream is) {
        this.reader = new BufferedReader(new InputStreamReader(is));
    }

    public void run() {
        try {
            String line = reader.readLine();
            while (line != null) {
            	logger.info(line);
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error writing stack", e);
        }
    }
}

