package com.assessment.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.assessment.data.CandidateProfileParams;

public interface CandidateProfileParamsService {
	
	public CandidateProfileParams findUniqueCandidateProfileParams(String companyId,  String qualifier1,  String qualifier2, String qualifier3,  String qualifier4,  String qualifier5);

	public void saveOrUpdate(CandidateProfileParams candidateProfileParams);
	
	public List<CandidateProfileParams> findCandidateProfileParamsByCompanyId(String companyId);
	
}
