package com.automation.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.adeptia.indigo.services.ServiceException;

/**
 * @author Rakesh
 * Java interface to generate schema XSD based on comments
 *
 */
public interface SchemaInterface {
	
	/**
	 * Method to get HashSet<String> From Comma Separator String
	 * @param fileType
	 * @return HashSet<String>
	 * @author Rakesh
	 */
	public abstract HashSet<String> getFieldsForCommentVerification(String fileType) ;
	
	/**
	 * Method to compare commentValue with Standard fields
	 * @param compareValue
	 * @param fileType
	 * @return boolean
	 * @author Rakesh
	 */
	public abstract boolean compareCommentValue(String compareValue, String fileType);
	
	/**
	 * Method to check whether comment is valid
	 * @param comment
	 * @param fileType
	 * @return boolean
	 * @author Rakesh
	 */
	public abstract boolean verifyComment(String comment, String fileType);
	
	
	/**
	 * Method to get value or key from comment on basis of key
	 * @param comment
	 * @param key
	 * @return String
	 * @author Rakesh
	 */
	 public abstract String getValueFromComment(String comment, String key);
	 
	 /**
	 * Method to get count of particular field in comment
	 * @param comment
	 * @param field
	 * @return int
	 * @author Rakesh
	 */
	public abstract int getCount(String comment,String field);
	 
	 /**
	 *  Method to check whether list contains sectionName
	 * @param sectionName
	 * @return boolean
	 * @author Rakesh
	 */
	public abstract boolean checkListContainSection(String sectionName);
	 
	/**
	 * Method to generate XSD for Section 
	 * @param comment
	 * @param fileType
	 * @author Rakesh
	 */
	public abstract void generateSectionXSD(String comment , String fileType);
	 
	/**
	 * Method to generate XSD for Element
	 * @param comment
	 * @param fileType
	 * @author Rakesh
	 */
	public abstract void generateElementXSD(String comment, String fileType);
	 
	/**
	 * Method to generate final XSD from String buffer at same location of file
	 * @param inputFile
	 * @param sb
	 * @throws ServiceException
	 * @author Rakesh
	 */
	public abstract void generateXSDFromStringBuffer(String inputFile, StringBuffer sb) throws ServiceException;
	
	/**
	 * Method to get element name from OriginalName
	 * @param originalName
	 * @return String
	 * @author Rakesh
	 */
	public abstract String getElementNameUsingRegex(String originalName);
	 
	/**
	 * Method to add elementName and originalName in comment
	 * @param comment
	 * @param cell
	 * @return String
	 * @author Rakesh
	 */
	public abstract String modifyComment(String comment, Cell cell);
	 
	/**
	 * Method to generate XSD for key-values
	 * @param sectionType
	 * @param sectionName
	 * @param commentList
	 * @param fileType
	 * @author Rakesh
	 */
	public abstract void generateKeyValuesXSD(String sectionType, String sectionName,
			List<String> commentList, String fileType);
	 
	 /**
	 * Method to generate XSD for table
	 * @param sectionType
	 * @param sectionName
	 * @param commentList
	 * @param fileType
	 * @author Rakesh
	 */
	public abstract void generateTableXSD(String sectionType, String sectionName ,
				ArrayList<String> commentList, String fileType);
	 
	 /**
	 * Method to generate typeMap and commentMap
	 * @param comment
	 * @param fileType
	 * @param cell
	 * @author Rakesh
	 */
	public abstract void generateMapFromComment(String comment,String fileType, Cell cell);
	 
}
