package com.example.bloodbankmanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;



public class OrganizationLoginActivity extends Activity{
	   Button login_btn,signup_btn;
	   EditText ed_acu,ed_acp;
	   int counter = 3;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.org_loginpage);
	      login_btn=(Button)findViewById(R.id.login);
	      signup_btn=(Button)findViewById(R.id.signup);
	      ed_acu=(EditText)findViewById(R.id.username);
	      ed_acp=(EditText)findViewById(R.id.password);
	      
	     
	      
	      login_btn.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            if(ed_acu.getText().toString().equals("admin") &&
	         
	            ed_acp.getText().toString().equals("admin")) {
	            	
	            	Intent intent = new Intent(v.getContext(),
							RequestActivity.class);
					startActivityForResult(intent, 0);
	            }
	            else{
	               Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
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
}
