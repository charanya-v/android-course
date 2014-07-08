package com.codepath.apps.basic.twitter;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {
	private TwitterClient client;
	private EditText etTweet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		etTweet = (EditText) findViewById(R.id.etTweet);
		client = TwitterApplication.getRestClient();
	}

	public void onPost(View v) {
		String tweetText = etTweet.getText().toString();

		client.composeTweet(tweetText, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonTweet) {
				Intent i = new Intent();
				i.putExtra("jsonTweet", jsonTweet.toString());
				setResult(RESULT_OK, i);
				finish();
				Log.d("DEBUG", jsonTweet.toString());
			}
		});
	}

	public void onCancel(View v) {
		setResult(RESULT_CANCELED, null);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_tweet, menu);
		return true;
	}

}
