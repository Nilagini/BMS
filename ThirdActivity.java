package com.example.bloodbankmanagement;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankmanagement.R;

public class ThirdActivity extends ActionBarActivity {
	String fname, lname, email;
	int donorage;

	RelativeLayout Linear;
	SQLiteDatabase mydbnw;
	TableRow tableRow;
	TextView textView, textView1, textView2, textView3, textView4, textView5;
	private static String DBNAME = "DonorDatabase.db";
	private static String TABLE = "MY_TABLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdpage);
		Linear = (RelativeLayout) findViewById(R.id.linear);
		createTable();
	}

	public void createTable() {
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			mydbnw.execSQL("CREATE TABLE IF  NOT EXISTS " + TABLE
					+ " (NAME TEXT PRIMARY KEY, AGE INTEGER);");
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error in creating table",
					Toast.LENGTH_LONG);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addData(View view) {
		EditText edittext1 = (EditText) findViewById(R.id.firstname);
		EditText edittext2 = (EditText) findViewById(R.id.age);
		if (edittext1.getText().toString().trim().equals("")) {
			Toast t = Toast.makeText(getApplicationContext(),
					"first name required!", Toast.LENGTH_SHORT);
			edittext1.setError("First name is required!");
			// You can Toast a message here that the Username is Empty
		} else {
			fname = edittext1.getText().toString();
			try {
				donorage = Integer.parseInt(edittext2.getText().toString());
			} catch (NumberFormatException nfe) {
				System.out.println("Could not parse " + nfe);
			}

			try {
				mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,
						null);
				mydbnw.execSQL("INSERT INTO " + TABLE + "(NAME, AGE) "
						+ "VALUES('" + fname + "','" + donorage + "')");
				mydbnw.close();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"Error in inserting into table", Toast.LENGTH_LONG);
			}
		}
	}

	public void showdata(View view) {
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			Cursor c = mydbnw.rawQuery("SELECT * from " + TABLE, null);
			int count = c.getCount();
			c.moveToFirst();
			TableLayout tableLayout = new TableLayout(getApplicationContext());
			tableLayout.setVerticalScrollBarEnabled(true);
			TableRow tableRow,tableRow1 = null;
			TextView textView, textView1,textView2;
			tableRow = new TableRow(getApplicationContext());
			tableRow1 = new TableRow(getApplicationContext());
			textView = new TextView(getApplicationContext());
			textView1 = new TextView(getApplicationContext());
			
			textView.setText("Firstname");
			textView.setTextColor(Color.RED);
			textView.setTypeface(null, Typeface.BOLD);
			textView.setPadding(20, 20, 20, 20);
			tableRow.addView(textView);
			tableLayout.addView(tableRow);
			
			textView1.setText("Age");
			textView1.setTextColor(Color.RED);
			textView1.setTypeface(null, Typeface.BOLD);
			textView1.setPadding(20, 20, 20, 20);
			tableRow.addView(textView1);
			tableLayout.addView(tableRow);
			
			
			
			for (Integer j = 0; j < count; j++) {
				tableRow = new TableRow(getApplicationContext());
				textView2 = new TextView(getApplicationContext());
				textView2.setText(c.getString(c.getColumnIndex("NAME")));
				textView2.setPadding(10, 10, 10, 10);

				tableRow1 = new TableRow(getApplicationContext());
				textView1 = new TextView(getApplicationContext());
				textView1.setText(c.getString(c.getColumnIndex("AGE")));
				textView1.setPadding(10, 10, 10, 10);
				
				tableRow.addView(textView2);
				tableRow.addView(textView1);

				tableLayout.addView(tableRow);
				//tableLayout.addView(tableRow1);
				c.moveToNext();
			}
			setContentView(tableLayout);
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error encountered.",
					Toast.LENGTH_LONG);
		}
	}

}

