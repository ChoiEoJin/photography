package  com.collabo.photography.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestCommand {

	private Map<String, Object> parameterMap = new HashMap<String, Object>();

	public RequestCommand() {
	}

	public RequestCommand(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public Object put(String key, Object value) {
		return parameterMap.put(key, value);
	}

	public Object get(String key) {
		return parameterMap.get(key);
	}
	
	public List<String> getAllKeys() {
		List<String> l = new ArrayList<String>(this.parameterMap.keySet());
		return l;
	}

	public boolean containsKey(String key) {
		return parameterMap.containsKey(key);
	}

//	public float getFloat(String key) {
//		return Float.parseFloat(getString(key));
//	}
//	
	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}
//
//	public String getString(String key) {
//		return CommonUtils.toString(parameterMap.get(key));
//	}
//
//	public int getInt(String key) {
//		return CommonUtils.parseInt(parameterMap.get(key));
//	}
//
//	public long getLong(String key) {
//		return CommonUtils.parseLong(parameterMap.get(key));
//	}
//	
//	public double getDouble(String key) {
//		return CommonUtils.getDouble(parameterMap.get(key).toString());
//	}
//
//	public String[] getStringArray(String key) {
//		return CommonUtils.toStringArray(parameterMap.get(key));
//	}
}
