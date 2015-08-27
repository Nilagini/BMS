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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankmanagement.R;

public class ThirdActivity extends ActionBarActivity {
	String fname, bloodgroup, available, location;
	int donorage;

	RelativeLayout Linear;
	SQLiteDatabase mydbnw;
	TableRow tableRow;
	TextView textView, textView1, textView2, textView3, textView4, textView5;
	static String DBNAME = "DonorDatabase1.db";
	static String TABLE = "MY_TABLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdpage);
		Linear = (RelativeLayout) findViewById(R.id.linear);
		//dropTable();
		createTable();
	}

	public void createTable() {
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			mydbnw.execSQL("CREATE TABLE IF  NOT EXISTS " + TABLE
					+ " (BLOOD_GROUP TEXT,LOCATION TEXT,AVAILABLE TEXT);");
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

		Spinner edittext1 = (Spinner) findViewById(R.id.bloodgroup);
		Spinner edittext2 = (Spinner) findViewById(R.id.location);
		Spinner edittext3 = (Spinner) findViewById(R.id.Available);
		EditText edittext4 = (EditText) findViewById(R.id.firstname);
		if (edittext4.getText().toString().trim().equals("")) {
			Toast t = Toast.makeText(getApplicationContext(),
					"first name required!", Toast.LENGTH_SHORT);
			edittext4.setError("First name is required!");
			// You can Toast a message here that the Username is Empty
		}
		bloodgroup = edittext1.getSelectedItem().toString();
		location = edittext2.getSelectedItem().toString();
		available = edittext3.getSelectedItem().toString();
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			mydbnw.execSQL("INSERT INTO " + TABLE
					+ "(BLOOD_GROUP,LOCATION,AVAILABLE) " + "VALUES('"
					+ bloodgroup + "','" + location + "','" + available + "')");
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error in inserting into table", Toast.LENGTH_LONG);
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
			TableRow tableRow, tableRow1, tableRow2 = null;
			TextView textView, textView1, textView2, textView3;
			tableRow = new TableRow(getApplicationContext());
			tableRow1 = new TableRow(getApplicationContext());
			tableRow2 = new TableRow(getApplicationContext());
			textView = new TextView(getApplicationContext());
			textView1 = new TextView(getApplicationContext());
			textView2 = new TextView(getApplicationContext());

			textView.setText("Blood_group");
			textView.setTextColor(Color.RED);
			textView.setTypeface(null, Typeface.BOLD);
			textView.setPadding(10, 10, 10, 10);

			tableRow.addView(textView);
			tableLayout.addView(tableRow);

			textView1.setText("Location");
			textView1.setTextColor(Color.RED);
			textView1.setTypeface(null, Typeface.BOLD);
			textView1.setPadding(10, 10, 10, 10);

			tableRow.addView(textView1);
			tableLayout.addView(tableRow1);

			textView2.setText("Available");
			textView2.setTextColor(Color.RED);
			textView2.setTypeface(null, Typeface.BOLD);
			textView2.setPadding(10, 10, 10, 10);

			tableRow.addView(textView2);
			tableLayout.addView(tableRow2);

			for (Integer j = 0; j < count; j++) {
				tableRow = new TableRow(getApplicationContext());

				textView2 = new TextView(getApplicationContext());
				textView2.setText(c.getString(c.getColumnIndex("BLOOD_GROUP")));
				textView2.setPadding(10, 10, 10, 10);
				tableRow.addView(textView2);

				tableRow1 = new TableRow(getApplicationContext());
				textView1 = new TextView(getApplicationContext());
				textView1.setText(c.getString(c.getColumnIndex("LOCATION")));
				textView1.setPadding(10, 10, 10, 10);
				tableRow.addView(textView1);

				textView3 = new TextView(getApplicationContext());
				textView3.setText(c.getString(c.getColumnIndex("AVAILABLE")));
				textView3.setPadding(10, 10, 10, 10);
				tableRow.addView(textView3);

				tableLayout.addView(tableRow);
				c.moveToNext();

			}
			setContentView(tableLayout);
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error encountered.",
					Toast.LENGTH_LONG);
		}
	}

	public void dropTable() {
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			mydbnw.execSQL("DROP TABLE " + TABLE);
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error encountered while dropping.", Toast.LENGTH_LONG);
		}
	}

}
