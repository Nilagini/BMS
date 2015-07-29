package com.example.bloodbankmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DonorActivity extends Activity{
	public void onCreate(Bundle savedInstenceState){
		super.onCreate(savedInstenceState);
		setContentView(R.layout.donorpage);
	}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		public void viewDetails(View v){
			ThirdActivity th=new ThirdActivity();
			th.showdata(v);
		}
			
	}


