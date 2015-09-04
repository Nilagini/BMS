package com.example.bloodbankmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OrganizationRegistration extends Activity{
	private String URL_NEW_PREDICTION = "http://10.0.2.2/bbms/save_org.php";
	private String URL_ITEMS = "http://10.0.2.2/bbms/get_authorizedorg.php";
	private static final String Details = "jresponse";
	private static final String ORG_NAME = "org_name";
	private static final String LOCATION = "location";
	private Button btnRegister;
	private String username,password,code,phone_num,email,org_name;
	JSONArray matchFixture = null;
	private boolean isAuthorizedname=false,isFinished=false;
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.org_registration);
	      
	      btnRegister = (Button) findViewById(R.id.register1);
	      btnRegister.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	EditText eTun = (EditText) findViewById(R.id.username1);
	        		EditText eTpwd = (EditText) findViewById(R.id.pwd1);
	        		EditText eTmail = (EditText) findViewById(R.id.mail_id1);
	        		EditText eTcode = (EditText) findViewById(R.id.code1);
	        		EditText eTorgname = (EditText) findViewById(R.id.org_name1);
	        		EditText eTphone = (EditText) findViewById(R.id.phonenumber1);
	        			                
	            	username =  eTun.getText().toString();
	        		password =  eTpwd.getText().toString();
	        		email =  eTmail.getText().toString();
	        		code= eTcode .getText().toString();
	        		org_name=eTorgname.getText().toString();
	        		phone_num=eTphone.getText().toString();
	        		isFinished = false;
	        		new GetAuthorizedOrg().execute();
					while (!isFinished) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (isAuthorizedname) {

						new AddData().execute(phone_num, email, code, org_name,password,username);
					} else {
						Toast.makeText(getApplicationContext(),
								"Wrong Credentials", Toast.LENGTH_SHORT).show();
					}

				}
			});
	} 
	
	
	private class GetAuthorizedOrg extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg) {
			
			String orgname,loct;
			
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
						orgname = c.getString(ORG_NAME);
						Log.d("orgname", orgname);
						if (org_name.equals(orgname)) {
							isAuthorizedname = true;
						}

					}
					if (!isAuthorizedname) {
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
			isFinished = true;
			return null;
		}

		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

		}
	}
	
	      private class AddData extends AsyncTask<String, Void, Void> {

	          @Override
	          protected void onPreExecute() {
	          	
	              super.onPreExecute();

	          }

	          @Override
	          protected Void doInBackground(String... arg) {
	              // TODO Auto-generated method stub
	              // Preparing post params
	        	  String cn,mail,code,orgname,pwd,un;
	        	  cn=arg[0];
	        	  mail=arg[1];
	        	  code=arg[2];
	        	  orgname=arg[3];
	        	  pwd=arg[4];
	        	  un=arg[5];
	              List<NameValuePair> params = new ArrayList<NameValuePair>();
	              params.add(new BasicNameValuePair("contactnumber", cn));
	              params.add(new BasicNameValuePair("email_id", mail));
	              params.add(new BasicNameValuePair("organizationbbcode", code));
	              params.add(new BasicNameValuePair("organization_name", orgname));
	              params.add(new BasicNameValuePair("password", pwd));
	              params.add(new BasicNameValuePair("username", un));

	              Servicehandler serviceClient = new Servicehandler();
	              String json = serviceClient.makeServiceCall(URL_NEW_PREDICTION,
	                      Servicehandler.POST, params);

	              Log.d("Create Prediction Request: ", "> " + json);

	              if (json != null) {
	                  try {
	                      JSONObject jsonObj = new JSONObject(json);
	                      boolean error = jsonObj.getBoolean("error");
	                      // checking for error node in json
	                      if (!error) {
	                          // new category created successfully
	                          Log.e("Prediction added successfully ",
	                                  "> " + jsonObj.getString("message"));
	                      } else {
	                          Log.e("Add Prediction Error: ",
	                                  "> " + jsonObj.getString("message"));
	                      }

	                  } catch (JSONException e) {
	                      e.printStackTrace();
	                  }

	              } else {
	                  Log.e("JSON Data", "JSON data error!");
	              }
	              return null;
	          }

	          @Override
	          protected void onPostExecute(Void result) {
	          
	          }
	      }
	      
	    
}
