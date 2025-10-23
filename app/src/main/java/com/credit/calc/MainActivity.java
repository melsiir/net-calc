package com.credit.calc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	
	EditText etAmount, etDiscount;
	TextView tvResult;
	Button btnCalculate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etAmount = findViewById(R.id.etAmount);
		etDiscount = findViewById(R.id.etDiscount);
		btnCalculate = findViewById(R.id.btnCalculate);
		tvResult = findViewById(R.id.tvResult);
		
		btnCalculate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				calculateNetAmount();
			}
		});
	}
	
	private void calculateNetAmount() {
		String amountStr = etAmount.getText().toString().trim();
		String discountStr = etDiscount.getText().toString().trim();
		
		if (amountStr.isEmpty() || discountStr.isEmpty()) {
			Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show();
			return;
		}
		
		double amount = Double.parseDouble(amountStr);
		double discountPercent = Double.parseDouble(discountStr);
		
		double discountValue = amount * (discountPercent / 100);
		double netAmount = amount - discountValue;
		
		tvResult.setText("Net Amount: " + String.format("%.2f", netAmount));
	}
}