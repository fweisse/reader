package reader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.*;

/*
 * java -jar reader.jar C:\Users\Federico\workspace-mars\reader\src\main\resources\as-pkg-clinics.json
 */

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		String path ="C:\\as-pkg-clinics.json";
		if (args.length == 1) {
			path = args[0];
		}

		FileReader fr = new FileReader(path);
		JSONTokener t = new JSONTokener(fr);
		
		JSONObject obj = new JSONObject(t);
		
		System.out.println("CLINIC, #,  ID, NAME");
		JSONArray arr = obj.getJSONArray("array");
		for (int i = 0; i < arr.length(); i++)
		{
			JSONObject o = arr.getJSONObject(i);
			List<Integer> list = getClinicians(o);
			
			for(int c: list) {
				System.out.println(getClinic(o)
						+ ", "
						+ c 
						+ ", "
						+ getId(o, c)
						+ ", "
						+ getName(o, c)
						);	
			}
		     
		    
		   
		}
		
	}


	private static String getName(JSONObject o, int c) {
		return o.getString("Clinician " + c + " Name");
	}


	private static String getId(JSONObject o, int c) {
		return o.getString("Clinician " + c + " ID");
	}


	private static String getClinic(JSONObject o) {
		return o.getString("itemName()");
	}
	
	
	private static List<Integer> getClinicians(JSONObject obj) {
		List<Integer> l = new ArrayList();
		int beginIdx = "Clinician ".length();
		int endIdx = beginIdx +1;
		for (String key : obj.keySet()) {
			if (key.startsWith("Clinician")) {
				Integer i = Integer.valueOf(key.substring( beginIdx, endIdx));
				if (!l.contains(i)) {
					l.add(i);
				}
			}
		}
		Collections.sort(l);
		return l;
	}

}
