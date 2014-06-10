package com.exercise.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar;
import android.widget.TextView;

public class TipCalculator extends Activity {

	private static int percentValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		setPercentValue();
		setUpBillAmountTextChangeListener();
		setUpSeekBarChangeListener();
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
	
	private void setUpSeekBarChangeListener() {
		SeekBar percentageBar = (SeekBar) findViewById(R.id.tipPercentageId);
		percentageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { 
		   @Override 
		   public void onProgressChanged(SeekBar seekBar, int progress, 
		     boolean fromUser) { 
			   percentValue = progress;
			   setPercentValue();
			   setTipAmount();
		   } 

		   @Override 
		   public void onStartTrackingTouch(SeekBar seekBar) { 
		    // TODO Auto-generated method stub 
		   } 

		   @Override 
		   public void onStopTrackingTouch(SeekBar seekBar) { 
		    // TODO Auto-generated method stub 
		   } 
       }); 
   } 
	
	private void setPercentValue() {
		TextView percentageValueText = (TextView) findViewById(R.id.percentageValueTextId);
		   percentageValueText.setText(percentValue + "%");
	}
	
	private void setTipAmount() {
		TextView billAmountText = (TextView) findViewById(R.id.billAmtId);
		TextView computedAmtText = (TextView) findViewById(R.id.computedAmountId);
		computedAmtText.setText("");
		String billAmount = billAmountText.getText().toString();
    	String computedTip = formatCurrency(billAmount, percentValue);
    	computedAmtText.setText(computedTip);

	}
	
	private String formatCurrency(String billAmount, double percentValue) {
		double computedTip = Float.valueOf(billAmount) * (percentValue/100);
		String formattedAmount;
        formattedAmount = String.format("$%.2f", computedTip);
		return formattedAmount;
    	
	}
}
