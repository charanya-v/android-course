package com.codepath.apps.basic.twitter;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basic.twitter.fragments.OtherUserProfileFragment;
import com.codepath.apps.basic.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class OtherUserProfileActivity extends FragmentActivity {
	private TwitterClient client;
	private User otherUser = null;
	private String otherUserScreenName = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		otherUserScreenName = (String) getIntent().getStringExtra("otherUserProfile");
		if (null != otherUserScreenName) {
			loadOtherUserProfileInfo();
			OtherUserProfileFragment.setOtherUserScreenName(otherUserScreenName);
		} 
		setContentView(R.layout.activity_other_profile);
		
	}
	
	private void loadOtherUserProfileInfo() {
		client = TwitterApplication.getRestClient();
		
		client.getOtherUserTimeLine(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonObject) {
				Log.d("debug jsonObject ", jsonObject.toString());
				otherUser = User.fromJson(jsonObject);
				getActionBar().setTitle("@" + otherUser.getScreenName());
				
				populateProfileHeader(otherUser);
				Log.d("debug", otherUser.getScreenName());
			}

		}, otherUserScreenName);
		
	}
	
	private void populateProfileHeader(User u) {
		TextView tvName = (TextView) findViewById(R.id.tvUserName);
		TextView tvTagline = (TextView) findViewById(R.id.tvUserTagLine);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivImage = (ImageView) findViewById(R.id.ivUserImage);
		
		tvName.setText(u.getName());
		tvTagline.setText(u.getTagLine());
		tvFollowers.setText(u.getFollowersCount() + " Followers");
		tvFollowing.setText(u.getFollowingCount() + " Following");
		ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), ivImage);
		
		
	}
}
