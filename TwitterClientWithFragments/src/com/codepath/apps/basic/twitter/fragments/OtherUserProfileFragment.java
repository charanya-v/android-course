package com.codepath.apps.basic.twitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.basic.twitter.TwitterApplication;
import com.codepath.apps.basic.twitter.TwitterClient;
import com.codepath.apps.basic.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class OtherUserProfileFragment extends TweetsListFragment {

	private TwitterClient twitterClient;
	private static String otherUserScreenName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		twitterClient = TwitterApplication.getRestClient();
		populateTimeLine(-1);
	}
	
	public static void setOtherUserScreenName(String screenName) {
		otherUserScreenName = screenName;
	}

	
	public void populateTimeLine(long maxId) {
		twitterClient.getOtherUserTimeLine(new JsonHttpResponseHandler() {
			
			@Override
			public void onSuccess(JSONArray jsonArray) {
				addAll(Tweet.fromJsonArray(jsonArray));
			}
			
			@Override
			public void onFailure(Throwable e) {
				Log.d("debug profile", e.toString());
			}
		}, otherUserScreenName);
		
	}
	
}
