package com.assessment.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.QuestionMapperInstance;
import com.assessment.repositories.QuestionMapperInstanceRepository;
import com.assessment.services.QuestionMapperInstanceService;

@Service
@Transactional
public class QuestionMapperInstanceServiceImpl implements
		QuestionMapperInstanceService {

	@Autowired
	QuestionMapperInstanceRepository questionMapperInstanceRepository;
	
	@Autowired
	EntityManagerFactory emf;
	
	public List<QuestionMapperInstance> getInstances(String qualifier1, String companyId){
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createNativeQuery("select * from QuestionMapperInstance questionma0_  join QuestionMapper questionma1_  join Question question2_ where questionma0_.questionMapper_id=questionma1_.id and questionma1_.question_id=question2_.id and question2_.qualifier1=:qualifier1 AND question2_.companyId=:companyId", QuestionMapperInstance.class);
		q.setParameter("qualifier1", qualifier1);
		q.setParameter("companyId", companyId);
		List<QuestionMapperInstance> list = q.getResultList();
		
		
		
		return list;
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<QuestionMapperInstance> query = builder.createQuery(QuestionMapperInstance.class);
//		Root<QuestionMapperInstance> root = query.from(QuestionMapperInstance.class);
//		Predicate where = builder.conjunction();
//		where = builder.equal(root.get("questionMapper").get("question").get("qualifier1"), qualifier1);
//		query = query.where(where);
//		
//		Query queryObj = entityManager.createQuery(query);
//		String wry = queryObj.toString();
//		System.out.println("wry "+wry);
//		List<QuestionMapperInstance> list = queryObj.getResultList();
//		return list;
	}
	
	public List<QuestionMapperInstance> getInstances(String qualifier1, String qualifier2, String companyId){
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createNativeQuery("select * from QuestionMapperInstance questionma0_  join QuestionMapper questionma1_  join Question question2_ where questionma0_.questionMapper_id=questionma1_.id and questionma1_.question_id=question2_.id and question2_.qualifier1=:qualifier1 AND question2_.companyId=:companyId AND question2_.qualifier2=:qualifier2", QuestionMapperInstance.class);
		q.setParameter("qualifier1", qualifier1);
		q.setParameter("qualifier2", qualifier2);
		q.setParameter("companyId", companyId);
		List<QuestionMapperInstance> list = q.getResultList();
		return list;

	}

	@Override
	public QuestionMapperInstance findUniqueQuestionMapperInstanceForUser(
			String questionText, String testName, String sectionName,
			String user, String companyId) {
		return questionMapperInstanceRepository
				.findUniqueQuestionMapperInstanceForUser(questionText,
						testName, sectionName, user, companyId);
	}

	@Override
	public List<QuestionMapperInstance> findQuestionMapperInstancesForUserForTest(
			String testName, String user, String companyId) {
		return questionMapperInstanceRepository
				.findQuestionMapperInstancesForUserForTest(testName, user,
						companyId);
	}

	@Override
	public boolean canEditTest(String sectionName, String testName, String companyId) {
		// TODO Auto-generated method stub
		List<QuestionMapperInstance> list = questionMapperInstanceRepository.findQuestionMapperInstancesForTestAndSection(sectionName, testName, companyId);
		return list.size() > 0 ? false:true;
	}

	@Override
	public List<QuestionMapperInstance> getInstances(String qualifier1, String qualifier2, String qualifier3,
			String companyId) {
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createNativeQuery("select * from QuestionMapperInstance questionma0_  join QuestionMapper questionma1_  join Question question2_ where questionma0_.questionMapper_id=questionma1_.id and questionma1_.question_id=question2_.id and question2_.qualifier1=:qualifier1 AND question2_.companyId=:companyId AND question2_.qualifier2=:qualifier2 AND question2_.qualifier3=:qualifier3", QuestionMapperInstance.class);
		q.setParameter("qualifier1", qualifier1);
		q.setParameter("qualifier2", qualifier2);
		q.setParameter("qualifier3", qualifier3);
		q.setParameter("companyId", companyId);
		List<QuestionMapperInstance> list = q.getResultList();
		return list;
	}

	@Override
	public List<QuestionMapperInstance> getInstances(String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, String companyId) {
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createNativeQuery("select * from QuestionMapperInstance questionma0_  join QuestionMapper questionma1_  join Question question2_ where questionma0_.questionMapper_id=questionma1_.id and questionma1_.question_id=question2_.id and question2_.qualifier1=:qualifier1 AND question2_.companyId=:companyId AND question2_.qualifier2=:qualifier2 AND question2_.qualifier3=:qualifier3 AND question2_.qualifier4=:qualifier4", QuestionMapperInstance.class);
		q.setParameter("qualifier1", qualifier1);
		q.setParameter("qualifier2", qualifier2);
		q.setParameter("qualifier3", qualifier3);
		q.setParameter("qualifier4", qualifier4);
		q.setParameter("companyId", companyId);
		List<QuestionMapperInstance> list = q.getResultList();
		return list;
	}

	@Override
	public List<QuestionMapperInstance> getInstances(String qualifier1, String qualifier2, String qualifier3,
			String qualifier4, String qualifier5, String companyId) {
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createNativeQuery("select * from QuestionMapperInstance questionma0_  join QuestionMapper questionma1_  join Question question2_ where questionma0_.questionMapper_id=questionma1_.id and questionma1_.question_id=question2_.id and question2_.qualifier1=:qualifier1 AND question2_.companyId=:companyId AND question2_.qualifier2=:qualifier2 AND question2_.qualifier3=:qualifier3 AND question2_.qualifier4=:qualifier4 AND question2_.qualifier5=:qualifier5", QuestionMapperInstance.class);
		q.setParameter("qualifier1", qualifier1);
		q.setParameter("qualifier2", qualifier2);
		q.setParameter("qualifier3", qualifier3);
		q.setParameter("qualifier4", qualifier4);
		q.setParameter("qualifier5", qualifier5);
		q.setParameter("companyId", companyId);
		List<QuestionMapperInstance> list = q.getResultList();
		return list;
	}

	

	@Override
	public List<QuestionMapperInstance> getInstancesOR(String qualifier, String companyId) {
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createNativeQuery("select * from QuestionMapperInstance questionma0_  join QuestionMapper questionma1_  join Question question2_ where questionma0_.questionMapper_id=questionma1_.id and questionma1_.question_id=question2_.id  AND question2_.companyId=:companyId AND ( question2_.qualifier1=:qualifier OR question2_.qualifier2=:qualifier OR question2_.qualifier3=:qualifier OR question2_.qualifier4=:qualifier OR question2_.qualifier5=:qualifier) ORDER BY questionma0_.createDate desc", QuestionMapperInstance.class);
		q.setParameter("qualifier", qualifier);
		q.setParameter("companyId", companyId);
		List<QuestionMapperInstance> list = q.getResultList();
		return list;
	}

	@Override
	public List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJava(String companyId) {
		// TODO Auto-generated method stub
		return questionMapperInstanceRepository.findFullStackQuestionMapperInstancesForJava(companyId);
	}

	@Override
	public List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForDotNet(String companyId) {
		// TODO Auto-generated method stub
		return questionMapperInstanceRepository.findFullStackQuestionMapperInstancesForDotNet(companyId);
	}

	@Override
	public List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJavaScript(String companyId) {
		// TODO Auto-generated method stub
		return questionMapperInstanceRepository.findFullStackQuestionMapperInstancesForJavaScript(companyId);
	}

}
