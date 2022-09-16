package dip.clever.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

public class Json {
	public static List<Map<String, Object>> parse(String data){
		List<Map<String, Object>> jsonList = new ArrayList<>();
		String2 dat = new String2(data);
		String2 json;
		
		if(isList(dat))	dat.removeSide();		
		
		while(isJson(dat)) {
			json = getJson(dat);
			
			if(json == null)	break;
			
			jsonList.add(parse(json));
		}		
		
		return jsonList;
	}
	
	private static Map<String, Object> parse(String2 data){
		Map<String, Object> json = new HashMap<>();
		String key;
		Object value = null;
		
		data.removeSide();
		while(true) {
			key = getKey(data);
			
			if(key == null)		break;
			if(isJson(data))	value = parse(getJson(data));
			else 				value = getValue(data);
			
			json.put(key, value);
		}
		return json; 
	}
	
	private static String2 getJson(String2 data) {
		final int index = data.find("},");

		if (index == -1)	return data;
		return data.divide(index + 1);
	}

	private static String getKey(String2 data) {
		final int index = data.find(":");
		String2 key;
		if (index == -1)	return null;
		
		key = data.divide(index).removeSide();

		return key.get();
	}
	
	private static Object getValue(String2 data) {
		final int index = data.find(",");
		String2 dat;
		
		if(index >= 0)	dat = data.divide(index);
		else			dat = data;
		
		if(dat.equals("null"))	return null;		
		if(isString(dat))		return dat.removeSide().get();
		
		return getInt(dat);
	}
	
	private static boolean isList(String2 data) {
		return data.isFirst('[');
	}

	private static boolean isJson(String2 data) {
		return data.isFirst('{');
	}
	
	private static boolean isString(String2 data) {
		return data.isFirst('"');
	}
	
	public static void main(String[] args) {
		final String str = "[{\"categoryNo\":1,\"categoryName\":\"국가기술자격시험\"},{\"categoryNo\":2,\"categoryName\":\"민간자격시험\"},{\"categoryNo\":3,\"categoryName\":\"공무원시험\"}]";
		
		System.out.println(parse(str));
	}
	
	private static int getInt(String2 number) {
		return Integer.parseInt(number.get());
	}
}
