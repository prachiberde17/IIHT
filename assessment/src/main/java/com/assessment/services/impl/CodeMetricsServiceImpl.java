package com.assessment.services.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CodeMetrics;
import com.assessment.repositories.CodeMetricsRepository;
import com.assessment.services.CodeMetricsService;
@Service
@Transactional
public class CodeMetricsServiceImpl implements CodeMetricsService {

	@Autowired
	CodeMetricsRepository rep;
	
	
	@Override
	public void saveOrUpdate(CodeMetrics codeMetrics) {
		// TODO Auto-generated method stub
		CodeMetrics codeMetrics2 = findByPrimaryKey(codeMetrics.getCompanyId(), codeMetrics.getTestName(), codeMetrics.getEmail(), codeMetrics.getQuestionMapperInstanceId());
		if(codeMetrics2 == null){
			//create
			codeMetrics.setCreateDate(new Date());
			rep.save(codeMetrics);
		}
		else{
			//update
			Mapper mapper = new DozerBeanMapper();
			codeMetrics.setId(codeMetrics2.getId());
			codeMetrics.setUpdateDate(new Date());
			mapper.map(codeMetrics, codeMetrics2);
			rep.save(codeMetrics2);
		}
	}

	@Override
	public CodeMetrics findByPrimaryKey(String companyId, String testName, String email, Long qId) {
		// TODO Auto-generated method stub
		return rep.getUniqueCodeMetrics(companyId, testName, email, qId);
	}

	@Override
	public List<CodeMetrics> findCodeMetricsForTestAndQuestion(String companyId, String testName, Long qId) {
		// TODO Auto-generated method stub
		return rep.findCodeMetricsForTestAndQuestion(companyId, testName, qId);
	}

	@Override
	public List<CodeMetrics> findCodeMetricsForTest(String companyId, String testName) {
		// TODO Auto-generated method stub
		return rep.findCodeMetricsForTest(companyId, testName);
	}

}
