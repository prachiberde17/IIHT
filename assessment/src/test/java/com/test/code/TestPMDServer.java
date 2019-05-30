package com.test.code;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.assessment.common.CodeInput;
import com.assessment.common.CodeOutput;

public class TestPMDServer {

	static String program = "import java.util.*;\n" +
	        "public class CodeGenTest {\n" +
	        "  public static void main(String[] args) {\n" +
	        "String str = \"ddd\"; \n"+
	        "    System.out.println(\"Hello World, from a generated program!\");\n" +
	        "  }\n" +
	        "}\n";
	
	@Test
	@Rollback(value=false)
	public void testPMDServer() {
		ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target("http://localhost/pmd-server-0.0.1-SNAPSHOT/evaluate");
        
        CodeInput codeInput = new CodeInput();
        codeInput.setCode(program);
        codeInput.setTestName("Dummy T4est");
        codeInput.setEmail("jatin.sutaria@email.com");
        CodeOutput codeOutput = target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(Entity.json(codeInput), CodeOutput.class);
      //  CodeOutput codeOutput = (CodeOutput) response.getEntity();
        System.out.println(codeOutput.getTestName());
	}
}
