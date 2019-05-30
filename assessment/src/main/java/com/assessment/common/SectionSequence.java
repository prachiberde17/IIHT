package com.assessment.common;

import java.util.ArrayList;
import java.util.List;

import com.assessment.web.dto.SectionInstanceDto;

public class SectionSequence {
	
	
	
	List<SectionInstanceDto> sectionInstanceDtos = new ArrayList<>();
	
	public SectionSequence(List<SectionInstanceDto> sectionInstanceDtos) {
		this.sectionInstanceDtos = sectionInstanceDtos;
	}
	
	public SectionInstanceDto nextSection(String currentSectionName) {
		for(int i=0 ;i<sectionInstanceDtos.size() ; i++) {
			SectionInstanceDto dto = 	sectionInstanceDtos.get(i);
			if(dto.getSection().getSectionName().equals(currentSectionName)) {
				if(i < (sectionInstanceDtos.size() -1)) {
					return sectionInstanceDtos.get(i+1);
				}
				else {
					return null;
				}
			}
		}
		
		return null;
	}
	
	public SectionInstanceDto prevSection(String currentSectionName) {
		for(int i=sectionInstanceDtos.size()-1 ;i>=0 ; i--) {
			SectionInstanceDto dto = 	sectionInstanceDtos.get(i);
			if(dto.getSection().getSectionName().equals(currentSectionName)) {
				if(i > 0) {
					return sectionInstanceDtos.get(i-1);
				}
				else {
					return null;
				}
			}
		}
		
		return null;
	}
	

}
