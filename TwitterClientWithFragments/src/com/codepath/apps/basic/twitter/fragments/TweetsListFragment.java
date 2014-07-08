package com.codepath.apps.basic.twitter.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basic.twitter.R;
import com.codepath.apps.basic.twitter.TweetArrayAdapter;
import com.codepath.apps.basic.twitter.models.Tweet;

public class TweetsListFragment extends Fragment {

	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private ListView lvTweets;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//inflate layout
		View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
		//assign view preferences
		lvTweets = (ListView)v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(aTweets);
		
		/*
		 // Attach the listener to the AdapterView onCreate
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
		    @Override
		    public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
		    	//populateTimeline(getMinId() - 1); 
		    }
	    });
	    */
		//return layout view
		return v;
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(getActivity(), tweets);
		
	}
	
	public void addAll(ArrayList<Tweet> tweets) {
		aTweets.addAll(tweets);
	}
	
	/*
	public long getMinId() {
		return aTweets.getItem(aTweets.getCount()-1).getUid();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent i) {
 		if (resultCode == 1) {
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
	*/
}
