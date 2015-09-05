package com.example.bloodbankmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.view.View.OnClickListener;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AvailableDonorTable extends ListActivity {
	private ArrayList<HashMap<String, String>> nearbyDonorsList = new ArrayList<HashMap<String, String>>();
	SearchActivity sea=new SearchActivity();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.available_list);
		availableList();

		Button nearby = (Button) findViewById(R.id.nearby_donor);
		nearby.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(),
						AvailableNearbyDonors.class);
				startActivityForResult(intent, 0);

			}
		});
		// TODO Auto-generated method stub

	}

	public void availableList() {

		ListAdapter adapter = new SimpleAdapter(AvailableDonorTable.this,
				sea.getAvailableDonorsList(), R.layout.available_donors,
				new String[] { "blood_group", "location", "username",
						"p_number", "distance" }, new int[] { R.id.avai_bg,
						R.id.avai_location, R.id.avai_dname, R.id.p_num,
						R.id.avai_distance });
		setListAdapter(adapter);
	}

	

	public ArrayList<HashMap<String, String>> getNearbyDonorsList() {
		return nearbyDonorsList;
	}

	public void setNearbyDonorsList(
			ArrayList<HashMap<String, String>> nearbyDonorsList) {
		this.nearbyDonorsList = nearbyDonorsList;
	}
	
	

}
