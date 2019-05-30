package com.assessment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.repositories.UserRepository;
import com.assessment.services.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	UserRepository userRepository;
	
	
	
}
