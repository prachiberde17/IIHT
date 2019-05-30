package com.assessment.common;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.assessment.common.util.EmailGenericMessageThread;

public class SchedulerTask implements Runnable{

	Long testId;
	
	String testName;
	
	String companyId;
	
	String baseUrl;
	
	List<String> users = new ArrayList<>();
	
	String htmlLocation;
	
	PropertyConfig propertyConfig;
	
	public SchedulerTask(Long testId, String testName, String companyId, String baseUrl, List<String> users, String htmlLocation, PropertyConfig propertyConfig){
		this.testName = testName;
		this.users = users;
		this.testId = testId;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
		this.htmlLocation = htmlLocation;
		this.propertyConfig = propertyConfig;
	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		try {
			String html = htmlLocation;
			
				for(String email: users){
					String welcomeMailData = FileUtils.readFileToString(new File(html));
					welcomeMailData = welcomeMailData.replace("{TEST_NAME}", testName);
					String url = getUrlForUser(email);
					welcomeMailData = welcomeMailData.replace("{URL}", url);
					EmailGenericMessageThread client = new EmailGenericMessageThread(email, "Test Link - "+testName+" Sent by IIHT", welcomeMailData, propertyConfig);
					Thread th = new Thread(client);
					th.start();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 private String getUrlForUser(String email) {
		 String userBytes =  Base64.getEncoder().encodeToString(email.getBytes());
		 
		 String after = "userId="+URLEncoder.encode(userBytes)+"&testId="+URLEncoder.encode(testId.toString())+"&companyId="+URLEncoder.encode(companyId);
		 String url = baseUrl+"startTestSession?"+after;
		 return url;
	  }
	
	
}
