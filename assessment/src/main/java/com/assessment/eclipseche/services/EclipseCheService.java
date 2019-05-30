package com.assessment.eclipseche.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.assessment.eclipseche.config.response.WorkspaceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EclipseCheService {
String url = "http://13.233.2.169:8080/api/workspace?start-after-create=false&namespace=che";
	//http://52.66.22.44:8080
	//String url = "http://52.66.22.44:8080/api/workspace?start-after-create=false&namespace=che";
	
ObjectMapper mapper = new ObjectMapper();
	
	public WorkspaceResponse createWorkSpace(String  workspace) throws Exception{
		//workspace.setName(user);
		URL url2 = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//String postData = mapper.writeValueAsString(workspace);
		// String post = postData.toString();
		 String postData = workspace;
	     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	     conn.setDoOutput(true);
	     conn.getOutputStream().write(postDataBytes);
	     String data = getResponse(conn);
	     WorkspaceResponse workspaceResponse = mapper.readValue(data.getBytes(), WorkspaceResponse.class);
//		Client client = ClientBuilder.newClient( new ClientConfig() );
//		 
//		WebTarget webTarget = client.target(url);
//		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
//		String response = invocationBuilder.post(Entity.entity(mapper.writeValueAsString(workspace), MediaType.APPLICATION_JSON), String.class);
		return workspaceResponse;
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
