package com.codepath.apps.basic.twitter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basic.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {

	private TwitterClient client;
	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private ListView lvTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		client = TwitterApplication.getRestClient();
		lvTweets = (ListView)findViewById(R.id.lvTweets);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(this, tweets);
		lvTweets.setAdapter(aTweets);
		populateTimeline(-1);
		
		 // Attach the listener to the AdapterView onCreate
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
		    @Override
		    public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
		    	populateTimeline(getMinId() - 1); 
		    }
	    });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweets, menu);
		return true;
	}
	
	public void populateTimeline(long maxId){
		client.getHomeTimeLine(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray array) {
				Log.d("debug ", array.toString());
				aTweets.addAll(Tweet.fromJsonArray(array));
				
			}
			
			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug ", e.toString());
				Log.d("debug ", e.toString());
			}
		}, maxId);
	}
	
	public long getMinId() {
		return aTweets.getItem(aTweets.getCount()-1).getUid();
	}
	
	
	public void onComposeClick(MenuItem mi) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivityForResult(i, 50);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i) {
 		if (resultCode == RESULT_OK) {
 			if (requestCode == 50) {
 				JSONObject jsonTweet;
 				try {
 					jsonTweet = new JSONObject(i.getExtras().getString("jsonTweet"));
 					aTweets.insert(Tweet.fromJson(jsonTweet), 0);
 				} catch (JSONException e) {
 					e.printStackTrace();
 					return;
 				}
 			}
 		}
	}
}
