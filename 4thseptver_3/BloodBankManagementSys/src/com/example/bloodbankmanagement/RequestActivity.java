package com.example.bloodbankmanagement;

import com.example.bloodbankmanagement.Donor;

import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankmanagement.R;

public class RequestActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_page);

		  Button btn2=(Button) findViewById(R.id.b_makerequest);
		  btn2.setOnClickListener(new OnClickListener( ) { public void
		  onClick(View v){ Intent intent=new
		  Intent(v.getContext(),SearchActivity.class);
		  startActivityForResult(intent, 0); } });
		  
		  Button btn1=(Button) findViewById(R.id.req_donordetail);
		  btn1.setOnClickListener(new OnClickListener( ) { public void
		  onClick(View v){ Intent intent=new
		  Intent(v.getContext(),DataRetrieve.class);
		  startActivityForResult(intent, 0); } });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
