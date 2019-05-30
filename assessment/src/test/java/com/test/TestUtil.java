package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.common.PropertyConfig;
import com.assessment.common.util.EmailGenericMessageThread;
import com.assessment.services.TenantService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUtil {
	@Autowired
	DataSource dataSourceRoot;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PropertyConfig propertyConfig;
	
	private static String LOCAL_BASE_URL="http://localhost/";
	
	private static String REMOTE_BASE_URL="http://13.59.126.83/";
	
	@Autowired
	TenantService tenantService;
	
//	private static String LOCAL_SCREEN_SHOT_FOLDER = "D:\\apache-tomcat-8.0.3\\bin";
	
//	private static String REMOTE_SCREEN_SHOT_FOLDER ="";
	
	@Test
	public void testGen() {
		int a = 1;
		int b = 2;
		DecimalFormat decimalFormat = new DecimalFormat("##.##");
		System.out.println(decimalFormat.format((float)a/(float)b * 100));
	}
	
	String dec = "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";
	
	@Test
	public void testDecode() throws IOException {
		
		byte b[] = Base64.getDecoder().decode(dec);
		FileUtils.writeByteArrayToFile(new File("screen.png"), b);
	}
	
	@Test
	public void testAllSchemas() throws SQLException {
//		String sql = "SHOW DATABASES";
//		ResultSet rs = dataSource.getConnection().createStatement().executeQuery(sql);
//			while(rs.next()) {
//				System.out.println(rs.getString(1));
//			}
		tenantService.findAllTenants(0);
	}
	
	@Test
	public void testCopy() throws IOException, SQLException {
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
		String tenantId = "EX1";
		String tenantName = "Example Tenant 1";
		String exampleConfig = propertyConfig.getDefaultReferenceConfigFileLocation()+File.separator+"config.properties";
		String tenatsFolder = propertyConfig.getTenantsConfigLocation();
		String tenantEmailId = "jatin.sutaria@thev2technologies.com";
		File file = new File(exampleConfig);
		
			/**
			 * Step0 - Create a schema for new tenant in database
			 */
		dataSourceRoot.getConnection().createStatement().execute("create schema "+tenantId);
		dataSourceRoot.getConnection().createStatement().execute("create user 'User_"+tenantId+"'@'%' IDENTIFIED BY 'password'");
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
					configData = configData.replace("${BASE_URL}", REMOTE_BASE_URL);
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
			String password = "password";
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
					message = "Hello \n\n Please go to http://localhost/"+tenantId+"/init?companyId=EX1&companyName=Example%20Tenant%201&tenantEmailId="+tenantEmailId+"\n"+
							"Thanks and Regards\n"
							+ "System Admin - Assessment Platform\n"
							+"IIHT";
				}
				else {
					message = "Hello \n\n Please go to "+(REMOTE_BASE_URL+tenantId)+"/init?companyId=EX1&companyName=Example%20Tenant%201&tenantEmailId="+tenantEmailId+"\n"+
							"Thanks and Regards\n"
							+ "System Admin - Assessment Platform\n"
							+"IIHT";
				}
			
					
			EmailGenericMessageThread runnable = new EmailGenericMessageThread(tenantEmailId, "Asessment Platform Setup Details", message, propertyConfig);
			Thread th = new Thread(runnable);
			th.start();
	//	}
		
		
		
		
//		FileUtils.forceMkdir(new File("D:\\apache-tomcat-8.0.3\\bin\\assessment\tenant1"));
//		FileUtils.copyDirectory(new File("D:\\apache-tomcat-8.0.3\\bin\\assessment\\reference"), new File("D:\\apache-tomcat-8.0.3\\bin\\assessment\\tenant1"));
//		FileUtils.copyDirectory(new File("D:\\apache-tomcat-8.0.3\\webapps\\assessment"), new File("D:\\apache-tomcat-8.0.3\\webapps\\tenant1"));
	}
	
//	@Test
//	@Rollback(value=false)
//	public void testCreateDataXml() throws SQLException, PropertyVetoException {
//		//dataSourceSource.getConnection().createStatement().execute("create schema deleteLater1");
//		//dataSourceSource.getConnection().createStatement().execute("create user 'deleteUser1'@'%' IDENTIFIED BY 'password'");
//		//dataSourceSource.getConnection().createStatement().execute("grant all privileges on deleteLater1.* to 'deleteUser1'@'%'");
//		ComboPooledDataSource dataSourceTarget = new ComboPooledDataSource();
//		dataSourceTarget.setJdbcUrl("jdbc:mysql://localhost:3306/deleteLater1");
//		dataSourceTarget.setDriverClass("com.mysql.jdbc.Driver");
//		dataSourceTarget.setUser("deleteUser1");
//		dataSourceTarget.setPassword("password");
//		org.apache.ddlutils.Platform platformSource = PlatformFactory.createNewPlatformInstance(dataSourceSource);
//		Database database = platformSource.readModelFromDatabase("model");
//		org.apache.ddlutils.Platform platformTarget =  PlatformFactory.createNewPlatformInstance(dataSourceTarget);
//		platformSource.createTables(platformTarget.readModelFromDatabase("model"), false, true);
//	}
//	

}
