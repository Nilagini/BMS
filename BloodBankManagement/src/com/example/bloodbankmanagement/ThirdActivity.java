package com.example.bloodbankmanagement;

import android.support.v7.app.ActionBarActivity;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankmanagement.R;

public class ThirdActivity extends ActionBarActivity {
	String fname, lname, email;
	SQLiteDatabase db3;
	TableRow tableRow;
	TextView textView, textView1, textView2, textView3, textView4, textView5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdpage);

		db3 = openOrCreateDatabase("MyDB3", MODE_PRIVATE, null);
		db3.execSQL("CREATE TABLE IF NOT EXISTS DonorDetail(fname VARCHAR);");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addData(View view) {

		EditText edittext1 = (EditText) findViewById(R.id.firstname);
		if (edittext1.getText().toString().trim().equals("")) {
			Toast t=Toast.makeText(getApplicationContext(), "first name required!", Toast.LENGTH_SHORT);
			edittext1.setError("First name is required!");
			// You can Toast a message here that the Username is Empty
		} else {
			fname = edittext1.getText().toString();

			db3.execSQL("INSERT INTO  DonorDetail VALUES('" + fname + "');");

		}

	}

	public void showdata(View view) {
		Cursor c = db3.rawQuery("SELECT * from DonorDetail", null);
		int count = c.getCount();
		c.moveToFirst();
		TableLayout tableLayout = new TableLayout(getApplicationContext());
		tableLayout.setVerticalScrollBarEnabled(true);
		TableRow tableRow;
		TextView textView, textView1;
		tableRow = new TableRow(getApplicationContext());
		textView = new TextView(getApplicationContext());
		textView.setText("Firstname");
		textView.setTextColor(Color.RED);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setPadding(20, 20, 20, 20);
		tableRow.addView(textView);

		tableLayout.addView(tableRow);
		for (Integer j = 0; j < count; j++) {
			tableRow = new TableRow(getApplicationContext());
			textView1 = new TextView(getApplicationContext());
			textView1.setText(c.getString(c.getColumnIndex("fname")));
			textView1.setPadding(20, 20, 20, 20);

			tableRow.addView(textView1);

			tableLayout.addView(tableRow);
			c.moveToNext();
		}
		setContentView(tableLayout);
		db3.close();
	}

}