package com.example.bloodbankmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

public class DistanceActivity extends ActionBarActivity {
	public void findDistance(View v){
		String s = getDirectionsUrl();
		String[] array;
		JSONParser parser = new JSONParser();
		Object obj1,obj2,obj3,obj4;
		Toast.makeText(getApplicationContext(),
				"nilaaa", Toast.LENGTH_LONG);
		try {
			String ur = downloadUrl(s);
			String ur1="[0,"+ur+"]";
			
			System.out.println(ur1);
			
			 obj1 = parser.parse(ur1);
	         JSONArray jarray1 = (JSONArray)obj1;
	         JSONObject jobj1 = (JSONObject)jarray1.get(1);
	         
	         obj2=parser.parse((jobj1.get("rows")).toString());
	         JSONArray jarray2 = (JSONArray)obj2;
	         JSONObject jobj2 = (JSONObject)jarray2.get(0);
	         
	         obj3=parser.parse((jobj2.get("elements")).toString());
	         JSONArray jarray3 = (JSONArray)obj3;
	         JSONObject jobj3 = (JSONObject)jarray3.get(0);
	                  
	        obj4=parser.parse((jobj3.get("distance")).toString());
	        String[] sarr1=obj4.toString().split("value");
	        String[] sarr2=sarr1[1].split("}");
	        String[] sarr3=sarr2[0].split(":");      
	        
	         Float distance=Float.valueOf(sarr3[1]);
	         String dis=distance.toString();
	        // System.out.println("Distance is "+distance/1000);
	         Toast.makeText(getApplicationContext(),
						dis, Toast.LENGTH_LONG);
	       
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	private  String getDirectionsUrl() {

		String origin = "manipay,jaffna";
		String destination = "Nallur,jaffna";
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
				+ origin
				+ "&destinations="
				+ destination
				+ "&mode=driving&language=English&key=AIzaSyAdmyjZoP2uTdD9stXlJPOjD4qAWDGmN74";

		return url;
	}

	/** A method to download json data from url */
	private  String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);
			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();
			// Connecting to url
			urlConnection.connect();
			// Reading data from url
			iStream = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			data = sb.toString();
			br.close();
		} catch (Exception e) {
			// Log.d("Exception while downloading url", e.toString());
			e.printStackTrace();
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}
}
