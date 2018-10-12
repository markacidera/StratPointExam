package com.stratpoint.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringJoiner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stratpoint.constant.RestConstants;

public class ProfileLogic {

	public String getProfileList() {
		StringJoiner sj = new StringJoiner("\n");
		try {

			URL url = new URL(RestConstants.PROFILE_API_LINK);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line = null;
			while ((line = br.readLine()) != null) {
				sj.add(line);

			}
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return sj.toString();
	}
	
	
	
	public String searchProfileName(String name) {
		String profiles = null;
		JSONArray profileList = new JSONArray(getProfileList());
		String upCaseName = name.toUpperCase();
		String upCaseValue;
		JSONArray ja = new JSONArray();
		for (int i = 0; i < profileList.length(); i++) {
			JSONObject profileObj = profileList.getJSONObject(i).getJSONObject("name");;
			for (String field : profileObj.keySet()) {
				upCaseValue = profileObj.get(field).toString().toUpperCase();
				if (upCaseValue.contains(upCaseName)) {
					ja.put(profileList.getJSONObject(i));
				}
			}
		}
		
		return ja.toString();
	}
}
