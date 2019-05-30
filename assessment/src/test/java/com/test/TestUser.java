package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import com.assessment.data.User;
import com.assessment.data.UserType;
import com.assessment.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUser {
	
	@Autowired
	private UserService userService;
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testaddUser()
	{
		User user=new User();
		user.setEmail("contact@thev2technologies.com");
		user.setMobileNumber("989878");
		user.setFirstName("Tikam");
		user.setLastName("Singh");
		user.setPassword("12345");
		user.setCompanyId("ALS2019");
		user.setCompanyName("ALS2019");
		user.setDepartment("IT");
		user.setUserType(UserType.ADMIN);
		userService.addUser(user);
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testupdateUser()
	{
		User user=new User();
		user.setEmail("abc@gmail.com");
		user.setMobileNumber("9930070660");
		user.setFirstName("abc111");
		user.setLastName("xyz");
		user.setPassword("1234");
		user.setCompanyId("V2");
		user.setCompanyName("V2 Technologies");
		user.setDepartment("technical");
		userService.updateUser(user);
	}
	

}
