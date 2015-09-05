package com.example.bloodbankmanagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;

public class OrganizationLoginActivity extends Activity {
	private String URL_ITEMS = "http://10.0.2.2/bbms/get_orgpwd.php";
	private static final String Details = "jresponse";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static String u_name, passwrd;
	JSONArray matchFixture = null;
	ArrayList<HashMap<String, String>> matchFixtureList = new ArrayList<HashMap<String, String>>();
	Button login_btn, signup_btn;
	EditText ed_acu, ed_acp;
	boolean isFinished = true;
	int counter = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.org_loginpage);
		login_btn = (Button) findViewById(R.id.org_login);
		signup_btn = (Button) findViewById(R.id.org_signup);
		ed_acu = (EditText) findViewById(R.id.org_username);
		ed_acp = (EditText) findViewById(R.id.org_password);
		login_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new GetPassword().execute();
				while (isFinished) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (ed_acu.getText().toString().equals(u_name) &&

				ed_acp.getText().toString().equals(passwrd)) {

					Intent intent = new Intent(v.getContext(),
							RequestActivity.class);
					startActivityForResult(intent, 0);
				} else {
					Toast.makeText(getApplicationContext(),
							"Wrong Credentials", Toast.LENGTH_SHORT).show();
				}

			}
		});

		signup_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						OrganizationRegistration.class);
				startActivityForResult(intent, 0);

			}
		});

	}

	private class GetPassword extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg) {
			boolean isValidusername = false;
			Servicehandler serviceClient = new Servicehandler();
			Log.d("url: ", "> " + URL_ITEMS);
			String json = serviceClient.makeServiceCall(URL_ITEMS,
					Servicehandler.GET);
			// print the json response in the log
			Log.d("Get match fixture response: ", "> " + json);
			if (json != null) {
				try {
					Log.d("try", "in the try");
					JSONObject jsonObj = new JSONObject(json);
					Log.d("jsonObject", "new json Object");
					// Getting JSON Array node
					matchFixture = jsonObj.getJSONArray(Details);
					Log.d("json aray", "user point array");
					int len = matchFixture.length();
					Log.d("len", "get array length");
					for (int i = 0; i < matchFixture.length(); i++) {
						JSONObject c = matchFixture.getJSONObject(i);
						u_name = c.getString(USERNAME);
						Log.d("Blood_group", u_name);
						if (ed_acu.getText().toString().equals(u_name)) {
							passwrd = c.getString(PASSWORD);

							Log.d("Location", passwrd);
							isValidusername = true;
						}

					}
					if (!isValidusername) {
						Toast.makeText(getApplicationContext(),
								"wrong username", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					Log.d("catch", "in the catch");
					e.printStackTrace();
				}
			} else {
				Log.e("JSON Data", "Didn't receive any data from server!");
			}
			isFinished = false;
			return null;
		}

		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

		}
	}

	public static String getU_name() {
		return u_name;
	}

	public static void setU_name(String u_name) {
		OrganizationLoginActivity.u_name = u_name;
	}

}
