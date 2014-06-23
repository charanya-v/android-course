package com.codepath.apps.basic.twitter;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1"; 
    public static final String REST_CONSUMER_KEY = "GmAiz0cCUiZYYxUWEEIH8JybA";
    public static final String REST_CONSUMER_SECRET = "KXKWMTglNXhpZnykfdR7agXyumb1YDFXfP5z13HNr6NWUGfxhF"; 
    public static final String REST_CALLBACK_URL = "oauth://cpbasictweets"; 
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeLine(AsyncHttpResponseHandler handler, Long maxId) {
        String apiUrl = getApiUrl("/statuses/home_timeline.json");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("count", "25");
        if (maxId != -1) {
	    	params.put("max_id", Long.toString(maxId));
    	}
    	client.get(apiUrl, params, handler);
    }
    
   
    public void composeTweet(String tweetText,
    		AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/update.json");
    	RequestParams params = new RequestParams();
    	params.put("status", tweetText);
    	client.post(url, params, handler);
    }
}