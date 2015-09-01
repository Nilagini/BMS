package com.example.bloodbankmanagement;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
	private Button btnRegister;
	private String username,password,code,phone_num,email,org_name;
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.org_registration);
	      
	      btnRegister = (Button) findViewById(R.id.register);
	      btnRegister.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	EditText eTun = (EditText) findViewById(R.id.username);
	        		EditText eTpwd = (EditText) findViewById(R.id.pwd);
	        		EditText eTmail = (EditText) findViewById(R.id.mail_id);
	        		EditText eTcode = (EditText) findViewById(R.id.code);
	        		EditText eTorgname = (EditText) findViewById(R.id.org_name);
	        		EditText eTphone = (EditText) findViewById(R.id.phonenumber);
	        			                
	            	username =  eTun.getText().toString();
	        		password =  eTpwd.getText().toString();
	        		email =  eTmail.getText().toString();
	        		code= eTcode .getText().toString();
	        		org_name=eTorgname.getText().toString();
	        		phone_num=eTphone.getText().toString();
	               new AddData().execute(phone_num, email,code,org_name,password,username);
	            }
	        });
	        
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
