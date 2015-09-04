package com.example.bloodbankmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.*;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends ActionBarActivity {
	private String location, bloodgroup;
	private String URL_ITEMS = "http://10.0.2.2/bbms/get_data.php";
	private static final String TAG_FIXTURE = "jresponse";
	private static final String DONOR_NAME = "username";
	private static final String TAG_MATCHID = "blood_group";
	private static final String TAG_TEAMA = "location";
	private static final String TAG_TEAMB = "available";
	public static boolean downloadurlFinished = false,
			getFixureFinished = false, downloadUrlTaskFinished = false;
	private String directionUrl, downloadedUrl;
	JSONArray matchFixture = null;
	HashMap<String, String> availableDonors = new HashMap<String, String>();
	 static ArrayList<HashMap<String, String>> availableDonorsList = new ArrayList<HashMap<String, String>>();
	static String dis;
	String ur1;
	String difference;
	SQLiteDatabase mydbnw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.searchpage);
		
		Button nearby=(Button)findViewById(R.id.bnearbyd);
		nearby.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), AvailableNearbyDonors.class);
				startActivityForResult(intent, 0);
				
			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	
	public void onClick(View view) {
		//this onclick method will start when click on the available donors button
		Spinner edittext1 = (Spinner) findViewById(R.id.searchd_location);	//retrieve the location to find the availble donors
		Spinner edittext2 = (Spinner) findViewById(R.id.searchb_group);		//retrieve the blood group to find the available donors
		location = edittext1.getSelectedItem().toString();
		bloodgroup = edittext2.getSelectedItem().toString();
		new GetFixture().execute();
		while (!getFixureFinished) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Intent intent = new Intent(view.getContext(), AvailableDonorTable.class);
		startActivityForResult(intent, 0);
		
		//new AvailableDonors().showAvalableDonors();

		new DownloadURL().execute(new String[] { directionUrl });

		while (!downloadurlFinished) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		new DownloadURLTask().execute(new String[] { downloadedUrl });
		while (!downloadUrlTaskFinished) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Toast.makeText(getApplicationContext(), dis, Toast.LENGTH_LONG).show();

	}

	private String getDirectionsUrl(String origin, String destination) {
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
				+ origin
				+ "&destinations="
				+ destination
				+ "&mode=driving&language=English&key=AIzaSyAdmyjZoP2uTdD9stXlJPOjD4qAWDGmN74";

		return url;
	}

	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
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

	private class DownloadURLTask extends AsyncTask<String, Void, String> {
		Object obj1, obj2, obj3, obj4, obj5;

		@Override
		protected String doInBackground(String... urls) {
			try {
				JSONParser parser = new JSONParser();
				obj1 = parser.parse(urls[0]);
				org.json.simple.JSONArray jarray1 = (org.json.simple.JSONArray) obj1;
				org.json.simple.JSONObject jobj1 = (org.json.simple.JSONObject) jarray1
						.get(1);

				obj2 = parser.parse((jobj1.get("rows")).toString());
				org.json.simple.JSONArray jarray2 = (org.json.simple.JSONArray) obj2;
				org.json.simple.JSONObject jobj2 = (org.json.simple.JSONObject) jarray2
						.get(0);

				obj3 = parser.parse((jobj2.get("elements")).toString());
				org.json.simple.JSONArray jarray3 = (org.json.simple.JSONArray) obj3;
				org.json.simple.JSONObject jobj3 = (org.json.simple.JSONObject) jarray3
						.get(0);

				obj4 = parser.parse((jobj3.get("distance")).toString());
				String[] sarr1 = obj4.toString().split("value");
				String[] sarr2 = sarr1[1].split("\\}");
				String[] sarr3 = sarr2[0].split(":");
				String[] sarr4 = sarr3[1].split(",");
				dis = sarr4[0];
			} catch (Exception e) {
				e.printStackTrace();
			}
			downloadUrlTaskFinished = true;
			return dis;
		}

		@Override
		protected void onPostExecute(String result) {

		}
	}

	private class GetFixture extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg) {
			String minDis = null;
			float defalultDis = 20000;
			Servicehandler serviceClient = new Servicehandler();
			Log.d("url: ", "> " + URL_ITEMS);
			String json = serviceClient.makeServiceCall(URL_ITEMS,
					Servicehandler.GET);
			// print the json response in the log
			Log.d("Get match fixture response: ", "> " + json);
			if (json != null) {
				try {

					JSONObject jsonObj = new JSONObject(json);
					Log.d("jsonObject", "new json Object");
					// Getting JSON Array node
					matchFixture = jsonObj.getJSONArray(TAG_FIXTURE);
					Log.d("json aray", "user point array");
					int len = matchFixture.length();
					String[] distance1 = new String[len];
					Log.d("len", "get array length");
					for (int i = 0; i < matchFixture.length(); i++) {
						JSONObject c = matchFixture.getJSONObject(i);
						String matchId = c.getString(TAG_MATCHID);
						Log.d("Blood_group", matchId);
						if (matchId.equals(bloodgroup)) {
							String teamA = c.getString(TAG_TEAMA);
							String dusername = c.getString(DONOR_NAME);
							Log.d("Location", teamA);
							directionUrl = getDirectionsUrl(teamA, location);

							// new
							// DownloadURL().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
							// new String[] { s });
							/*
							 * while(!task1Finished){ //Thread.currentThread();
							 * try { Thread.sleep(1000); } catch
							 * (InterruptedException e) { // TODO Auto-generated
							 * catch block e.printStackTrace(); } }
							 */
							// Float f;
							// Float f1;
							// String sss=difference;
							// Float f1=Float.parseFloat(dis);
							// f=f1/1000;
							/*
							 * distance1[i]=f.toString(); //minDis=distance1[j];
							 * if(Integer.valueOf(distance1[i])<defalultDis){
							 * minDis=distance1[i]; }
							 */
							// else{
							// minDis=String.valueOf(defalultDis);
							// }
							// defalultDis=Float.valueOf(distance1[i]);
							availableDonors.put(TAG_MATCHID, matchId);
							availableDonors.put(TAG_TEAMA, teamA);
							availableDonors.put(DONOR_NAME, dusername);
							availableDonorsList.add(availableDonors);
						}

						// adding each child node to HashMap key => value

					}

					// Toast.makeText(getApplicationContext(),dis,Toast.LENGTH_LONG).show();
				} catch (JSONException e) {
					Log.d("catch", "in the catch");
					e.printStackTrace();
				}
			} else {
				Log.e("JSON Data", "Didn't receive any data from server!");
			}
			getFixureFinished = true;
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
		}

	}

	private class DownloadURL extends AsyncTask<String, Void, String> {
		String ur = null;

		@Override
		protected String doInBackground(String... urls) {
			try {
				ur = downloadUrl(urls[0]);
				downloadedUrl = "[0," + ur + "]";

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			downloadurlFinished = true;
			return downloadedUrl;
		}

		@Override
		protected void onPostExecute(String result) {

		}
	}
	
	}
	

	

