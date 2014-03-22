package me.osm.gazetter.striper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

public final class JSONFeature extends JSONObject {
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterator keys() {
		
		List<String> keys = new ArrayList<String>(keySet());
		Collections.sort(keys, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int i1 = "id".equals(o1) ? 0 : "ftype".equals(o1) ? 1 : GeoJsonWriter.TIMESTAMP.equals(o1) ? 2 : 10; 
				int i2 = "id".equals(o2) ? 0 : "ftype".equals(o2) ? 1 : GeoJsonWriter.TIMESTAMP.equals(o2) ? 2 : 10; 
				
				return i1 - i2;
			}
		});
		
		return keys.iterator();
	}
	
	public JSONFeature (JSONObject obj) {
		super(obj, JSONObject.getNames(obj));
	}
	
	public JSONFeature() {
		super();
	}
	
	public static JSONObject asRefer(JSONObject feature) {
		JSONObject result = new JSONObject();
		result.put("id", feature.getString("id"));
		result.put(GeoJsonWriter.PROPERTIES, feature.getJSONObject(GeoJsonWriter.PROPERTIES));
		return result;
	}

	public static List<JSONObject> asRefers(List<JSONObject> features) {
		List<JSONObject> result = new ArrayList<>();
		if(features != null) {
			for(JSONObject obj : features) {
				result.add(asRefer(obj));
			}
		}
		return result;
	}

}