package com.exercise.android.googleimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class ImageSearchSettingsActivity extends Activity {
	private static final String SEARCH_KEY = "searchSettings";
	private static final ImageSearchSettings searchSettings = new ImageSearchSettings();
	Spinner imageSizeSpinner;
	Spinner colorSpinner;
	Spinner imageTypeSpinner;
	EditText sitesEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search_settings);
		setupViews();
	}
	
	
	public void onSave(View v) {
		Intent i = new Intent();
		searchSettings.setSiteFilter(sitesEditText.getText().toString());
		i.putExtra(SEARCH_KEY, searchSettings);
		setResult(RESULT_OK, i);
		finish();
		
	}
	
	public void setupViews() {
		Spinner sizeSpinner = (Spinner) findViewById(R.id.imageSizeSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.size_filters_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		sizeSpinner.setAdapter(sizeAdapter);
		setSpinnerToValue(sizeSpinner, searchSettings.getImageSize());
		sizeSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				searchSettings.setImageSize(parent.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		Spinner typeSpinner = (Spinner) findViewById(R.id.imageTypeSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.type_filters_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		typeSpinner.setAdapter(typeAdapter);
		setSpinnerToValue(typeSpinner, searchSettings.getImageType());
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				searchSettings.setImageType(parent.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
		        R.array.color_filters_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		colorSpinner.setAdapter(colorAdapter);
		setSpinnerToValue(colorSpinner, searchSettings.getColorFilter());
		colorSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				searchSettings.setColorFilter(parent.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sitesEditText = (EditText) findViewById(R.id.sitesEditText);
	}
	
	public void setSpinnerToValue(Spinner spinner, String value) {
		int index = 0;
		SpinnerAdapter adapter = spinner.getAdapter();
		for (int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).equals(value)) {
				index = i;
			}
		}
		spinner.setSelection(index);
	}

	
}
