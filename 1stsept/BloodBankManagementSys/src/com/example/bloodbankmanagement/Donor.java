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

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.*;
public class Donor extends ActionBarActivity {
	String fname, bloodgroup, available, location;
	int donorage;
    private String URL_NEW_PREDICTION = "http://10.0.2.2/tech/new_predict.php";
    private Button btnAddPrediction,btnReadData;
    static String DBNAME = "BBMS";
	static String TABLE = "Donor_details";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_registration);
        btnAddPrediction = (Button) findViewById(R.id.button2);
        btnAddPrediction.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
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
                // TODO Auto-generated method stub
            	
                new AddNewPrediction().execute(bloodgroup, location, available);
              
            }
        });
        
        
       btnReadData = (Button) findViewById(R.id.button3);
        btnReadData.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), DataRetrieve.class);
				startActivityForResult(intent, 0);
				
			}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class AddNewPrediction extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
        	
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String blood_Group = arg[0];
            String loca_tion = arg[1];
            String avai_lable = arg[2];
           // Toast.makeText(getApplicationContext(), goalNo,Toast.LENGTH_LONG).show();
            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Blood_group", bloodgroup));
            params.add(new BasicNameValuePair("Location", location));
            params.add(new BasicNameValuePair("Available", available));

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
        //	Toast.makeText(getApplicationContext(), cardNo,Toast.LENGTH_LONG).show();
           // super.onPostExecute(result);
        }
    }
    
}




















//public class ThirdActivity extends ActionBarActivity {
	//String fname, bloodgroup, available, location;
	//int donorage;

	//RelativeLayout Linear;
	//SQLiteDatabase mydbnw;
//	TableRow tableRow;
	//TextView textView, textView1, textView2, textView3, textView4, textView5;
	//static String DBNAME = "DonorDatabase1.db";
	//static String TABLE = "MY_TABLE";

	//@Override
/*	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdpage);
		Linear = (RelativeLayout) findViewById(R.id.linear);
		//dropTable();
		createTable();
	}
*/
	/*public void createTable() {
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			mydbnw.execSQL("CREATE TABLE IF  NOT EXISTS " + TABLE
					+ " (BLOOD_GROUP TEXT,LOCATION TEXT,AVAILABLE TEXT);");
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error in creating table",
					Toast.LENGTH_LONG);
		}
	}*/

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
	/*public void addData(View view) {

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
*/
/*	public void showdata(View view) {
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
	}*/

/*	public void dropTable() {
		try {
			mydbnw = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			mydbnw.execSQL("DROP TABLE " + TABLE);
			mydbnw.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Error encountered while dropping.", Toast.LENGTH_LONG);
		}
	}*/
	
	
	
	
	

