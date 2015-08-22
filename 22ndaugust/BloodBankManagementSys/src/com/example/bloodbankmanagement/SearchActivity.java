package com.example.bloodbankmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends ActionBarActivity {
	private String location, bloodgroup;
	String dis;
	String ur1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.searchpage);
		Spinner edittext1 = (Spinner) findViewById(R.id.location);
		Spinner edittext2 = (Spinner) findViewById(R.id.b_group);
		location = edittext1.getSelectedItem().toString();
		bloodgroup = edittext2.getSelectedItem().toString();
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "hiiiiiii",
						Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String getDirectionsUrl() {
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
				JSONArray jarray1 = (JSONArray) obj1;
				JSONObject jobj1 = (JSONObject) jarray1.get(1);

				obj2 = parser.parse((jobj1.get("rows")).toString());
				JSONArray jarray2 = (JSONArray) obj2;
				JSONObject jobj2 = (JSONObject) jarray2.get(0);

				obj3 = parser.parse((jobj2.get("elements")).toString());
				JSONArray jarray3 = (JSONArray) obj3;
				JSONObject jobj3 = (JSONObject) jarray3.get(0);

				obj4 = parser.parse((jobj3.get("distance")).toString());
				String[] sarr1 = obj4.toString().split("value");
				String[] sarr2 = sarr1[1].split("\\}");
				String[] sarr3 = sarr2[0].split(":");
				String[] sarr4 = sarr3[1].split(",");
				dis = sarr4[0];
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dis;
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), dis, Toast.LENGTH_LONG)
					.show();
		}
	}

	public void onClick(View view) {
		String s = getDirectionsUrl();
		DownloadURL du = new DownloadURL();
		du.execute(new String[] { s });
	}

	private class DownloadURL extends AsyncTask<String, Void, String> {
		String ur = null;
		@Override
		protected String doInBackground(String... urls) {
			try {
				ur = downloadUrl(urls[0]);
				ur1 = "[0," + ur + "]";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ur1;
		}

		@Override
		protected void onPostExecute(String result) {
			DownloadURLTask dt = new DownloadURLTask();
			dt.execute(new String[] { result });
		}
	}

}
