package com.codepath.apps.basic.twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basic.twitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
	final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
	
	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tweet tweet = getItem(position);
		View view;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(R.layout.tweet_item, parent, false);
		} else {
			view = convertView;
		}
		
		ImageView ivProfileImage = (ImageView) view.findViewById(R.id.ivProfileImage);
		TextView tvUserName = (TextView) view.findViewById(R.id.tvUserName);
		TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
		TextView tvRelativeTime = (TextView) view.findViewById(R.id.tvRelativeTime);
		Date date;
		long milliseconds = 0;
		try {
			date = new SimpleDateFormat(TWITTER, Locale.ENGLISH).parse(tweet.getCreatedAt());
			milliseconds = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = null;
		}
				
		CharSequence relativeDate = DateUtils.getRelativeDateTimeString(
		        this.getContext(), // Suppose you are in an activity or other Context subclass
		        milliseconds,
		        DateUtils.MINUTE_IN_MILLIS, // The resolution. This will display only 
		                                        // minutes (no "3 seconds ago") 
		        DateUtils.WEEK_IN_MILLIS, // The maximum resolution at which the time will switch 
		                         // to default date instead of spans. This will not 
		                         // display "3 weeks ago" but a full date instead
		        0); // Eventual flags

		
		ivProfileImage.setImageResource(android.R.color.transparent);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
		tvUserName.setText(tweet.getUser().getScreenName());
		tvBody.setText(tweet.getBody());
		tvRelativeTime.setText(relativeDate);
		return view;
	}
	
	

}
