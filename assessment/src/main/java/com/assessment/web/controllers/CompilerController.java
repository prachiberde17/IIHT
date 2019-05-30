package com.assessment.web.controllers;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.common.CompileData;
import com.assessment.common.CompileOutput;
import com.assessment.services.impl.CompilerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CompilerController {
	@Autowired
	CompilerService compilerService;
	
	
	@RequestMapping(value = "/compile", method = RequestMethod.POST , consumes="application/json")
	  public @ResponseBody String compile(HttpServletRequest request, HttpServletResponse response,@RequestBody CompileData data) {
		try {
//			data = URLDecoder.decode(data);
//			ObjectMapper mapper = new ObjectMapper();
//			CompileData dat = mapper.readValue(data, CompileData.class);
			CompileOutput op=  compilerService.compile(data);
			return op.getOutput() +"\n\n"+op.getErrors();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Compiler Services not available. Contact your Test Administrator";
		} 
	  }
	
	//compileAndRunSystemTest
	@RequestMapping(value = "/compileAndRunSystemTest", method = RequestMethod.POST , consumes="application/json" )
	  public @ResponseBody String compileAndRunSystemTest(HttpServletRequest request, HttpServletResponse response,@RequestBody CompileData data) {
		try {
			
			CompileOutput op=  compilerService.compile(data);
				if(op.getErrors() != null && op.getErrors().trim().length() > 0){
					return op.getErrors() +"\n"+op.getOutput();
				}
			return op.getOutput().replaceAll("\n", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Compiler Services not available. Contact your Test Administrator";
		} 
	  }
}
