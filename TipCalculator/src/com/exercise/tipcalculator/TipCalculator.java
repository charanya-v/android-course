package com.exercise.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TipCalculator extends Activity {

	private static Button lastClickedButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		setUpBillAmountTextChangeListener();
		
	}
	
	public void onClickTipAmountTenPercent(View v) {
		lastClickedButton = (Button) findViewById(R.id.tenPercentId);
		setTipAmount();
	}
	
	public void onClickTipAmountFifteenPercent(View v) {
		lastClickedButton = (Button) findViewById(R.id.fifteenPercentId);
		setTipAmount();
	}
	
	public void onClickTipAmountTwentyPercent(View v) {
		lastClickedButton = (Button) findViewById(R.id.twentyPercentId);
		setTipAmount();
	}
	
	private void setUpBillAmountTextChangeListener() {
		TextView billAmountText = (TextView) findViewById(R.id.billAmtId);
		billAmountText.addTextChangedListener(new TextWatcher() {

	        @Override
	        public void afterTextChanged(Editable s) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            setTipAmount();
	        } 

	    });
	}
	
	private void setTipAmount() {
		TextView billAmountText = (TextView) findViewById(R.id.billAmtId);
		TextView computedAmtText = (TextView) findViewById(R.id.computedAmountId);
		double percentValue = 0;
		if (null != lastClickedButton && null != billAmountText.getText()) {
			switch(lastClickedButton.getId()) {
				case R.id.tenPercentId:
					percentValue = 0.1;
					break;
					
				case R.id.fifteenPercentId:
					percentValue = 0.15;
					break;
					
				case R.id.twentyPercentId:
					percentValue = 0.2;
					break;
			}
			computedAmtText.setText("");
			String billAmount = billAmountText.getText().toString();
	    	String computedTip = formatCurrency(billAmount, percentValue);
	    	computedAmtText.setText(computedTip);    
		}

	}
	
	private String formatCurrency(String billAmount, double percentValue) {
		double computedTip = Float.valueOf(billAmount) * percentValue;
		String formattedAmount;
        formattedAmount = String.format("$%.2f", computedTip);
		return formattedAmount;
    	
	}
}
