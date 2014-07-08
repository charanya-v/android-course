package com.codepath.apps.basic.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private long uid;
	private String name;
	private String screenName;
	private String profileImageUrl;
	private String followersCount;
	private String followingCount;
	private String tagLine;
	
	public static User fromJson(JSONObject jsonObject) {
		User user = new User();
		try {
			user.name = jsonObject.getString("name");
			user.uid = jsonObject.getLong("id");
			user.screenName = jsonObject.getString("screen_name");
			user.profileImageUrl = jsonObject.getString("profile_image_url");
			user.followersCount = jsonObject.getString("followers_count");
			user.followingCount = jsonObject.getString("friends_count");
			user.tagLine = jsonObject.getString("description");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return user;
	}

	public long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	
	public String getFollowersCount() {
		return followersCount;
	}
	
	public String getFollowingCount() {
		return followingCount;
	}
	
	public String getTagLine() {
		return tagLine;
	}

}
