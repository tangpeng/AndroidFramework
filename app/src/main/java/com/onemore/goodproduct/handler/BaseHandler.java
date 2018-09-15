package com.onemore.goodproduct.handler;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseHandler {
	
//	private final String TAG = "BaseHandler" ;
	
	public abstract Object parseJSON(String jsonString)throws JSONException;
	
	public String checkResponse(String paramString) throws JSONException {
		if (paramString == null) {
			return null;
		} else {
			JSONObject jsonObject = new JSONObject(paramString);
			String result = jsonObject.getString("response");
			if (result != null && !result.equals("error")) {
				return result;
			} else {
				Log.e("Error", result.toString()) ;
				return null;
			}

		}
	}
	public String checkSuccessResponse(String paramString) throws JSONException {
		if (paramString == null) {
			return null;
		} else {
			JSONObject jsonObject = new JSONObject(paramString);
			String result = jsonObject.getString("success_response");
			if (result != null && !result.equals("error")) {
				return result;
			} else {
				Log.e("Error", result.toString()) ;
				return null;
			}
			
		}
	}
}
