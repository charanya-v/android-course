package com.exercise.android.googleimagesearch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {

	EditText etQuery;
	Button btnSearch;
	GridView gvResults;
	private ImageSearchSettings searchSettings;
	private static final String SEARCH_KEY = "searchSettings";
	
	List<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				intent.putExtra("result", imageResult);
				startActivity(intent);
			}
			
		});
		
		 // Attach the listener to the AdapterView onCreate
        gvResults.setOnScrollListener(new EndlessScrollListener() {
	    @Override
	    public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
	        customLoadMoreDataFromApi(page); 
                // or customLoadMoreDataFromApi(totalItemsCount); 
	    }

		private void customLoadMoreDataFromApi(int offset) {
			fetchImages();
		}
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void setupViews(){
		etQuery = (EditText) findViewById(R.id.etQuery);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvResults = (GridView) findViewById(R.id.gvResults);
	}
	

	public void onImageSearch(View v){
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
		fetchImages();
	}
	
	private void fetchImages() {
		String query = etQuery.getText().toString();
		AsyncHttpClient client = new AsyncHttpClient();
		//https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=barack%20obama&userip=INSERT-USER-IP
		String httpQuery = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" +
	            "start=" + 0 + "&v=1.0" ;
		
		if (null != searchSettings) {
			httpQuery += "&imgcolor=" + searchSettings.getColorFilter();
			httpQuery += "&imgsz=" + searchSettings.getImageSize();
			httpQuery += "&imgtype=" + searchSettings.getImageType();
			httpQuery += "&as_sitesearch=" + searchSettings.getSiteFilter();
		}
		
		httpQuery += "&q=";
		
		client.get(httpQuery + Uri.encode(query), new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject response) {
					JSONArray imageJsonResults = null;
					Log.d("DEBUG", response.toString());
					try {
						imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
						imageResults.clear();
						imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
						Log.d("DEBUG", imageResults.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		});
	}
	
	public void onSettings(MenuItem mi) {
        Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ImageSearchSettingsActivity.class);
        
        //pass data
        i.putExtra(SEARCH_KEY, searchSettings);
        //execute
        startActivityForResult(i, 50); //arbitrary request code = 50
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 		if (resultCode == RESULT_OK) {
 			if (requestCode == 50) {
 				searchSettings = (ImageSearchSettings) data.getSerializableExtra(SEARCH_KEY);
 				fetchImages();
 				Toast.makeText(this, searchSettings.toString(), Toast.LENGTH_SHORT).show();
 			}
 		}
	}
			
}
