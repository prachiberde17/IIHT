package com.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.assessment.common.CompileData;
import com.assessment.services.impl.CompilerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestLocal {
	@Autowired
	CompilerService compilerService;
	
	String code = "import java.util.Scanner;"+



"class Abc{ "+
"	public static void main(String args[]){"+
"	Scanner reader = new Scanner(System.in);"+
"   String str = reader.nextLine();"+
"   System.out.print(str);"+
"	}"+

"}";
	
	String str = "//import libraries..Import more as you need\n#include <iostream>\n#include<stdio.h>\nusing namespace std;\n\nint main()\n{\n  int n, i;\n   cin >> n;\n\n   //Start coding here...\n boolean isPrime = true;    \n  for(i = 2; i <= n / 2; ++i){\n       if(n % i == 0){\n           isPrime = false;\n           break;\n       }\n  }\n  \n  if (isPrime){\n      printf(\"Prime\");\n  }\n  else{\n      printf(\"Not Prime\");\n  }\n   return 0;\n}";
	
	@Test
	public void testReadFileAndCompile() throws IOException{
		String data = FileUtils.readFileToString(new File("data.txt"));
		ObjectMapper mapper = new ObjectMapper();
		CompileData  data2 = mapper.readValue(data.getBytes(), CompileData.class);
		System.out.println(data2);
		 URL url = new URL("http://13.233.2.169:8088/compile"); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        connection.setRequestMethod("POST"); 
	        connection.setRequestProperty("Content-Type", "application/json"); 

	        OutputStream os = connection.getOutputStream(); 
	        os.write(str.getBytes());
			os.flush();
			InputStream is = connection.getInputStream();
			byte bat[] = new byte[is.available()];
			is.read(bat);
			String op = new String(bat);
	        connection.getResponseCode(); 
	        connection.disconnect(); 
	        System.out.println(op);
	}
	
	
	@Test
	public void testJavascriptProgram() throws Exception{
		String pg = FileUtils.readFileToString(new File("javascript_prg.txt"));
		CompileData data = new CompileData();
		data.setLanguage("4");
		data.setCode(pg);
		data.setStdin("44");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(data);
		System.out.println(str);
		 URL url = new URL("http://13.233.2.169:8088/compile"); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        connection.setRequestMethod("POST"); 
	        connection.setRequestProperty("Content-Type", "application/json"); 

	        OutputStream os = connection.getOutputStream(); 
	      
			os.write(str.getBytes());
			os.flush();
			InputStream is = connection.getInputStream();
			byte bat[] = new byte[is.available()];
			is.read(bat);
			String op = new String(bat);
	        connection.getResponseCode(); 
	        connection.disconnect(); 
	        System.out.println(op);
	}
	
	@Test
	public void testPHPProgram() throws Exception{
		String pg = FileUtils.readFileToString(new File("prime_php.txt"));
		CompileData data = new CompileData();
		data.setLanguage("3");
		data.setCode(pg);
		data.setStdin("29");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(data);
		System.out.println(str);
		 URL url = new URL("http://13.233.2.169:8088/compile"); 
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
	        connection.setDoOutput(true); 
	        connection.setInstanceFollowRedirects(false); 
	        connection.setRequestMethod("POST"); 
	        connection.setRequestProperty("Content-Type", "application/json"); 

	        OutputStream os = connection.getOutputStream(); 
	      
			os.write(str.getBytes());
			os.flush();
			InputStream is = connection.getInputStream();
			byte bat[] = new byte[is.available()];
			is.read(bat);
			String op = new String(bat);
	        connection.getResponseCode(); 
	        connection.disconnect(); 
	        System.out.println(op);
	}
	
	@Test
	public void testGetJson() throws Exception {
		CompileData data = new CompileData();
		data.setLanguage("7");
		data.setCode(code);
		data.setStdin("29");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(data);
		System.out.println(str);
		
		
	}
	
	@Test
	public void testCompilerWebservice(){
		for(int i=0;i<5;i++){
			try { 
				System.out.println(i);
		        URL url = new URL("http://13.233.2.169:8088/compile"); 
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
		        connection.setDoOutput(true); 
		        connection.setInstanceFollowRedirects(false); 
		        connection.setRequestMethod("POST"); 
		        connection.setRequestProperty("Content-Type", "application/json"); 

		        OutputStream os = connection.getOutputStream(); 
		        CompileData data = new CompileData();
				data.setLanguage("8");
				data.setCode(code);
				data.setStdin("29");
				ObjectMapper mapper = new ObjectMapper();
				String str = mapper.writeValueAsString(data);
				os.write(str.getBytes());
				os.flush();
				InputStream is = connection.getInputStream();
				byte bat[] = new byte[is.available()];
				is.read(bat);
				String op = new String(bat);
		        connection.getResponseCode(); 
		        connection.disconnect(); 
		        System.out.println(op);
		    } catch(Exception e) { 
		        throw new RuntimeException(e); 
		    } 
		}
		 
	}

}
