package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.User;
import com.assessment.data.UserTestSession;
import com.assessment.reports.manager.AssessmentTestData;

public interface UserTestSessionRepository extends JpaRepository< UserTestSession,Long>{
	

	@Query("SELECT u FROM UserTestSession u WHERE u.user=:user and u.testName=:testName and u.companyId=:companyId")
	 UserTestSession findByPrimaryKey(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId);
	
	@Query("SELECT " +
	           "    new com.assessment.reports.manager.AssessmentTestData(u.percentageMarksRecieved, u.testName, u.user, u.noOfAttempts, u.pass, u.sectionResults, u.companyId, u.testInviteSent, u.sharedDirect, u.sectionsNoOfQuestionsNotAnswered, u.createDate, u.updateDate) " +
	           "FROM " +
	           "    UserTestSession u where u.companyId=:companyId " +
	           "GROUP BY " +
	           "    u.testName, u.user ORDER BY u.createDate desc")
	List<AssessmentTestData> getAllResultsData(@Param("companyId") String companyId);
	
	@Query("SELECT u FROM UserTestSession u WHERE u.testName=:testName and u.companyId=:companyId ORDER BY u.createDate desc, u.percentageMarksRecieved desc")
	 List<UserTestSession> findUserSessionsForTest( @Param("testName")  String testName, @Param("companyId") String companyId);
	
	@Query("SELECT u FROM UserTestSession u WHERE u.user LIKE CONCAT(:user,'%') and u.testName=:testName and u.companyId=:companyId")
	 List<UserTestSession> findByTestNamePart(@Param("user") String user, @Param("testName")  String testName, @Param("companyId") String companyId);



}
