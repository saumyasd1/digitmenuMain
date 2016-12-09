package com.avery.Model;

import java.util.ArrayList;
import java.util.HashMap;




public interface OrderEmailQueueInterface {
	public HashMap<String, String> EmailSource(int id );
	public int getPartnerId(String email, String domain );
	public HashMap<Integer, String> getPartnerRbo(int partnerId);
	public int getPartnerProductLines(int partnerId );
	public int getPartner_rboProductLineId(int partnerId, int RboID, int productlineId );
	public int updateSchemaId(int schemaId );
	
}
