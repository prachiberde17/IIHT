package com.assessment.common;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;

import com.assessment.Exceptions.AssessmentGenericException;
public class ExcelReader {

	/**
	* Parses an excel file into a list of beans.
	*
	* @param <T> the type of the bean
	* @param xlsFile the excel data file to parse
	* @param jxlsConfigFile the jxls config file describing how to map rows to beans
	* @return the list of beans or an empty list there are none
	* @throws Exception if there is a problem parsing the file
	*/
	public static <T> List<T> parseExcelFileToBeans(final InputStream xlsFile,
	                                                final File jxlsConfigFile)
	                                                throws Exception {
		final XLSReader xlsReader = ReaderBuilder.buildFromXML(jxlsConfigFile);
	 
	  final List<T> result = new ArrayList<>();
	  final Map<String, Object> beans = new HashMap<>();
	  beans.put("result", result);
		  try {
						xlsReader.read(xlsFile, beans);
		  }
		  catch(Exception r){
			  r.printStackTrace();
			  throw new AssessmentGenericException("Problems in converting excel into java beans");
		  }
	return result;	
	}
}