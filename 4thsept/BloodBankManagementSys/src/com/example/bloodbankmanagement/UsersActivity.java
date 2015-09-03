package com.example.bloodbankmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.bloodbankmanagement.Donor;
import com.example.bloodbankmanagement.R;

public class UsersActivity extends Activity {

	public void onCreate(Bundle savedInstenceState) {
		super.onCreate(savedInstenceState);
		setContentView(R.layout.userspage);
		Button btn = (Button) findViewById(R.id.donor);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), Donor.class);
				startActivityForResult(intent, 0);
			}
		});
		Button btn2 = (Button) findViewById(R.id.b_makerequest);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						OrganizationLoginActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		Button btn3 = (Button) findViewById(R.id.admin);
		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), AdminLoginActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		

	}
}
