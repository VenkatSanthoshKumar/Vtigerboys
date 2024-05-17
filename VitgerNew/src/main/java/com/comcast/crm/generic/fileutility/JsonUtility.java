package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility { 
	public String getDataFromJsonFile(String Key) throws IOException, ParseException
	{   
		//step1 : parse Json Physical file into java using JSonPArse class
	 	FileReader filer=new FileReader("./ConfigAppData/comcommonData.json");
		JSONParser parser=new JSONParser();	
		Object obj=parser.parse(filer);
		
		//Step 2:-convert java object into JSONObject by DownCasting
		JSONObject map=(JSONObject)obj;
		String data=(String)map.get(Key);
		return data;
	}

}
