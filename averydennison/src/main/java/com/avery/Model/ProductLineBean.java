package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;

import com.avery.dao.Partner_RBOProductLine;

public class ProductLineBean {
	
	ProductLineModel plm =new ProductLineModel();
	ArrayList<Object> productlinesList= new ArrayList<Object>();
	public static HashMap<Integer, Partner_RBOProductLine> productLineMap =new HashMap<Integer, Partner_RBOProductLine>();
	
	public HashMap<Integer, Partner_RBOProductLine> productLineMap(String email) throws Exception{
		
		productlinesList = plm.getPartnerRbo_productlines(email);
		Iterator<Object> iterator = productlinesList.iterator();
		Partner_RBOProductLine schemaInfo = new Partner_RBOProductLine();
		
		
		while (iterator.hasNext()) {
			schemaInfo = (Partner_RBOProductLine) iterator.next();
			productLineMap.put(schemaInfo.getId(), schemaInfo);
		}
		return productLineMap;
		
	}
	
	
	
	
	
	
	
	public HashMap<Integer, Partner_RBOProductLine> getProductLinesForEmail(){
		
		return productLineMap;
	}
	public void setProductLinesForEmail(String email) throws Exception{
		
		productLineMap = productLineMap(email);
	}
	
	
}
