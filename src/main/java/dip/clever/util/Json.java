package dip.clever.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {
	private List<HashMap<String, Object>> jsonList = new ArrayList<>();
	private HashMap<String, Object> json;
	private String jsonStr;
	
	public Json(HashMap<String, String> param) {
		jsonStr = param.get("param");
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getObject() {
		String[] list;
		String key;
		Object value;

		replace("[").replace("{");
		jsonStr = jsonStr.replace("]", ",");

		list = jsonStr.split("},");
		for(String str: list) {			
			String[] strs = str.split(",");
			
			json = new HashMap<>();			
			for(String s : strs) {
				String[] split = s.split(":");				
				key = split[0].replace("\"", "");
				s = split[1];
				if (isString(s))
					value = s.replace("\"", "");
				else {
					if(s.equals("null"))value = null;
					else 			value = Integer.parseInt(s);
				}
				json.put(key, value);				
			}
			if(json.isEmpty()) break;
			jsonList.add(json);
		}
		return jsonList;
	}
	
	private boolean isString(String jsonString) {
		return jsonString.contains("\"");
	}
	
	private Json replace(String str) {
		jsonStr = jsonStr.replace(str, "");
		
		return this;
	}
}
