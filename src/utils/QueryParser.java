package utils;

import java.util.HashMap;
import java.util.Map;

public class QueryParser {
	private static QueryParser instance;
	
	private QueryParser(){ }
	
	public static synchronized QueryParser getInstance() {
		if(instance == null) {
			instance = new QueryParser();
		}
		
		return instance;
	}
	
	public Map<String, String> parse(String query) {
		if(query == null)	return null;
		Map<String, String> result = new HashMap<>();
		
		String[] queries = query.split("&");
		for (String s:queries) {
			String[] spl = s.split("=");
			result.put(spl[0], spl[1]);
		}
		return result;
	}
}