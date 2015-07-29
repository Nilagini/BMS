package com.example.bloodbankmanagement;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
	EditText name;
	EditText password;
	Button login;
	
	public void onCreate(Bundle savedInstenceState){
		super.onCreate(savedInstenceState);
		setContentView(R.layout.loginpage);
		name=(EditText)findViewById(R.id.name);
		password=(EditText)findViewById(R.id.password);
		login=(Button)findViewById(R.id.login);
		login.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		//Registration r=new Registration();
		//Cursor c = r.getDb3().rawQuery("SELECT pwd from DonorDetail where fname=name", null);
		/*int count = c.getCount();
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
		}*/
	//	String passwordo=c.getString(c.getColumnIndex("pwd"));
		//setContentView(tableLayout);
		//r.getDb3().close();
	
		// TODO Auto-generated method stub
		
		String na=name.getText().toString();
		String pa=password.getText().toString();
		switch(v.getId()){
		case R.id.login:
		//	if(pa.equals (passwordo)){
			//	Intent intent=new Intent(v.getContext(),DonorActivity.class);
				//startActivityForResult(intent, 0);
			//}
		
		}
		
		
		
		
	}
	
	
	

}
