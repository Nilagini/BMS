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

public class AvailableNearbyDonors extends ListActivity {
	private String distanceArray[];
	ArrayList<HashMap<String, String>> nearbyDonorsList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby_list);
		distanceArray = new String[SearchActivity.distanceList.size()];
		distanceArray = SearchActivity.distanceList.toArray(distanceArray);
		findNearbyDonor(distanceArray);

		// TODO Auto-generated method stub

	}

	public void findNearbyDonor(String[] distance) {
		String minDis;
		List<Long> distanceInLong = new ArrayList<Long>();
		for (int i = 0; i < distance.length; i++) {
			distanceInLong.add(Long.parseLong(distance[i]));
		}
		Collections.sort(distanceInLong);
		int x = 0;
		if (distanceInLong.get(0) == distanceInLong.get(1)) {
			while (distanceInLong.get(x) == distanceInLong.get(x + 1)) {
				minDis = distanceInLong.get(x).toString();
				HashMap<String, String> availableDonors = new HashMap<String, String>();
				for (int y = 0; y < distance.length; y++) {
					availableDonors = SearchActivity.availableDonorsList.get(y);
					if ((availableDonors.containsValue(minDis))) {
						String s = availableDonors.get("username");
						String loct = availableDonors.get("location");
						String phone_num = availableDonors.get("contact");
						HashMap<String, String> nearDonor = new HashMap<String, String>();
						nearDonor.put("username", s);
						nearDonor.put("location", loct);
						nearDonor.put("contact", phone_num);
						nearbyDonorsList.add(nearDonor);
					}

				}
			}
		}

		else {
			minDis = distanceInLong.get(x).toString();
			HashMap<String, String> availableDonors = new HashMap<String, String>();
			for (int y = 0; y < distance.length; y++) {
				availableDonors = SearchActivity.availableDonorsList.get(y);
				if ((availableDonors.containsValue(minDis))) {
					String s = availableDonors.get("username");
					String loct = availableDonors.get("location");
					String phone_num = availableDonors.get("contact");
					HashMap<String, String> nearDonor = new HashMap<String, String>();
					nearDonor.put("username", s);
					nearDonor.put("location", loct);
					nearDonor.put("contact", phone_num);
					nearbyDonorsList.add(nearDonor);
				}

			}

		}

		ListAdapter adapter1 = new SimpleAdapter(AvailableNearbyDonors.this,
				nearbyDonorsList, R.layout.nearby_donors, new String[] {
						"location", "username", "contact" }, new int[] {
						R.id.nearby_location, R.id.nearby_name,
						R.id.nearby_phone

				});
		setListAdapter(adapter1);

	}

}
