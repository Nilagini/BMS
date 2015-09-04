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
public class AvailableDonorTable  extends ListActivity {
private String distanceArray[];
    
    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_list);
        availableList();
        
        Button nearby=(Button)findViewById(R.id.nearby_donor);
		nearby.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				distanceArray=new String[SearchActivity.distanceList.size()];
				distanceArray=SearchActivity.distanceList.toArray(distanceArray);
				findNearbyDonor(distanceArray);
			}
		});
				// TODO Auto-generated method stub
	
       
    }
    public void availableList(){
    
    ListAdapter adapter = new SimpleAdapter(
            AvailableDonorTable.this, SearchActivity.availableDonorsList,
            R.layout.available_donors, new String[] {
            		"blood_group", "location","username"
            	}, new int[] {
            		R.id.avai_bg,R.id.avai_location,
                        R.id.avai_dname
            			}
    			);
    		setListAdapter(adapter);
    		}
    
    
    public void findNearbyDonor(String[] distance){
    	String minDis;
    	List<Float> distanceInFloat=new ArrayList<Float>();
    	for(int i=0;i<distance.length;i++){
    		distanceInFloat.add((Float.parseFloat(distance[i]))/1000);
    	}
    	Collections.sort(distanceInFloat);
		minDis=distanceInFloat.get(0).toString();
		
		Toast.makeText(getApplicationContext(), minDis, Toast.LENGTH_LONG).show();
    }
    
    
  }


