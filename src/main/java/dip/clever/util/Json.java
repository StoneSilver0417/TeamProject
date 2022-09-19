package dip.clever.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import dip.clever.mapper.QuestMapper;
import dip.clever.model.Choice;
import dip.clever.model.Quest;
import dip.clever.serviceImp.QuestServiceImp;

public class Json {
	// JSON.stringify로 받은 문자열 데이터를 해시맵 리스트로 변환
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
	
	// 한 개의 JSON 문자열 데이터를 해시맵으로 변환
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
		
		if(isString(data))		return getString(data);
		
		if(index >= 0)	dat = data.divide(index);
		else			dat = data;
		
		if(dat.equals("null"))	return null;		
		
		return getInt(dat);
	}

	private static String getString(String2 string) {
		final int index = string.removefirst().find("\"");
				
		return string.divide(index + 1).removeLast().get();
	}
	
	private static int getInt(String2 number) {
		return Integer.parseInt(number.get());
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
		final String str = "[{\"questSeq\":1,\"questContent\":\"소프트웨어 개발에 이용되는 모델(Model)에 대한 설명 중 거리가 먼 것은?\",\"answer\":null,\"choice\":{\"questNo\":0,\"c1\":\"스크럼 마스터(Scrum Master)는 스크럼 프로세스를 따르고, 팀이 스크럼을 효과적으로 활용할 수 있도록 보장하는 역할 등을 맡는다.\",\"c2\":\"제품 백로그(Product Backlog)는 스크럼 팀이 해결해야 하는 목록으로 소프트웨어 요구사항, 아키텍처 정의 등이 포함될 수 있다.\",\"c3\":\"오류로 인해 발생 될 수 있는 부정적인 내용을 적극적으로 사용자들에게 알려야 한다.\",\"c4\":\"소리나 색의 사용을 줄이고 텍스트로만 전달하도록 한다.\",\"c5\":null}}]";
		
		System.out.println(parse(str));
	}
}
