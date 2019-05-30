package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.QuestionMapperInstance;

public interface QuestionMapperInstanceRepository extends JpaRepository<QuestionMapperInstance,Long>{
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.questionText=:questionText and q.testName=:testName and q.sectionName=:sectionName and q.user=:user and q.companyId=:companyId")
	QuestionMapperInstance findUniqueQuestionMapperInstanceForUser(@Param("questionText") String questionText, @Param("testName") String testName, @Param("sectionName") String sectionName, @Param("user") String user,@Param("companyId") String companyId);

	@Query("SELECT q FROM QuestionMapperInstance q WHERE  q.testName=:testName and q.user=:user and q.companyId=:companyId GROUP BY q.questionMapper.id")
	List<QuestionMapperInstance> findQuestionMapperInstancesForUserForTest( @Param("testName") String testName,  @Param("user") String user, @Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.sectionName=:sectionName and q.testName=:testName and q.companyId=:companyId GROUP BY q.questionMapper.id")
	List<QuestionMapperInstance> findQuestionMapperInstancesForTestAndSection(@Param("sectionName") String sectionName, @Param("testName") String testName,   @Param("companyId") String companyId);

//	@Query("SELECT q FROM QuestionMapperInstance q WHERE  q.testName=:testName and q.user=:user and q.companyId=:companyId GROUP BY q.questionMapper.id")
//	List<QuestionMapperInstance> findUniqueQuestionMapperInstancesForUserForTest( @Param("testName") String testName,  @Param("user") String user, @Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.codeByUser IS NOT NULL GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findCodingQuestionMapperInstances(@Param("companyId") String companyId);
	
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.questionMapper.question.fullstack = 'JAVA_FULLSTACK' AND q.workspaceSubmitted = true GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJava(@Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.questionMapper.question.fullstack = 'DOTNET_FULLSTACK' AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForDotNet(@Param("companyId") String companyId);
	
	@Query("SELECT q FROM QuestionMapperInstance q WHERE q.companyId=:companyId AND q.workspaceUrl IS NOT NULL AND q.workSpaceId IS NOT NULL AND q.questionMapper.question.fullstack = 'JAVASCRIPT_FULLSTACK' AND q.workspaceSubmitted = true  GROUP BY q.questionMapper.id, q.user order by q.createDate desc")
	List<QuestionMapperInstance> findFullStackQuestionMapperInstancesForJavaScript(@Param("companyId") String companyId);

}

