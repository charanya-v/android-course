package com.codepath.apps.basic.twitter;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
		populateTimeline();
		lvTweets = (ListView)findViewById(R.id.lvTweets);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(this, tweets);
		lvTweets.setAdapter(aTweets);
	}
	
	public void populateTimeline(){
		client.getHomeTimeLine(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray array) {
				aTweets.addAll(Tweet.fromJsonArray(array));
				
			}
			
			
			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug ", e.toString());
				Log.d("debug ", e.toString());
			}
		});
	}
}