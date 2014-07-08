package com.codepath.apps.basic.twitter.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
	private String body;
	private long uid;
	private String createdAt;
	private User user;

	public static Tweet fromJson(JSONObject jsonObject) {
		Tweet tweet = new Tweet();
		try {
			tweet.body = jsonObject.getString("text");
			tweet.uid = jsonObject.getLong("id");
			tweet.createdAt = jsonObject.getString("created_at");
			tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return tweet;
	}

	public String getBody() {
		return body;
	}

	public long getUid() {
		return uid;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}

	public static ArrayList<Tweet> fromJsonArray(JSONArray array) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(array.length());
		for (int i =0; i<array.length(); i++){
			JSONObject jsonObject = null;
			try {
				jsonObject = array.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			Tweet tweet = fromJson(jsonObject);
			if (null != tweet) {
				tweets.add(tweet);
			}
		}
		return tweets;
			
	}
	
	@Override
	public String toString() {
		return this.getBody() + "-" + user.getScreenName();
	}
}

