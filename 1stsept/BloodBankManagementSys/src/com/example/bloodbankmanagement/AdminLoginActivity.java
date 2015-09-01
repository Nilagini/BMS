package com.example.bloodbankmanagement;

import android.app.Activity;
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
	      
	      b1=(Button)findViewById(R.id.login);
	      edu=(EditText)findViewById(R.id.username);
	      edp=(EditText)findViewById(R.id.password);
	      
	     // b2=(Button)findViewById(R.id.button2);
	      //tx1=(TextView)findViewById(R.id.textView3);
	      //tx1.setVisibility(View.GONE);
	      
	      b1.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            if(edu.getText().toString().equals("admin") &&
	         
	            edp.getText().toString().equals("admin")) {
	               Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
	            }
	            else{
	               Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
	               
	               //tx1.setVisibility(View.VISIBLE);
	               //tx1.setBackgroundColor(Color.RED);
	               //counter--;
	               //tx1.setText(Integer.toString(counter));
	            
	               //if (counter == 0) {
	                 // b1.setEnabled(false);
	              // }
	            }
	         }
	      });
	      
	   }
	   
	}