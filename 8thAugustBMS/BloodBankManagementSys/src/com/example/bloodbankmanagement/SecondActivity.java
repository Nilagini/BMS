package com.example.bloodbankmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.bloodbankmanagement.ThirdActivity;
import com.example.bloodbankmanagement.R;

public class SecondActivity extends Activity {

	public void onCreate(Bundle savedInstenceState) {
		super.onCreate(savedInstenceState);
		setContentView(R.layout.secondpage);
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ThirdActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						RequestActivity.class);
				startActivityForResult(intent, 0);
			}
		});

	}
}
