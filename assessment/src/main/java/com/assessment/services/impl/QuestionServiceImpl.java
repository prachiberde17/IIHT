package com.assessment.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.Exceptions.AssessmentGenericException;
import com.assessment.Exceptions.ErrorCodes;
import com.assessment.common.ApplicationConstants;
import com.assessment.common.ExcelReader;
import com.assessment.data.Question;
import com.assessment.data.QuestionMapper;
import com.assessment.repositories.QuestionMapperRepository;
import com.assessment.repositories.QuestionRepository;
import com.assessment.services.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapperRepository questionMapperRepository;

	@Transactional
	public void createQuestion(Question question) {
		// questionRepository.f
		Question q = questionRepository.findByPrimaryKey(
				question.getQuestionText(), question.getCompanyId());
		if (q != null) {
//			throw new AssessmentGenericException("Question already exists");// Make
//																			// it
//																			// externalized
//																			// and
//			/**																// configurable
			/**
			 * To make multiple upload of same questions possible.
			 */
			Mapper mapper = new DozerBeanMapper();
			question.setId(q.getId());
			mapper.map(question, q);
			q.setUpdateDate(new Date());
			questionRepository.save(q);
		}
		else {
			question.setCreateDate(new Date());
			questionRepository.save(question);
		}
		
	}

	@Override
	public Page<Question> findQuestionsByQualifier1AndPage(String companyId,
			String qualifier1, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier1(companyId,
				qualifier1, PageRequest.of(pageNumber, 10));
	}

	public Page<Question> findQuestionsByQualifier2AndPage(String companyId,
			String qualifier1, String qualifier2, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier2(companyId,
				qualifier1, qualifier2, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Page<Question> findQuestionsByQualifier3AndPage(String companyId,
			String qualifier1, String qualifier2, String qualifier3, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier3(companyId,
				qualifier1, qualifier2, qualifier3, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Page<Question> findQuestionsByQualifier4AndPage(String companyId,
			String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier4(companyId,
				qualifier1, qualifier2, qualifier3, qualifier4, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Page<Question> findQuestionsByQualifier5AndPage(String companyId,
			String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, String qualifier5, Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier5(companyId,
				qualifier1, qualifier2, qualifier3, qualifier4, qualifier5, PageRequest.of(pageNumber, 10));
	}

	@Transactional
	public List<Question> uploadQuestionsFromExcelDoc(
			FileInputStream fileInputStream, File mappingObjectFile) {
		List<Question> questions = null;
		try {
			questions = ExcelReader.parseExcelFileToBeans(fileInputStream,
					mappingObjectFile);
			if (questions.size()>0) {
				for (Question question : questions) {
					createQuestion(question);
				}
			}
		} catch (Exception e) {
			throw new AssessmentGenericException(
					ErrorCodes.ERROR_WHILE_PARSING_EXCEL_DOCUMENT);
		}
		return questions;
	}

	@Override
	public List<Question> findQuestions(String companyId) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByCompanyId(companyId);
	}

	public Page<Question> searchQuestions(@Param("companyId") String companyId, @Param("searchText")  String searchText, Integer pageNumber){
		return questionRepository.searchQuestions(companyId, searchText, PageRequest.of(pageNumber, 10));
	}

	@Override
	public Question findById(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.findById(id).get();
	}

	

	@Override
	public Page<Question> findQuestionsByPage(String companyId, Integer pageNumber) {
		// TODO Auto-generated method stub
		//return questionRepository.findQuestionsByCompanyIdAndPageNumber(companyId, PageRequest.of(pageNumber, ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE));
		return questionRepository.findQuestionsByCompanyIdAndPageNumber(companyId, PageRequest.of(pageNumber, 10));
	}

	@Override
	public List<Question> findQuestionsByQualifier1(String companyId, String qualifier1) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier1(companyId, qualifier1);
	}

	@Override
	public List<Question> findQuestionsByQualifier2(String companyId, String qualifier1, String qualifier2) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier2(companyId, qualifier1, qualifier2);
	}

	@Override
	public List<Question> findQuestionsByQualifier3(String companyId, String qualifier1, String qualifier2,
			String qualifier3) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier3(companyId, qualifier1, qualifier2, qualifier3);
	}

	@Override
	public List<Question> findQuestionsByQualifier4(String companyId, String qualifier1, String qualifier2,
			String qualifier3, String qualifier4) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier4(companyId, qualifier1, qualifier2, qualifier3, qualifier4);
	}

	@Override
	public List<Question> findQuestionsByQualifier5(String companyId, String qualifier1, String qualifier2,
			String qualifier3, String qualifier4, String qualifier5) {
		// TODO Auto-generated method stub
		return questionRepository.findQuestionsByQualifier5(companyId, qualifier1, qualifier2, qualifier3, qualifier4, qualifier5);
	}

	@Override
	public List<Question> searchQuestions(String companyId, String searchText) {
		// TODO Auto-generated method stub
		return questionRepository.searchQuestions(companyId, searchText);
	}
	
	@Override
	public List<Question> getAllLevel1Questions(String companyId){
		return questionRepository.getAllLevel1Questions(companyId);
	}
	
	public boolean canDeleteQuestion(Long qid) {
		List<QuestionMapper> mappers = questionMapperRepository.findByQuestion_id(qid);
			if(mappers.size() > 0) {
				return false;
			}
			else {
				return true;
			}
	}

	@Override
	public void updateQuestion(Question q) {
		logger.info("In updateQuestion");
		// TODO Auto-generated method stub
		if(q.getId() == null) {
			throw new AssessmentGenericException("QUESTION_WITHOUT_ID_CAN_NOT_BE_UPDATED");
		}
		
		Question q2  = questionRepository.findById(q.getId()).get();
		if(q2 == null) {
			throw new AssessmentGenericException("QUESTION_TO_BE_UPDATED_DOESNT_EXIST");
		}
		logger.info("database q constr "+q2.getConstrnt());
		logger.info("q constr "+q.getConstrnt());
		Mapper mapper = new DozerBeanMapper();
		mapper.map(q, q2);
		q2.setUpdateDate(new Date());
		questionRepository.save(q2);
	}

	@Override
	public void removeQuestion(Long id) {
		// TODO Auto-generated method stub
		questionRepository.deleteById(id);
	}
}
