package com.assessment.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.common.Qualifiers;
import com.assessment.data.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>
{
	@Query("SELECT q FROM Question q WHERE q.companyId=:companyId")
	public List<Question> findQuestionsByCompanyId(@Param("companyId") String companyId);

	@Query("SELECT q FROM Question q WHERE q.companyId=:companyId")
	public Page<Question> findQuestionsByCompanyId(@Param("companyId") String companyId, Pageable pageable);
	
	@Query("SELECT q FROM Question q WHERE q.questionText=:questionText and q.companyId=:companyId")
	Question findByPrimaryKey(@Param("questionText") String questionText, @Param("companyId") String companyId);
	
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier1(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, Pageable pageable);
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier1(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1);
	
	
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier2(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, Pageable pageable);
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier2(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2);
	
	
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier3(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, Pageable pageable);
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier3(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3);

	
	
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier4(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, Pageable pageable);
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier4(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4);

	
	
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId")
	public Page<Question> findQuestionsByQualifier5(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5, Pageable pageable);
	@Query(value="SELECT q FROM Question q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId")
	public List<Question> findQuestionsByQualifier5(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5);

	
	
	@Query(value="SELECT q FROM Question q WHERE q.companyId=:companyId and q.questionText LIKE %:searchText%", countQuery="SELECT COUNT(*) FROM Question q WHERE q.companyId=:companyId and q.questionText LIKE %:searchText%")
	public Page<Question> searchQuestions(@Param("companyId") String companyId, @Param("searchText")  String searchText, Pageable pageable);
	@Query(value="SELECT q FROM Question q WHERE q.companyId=:companyId and q.questionText LIKE %:searchText%")
	public List<Question> searchQuestions(@Param("companyId") String companyId, @Param("searchText")  String searchText);

	@Query(value= "SELECT q FROM Question q WHERE q.companyId=:companyId", countQuery="SELECT COUNT(*) FROM Question q WHERE q.companyId=:companyId")
	public Page<Question> findQuestionsByCompanyIdAndPageNumber(@Param("companyId") String companyId, Pageable pageable);
	
	//@Query(value="SELECT q FROM Question q WHERE q.companyId=:companyId and (q.qualifier2 IS NULL or trim(q.qualifier2)='' ) GROUP BY q.qualifier1")
	@Query(value="SELECT q FROM Question q WHERE q.companyId=:companyId  GROUP BY q.qualifier1")
	public List<Question> getAllLevel1Questions(@Param("companyId") String companyId);
	
	 @Query("SELECT " +
	           "    new com.assessment.common.Qualifiers(q.qualifier1, q.qualifier2, q.qualifier3, q.qualifier4, q.qualifier5) " +
	           "FROM " +
	           "    Question q WHERE q.companyId=:companyId " +
	           "GROUP BY " +
	           "    q.qualifier1, q.qualifier2, q.qualifier3, q.qualifier4, q.qualifier5")
	public Set<Qualifiers> getAllUniqueQualifiers(@Param("companyId")String companyId);

}
