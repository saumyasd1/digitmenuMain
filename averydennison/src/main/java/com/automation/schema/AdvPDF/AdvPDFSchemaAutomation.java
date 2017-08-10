package com.automation.schema.AdvPDF;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.adeptia.indigo.logging.Logger;
import com.adeptia.indigo.services.ServiceException;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.annot.Annotation;
import com.automation.Utility.SchemaAutomation;


/**
 * @author Rakesh
 * Java Class to generate schema XSD based pdf comments
 *
 */
public class AdvPDFSchemaAutomation extends SchemaAutomation{
	
	public final static String fileType="PDF";
	
	public static Document document=null;
	

	/**
	 * Method to get document
	 * @param file
	 * @author Rakesh
	 */
	public void getDocument(String file) throws ServiceException{
		log.debug("Start method name=\"getDocument\"");
			document = PDF.open(new File(file));
			if(document == null){
				log.error("Document in null.");
				throw new ServiceException("Document in null.");
			}
		log.debug("End method name=\"getDocument\"");
	}
	
	/**
	 * Method to generate CommentMap and typeMap
	 * @throws ServiceException 
	 * @author Rakesh
	 */
	public void readComment() throws ServiceException{
		log.debug("Start method name=\"readComment\"");
		 for (Annotation a : document.getAllAnnotations()) {
		        if (a.getTypeCd().equals("/Highlight")) {
		        	String comment=a.getContents();
		        	if(verifyComment(comment, fileType)){
		        		generateMapFromComment(comment, fileType, null);
		        	}else{
		        		log.error("Comment isn't valid.");
		        		log.info("Invalid Comment  :"+comment);
		        	}
		        }
		    }
		 log.debug("End method name=\"readComment\"");
	}
	
	
	
	/**
	 * Method to generate Schema Definition for XSD 
	 * @author Rakesh
	 */
	public void generateSchemaDefinition(){
		log.debug("Start method name=\"generateSchemaDefinition\"");
		sb.append(commonSchemaDefinition+pdfSchemaDefinition);
		log.debug("End method name=\"generateSchemaDefinition\"");
	}
	
	
	/**
	 * Method to generate XSD for PDF
	 * @param file
	 * @author Rakesh
	 */
	public void generateSchemaXSD(String file,Logger _log) throws ServiceException{
		log=_log;
		log.debug("Start method name=\"generateSchemaXSD\"");
		
		if(file == null || file.trim().isEmpty()){
			log.error("File :\""+file+"\" isn't valid.");
			throw new ServiceException("File :\""+file+"\" isn't valid.");
		} else{
			try {
			
			getDocument(file);
			readComment();
			sb.append(XSDHeader + RootElementHeader + APPINFOHeader);
			generateSchemaDefinition();
			sb.append(APPINFOFooter + ComplexHeader + SequenceHeader);
			for (Map.Entry<String, String> typeEntry : super.typeMap.entrySet()) {
				String type = typeEntry.getValue();
				String name = typeEntry.getKey();
				for (Map.Entry<String, ArrayList<String>> commentEntry : super.commentMap
						.entrySet()) {
					String section_Name = commentEntry.getKey();
					if (type.equals("keyvalues")) {
						if (name.equals(section_Name)) {
							generateKeyValuesXSD(type, section_Name,
									super.commentMap.get(section_Name), fileType);
						}
					}
					if (type.equals("table")) {
						if (name.equals(section_Name)) {
							generateTableXSD(type, section_Name,
									super.commentMap.get(section_Name), fileType);
						}
					}
				}
			}
			
			sb.append(SequenceFooter + ComplexFooter + ElementFooter + XSDFooter);
			generateXSDFromStringBuffer(file, sb);
		}catch (Exception e) {
			log.error("Error while generating XSD." + e.getMessage(), e);
			throw new ServiceException("Error while generating XSD."
					+ e.getMessage(), e);

		}
		}
		log.debug("End method name=\"generateSchemaXSD\"");
	}
	
	
	


	/*public static void main(String[] args) throws IndigoException, ServiceException{
		String file ="E:\\PDF Schema\\TestPDFComment.pdf";
		AdvPDFSchemaAutomation pdfSchemaAutomation=new AdvPDFSchemaAutomation();
		pdfSchemaAutomation.generateSchemaXSD(file);
				
	}*/
	

	
}
