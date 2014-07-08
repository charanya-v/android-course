package com.codepath.apps.basic.twitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.basic.twitter.TwitterApplication;
import com.codepath.apps.basic.twitter.TwitterClient;
import com.codepath.apps.basic.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimeLineFragment extends TweetsListFragment {
	private TwitterClient client;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
		populateTimeline(-1);
	}
	
	public void populateTimeline(long maxId){
		client.getMentionsTimeLine(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray array) {
				Log.d("debug ", array.toString());
				addAll(Tweet.fromJsonArray(array));
				
			}
			
			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug ", e.toString());
				Log.d("debug ", e.toString());
			}
		}/*, maxId*/);
	}
}
