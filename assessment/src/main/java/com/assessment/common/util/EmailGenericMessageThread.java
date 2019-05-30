package com.assessment.common.util;

import java.io.File;

import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.common.PropertyConfig;

public class EmailGenericMessageThread  implements Runnable{
	private String emailSentTo;
	
	
	private String subject;
	
	private String message;
	
	String emailSentCC;
	
	PropertyConfig config;
	
	String  pdfAttachmentFile;
	
	String pdfAttachmentFileName;
	String ccArray[];
	
	public EmailGenericMessageThread(String emailSentTo, String subject, String message, PropertyConfig propertyConfig){
		this.emailSentTo = emailSentTo;
		this.subject = subject;
		this.message = message;
		config = propertyConfig;
	}
	
	public EmailGenericMessageThread(String emailSentTo, String subject, String message, String cc, PropertyConfig propertyConfig){
		this.emailSentTo = emailSentTo;
		this.subject = subject;
		this.message = message;
		this.emailSentCC = cc;
		config = propertyConfig;
	}
	
	public EmailGenericMessageThread(String emailSentTo, String subject, String message, String cc, PropertyConfig propertyConfig, String pdfAttachmentFile, String pdfAttachmentFileName){
		this.emailSentTo = emailSentTo;
		this.subject = subject;
		this.message = message;
		this.emailSentCC = cc;
		config = propertyConfig;
		this.pdfAttachmentFile = pdfAttachmentFile;
		this.pdfAttachmentFileName = pdfAttachmentFileName;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			
			HtmlEmail email = new HtmlEmail();
			  String host = config.getHostName();
			  String from = config.getSendFrom();
			  String fromName = config.getSendFromName();
			  String pass = config.getSendFromPassword();
			  String smtpPort = config.getSmtpPort();
			  email.setHostName(host);
			  email.setSmtpPort(Integer.parseInt(smtpPort));
			  //email.addTo("jatin.sutaria@thev2technologies.com");
			  String bccs[] = {"jatin.sutaria@thev2technologies.com", "tikamsingh9768@gmail.com"};
			  email.addBcc(bccs);//keep 4 arguments.
			  
			  
		  		email.addTo(emailSentTo);
		  			if(getEmailSentCC() != null && getEmailSentCC().trim().length() > 0) {
		  				System.out.println("cc is "+getEmailSentCC());
		  				email.addCc(emailSentCC);
		  			}
		  			
		  			if(getCcArray() != null && getCcArray().length > 0){
		  				System.out.println("ccsssss is "+getEmailSentCC());
		  				email.addCc(getCcArray());
		  			}
		  		email.setHtmlMsg(message);
		  		email.setSubject(subject);
			  	email.setCharset("UTF-8");
			  
			  email.setFrom(from, fromName);
			 
			  
			 
			  email.setAuthenticator(new DefaultAuthenticator(from, pass)	);
				email.setTLS(true);
				//email.setSmtpPort(Integer.parseInt(smtpPort));
				email.setSSL(true);
				/**
				 * Send attachment if there
				 */
				if(this.pdfAttachmentFile != null && this.pdfAttachmentFileName != null){
					byte data[] = FileUtils.readFileToByteArray(new File(this.pdfAttachmentFile));
					email.attach(new ByteArrayDataSource(data, "application/pdf"),
							pdfAttachmentFileName+".pdf", "Document description",
						       EmailAttachment.ATTACHMENT);
				}
				/**
				 * End attachemnt code
				 */

			  // send the email
			 // email.send();
				email.buildMimeMessage();
				email.sendMimeMessage();
			  System.out.println("Email Sent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AssessmentGenericException("Can not send Email", e);
		}
	}

	public String getEmailSentCC() {
		return emailSentCC;
	}

	public void setEmailSentCC(String emailSentCC) {
		this.emailSentCC = emailSentCC;
	}

	public String[] getCcArray() {
		return ccArray;
	}

	public void setCcArray(String[] ccArray) {
		this.ccArray = ccArray;
	}




	
	

}
