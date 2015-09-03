package com.example.bloodbankmanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends Activity  {
	   Button b1,b2;
	   EditText edu,edp;
	   
	   TextView tx1;
	   int counter = 3;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.admin_login_page);
	      
	      b1=(Button)findViewById(R.id.ad_login);
	      edu=(EditText)findViewById(R.id.ad_username);
	      edp=(EditText)findViewById(R.id.ad_password);
	     
	      b1.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	        	 
	            if(edu.getText().toString().equals("admin") &&
	         
	            edp.getText().toString().equals("admin")) {
	            	Intent intent = new Intent(v.getContext(), OrgAddActivity.class);
					startActivityForResult(intent, 0);
	               //Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
	            }
	            else{
	               Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
	              
	            }
	         }
	      });
	      
	   }
	   
	}