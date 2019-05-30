package com.test.eclipseche.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.assessment.eclipseche.services.EclipseCheService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestWorkspaceService {
	EclipseCheService eclipseCheService = new EclipseCheService();
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testCreateWorkSpace() throws Exception{
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/Java_FullStack.json"));
		json = json.replace("${APP_USER}", "tikamsingh-3456-67894224"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	@Test
	public void testCreateWorkSpacePHP() throws Exception{
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/PHP_MySQL.json"));
		json = json.replace("${APP_USER}", "php-3456-67894224"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	@Test
	public void testCreateWorkSpaceAngularJavascript() throws Exception{
		String json = FileUtils.readFileToString(new File("assessment/eclipseChe/ANGULAR_JAVASCRIPT_MYSQL.json"));
		json = json.replace("${APP_USER}", "angular-3456-67894224"+System.currentTimeMillis());
		//json = json.replace("${APP_USER}", "a01");
		json = json.replace("${APP_DESC}", "Sample............................Project\n\n\n.........");
		
		WorkspaceResponse workspaceResponse = eclipseCheService.createWorkSpace(json);
		System.out.println(workspaceResponse.getLinks().getIde());
	}
	
	
	@Test
	public void testWorkspacesFetch() throws Exception{
		URL url2 = new URL("http://13.233.2.169:8080/api/workspace?skipCount=0&maxItems=500");
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	
	     String data = getResponse(conn);
	     List<WorkspaceResponse> workspaceResponses = mapper.readValue(data.getBytes(), new TypeReference<List<WorkspaceResponse>>() {});
	     	for(WorkspaceResponse response : workspaceResponses){
	     		if(response.getConfig().getName().contains("GulrezFarooqi-171-170") || response.getConfig().getName().contains("RamGopal-171-170-1554") || response.getConfig().getName().contains("RamG-171-170-15541162")){
	     			System.out.println("*************");
	     		}
	     		else{
	     			deleteWorkspace(response.getId());
	     		}
	     	}
	     System.out.println(workspaceResponses.size());
	}
	
	private void deleteWorkspace(String id) throws Exception{
		String url = "http://13.233.2.169:8080/api/workspace/"+id;
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("DELETE");
		conn.setRequestProperty("Content-Type", "application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String data = getResponse(conn);
		System.out.println(data);
	}
	
	public String getResponse(HttpURLConnection con) {
		if(con!=null){
			
			try {
				
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
						
			   String input;
			   String output="";
						
			   while ((input = br.readLine()) != null){
				   output +=input;
			   }
			   br.close();
			   return output;
						
			} catch (IOException e) {
			   e.printStackTrace();
			}
					
		       }
				
		   return null;
	}
}
