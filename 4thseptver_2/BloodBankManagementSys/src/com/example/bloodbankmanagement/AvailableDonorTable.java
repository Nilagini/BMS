package com.example.bloodbankmanagement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
public class AvailableDonorTable  extends ListActivity {

    
    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_list);
        availableList();
       
    }
    public void availableList(){
    
    ListAdapter adapter = new SimpleAdapter(
            AvailableDonorTable.this, SearchActivity.availableDonorsList,
            R.layout.available_donors, new String[] {
            		"blood_group", "location","username"
            	}
            		, new int[] {
            		R.id.avai_bg,R.id.avai_location,
                        R.id.avai_dname
            			}
    			);
    		setListAdapter(adapter);
    		}
    
			}