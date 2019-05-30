package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.Question;
import com.assessment.data.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	@Query("SELECT u FROM User u WHERE u.email=:email and u.companyId=:companyId")
	User findByPrimaryKey(@Param("email") String email, @Param("companyId") String companyId);
	
	@Query("SELECT u FROM User u WHERE u.companyId=:companyId")
	List<User> findByCompany( @Param("companyId") String companyId);
	
	@Query("SELECT u FROM User u WHERE u.email=:email and u.password=:password and u.companyId=:companyId")
	User findByPrimaryKeyAndPassword(@Param("email") String email, @Param("password") String password,  @Param("companyId") String companyId);
	
	@Query("SELECT q FROM User q WHERE q.companyId=:companyId and ( q.email LIKE %:searchText%  OR q.mobileNumber LIKE %:searchText%  OR q.firstName LIKE %:searchText%  OR q.lastName LIKE %:searchText%  OR q.department LIKE %:searchText% OR q.groupOfUser LIKE %:searchText% OR q.grade LIKE %:searchText%)")
	public List<User> searchQuestions(@Param("companyId") String companyId, @Param("searchText")  String searchText);
}