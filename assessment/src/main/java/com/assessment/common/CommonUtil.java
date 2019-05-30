package com.assessment.common;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

public class CommonUtil {

	private CommonUtil() {
		// private constructor to avoid object creation as this class is static
	}

	public static void setCommonAttributesOfPagination(Page<?> paginationObject, ModelMap modelMap, int pageNumber, String callingMethod, Map<String, String> queryParams) {
		modelMap.put("recordsFrom", paginationObject.getNumber());
		modelMap.put("recordsTo", paginationObject.getNumberOfElements());
		modelMap.put("totalNumberOfRecords", paginationObject.getTotalElements());
		modelMap.put("totalNumberOfPages", paginationObject.getTotalPages());
		modelMap.put("selectedPage", pageNumber + 1);
		modelMap.put("previousPage", pageNumber - 1);
		modelMap.put("nextPage", pageNumber + 1);
		modelMap.put("recordsPerPage", ApplicationConstants.NUMBER_OF_RECORDS_PER_PAGE);
		modelMap.put("showPreviousPage", true);
		modelMap.put("showNextPage", true);
		modelMap.put("callingMethod", callingMethod);
			if(queryParams != null && queryParams.size() > 0) {
				String param = "";
				for(String key : queryParams.keySet()) {
					param += "&"+key+"="+queryParams.get(key);
				}
				modelMap.put("queryParam", param);
			}
		
		if (pageNumber == 0) {
			modelMap.put("showPreviousPage", false);
		}
		if (pageNumber + 1 == paginationObject.getTotalPages()) {
			modelMap.put("showNextPage", false);
		}
	}
}
