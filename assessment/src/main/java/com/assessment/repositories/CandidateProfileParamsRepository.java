package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CandidateProfileParams;

public interface CandidateProfileParamsRepository extends JpaRepository<CandidateProfileParams, Long> {
	
	@Query(value="SELECT q FROM CandidateProfileParams q WHERE q.qualifier1=:qualifier1 and q.qualifier2=:qualifier2 and q.qualifier3=:qualifier3 and q.qualifier4=:qualifier4 and q.qualifier5=:qualifier5 and q.companyId=:companyId")
	public CandidateProfileParams findUniqueCandidateProfileParams(@Param("companyId") String companyId, @Param("qualifier1") String qualifier1, @Param("qualifier2") String qualifier2, @Param("qualifier3") String qualifier3, @Param("qualifier4") String qualifier4, @Param("qualifier5") String qualifier5);

	@Query(value="SELECT q FROM CandidateProfileParams q WHERE q.companyId=:companyId")
	public List<CandidateProfileParams> findCandidateProfileParamsByCompanyId(@Param("companyId") String companyId);

}
